package uiPayroll;
import Dao.UserDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginForm extends JFrame {

    private JButton btnLogin, btnSignUp;
    private JLabel lblEmail, lblPassword, lblTitle;
    private JTextField txtEmail;
    private JPasswordField txtPassword;

    public LoginForm() {
        setTitle("Employee System - Login");
        setSize(450, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.white);

        // Title
        lblTitle = new JLabel("Employee Login System");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setBounds(100, 20, 300, 30);
        add(lblTitle);

        // Email
        lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblEmail.setBounds(50, 80, 80, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(140, 80, 220, 25);
        add(txtEmail);

        // Password
        lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblPassword.setBounds(50, 130, 80, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(140, 130, 220, 25);
        add(txtPassword);

        // Buttons
        btnLogin = new JButton("Login");
        btnLogin.setBounds(80, 200, 120, 30);
        btnLogin.setBackground(new Color(0, 149, 246));
        btnLogin.setForeground(Color.white);
        btnLogin.setFocusPainted(false);
        add(btnLogin);

        btnSignUp = new JButton("Sign Up");
        btnSignUp.setBounds(240, 200, 120, 30);
        btnSignUp.setBackground(new Color(0, 200, 150));
        btnSignUp.setForeground(Color.white);
        btnSignUp.setFocusPainted(false);
        add(btnSignUp);

        // Login action
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText().trim();
                String password = new String(txtPassword.getPassword()).trim();

                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both email and password");
                    return;
                }

                if (!email.contains("@") || !email.contains(".")) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid email address");
                    return;
                }

                UserDAO userDAO = new UserDAO();
                boolean success = userDAO.loginUser(email, password);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    DashboardForm dashboard = new DashboardForm();
                    dashboard.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email or password");
                }
            }
        });

        // Sign Up action
        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SignUpForm signUpForm = new SignUpForm();
                signUpForm.setVisible(true);
                dispose();
            }
        });

        setVisible(true);
    }

    public static void main(String args[]) {
        new LoginForm();
    }
}
