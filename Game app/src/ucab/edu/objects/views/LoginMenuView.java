package ucab.edu.objects.views;

import ucab.edu.objects.users.Email;
import ucab.edu.objects.users.User;
import ucab.edu.objects.users.exceptions.InvalidAliasException;
import ucab.edu.objects.users.exceptions.InvalidEmailException;

import java.util.LinkedList;
import java.util.Scanner;

import static ucab.edu.objects.Color.ANSI_RESET;
import static ucab.edu.objects.Color.ANSI_YELLOW;

public class LoginMenuView {
    private Scanner read = new Scanner(System.in);

    public void showUsersMenu(LinkedList<User> listOfRegisteredUsers) {
        System.out.println(ANSI_YELLOW+"MENU DE INGRESO DE USUARIOS:" + ANSI_RESET);
        for (User testuser : listOfRegisteredUsers) {
            System.out.println("alias: " + testuser.getAlias() + ", email: " + testuser.getStringEmail());
        }
    }

    public Email getEmail(int index) {
        Email email;
        String emailText;

        while(true) {
            System.out.println("User " + index + " email: ");
            emailText = read.next();
            email = new Email(emailText);
            try {
                email.validateEmail();
                break;
            } catch (InvalidEmailException ex) {
                ex.messageInvalidEmailException();
            }
        }
        return email;
    }

    public User getUser(int index) {
        String alias;
        User user;
        Email email = getEmail(index);

        while(true) {
            System.out.println("User "+index+" alias: ");
            alias = read.next();
            user = new User(alias, email);
            try {
                user.validateAlias();
                break;
            } catch (InvalidAliasException ex) {
                ex.messageInvalidAliasException();
            }
        }
        return user;
    }
}
