package uiPayroll;
import com.employee.util.TestConnection;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Employee Login System Starting ===");
        
        // Test database connection first
        System.out.println("Testing database connection...");
        TestConnection.main(args);
        
        // Launch login form
        System.out.println("Launching login form...");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginForm loginForm = new LoginForm();
                    loginForm.setVisible(true);
                    System.out.println("✅ Login form launched successfully!");
                } catch (Exception e) {
                    System.out.println("❌ Error launching login form: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        
        System.out.println("=== Application startup completed ===");
    }
}
