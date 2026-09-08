package Dao;
public class User {
    private int id;
    private String fullName;
    private String email;
    private String password;
    private double salary;
    private String phone;
    private String department;
    private String position;
    
    // Constructors
    public User() {}
    
    public User(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }
    
    public User(String fullName, String email, String password, double salary, String phone, String department, String position) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.salary = salary;
        this.phone = phone;
        this.department = department;
        this.position = position;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
}