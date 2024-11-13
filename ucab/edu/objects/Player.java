package ucab.edu.objects;

import java.util.ArrayList;

public class Player extends User{
    private String alias;
    private int score;
    private int lettersAvailable;
    private ArrayList <Letter> holder = new ArrayList<>();
    private boolean winner;

    public Player(String alias, String email, int score, int lettersAvailable, ArrayList<Letter> holder, boolean winner) {
        super(alias,email);
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

    public void seeHolder(){
        for(Letter letter:holder){
            System.out.printf("%3s",letter.getLetter());
            System.out.printf("|%s",letter.getValue());
        }
        System.out.println();
    }

}
