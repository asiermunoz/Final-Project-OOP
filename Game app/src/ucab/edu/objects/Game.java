package ucab.edu.objects;
import java.util.Scanner;

public class Game {
    public boolean endGame(Order order, Player turn){
        Scanner select = new Scanner(System.in);
        int opc;
        for(Player next: order.getPlayers()){
            if(next != turn){
                do {
                    System.out.println("El jugador " + next.getAlias() + " desea terminar igualmente la partida?");
                    System.out.println("1. Sí");
                    System.out.println("0. No");
                    opc = select.nextByte();
                    if (opc == 1) {
                        System.out.println("Saliendo del juego.");
                        return true;
                    } else if (opc == 0) {
                        System.out.println("Volviendo al juego.");
                        return false;
                    } else {
                        System.out.println("ERROR. número ingresado fuera de los parámetros indicados.");
                    }
                }while(opc != 1 && opc != 0);
            }
        }
        return false;
    }
}
