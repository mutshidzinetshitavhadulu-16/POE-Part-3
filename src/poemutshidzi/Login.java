/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package poemutshidzi;

public class Login {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellPhone;

    public boolean checkUserName(String username) {
        if (username == null) {
            return false;
        }
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        if (password == null) {
            return false;
        }
        if (password.length() < 8) {
            return false;
        }
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                hasCapital = true;
            }
            if (c >= '0' && c <= '9') {
                hasNumber = true;
            }
            if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z') && !(c >= '0' && c <= '9')) {
                hasSpecial = true;
            }
        }
        if (hasCapital && hasNumber && hasSpecial) {
            return true;
        } else {
            return false;
        }
    }
    public boolean checkEmailAddress(String email) {
    if (email == null) return false;
    if (!email.contains("@")) 
        return false;
    if (!email.contains("."))
        return false;
    if (email.length() < 5) return false;
    // must have @ before .
    if (email.indexOf("@") > email.lastIndexOf(".")) return false;

        return true;
}

    public boolean checkCellPhoneNumber(String cellPhone) {
        if (cellPhone == null) {
            return false;
        }
        if (cellPhone.length() < 11 || cellPhone.length() > 13) {
            return false;
        }
        if (cellPhone.charAt(0) != '+') {
            return false;
        }
        for (int i = 1; i < cellPhone.length(); i++) {
            char c = cellPhone.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public String registerUser(String firstName, String lastName, String username, String password, String cellPhone) {
        if (checkUserName(username) == false) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (checkPasswordComplexity(password) == false) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (checkCellPhoneNumber(cellPhone) == false) {
            return "Cell number is incorrectly formatted or does not contain an international code, please correct the number and try again.";
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhone = cellPhone;
        return "User registered successfully.";
    }

    public boolean loginUser(String username, String password) {
        if (this.username == null || this.password == null) {
            return false;
        }
        if (this.username.equals(username) && this.password.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password) == true) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }



    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getUsername() { return username; }
    public String getCellPhone() { return cellPhone; }
}    
       

