import com.ucab.objects.*;
import org.w3c.dom.ls.LSOutput;
import ucab.edu.ve.Player;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner read = new Scanner(System.in);
        int opc;
        User user1, user2;
        Email email1, email2;

        //RUN menu
        System.out.println("\t\t MENU LOGIN USERS: \n");

        //User 1.
        do{
            System.out.println("User 1 email: ");
            email1 = new Email(read.nextLine());
            if(!email1.validateEmail()){
                System.out.println("Email no valido.");
                System.out.println("Ingrese de nuevo su email.");
            }
        }while(!email1.validateEmail());
        do{
            System.out.println("User 1 alias: ");
            user1 = new User(read.nextLine(), email1);
            if(!user1.validateAlias()){
                System.out.println("Alias ingresado no disponible.");
                System.out.println("Ingrese un nuevo alias.");
            }
        }while(!user1.validateAlias());

        //User 2.
        do{
            System.out.println("User 2 email: ");
            email2 = new Email(read.nextLine());
            if(!email2.validateEmail()){
                System.out.println("Email no valido.");
                System.out.println("Ingrese de nuevo su email.");
            }
        }while(!email2.validateEmail());
        do{
            System.out.println("User 2 alias: ");
            user2 = new User(read.nextLine(), email2);
            if(!user2.validateAlias()){
                System.out.println("Alias ingresado no disponible.");
                System.out.println("Ingrese un nuevo alias.");
            }
        }while(!user2.validateAlias());

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

