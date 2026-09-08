package uiPayroll;

import Dao.User;
import Dao.UserDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class UserManagementForm extends JFrame {

    private JTable usersTable;
    private JButton btnRefresh, btnBack, btnUpdate, btnDelete, btnSaveDetails;
    private JTextField txtSalary, txtPhone, txtDepartment, txtPosition;
    private UserDAO userDAO;

    public UserManagementForm() {
        setTitle("User Management");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.white);

        userDAO = new UserDAO();

        // Title
        JLabel lblTitle = new JLabel("User Management");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setBounds(20, 10, 300, 30);
        add(lblTitle);

        // Users Table
        usersTable = new JTable();
        usersTable.setModel(new DefaultTableModel(
                new Object[]{"ID", "Full Name", "Email", "Salary", "Department", "Position"}, 0
        ));
        usersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(usersTable);
        scrollPane.setBounds(20, 50, 550, 200);
        add(scrollPane);

        usersTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                loadSelectedUserToForm();
            }
        });

        // Buttons
        btnRefresh = createButton("Refresh", 20, 270, 100, 30, new Color(0, 123, 255));
        btnBack = createButton("Back", 140, 270, 100, 30, new Color(108, 117, 125));
        btnUpdate = createButton("Update Selected", 260, 270, 140, 30, new Color(40, 167, 69));
        btnDelete = createButton("Delete Selected", 410, 270, 160, 30, new Color(220, 53, 69));

        add(btnRefresh);
        add(btnBack);
        add(btnUpdate);
        add(btnDelete);

        // Form Panel for Editing
        JPanel formPanel = new JPanel();
        formPanel.setBorder(BorderFactory.createTitledBorder("Edit User Details"));
        formPanel.setBounds(20, 320, 550, 120);
        formPanel.setLayout(null);
        formPanel.setBackground(Color.white);
        add(formPanel);

        JLabel lblSalary = new JLabel("Salary:");
        lblSalary.setBounds(10, 30, 80, 25);
        formPanel.add(lblSalary);

        txtSalary = new JTextField();
        txtSalary.setBounds(80, 30, 120, 25);
        formPanel.add(txtSalary);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(220, 30, 80, 25);
        formPanel.add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(290, 30, 120, 25);
        formPanel.add(txtPhone);

        JLabel lblDepartment = new JLabel("Department:");
        lblDepartment.setBounds(10, 70, 80, 25);
        formPanel.add(lblDepartment);

        txtDepartment = new JTextField();
        txtDepartment.setBounds(80, 70, 120, 25);
        formPanel.add(txtDepartment);

        JLabel lblPosition = new JLabel("Position:");
        lblPosition.setBounds(220, 70, 80, 25);
        formPanel.add(lblPosition);

        txtPosition = new JTextField();
        txtPosition.setBounds(290, 70, 120, 25);
        formPanel.add(txtPosition);

        btnSaveDetails = createButton("Save Details", 430, 50, 100, 30, new Color(255, 193, 7));
        formPanel.add(btnSaveDetails);

        // Button Actions
        btnRefresh.addActionListener(e -> loadUsersData());
        btnBack.addActionListener(e -> dispose());
        btnDelete.addActionListener(e -> deleteSelectedUser());
        btnSaveDetails.addActionListener(e -> saveUserDetails());
        btnUpdate.addActionListener(e -> loadSelectedUserToForm());

        // Load data initially
        loadUsersData();

        setVisible(true);
    }

    private JButton createButton(String text, int x, int y, int w, int h, Color color) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, w, h);
        btn.setBackground(color);
        btn.setForeground(Color.white);
        btn.setFocusPainted(false);
        return btn;
    }

    private void loadUsersData() {
        try {
            List<User> users = userDAO.getAllUsers();
            DefaultTableModel model = (DefaultTableModel) usersTable.getModel();
            model.setRowCount(0);
            for (User user : users) {
                model.addRow(new Object[]{
                        user.getId(),
                        user.getFullName(),
                        user.getEmail(),
                        user.getSalary(),
                        user.getDepartment(),
                        user.getPosition()
                });
            }
            if (users.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No users found.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading users: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadSelectedUserToForm() {
        int selectedRow = usersTable.getSelectedRow();
        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) usersTable.getModel();

            Object salaryValue = model.getValueAt(selectedRow, 3);
            Object deptValue = model.getValueAt(selectedRow, 4);
            Object posValue = model.getValueAt(selectedRow, 5);

            txtSalary.setText(salaryValue != null ? salaryValue.toString() : "");
            txtPhone.setText(""); // phone not in table
            txtDepartment.setText(deptValue != null ? deptValue.toString() : "");
            txtPosition.setText(posValue != null ? posValue.toString() : "");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user first.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void saveUserDetails() {
        int selectedRow = usersTable.getSelectedRow();
        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) usersTable.getModel();
            int userId = (int) model.getValueAt(selectedRow, 0);

            try {
                User user = userDAO.getUserById(userId);
                if (user != null) {
                    user.setSalary(txtSalary.getText().isEmpty() ? 0 : Double.parseDouble(txtSalary.getText()));
                    user.setPhone(txtPhone.getText());
                    user.setDepartment(txtDepartment.getText());
                    user.setPosition(txtPosition.getText());

                    boolean success = userDAO.updateUserDetails(user);
                    if (success) {
                        JOptionPane.showMessageDialog(this, "User details updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        loadUsersData();
                        clearForm();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to update user.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid salary.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user first.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteSelectedUser() {
        int selectedRow = usersTable.getSelectedRow();
        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) usersTable.getModel();
            int userId = (int) model.getValueAt(selectedRow, 0);
            String userName = (String) model.getValueAt(selectedRow, 1);

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete user: " + userName + "?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = userDAO.deleteUser(userId);
                if (success) {
                    JOptionPane.showMessageDialog(this, "User deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadUsersData();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete user.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user first.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void clearForm() {
        txtSalary.setText("");
        txtPhone.setText("");
        txtDepartment.setText("");
        txtPosition.setText("");
    }

    public static void main(String[] args) {
        new UserManagementForm();
    }
}
