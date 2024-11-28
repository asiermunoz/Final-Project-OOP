package ucab.edu.objects;

import java.util.ArrayList;
import java.util.Objects;

import static ucab.edu.objects.Color.*;

public class Holder extends LettersHold{

    public Holder(ArrayList<Letter> hold) {
        setHold(hold);
    }

    public Letter takeLetter(String token) {
        token = token.toUpperCase();
        try {
            for (Letter letter : getHold()) {
                if (Objects.equals(letter.getLetter(), token)) {
                    getHold().remove(letter);
                    return letter;
                }
            }
            throw new LetterNotFoundException();
        } catch (LetterNotFoundException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void finishExchange(Bag bag, int size){
        this.getHold().addAll(bag.changeHolder(size));
        show();
    }

    public void backtrack(LettersHold lettersHold) {
        try {
            if(lettersHold.getHoldSize() == 0){
                throw new EmptyArrayException();
            }
            addLetter(lettersHold.getHold().getLast());
            lettersHold.removeLetter();
            show();
        }catch(EmptyArrayException e){
            System.out.println(e.getMessage());
        }
    }

    public void restartSelection(LettersHold lettersHold){
        try {
            if(lettersHold.getHoldSize() == 0){
                throw new EmptyArrayException();
            }
            getHold().addAll(lettersHold.getHold());
            show();
        }catch(EmptyArrayException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void show() {
        System.out.print(ANSI_YELLOW + "\nFichas actuales del jugador:" + ANSI_RESET);
        for(Letter letter:getHold()){
            System.out.printf("%3s",letter.getLetter());
            System.out.printf("|%s",letter.getValue());
        }
        System.out.println();
    }
}
