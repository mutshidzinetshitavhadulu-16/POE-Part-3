/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Login;

import poemutshidzi.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class SendMessageTest {

    @Before
    public void setup() {
        SendMessage.populateTestData();
    }

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        assertEquals(2, SendMessage.sentMessages.size());
        assertEquals("Did you get the cake?", SendMessage.sentMessages.get(0));
        assertEquals("It is dinner time !", SendMessage.sentMessages.get(1));
    }

    @Test
    public void testDisplayLongestMessage() {
        assertEquals("Where are you? You are late! I have asked you to be on time.", SendMessage.getLongestStoredMessage());
    }

    @Test
    public void testSearchForMessageID() {
        String result = SendMessage.searchMessageByID("0838884567");
        assertTrue(result.contains("It is dinner time !"));
        assertTrue(result.contains("0838884567"));
    }

    @Test
    public void testSearchMessagesByRecipient() {
        String result = SendMessage.searchMessagesByRecipient("+27838884567");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteMessageByHash() {
        String hashToDelete = SendMessage.storedHashes.get(0);
        String result = SendMessage.deleteMessageByHash(hashToDelete);
        assertEquals("Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.", result);
        assertEquals(1, SendMessage.storedMessages.size());
    }

    @Test
    public void testDisplayReport() {
        String report = SendMessage.displayReport();
        assertTrue(report.contains("Message Hash"));
        assertTrue(report.contains("Recipient"));
        assertTrue(report.contains("Message"));
        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains("Where are you? You are late! I have asked you to be on time."));
    }

    @Test
    public void testCheckRecipientCell_Success() {
        SendMessage.recipient = "+27718693002";
        assertEquals("Cell phone number successfully captured.", SendMessage.checkRecipientCell());
    }

    @Test
    public void testCheckMessageLength() {
        SendMessage.messageText = "Hi there";
        assertEquals("Message ready to send.", SendMessage.checkMessageLength());
    }

    @Test
    public void testCheckMessageID() {
        SendMessage.messageID = SendMessage.createMessageID();
        assertTrue(SendMessage.checkMessageID());
        assertEquals(10, SendMessage.messageID.length());
    }
}