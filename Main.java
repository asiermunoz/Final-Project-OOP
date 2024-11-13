import ucab.edu.objects.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ucab.edu.objects.Color.*;
import static ucab.edu.objects.User.*;

public class Main {

    private static Order newOrder(Player player1, Player player2){
        FirstOne first;
        Random randomChoice = new Random();
        if(randomChoice.nextBoolean()){
            first = new FirstPlayer1();
        }
        else{
            first = new FirstPlayer2();
        }
        return first.setFirst(player1,player2);
    }

    //Menú principal.
    private static int menu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "---------SCRABBLE-UCAB---------" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "Ingrese el número de la acción que desee realizar:" + ANSI_RESET);
        System.out.println("1. Jugar nueva Partida.");
        System.out.println("2. Continuar partida anterior.");
        System.out.println("3. Mostrar lista total de jugadores.");
        System.out.println("4. Mostrar estadística de jugadores.");
        System.out.println("0. Salir.");
        return scanner.nextInt();
    }

    private static boolean setFirst(){
        Random randomChoice = new Random();
        return randomChoice.nextBoolean();
    }

    private static String logIn(){
        Scanner read = new Scanner(System.in);
        String email;
        do{
            email=read.nextLine();
            if(!validateEmail(email)){
                System.out.println(ANSI_RED + "Email no valido." + ANSI_RESET);
                System.out.println("Ingrese de nuevo su email.");
            }
        }while(!validateEmail(email));
        return email;
    }

    public static void main(String[] args) throws InterruptedException, IOException {

        Scanner read = new Scanner(System.in);
        String email, alias;
        User user1 = null, user2 = null;
        int option;
        System.out.println(ANSI_YELLOW+"MENU DE INGRESO DE USUARIOS:" + ANSI_RESET);

        for (int i = 1; i <= 2; i++){
            System.out.println("Ingrese el mail del usuario " + i + ":");
            email=logIn();
            System.out.println("Ingrese el alias del usuario " + i + ":");
            alias = read.nextLine();
            if(i==1){ user1 = new User(email,alias); }
            else { user2 = new User(email,alias); }
        }
        System.out.println(ANSI_GREEN+"\nBienvenidos " + user1.getAlias() + " y " + user2.getAlias());
        Thread.sleep(800);
        do{
            option = menu();
            switch(option){
                case 1:
                    //new Game
                    String[][] table = new String [15][15];
                    Board board = new Board(table);
                    board.emptyTable();
                    Bag bag = new Bag();
                    Order order;

                    System.out.println("Iniciando nuevo juego: \n");
                    Player player1 = new Player(user1.getAlias(),user1.getEmail(),0,
                            7,bag.fillNewHolder(7), false);
                    Player player2 = new Player(user2.getAlias(),user2.getEmail(),0,
                            7,bag.fillNewHolder(7), false);
                    //Establecer orden de jugadores
                    order = newOrder(player1,player2);
//                  do {
                        for (Player turn : order.getPlayers()) {
                            System.out.println("Es el turno de: " + turn.getAlias());
                            Thread.sleep(800);
                            System.out.printf("%3s"," ");
                            for(int i = 1; i<=15; i++){
                                System.out.printf("%3s",i);
                                System.out.printf("%s"," ");
                            }
                            System.out.println();
                            board.printTable();
                            Thread.sleep(800);
                            System.out.println("Letras que posee " + turn.getAlias() + ":"); turn.seeHolder();
                        }
//                  }while(!player1.isWinner() && !player2.isWinner());

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    System.out.println(ANSI_BLUE + "Saliendo del juego...");
                    Thread.sleep(800); //Pausa la corrida de código por los milisegundos establecidos.
                    System.out.println(ANSI_YELLOW + "Vuelva pronto!");
                    break;
                default:
                    System.out.println(ANSI_RED + "El número ingresado no posee acción alguna\n");
                    Thread.sleep(500); //Pausa la corrida de código por los milisegundos establecidos.
                    break;
            }
        }while(option != 0);
    }
}