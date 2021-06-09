package chess.cli;

import java.util.Locale;
import java.util.Scanner;

import chess.Attributes;
import chess.controller.CliController;
import chess.model.Game;
import chess.model.Player;
import chess.view.View;

/**
 * The command line interface
 */
public class Cli implements View {

    /**
     * The connected game
     */
    protected Game game;

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

    public void showWelcomeScreen() {
        System.out.println("*****************************************************" + "\n"
                        +  "* Welcome to the beta version of the chess program! *" + "\n"
                        +  "*****************************************************" + "\n"
                        +  "*               Choose your opponent                *" + "\n"
                        +  "*                                                   *" + "\n"
                        +  "*     1. Human                       2. Computer    *" + "\n"
                        +  "*****************************************************" + "\n"
                        +  "*****************************************************" + "\n");
    }

    /**
     * determine the Game Mode of the Player.
     */
    public void gameMode() {
        boolean finish = false;
        String gameMode;
        while (!finish) {
            gameMode = scanner.nextLine().toLowerCase(Locale.ROOT);
            if (gameMode.equals("1")) {
                controller.setGameMode(Attributes.GameMode.HUMAN);
            }
            else if(gameMode.equals("2")) {
                controller.setGameMode(Attributes.GameMode.COMPUTER);
            }
            else {
                System.out.println("Pleas enter a Valid Input!");
                continue;
            }
            finish = true;
        }
    }

    /**
     * read Input from User
     */
    @Override
    public void readInputFromHuman() {
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
    public boolean readInputFromComputer(String move) {
        if (controller.isValidMove(move)) {
            System.out.println("!" + move);
            return true;
        }
        return false;
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
    public void assignController(CliController controller) {
        this.controller = controller;
    }

    /**
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * @param game the game to set
     */
    public void setGame(Game game) {
        this.game = game;
        game.addObserver(this);
    }

    /**
     * @param game The game to observe
     */
    public void modelChanged(Game game) {
        this.game = game;
        System.out.println(game.getBoard());
    }
}
