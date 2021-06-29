package chess.view;

import java.util.Locale;
import java.util.Scanner;

import chess.Attributes;
import chess.controller.CliController;
import chess.controller.Controller;
import chess.model.Game;
import chess.model.Player;
import chess.util.Observer;

/**
 * The command line interface
 */
public class Cli implements Observer, View {

    /**
     * The connected game
     */
    protected Game game;
    /**
     * The CLIController
     */
    protected CliController controller;
    /**
     * if Game with Timer
     */
    String time;
    /**
     * Scanner to get User Input
     */
    Scanner scanner = new Scanner(System.in);
    /**
     * Scanner for the Timer Duration.
     */
    Scanner scanDuration = new Scanner(System.in);

    /**
     * The constructor of the view of the CLI application.
     */
    public Cli() {
        super();
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
     * determine the Game Mode of the Player.
     */
    public void gameMode() {
        boolean finish = false;
        String gameMode;
        while (!finish) {
            gameMode = scanner.nextLine().toLowerCase(Locale.ROOT);
            if (gameMode.equals("1")) {
                scanTime(Attributes.GameMode.HUMAN, Attributes.GameMode.HUMAN_TIMER);
            } else if (gameMode.equals("2")) {
                scanTime(Attributes.GameMode.COMPUTER, Attributes.GameMode.COMPUTER_TIMER);
            } else {
                System.out.println("Pleas enter a Valid Input!");
                continue;
            }
            finish = true;
        }
    }

    /**
     * Game Mode Choice if with Timer or not.
     *
     * @param mode
     * @param modeWithTimer
     */
    public void scanTime(Attributes.GameMode mode, Attributes.GameMode modeWithTimer) {
        boolean finish = false;
        while (!finish) {
            System.out.println("Please Enter the Game Time(Enter 0 if you wish to play without Timer): ");
            time = scanDuration.nextLine();
            if (time.matches("\\d*")) {
                if (time.equals("0"))
                    controller.setGameMode(mode);
                else controller.setGameMode(modeWithTimer);
                finish = true;
            } else {
                System.out.println("Please enter a Number!");
            }
        }
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
                controller.undoMove();
                input = scanner.nextLine();
                continue;
            }
            if (input.equals("redo")) {
                controller.redoMove();
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

    /**
     * this method reads inputs from computer
     *
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
     * this method assigns controller
     *
     * @param controller controller of console
     */
    public void assignController(Controller controller) {
        this.controller = (CliController) controller;
    }


    public Game getGame() {
        return game;
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
        game.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println(game.getBoard());
    }

    /**
     * Getter time Duration
     *
     * @return Duration
     */
    public String getTime() {
        return time;
    }
}
