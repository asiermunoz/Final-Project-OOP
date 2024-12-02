package ucab.edu.objects;
import static ucab.edu.objects.Color.*;

public class EmptyBagException extends RuntimeException {
  public EmptyBagException() {
    super(ANSI_RED + "La bolsa ha quedado vacía." + ANSI_RESET);
  }
}