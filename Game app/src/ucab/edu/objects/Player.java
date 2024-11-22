package ucab.edu.objects;

import java.util.ArrayList;
import java.util.Objects;

public class Player {
    private String alias;
    private int score;
    private int lettersAvailable;
    private Holder holder;
    private boolean winner;

    public Player(String alias, int score, int lettersAvailable, Holder holder, boolean winner) {
        this.alias = alias;
        this.score = score;
        this.lettersAvailable = lettersAvailable;
        this.holder = holder;
        this.winner = winner;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
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

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public Holder getHolder() {
        return holder;
    }

    public void setHolder(Holder holder) {
        this.holder = holder;
    }

}
