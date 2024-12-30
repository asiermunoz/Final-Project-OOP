package ucab.edu.objects.models;

import ucab.edu.objects.users.User;

import java.util.LinkedList;

public class LoginMenuModel {

    public boolean validateUser(LinkedList<User> usersLinkedList, User newUser) {
        for (User user : usersLinkedList) {
            if (newUser.equals(user)) {
                return true;
            }
        }
        return false;
    }
}
