/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import poemutshidzi.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {
    @Test
    public void testUsernameCorrectlyFormatted() {
        Login l = new Login();
        assertTrue(l.checkUserName("kyl_1"));
    }
    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login l = new Login();
        assertFalse(l.checkUserName("kyle!!!!!!"));
    }
    @Test
    public void testPasswordMeetsComplexity() {
        Login l = new Login();
        assertTrue(l.checkPasswordComplexity("Ch&&sec@ke99!"));
    }
    @Test
    public void testPasswordDoesNotMeetComplexity() {
        Login l = new Login();
        assertFalse(l.checkPasswordComplexity("password"));
    }
    @Test
    public void testCellPhoneCorrectlyFormatted() {
        Login l = new Login();
        assertTrue(l.checkCellPhoneNumber("+27838968976"));
    }
    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        Login l = new Login();
        assertFalse(l.checkCellPhoneNumber("08966553"));
    }
    @Test
    public void testLoginSuccessful() {
        Login l = new Login();
        l.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(l.loginUser("kyl_1", "Ch&&sec@ke99!"));
        assertEquals("Welcome Kyle, Smith it is great to see you again.", l.returnLoginStatus("kyl_1", "Ch&&sec@ke99!"));
    }
    @Test
    public void testLoginFailed() {
        Login l = new Login();
        l.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(l.loginUser("kyl_1", "wrong123"));
        assertEquals("Username or password incorrect, please try again.", l.returnLoginStatus("kyl_1", "wrong123"));
    }
}