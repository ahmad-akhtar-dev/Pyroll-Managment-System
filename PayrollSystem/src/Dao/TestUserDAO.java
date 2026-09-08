package Dao;
public class TestUserDAO {
    public static void main(String[] args) {
        System.out.println("=== Starting UserDAO Test ===\n");
        
        UserDAO userDAO = new UserDAO();
        
        try {
            // Test 1: Register a new user
            System.out.println("1. Testing User Registration:");
            User newUser = new User("John Doe", "john.doe@email.com", "password123");
            System.out.println("   Creating user: " + newUser.getFullName());
            boolean registrationSuccess = userDAO.registerUser(newUser);
            System.out.println("   Registration result: " + (registrationSuccess ? "SUCCESS" : "FAILED"));
            
            // Small delay to ensure database operations complete
            Thread.sleep(500);
            
            // Test 2: Try to register with same email (should fail)
            System.out.println("\n2. Testing Duplicate Email Registration:");
            User duplicateUser = new User("John Doe", "john.doe@email.com", "password123");
            boolean duplicateResult = userDAO.registerUser(duplicateUser);
            System.out.println("   Duplicate registration result: " + (duplicateResult ? "SUCCESS" : "FAILED (Expected)"));
            
            Thread.sleep(500);
            
            // Test 3: Test login with correct credentials
            System.out.println("\n3. Testing Login with Correct Credentials:");
            boolean loginSuccess = userDAO.loginUser("john.doe@email.com", "password123");
            System.out.println("   Login result: " + (loginSuccess ? "SUCCESS" : "FAILED"));
            
            // Test 4: Test login with wrong password
            System.out.println("\n4. Testing Login with Wrong Password:");
            boolean wrongPasswordLogin = userDAO.loginUser("john.doe@email.com", "wrongpassword");
            System.out.println("   Wrong password login result: " + (wrongPasswordLogin ? "SUCCESS" : "FAILED (Expected)"));
            
            // Test 5: Test email validation
            System.out.println("\n5. Testing Email Validation:");
            boolean emailExists = userDAO.isEmailExists("john.doe@email.com");
            boolean emailNotExists = userDAO.isEmailExists("nonexistent@email.com");
            System.out.println("   Existing email check: " + emailExists);
            System.out.println("   Non-existent email check: " + emailNotExists);
            
            // Test 6: Test invalid inputs
            System.out.println("\n6. Testing Invalid Inputs:");
            User invalidUser = new User("", "invalid-email", "123");
            boolean invalidResult = userDAO.registerUser(invalidUser);
            System.out.println("   Invalid input registration result: " + (invalidResult ? "SUCCESS" : "FAILED (Expected)"));
            
            System.out.println("\n=== UserDAO Test Completed ===");
            
        } catch (Exception e) {
            System.out.println("❌ ERROR during test: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
