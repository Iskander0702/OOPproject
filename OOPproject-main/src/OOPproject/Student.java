package OOPproject;
public class Student extends User {
    private static final long serialVersionUID = 1L;
    // ── Additional Attributes ─────────────────────────────────────────────────
    private String major;
    // ── Constructor ───────────────────────────────────────────────────────────
    public Student(String login, String password,
                   String firstName, String lastName, String email,
                   String major) {
        super(login, password, firstName, lastName, email);
        this.major = major;
    }
    // ── Override Methods ───────────────────────────────────────────────────────
    @Override
    public void openMenu() {
        System.out.println("Welcome, " + getFullName() + " (Student)");
        System.out.println("1. View Enrolled Courses");
        System.out.println("2. Submit Assignments");
        System.out.println("3. View Grades");
        System.out.println("4. Logout");
    }
    // ── Getters & Setters ─────────────────────────────────────────────────────
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
}