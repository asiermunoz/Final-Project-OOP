package ucab.edu.objects;

import java.util.ArrayList;
import java.util.Objects;

import static ucab.edu.objects.Color.*;

public abstract class Rotation {
    protected Board board;
    protected int score;
    protected Bonus bonus = new Bonus();

    public Rotation(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void transferBoard(Board copy) {
        for (int i = 0; i < board.getLength(); i++){
            for(int j = 0; j < board.getLength(); j++){
                if (!Objects.equals(board.getTable()[i][j].letter.getLetter(), "  ")) {
                    copy.getTable()[i][j].letter = board.getTable()[i][j].letter;
                }
            }
        }
    }

    public boolean reviseCollision(Board copy, int y, int x){
        for (int i = 0; i < board.getLength(); i++){
            for(int j = 0; j < board.getLength(); j++){
                if(!Objects.equals(board.getTable()[i][j].letter.getLetter(), "  ") && !copy.getTable()[i][j].marked){
                    if((i == y+1 && j == x) || (i==y-1 && j == x) || (i == y && j == x+1) || (i == y && j == x-1)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean readHorizontal(Board copy){
        boolean truth = true;
        boolean enter = false;
        int copyScore = 0;
        ArrayList<Letter> letters = new ArrayList<>();
        for (int i = 0; i<copy.getLength(); i++){
            for(int j = 0; j<copy.getLength(); j++){
                if (!Objects.equals(copy.getTable()[i][j].letter.getLetter(), "  ")) {
                    letters.add(copy.getTable()[i][j].letter);
                    copyScore = copyScore + copy.getTable()[i][j].getValue();
                    if(copy.getTable()[i][j].marked){
                        enter = true;
                    }
                }
                else{
                    if((letters.size() > 1 && enter)){
                        if(readWord(letters)){
                            this.score = this.score + copyScore;
                            truth = true;
                            letters = new ArrayList<>();
                            copyScore = 0;
                        }
                        else{
                            truth = false;
                        }
                    }
                    else{
                        copyScore = 0;
                        letters = new ArrayList<>();
                        enter = false;
                    }
                }
            }
        }
        return truth;
    }

    public boolean readVertical(Board copy){
        boolean truth = true;
        boolean enter = false;
        int copyScore = 0;
        ArrayList<Letter> letters = new ArrayList<>();
        for (int i = 0; i<copy.getLength(); i++){
            for(int j = 0; j<copy.getLength(); j++){
                if (!Objects.equals(copy.getTable()[j][i].letter.getLetter(), "  ")) {
                    letters.add(copy.getTable()[j][i].letter);
                    copyScore = copyScore + copy.getTable()[j][i].getValue();
                    if(copy.getTable()[j][i].marked){
                        enter = true;
                    }
                }
                else{
                    if((letters.size() > 1 && enter)){
                        if(readWord(letters)){
                            this.score = this.score + copyScore;
                            truth = true;
                            letters = new ArrayList<>();
                        }
                        else{
                            truth = false;
                        }
                    }
                    else{
                        copyScore = 0;
                        letters = new ArrayList<>();
                        enter = false;
                    }
                }
            }
        }
        return truth;
    }

    public boolean readBoard(Board copy) {
        boolean truth;
        truth = readHorizontal(copy);
        if(truth){
            truth = readVertical(copy);
        }
        return truth;
    }

    public boolean readWord(ArrayList<Letter> letters){
        StringBuilder word = new StringBuilder();
        for (Letter letter : letters) {
            word.append(letter.getLetter().toLowerCase());
        }
        return new WordChecker().checkWord(String.valueOf(word));
    }

    public abstract boolean initialRead(Board copy, int coordinate);
    public abstract boolean write(Word word, int x, int y,  int initialLettersNeeded);
}