package ucab.edu.objects;

public class Letter {
    private String letter;
    private int value;
    private boolean joker;
    public static final int jokerValue = 0;

    public Letter(String letter, int value) {
        this.letter = letter;
        this.value = value;
        this.joker = false;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isJoker() {
        return joker;
    }

    public void setJoker(boolean b) {
    }
}
