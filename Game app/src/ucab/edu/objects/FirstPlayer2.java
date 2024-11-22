package ucab.edu.objects;

import java.util.ArrayList;
import java.util.Arrays;

public class FirstPlayer2 implements FirstOne{

    @Override
    public Order setFirst(Player player1, Player player2) {
        ArrayList<Player> players = new ArrayList<>(Arrays.asList(player2,player1));
        System.out.println("Inicia el jugador: " + player2.getAlias());
        return new Order(players);
    }
}
