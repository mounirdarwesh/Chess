package chess.view.cli;

import chess.Attributes;
import chess.controller.CliController;
import chess.model.game.Game;
import chess.model.player.Player;
import chess.util.Observer;
import chess.view.View;

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
            if (input.equals("undo")) {
                controller.undoMove(game.getAllListOfMoves().size()-2);
                break;
            }
            if (input.equals("redo")) {
                controller.redoMove(2);
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
        }
    }

    /**
     *
     */
    public void gameMode() {
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
