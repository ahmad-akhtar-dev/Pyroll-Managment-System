package uiPayroll;

import javax.swing.*;
import java.awt.*;

public class DashboardForm extends JFrame {

    private JButton btnViewUsers;
    private JButton btnUserManagement;
    private JButton btnLogout;

    public DashboardForm() {
        setTitle("Employee System - Dashboard");
        setSize(500, 250);
        setLocationRelativeTo(null);  // Center the form
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Set background color of JFrame
        getContentPane().setBackground(new Color(245, 245, 245)); // light gray

        // Title Label
        JLabel lblTitle = new JLabel("Welcome to Employee System");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(33, 37, 41)); // dark text
        lblTitle.setBounds(50, 20, 400, 30);
        add(lblTitle);

        // Buttons
        btnViewUsers = new JButton("View Registered Users");
        btnViewUsers.setBounds(20, 100, 150, 35);
        btnViewUsers.setBackground(new Color(0, 123, 255)); // Blue
        btnViewUsers.setForeground(Color.WHITE);
        btnViewUsers.setFocusPainted(false); // Remove focus border
        add(btnViewUsers);

        btnUserManagement = new JButton("User Management");
        btnUserManagement.setBounds(180, 100, 150, 35);
        btnUserManagement.setBackground(new Color(40, 167, 69)); // Green
        btnUserManagement.setForeground(Color.WHITE);
        btnUserManagement.setFocusPainted(false);
        add(btnUserManagement);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(340, 100, 120, 35);
        btnLogout.setBackground(new Color(220, 53, 69)); // Red
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFocusPainted(false);
        add(btnLogout);

        // Button Actions
        btnViewUsers.addActionListener(e -> new ViewUsersForm().setVisible(true));
        btnUserManagement.addActionListener(e -> new UserManagementForm().setVisible(true));
        btnLogout.addActionListener(e -> {
            new LoginForm().setVisible(true);
            dispose();
        });

        setVisible(true);
    }

    // Main method for testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DashboardForm::new);
    }
}
