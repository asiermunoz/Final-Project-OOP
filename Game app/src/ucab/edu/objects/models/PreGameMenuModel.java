package ucab.edu.objects.models;

import ucab.edu.objects.*;
import ucab.edu.objects.jsonHandlers.JsonFinishedGamesHandler;
import ucab.edu.objects.jsonHandlers.JsonGamesHandler;
import ucab.edu.objects.users.User;

import java.util.LinkedList;

public class PreGameMenuModel {

    public GameInformation searchGame(User user1, User user2, LinkedList<GameInformation> gamesInProgress) {
        GameInformation foundedGame;

        if (gamesInProgress == null) {
            throw new NoGamesInProgressException();
        }
        //Buscar partida en base al jugador 1
        for (int i = 0; i < gamesInProgress.size(); i++) {
            if (user1.equalsName(gamesInProgress.get(i).getPlayer1Alias())) {

                //Si se encuentra al jugador 1 se busca al jugador 2
                for (int j = 0; j < gamesInProgress.size(); j++) {
                    if (user2.equalsName(gamesInProgress.get(i).getPlayer2Alias())) {
                        System.out.println("Partida encontrada");
                        foundedGame = gamesInProgress.get(i);
                        return foundedGame;
                    }
                }

                //Si no se encuentra se busca el user pero como jugador 2
            } else if (user1.equalsName(gamesInProgress.get(i).getPlayer2Alias())) {

                //Si se encuentra se busca al jugador 2
                for (int j = 0; j < gamesInProgress.size(); j++) {
                    if (user2.equalsName(gamesInProgress.get(i).getPlayer1Alias())) {
                        System.out.println("Partida encontrada");
                        foundedGame = gamesInProgress.get(i);
                        return foundedGame;
                    }
                }

                //No se consiguio ninguno
            } else {
                System.out.println("No se ha encontrado ninguna partida");
                return null;
            }
        }
        return null;
    }


    public GameInformation playNewGame(User user1, User user2, int initialLettersNeeded) throws InterruptedException {
        Bag bag = new Bag();
        Player player1 = new Player(user1.getAlias(), 0, bag.fillNewHolder(initialLettersNeeded), false);
        Player player2 = new Player(user2.getAlias(),0, bag.fillNewHolder(initialLettersNeeded), false);
        Board board = new Board();
        TimePlayed generatedTimePlayed = new TimePlayed(0,0,0);
        Order order = new Order();


        //Establecer orden de jugadores
        order.setNewOrder(player1,player2);
        return PlayGame.playGame(order.getFirstPlayer(), order.getLastPlayer(), order, board, bag, generatedTimePlayed);
    }

    public GameInformation playGameInProgress(GameInformation foundedGame, LinkedList<GameInformation> gamesInProgress) throws InterruptedException {
        assert foundedGame != null;
        Player player1 = foundedGame.getGamePlayer1();
        Player player2 = foundedGame.getGamePlayer2();
        Order order = foundedGame.getGameOrder();
        Board board = foundedGame.getGameBoard();
        Bag bag = foundedGame.getGameBag();
        TimePlayed generatedTimePlayed = foundedGame.getGameTimePlayed();

        return PlayGame.playGame(order.getFirstPlayer(), order.getLastPlayer(), order, board, bag, generatedTimePlayed);
    }


    public void finishGame(GameInformation gamePlayed, LinkedList<GameInformation> finishedGames, LinkedList<GameInformation> gamesInProgress) {
        if(!gamePlayed.isGameFinished()) {
            gamesInProgress.add(gamePlayed);
            JsonGamesHandler.writeToJson(gamesInProgress);
        } else {
            finishedGames.add(gamePlayed);
            JsonFinishedGamesHandler.writeToJson(gamesInProgress);
        }
    }


}
