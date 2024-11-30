package ucab.edu.objects;

import java.util.Objects;

public class Horizontal extends Rotation{

    public Horizontal(Board board) {
        super(board);
    }

    @Override
    public boolean read() {
        return false;
    }


    @Override
    public boolean write(Word word, int x, int y) {
        Board copyBoard = new Board();
        boolean collision = false;
        boolean scrabble = false;
        int doubleWordBoost = 0;
        int tripleWordBoost = 0;
        score = 0;
        try {
            if(word.getHoldSize() == 0){
                throw new EmptyArrayException();
            }
            if(word.getHoldSize() == 7){
                scrabble = true;
            }

            //Recorrer
            for (int j = x; j < board.getLength(); j++) {
                if(word.getHoldSize() != 0) {
                    if (Objects.equals(board.getTable()[y][j].letter.getLetter(), "  ")) {
                        if (word.hold.getFirst().getLetter().length() == 1) {
                            putSimpleCharacter(y, j, word, copyBoard);
                        } else {
                            putDoubleCharacter(y, j, word, copyBoard);
                        }
                        score = score + copyBoard.getTable()[y][j].letter.getValue();
                        if (board.getTable()[y][j] instanceof CentralSquare) {
                            collision = true;
                            doubleWordBoost++;
                        }
                        if(board.getTable()[y][j] instanceof DoubleWordSquare){
                            doubleWordBoost++;
                        }
                        if(board.getTable()[y][j] instanceof TripleWordSquare){
                            tripleWordBoost++;
                        }
                    }
                    else{
                        collision = true;
                    }
                }
            }
            if(!collision){
                throw new LackOfCollisionException();
            }
            if(word.getHoldSize() != 0){
                throw new WordSizeException();
            }
            if(scrabble){
                scrabbleBonus();
            }
            if(doubleWordBoost != 0){
                doubleWordBonus(doubleWordBoost);
            }
            if(tripleWordBoost != 0){
                tripleWordBonus(tripleWordBoost);
            }
            transferBoard(copyBoard);
            return true;
        }catch (WordSizeException | EmptyArrayException | LackOfCollisionException e){
            System.out.println(e.getMessage());
            return false;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
