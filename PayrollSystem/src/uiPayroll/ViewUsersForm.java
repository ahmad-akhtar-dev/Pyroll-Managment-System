package uiPayroll;
import Dao.User;
import Dao.UserDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ViewUsersForm extends JFrame {

    private JTable usersTable;
    private JButton btnRefresh, btnBack;

    public ViewUsersForm() {
        setTitle("Registered Users");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.white);

        // Title
        JLabel lblTitle = new JLabel("Registered Users");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setBounds(20, 10, 300, 30);
        add(lblTitle);

        // Users Table
        usersTable = new JTable();
        usersTable.setModel(new DefaultTableModel(
                new Object[]{"ID", "Full Name", "Email"}, 0
        ));
        usersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(usersTable);
        scrollPane.setBounds(20, 50, 440, 250);
        add(scrollPane);

        // Buttons
        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(20, 320, 120, 30);
        btnRefresh.setBackground(new Color(0, 123, 255));
        btnRefresh.setForeground(Color.white);
        btnRefresh.setFocusPainted(false);
        add(btnRefresh);

        btnBack = new JButton("Back to Dashboard");
        btnBack.setBounds(300, 320, 160, 30);
        btnBack.setBackground(new Color(108, 117, 125));
        btnBack.setForeground(Color.white);
        btnBack.setFocusPainted(false);
        add(btnBack);

        // Button Actions
        btnRefresh.addActionListener(e -> loadUsersData());
        btnBack.addActionListener(e -> dispose());

        // Load data initially
        loadUsersData();

        setVisible(true);
    }

    private void loadUsersData() {
        try {
            UserDAO userDAO = new UserDAO();
            List<User> users = userDAO.getAllUsers();

            DefaultTableModel model = (DefaultTableModel) usersTable.getModel();
            model.setRowCount(0); // Clear existing data

            for (User user : users) {
                model.addRow(new Object[]{
                        user.getId(),
                        user.getFullName(),
                        user.getEmail()
                });
            }

            if (users.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No users found in database.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading users: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new ViewUsersForm();
    }
}
