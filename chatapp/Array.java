package chatapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Array {
    public static ArrayList<String> sentMessages = new ArrayList<>();
    public static ArrayList<String> disregardedMessages = new ArrayList<>();
    public static ArrayList<String> storedMessages = new ArrayList<>();
    public static ArrayList<String> messageHashes = new ArrayList<>();
    public static ArrayList<String> messageIDs = new ArrayList<>();
    public static ArrayList<String> recipients = new ArrayList<>();

    public static void loadTestData() {
        recipients.add("+27834557896");
        sentMessages.add("Did you get the cake?");
        messageIDs.add("0838884567");
        messageHashes.add("08:1:DIDCAKE?");
        
        recipients.add("+27838884567");
        storedMessages.add("Where are you? You are late! I have asked you to be on time.");
        messageIDs.add("0838884568");
        messageHashes.add("08:2:WHEREON TIME.");
        
        recipients.add("+27834484567");
        disregardedMessages.add("Yohoo!!, I am at your gate.");
        messageIDs.add("0838884569");
        messageHashes.add("08:3:YOHGATE.");
        
        recipients.add("0838884567"); 
        sentMessages.add("It is dinner time !");
        messageIDs.add("0838884570");
        messageHashes.add("08:4:IT! TIME!");
        
        recipients.add("+27838884567");
        storedMessages.add("Ok, I am leaving without you.");
        messageIDs.add("0838884571");
        messageHashes.add("08:5:OKYOU.");
    }

    public static void searchByRecipient(String targetRecipient) {
        boolean found = false;
        System.out.println("Searching for: " + targetRecipient);
        for (int i = 0; i < recipients.size(); i++) {
            if (recipients.get(i).equals(targetRecipient)) {
                if (i < sentMessages.size()) System.out.println("Found: " + sentMessages.get(i));
                else if (i < storedMessages.size()) System.out.println("Found: " + storedMessages.get(i));
                found = true;
            }
        }
        if (!found) System.out.println("No messages found for this number.");
    }

    public static void deleteByHash(String targetHash) {
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equals(targetHash)) {
                String deletedText = "Unknown";
                if (i < sentMessages.size()) deletedText = sentMessages.remove(i);
                else if (i < storedMessages.size()) deletedText = storedMessages.remove(i);
                messageHashes.remove(i);
                messageIDs.remove(i);
                recipients.remove(i);
                System.out.println("Message deleted: " + deletedText);
                return;
            }
        }
        System.out.println("Hash not found.");
    }

    public static void displayReport() {
        System.out.println("--- MESSAGE REPORT ---");
        for (int i = 0; i < messageHashes.size(); i++) {
            System.out.println("ID: " + messageIDs.get(i));
            System.out.println("Hash: " + messageHashes.get(i));
            System.out.println("Recipient: " + recipients.get(i));
            System.out.println("-----------------");
        }
    }

    public static void saveToJson() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("messages.json"));
            writer.println("[\n");
            for (int i = 0; i < messageIDs.size(); i++) {
                writer.println("  {");
                writer.println("    \"id\": \"" + messageIDs.get(i) + "\",");
                writer.println("    \"recipient\": \"" + recipients.get(i) + "\",");
                writer.println("    \"hash\": \"" + messageHashes.get(i) + "\"");
                if (i < messageIDs.size() - 1) writer.println("  },");
                else writer.println("  }");
            }
            writer.println("]");
            writer.close();
            System.out.println("Saved to messages.json successfully.");
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    public static void readFromJson() {
        System.out.println("--- READING JSON FILE ---");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("messages.json"));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("JSON file not found. Save a message first.");
        }
    }
}
