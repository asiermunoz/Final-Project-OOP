package ucab.edu.objects;

import java.time.LocalTime;
import java.util.Objects;
import java.util.Scanner;

import static ucab.edu.objects.Color.*;
import static ucab.edu.objects.Color.ANSI_YELLOW;

public class PlayGame {
    private static final int initialLettersNeeded = 7;
    private static final Scanner read = new Scanner(System.in);

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


    public static GameInformation playGame(Player player1, Player player2, Order order, Board board, Bag bag, TimePlayed timePlayed) throws InterruptedException {
        int opc,opc2,x;
        char y;
        Letter letter;
        boolean out;
        boolean end = false;
        boolean finished = false;
        int pastShifts = 0;
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
                                while(!read.hasNextInt()) {
                                    read.next();
                                    if(!read.hasNextInt()){
                                        System.out.println(ANSI_RED + "ERROR. Aporte ingresado no corresponde con lo pedido." + ANSI_RESET);
                                        System.out.println("Ingrese el número de la coordenada x en donde va a iniciar su palabra.");
                                    }
                                }
                                x=read.nextInt();
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
                                while(!read.hasNextByte()) {
                                    read.next();
                                    if(!read.hasNextByte()){
                                        System.out.println(ANSI_RED + "ERROR. Aporte ingresado no corresponde con lo pedido." + ANSI_RESET);
                                        System.out.println("Indique el número de la forma en la que desea ingresar la palabra: ");
                                        System.out.println("1. Vertical ( | )");
                                        System.out.println("2. Horizontal ( - )");
                                    }
                                }
                                opc2=read.nextByte();
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
                                        turn.setPass(false);
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
                                        turn.setPass(false);
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
                            turn.setPass(true);
                            out = true;
                            break;

                        default:
                            System.out.println(ANSI_RED + "ERROR. número ingresado fuera de los parámetros indicados." + ANSI_RESET);
                            break;
                    }
                }

                if(order.getFirstPlayer().isPass() && order.getLastPlayer().isPass()){
                    System.out.println("\nSe han pasado 2 turnos seguidos.");
                    System.out.println("¿Desea terminar la partida?");
                    System.out.println("1. Sí");
                    System.out.println("2. No");
                    while(!read.hasNextInt()) {
                        read.next();
                        if(!read.hasNextInt()){
                            System.out.println(ANSI_RED + "ERROR. Aporte ingresado no corresponde con lo pedido." + ANSI_RESET);
                            System.out.println("Ingrese el número de la coordenada x en donde va a iniciar su palabra.");
                        }
                    }
                    opc = read.nextInt();
                    if(opc == 1){
                        finished = true;
                        end = true;
                        break;
                    }
                }
                if(turn.getHolder().getHoldSize() == 0){
                    finished = true;
                    end = true;
                    break;
                }
            }

            LocalTime newHour = LocalTime.now();
            GameTimer timer = new GameTimer(0,0,0,0,0,0);
            LocalTime newTimePlayed = timer.calculateDiference(oldHour, newHour);
            int newSeconds = newTimePlayed.getSecond() + timePlayed.getSeconds();
            int newMinutes = newTimePlayed.getMinute() + timePlayed.getMinutes();
            int newHours = newTimePlayed.getHour() + timePlayed.getHours();
            TimePlayed totalTimePlayed = new TimePlayed(newHours, newMinutes, newSeconds);

            gameInformation = new GameInformation(bag, false, player2, player1, board, order, totalTimePlayed);


        }while(!end);
        player1 = order.getFirstPlayer();
        player2 = order.getLastPlayer();
        if(!finished){
            System.out.println("Guardando partida...");
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
                System.out.println(ANSI_YELLOW + "¡¡¡¡¡El ganador fue " + player2.getAlias() + "!!!!!");
            }
            else{
                player1.setWinner(true);
                player2.setWinner(true);
                System.out.println(ANSI_YELLOW + "Empate. ");
            }
            System.out.println();
        }
        Thread.sleep(2000);
        return gameInformation;
    }
}
