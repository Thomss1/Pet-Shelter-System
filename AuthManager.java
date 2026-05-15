package com.petshelter.petsheltersystem;

import java.util.Scanner;

public class AuthManager {

    private String[] usernames = new String[10];   // max 10 users
    private String[] passwords = new String[10];
    private int userCount = 0;
    private Scanner scanner = new Scanner(System.in);

    public AuthManager() {
        // Default admin account
        usernames[0] = "admin";
        passwords[0] = "Admin123!";
        userCount = 1;
    }

    public boolean register() {
        try {
            System.out.println("\n=== REGISTER NEW USER ===");

            System.out.print("Enter Username (3-20 letters/numbers): ");
            String username = scanner.nextLine().trim();

            if (!username.matches("^[a-zA-Z0-9]{3,20}$")) {
                throw new InvalidPetException("Username must be 3-20 letters or numbers only.");
            }

            // Check if username already exists
            for (int i = 0; i < userCount; i++) {
                if (usernames[i].equals(username)) {
                    throw new InvalidPetException("Username already exists!");
                }
            }

            System.out.print("Enter Password (min 6 chars with letter, number & special char): ");
            String password = scanner.nextLine().trim();

            if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$")) {
                throw new InvalidPetException("Password must be at least 6 characters with letter, number, and special character (@$!%*?&).");
            }

            if (userCount >= 10) {
                throw new InvalidPetException("Maximum users reached!");
            }

            usernames[userCount] = username;
            passwords[userCount] = password;
            userCount++;

            System.out.println("Registration successful!");
            return true;

        } catch (InvalidPetException e) {
            System.out.println("Registration Failed: " + e.getMessage());
            return false;
        }
    }

    public boolean login() {
        try {
            System.out.println("\n=== LOGIN ===");

            System.out.print("Enter Username: ");
            String username = scanner.nextLine().trim();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine().trim();

            for (int i = 0; i < userCount; i++) {
                if (usernames[i].equals(username) && passwords[i].equals(password)) {
                    System.out.println("Login successful! Welcome, " + username + "!");
                    return true;
                }
            }

            throw new InvalidPetException("Invalid username or password.");

        } catch (InvalidPetException e) {
            System.out.println("Login Failed: " + e.getMessage());
            return false;
        }
    }

    public void showAuthMenu() {
        while (true) {
            System.out.println("\n=== PET SHELTER SYSTEM ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                register();
            } else if (choice.equals("2")) {
                if (login()) {
                    return;   // Successful login → exit auth menu
                }
            } else if (choice.equals("3")) {
                System.out.println("Thank you! Goodbye.");
                System.exit(0);
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
