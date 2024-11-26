package ucab.edu.objects;

import java.util.ArrayList;
import java.util.Random;

public class Bag {
    private int total;
    private ArrayList<Letter> letters = new ArrayList<>();

    //Rellenar bolsa inicial
    public Bag(){
        for(LettersAmount bagList:new LettersList().getList()){
            for(int i = 0; i < bagList.getAmount(); i++){
                this.letters.add(bagList);
                total++;
            }
        }
    }

    public int getTotal() {
        return total;
    }

    public Holder fillNewHolder(int lettersNeeded){
        ArrayList <Letter> letters = new ArrayList<>();
        for(int i = 0; i < lettersNeeded; i++){
            if(total!=0){
                letters.add(takeRandomLetter());
                this.total--;
            }
            else{
                System.out.println("Bolsa vacía.");
                break;
            }
        }
        return new Holder(letters);
    }

    public ArrayList <Letter> changeHolder(int lettersNeeded){
        ArrayList <Letter> letters = new ArrayList<>();
        for(int i = 0; i < lettersNeeded; i++){
            letters.add(takeRandomLetter());
        }
        return letters;
    }

    public void fillBag(ArrayList<Letter> letters) {
        this.letters.addAll(letters);
    }

    //Sacar letra al azar
    private Letter takeRandomLetter(){
        int i = (int)Math.floor(Math.random()*this.total);
        Letter letter = letters.get(i);
        letters.remove(i);
        return letter;
    }



}
