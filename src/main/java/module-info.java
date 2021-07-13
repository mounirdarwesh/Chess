/**
 * The main module of the chess application.
 */
module chess {
    requires javafx.controls;
    requires transitive javafx.graphics;
    
    exports chess.view.gui;
    exports chess.view.gui.startMenuView;
    exports chess.view.gui.gameView;
}
