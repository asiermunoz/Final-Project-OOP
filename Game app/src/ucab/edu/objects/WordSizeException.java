package ucab.edu.objects;
import static ucab.edu.objects.Color.*;

public class WordSizeException extends RuntimeException {
    public WordSizeException() {
        super(ANSI_RED +"ERROR. Palabra ingresada fuera de las dimensiones del tablero."+ANSI_RESET);
    }
}
