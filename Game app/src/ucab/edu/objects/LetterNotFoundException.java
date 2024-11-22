package ucab.edu.objects;

import static ucab.edu.objects.Color.*;

public class LetterNotFoundException extends RuntimeException {
    public LetterNotFoundException() {
        super(ANSI_RED +"ERROR. Ficha ingresada no encontrada en el atril del jugador" + ANSI_RESET);
    }
}
