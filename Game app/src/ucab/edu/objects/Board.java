package ucab.edu.objects;

import static ucab.edu.objects.Color.*;

public class Board implements Show{
    private final int horizontalLength = 15;
    private final int verticalLength = 15;
    private final Square[][] table = new Square[horizontalLength][verticalLength];

    public int getHorizontalLength() {
        return horizontalLength;
    }

    public int getVerticalLength() {
        return verticalLength;
    }


    public Board(){
        int centralHorizontal = horizontalLength/2;
        int centralVertical = verticalLength/2;

        for(int i = 0; i<horizontalLength; i++){
            for(int j = 0; j<verticalLength; j++){
                if(i==centralHorizontal && j==centralVertical){
                    table[i][j] = new CentralSquare(i,j);
                }
                else {
                    table[i][j] = new NormalSquare(i, j);
                }
            }
        }

        for(int i = 0; i<horizontalLength; i++){
            if(i == 0 || i == horizontalLength - 1) {
                table[i][i] = new TripleWordSquare(i, i);
            }
            else if(i == 5 || i == 9){
                table[i][i] = new TripleLetterSquare(i,i);
            }
            else if(i == 6 || i == 8){
                table[i][i] = new DoubleLetterSquare(i,i);
            }
            else if (i != centralHorizontal){
                table[i][i] = new DoubleWordSquare(i,i);
            }
        }

        int i = 0;
        for(int j = verticalLength-1; j >=0; j--){
            if(j == 0 || j == horizontalLength - 1) {
                table[i][j] = new TripleWordSquare(i,j);
            }
            else if(j == 5 || j == 9){
                table[i][j] = new TripleLetterSquare(i,j);
            }
            else if(j == 6 || j == 8){
                table[i][j] = new DoubleLetterSquare(i,j);
            }
            else if (j != centralVertical){
                table[i][j] = new DoubleWordSquare(i,j);
            }
            i++;
        }
        table[0][3] = new DoubleLetterSquare(0,3);
        table[3][0] = new DoubleLetterSquare(3,0);
        table[3][14] = new DoubleLetterSquare(3,14);
        table[14][3] = new DoubleLetterSquare(14,3);
        table[0][11] = new DoubleLetterSquare(0,11);
        table[11][0] = new DoubleLetterSquare(11,0);
        table[11][14] = new DoubleLetterSquare(11,14);
        table[14][11] = new DoubleLetterSquare(14,11);
        table[1][5] = new TripleLetterSquare(1,5);
        table[5][1] = new TripleLetterSquare(5,1);
        table[1][9] = new TripleLetterSquare(1,9);
        table[9][1] = new TripleLetterSquare(9,1);
        table[5][13] = new TripleLetterSquare(5,13);
        table[13][5] = new TripleLetterSquare(13,5);
        table[9][13] = new TripleLetterSquare(9,13);
        table[13][9] = new TripleLetterSquare(13,9);
        table[6][12] = new DoubleLetterSquare(6,12);
        table[12][6] = new DoubleLetterSquare(12,6);
        table[6][2] = new DoubleLetterSquare(6,2);
        table[2][6] = new DoubleLetterSquare(2,6);
        table[8][2] = new DoubleLetterSquare(8,2);
        table[2][8] = new DoubleLetterSquare(2,8);
        table[8][12] = new DoubleLetterSquare(8,12);
        table[12][8] = new DoubleLetterSquare(12,8);
        table[centralHorizontal][3] = new DoubleLetterSquare(centralHorizontal,3);
        table[3][centralVertical] = new DoubleLetterSquare(3,centralVertical);
        table[centralHorizontal][11] = new DoubleLetterSquare(centralHorizontal,11);
        table[11][centralVertical] = new DoubleLetterSquare(11,centralVertical);
        table[0][centralVertical] = new TripleWordSquare(0, centralVertical);
        table[horizontalLength-1][centralVertical] = new TripleWordSquare(horizontalLength-1,centralVertical);
        table[centralHorizontal][0] = new TripleWordSquare(centralHorizontal, 0);
        table[centralHorizontal][verticalLength-1] = new TripleWordSquare(centralHorizontal, 0);
    }

    public boolean verifyCoordinateX(int coordinate) {
        try {
            if (coordinate > 0 && coordinate <= horizontalLength) {
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

    @Override
    public void show() {
        System.out.printf("%3s"," ");
        for(int i = 1; i<= horizontalLength; i++){
            System.out.printf("%3s",i);
            System.out.printf("%s"," ");
        }
        System.out.println();
        for (int i = 0; i < verticalLength; i++){
            System.out.printf("%2s",(char)(65+i));
            System.out.printf("%s"," ");
            for (int j = 0; j < horizontalLength; j++){
                String place = table[i][j].color + table[i][j].letter + ANSI_RESET;
                System.out.printf("|%2s",place);
                System.out.printf("%s"," ");
            }
            System.out.printf("|%n");
        }
    }
}
