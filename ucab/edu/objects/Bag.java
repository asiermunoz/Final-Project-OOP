package ucab.edu.objects;

import java.util.ArrayList;
import java.util.Random;

public class Bag {
    private int total;
    private ArrayList<Letter> letters = new ArrayList<>();


    //Rellenar bolsa inicial
    public Bag(){
        total = 100;
        Letter letter;
        letter = new Letter("A",1);
        for(int i = 0; i<=11; i++) { letters.add(letter); }

        letter = new Letter("B",4);
        for(int i = 0; i<=1; i++) { letters.add(letter); }

        letter = new Letter("C",3);
        for(int i = 0; i<=3; i++) { letters.add(letter); }

        letter = new Letter("CH",8);
        letters.add(letter);

        letter = new Letter("D",3);
        for(int i = 0; i<=4; i++) { letters.add(letter); }

        letter = new Letter("E",1);
        for(int i = 0; i<=11; i++) { letters.add(letter); }

        letter = new Letter("F",5);
        letters.add(letter);

        letter = new Letter("G",3);
        for(int i = 0; i<=1; i++) { letters.add(letter); }

        letter = new Letter("H",5);
        for(int i = 0; i<=1; i++) { letters.add(letter); }

        letter = new Letter("I",1);
        for(int i = 0; i<=5; i++) { letters.add(letter); }

        letter = new Letter("J",10);
        letters.add(letter);

        letter = new Letter("L",2);
        for(int i = 0; i<=3; i++) { letters.add(letter); }

        letter = new Letter("LL",8);
        letters.add(letter);

        letter = new Letter("M",3);
        for(int i = 0; i<=1; i++) { letters.add(letter); }

        letter = new Letter("N",2);
        for(int i = 0; i<=4; i++) { letters.add(letter); }

        letter = new Letter("Ñ",10);
        letters.add(letter);

        letter = new Letter("O",1);
        for(int i = 0; i<=8; i++) { letters.add(letter); }

        letter = new Letter("P",4);
        for(int i = 0; i<=1; i++) { letters.add(letter); }

        letter = new Letter("Q",8);
        letters.add(letter);

        letter = new Letter("R",2);
        for(int i = 0; i<=4; i++) { letters.add(letter); }

        letter = new Letter("RR",8);
        letters.add(letter);

        letter = new Letter("S",1);
        for(int i = 0; i<=5; i++) { letters.add(letter); }

        letter = new Letter("T",2);
        for(int i = 0; i<=3; i++) { letters.add(letter); }

        letter = new Letter("U",1);
        for(int i = 0; i<=4; i++) { letters.add(letter); }

        letter = new Letter("V",4);
        letters.add(letter);

        letter = new Letter("X",10);
        letters.add(letter);

        letter = new Letter("Y",5);
        letters.add(letter);

        letter = new Letter("Z",10);
        letters.add(letter);

        letter = new Letter("☻",0);
        for(int i = 0; i<=1; i++) { letters.add(letter); }
    }


    public ArrayList <Letter> fillNewHolder(int lettersNeeded){
        ArrayList <Letter> letters = new ArrayList<>();
        for(int i = 0; i < lettersNeeded; i++){
            if(total!=0){
                letters.add(takeRandomLetter());
            }
            else{
                System.out.println("Bolsa vacía.");
                break;
            }
        }
        return letters;
    }

    public int getTotal() {
        return total;
    }


    //Sacar letra al azar
    private Letter takeRandomLetter(){
        int i = (int)Math.floor(Math.random()*this.total);
        Letter letter = letters.get(i);
        letters.remove(i);
        this.total--;
        return letter;
    }
}
