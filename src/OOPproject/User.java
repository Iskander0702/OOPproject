package OOPproject;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;
    // ── Identity ──────────────────────────────────────────────────────────────
    private final String userId;      // immutable UUID assigned at creation
    private String login;
    private String password;          // stored as a simple hash (SHA-256 hex)
    private String firstName;
    private String lastName;
    private String email;
    // ── Constructor ───────────────────────────────────────────────────────────
    public User(String login, String password,
                String firstName, String lastName, String email) {
        this.userId    = UUID.randomUUID().toString();
        this.login     = login;
        this.password  = hashPassword(password);
        this.firstName = firstName;
        this.lastName  = lastName;
        this.email     = email;
    }
    // ── Authentication ────────────────────────────────────────────────────────
    public boolean authenticate(String plainPassword) {
        return this.password.equals(hashPassword(plainPassword));
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        if (!authenticate(oldPassword)) return false;
        this.password = hashPassword(newPassword);
        return true;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public abstract void openMenu();
    // ── Private helpers ───────────────────────────────────────────────────────
    private static String hashPassword(String plain) {
        try {
            java.security.MessageDigest md =
                    java.security.MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(plain.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            // SHA-256 is guaranteed to exist in every JVM
            throw new RuntimeException("SHA-256 not available", e);
        }
    }
    // ── Getters & Setters ─────────────────────────────────────────────────────
    public String getUserId()    { return userId; }
    public String getLogin()     { return login; }
    public String getFirstName() { return firstName; }
    public String getLastName()  { return lastName; }
    public String getEmail()     { return email; }

    public void setLogin(String login)         { this.login = login; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName)   { this.lastName = lastName; }
    public void setEmail(String email)         { this.email = email; }

    void setPasswordHash(String hash) { this.password = hash; }

    // ── Object overrides ──────────────────────────────────────────────────────
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
    @Override
    public String toString() {
        return String.format("[%s] %s %s | login: %s | email: %s",
                getClass().getSimpleName(), firstName, lastName, login, email);
    }
}
