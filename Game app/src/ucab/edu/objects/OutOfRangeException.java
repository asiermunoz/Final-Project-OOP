package ucab.edu.objects;
import static ucab.edu.objects.Color.*;

public class OutOfRangeException extends RuntimeException {
    public OutOfRangeException() {
        super(ANSI_RED+"ERROR. Número ingresado fuera de los parámetros indicados."+ANSI_RESET);
    }
}
