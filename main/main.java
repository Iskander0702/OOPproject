package OOPproject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║         OOP PROJECT — DEMO               ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // ── 1. Create shared user registry ───────────────────────────────────
        List<User> registry = new ArrayList<>();

        // ── 2. Create Admin ───────────────────────────────────────────────────
        Admin admin = new Admin(
                "admin01", "adminPass123",
                "Alice", "Smith",
                "alice@company.com",
                120_000, "Management",
                LocalDate.of(2018, 3, 15),
                registry
        );
        registry.add(admin);

        // ── 3. Create Employees ───────────────────────────────────────────────
        // Employee is abstract, so we use a minimal anonymous subclass
        // to simulate a plain employee (in a real project you'd have a
        // concrete Employee subclass like Manager, Developer, etc.)
        Employee emp1 = new Employee(
                "john_doe", "pass1234",
                "John", "Doe",
                "john@company.com",
                75_000, "Engineering",
                LocalDate.of(2020, 6, 1)
        ) {
            @Override
            public void openMenu() {
                System.out.println("[John's menu — not implemented in demo]");
            }
        };

        Employee emp2 = new Employee(
                "jane_roe", "securePass",
                "Jane", "Roe",
                "jane@company.com",
                82_000, "Design",
                LocalDate.of(2021, 9, 10)
        ) {
            @Override
            public void openMenu() {
                System.out.println("[Jane's menu — not implemented in demo]");
            }
        };

        // ── 4. Admin: addUser ─────────────────────────────────────────────────
        separator("4. addUser");
        admin.addUser(emp1);
        admin.addUser(emp2);
        admin.addUser(emp1); // duplicate — should warn

        // ── 5. viewAllUsers ───────────────────────────────────────────────────
        separator("5. viewAllUsers");
        admin.viewAllUsers();

        // ── 6. findByLogin ────────────────────────────────────────────────────
        separator("6. findByLogin");
        User found = admin.findByLogin("john_doe");
        System.out.println("Found: " + found);
        User notFound = admin.findByLogin("ghost");
        System.out.println("Not found result: " + notFound);

        // ── 7. updateUser ─────────────────────────────────────────────────────
        separator("7. updateUser");
        admin.updateUser("john_doe", "Jonathan", "Doe-Updated", null);
        System.out.println("After update: " + admin.findByLogin("john_doe"));

        // ── 8. Authentication (User) ──────────────────────────────────────────
        separator("8. authenticate");
        System.out.println("emp1 correct password : " + emp1.authenticate("pass1234"));
        System.out.println("emp1 wrong   password : " + emp1.authenticate("wrong"));

        // ── 9. changePassword (User) ──────────────────────────────────────────
        separator("9. changePassword");
        boolean changed = emp1.changePassword("pass1234", "newPass999");
        System.out.println("Password changed: " + changed);
        System.out.println("Auth with new password: " + emp1.authenticate("newPass999"));

        // ── 10. resetPassword (Admin) ─────────────────────────────────────────
        separator("10. resetPassword");
        admin.resetPassword("jane_roe", "resetSecret");
        System.out.println("Jane auth with reset pwd: " + emp2.authenticate("resetSecret"));

        // ── 11. Messaging (Employee) ──────────────────────────────────────────
        separator("11. sendMessage / viewInbox");
        admin.sendMessage(emp1, "Welcome", "Hi Jonathan, welcome to the team!");
        admin.sendMessage(emp2, "Project kick-off", "Meeting at 10am tomorrow.");
        emp1.sendMessage(admin, "Re: Welcome", "Thanks Alice!");

        System.out.println("\n-- Admin inbox --");
        admin.viewInbox();
        System.out.println("\n-- John's inbox --");
        emp1.viewInbox();

        // ── 12. sendComplaint (Employee) ──────────────────────────────────────
        separator("12. sendComplaint");
        emp2.sendComplaint(admin, "The office AC is broken for 3 days.");
        System.out.println("\n-- Admin inbox after complaint --");
        admin.viewInbox();

        // ── 13. unreadCount ───────────────────────────────────────────────────
        separator("13. unreadCount");
        // Send a new message that admin hasn't read yet
        emp1.sendMessage(admin, "Question", "Can I WFH on Friday?");
        System.out.println("Admin unread messages: " + admin.unreadCount());

        // ── 14. getFullName / toString ────────────────────────────────────────
        separator("14. getFullName / toString");
        System.out.println("Admin full name : " + admin.getFullName());
        System.out.println("Admin toString  : " + admin);
        System.out.println("emp2 toString   : " + emp2);

        // ── 15. equals / getUserId ────────────────────────────────────────────
        separator("15. equals / getUserId");
        System.out.println("admin.equals(admin): " + admin.equals(admin));
        System.out.println("admin.equals(emp1) : " + admin.equals(emp1));
        System.out.println("admin userId       : " + admin.getUserId());

        // ── 16. getUserRegistry (unmodifiable) ────────────────────────────────
        separator("16. getUserRegistry");
        System.out.println("Registry size: " + admin.getUserRegistry().size());
        try {
            admin.getUserRegistry().add(emp2); // should throw
        } catch (UnsupportedOperationException e) {
            System.out.println("✔  Registry is unmodifiable (expected).");
        }

        // ── 17. removeUser ────────────────────────────────────────────────────
        separator("17. removeUser");
        admin.removeUser("jane_roe");
        admin.removeUser("ghost"); // not found — should warn
        admin.viewAllUsers();

        // ── 18. viewLog ───────────────────────────────────────────────────────
        separator("18. viewLog");
        admin.viewLog();

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║           DEMO COMPLETE                  ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    // ── Helper ────────────────────────────────────────────────────────────────
    private static void separator(String title) {
        System.out.println("\n──── " + title + " " + "─".repeat(Math.max(0, 40 - title.length())));
    }
}