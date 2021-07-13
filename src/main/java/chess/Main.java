package chess;

import chess.controller.CliController;
import chess.view.cli.Cli;
import chess.view.gui.Gui;

import java.util.Arrays;

/**
 * The common starting point of the GUI and the CLI. Depending on the given command line arguments either the GUI or the
 * CLI interface are initialized.
 */
public class Main {
    /**
     * The external entry point of the application.
     * @param args The command line arguments passed to the application.
     */
    public static void main(String[] args) {
        // If the user asks for a CLI Game
        boolean cli = Arrays.asList(args).contains("--no-gui");
        // If the user asks for a simple CLI game
        boolean simple = Arrays.asList(args).contains("--simple");

        if (cli && !simple) {
            // Create the view
            Cli cliView = new Cli();
            new CliController(cliView, false, false);
        }
        else if (simple) {
            // Create the view
            Cli cliView = new Cli();
            new CliController(cliView, false, true);
        }
        else {
            Gui.main(args);
        }
    }
}
