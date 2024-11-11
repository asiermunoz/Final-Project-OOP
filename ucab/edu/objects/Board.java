package ucab.edu.objects;

public class Board {
    private Box[][] table = new Box[15][15];

    public Board() {
        Letter letter = new Letter(" ", 0);
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 15; j++){
                this.table[i][j] = new Box(null,i,j);
            }
        }
    }

    public void printTable(){
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 15; j++){
                System.out.printf("|%5s",table[i][j].getLetter());
            }
            System.out.printf("|%n");
        }
    }

    public Box[][] getTable() {
        return table;
    }

    public void setTable(Box[][] table) {
        this.table = table;
    }
}
