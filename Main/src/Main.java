import project.users.*;
import project.users.exceptions.*;
import json.handler.*;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner read = new Scanner(System.in);
        int opc;
        User user1, user2;
        Email email1, email2;
        LinkedList<User> listUsersToAdd = new LinkedList<User>();

        //RUN menu
        System.out.println("\t\t LOGIN MENU USERS: \n");

        //User 1.
        //ENTER EMAIL
        while (true) {
            System.out.println("User 1 email: ");
            email1 = new Email(read.nextLine());
            try {
                email1.validateEmail();
                break;
            } catch (InvalidEmailException ex) {
                ex.messageInvalidEmailException();
            }
        }

        //ENTER ALIAS
        while (true) {
            System.out.println("User 1 alias: ");
            user1 = new User(read.nextLine(), email1);
            try {
                user1.validateAlias();
                break;
            } catch (InvalidAliasException ex) {
                ex.messageInvalidAliasException();
            }
        }

        //ADD TO LIST
        listUsersToAdd.add(user1);

        //User 2.
        //ENTER EMAIL
        while (true) {
            System.out.println("User 2 email: ");
            email2 = new Email(read.nextLine());
            try {
                email2.validateEmail();
                break;
            } catch (InvalidEmailException ex) {
                ex.messageInvalidEmailException();
            }
        }

        //ENTER ALIAS
        while (true) {
            System.out.println("User 2 alias: ");
            user2 = new User(read.nextLine(), email2);
            try {
                user2.validateAlias();
                break;
            } catch (InvalidAliasException ex) {
                ex.messageInvalidAliasException();
            }
        }

        //ADD TO LIST
        listUsersToAdd.add(user2);

        //MANAGING JSON

        JsonHandler.writeToJson(listUsersToAdd);

        LinkedList<User> listOfUsers = JsonHandler.readFromJson();

        do{
            Scanner select = new Scanner(System.in);
            System.out.println("\t\t MENU SCRABBLE: \n");
            System.out.println("Ingrese la opcion que desea realizar.");
            System.out.println("1. Iniciar nuevo juego.");
            System.out.println("2. Continuar juego.");
            System.out.println("3. Mostrar lista de Jugadores.");
            System.out.println("4. Mostrar estadisticas de jugadores.");
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
                    break;
                case 2:
                    break;
                case 3:

                    break;
                case 4:
                    break;
                default:
                    System.out.println("ERROR. Invalid option");
                    break;
            }

        }while(opc != 0);
    }
}

