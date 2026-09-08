Payroll-Management-System

A secure, desktop-based **Payroll Management System** developed using **Java Swing/AWT** with **MySQL** as the backend database. The system is designed to simplify employee record management, secure user authentication, and payroll processing through an intuitive graphical user interface.

## 🚀 Features

### 🔐 Secure Authentication

* Role-based login and registration
* Separate access levels for administrators and users
* Secure authentication and user management

### 👥 Employee & User Management

* Add, update, and manage employee records
* Maintain employee information in a centralized database
* View and manage registered system users
* User-friendly administrative dashboard

### 💰 Payroll Management

* Calculate employee salaries
* Manage bonuses and deductions
* Track payroll information
* Maintain organized salary records

### 💾 Database Management

* Persistent data storage using MySQL
* JDBC-based database connectivity
* Structured relational database design
* Improved data consistency and integrity

## 🛠️ Technologies Used

| Technology                   | Purpose                      |
| ---------------------------- | ---------------------------- |
| **Java**                     | Core application development |
| **Java Swing / AWT**         | Graphical User Interface     |
| **MySQL**                    | Relational database          |
| **JDBC**                     | Database connectivity        |
| **IntelliJ IDEA / NetBeans** | Development environment      |
| **mysql-connector-j**        | MySQL JDBC Driver            |

## 📂 Project Structure

```text
Payroll-Management-System/
│
├── src/
│   ├── com/employee/
│   │   ├── dao/              # Database access classes
│   │   ├── model/            # Application data models
│   │   └── util/             # Database connection utilities
│   │
│   └── uiPayroll/            # GUI forms and application screens
│       ├── Login
│       ├── Dashboard
│       └── Main
│
├── pics/                     # Images, icons, and logos
├── build.xml                 # Apache Ant build configuration
└── manifest.mf               # Application manifest
```

## ⚙️ Getting Started

### Prerequisites

Before running the application, make sure the following are installed:

* **JDK 8 or later**
* **MySQL Server**
* **IntelliJ IDEA or NetBeans**
* **MySQL Connector/J**

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/Payroll-Management-System.git
cd Payroll-Management-System
```

### 2. Configure the Database

1. Start your MySQL Server.
2. Create the required database.
3. Execute the provided SQL setup script, if available.
4. Create the required tables such as:

   * `users`
   * `employees`
   * `payroll`
5. Open the database connection utility, such as `DBConnection.java`.
6. Update the database URL, username, and password according to your local MySQL configuration.

### 3. Configure JDBC

Add the **MySQL Connector/J** JAR file to your project's classpath or module dependencies.

### 4. Run the Application

Open the project in **IntelliJ IDEA** or **NetBeans** and run the main application class:

```text
uiPayroll.Main
```

## 🗄️ Database

The application uses **MySQL** for persistent data storage.

The database is responsible for maintaining information related to:

* Users and authentication
* Employee records
* Salary information
* Bonuses
* Deductions
* Payroll records

The application communicates with MySQL through **JDBC (Java Database Connectivity)**.

## 🔒 Security

The system implements role-based access control to provide different levels of access for administrators and regular users.

> **Note:** For production use, database credentials and user passwords should be securely stored and passwords should be hashed rather than stored as plain text.

## 🎯 Project Objective

The primary objective of this project is to develop a reliable and user-friendly payroll management solution that reduces manual record-keeping and simplifies employee salary management.

The project also demonstrates practical implementation of:

* Object-Oriented Programming in Java
* GUI development using Swing/AWT
* Database management using MySQL
* JDBC connectivity
* CRUD operations
* Authentication and authorization
* Layered application architecture

## 👨‍💻 Author

**Ahmad Akhtar**

**Email:** [ahamdiit031@gmail.com](mailto:ahamdiit031@gmail.com)

## 📄 License

This project is developed for **educational and academic purposes**.

---

⭐ If you find this project useful, consider giving the repository a star.
