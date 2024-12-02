package ucab.edu.objects;

import java.util.ArrayList;
import java.util.Objects;

import static ucab.edu.objects.Color.*;

public class Holder extends LettersHold{

    public Holder(ArrayList<Letter> hold) {
        setHold(hold);
    }

    public void takeEverythingBack(LettersHold lettersHold){
        try {
            if (lettersHold.getHoldSize() == 0) {
                throw new EmptyArrayException();
            }
            for (Letter letter : lettersHold.getHold()) {
                if (letter.isJoker()) {
                    addLetter(new Letter("☻", Letter.jokerValue));
                } else {
                    addLetter(letter);
                }
            }
        }catch(EmptyArrayException _){
        }
    }

    public Letter takeLetter(String token) {
        token = token.toUpperCase();
        boolean joker = false;
        int jokerMark = 0;
        try {
            for (Letter letter : getHold()) {
                if (Objects.equals(letter.getLetter(), token)) {
                    hold.remove(letter);
                    return letter;
                }
                else if(Objects.equals(letter.getLetter(), "☻")){
                    joker = true;
                }
                if(!joker){
                    jokerMark++;
                }
            }
            if(joker){
                for(Letter letter: new LettersList().getList()){
                    if(Objects.equals(letter.getLetter(), token)){
                        hold.remove(jokerMark);
                        letter.setJoker(true);
                        letter.setValue(Letter.jokerValue);
                        return letter;
                    }
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
            if(lettersHold.getHold().getLast().isJoker()){
                addLetter(new Letter("☻",Letter.jokerValue));
            }
            else {
                addLetter(lettersHold.getHold().getLast());
            }
            lettersHold.removeLetter();
            show();
            lettersHold.show();
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