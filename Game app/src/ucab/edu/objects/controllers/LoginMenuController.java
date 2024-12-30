package ucab.edu.objects.controllers;

import ucab.edu.objects.models.LoginMenuModel;
import ucab.edu.objects.users.User;
import ucab.edu.objects.views.LoginMenuView;

import java.util.LinkedList;

public class LoginMenuController {
    private LoginMenuModel model;
    private LoginMenuView view;

    public LoginMenuController() {
        this.model = new LoginMenuModel();
        this.view = new LoginMenuView();
    }

    public LinkedList<User> usersLogIn(LinkedList<User> usersLinkedList) {
        User newUser, user1, user2;
        boolean userFound;
        LinkedList<User> usersLogged = new LinkedList<User>();

        view.showUsersMenu(usersLinkedList);

        for (int i = 1; i <= 2; i++){
            newUser = view.getUser(i);

            userFound = model.validateUser(usersLinkedList, newUser);

            if(userFound) {
                usersLogged.add(newUser);
            } else {
                System.out.println("El usuario "+i+" no ha sido encontrado, porfavor ingrese un usuario existente");
                i-=1;
            }

        }
        return usersLogged;
    }


}
