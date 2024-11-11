package ucab.edu.objects;

public class Box {
    private Letter letter;
    private int x;
    private int y;

    public Box(Letter letter, int x, int y) {
        this.letter = letter;
        this.x = x;
        this.y = y;
    }

    public Letter getLetter() {
        return letter;
    }

    public void setLetter(Letter letter) {
        this.letter = letter;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
