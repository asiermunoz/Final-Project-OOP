package ucab.edu.objects;

public class Board {
    private String[][] table = new String[15][15];

    public Board(String[][] table) {
        this.table = table;
    }

    public void printTable(){
        for (int i = 0; i < 15; i++){
            System.out.printf("%2s",i+1);
            System.out.printf("%s"," ");
            for (int j = 0; j < 15; j++){
                System.out.printf("|%2s",table[i][j]);
                System.out.printf("%s"," ");
            }
            System.out.printf("|%n");
        }
    }

    public void emptyTable() {
        for (int i = 0; i < table.length; i++){
            for (int j = 0; j < table.length; j++){
                table[i][j]=" ";
            }
        }
    }

    public String[][] getTable() {
        return table;
    }

    public void setTable(String[][] table) {
        this.table = table;
    }
}
