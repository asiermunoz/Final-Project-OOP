package ucab.edu.objects;
import static ucab.edu.objects.Color.*;

public class InexistentWord extends RuntimeException {
    public InexistentWord() {
        super(ANSI_RED + "La palabra ingresada no existe. Intentelo de nuevo." + ANSI_RESET);
    }
}
