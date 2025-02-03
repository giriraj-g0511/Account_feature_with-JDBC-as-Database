# LinkedIn Login & Signup System

## 📌 Project Overview
This project is a **LinkedIn-style Login & Signup System** built using **Java Swing, AWT, and JDBC (SQL Database)**. It provides a user-friendly authentication system with a modern UI featuring **background images on all frames**.

## 🛠️ Technologies Used
- **Java Swing & AWT** → For designing the GUI
- **JDBC (Java Database Connectivity)** → For database interaction
- **MySQL** → As the database for storing user credentials

## 🚀 Features
✅ **User Registration (Sign Up)**
- Users can create an account by entering their details.
- Passwords are securely stored in the database.

✅ **User Authentication (Sign In)**
- Validates user credentials against the database.
- Displays appropriate error messages for incorrect details.

✅ **Logout Feature**
- Users can securely log out of their session.

✅ **Background Images for UI**
- All frames (Login, Signup, and Home) feature **custom background images** for an enhanced user experience.

## 📂 Project Structure
```
📦 LinkedInLoginSystem
 ┣ 📂 src
 ┃ ┣ 📜 LoginFrame.java  (User Login UI)
 ┃ ┣ 📜 SignupFrame.java  (User Signup UI)
 ┃ ┣ 📜 ConnectionJDBC.java  (Handles JDBC Connection)
 ┃ ┣ 📜 HomeFrame.java  (Main Dashboard After Login)
 ┣ 📜 README.md
 ┣ 📜 LinkedIn_DB.sql (Database Schema)
```

## 🏗️ Setup & Installation
### 1️⃣ **Clone the Repository**
```sh
git clone https://github.com/your-repo/LinkedInLoginSystem.git
cd LinkedInLoginSystem
```

### 2️⃣ **Set Up MySQL Database**
- Create a database in MySQL:
```sql
CREATE DATABASE LinkedInDB;
USE LinkedInDB;
```
- Run the `LinkedIn_DB.sql` script to create the users table.

### 3️⃣ **Configure Database Connection**
Modify `DatabaseConnection.java` with your MySQL credentials:
```java
String url = "jdbc:mysql://localhost:3306/LinkedInDB";
String user = "your_username";
String password = "your_password";
```

### 4️⃣ **Compile & Run**
```sh
javac src/*.java
java src.LoginFrame
```

## 📸 Screenshots
![Login Page](screenshots/login.png)
![Signup Page](screenshots/signup.png)

## 🤝 Contribution
Feel free to fork this repository and submit a pull request if you'd like to improve the project!

## 📜 License
This project is **open-source** and free to use under the MIT License.
