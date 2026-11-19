/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Login;

import poemutshidzi.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class StoredMessagesTest {

    @Test
    public void testStoredMessagesConstructorAndGetters() {
        StoredMessages msg = new StoredMessages("1", "01:0:DIDCAKE?", "User", "+27834557896", "Did you get the cake?", "Sent");
        assertEquals("1", msg.getMessageID());
        assertEquals("01:0:DIDCAKE?", msg.getMessageHash());
        assertEquals("User", msg.getSender());
        assertEquals("+27834557896", msg.getRecipient());
        assertEquals("Did you get the cake?", msg.getMessageText());
        assertEquals("Sent", msg.getFlag());
    }

    @Test
    public void testIsSentStoredDisregarded() {
        StoredMessages sent = new StoredMessages("1", "hash1", "User", "+27834557896", "Did you get the cake?", "Sent");
        StoredMessages stored = new StoredMessages("2", "hash2", "User", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
        StoredMessages disregarded = new StoredMessages("3", "hash3", "User", "+27834484567", "Yohoooo, I am at your gate.", "Disregard");
        assertTrue(sent.isSent());
        assertFalse(sent.isStored());
        assertTrue(stored.isStored());
        assertFalse(stored.isSent());
        assertTrue(disregarded.isDisregarded());
        assertFalse(disregarded.isSent());
    }

    @Test
    public void testToStringContainsAllFields() {
        StoredMessages msg = new StoredMessages("0838884567", "08:0:ITTIME!", "Developer", "0838884567", "It is dinner time !", "Sent");
        String result = msg.toString();
        assertTrue(result.contains("0838884567"));
        assertTrue(result.contains("Developer"));
        assertTrue(result.contains("It is dinner time !"));
        assertTrue(result.contains("Sent"));
    }

    @Test
    public void testAllMessagesListPopulatedFromTestData() {
        SendMessage.populateTestData();
        assertEquals(5, SendMessage.allMessages.size());
        assertEquals("Did you get the cake?", SendMessage.allMessages.get(0).getMessageText());
        assertEquals("Where are you? You are late! I have asked you to be on time.", SendMessage.allMessages.get(1).getMessageText());
        assertEquals("Yohoooo, I am at your gate.", SendMessage.allMessages.get(2).getMessageText());
        assertEquals("It is dinner time !", SendMessage.allMessages.get(3).getMessageText());
        assertEquals("Ok, I am leaving without you.", SendMessage.allMessages.get(4).getMessageText());
    }
}