package ucab.edu.objects;

public class Board {
    private final int horizontalLength = 15;
    private final int verticalLength = 15;
    private final String[][] table = new String[horizontalLength][verticalLength];

    public int getHorizontalLength() {
        return horizontalLength;
    }

    public int getVerticalLength() {
        return verticalLength;
    }

    public void printTable(){
        System.out.printf("%3s"," ");
        for(int i = 0; i< horizontalLength; i++){
            System.out.printf("%3s",i);
            System.out.printf("%s"," ");
        }
        System.out.println();
        for (int i = 0; i < verticalLength; i++){
            System.out.printf("%2s",(char)(65+i));
            System.out.printf("%s"," ");
            for (int j = 0; j < 15; j++){
                System.out.printf("|%2s",table[i][j]);
                System.out.printf("%s"," ");
            }
            System.out.printf("|%n");
        }
    }

    public void emptyTable() {
        for (int i = 0; i < horizontalLength; i++){
            for (int j = 0; j < verticalLength; j++){
                table[i][j]=" ";
            }
        }
    }

    public boolean verifyCoordinateX(int coordinate) {
        try {
            if (coordinate >= 0 && coordinate <= horizontalLength) {
                return true;
            }
            throw new OutOfDimensionsException();
        }catch(OutOfDimensionsException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean verifyCoordinateY(char coordinate) {
        try {
            if ((int)(coordinate) >= 65 && (int)(coordinate) <= (65 + verticalLength)) {
                return true;
            }
            throw new OutOfDimensionsException();
        }catch(OutOfDimensionsException e){
            System.out.println(e.getMessage());
            return false;
        }
    }



}
