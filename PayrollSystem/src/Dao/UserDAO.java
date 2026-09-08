package Dao;
import com.employee.util.DBConnection;
import java.sql.*;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    
    // Email validation pattern
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);
    
    // Method to register a new user (Sign-Up)
    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (full_name, email, password) VALUES (?, ?, ?)";
        
        // Input validations
        if (user.getFullName() == null || user.getFullName().trim().isEmpty()) {
            System.out.println("Error: Full Name cannot be empty");
            return false;
        }
        
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            System.out.println("Error: Email cannot be empty");
            return false;
        }
        
        if (!isValidEmail(user.getEmail())) {
            System.out.println("Error: Invalid email format");
            return false;
        }
        
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            System.out.println("Error: Password cannot be empty");
            return false;
        }
        
        if (user.getPassword().length() < 6) {
            System.out.println("Error: Password must be at least 6 characters long");
            return false;
        }
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, user.getFullName().trim());
            pstmt.setString(2, user.getEmail().trim().toLowerCase());
            pstmt.setString(3, user.getPassword()); // In real app, hash the password
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // MySQL duplicate entry error code
                System.out.println("Error: Email already exists");
            } else {
                System.out.println("Database error: " + e.getMessage());
            }
            return false;
        }
    }
    
    // Method to authenticate user (Sign-In)
    public boolean loginUser(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        
        // Input validations
        if (email == null || email.trim().isEmpty()) {
            System.out.println("Error: Email cannot be empty");
            return false;
        }
        
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Error: Password cannot be empty");
            return false;
        }
        
        if (!isValidEmail(email)) {
            System.out.println("Error: Invalid email format");
            return false;
        }
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, email.trim().toLowerCase());
            pstmt.setString(2, password); // In real app, compare hashed passwords
            
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returns true if user exists with given credentials
            
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
            return false;
        }
    }
    
    // Method to check if email already exists
    public boolean isEmailExists(String email) {
        String sql = "SELECT id FROM users WHERE email = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, email.trim().toLowerCase());
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
            
        } catch (SQLException e) {
            System.out.println("Error checking email: " + e.getMessage());
            return false;
        }
    }
    
    // Email validation method
    private boolean isValidEmail(String email) {
        return pattern.matcher(email).matches();
    }
    
    // Method to get user by email (for testing)
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, email.trim().toLowerCase());
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                
                // New fields - handle potential NULL values
                user.setSalary(rs.getDouble("salary"));
                if (rs.wasNull()) user.setSalary(0.0);
                
                user.setPhone(rs.getString("phone"));
                user.setDepartment(rs.getString("department"));
                user.setPosition(rs.getString("position"));
                
                return user;
            }
            
        } catch (SQLException e) {
            System.out.println("Error getting user: " + e.getMessage());
        }
        
        return null;
    }
    
    // Method to get user by ID
    public User getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                
                // New fields - handle potential NULL values
                user.setSalary(rs.getDouble("salary"));
                if (rs.wasNull()) user.setSalary(0.0);
                
                user.setPhone(rs.getString("phone"));
                user.setDepartment(rs.getString("department"));
                user.setPosition(rs.getString("position"));
                
                return user;
            }
            
        } catch (SQLException e) {
            System.out.println("Error getting user by ID: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    // Method to delete user by ID
    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // Method to update user details
    public boolean updateUserDetails(User user) {
        String sql = "UPDATE users SET salary = ?, phone = ?, department = ?, position = ? WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDouble(1, user.getSalary());
            pstmt.setString(2, user.getPhone());
            pstmt.setString(3, user.getDepartment());
            pstmt.setString(4, user.getPosition());
            pstmt.setInt(5, user.getId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.out.println("Error updating user details: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // Method to get all users (for admin view)
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY created_at DESC";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword("********");
                
                // New fields - handle potential NULL values
                user.setSalary(rs.getDouble("salary"));
                if (rs.wasNull()) user.setSalary(0.0);
                
                user.setPhone(rs.getString("phone"));
                user.setDepartment(rs.getString("department"));
                user.setPosition(rs.getString("position"));
                
                users.add(user);
            }
            
        } catch (SQLException e) {
            System.out.println("Error getting users: " + e.getMessage());
            e.printStackTrace();
        }
        
        return users;
    }
}
