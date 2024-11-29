package ucab.edu.objects;
import java.util.Scanner;

public class Game {

    public boolean endGame(Order order, Player turn){
        Scanner select = new Scanner(System.in);
        int opc;
        for(Player next: order.getPlayers()){
            if(next != turn){
                System.out.println("El jugador " + next.getAlias() + " desea terminar igualmente la partida?");
                System.out.println("1. Sí");
                System.out.println("0. No");
                opc = select.nextByte();
                if(opc == 1){
                    return true;
                }
            }
        }
        return false;
    }
}
