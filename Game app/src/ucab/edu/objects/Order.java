package ucab.edu.objects;

import java.util.ArrayList;
import java.util.Random;

public class Order {
    ArrayList<Player> players = new ArrayList<Player>();

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setNewOrder(Player player1, Player player2){
        FirstOne first;
        Random randomChoice = new Random();
        if(randomChoice.nextBoolean()){
            first = new FirstPlayer1();
        }
        else{
            first = new FirstPlayer2();
        }
        this.players = first.setFirst(player1,player2);
    }
}
