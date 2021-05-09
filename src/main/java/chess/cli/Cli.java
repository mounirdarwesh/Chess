package chess.cli;

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
     * read Input from User
     */
    public void readInputFromPlayer() {

        String input = scanner.nextLine();

        while (true) {
            if(input.equals("beaten")){
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
            } else System.out.println("!" + input);
            break;
        }

    }

    @Override
    public void notifyUser(Attributes.GameStatus status, Player player) {
        if (status == Attributes.GameStatus.ENDED) {
            System.out.println(player + " has won the game!");
            System.exit(1);
        }
        if (status == Attributes.GameStatus.KING_IN_CHECK) {
            System.out.println(player + "'s king is in check.");
        }
    }

    /**
     * assigning Controller to View
     *
     * @param controller that controls the View
     */
    public void assignController(CliController controller) {
        this.controller = controller;
    }

}
