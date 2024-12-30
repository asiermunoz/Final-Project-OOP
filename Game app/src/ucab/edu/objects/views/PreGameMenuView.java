package ucab.edu.objects.views;

import ucab.edu.objects.GameInformation;

import java.util.Scanner;

import static ucab.edu.objects.Color.*;

public class PreGameMenuView {
    private Scanner read = new Scanner(System.in);

    public int getOption(){
        System.out.println(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + "---------SCRABBLE-UCAB---------" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "Ingrese el número de la acción que desee realizar:" + ANSI_RESET);
        System.out.println("1. Jugar nueva Partida.");
        System.out.println("2. Continuar partida anterior.");
        System.out.println("3. Mostrar estadística de jugadores.");
        System.out.println("0. Salir.");
        return read.nextInt();
    }

    public int overwriteGameOption(boolean gameAlreadyCreated) {
        System.out.println("Ya existe una partida creada para estos jugadores, quiere sobreescribir la partida?");
        System.out.println("1. Si");
        System.out.println("2. No");
        return read.nextInt();
    }

    public void showInformation(GameInformation foundedGame) {
        System.out.println("Referencia:");
        System.out.println("Nombre, Score, gano?");
        System.out.println("Jugador 1");
        System.out.println(foundedGame.getPlayer1Alias()+", "+foundedGame.getGamePlayer1().getScore()+", "+foundedGame.getGamePlayer1().isWinner());
        System.out.println("Jugador 2");
        System.out.println(foundedGame.getPlayer2Alias()+", "+foundedGame.getGamePlayer2().getScore()+", "+foundedGame.getGamePlayer2().isWinner());
        System.out.println("Tiempo total jugado: ");
        foundedGame.getGameTimePlayed().printTime();
    }
}
