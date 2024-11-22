package ucab.edu.objects;

import java.util.ArrayList;
import java.util.Objects;

import static ucab.edu.game.Color.*;

public class Holder implements Show {
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

    public Letter searchLetter(String token) {
        token = token.toUpperCase();
        try {
            for (Letter letter : tokensHold) {
                if (Objects.equals(letter.getLetter(), token)) {
                    return letter;
                }
            }
            throw new LetterNotFoundException();
        } catch (LetterNotFoundException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void backtrackExchange(Exchange exchange){
        this.tokensHold.addLast(exchange.getChangeLetters().getLast());
        exchange.getChangeLetters().removeLast();
    }

    public void finishExchange(Bag bag, int size){
        this.tokensHold.addAll(bag.changeHolder(size));
    }

    @Override
    public void showLetters() {
        System.out.print(ANSI_YELLOW + "\nFichas actuales del jugador:" + ANSI_RESET);
        for(Letter letter:tokensHold){
            System.out.printf("%3s",letter.getLetter());
            System.out.printf("|%s",letter.getValue());
        }
        System.out.println();
    }
}
