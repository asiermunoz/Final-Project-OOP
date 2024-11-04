import ucab.edu.ve.Information;
import ucab.edu.ve.Player;
import ucab.edu.ve.Statistics;
import java.util.LinkedList;
import java.util.Scanner;

public class SecondAppMenu {
    public static int menu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("----TEST MENU----");
        System.out.println("1. Generate player");
        System.out.println("2. Show list of players");
        System.out.println("3. Show stats of a player");
        System.out.println("4. Leave");
        return scanner.nextInt();
    }
    public static void main(String[] args) {
        LinkedList<Player> listOfPlayers = new LinkedList<Player>();
        int mainMenuOption;
        do {
            mainMenuOption = menu();
            switch(mainMenuOption){
                case 1:
                    Statistics initialStats = new Statistics(0, new Information("",false,0,0,0));
                    Player newPlayer = new Player("","", initialStats);
                    Scanner newPlayerScanner = new Scanner(System.in);

                    System.out.println("Enter your name: ");
                    newPlayer.setName(newPlayerScanner.nextLine());

                    System.out.println("Enter your alias: ");
                    newPlayer.setPlayerAlias(newPlayerScanner.nextLine());

                    do {
                        System.out.println("Enter your mail: ");
                        newPlayer.setEmail(newPlayerScanner.nextLine());

                        if(!newPlayer.validateEmail()) {
                            System.out.println("Invalid mail, try again.");
                        }
                    } while(!newPlayer.validateEmail());
                    System.out.println("Valid mail");

                    System.out.println("Your player name is: " + newPlayer.getName());
                    System.out.println("Your player alias is: " + newPlayer.getPlayerAlias());
                    System.out.println("Your player mail is: " + newPlayer.getEmail());
                    listOfPlayers.add(newPlayer);
                    break;
                case 2:
                    if(listOfPlayers.isEmpty()) {
                        System.out.println("No players created.");
                    } else {
                        for (int i = 0; i < listOfPlayers.size(); i++) {
                            System.out.println(i + ". " + listOfPlayers.get(i).getName() + "(" + listOfPlayers.get(i).getPlayerAlias() + ") " + listOfPlayers.get(i).getEmail());
                        };
                    }
                    break;
                case 3:
                    Scanner searchPlayerScanner = new Scanner(System.in);
                    System.out.println("Enter the player's number: ");
                    int playerPosition = searchPlayerScanner.nextInt();

                    if (playerPosition < 0 || playerPosition > listOfPlayers.size()) {
                        System.out.println("No player has been found in that position");
                    } else {
                        listOfPlayers.get(playerPosition).printPlayerStats();
                    }
                    break;
                case 4:
                    System.out.println("Leaving menu...");
                    break;
                default:
                    System.out.println("Error");
                    break;
            }
        } while(mainMenuOption != 4);
    }
}
