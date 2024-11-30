package ucab.edu.objects;

import static ucab.edu.objects.Color.*;

public class EmptyArrayException extends RuntimeException {
    public EmptyArrayException() {
        super(ANSI_RED +"ERROR. Lista con la que se desea interactuar vacía." + ANSI_RESET);
    }
}
