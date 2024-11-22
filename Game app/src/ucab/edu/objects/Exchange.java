package ucab.edu.objects;

import java.util.ArrayList;

import static ucab.edu.game.Color.*;

public class Exchange implements ucab.edu.game.Show {
    private ArrayList<Letter> changeLetters = new ArrayList<>();

    public ArrayList<Letter> getChangeLetters() {
        return changeLetters;
    }

    public void setChangeLetters(ArrayList<Letter> changeLetters) {
        this.changeLetters = changeLetters;
    }

    public int getChangeLettersSize() {
        return changeLetters.size();
    }

    @Override
    public void showLetters() {
        System.out.print(ANSI_CYAN + "Fichas que se van a cambiar:" + ANSI_RESET);
        for(Letter letter:changeLetters){
            System.out.printf("%3s",letter.getLetter());
            System.out.printf("|%s",letter.getValue());
        }
        System.out.println();
    }
}
