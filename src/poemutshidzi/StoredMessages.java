/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poemutshidzi;

/**
 *
 * @author mutsh
 */


public class StoredMessages {
    private String messageID;
    private String messageHash;
    private String sender;
    private String recipient;
    private String messageText;
    private String flag;

    public StoredMessages(String messageID, String messageHash, String sender, String recipient, String messageText, String flag) {
        this.messageID = messageID;
        this.messageHash = messageHash;
        this.sender = sender;
        this.recipient = recipient;
        this.messageText = messageText;
        this.flag = flag;
    }

    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getSender() { return sender; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getFlag() { return flag; }

    public boolean isSent() { return flag.equals("Sent"); }
    public boolean isStored() { return flag.equals("Stored"); }
    public boolean isDisregarded() { return flag.equals("Disregard"); }

    @Override
    public String toString() {
        return "MessageID: " + messageID + "\nMessage Hash: " + messageHash + "\nSender: " + sender + "\nRecipient: " + recipient + "\nMessage: " + messageText + "\nFlag: " + flag;
    }
}