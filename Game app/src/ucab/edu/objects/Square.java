package ucab.edu.objects;

import static ucab.edu.objects.Color.*;

public class Square{
    protected Letter letter;
    protected String color;
    protected int x;
    protected int y;

    public Square(int y, int x){
        this.letter = new Letter ("  ", 0);
        this.color = ANSI_YELLOW_BACKGROUND;
        this.x = x;
        this.y = y;
    }

    public String getLetter() {
        return letter.getLetter();
    }

    public void setLetter(Letter letter) {
        this.letter = letter;
    }

    public int getValue() {
        return letter.getValue();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
