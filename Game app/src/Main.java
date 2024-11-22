import ucab.edu.objects.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
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

        int opc, opc2, x;
        char y;
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
                    Player player1 = new Player(user1.getAlias(), 0,
                            7,bag.fillNewHolder(7), false);
                    Player player2 = new Player(user2.getAlias(),0,
                            7,bag.fillNewHolder(7), false);

                    //Establecer orden de jugadores
                    order = newOrder(player1,player2);

//        do {
                    for (Player turn : order.getPlayers()) {
                        out = false;
                        System.out.println();
                        System.out.println("\n" + ANSI_WHITE_BACKGROUND + ANSI_BLACK + "  Es el turno de: " + turn.getAlias() + "  "+ ANSI_RESET);
                        Thread.sleep(1000);
                        while(!out){
                            board.printTable();
                            turn.getHolder().showLetters();
                            System.out.println(ANSI_BLUE + "Score: " + ANSI_RESET + turn.getScore());
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
                                    String token;
                                    do{
                                        System.out.println("Ingrese el número de la coordenada x en donde va a iniciar su palabra.");
                                        x = read.nextInt();
                                    }while(!board.verifyCoordinateX(x));
                                    do{
                                        System.out.println("Ingrese la letra de la coordenada y en donde va a iniciar su palabra.");
                                        y = read.next().charAt(0);
                                        y = Character.toUpperCase(y);
                                    }while(!board.verifyCoordinateY(y));
                                    System.out.println(ANSI_BLUE+"La casilla elegida fue: " + ANSI_RESET + x + ", " + y);
                                    out = true;
                                    break;

                                case 2:
                                    Exchange exchange = new Exchange();
                                    String change;
                                    do {
                                        System.out.println("\n" + ANSI_YELLOW + "Escriba la letra de la ficha que desea cambiar: " + ANSI_RESET);
                                        System.out.println(ANSI_GREEN + "En caso que desee realizar otra acción ingrese uno de los siguientes números:");
                                        System.out.println("1. Terminar y realizar cambio de fichas.");
                                        System.out.println("2. Retroceder selección.");
                                        System.out.println("0. Volver al menú de opciones." + ANSI_RESET);
                                        change = read.next();
                                        if(Objects.equals(change, "0")) {
                                            turn.getHolder().getTokensHold().addAll(exchange.getChangeLetters());
                                        }
                                        else if(Objects.equals(change, "1")){
                                            bag.fillBag(exchange.getChangeLetters());
                                            turn.getHolder().finishExchange(bag,exchange.getChangeLettersSize());
                                            turn.getHolder().showLetters();
                                            out=true;
                                        }
                                        else if(Objects.equals(change, "2")){
                                            turn.getHolder().backtrackExchange(exchange);
                                            turn.getHolder().showLetters();
                                            exchange.showLetters();
                                        }
                                        else if(new ChangeLetter().setChangeLetter(exchange,turn.getHolder(),change)){
                                            turn.getHolder().showLetters();
                                            exchange.showLetters();
                                        }
                                    }while(!Objects.equals(change,"0") && !Objects.equals(change,"1"));
                                    break;

                                case 3:
                                    out = true;
                                    break;

                                default:
                                    System.out.println("ERROR. número ingresado fuera de los parámetros indicados.");
                                    break;
                            }
                        }
                        if(turn.isWinner() || end){break;}
                    }
//        }while((!player1.isWinner() && !player2.isWinner()) && !end);

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