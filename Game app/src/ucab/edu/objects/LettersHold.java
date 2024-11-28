package ucab.edu.objects;

import java.util.ArrayList;

public abstract class LettersHold implements Show{
    private ArrayList<Letter> hold = new ArrayList<>();

    public ArrayList<Letter> getHold() {
        return hold;
    }

    public void setHold(ArrayList<Letter> hold) {
        this.hold = hold;
    }

    public int getHoldSize(){
        return hold.size();
    }

    public void addLetter(Letter letter){
        this.hold.addLast(letter);
    }

    public void removeLetter(){
        this.hold.removeLast();
    }
}
