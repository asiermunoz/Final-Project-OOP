package ucab.edu.objects;

import java.util.ArrayList;

public class Player extends User{
    private String alias;
    private int score;
    private int lettersAvailable;
    private Holder holder;
    private boolean winner;

    public Player(String email, String alias, int score, int lettersAvailable, Holder holder, boolean winner) {
        super(email, alias);
        this.score = score;
        this.lettersAvailable = lettersAvailable;
        this.holder = holder;
        this.winner = winner;
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

    public Holder getHolder() { return holder; }

    public void setHolder(Holder holder) { this.holder = holder; }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }


}
