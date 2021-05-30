package chess.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Starting point of the JavaFX GUI
 */
public class Gui extends Application {
    GridPane root;
    Scene startScene;
    RadioButton vsHuman;
    RadioButton vsComp;
    RadioButton black;
    RadioButton white;
    BorderPane layout;
    GridPane board;
    Rectangle[] tiles;

    /**
     * This method is called by the Application to start the GUI.
     *
     * @param primaryStage The initial root stage of the application.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chess");
        // RadioButton to choose game Mode.
        Label mode = new Label("Select your Opponent");
        vsHuman = new RadioButton("Vs. Human");
        vsComp = new RadioButton("Vs. Computer");
        ToggleGroup gameMode = new ToggleGroup();
        vsComp.setToggleGroup(gameMode);
        vsHuman.setToggleGroup(gameMode);
        // if versus Computer, the Player can choose a Color.
        Label colorPiece = new Label("Select your Color");
        black = new RadioButton("Black");
        white = new RadioButton("White");
        ToggleGroup color = new ToggleGroup();
        black.setToggleGroup(color);
        white.setToggleGroup(color);
        // Color is disabled in the Start.
        black.setDisable(true);
        white.setDisable(true);
        Button start = new Button("begin the Game");
        // if vs. Computer chosen, color is available.
        vsComp.setOnAction(event -> {
            black.setDisable(false);
            white.setDisable(false);
            start.setDisable(false);
        });
        vsHuman.setOnAction(event -> {
            black.setDisable(true);
            white.setDisable(true);
            start.setDisable(false);
        });
        root = new GridPane();
        root.setVgap(5);
        root.setAlignment(Pos.CENTER);
        root.add(mode, 0, 0);
        root.add(vsComp, 0, 1);
        root.add(vsHuman, 0, 2);
        root.add(colorPiece, 0, 3);
        root.add(black, 0, 4);
        root.add(white, 0, 5);

        start.setDisable(true);
        start.setOnAction(event -> gameInitView(primaryStage));
        root.add(start, 0, 6);
        startScene = new Scene(root,300, 250);
        primaryStage.setScene(startScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void gameInitView(Stage primaryStage) {
        layout = new BorderPane();
        board = new GridPane();
        Menu menu = new Menu("Game");
        MenuItem mainScreen = new MenuItem("Main Menu");
        mainScreen.setOnAction(event -> primaryStage.setScene(startScene));
        menu.getItems().add(mainScreen);
        Menu setting = new Menu("Settings");
        setting.getItems().add(new CheckMenuItem("Rotate Field"));
        setting.getItems().add(new CheckMenuItem("Reselect Piece"));
        setting.getItems().add(new CheckMenuItem("Check Status Notification"));
        MenuBar gameMenu = new MenuBar();
        gameMenu.getMenus().addAll(menu, setting);
        layout.setTop(gameMenu);
        layout.setCenter(board);
        createChessTile();
        TableView<String> history = new TableView<>();

        TableColumn<String,String> whiteBeaten = new TableColumn<>("White");
        TableColumn<String,String> blackBeaten = new TableColumn<>("Black");
        whiteBeaten.setResizable(true);
        history.getColumns().add(blackBeaten);
        history.getColumns().add(whiteBeaten);
        layout.setRight(history);
        primaryStage.setScene(new Scene(layout,630,505));
    }


    public void createChessTile() {
        tiles = new Rectangle[64];
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                /*createAllPieces(row, column);
                createMarker(row, column);*/
                Rectangle rectangle = new Rectangle();
                rectangle.setWidth(60);
                rectangle.setHeight(60);
                rectangle.setFill((row + column) % 2 != 0 ? Color.CHOCOLATE : Color.BLANCHEDALMOND);
                tiles[row + column * 8] = rectangle;
                board.add(rectangle, row, column);
            }
        }

    }

    /**
     * The entry point of the GUI application.
     *
     * @param args The command line arguments passed to the application
     */
    public static void main(String[] args) {
        launch(args);
    }
}
