import ucab.edu.objects.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import static ucab.edu.objects.Color.*;
import static ucab.edu.objects.User.*;

public class Main {

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
        int opc,opc2,x;
        int initialLettersNeeded = 7;
        char y;
        Game game = new Game();
        Board board = new Board();
        Bag bag = new Bag();
        Letter letter;
        Order order = new Order();
        boolean out;
        boolean end = false;
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
        Thread.sleep(2000);
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
                    order.setNewOrder(player1,player2);
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
                                        end = game.endGame(order, turn);
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
                                            System.out.println(ANSI_YELLOW + "(Letras ya presentes en el tablero se tomaran en cuenta automáticamente)");
                                            System.out.println(ANSI_GREEN + "En caso que desee realizar otra acción ingrese uno de los siguientes números:");
                                            System.out.println("1. Terminar y colocar palabra.");
                                            System.out.println("2. Borrar letra anterior.");
                                            System.out.println("3. Reiniciar palabra.");
                                            System.out.println("0. Volver al menú de opciones." + ANSI_RESET);
                                            token = read.next();
                                            if(Objects.equals(token,"0")){
                                                turn.getHolder().getHold().addAll(word.getHold());
                                                System.out.println("Saliendo al menú de opciones.");
                                            }
                                            else if(Objects.equals(token,"1")){
                                                Word toPut = new Word();
                                                toPut.getHold().addAll(word.getHold());

                                                //Función para colocar palabra en la tabla;
                                                if (writer.write(toPut, x-1, y - 65)) {
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
                                                    turn.getHolder().getHold().addAll(word.getHold());
                                                    word = new Word();
                                                    turn.getHolder().show();
                                                    word.show();
                                                }
                                            }
                                            else if(Objects.equals(token,"2")){
                                                turn.getHolder().backtrack(word);
                                            }
                                            else if(Objects.equals(token,"3")){
                                                turn.getHolder().restartSelection(word);
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
                                            System.out.println(ANSI_GREEN + "En caso que desee realizar otra acción ingrese uno de los siguientes números:");
                                            System.out.println("1. Terminar y realizar cambio de fichas.");
                                            System.out.println("2. Retroceder selección.");
                                            System.out.println("3. Reiniciar selección.");
                                            System.out.println("4. Intercambiar todas las fichas.");
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
                                        System.out.println("ERROR. número ingresado fuera de los parámetros indicados.");
                                        break;
                                }
                            }
                            if(turn.getHolder().getHoldSize() == 0 || end){break;}
                        }

                    }while((player1.getHolder().getHoldSize() != 0 && player2.getHolder().getHoldSize() != 0) && !end);

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