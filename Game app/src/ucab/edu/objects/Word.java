package ucab.edu.objects;
import static ucab.edu.objects.Color.*;

public class Word extends LettersHold{

    @Override
    public void show() {
        System.out.print(ANSI_YELLOW + "\nLetras ingresadas: " + ANSI_RESET);
        for(Letter letter:hold){
            System.out.printf("%3s",letter.getLetter());
            System.out.printf("|%s",letter.getValue());
        }
        System.out.println();
    }
}
