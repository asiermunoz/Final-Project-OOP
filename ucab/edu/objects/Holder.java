package ucab.edu.objects;

import java.util.ArrayList;
import java.util.Objects;

public class Holder {
    private ArrayList<Letter> tokensHold = new ArrayList<>();

    public Holder(ArrayList<Letter> tokensHold) {
        this.tokensHold = tokensHold;
    }

    public ArrayList<Letter> getTokensHold() {
        return tokensHold;
    }

    public void setTokensHold(ArrayList<Letter> tokensHold) {
        this.tokensHold = tokensHold;
    }

    public void seeHolder(){
        for(Letter letter:tokensHold){
            System.out.printf("%3s",letter.getLetter());
            System.out.printf("|%s",letter.getValue());
        }
        System.out.println();
    }

    public Letter searchLetter(String changeLetter){
        changeLetter = changeLetter.toUpperCase();
        for (Letter letter:tokensHold){
            if(Objects.equals(letter.getLetter(), changeLetter)){
                return letter;
            }
        }
        return null;
    }
}
