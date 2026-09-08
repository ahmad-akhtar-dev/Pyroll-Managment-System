package com.employee.util;
import java.sql.Connection;
public class TestConnection {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            System.out.println("Congratulations!!!");
            System.out.println("Driver Load Successfully");
            System.out.println("Database connected successfully!");
            conn.close();
        } catch (Exception e) {
            System.out.println(" Connection failed: " + e.getMessage());
        }
    }
}
