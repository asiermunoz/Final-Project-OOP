package ucab.edu.objects;

import java.util.ArrayList;

import static ucab.edu.objects.Color.*;

public class Exchange extends LettersHold{

    @Override
    public void show() {
        System.out.print(ANSI_CYAN + "Fichas que se van a cambiar:" + ANSI_RESET);
        for(Letter letter:getHold()){
            System.out.printf("%3s",letter.getLetter());
            System.out.printf("|%s",letter.getValue());
        }
        System.out.println();
    }
}
