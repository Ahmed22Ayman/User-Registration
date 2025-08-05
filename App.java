import dao.UserDAO;
import model.User;

import java.security.MessageDigest;
import java.util.Scanner;

public class App {
    
    public static void main(String[] args) {
        try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    System.out.println("MySQL JDBC Driver Loaded ✅");
} catch (ClassNotFoundException e) {
    System.out.println("❌ MySQL JDBC Driver not found");
}

        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        System.out.println("=== User Registration ===");

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        if (userDAO.checkEmailExists(email)) {
            System.out.println("This email is already registered.");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Encrypt password
        String encryptedPassword = encryptPassword(password);

        User user = new User(name, email, encryptedPassword);
        boolean success = userDAO.registerUser(user);

        if (success) {
            System.out.println("✅ Registration successful!");
        } else {
            System.out.println("❌ Registration failed.");
        }
    }

    private static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
