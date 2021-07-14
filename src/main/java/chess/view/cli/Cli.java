package chess.view.cli;

import chess.Attributes;
import chess.controller.CliController;
import chess.model.game.Game;
import chess.model.player.Player;
import chess.util.Observer;
import chess.view.View;

import java.util.Locale;
import java.util.Scanner;

/**
 * Starting point of the command line interface
 * @author Gr.45
 */
public class Cli implements View, Observer {

    /**
     * The connected game
     */
    private Game game;

    /**
     * The CLIController
     */
    private CliController controller;

    /**
     * Scanner to get User Input
     */
    private Scanner scanner = new Scanner(System.in);


    @Override
    public void update() {
        System.out.println(game.getBoard());
    }

    /**
     * this method shows the welcome screen in console mode
     */
    public void showWelcomeScreen() {
        String welcomeScreenStars = "*****************************************************";
        System.out.println(welcomeScreenStars + "\n"
                + "* Welcome to the beta version of the chess program! *" + "\n"
                + welcomeScreenStars + "\n"
                + "*               Choose your opponent                *" + "\n"
                + "*                                                   *" + "\n"
                + "*     1. Human                       2. Computer    *" + "\n"
                + "*                                                   *" + "\n"
                + welcomeScreenStars + "\n"
                + welcomeScreenStars + "\n");
    }


    /**
     * read Input from User
     */
    public void readInputFromHuman() {
        String input = scanner.nextLine();
        boolean FINISHED = false;
        while (!FINISHED) {
            if (input.equals("beaten")) {
                System.out.println(controller.getBeatenPieces());
                input = scanner.nextLine();
                continue;
            }
            if (input.equals("time")) {
                System.out.println(controller.convertTime(
                        game.getCurrentPlayer().getTimeLeft()));
                input = scanner.nextLine();
                continue;
            }
            if (input.equals("undo")) {
                controller.undoMove(game.getAllListOfMoves().size()-1);
                break;
            }
            if (input.equals("redo")) {
                controller.redoMove(1);
                break;
            }
            if (!controller.isValidInput(input)) {
                System.out.println("!Invalid move");
                input = scanner.nextLine();
                continue;
            }
            if(!controller.isValidMove(input)) {
                System.out.println("!Move not allowed");
                input = scanner.nextLine();
                continue;
            }
             else {
                System.out.println("!" + input);
                game.getCurrentPlayer().setHasPlayerUndidAMove(false);
                game.getCurrentPlayer().setHasPlayerRedidAMove(false);
            }
            FINISHED = true;
        }
    }

    /**
     * this method reads inputs from computer
     * @param move move of a piece
     * @return boolean
     */
    public boolean readInputFromComputer(String move) {
        if (controller.isValidMove(move)) {
            System.out.println("!" + move);
            return true;
        }
        return false;
    }

    /**
     * this method notifies player status of the game
     *
     * @param status status of the game
     * @param player player
     */
    public void notifyUser(Attributes.GameStatus status, Player player) {
        switch (status) {
            case ENDED_IN_WIN:
                System.out.println(player + " has won the game!");
                break;
            case KING_IN_CHECK:
                System.out.println(player + "'s king is in check.");
                break;
            case ENDED_IN_DRAW:
                System.out.println("Game Ended in a draw.");
            case TIME_OUT:
                System.out.println(player + " has ran out of time and lost the game!!");
        }
    }

    /**
     * Show the game mode
     * @param finished if the loop finished
     */
    public void gameMode(boolean finished) {
        boolean finish = false;
        String gameMode;
        while (!finished) {
            gameMode = scanner.nextLine().toLowerCase(Locale.ROOT);
            if (gameMode.equals("1")) {
                controller.setGameSettings(4, "0");
                scanTime();
            } else if (gameMode.equals("2")) {
                controller.setGameSettings(4, "1");
                scanTime();
            } else {
                System.out.println("Pleas enter a Valid Input!");
                continue;
            }
            finished = true;
        }

    }

    /**
     * Game Mode Choice if with Timer or not.
     */
    public void scanTime() {
        boolean finish = false;
        while (!finish) {
            System.out.println("Please Enter the Game Time (Enter 0 if you wish to play without Timer): ");
            System.out.println("With inputting the keyword 'time' you can see how much time you have.");
            String time = scanner.nextLine();
            if (time.matches("\\d*")) {
                if (time.equals("0")) {
                    controller.setGameSettings(2, "0");
                } else if(time.matches("")) {
                    System.out.println("Pleas enter a Valid Input!");
                    continue;
                }else {
                    controller.setGameSettings(2, time);
                }
                finish = true;
            } else {
                System.out.println("Please enter a Number!");
            }
        }
    }


    /**
     * Getter for the game
     * @return game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Setter for the game
     * @param game The current game
     */
    public void setGame(Game game) {
        // set the game as model
        this.game = game;
        game.addObserver(this);
    }

    /**
     * Setting the controller
     * @param controller controller
     */
    public void setController(CliController controller) {
        this.controller = controller;
    }
}
