package ucab.edu.objects;

import static ucab.edu.objects.Color.*;

public class Bonus {

    public int scrabbleBonus(int score) throws InterruptedException {
        System.out.println("¡¡¡¡¡¡¡" + ANSI_RED + "S" + ANSI_YELLOW + "C" + ANSI_CYAN + "R" + ANSI_PURPLE + "A" + ANSI_WHITE + "B" + ANSI_YELLOW + "B" + ANSI_RED + "L" + ANSI_GREEN +"E " + ANSI_RESET +" conseguido!!!!!!!!! +50 puntos");
        Thread.sleep(2000);
        int scrabble = 50;
        score = score + scrabble;
        return score;
    }

    public int doubleWordBonus(int score, int doubleWordBoost) throws InterruptedException {
        System.out.println(ANSI_CYAN + "Se ha colocado una pieza en una casilla doble palabra.");
        System.out.println("Se multiplicará la palabra formada x2" + ANSI_RESET);
        Thread.sleep(1500);
        for(int i = 0; i<doubleWordBoost; i++){
            score = score * 2;
        }
        return score;
    }

    public int tripleWordBonus(int score, int tripleWordBoost) throws InterruptedException {
        System.out.println(ANSI_CYAN + "Se ha colocado una pieza en una casilla triple palabra.");
        System.out.println("Se multiplicará la palabra formada x3" + ANSI_RESET);
        Thread.sleep(1500);
        for(int i = 0; i<tripleWordBoost; i++){
            score = score * 3;
        }
        return score;
    }

    public int doubleLetterBonus(int letterToMultiply) throws InterruptedException {
        System.out.println(ANSI_CYAN + "Se ha colocado una pieza en una casilla doble letra.");
        System.out.println("Se multiplicará la letra colocada x2" + ANSI_RESET);
        Thread.sleep(1500);
        return letterToMultiply;
    }

    public int tripleLetterBonus(int letterToMultiply) throws InterruptedException {
        System.out.println(ANSI_CYAN + "Se ha colocado una pieza en una casilla doble letra.");
        System.out.println("Se multiplicará la letra colocada x3" + ANSI_RESET);
        Thread.sleep(1500);
        return letterToMultiply*2;
    }
}
