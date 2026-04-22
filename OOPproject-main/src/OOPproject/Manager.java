package OOPproject;
public class Manager extends Employee {
    private static final long serialVersionUID = 1L;
    // ── Additional Attributes ─────────────────────────────────────────────────
    private String officeLocation;
    // ── Constructor ───────────────────────────────────────────────────────────
    public Manager(String login, String password,
                   String firstName, String lastName, String email,
                   double salary, String department, String officeLocation) {
        super(login, password, firstName, lastName, email, salary, department, null);
        this.officeLocation = officeLocation;
    }
    // ── Override Methods ───────────────────────────────────────────────────────
    @Override
    public void openMenu() {
        System.out.println("Welcome, " + getFullName() + " (Manager)");
        System.out.println("1. View Team");
        System.out.println("2. Approve Requests");
        System.out.println("3. Send Messages");
        System.out.println("4. Logout");
    }
    // ── Getters & Setters ─────────────────────────────────────────────────────
    public String getOfficeLocation() { return officeLocation; }
    public void setOfficeLocation(String officeLocation) { this.officeLocation = officeLocation; }
    
}
