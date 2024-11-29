package ucab.edu.objects;

import static ucab.edu.objects.Color.*;

public abstract class Square{
    protected String letter;
    protected String color;
    protected int x;
    protected int y;

    public Square(int x, int y){
        this.letter = "  ";
        this.color = ANSI_YELLOW_BACKGROUND;
        this.x = x;
        this.y = y;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
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
