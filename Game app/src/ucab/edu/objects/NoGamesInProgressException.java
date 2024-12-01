package ucab.edu.objects;

public class NoGamesInProgressException extends RuntimeException {
    public NoGamesInProgressException() {
        super("No se han encontrado partidas jugadas previas.");
    }
}
