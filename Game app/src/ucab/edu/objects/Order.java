package ucab.edu.objects;

import java.util.ArrayList;

public class Order {
    ArrayList<Player> players = new ArrayList<Player>();

    public Order(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

}
