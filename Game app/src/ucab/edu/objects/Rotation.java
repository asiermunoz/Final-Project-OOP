package ucab.edu.objects;

import static ucab.edu.objects.Color.*;

public abstract class Rotation {
    protected Board board;
    protected int score;
    protected int scrabbleValue = 50;

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

    public void scrabbleBonus() throws InterruptedException {
        System.out.println("¡¡¡¡¡¡¡" + ANSI_RED + "S" + ANSI_YELLOW + "C" + ANSI_CYAN + "R" + ANSI_PURPLE + "A" + ANSI_WHITE + "B" + ANSI_YELLOW + "B" + ANSI_RED + "L" + ANSI_GREEN +"E " + ANSI_RESET +" conseguido!!!!!!!!! +50 puntos");
        Thread.sleep(2000);
        score = score + scrabbleValue;
    }

    public void doubleWordBonus(int doubleWordBoost) throws InterruptedException {
        System.out.println(ANSI_CYAN + "Se ha colocado una pieza en una casilla doble palabra.");
        System.out.println("Se multiplicará la palabra formada x2");
        Thread.sleep(1500);
        for(int i = 0; i<doubleWordBoost; i++){
            score = score * 2;
        }
    }

    public void tripleWordBonus(int tripleWordBoost) throws InterruptedException {
        System.out.println(ANSI_CYAN + "Se ha colocado una pieza en una casilla triple palabra.");
        System.out.println("Se multiplicará la palabra formada x3");
        Thread.sleep(1500);
        for(int i = 0; i<tripleWordBoost; i++){
            score = score * 3;
        }
    }

    public void putSimpleCharacter(int y, int x, Word word){
        board.getTable()[y][x].letter.setLetter(word.hold.getFirst().getLetter() + " ");
        board.getTable()[y][x].letter.setValue(word.hold.removeFirst().getValue());
    }
    public void putDoubleCharacter(int y, int x, Word word){
        board.getTable()[y][x].letter = word.hold.removeFirst();
    }
    public abstract boolean read();
    public abstract boolean write(Word word, int x, int y);
}
