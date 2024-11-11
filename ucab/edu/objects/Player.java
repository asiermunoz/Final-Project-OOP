package ucab.edu.objects;

import java.util.ArrayList;

public class Player extends User {
    private int score;
    private int lettersAvailable;
    private ArrayList <Letter> holder = new ArrayList<>();
    private boolean winner;

    public Player(String email, String alias, int score, int lettersAvailable, Bag bag, boolean winner) {
        super(email,alias);
        this.score = score;
        this.lettersAvailable = lettersAvailable;
        putInHolder(lettersAvailable,bag);
        this.winner = winner;
    }

    private void putInHolder(int lettersAvailable, Bag bag){
        for(int i = 0; i < lettersAvailable; i++){
            this.holder.add(bag.takeRandomLetter());
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLettersAvailable() {
        return lettersAvailable;
    }

    public void setLettersAvailable(int lettersAvailable) {
        this.lettersAvailable = lettersAvailable;
    }

    public ArrayList<Letter> getHolder() {
        return holder;
    }

    public void setHolder(ArrayList<Letter> holder) {
        this.holder = holder;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }


}
