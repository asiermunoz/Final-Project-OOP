package ucab.edu.objects;
import static ucab.edu.objects.Color.*;

public class OutOfDimensionsException extends RuntimeException {
    public OutOfDimensionsException() {
        super(ANSI_RED+"ERROR. Número ingresado fuera de las dimensiones disponibles en el tablero."+ANSI_RESET);
    }
}
