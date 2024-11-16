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

        int opc;
        int opc2 = 0;
        Game game = new Game();
        String[][] table = new String [15][15];
        Board board = new Board(table);
        board.emptyTable();
        Bag bag = new Bag();
        Order order;
        boolean out;
        boolean end = false;
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
        Thread.sleep(1000);
        do{
            option = menu();
            switch(option){
                case 1:
                    //New game
                    System.out.println("Iniciando nuevo juego: ");
                    Thread.sleep(800);
                    Player player1 = new Player(user1.getEmail(), user1.getAlias(), 0,
                            7,bag.fillNewHolder(7), false);
                    Player player2 = new Player(user2.getEmail(), user2.getAlias(),0,
                            7,bag.fillNewHolder(7), false);

                    //Establecer orden de jugadores
                    order = newOrder(player1,player2);

//                  do {
                    for (Player turn : order.getPlayers()) {
                        out = false;
                        System.out.println();
                        System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLACK + "  Es el turno de: " + turn.getAlias() + "  "+ ANSI_RESET);
                        Thread.sleep(1000);
                        while(!out){
                            board.printTable();
                            System.out.println(ANSI_YELLOW+"Letras que posee " + turn.getAlias() + ":" + ANSI_RESET); turn.getHolder().seeHolder();
                            Thread.sleep(1000);
                            System.out.println(ANSI_GREEN + "Indique la acción que desea realizar: " + ANSI_RESET);
                            System.out.println("1. Ingresar palabra.");
                            System.out.println("2. Cambiar fichas.");
                            System.out.println("3. Pasar turno.");
                            System.out.println("0. Salir.");
                            opc = read.nextByte();

                            switch (opc) {
                                case 0:
                                    end = game.endGame(order, turn);
                                    out = end;
                                    break;
                                case 1:
                                    out = true;
                                    break;
                                case 2:
                                    String change;
                                    Letter changeLetter;
                                    ArrayList<Letter> changeLetters = new ArrayList<>();
                                    Holder lettersToChange;
                                    do {
                                        System.out.println("Escriba la ficha que desea cambiar: ");
                                        change = read.next();

                                        if((changeLetter = turn.getHolder().searchLetter(change)) == null){
                                            System.out.println(ANSI_RED + "ERROR. El jugador " + turn.getAlias() + " no posee la ficha " + change + " en su inventario" + ANSI_RESET);
                                        }
                                        else{
                                            changeLetters.add(changeLetter);
                                            turn.getHolder().getTokensHold().remove(changeLetter);
                                            do{
                                                System.out.print(ANSI_YELLOW + "\nFichas actuales del jugador:" + ANSI_RESET);
                                                turn.getHolder().seeHolder();
                                                lettersToChange = new Holder(changeLetters);
                                                System.out.print(ANSI_CYAN + "Fichas que se van a cambiar:" + ANSI_RESET);
                                                lettersToChange.seeHolder();
                                                System.out.println(ANSI_GREEN + "Indique la acción que desea realizar: " + ANSI_RESET);
                                                System.out.println("1. Agregar más fichas para cambiar.");
                                                System.out.println("2. Realizar cambio.");
                                                System.out.println("0. Volver atras.");
                                                opc2 = read.nextByte();

                                                if(opc2 != 1 && opc2 !=2 && opc2 != 0){
                                                    System.out.println(ANSI_RED + "Opción ingresada no válida" + ANSI_RESET);
                                                }
                                            }while(opc2 != 1 && opc2 !=2 && opc2 != 0);
                                        }
                                    }while(opc2 != 2 && opc2 != 0);
                                    if(opc2 != 0){
                                        bag.fillBag(changeLetters);
                                        turn.getHolder().getTokensHold().addAll(bag.changeHolder(changeLetters.size()));
                                        System.out.print(ANSI_YELLOW + "Fichas actuales del jugador " + turn.getAlias() + ":" + ANSI_RESET);
                                        turn.getHolder().seeHolder();
                                        Thread.sleep(1600);
                                        out = true;
                                    }
                                    else{
                                        turn.getHolder().getTokensHold().addAll(changeLetters);
                                    }
                                    break;

                                case 3:
                                    out = true;
                                    break;
                                default:
                                    System.out.println("Número ingresado fuera de los parámetros establecidos.");
                                    break;
                            }
                        }
                        if(turn.isWinner() || end){break;}
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