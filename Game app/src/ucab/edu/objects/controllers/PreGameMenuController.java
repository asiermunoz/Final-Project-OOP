package ucab.edu.objects.controllers;

import ucab.edu.objects.GameInformation;
import ucab.edu.objects.NoGamesInProgressException;
import ucab.edu.objects.jsonHandlers.JsonGamesHandler;
import ucab.edu.objects.models.PreGameMenuModel;
import ucab.edu.objects.users.User;
import ucab.edu.objects.views.PreGameMenuView;

import java.util.LinkedList;

import static ucab.edu.objects.Color.*;

public class PreGameMenuController {
    PreGameMenuModel model = new PreGameMenuModel();
    PreGameMenuView view = new PreGameMenuView();
    int option;

    public void start(LinkedList<GameInformation> gamesInProgress, User user1, User user2, LinkedList<GameInformation> finishedGames) throws InterruptedException {
        boolean gameAlreadyCreated;
        GameInformation foundedGame = null;
        final int initialLettersNeeded = 7;
        GameInformation gamePlayed;

        try {
            foundedGame = model.searchGame(user1, user2, gamesInProgress);
            gameAlreadyCreated = true;
        } catch (NoGamesInProgressException e){
            System.out.println(e.getMessage());
            Thread.sleep(1500);
            gameAlreadyCreated = false;
        }

        do {
            option = view.getOption();
            switch(option) {
                case 1:
                    if(gameAlreadyCreated) {
                        switch (view.overwriteGameOption(gameAlreadyCreated)){
                            case 1:
                                System.out.println("Partida sobreescrita");
                                gamesInProgress.remove(foundedGame);
                                if(gamesInProgress.isEmpty()) {
                                    JsonGamesHandler.clearJsonFile();
                                } else {
                                    JsonGamesHandler.writeToJson(gamesInProgress);
                                }
                                break;
                            case 2:
                                continue;
                            default:
                                System.out.println(ANSI_RED + "El número ingresado no posee acción alguna\n");
                                Thread.sleep(500);
                                break;
                        }
                    }

                    System.out.println("Iniciando nuevo juego: ");
                    Thread.sleep(1000);
                    gamePlayed = model.playNewGame(user1, user2, initialLettersNeeded);
                    model.finishGame(gamePlayed, finishedGames, gamesInProgress);
                    break;

                case 2:
                    if(!gameAlreadyCreated) {
                        System.out.println("No existen partidas creadas con estos jugadores, inicie un nuevo juego.");
                        continue;
                    }
                    gamesInProgress.remove(foundedGame);
                    gamePlayed = model.playGameInProgress(foundedGame, gamesInProgress);
                    model.finishGame(gamePlayed, finishedGames, gamesInProgress);

                case 3:
                    if(!gameAlreadyCreated) {
                        System.out.println("No existen partidas creadas con estos jugadores, por lo tanto no hay estadisticas de ellos, inicie un nuevo juego.");
                        continue;
                    }
                    view.showInformation(foundedGame);
                case 0:
                    System.out.println(ANSI_BLUE + "Saliendo del juego...");
                    Thread.sleep(800); //Pausa la corrida de código por los milisegundos establecidos.
                    System.out.println(ANSI_YELLOW + "Vuelva pronto!");
                    break;
                default:
                    System.out.println(ANSI_RED + "El número ingresado no posee acción alguna\n");
                    Thread.sleep(500); //Pausa la corrida de código por los milisegundos establecidos.
                    break;
            }


        } while (option != 0);
    }


}
