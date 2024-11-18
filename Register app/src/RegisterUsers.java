import project.users.*;
import project.users.exceptions.*;
import json.handler.*;

import java.util.LinkedList;
import java.util.Scanner;

public class RegisterUsers{
    public static void main(String[] args) throws InterruptedException {
        /*
        //ADD TO LIST
        listUsersToAdd.add(user2);
        //MANAGING JSON
        JsonHandler.writeToJson(listUsersToAdd);
        LinkedList<User> listOfUsers = JsonHandler.readFromJson();
        */

        //MENU FINAL
        Scanner read = new Scanner(System.in);
        int opc;
        User user = null;
        Email email = null;
        LinkedList<User> listOfUsers = new LinkedList<User>();

        do{
            listOfUsers = JsonHandler.readFromJson();
            Scanner select = new Scanner(System.in);
            System.out.println("\t\t MENU JSON FILE: \n");
            System.out.println("Ingrese la opcion que desea realizar.");
            System.out.println("1. Register User");
            System.out.println("2. Modify Name");
            System.out.println("3. Modify Email");
            System.out.println("4. Show File");
            System.out.println("5. Delete User");
            System.out.println("0. Salir.");
            opc = select.nextInt();

            switch(opc){
                case 0:
                    System.out.println("Saliendo...");
                    Thread.sleep(1000);
                    System.out.println("Vuelva Pronto...");
                    Thread.sleep(1000);
                    break;
                case 1:
                    //EMAIL
                    while (true) {
                        System.out.println("User 1 email: ");
                        String mail = read.next();
                        email = new Email(mail);
                        try {
                            email.validateEmail();
                            break;
                        } catch (InvalidEmailException ex) {
                            ex.messageInvalidEmailException();
                        }
                    }
                    //ALIAS
                    while (true) {
                        System.out.println("User 1 alias: ");
                        String alias = read.next();
                        user = new User(alias, email);
                        try {
                            user.validateAlias();
                            break;
                        } catch (InvalidAliasException ex) {
                            ex.messageInvalidAliasException();
                        }
                    }
                    listOfUsers.add(user);
                    JsonHandler.writeToJson(listOfUsers);

                    break;

                case 2:
                    break;
                case 3:
                    break;

                case 4:
                    if(listOfUsers == null) {
                        listOfUsers = new LinkedList<User>();
                        System.out.println("LINKED LIST IS EMPTY.");
                    }
                    for (User user1 : listOfUsers) {
                        System.out.println("alias: " + user1.getAlias() + ", email: " + user1.getStringEmail());
                    }

                    break;
                case 5:
                    int i = 0;
                    if(listOfUsers == null) {
                        listOfUsers = new LinkedList<User>();
                        System.out.println("LINKED LIST IS EMPTY.");
                    }
                    for (User user1 : listOfUsers) {
                        System.out.println(i + ". " + "alias: " + user1.getAlias() + ", email: " + user1.getStringEmail());
                        i++;
                    }
                    System.out.println("\n Write the index of the user to delete: ");
                    int index = read.nextInt();
                    listOfUsers.remove(index);
                    JsonHandler.writeToJson(listOfUsers);
                    break;

                default:
                    System.out.println("ERROR. Invalid option");
                    break;
            }

        }while(opc != 0);
    }


}

