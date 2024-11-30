package ucab.edu.objects;
import static ucab.edu.objects.Color.*;

public class NormalSquare extends Square{

    public NormalSquare(int y, int x) {
        super(y, x);
        this.color = ANSI_RESET;
    }
}
