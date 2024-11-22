package ucab.edu.objects;

import java.util.ArrayList;
import java.util.Arrays;

public class FirstPlayer1 implements FirstOne{

    @Override
    public Order setFirst(Player player1, Player player2) {
        ArrayList<Player> players = new ArrayList<>(Arrays.asList(player1,player2));
        System.out.println("Inicia el jugador: " + player1.getAlias());
        return new Order(players);
    }
}
