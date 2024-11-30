package ucab.edu.objects;

public class LettersAmount extends Letter{
    private int amount;
    public LettersAmount(String letter, int value, int amount) {
        super(letter, value);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
