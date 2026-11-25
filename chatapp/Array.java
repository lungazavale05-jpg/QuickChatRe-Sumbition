package chatapp;

import java.util.Scanner;

public class Messages {
    static Login log = new Login();
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Array.loadTestData();
        Messages app = new Messages();
        app.part();
    }

    public static boolean checkMessageLength(String message) {
        return message.length() <= 250;
    }
    
    public static String generateMessageID() {
        java.util.Random ran = new java.util.Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) id.append(ran.nextInt(10));
        return id.toString();
    }
    
    public static String generateMessageHash(String messageID, int messageNumber, String messageText) {
        String firstTwoID = messageID.substring(0, 2);
        String trimmed = messageText.trim();
        if (trimmed.isEmpty()) return firstTwoID + ":" + messageNumber + ":";
        String[] words = trimmed.split("\\s+");
        String combinedWords = (words[0] + words[words.length - 1]).toUpperCase();
        return firstTwoID + ":" + messageNumber + ":" + combinedWords;
    }

    public void part() {
        int option = 0;
        while (option != 3 && option != 4) {
            System.out.println("\nWelcome to QuickChat");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.println("4) Stored Messages"); 
            System.out.print("Choose an option: ");
            option = Integer.parseInt(scan.nextLine().trim());

            if (option == 1) sendMessages();
            else if (option == 2) System.out.println("Coming soon");
            else if (option == 3) System.out.println("Thanks for using QuickChat");
            else if (option == 4) storedMessagesMenu();
            else System.out.println("Invalid choice.");
        }
    }

    public static void storedMessagesMenu() {
        int menuChoice = 0;
        while (menuChoice != 8) {
            System.out.println("\n--- Stored Messages Menu ---");
            System.out.println("1) Display sender and recipient");
            System.out.println("2) Display longest message");
            System.out.println("3) Search by ID");
            System.out.println("4) Search by Recipient");
            System.out.println("5) Delete by Hash");
            System.out.println("6) Display Report");
            System.out.println("7) Read JSON File");
            System.out.println("8) Back to Main Menu");
            System.out.print("Choose an option: ");
            menuChoice = Integer.parseInt(scan.nextLine().trim());

            if (menuChoice == 1) {
                System.out.println("\n--- Sender & Recipient ---");
                for(int i = 0; i < Array.recipients.size(); i++) System.out.println("To: " + Array.recipients.get(i));
            } else if (menuChoice == 2) {
                String longest = "";
                for(String s : Array.storedMessages) if(s.length() > longest.length()) longest = s;
                System.out.println("Longest message is: " + longest);
            } else if (menuChoice == 3) {
                System.out.print("Enter ID: ");
                String searchId = scan.nextLine();
                int index = Array.messageIDs.indexOf(searchId);
                if(index != -1) System.out.println("Found at index: " + index);
                else System.out.println("ID not found.");
            } else if (menuChoice == 4) {
                System.out.print("Enter Recipient number: ");
                Array.searchByRecipient(scan.nextLine());
            } else if (menuChoice == 5) {
                System.out.print("Enter Hash to delete: ");
                Array.deleteByHash(scan.nextLine());
            } else if (menuChoice == 6) {
                Array.displayReport();
            } else if (menuChoice == 7) {
                Array.readFromJson();
            } else if (menuChoice == 8) {
                System.out.println("Going back...");
            } else {
                System.out.println("Invalid option.");
            }
        }
    }

    public static void sendMessages() {
        System.out.print("Enter recipient number: ");
        String rec = scan.nextLine();
        System.out.print("Enter message: ");
        String msg = scan.nextLine();
        if (!checkMessageLength(msg)) {
            System.out.println("Please enter a message of 250 characters or less.");
            return;
        }
        String id = generateMessageID();
        String hash = generateMessageHash(id, Array.messageIDs.size() + 1, msg);
        Array.sentMessages.add(msg);
        Array.recipients.add(rec);
        Array.messageIDs.add(id);
        Array.messageHashes.add(hash);
        System.out.println("Message successfully sent.");
        System.out.println("ID: " + id);
        System.out.println("Hash: " + hash);
    }
}
