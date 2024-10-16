import java.util.Scanner;

public class RunMenu {
    Scanner read = new Scanner(System.in);
    private int counter = -1;

    public RunMenu() {
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void runMenu(){
        while (counter != 0){
            System.out.println("\t\t MENU SCRABBLE: \n");
            System.out.println("0. Exit");
            System.out.println("1. New Game");
            System.out.println("2. Continue Last Game");
            System.out.println("Settings");
            System.out.println("Player Score");

            System.out.println("\n insert your option: ");
            counter = read.nextInt();

            switch (counter){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }


        }
    }
}
