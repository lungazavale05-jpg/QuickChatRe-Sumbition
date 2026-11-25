package chatapp;

import java.util.Scanner;

public class ChatApp {
    public static void main(String[] args) {
        Login log = new Login();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== REGISTRATION ===");
        
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (log.checkUsername(username)) {
            System.out.println("Username successfully captured.");
        } else {
            System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (log.checkPasswordComplexity(password)) {
            System.out.println("Password successfully captured.");
        } else {
            System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
        }

        System.out.print("Enter cell phone number (e.g. +27831234567): ");
        String phone = scanner.nextLine();
        if (log.checkCellPhoneNumber(phone)) {
            System.out.println("Cell phone number successfully added.");
        } else {
            System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
        }

        System.out.println("\n=== LOGIN ===");
        System.out.print("Enter username to login: ");
        String loginUser = scanner.nextLine();
        
        System.out.print("Enter password to login: ");
        String loginPass = scanner.nextLine();

        boolean isLoggedIn = log.loginUser(loginUser, loginPass, username, password);

        if (isLoggedIn) {
            System.out.println("Welcome back, your Login Successful.");
            System.out.println("Welcome " + username + ", it is great to see you again.");
            
            Messages app = new Messages();
            app.part(); 
        } else {
            System.out.println("Login Failed, Please check username and password.");
            System.out.println("Username or password incorrect, please try again.");
        }
    }
}
