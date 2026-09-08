package uiPayroll;
import Dao.User;
import Dao.UserDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUpForm extends JFrame {

    private JLabel lblTitle, lblFullName, lblEmail, lblPassword;
    private JTextField txtFullName, txtEmail;
    private JPasswordField txtPassword;
    private JButton btnRegister, btnBackToLogin;

    public SignUpForm() {
        setTitle("Employee System - Sign Up");
        setSize(450, 380);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.white);

        // Title
        lblTitle = new JLabel("Create New Account");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setBounds(100, 20, 300, 30);
        add(lblTitle);

        // Full Name
        lblFullName = new JLabel("Full Name:");
        lblFullName.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblFullName.setBounds(50, 80, 100, 25);
        add(lblFullName);

        txtFullName = new JTextField();
        txtFullName.setBounds(160, 80, 220, 25);
        add(txtFullName);

        // Email
        lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblEmail.setBounds(50, 130, 100, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(160, 130, 220, 25);
        add(txtEmail);

        // Password
        lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblPassword.setBounds(50, 180, 100, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(160, 180, 220, 25);
        add(txtPassword);

        // Buttons
        btnRegister = new JButton("Register");
        btnRegister.setBounds(80, 250, 120, 30);
        btnRegister.setBackground(new Color(0, 149, 246));
        btnRegister.setForeground(Color.white);
        btnRegister.setFocusPainted(false);
        add(btnRegister);

        btnBackToLogin = new JButton("Back to Login");
        btnBackToLogin.setBounds(240, 250, 140, 30);
        btnBackToLogin.setBackground(new Color(200, 50, 50));
        btnBackToLogin.setForeground(Color.white);
        btnBackToLogin.setFocusPainted(false);
        add(btnBackToLogin);

        // Register button action
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fullName = txtFullName.getText().trim();
                String email = txtEmail.getText().trim();
                String password = new String(txtPassword.getPassword()).trim();

                if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields");
                    return;
                }

                if (!email.contains("@") || !email.contains(".")) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid email address");
                    return;
                }

                if (password.length() < 6) {
                    JOptionPane.showMessageDialog(null, "Password must be at least 6 characters long");
                    return;
                }

                UserDAO userDAO = new UserDAO();
                User newUser = new User(fullName, email, password);
                boolean success = userDAO.registerUser(newUser);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Registration Successful! You can now login.");
                    LoginForm loginForm = new LoginForm();
                    loginForm.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Registration failed. Email may already exist.");
                }
            }
        });

        // Back to login action
        btnBackToLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginForm loginForm = new LoginForm();
                loginForm.setVisible(true);
                dispose();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new SignUpForm();
    }
}
