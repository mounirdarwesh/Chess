package chess;

import chess.view.Cli;
import chess.controller.CliController;
import chess.view.gui.Gui;
import javafx.application.Application;

import java.util.*;

/**
 * The common starting point of the GUI and the CLI. Depending on the given command line arguments either the GUI or the
 * CLI interface are initialized.
 */
public class Main {
    /**
     * The external entry point of the application.
     *
     * @param args The command line arguments passed to the application.
     */
    public static void main(String[] args) {

        if (Arrays.asList(args).contains("--simple")) {
            // Create the view
            Cli cli = new Cli();
            // Create the controller and pass the view object to it
            new CliController(cli, false, true);

        } else if (Arrays.asList(args).contains("--no-gui")) {
            // Create the view
            Cli cli = new Cli();
            // Create the controller and pass the view object to it
            new CliController(cli, false, false);
        } else {
            Application.launch(Gui.class, args);
        }
    }
}
