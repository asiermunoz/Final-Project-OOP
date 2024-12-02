package ucab.edu.objects;

import java.util.ArrayList;
import java.util.Objects;

public class Horizontal extends Rotation{

    public Horizontal(Board board) {
        super(board);
    }

    @Override
    public boolean initialRead(Board copy, int coordinate) {
        return false;
    }


    @Override
    public boolean write(Word word, int x, int y,  int initialLettersNeeded) {
        Board copyBoard = new Board();
        transferBoard(copyBoard);
        boolean collision = false;
        boolean scrabble = false;
        int doubleLetterBoost = 0;
        int tripleLetterBoost = 0;
        int doubleWordBoost = 0;
        int tripleWordBoost = 0;
        score = 0;
        try {
            if(word.getHoldSize() == 0){
                throw new EmptyArrayException();
            }
            if(word.getHoldSize() == initialLettersNeeded){
                scrabble = true;
            }

            //Recorrer
            for (int j = x; j < board.getLength(); j++) {
                if(word.getHoldSize() != 0) {
                    if (Objects.equals(board.getTable()[y][j].letter.getLetter(), "  ")) {
                        copyBoard.getTable()[y][j].letter = word.hold.removeFirst();
                        copyBoard.getTable()[y][j].marked = true;
                        if (board.getTable()[y][j] instanceof CentralSquare) {
                            collision = true;
                            doubleWordBoost++;
                        }
                        if(board.getTable()[y][j] instanceof  DoubleLetterSquare){
                            doubleLetterBoost = doubleLetterBoost + copyBoard.getTable()[y][j].letter.getValue();
                        }
                        if(board.getTable()[y][j] instanceof  TripleLetterSquare){
                            tripleLetterBoost = tripleLetterBoost + copyBoard.getTable()[y][j].letter.getValue();
                        }
                        if(board.getTable()[y][j] instanceof DoubleWordSquare){
                            doubleWordBoost++;
                        }
                        if(board.getTable()[y][j] instanceof TripleWordSquare){
                            tripleWordBoost++;
                        }
                        if (!collision) {
                            collision = reviseCollision(copyBoard, y, j);
                        }
                    }
                    else{
                        collision = true;
                    }
                }
            }
            if(word.getHoldSize() != 0){
                throw new WordSizeException();
            }
            if(!readBoard(copyBoard)){
                throw new InexistentWord();
            }
            if(!collision){
                throw new LackOfCollisionException();
            }
            if(scrabble){
                score = bonus.scrabbleBonus(score);
            }
            if(doubleLetterBoost != 0){
                score = score + bonus.doubleLetterBonus(doubleLetterBoost);
            }
            if(tripleLetterBoost != 0){
                score = score + bonus.tripleLetterBonus(doubleLetterBoost);
            }
            if(doubleWordBoost != 0){
                score = bonus.doubleWordBonus(score, doubleWordBoost);
            }
            if(tripleWordBoost != 0){
                score = bonus.tripleWordBonus(score, tripleWordBoost);
            }
            this.board = copyBoard;
            this.board.setMarkersFalse();
            return true;
        }catch (WordSizeException | EmptyArrayException | LackOfCollisionException e){
            System.out.println(e.getMessage());
            return false;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}