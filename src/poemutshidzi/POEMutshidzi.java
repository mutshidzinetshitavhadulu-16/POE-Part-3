/*
- Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
- Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package poemutshidzi;
import java.util.Scanner;

public class POEMutshidzi {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login login = new Login();

        String name;
        String surname;
        String userName;
        String emailAddress;
        String password;
        String numberPhone;

        System.out.print("Enter your Name: ");
        name = input.nextLine();

        System.out.print("Enter your Surname: ");
        surname = input.nextLine();

        while (true) {
            System.out.print("Please enter your username: ");
            userName = input.nextLine();
            if (login.checkUserName(userName)) {
                System.out.println("Username succesfully captured.");
                break;
            } else {
                System.out.println("Username is not correctly formated, Username must contain an underscore, Username must atleast contain atleast 5 characters.");
            }
        }

        while (true) {
            System.out.print("Please enter your email address: ");
            emailAddress = input.nextLine();
            if (login.checkEmailAddress(emailAddress)) {
                System.out.println("Email address succesfully captured");
                break;
            } else {
                System.out.println("Email address is not correctly formated: Email address must contain @gmail.com");
            }
        }

        while (true) {
            System.out.print("Please enter your password: ");
            password = input.nextLine();
            if (login.checkPasswordComplexity(password)) {
                System.out.println("password succesfully captured");
                break;
            } else {
                System.out.println("Password is not correctly formatted, Password must contain atleast 8 characters, Password must contain a capital letter, Password must contain a number, Password must contain a special character.");
            }
        }

        while (true) {
            System.out.print("Please enter your number phone: ");
            numberPhone = input.nextLine();
            if (login.checkCellPhoneNumber(numberPhone)) {
                System.out.println("number phone succesfully captured");
                break;
            } else {
                System.out.println("number phone is not correctly formatted, Number phone must contain international code, Number phone must atleast contain 10 digits");
            }
        }

        System.out.println(login.registerUser(name, surname, userName, password, numberPhone));
        System.out.println("you have registerd succesfully");

        // LOGIN
        System.out.print("Enter username to login: ");
        String loginUser = input.nextLine();
        System.out.print("Enter password to login: ");
        String loginPass = input.nextLine();
        System.out.println(login.returnLoginStatus(loginUser, loginPass));
        if (login.loginUser(loginUser, loginPass) == false) {
            System.out.println("Login failed. Cannot send messages.");
            return;
        }
        System.out.println("Welcome " + name + " " + surname);

        // PART 2 + 3
        SendMessage.sender = userName;
        SendMessage.startQuickChat(input);
        input.close();
    }
}
