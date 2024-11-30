import ucab.edu.objects.*;
import ucab.edu.objects.jsonHandlers.JsonGamesHandler;
import ucab.edu.objects.jsonHandlers.JsonUserHandler;
import ucab.edu.objects.users.Email;
import ucab.edu.objects.users.User;
import ucab.edu.objects.users.exceptions.InvalidAliasException;
import ucab.edu.objects.users.exceptions.InvalidEmailException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import static ucab.edu.objects.Color.*;

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

    public static boolean verifySelection(int opc, int min, int max){
        try {
            if (opc < min || opc > max) {
                throw new OutOfRangeException();
            }
            return true;
        }catch(OutOfRangeException e){
            System.out.println(e.getMessage());
            return false;
        }
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

    private static User logIn(int index){
        Scanner read = new Scanner(System.in);
        String alias, emailText;
        Email email;
        User user;

        //Email Login
        while(true) {
            System.out.println("User "+index+" email: ");
            emailText = read.next();
            email = new Email(emailText);
            try {
                email.validateEmail();
                break;
            } catch (InvalidEmailException ex) {
                ex.messageInvalidEmailException();
            }
        }

        //Alias Login
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

    public static void main(String[] args) throws InterruptedException, IOException {

        //Variables de lectura de archivos
        boolean gameAlreadyCreated = false;
        boolean userFound = false;
        LinkedList<User> usersLinkedList = new LinkedList<User>();
        LinkedList<GameInformation> gamesInProgress = new LinkedList<GameInformation>();

        Scanner read = new Scanner(System.in);
        int opc,opc2,x;
        char y;
        Game game = new Game();
        Board board = new Board();
        Bag bag = new Bag();
        Letter letter;
        Order order;
        boolean out;
        boolean end = false;
        GameInformation currentGameInformation;
        User user1 = null, user2 = null, newUser = null;
        int option;

        //Json Reading
        usersLinkedList = JsonUserHandler.readFromJson();
        gamesInProgress = JsonGamesHandler.readFromJson();

        System.out.println(ANSI_YELLOW+"MENU DE INGRESO DE USUARIOS:" + ANSI_RESET);
        for (User testuser : usersLinkedList) {
            System.out.println("alias: " + testuser.getAlias() + ", email: " + testuser.getStringEmail());
        }

        for (int i = 1; i <= 2; i++){
            newUser = logIn(i);

            //Search newUser in the usersLinkedList exported in the Json file
            for(int j = 0; j<usersLinkedList.size(); j++) {
                if(newUser.equals(usersLinkedList.get(j))) {
                    userFound = true;
                    j = usersLinkedList.size();
                }
            }

            //Validate newUser
            if(userFound) {
                if(i==1){ user1 = newUser; }
                else { user2 = newUser; }
                userFound = false;
            } else {
                System.out.println("El usuario "+i+" no ha sido encontrado, porfavor ingrese un usuario existente");
                i-=1;
            }
        }



        System.out.println(ANSI_GREEN+"\nBienvenidos " + user1.getAlias() + " y " + user2.getAlias());
        Thread.sleep(2000);

        //Buscar partida en base al jugador 1
        for(int i = 0; i <= gamesInProgress.size(); i++) {
            if(user1.equalsName(gamesInProgress.get(i).getPlayer1Alias())) {

                //Si se encuentra al jugador 1 se busca al jugador 2
                for(int j = 0; j <= gamesInProgress.size(); j++) {
                    if(user2.equalsName(gamesInProgress.get(i).getPlayer2Alias())) {
                        System.out.println("Partida encontrada");
                    }
                }

            //Si no se encuentra se busca el user pero como jugador 2
            } else if (user1.equalsName(gamesInProgress.get(i).getPlayer2Alias())) {

                //Si se encuentra se busca al jugador 2
                for(int j = 0; j <= gamesInProgress.size(); j++) {
                    if (user2.equalsName(gamesInProgress.get(i).getPlayer1Alias())) {
                        System.out.println("Partida encontrada");
                    }
                }

            //No se consiguio ninguno
            } else {
                System.out.println("No se ha encontrado ninguna partida");
            }

        }

        do{
            option = menu();
            switch(option){
                case 1:
                    //New game
                    System.out.println("Iniciando nuevo juego: ");
                    Thread.sleep(1000);
                    Player player1 = new Player(user1.getAlias(), 0, bag.fillNewHolder(7), false);
                    Player player2 = new Player(user2.getAlias(),0, bag.fillNewHolder(7), false);

                    //Establecer orden de jugadores
                    order = newOrder(player1,player2);

//        do {
                    for (Player turn : order.getPlayers()) {
                        out = false;
                        System.out.println();
                        System.out.println("\n" + ANSI_WHITE_BACKGROUND + ANSI_BLACK + "  Es el turno de: " + turn.getAlias() + "  "+ ANSI_RESET);
                        Thread.sleep(2000);
                        while(!out){
                            board.show();
                            turn.getHolder().show();
                            System.out.println(ANSI_BLUE + "Score: " + ANSI_RESET + turn.getScore());
                            if(bag.getTotal() == 0){
                                System.out.println(ANSI_RED + "AVISO:" + ANSI_YELLOW + " Bolsa vacía." + ANSI_RESET);
                            }
                            Thread.sleep(2000);
                            System.out.println(ANSI_GREEN + "Indique el número de la acción que desea realizar: " + ANSI_RESET);
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
                                    Word word = new Word();
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

                                    do{
                                        System.out.println("Indique el número de la forma en la que desea ingresar la palabra: ");
                                        System.out.println("1. Vertical ( | )");
                                        System.out.println("2. Horizontal ( - )");
                                        opc2 = read.nextByte();
                                    }while(!verifySelection(opc2,1,2));

                                    do{
                                        System.out.println("\nColoque cada letra que desea colocar: ");
                                        System.out.println("Letras ya presentes en el tablero se tomaran en cuenta automáticamente)");
                                        System.out.println(ANSI_GREEN + "En caso que desee realizar otra acción ingrese uno de los siguientes números:");
                                        System.out.println("1. Terminar y colocar palabra.");
                                        System.out.println("2. Borrar letra anterior.");
                                        System.out.println("3. Reiniciar palabra.");
                                        System.out.println("0. Volver al menú de opciones." + ANSI_RESET);
                                        token = read.next();
                                        if(Objects.equals(token,"0")){
                                            System.out.println("Saliendo al menú de opciones.");
                                        }
                                        else if(Objects.equals(token,"1")){
                                            System.out.println("Colocando letras...");
                                        }
                                        else if(Objects.equals(token,"2")){
                                            System.out.println("Volviendo atrás.");
                                        }
                                        else if(Objects.equals(token,"3")){
                                            System.out.println("Palabra borrada.");
                                        }
                                        else if((letter = turn.getHolder().takeLetter(token))!=null){
                                            word.getHold().addLast(letter);
                                            turn.getHolder().show();
                                        }
                                    }while(!Objects.equals(token,"0") || !Objects.equals(token,"1"));
                                    break;

                                case 2:
                                    Exchange exchange = new Exchange();
                                    String change;
                                    do {
                                        System.out.println("\nEscriba la letra de la ficha que desea cambiar: ");
                                        System.out.println(ANSI_GREEN + "En caso que desee realizar otra acción ingrese uno de los siguientes números:");
                                        System.out.println("1. Terminar y realizar cambio de fichas.");
                                        System.out.println("2. Retroceder selección.");
                                        System.out.println("3. Reiniciar selección.");
                                        System.out.println("0. Volver al menú de opciones." + ANSI_RESET);
                                        change = read.next();
                                        if(Objects.equals(change, "0")) {
                                            turn.getHolder().getHold().addAll(exchange.getHold());
                                            System.out.println("Saliendo al menú de opciones.");
                                        }
                                        else if(Objects.equals(change, "1")){
                                            try {
                                                if(exchange.getHoldSize() == 0){
                                                    throw new EmptyArrayException();
                                                }
                                                bag.fillBag(exchange.getHold());
                                                turn.getHolder().finishExchange(bag, exchange.getHoldSize());
                                                out = true;
                                            }catch (EmptyArrayException e){
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                        else if(Objects.equals(change, "2")){
                                            turn.getHolder().backtrack(exchange);
                                        }
                                        else if(Objects.equals(change, "3")){
                                            turn.getHolder().restartSelection(exchange);
                                            exchange = new Exchange();
                                        }
                                        else if((letter = turn.getHolder().takeLetter(change))!=null){
                                            exchange.addLetter(letter);
                                            turn.getHolder().show();
                                            exchange.show();
                                        }
                                    }while(!Objects.equals(change,"0") && !out);
                                    break;

                                case 3:
                                    out = true;
                                    break;

                                default:
                                    System.out.println("ERROR. número ingresado fuera de los parámetros indicados.");
                                    break;
                            }
                        }
                        if(turn.getHolder().getHoldSize() == 0 || end){break;}
                    }
//        }while((player1.getHolder().getHoldSize() != 0 && player2.getHolder().getHoldSize() != 0) && !end);

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