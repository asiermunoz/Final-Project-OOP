package ucab.edu.objects;

public class GameInformation {
    private Bag gameBag;
    private Board gameBoard;
    private Player gamePlayer1;
    private Player gamePlayer2;
    private boolean gameFinished;
    private Order gamerOrder;

    public GameInformation(Bag gameBag, boolean gameFinished, Player gamePlayer2, Player gamePlayer1, Board gameBoard, Order gamerOrder) {
        this.gameBag = gameBag;
        this.gameFinished = gameFinished;
        this.gamePlayer2 = gamePlayer2;
        this.gamePlayer1 = gamePlayer1;
        this.gameBoard = gameBoard;
        this.gamerOrder = gamerOrder;
    }

    public Bag getGameBag() {
        return gameBag;
    }

    public void setGameBag(Bag gameBag) {
        this.gameBag = gameBag;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Player getGamePlayer1() {
        return gamePlayer1;
    }

    public void setGamePlayer1(Player gamePlayer1) {
        this.gamePlayer1 = gamePlayer1;
    }

    public Player getGamePlayer2() {
        return gamePlayer2;
    }

    public void setGamePlayer2(Player gamePlayer2) {
        this.gamePlayer2 = gamePlayer2;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public Order getGamerOrder() { return gamerOrder; }

    public void setGamerOrder(Order gamerOrder) { this.gamerOrder = gamerOrder; }

    public String getPlayer1Alias() {
        return gamePlayer1.getAlias();
    }

    public String getPlayer2Alias() {
        return gamePlayer2.getAlias();
    }
}
