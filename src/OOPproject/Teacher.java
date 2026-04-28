package OOPproject;
public class Teacher extends Employee {
    private static final long serialVersionUID = 1L;
    // ── Additional Attributes ─────────────────────────────────────────────────
    private String department;
    // ── Constructor ───────────────────────────────────────────────────────────
    public Teacher(String login, String password,
                   String firstName, String lastName, String email,
                   String department) {
        super(login, password, firstName, lastName, email, 0.0, department, null);
        this.department = department;
    }
    // ── Override Methods ───────────────────────────────────────────────────────
    @Override
    public void openMenu() {
        System.out.println("Welcome, " + getFullName() + " (Teacher)");
        System.out.println("1. View Courses");
        System.out.println("2. Grade Assignments");
        System.out.println("3. Send Messages");
        System.out.println("4. Logout");
    }
    // ── Getters & Setters ─────────────────────────────────────────────────────
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}