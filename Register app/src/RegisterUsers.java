import project.users.*;
import project.users.exceptions.*;
import json.handler.*;

import java.util.LinkedList;
import java.util.Scanner;

public class RegisterUsers{
    public static void main(String[] args) throws InterruptedException {

        //MENU FINAL
        Scanner read = new Scanner(System.in);
        int opc;
        User user = null;
        Email email = null;
        LinkedList<User> listOfUsers = new LinkedList<User>();
        int index;
        String alias, mail;

        do{
            listOfUsers = JsonHandler.readFromJson();
            System.out.println("\t\t MENU JSON FILE: \n");
            System.out.println("1. Register User");
            System.out.println("2. Modify Name");
            System.out.println("3. Modify Email");
            System.out.println("4. Delete User");
            System.out.println("0. Salir.\n");

            //LISTA DE USUARIOS REGISTRADOS
            int listIndex = 0;


            if(listOfUsers == null) {
                listOfUsers = new LinkedList<User>();
                System.out.println("LINKED LIST IS EMPTY.");
            }
            for (User user1 : listOfUsers) {
                System.out.println(listIndex + ". " + "alias: " + user1.getAlias() + ", email: " + user1.getStringEmail());
                listIndex++;
            }

            //INSERTAR OPCION
            System.out.println("\n Ingrese la opcion que desea realizar: ");
            opc = read.nextInt();


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
                        mail = read.next();
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
                        alias = read.next();
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
                    System.out.println("Index of User to Modify: ");
                    index = read.nextInt();
                    System.out.println("Type the new Name: ");
                    String name = read.next();

                    listOfUsers.get(index).setAlias(name);
                    JsonHandler.writeToJson(listOfUsers);
                    break;
                case 3:
                    System.out.println("Index of User to Modify: ");
                    index = read.nextInt();
                    System.out.println("Type the new email: ");
                    mail = read.next();
                    Email emailToModify = new Email(mail);

                    listOfUsers.get(index).setEmail(emailToModify);
                    JsonHandler.writeToJson(listOfUsers);
                    break;

                case 4:
                    System.out.println("\n Write the index of the user to delete: ");
                    index = read.nextInt();
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

