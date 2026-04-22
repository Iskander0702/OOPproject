package OOPproject;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Admin extends Employee {
    private static final long   serialVersionUID = 1L;
    private static final String LOG_FILE         = "logs/action_log.txt";
    private static final DateTimeFormatter LOG_FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final List<User> userRegistry;
    // ── Constructor ───────────────────────────────────────────────────────────
    public Admin(String login, String password,
                 String firstName, String lastName, String email,
                 double salary, String department, LocalDate hireDate,
                 List<User> userRegistry) {
        super(login, password, firstName, lastName, email,
              salary, department, hireDate);
        this.userRegistry = userRegistry;
        ensureLogDirectoryExists();
    }
    // ── Console menu (openMenu contract from User) ────────────────────────────
    @Override
    public void openMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║       ADMIN PANEL            ║");
            System.out.printf( "║  Logged in as: %-13s ║%n", getLogin());
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║ 1. Add user                  ║");
            System.out.println("║ 2. Remove user               ║");
            System.out.println("║ 3. Update user info          ║");
            System.out.println("║ 4. Reset user password       ║");
            System.out.println("║ 5. View all users            ║");
            System.out.println("║ 6. Find user by login        ║");
            System.out.println("║ 7. View action log           ║");
            System.out.println("║ 8. View my inbox             ║");
            System.out.println("║ 0. Logout                    ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Choice: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> menuAddUser(scanner);
                case "2" -> menuRemoveUser(scanner);
                case "3" -> menuUpdateUser(scanner);
                case "4" -> menuResetPassword(scanner);
                case "5" -> viewAllUsers();
                case "6" -> menuFindUser(scanner);
                case "7" -> viewLog();
                case "8" -> viewInbox();
                case "0" -> {
                    log("LOGOUT", getLogin(), "Admin logged out");
                    running = false;
                    System.out.println("Goodbye, " + getFullName() + "!");
                }
                default  -> System.out.println("⚠  Invalid option. Try again.");
            }
        }
    }
    // ── User management ───────────────────────────────────────────────────────
    public boolean addUser(User user) {
        if (findByLogin(user.getLogin()) != null) {
            System.out.println("⚠  Login '" + user.getLogin() + "' is already taken.");
            return false;
        }
        userRegistry.add(user);
        log("ADD_USER", getLogin(),
            "Added user: " + user.getLogin() + " (" + user.getClass().getSimpleName() + ")");
        System.out.println("✔  User '" + user.getLogin() + "' added successfully.");
        return true;
    }
    public boolean removeUser(String login) {
        User target = findByLogin(login);
        if (target == null) {
            System.out.println("⚠  User '" + login + "' not found.");
            return false;
        }
        userRegistry.remove(target);
        log("REMOVE_USER", getLogin(), "Removed user: " + login);
        System.out.println("✔  User '" + login + "' removed.");
        return true;
    }

    public boolean updateUser(String login, String firstName,
                              String lastName, String email) {
        User target = findByLogin(login);
        if (target == null) {
            System.out.println("⚠  User '" + login + "' not found.");
            return false;
        }
        if (firstName != null && !firstName.isBlank()) target.setFirstName(firstName);
        if (lastName  != null && !lastName.isBlank())  target.setLastName(lastName);
        if (email     != null && !email.isBlank())     target.setEmail(email);

        log("UPDATE_USER", getLogin(), "Updated profile for: " + login);
        System.out.println("✔  User '" + login + "' updated.");
        return true;
    }

    public boolean resetPassword(String login, String newPassword) {
        User target = findByLogin(login);
        if (target == null) {
            System.out.println("⚠  User '" + login + "' not found.");
            return false;
        }
        // Re-hash via the package-private setter on User
        try {
            java.security.MessageDigest md =
                    java.security.MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(
                    newPassword.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) sb.append(String.format("%02x", b));
            target.setPasswordHash(sb.toString());
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not available", e);
        }
        log("RESET_PASSWORD", getLogin(), "Password reset for: " + login);
        System.out.println("✔  Password reset for '" + login + "'.");
        return true;
    }

    public void viewAllUsers() {
        if (userRegistry.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }
        System.out.println("\n=== All Users (" + userRegistry.size() + ") ===");
        for (User u : userRegistry) System.out.println(u);
        log("VIEW_USERS", getLogin(), "Viewed all users");
    }

    public User findByLogin(String login) {
        return userRegistry.stream()
                           .filter(u -> u.getLogin().equals(login))
                           .findFirst()
                           .orElse(null);
    }

    public List<User> getUserRegistry() {
        return Collections.unmodifiableList(userRegistry);
    }
    // ── Action log ────────────────────────────────────────────────────────────

    public void log(String action, String actor, String detail) {
        String entry = String.format("%s | %-15s | %-12s | %s%n",
                LocalDateTime.now().format(LOG_FMT), action, actor, detail);
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(LOG_FILE, true))) {
            writer.write(entry);
        } catch (IOException e) {
            System.err.println("⚠  Could not write to log: " + e.getMessage());
        }
    }

    public void viewLog() {
        Path path = Paths.get(LOG_FILE);
        if (!Files.exists(path)) {
            System.out.println("No log file found yet.");
            return;
        }
        System.out.println("\n=== Action Log ===");
        try {
            List<String> lines = Files.readAllLines(path);
            if (lines.isEmpty()) {
                System.out.println("(empty)");
            } else {
                lines.forEach(System.out::println);
            }
        } catch (IOException e) {
            System.err.println("⚠  Could not read log: " + e.getMessage());
        }
    }
    // ── Private menu helpers ──────────────────────────────────────────────────
    private void menuAddUser(Scanner sc) {
        System.out.println("-- Add User (creates a generic placeholder) --");
        System.out.println("Note: In production, pass a fully constructed subclass.");
        System.out.print("This demo requires object injection. Returning to menu.\n");
        // Real implementation: receive a pre-built User from a factory / builder.
    }
    private void menuRemoveUser(Scanner sc) {
        System.out.print("Enter login to remove: ");
        removeUser(sc.nextLine().trim());
    }
    private void menuUpdateUser(Scanner sc) {
        System.out.print("Enter login to update: ");
        String login = sc.nextLine().trim();
        System.out.print("New first name (leave blank to keep): ");
        String fn = sc.nextLine().trim();
        System.out.print("New last name  (leave blank to keep): ");
        String ln = sc.nextLine().trim();
        System.out.print("New email      (leave blank to keep): ");
        String em = sc.nextLine().trim();
        updateUser(login,
                   fn.isEmpty() ? null : fn,
                   ln.isEmpty() ? null : ln,
                   em.isEmpty() ? null : em);
    }
    private void menuResetPassword(Scanner sc) {
        System.out.print("Enter login: ");
        String login = sc.nextLine().trim();
        System.out.print("New password: ");
        String pwd = sc.nextLine().trim();
        resetPassword(login, pwd);
    }
    private void menuFindUser(Scanner sc) {
        System.out.print("Enter login to find: ");
        User u = findByLogin(sc.nextLine().trim());
        System.out.println(u != null ? u : "⚠  Not found.");
    }
    private void ensureLogDirectoryExists() {
        try {
            Files.createDirectories(Paths.get("logs"));
        } catch (IOException e) {
            System.err.println("⚠  Could not create logs directory: " + e.getMessage());
        }
    }
    // ── Object overrides ──────────────────────────────────────────────────────
    @Override
    public String toString() {
        return super.toString() + " [ADMIN]";
    }
}
