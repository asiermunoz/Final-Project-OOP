package ucab.edu.objects;

import static ucab.edu.objects.Color.*;

public class Board implements Show{
    private final int length = 15;
    private Square[][] table = new Square[length][length];

    public Board(){
        int central = length/2;
        for(int i = 0; i<length; i++){
            for(int j = 0; j<length; j++){
                if(i==central && j==central){
                    table[i][j] = new CentralSquare(i,j);
                }
                else {
                    table[i][j] = new NormalSquare(i, j);
                }
            }
        }

        for(int i = 0; i < length; i++){
            if(i == 0 || i == length - 1) {
                table[i][i] = new TripleWordSquare(i, i);
            }
            else if(i == 5 || i == 9){
                table[i][i] = new TripleLetterSquare(i,i);
            }
            else if(i == 6 || i == 8){
                table[i][i] = new DoubleLetterSquare(i,i);
            }
            else if (i != central){
                table[i][i] = new DoubleWordSquare(i,i);
            }
        }

        int i = 0;
        for(int j = length-1; j >=0; j--){
            if(j == 0 || j == length - 1) {
                table[i][j] = new TripleWordSquare(i,j);
            }
            else if(j == 5 || j == 9){
                table[i][j] = new TripleLetterSquare(i,j);
            }
            else if(j == 6 || j == 8){
                table[i][j] = new DoubleLetterSquare(i,j);
            }
            else if (j != central){
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
        table[central][3] = new DoubleLetterSquare(central,3);
        table[3][central] = new DoubleLetterSquare(3,central);
        table[central][11] = new DoubleLetterSquare(central,11);
        table[11][central] = new DoubleLetterSquare(11,central);
        table[0][central] = new TripleWordSquare(0, central);
        table[length-1][central] = new TripleWordSquare(length-1,central);
        table[central][0] = new TripleWordSquare(central, 0);
        table[central][length-1] = new TripleWordSquare(central, 0);
    }

    public int getLength() {
        return length;
    }

    public Square[][] getTable() {
        return table;
    }


    public boolean verifyCoordinateX(int coordinate) {
        try {
            if (coordinate > 0 && coordinate <= length) {
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
            if ((int)(coordinate) >= 65 && (int)(coordinate) <= (65 + length)) {
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
        for(int i = 1; i<= length; i++){
            System.out.printf("%s%3s%s", " ", i, " ");
        }
        System.out.println();
        for (int i = 0; i < length; i++){
            System.out.printf("%2s",(char)(65+i));
            System.out.printf("%s"," ");
            for (int j = 0; j < length; j++){
                String place = table[i][j].color + table[i][j].letter.getLetter() + ANSI_RESET;
                System.out.printf("|%s%2s%s", " ", place," ");
            }
            System.out.printf("|%n");
        }
    }
}
