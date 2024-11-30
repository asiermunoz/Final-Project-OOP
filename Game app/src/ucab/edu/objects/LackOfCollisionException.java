package ucab.edu.objects;
import static ucab.edu.objects.Color.*;

public class LackOfCollisionException extends RuntimeException {
  public LackOfCollisionException() {
    super(ANSI_RED + "ERROR. Palabra ingresada no posee colisiones con otras palabras ni posee colisión con el centro del tablero." + ANSI_RESET);
  }
}
