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
                    hold.remove(letter);
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
        this.hold.addAll(bag.changeHolder(size));
        show();
    }

    public void exchangeAll(Bag bag){
        bag.fillBag(hold);
        int size = getHoldSize();
        this.hold = bag.changeHolder(size);
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
            lettersHold.show();
        }catch(EmptyArrayException e){
            System.out.println(e.getMessage());
        }
    }

    public void restartSelection(LettersHold lettersHold){
        try {
            if(lettersHold.getHoldSize() == 0){
                throw new EmptyArrayException();
            }
            hold.addAll(lettersHold.getHold());
            show();
        }catch(EmptyArrayException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void show() {
        System.out.print(ANSI_YELLOW + "\nFichas actuales del jugador:" + ANSI_RESET);
        for(Letter letter:hold){
            System.out.printf("%3s",letter.getLetter());
            System.out.printf("|%s",letter.getValue());
        }
        System.out.println();
    }
}
