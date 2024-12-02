import ucab.edu.objects.*;
import ucab.edu.objects.jsonHandlers.JsonFinishedGamesHandler;
import ucab.edu.objects.jsonHandlers.JsonGamesHandler;
import ucab.edu.objects.jsonHandlers.JsonUserHandler;
import ucab.edu.objects.users.Email;
import ucab.edu.objects.users.User;
import ucab.edu.objects.users.exceptions.InvalidAliasException;
import ucab.edu.objects.users.exceptions.InvalidEmailException;

import java.io.IOException;
import java.time.LocalTime;
import java.util.*;

import static ucab.edu.objects.Color.*;

public class Main {
    private static final int initialLettersNeeded = 7;
    private static final Scanner read = new Scanner(System.in);

    public static Rotation setWriter(int opc, Board board){
        try{
            if(opc == 1){
                return new Vertical(board);
            }
            else if(opc == 2){
                return new Horizontal(board);
            }
            else{
                throw new OutOfRangeException();
            }
        }catch(OutOfRangeException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    //Menú principal.
    private static int menu(){
        System.out.println(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "---------SCRABBLE-UCAB---------" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "Ingrese el número de la acción que desee realizar:" + ANSI_RESET);
        System.out.println("1. Jugar nueva Partida.");
        System.out.println("2. Continuar partida anterior.");
        System.out.println("3. Mostrar estadística de jugadores.");
        System.out.println("0. Salir.");
        return read.nextInt();
    }

    public static boolean endGame(Order order, Player turn){
        int opc;
        for(Player next: order.getPlayers()){
            if(next != turn){
                do {
                    System.out.println("El jugador " + next.getAlias() + " desea terminar igualmente la partida?");
                    System.out.println("1. Sí");
                    System.out.println("0. No");
                    opc = read.nextByte();
                    if (opc == 1) {
                        System.out.println("Saliendo del juego.");
                        return true;
                    } else if (opc == 0) {
                        System.out.println("Volviendo al juego.");
                        return false;
                    } else {
                        System.out.println("ERROR. número ingresado fuera de los parámetros indicados.");
                    }
                }while(opc != 1 && opc != 0);
            }
        }
        return false;
    }

    public static GameInformation playGame(Player player1, Player player2, Order order, Board board, Bag bag, TimePlayed timePlayed) throws InterruptedException {
        int opc,opc2,x;
        char y;
        Letter letter;
        boolean out;
        boolean end = false;
        GameInformation gameInformation;
        LocalTime oldHour = LocalTime.now();

        do {
            for (Player turn : order.getPlayers()) {
                out = false;
                System.out.println();
                System.out.println("\n" + ANSI_WHITE_BACKGROUND + ANSI_BLACK + "  Es el turno de: " + turn.getAlias() + "  "+ ANSI_RESET);
                Thread.sleep(1000);
                while(!out){
                    board.show();
                    turn.getHolder().show();
                    System.out.println(ANSI_BLUE + "Score de " + turn.getAlias() + ": " + ANSI_RESET + turn.getScore());
                    if(bag.getTotal() == 0){
                        System.out.println(ANSI_RED + "AVISO:" + ANSI_YELLOW + " Bolsa vacía." + ANSI_RESET);
                    }
                    Thread.sleep(1000);
                    System.out.println(ANSI_GREEN + "Indique el número de la acción que desea realizar: " + ANSI_RESET);
                    System.out.println("1. Ingresar palabra.");
                    System.out.println("2. Cambiar fichas.");
                    System.out.println("3. Pasar turno.");
                    System.out.println("0. Salir.");
                    opc = read.nextByte();

                    switch (opc) {
                        case 0:
                            end = endGame(order, turn);
                            out = end;
                            break;
                        case 1:
                            Word word = new Word();
                            String token;
                            Rotation writer;

                            //Coordenadas.
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

                            //Rotación.
                            do{
                                System.out.println("Indique el número de la forma en la que desea ingresar la palabra: ");
                                System.out.println("1. Vertical ( | )");
                                System.out.println("2. Horizontal ( - )");
                                opc2 = read.nextByte();
                            }while((writer = setWriter(opc2,board)) == null);

                            turn.getHolder().show();
                            do{
                                System.out.println("\nColoque cada letra que desea colocar: ");
                                System.out.println("Si desea colocar un comodín simplemente coloque la letra con la que desea reemplazarlo.");
                                System.out.println(ANSI_YELLOW + "(Letras ya presentes en el tablero se tomaran en cuenta automáticamente)");
                                System.out.println(ANSI_GREEN + "En caso que desee realizar otra acción ingrese uno de los siguientes números:");
                                System.out.println("1. Terminar y colocar palabra.");
                                System.out.println("2. Borrar letra anterior.");
                                System.out.println("3. Reiniciar palabra.");
                                System.out.println("0. Volver al menú de opciones." + ANSI_RESET);
                                token = read.next();
                                if(Objects.equals(token,"0")){
                                    turn.getHolder().takeEverythingBack(word);
                                    System.out.println("Saliendo al menú de opciones.");
                                }
                                else if(Objects.equals(token,"1")){
                                    Word toPut = new Word();
                                    toPut.getHold().addAll(word.getHold());

                                    //Función para colocar palabra en la tabla;
                                    if (writer.write(toPut, x-1, y - 65, initialLettersNeeded)) {
                                        int newScore = writer.getScore();
                                        board = writer.getBoard();
                                        board.show();
                                        turn.setScore(newScore + turn.getScore());
                                        System.out.println(ANSI_BLUE + "Score actual de " + turn.getAlias() + ": " + ANSI_RESET + turn.getScore());
                                        if(bag.getTotal() != 0){
                                            turn.getHolder().getHold().addAll(bag.reFill(initialLettersNeeded - turn.getHolder().getHoldSize()));
                                        }
                                        turn.getHolder().show();
                                        Thread.sleep(2000);
                                        out = true;
                                    }
                                    else{
                                        board.show();
                                        writer.setBoard(board);
                                        turn.getHolder().takeEverythingBack(word);
                                        word = new Word();
                                        turn.getHolder().show();
                                        word.show();
                                    }
                                }
                                else if(Objects.equals(token,"2")){
                                    turn.getHolder().backtrack(word);
                                }
                                else if(Objects.equals(token,"3")){
                                    turn.getHolder().takeEverythingBack(word);
                                    word = new Word();
                                }
                                else if((letter = turn.getHolder().takeLetter(token))!=null){
                                    word.addLetter(letter);
                                    turn.getHolder().show();
                                    word.show();
                                }
                            }while(!Objects.equals(token,"0") && !out);
                            break;

                        case 2:
                            Exchange exchange = new Exchange();
                            String change;
                            do {
                                System.out.println("\nEscriba la letra de la ficha que desea cambiar: ");
                                System.out.println("Si desea intercambiar un comodín se recomienda copiar y pegar la carita feliz para evitar confuciones.");
                                System.out.println(ANSI_GREEN + "En caso que desee realizar otra acción ingrese uno de los siguientes números:");
                                System.out.println("1. Terminar y realizar cambio de fichas.");
                                System.out.println("2. Retroceder selección.");
                                System.out.println("3. Reiniciar selección.");
                                System.out.println("4. Intercambiar todas las fichas.");
                                System.out.println("0. Volver al menú de opciones." + ANSI_RESET);
                                change = read.next();
                                if(Objects.equals(change, "0")) {
                                    turn.getHolder().takeEverythingBack(exchange);
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
                                    }catch (EmptyArrayException _){
                                    }
                                }
                                else if(Objects.equals(change, "2")){
                                    turn.getHolder().backtrack(exchange);
                                }
                                else if(Objects.equals(change, "3")){
                                    turn.getHolder().takeEverythingBack(exchange);
                                    exchange = new Exchange();
                                }
                                else if(Objects.equals(change,"4")){
                                    turn.getHolder().exchangeAll(bag);
                                    out = true;
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
                            System.out.println(ANSI_RED + "ERROR. número ingresado fuera de los parámetros indicados." + ANSI_RESET);
                            break;
                    }
                }
                if(turn.getHolder().getHoldSize() == 0 || end){break;}
            }

            LocalTime newHour = LocalTime.now();
            GameTimer timer = new GameTimer(0,0,0,0,0,0);
            LocalTime newTimePlayed = timer.calculateDiference(oldHour, newHour);
            int newSeconds = newTimePlayed.getSecond() + timePlayed.getSeconds();
            int newMinutes = newTimePlayed.getMinute() + timePlayed.getMinutes();
            int newHours = newTimePlayed.getHour() + timePlayed.getHours();
            TimePlayed totalTimePlayed = new TimePlayed(newHours, newMinutes, newSeconds);

            gameInformation = new GameInformation(bag, false, player2, player1, board, order, totalTimePlayed);


        }while((player1.getHolder().getHoldSize() != 0 && player2.getHolder().getHoldSize() != 0) && !end);
        if(end){
            return gameInformation;
        }
        else{
            System.out.println(ANSI_CYAN + "Score del jugador " + player1.getAlias() +  ": " + ANSI_RESET + player1.getScore());
            System.out.println(ANSI_CYAN + "Score del jugador " + player2.getAlias() +  ": " + ANSI_RESET + player2.getScore());
            Thread.sleep(2000);
            if(player1.getScore() > player2.getScore()){
                player1.setWinner(true);
                System.out.println(ANSI_YELLOW + "¡¡¡¡¡El ganador fue: " + player1.getAlias() + "!!!!!");
                System.out.println("¡¡¡¡¡¡¡" + ANSI_RED + "F" + ANSI_YELLOW + "E" + ANSI_CYAN + "L" + ANSI_PURPLE + "I" + ANSI_WHITE + "C" + ANSI_YELLOW + "I" + ANSI_RED + "D" + ANSI_GREEN +"A" + ANSI_CYAN + "D" + ANSI_YELLOW + "E" + ANSI_GREEN + "S" + ANSI_RESET + "!!!!!!!!!");
            }
            else if(player1.getScore() < player2.getScore()){
                player2.setWinner(true);
                System.out.println(ANSI_YELLOW + "¡¡¡¡¡El ganador fue: " + player2.getAlias() + "!!!!!");
            }
            else{
                player1.setWinner(true);
                player2.setWinner(true);
                System.out.println(ANSI_YELLOW + "Empate. ");
            }
            return gameInformation;
        }
    }

    private static User logIn(int index){
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


    //MAIN

    public static void main(String[] args) throws InterruptedException, IOException {

        //Variables de lectura de archivos
        boolean gameAlreadyCreated = false;
        boolean userFound = false;
        LinkedList<User> usersLinkedList = new LinkedList<User>();
        LinkedList<GameInformation> gamesInProgress = new LinkedList<GameInformation>();
        LinkedList<GameInformation> finishedGames = new LinkedList<GameInformation>();
        int overwriteGameOption;
        Scanner readOverwriteGameOption = new Scanner(System.in);
        GameInformation foundedGame = null;

        //Checker del web scraping
        WordChecker checker = new WordChecker();
        boolean existance;

        //Variables del juego
        Board board;
        TimePlayed generatedTimePlayed;
        Bag bag;
        Order order = new Order();
        User user1 = null, user2 = null, newUser = null;
        int option;
        GameInformation gamePlayed;
        Player player1;
        Player player2;

        //Json Reading
        usersLinkedList = JsonUserHandler.readFromJson();
        gamesInProgress = JsonGamesHandler.readFromJson();
        finishedGames = JsonFinishedGamesHandler.readFromJson();

        if(gamesInProgress == null) {
            gamesInProgress = new LinkedList<GameInformation>();
        }


        //MENU de ingreso
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



        System.out.println(ANSI_GREEN+"\nBienvenidos " + user1.getAlias() + " y " + user2.getAlias() + ANSI_RESET);
        Thread.sleep(2000);

        try {
            if (gamesInProgress == null) {
                throw new NoGamesInProgressException();
            }
            //Buscar partida en base al jugador 1
            for (int i = 0; i < gamesInProgress.size(); i++) {
                if (user1.equalsName(gamesInProgress.get(i).getPlayer1Alias())) {

                    //Si se encuentra al jugador 1 se busca al jugador 2
                    for (int j = 0; j < gamesInProgress.size(); j++) {
                        if (user2.equalsName(gamesInProgress.get(i).getPlayer2Alias())) {
                            System.out.println("Partida encontrada");
                            gameAlreadyCreated = true;
                            foundedGame = gamesInProgress.get(i);
                            break;
                        }
                    }

                    //Si no se encuentra se busca el user pero como jugador 2
                } else if (user1.equalsName(gamesInProgress.get(i).getPlayer2Alias())) {

                    //Si se encuentra se busca al jugador 2
                    for (int j = 0; j < gamesInProgress.size(); j++) {
                        if (user2.equalsName(gamesInProgress.get(i).getPlayer1Alias())) {
                            System.out.println("Partida encontrada");
                            gameAlreadyCreated = true;
                            foundedGame = gamesInProgress.get(i);
                            break;
                        }
                    }

                    //No se consiguio ninguno
                } else {
                    System.out.println("No se ha encontrado ninguna partida");
                }

            }
        }catch(NoGamesInProgressException e){
            System.out.println(e.getMessage());
            Thread.sleep(1500);
        }

        do{
            option = menu();
            switch(option){
                case 1:
                    //Revisa si existe una partida
                    if(gameAlreadyCreated) {
                        System.out.println("Ya existe una partida creada para estos jugadores, quiere sobreescribir la partida?");
                        System.out.println("1. Si");
                        System.out.println("2. No");

                        overwriteGameOption = readOverwriteGameOption.nextInt();
                        switch (overwriteGameOption) {
                            case 1:
                                System.out.println("Partida sobreescrita");
                                gamesInProgress.remove(foundedGame);
                                if(gamesInProgress.isEmpty()) {
                                    JsonGamesHandler.clearJsonFile();
                                } else {
                                    JsonGamesHandler.writeToJson(gamesInProgress);
                                }
                                break;
                            case 2:
                                continue;
                            default:
                                System.out.println(ANSI_RED + "El número ingresado no posee acción alguna\n");
                                Thread.sleep(500);
                                break;
                        }


                    }
                    //New game
                    System.out.println("Iniciando nuevo juego: ");
                    Thread.sleep(1000);
                    bag = new Bag();
                    player1 = new Player(user1.getAlias(), 0, bag.fillNewHolder(initialLettersNeeded), false);
                    player2 = new Player(user2.getAlias(),0, bag.fillNewHolder(initialLettersNeeded), false);
                    board = new Board();
                    generatedTimePlayed = new TimePlayed(0,0,0);


                    //Establecer orden de jugadores
                    order.setNewOrder(player1,player2);
                    gamePlayed = playGame(player1, player2, order, board, bag, generatedTimePlayed);
                    if(!gamePlayed.isGameFinished()) {
                        gamesInProgress.add(gamePlayed);
                        JsonGamesHandler.writeToJson(gamesInProgress);
                    } else {
                        finishedGames.add(gamePlayed);
                        JsonFinishedGamesHandler.writeToJson(gamesInProgress);
                    }
                    break;
                case 2:
                    //Revisa si existe una partida
                    if(!gameAlreadyCreated) {
                        System.out.println("No existen partidas creadas con estos jugadores, inicie un nuevo juego.");
                        continue;
                    }
                    player1 = foundedGame.getGamePlayer1();
                    player2 = foundedGame.getGamePlayer2();
                    order = foundedGame.getGameOrder();
                    board = foundedGame.getGameBoard();
                    bag = foundedGame.getGameBag();
                    generatedTimePlayed = foundedGame.getGameTimePlayed();
                    gamesInProgress.remove(foundedGame);

                    gamePlayed = playGame(player1, player2, order, board, bag, generatedTimePlayed);
                    if(!gamePlayed.isGameFinished()) {
                        gamesInProgress.add(gamePlayed);
                        JsonGamesHandler.writeToJson(gamesInProgress);
                    } else {
                        finishedGames.add(gamePlayed);
                        JsonFinishedGamesHandler.writeToJson(gamesInProgress);
                    }

                    break;
                case 3:
                    if(!gameAlreadyCreated) {
                        System.out.println("No existen partidas creadas con estos jugadores, por lo tanto no hay estadisticas de ellos, inicie un nuevo juego.");
                        continue;
                    }
                    System.out.println("Jugador 1");
                    System.out.println("Nombre: "+foundedGame.getPlayer1Alias());
                    System.out.println("Score: "+foundedGame.getGamePlayer1().getScore());
                    System.out.println("");
                    System.out.println("Jugador 2");
                    System.out.println("Nombre: "+foundedGame.getPlayer2Alias());
                    System.out.println("Score: "+foundedGame.getGamePlayer2().getScore());
                    System.out.println("");
                    System.out.println("Tiempo total jugado: ");
                    foundedGame.getGameTimePlayed().printTime();
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