package ucab.edu.objects;

import static ucab.edu.objects.Color.*;

public class EmptyArrayException extends RuntimeException {
    public EmptyArrayException() {
        super(ANSI_RED +"ERROR. Lista que se desea borrar vacía." + ANSI_RESET);
    }
}
