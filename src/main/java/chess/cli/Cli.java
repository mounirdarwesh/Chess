package chess.cli;

import java.util.Locale;
import java.util.Scanner;

import chess.Attributes;
import chess.controller.CliController;
import chess.model.Player;
import chess.view.View;

/**
 * The command line interface
 */
public class Cli extends View {

    /**
     * Scanner to get User Input
     */
    Scanner scanner = new Scanner(System.in);

    /**
     * The CLIController
     */
    protected CliController controller;


    /**
     * The constructor of the view of the CLI application.
     */
    public Cli() {
        super();
    }

    /**
     * determine the Game Mode of the Player.
     */
    public void gameMode() {
        boolean finish = false;
        String gameMode;
        System.out.println("Game against Human player press H\n" +
                "Game against Computer press C");
        while (!finish) {
            gameMode = scanner.nextLine().toLowerCase(Locale.ROOT);
            if (!(gameMode.equals("c") || gameMode.equals("h"))) {
                System.out.println("Pleas enter a Valid Input!");
                continue;
            }
            controller.isVsComp(gameMode);
            finish = true;
        }
    }

    /**
     * read Input from User
     */
    @Override
    public void readInputFromPlayer() {
        String input = scanner.nextLine();
        boolean FINISHED = false;
        while (!FINISHED) {
            if (input.equals("beaten")) {
                System.out.println(controller.getBeatenPieces());
                input = scanner.nextLine();
                continue;
            }
            if (!controller.isValidInput(input)) {
                System.out.println("!Invalid move");
                input = scanner.nextLine();
                continue;
            }
            if (!controller.isValidMove(input)) {
                System.out.println("!Move not allowed");
                input = scanner.nextLine();
                continue;
            } else {
                System.out.println("!" + input);
            }
            FINISHED = true;
        }
    }

    @Override
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
     * assigning Controller to View
     *
     * @param controller that controls the View
     */
    @Override
    public void assignController(CliController controller) {
        this.controller = controller;
    }

}
