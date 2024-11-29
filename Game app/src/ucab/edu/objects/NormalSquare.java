package ucab.edu.objects;
import static ucab.edu.objects.Color.*;

public class NormalSquare extends Square{

    public NormalSquare(int x, int y) {
        super(x, y);
        this.color = ANSI_RESET;
    }
}
