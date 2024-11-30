package ucab.edu.objects;

import java.util.ArrayList;

public class LettersList {
    private final ArrayList<LettersAmount> list = new ArrayList<>();

    public LettersList() {
        list.addLast(new LettersAmount("A",1,12));
        list.addLast(new LettersAmount("B",4,2));
        list.addLast(new LettersAmount("C",3,4));
        list.addLast(new LettersAmount("CH",8,1));
        list.addLast(new LettersAmount("D",3,5));
        list.addLast(new LettersAmount("E",1,12));
        list.addLast(new LettersAmount("F",5,1));
        list.addLast(new LettersAmount("G",3,2));
        list.addLast(new LettersAmount("H",5,2));
        list.addLast(new LettersAmount("I",1,6));
        list.addLast(new LettersAmount("J",10,1));
        list.addLast(new LettersAmount("L",2,4));
        list.addLast(new LettersAmount("LL",8,1));
        list.addLast(new LettersAmount("M",3,2));
        list.addLast(new LettersAmount("N",2,5));
        list.addLast(new LettersAmount("Ñ",10,1));
        list.addLast(new LettersAmount("O",1,9));
        list.addLast(new LettersAmount("P",4,2));
        list.addLast(new LettersAmount("Q",8,1));
        list.addLast(new LettersAmount("R",2,5));
        list.addLast(new LettersAmount("RR",8,1));
        list.addLast(new LettersAmount("S",1,6));
        list.addLast(new LettersAmount("T",2,4));
        list.addLast(new LettersAmount("U",1,5));
        list.addLast(new LettersAmount("V",4,1));
        list.addLast(new LettersAmount("X",10,1));
        list.addLast(new LettersAmount("Y",5,1));
        list.addLast(new LettersAmount("Z",10,1));
        list.addLast(new LettersAmount("☻",0,2));
    }

    public ArrayList<LettersAmount> getList() {
        return list;
    }
}
