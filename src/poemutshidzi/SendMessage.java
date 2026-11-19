/*
- Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
- Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package poemutshidzi;
import java.util.*;
import java.io.*;

public class SendMessage {
    public static int totalMessagesSent = 0;
    public static int messageCount = 0;
    public static String messageID;
    public static String messageHash;
    public static String recipient;
    public static String messageText;
    public static String sender = "User";
    static Scanner input = new Scanner(System.in);
    static Random rand = new Random();

    // PART 3 - PARALLEL ARRAYS - Made of StoredMessages objects
    public static ArrayList<StoredMessages> allMessages = new ArrayList<>();
    public static ArrayList<String> sentMessages = new ArrayList<>();
    public static ArrayList<String> disregardedMessages = new ArrayList<>();
    public static ArrayList<String> storedMessages = new ArrayList<>();
    public static ArrayList<String> messageHashes = new ArrayList<>();
    public static ArrayList<String> messageIDs = new ArrayList<>();
    public static ArrayList<String> storedRecipients = new ArrayList<>();
    public static ArrayList<String> storedSenders = new ArrayList<>();
    public static ArrayList<String> storedIDs = new ArrayList<>();
    public static ArrayList<String> storedHashes = new ArrayList<>();
    public static ArrayList<String> sentRecipients = new ArrayList<>();
    public static ArrayList<String> sentIDs = new ArrayList<>();
    public static ArrayList<String> sentHashes = new ArrayList<>();
    public static ArrayList<String> sentMessagesHistory = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== LOGIN ===");
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Login failed. Cannot send messages.");
            return;
        }
        sender = username;
        startQuickChat(input);
    }

    public static void startQuickChat(Scanner scanner) {
        input = scanner;
        System.out.println("\nWelcome to QuickChat.");
        System.out.print("\nHow many messages do you wish to enter? ");
        int numMessages = Integer.parseInt(input.nextLine());

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.println("4) Stored Messages");
            System.out.print("Choose option: ");
            int option = Integer.parseInt(input.nextLine());

            switch (option) {
                case 1: {
                    if (messageCount >= numMessages) {
                        System.out.println("You have reached the limit of " + numMessages + " messages.");
                        break;
                    }
                    System.out.print("Enter recipient number (e.g +27718693002): ");
                    recipient = input.nextLine();
                    String checkRec = checkRecipientCell();
                    if (!checkRec.equals("Cell phone number successfully captured.")) {
                        if (!(recipient.length() == 10 && recipient.startsWith("0"))) {
                            System.out.println(checkRec);
                            break;
                        }
                    }
                    System.out.print("Enter your message (max 250 chars): ");
                    messageText = input.nextLine();
                    String checkLength = checkMessageLength();
                    System.out.println(checkLength);
                    if (checkLength.contains("exceeds")) break;
                    messageID = createMessageID();
                    System.out.println("Message ID generated: " + messageID);
                    messageCount++;
                    messageHash = createMessageHash();
                    System.out.println("Message Hash: " + messageHash);
                    System.out.println("\nWhat do you want to do?");
                    System.out.println("1) Send Message");
                    System.out.println("2) Disregard Message");
                    System.out.println("3) Store Message");
                    System.out.print("Choose: ");
                    int sendOption = Integer.parseInt(input.nextLine());
                    String result = SentMessage(sendOption);
                    System.out.println(result);
                    if (result.equals("Message successfully sent.") || result.equals("Message successfully stored.")) {
                        System.out.println("\n--- MESSAGE DETAILS ---");
                        System.out.println(printMessages());
                        sentMessagesHistory.add(printMessages());
                    }
                    if (result.equals("Message disregarded.")) messageCount--;
                    if (messageCount == numMessages) System.out.println("\nTotal messages sent: " + returnTotalMessagess());
                    break;
                }
                case 2: {
                    if (sentMessagesHistory.size() == 0) System.out.println("No messages sent yet.");
                    else {
                        System.out.println("\n--- Recently Sent Messages ---");
                        for (String m : sentMessagesHistory) System.out.println(m + "\n");
                        System.out.println("Total messages sent: " + returnTotalMessagess());
                    }
                    break;
                }
                case 3: {
                    System.out.println("\nTotal messages sent: " + returnTotalMessagess());
                    System.out.println("Goodbye!");
                    return;
                }
                case 4: {
                    storedMessagesMenu();
                    break;
                }
                default: System.out.println("Invalid option. Enter 1, 2, 3 or 4."); break;
            }
        }
    }

    // PART 3 - STORED MENU 
    public static void storedMessagesMenu() {
        loadStoredMessagesFromFile();
        while (true) {
            System.out.println("\n--- STORED MESSAGES ---");
            System.out.println("a) Display sender and recipient of all stored messages");
            System.out.println("b) Display the longest stored message");
            System.out.println("c) Search for a message ID");
            System.out.println("d) Search for all messages for a particular recipient");
            System.out.println("e) Delete a message using the message hash");
            System.out.println("f) Display a report of all stored messages");
            System.out.println("g) Back to main menu");
            System.out.print("Choose: ");
            String choice = input.nextLine().toLowerCase();
            switch (choice) {
                case "a": System.out.println(displaySenderAndRecipient()); break;
                case "b": System.out.println("Longest stored message: " + getLongestStoredMessage()); break;
                case "c": {
                    System.out.print("Enter Message ID: ");
                    System.out.println(searchMessageByID(input.nextLine()));
                    break;
                }
                case "d": {
                    System.out.print("Enter Recipient: ");
                    System.out.println(searchMessagesByRecipient(input.nextLine()));
                    break;
                }
                case "e": {
                    System.out.print("Enter Message Hash: ");
                    System.out.println(deleteMessageByHash(input.nextLine()));
                    break;
                }
                case "f": System.out.println(displayReport()); break;
                case "g": return;
                default: System.out.println("Invalid option."); break;
            }
        }
    }

    // PART 3 - TEST DATA ONLY 
    public static void populateTestData() {
        allMessages.clear(); sentMessages.clear(); disregardedMessages.clear(); storedMessages.clear();
        messageHashes.clear(); messageIDs.clear();
        storedRecipients.clear(); storedSenders.clear(); storedIDs.clear(); storedHashes.clear();
        sentRecipients.clear(); sentIDs.clear(); sentHashes.clear();
        totalMessagesSent = 0; messageCount = 0;
        addTestMessage("1", "+27834557896", "Did you get the cake?", "Sent", "User");
        addTestMessage("2", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored", "User");
        addTestMessage("3", "+27834484567", "Yohoooo, I am at your gate.", "Disregard", "User");
        addTestMessage("0838884567", "0838884567", "It is dinner time !", "Sent", "Developer");
        addTestMessage("5", "+27838884567", "Ok, I am leaving without you.", "Stored", "User");
    }

    private static void addTestMessage(String id, String rec, String msg, String flag, String snd) {
        String[] words = msg.trim().split(" ");
        String first = words[0].replaceAll("[^a-zA-Z]", "");
        String last = words[words.length - 1].replaceAll("[^a-zA-Z]", "");
        String hash = id.substring(0, Math.min(2, id.length())) + ":" + messageIDs.size() + ":" + first.toUpperCase() + last.toUpperCase();
        messageIDs.add(id); messageHashes.add(hash);
        allMessages.add(new StoredMessages(id, hash, snd, rec, msg, flag));
        if (flag.equals("Sent")) { sentMessages.add(msg); sentRecipients.add(rec); sentIDs.add(id); sentHashes.add(hash); totalMessagesSent++; }
        if (flag.equals("Disregard")) { disregardedMessages.add(msg); }
        if (flag.equals("Stored")) { storedMessages.add(msg); storedRecipients.add(rec); storedSenders.add(snd); storedIDs.add(id); storedHashes.add(hash); }
    }

    
    public static String displaySenderAndRecipient() {
        if (storedMessages.size() == 0) return "No stored messages.";
        String out = "";
        for (int i = 0; i < storedMessages.size(); i++) out += "Sender: " + storedSenders.get(i) + " | Recipient: " + storedRecipients.get(i) + "\n";
        return out.trim();
    }
    
    public static String getLongestStoredMessage() {
        if (storedMessages.size() == 0) return "No stored messages.";
        String longest = storedMessages.get(0);
        for (String m : storedMessages) if (m.length() > longest.length()) longest = m;
        return longest;
    }
    
    public static String searchMessageByID(String id) {
        for (int i = 0; i < storedIDs.size(); i++) if (storedIDs.get(i).equals(id)) return "Recipient: " + storedRecipients.get(i) + "\nMessage: " + storedMessages.get(i);
        for (int i = 0; i < sentIDs.size(); i++) if (sentIDs.get(i).equals(id)) return "Recipient: " + sentRecipients.get(i) + "\nMessage: " + sentMessages.get(i);
        for (int i = 0; i < sentRecipients.size(); i++) if (sentRecipients.get(i).equals(id)) return "Recipient: " + sentRecipients.get(i) + "\nMessage: " + sentMessages.get(i);
        return "Message ID not found.";
    }
    
    public static String searchMessagesByRecipient(String rec) {
        String out = "";
        for (int i = 0; i < storedMessages.size(); i++) if (storedRecipients.get(i).equals(rec)) out += storedMessages.get(i) + "\n";
        for (int i = 0; i < sentMessages.size(); i++) if (sentRecipients.get(i).equals(rec)) out += sentMessages.get(i) + "\n";
        if (out.isEmpty()) return "No messages found for " + rec;
        return out.trim();
    }
    
    public static String deleteMessageByHash(String hash) {
        for (int i = 0; i < storedHashes.size(); i++) {
            if (storedHashes.get(i).equals(hash)) {
                String msg = storedMessages.get(i);
                storedMessages.remove(i); storedRecipients.remove(i); storedSenders.remove(i); storedIDs.remove(i); storedHashes.remove(i);
                messageHashes.remove(hash);
                for (int j = 0; j < allMessages.size(); j++) if (allMessages.get(j).getMessageHash().equals(hash)) { allMessages.remove(j); break; }
                return "Message: \"" + msg + "\" successfully deleted.";
            }
        }
        for (int i = 0; i < sentHashes.size(); i++) {
            if (sentHashes.get(i).equals(hash)) {
                String msg = sentMessages.get(i);
                sentMessages.remove(i); sentRecipients.remove(i); sentIDs.remove(i); sentHashes.remove(i);
                return "Message: \"" + msg + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }
    
    public static String displayReport() {
        if (storedMessages.size() == 0 && sentMessages.size() == 0) return "No messages to report.";
        String out = "--- REPORT ---\n";
        for (int i = 0; i < storedMessages.size(); i++) out += "Message Hash: " + storedHashes.get(i) + "\nRecipient: " + storedRecipients.get(i) + "\nMessage: " + storedMessages.get(i) + "\n\n";
        for (int i = 0; i < sentMessages.size(); i++) out += "Message Hash: " + sentHashes.get(i) + "\nRecipient: " + sentRecipients.get(i) + "\nMessage: " + sentMessages.get(i) + "\n\n";
        return out.trim();
    }

    // PART 2
    public static boolean checkMessageID() { return messageID != null && messageID.length() <= 10; }
    public static String checkRecipientCell() {
        if (recipient != null && recipient.startsWith("+") && recipient.length() >= 11 && recipient.length() <= 13) {
            boolean allNumbers = true;
            for (int i = 1; i < recipient.length(); i++) { char c = recipient.charAt(i); if (c < '0' || c > '9') allNumbers = false; }
            if (allNumbers) return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code.";
    }
    public static String checkMessageLength() {
        if (messageText.length() > 250) return "Message exceeds 250 characters by " + (messageText.length() - 250) + ", please reduce the size.";
        else return "Message ready to send.";
    }
    public static String createMessageHash() {
        String firstTwo = messageID.substring(0, 2);
        String[] words = messageText.trim().split(" ");
        String firstWord = words[0].replaceAll("[^a-zA-Z]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "");
        return firstTwo + ":" + messageCount + ":" + firstWord.toUpperCase() + lastWord.toUpperCase();
    }
    public static String SentMessage(int choice) {
        switch (choice) {
            case 1: {
                totalMessagesSent++;
                sentMessages.add(messageText); sentRecipients.add(recipient); sentIDs.add(messageID); sentHashes.add(messageHash);
                messageIDs.add(messageID); messageHashes.add(messageHash);
                allMessages.add(new StoredMessages(messageID, messageHash, sender, recipient, messageText, "Sent"));
                return "Message successfully sent.";
            }
            case 2: {
                disregardedMessages.add(messageText);
                messageIDs.add(messageID); messageHashes.add(messageHash);
                allMessages.add(new StoredMessages(messageID, messageHash, sender, recipient, messageText, "Disregard"));
                return "Message disregarded.";
            }
            case 3: {
                storeMessage();
                storedMessages.add(messageText); storedRecipients.add(recipient); storedSenders.add(sender); storedIDs.add(messageID); storedHashes.add(messageHash);
                messageIDs.add(messageID); messageHashes.add(messageHash);
                allMessages.add(new StoredMessages(messageID, messageHash, sender, recipient, messageText, "Stored"));
                return "Message successfully stored.";
            }
            default: return "Invalid choice.";
        }
    }
    public static String printMessages() {
        return "MessageID: " + messageID + "\nMessage Hash: " + messageHash + "\nRecipient: " + recipient + "\nMessage: " + messageText;
    }
    public static int returnTotalMessagess() { return totalMessagesSent; }
    public static void storeMessage() {
        try (FileWriter writer = new FileWriter("storedMessages.json", true)) {
            writer.write("{\n  \"MessageID\": \"" + messageID + "\",\n  \"MessageHash\": \"" + messageHash + "\",\n  \"Sender\": \"" + sender + "\",\n  \"Recipient\": \"" + recipient + "\",\n  \"Message\": \"" + messageText + "\"\n},\n");
            System.out.println("Message saved to storedMessages.json");
        } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
    }
    public static void loadStoredMessagesFromFile() {
        try {
            File f = new File("storedMessages.json");
            if (!f.exists()) return;
            String content = new String(java.nio.file.Files.readAllBytes(f.toPath()));
            if (content.contains("\"Message\"") && storedMessages.size() == 0) {
                String[] parts = content.split("\\{");
                for (String p : parts) {
                    if (p.contains("Message")) {
                        String msg = extract(p, "Message");
                        String rec = extract(p, "Recipient");
                        String snd = extract(p, "Sender");
                        String id = extract(p, "MessageID");
                        String hash = extract(p, "MessageHash");
                        if (msg != null && !msg.isEmpty() && !storedMessages.contains(msg)) {
                            storedMessages.add(msg); storedRecipients.add(rec); storedSenders.add(snd); storedIDs.add(id); storedHashes.add(hash);
                            allMessages.add(new StoredMessages(id, hash, snd, rec, msg, "Stored"));
                        }
                    }
                }
            }
        } catch (Exception e) { System.out.println("Could not read storedMessages.json: " + e.getMessage()); }
    }
    private static String extract(String text, String key) {
        try {
            int start = text.indexOf("\"" + key + "\"");
            if (start == -1) return "";
            int colon = text.indexOf(":", start);
            int q1 = text.indexOf("\"", colon + 1);
            int q2 = text.indexOf("\"", q1 + 1);
            return text.substring(q1 + 1, q2);
        } catch (Exception e) { return ""; }
    }
    public static String createMessageID() {
        long number = 1000000000L + (long) (rand.nextDouble() * 9000000000L);
        return String.valueOf(number);
    }
}
