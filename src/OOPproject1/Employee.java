package OOPproject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Employee extends User {
    private static final long serialVersionUID = 1L;
    // ── Employment data ───────────────────────────────────────────────────────
    private double    salary;
    private String    department;
    private LocalDate hireDate;
    // ── Inbox ─────────────────────────────────────────────────────────────────
    private final List<Message> inbox = new ArrayList<>();
    // ── Constructor ───────────────────────────────────────────────────────────
    public Employee(String login, String password,
                    String firstName, String lastName, String email,
                    double salary, String department, LocalDate hireDate) {
        super(login, password, firstName, lastName, email);
        this.salary     = salary;
        this.department = department;
        this.hireDate   = hireDate;
    }
    // ── Messaging ─────────────────────────────────────────────────────────────
    public void sendMessage(Employee recipient, String subject, String body) {
        Message msg = new Message(this.getLogin(), recipient.getLogin(),
                                  subject, body, false);
        recipient.receiveMessage(msg);
        System.out.println("✉  Message sent to " + recipient.getFullName());
    }

    public void sendComplaint(Employee recipient, String body) {
        Message complaint = new Message(this.getLogin(), recipient.getLogin(),
                                        "Complaint", body, true);
        recipient.receiveMessage(complaint);
        System.out.println("📋 Complaint sent to " + recipient.getFullName());
    }

    public void receiveMessage(Message message) {
        inbox.add(message);
    }

    public void viewInbox() {
        if (inbox.isEmpty()) {
            System.out.println("Inbox is empty.");
            return;
        }
        System.out.println("=== Inbox for " + getFullName() + " ===");
        for (Message m : inbox) {
            System.out.println(m);
            m.markAsRead();
        }
    }
    public long unreadCount() {
        return inbox.stream().filter(m -> !m.isRead()).count();
    }

    public List<Message> getInbox() {
        return Collections.unmodifiableList(inbox);
    }
    // ── Getters & Setters ─────────────────────────────────────────────────────
    public double    getSalary()     { return salary; }
    public String    getDepartment() { return department; }
    public LocalDate getHireDate()   { return hireDate; }
    public void setSalary(double salary)        { this.salary = salary; }
    public void setDepartment(String department){ this.department = department; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }
    // ── Object overrides ──────────────────────────────────────────────────────
    @Override
    public boolean equals(Object o) { return super.equals(o); }
    @Override
    public int hashCode() { return super.hashCode(); }
    @Override
    public String toString() {
        return super.toString() +
               String.format(" | dept: %s | hired: %s | salary: %.2f",
                              department, hireDate, salary);
    }
}
