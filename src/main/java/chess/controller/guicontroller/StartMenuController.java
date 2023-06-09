package chess.controller.guicontroller;

import chess.Attributes;
import chess.controller.LANGameController;
import chess.controller.MoveController;
import chess.model.game.Game;
import chess.model.game.GuiGame;
import chess.model.player.Player;
import chess.view.gui.startmenuview.*;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.concurrent.atomic.AtomicReference;

/**
 * controller of the start menu class
 * @author Gr.45
 */
public class StartMenuController extends GuiController {

    /**
     * The view that is connected to this controller
     */
    private StartMenuView startMenuView;


    /**
     * The constructor of the start menu view controller
     * to control the action events made by the start menu
     * view
     * @param startMenuView view of the start menu
     */
    public StartMenuController(StartMenuView startMenuView) {
        this.startMenuView = startMenuView;

        //Assigning the Listeners
        assignActionListener();
    }

    /**
     * Assigning this controller to the buttons of the start menu view
     * to listen for action events
     */
    private void assignActionListener() {
        startMenuView.getNormalGameOption().setOnAction(this);
        startMenuView.getLanGameOption().setOnAction(this);
        startMenuView.getBackButton().setOnAction(this);
    }

    @Override
    public void handle(Event event) {
        // If the user clicks on the normal game button
        if (startMenuView.getNormalGameOption().equals(event.getSource())) {
            // Set the settings to be a normal game
            setGameSettings(3, "0");
            NormalGamePanel normalGamePanel = new NormalGamePanel(startMenuView);
            startMenuView.setScenePanel(normalGamePanel.asPanel());
            assignActionHandlerToNormalGamePanel(normalGamePanel);
        }

        //  If the user wants to play a LAN game, and clicks on the
        // LAN game option
        if (startMenuView.getLanGameOption().equals(event.getSource())) {
            // Set the settings to Enable the LAN Game Mode
            setGameSettings(3, "1");
            LANGamePanel lanGamePanel = new LANGamePanel(startMenuView);
            startMenuView.setScenePanel(lanGamePanel.asPanel());
            assignActionHandlerToLANGamePanel(lanGamePanel);
        }

        // If the user wants go back to the previous panel
        // to change something
        if(startMenuView.getBackButton().equals(event.getSource())) {
            startMenuView.getStartMenuPanels()
                    .remove(startMenuView.getStartMenuPanels().size()-1);
            startMenuView.setScenePanel(startMenuView.getStartMenuPanels().get(
                    startMenuView.getStartMenuPanels().size()-1));
        }
    }

    /**
     * assign action handler to normal game panel
     * @param normalGamePanel Normal game panel
     */
    private void assignActionHandlerToNormalGamePanel(NormalGamePanel normalGamePanel) {
        // When the user chooses to play against a normal human opponent
        normalGamePanel.getHumanGameButton().setOnAction(e -> {
            // Set the player to have the white pieces
            startMenuView.getParent().getGameViewController().
                    setGameSettings(0, "1");
            // And no AI mode
            startMenuView.getParent().getGameViewController().
                    setGameSettings(4, "0");
            // create a clock panel and change the scene accordingly
            ClockChoicePanel clockChoicePanel = new ClockChoicePanel(startMenuView);
            startMenuView.setScenePanel(clockChoicePanel.asPanel());
            assignActionHandlerToClockChoicePanel(clockChoicePanel);
        });
        normalGamePanel.getAIGameButton().setOnAction(e -> {
            // Enable the AI mode
            startMenuView.getParent().getGameViewController().
                    setGameSettings(4, "1");
            // Let the player choose the color if he wishes to
            // play against the AI
            ColorChoicePanel colorChoicePanel = new ColorChoicePanel(startMenuView);
            startMenuView.setScenePanel(colorChoicePanel.asPanel());
            assignActionHandlerToColorChoicePanel(colorChoicePanel);
        });
    }

    /**
     * assign action handler to clock choice panel
     * @param clockChoicePanel clock choice panel
     */
    private void assignActionHandlerToClockChoicePanel(ClockChoicePanel clockChoicePanel) {
        String regex = "[0-9]+";
        clockChoicePanel.getWhiteTimerOptions().setOnKeyTyped(e -> {
            String wD = clockChoicePanel.getWhiteTimerOptions().getText();
            if (wD.matches(regex)) {
                startMenuView.getParent().getGameViewController()
                        .setGameSettings(2, wD);
            }
        });
        clockChoicePanel.getBlackTimerOptions().setOnKeyTyped(e -> {
            String bD = clockChoicePanel.getBlackTimerOptions().getText();
            if (bD.matches(regex)) {
                startMenuView.getParent().getGameViewController().
                        setGameSettings(9, bD);
            }
        });

        clockChoicePanel.getStartGameButton().setOnAction(e -> {
            Game game = new GuiGame(startMenuView.getParent().getGameViewController());
            startMenuView.getParent().getGameViewController().setGame(game);
            startMenuView.getParent().setGame(game);
            //Create the move controller
            new MoveController().setGame(game);
            startMenuView.getParent().getGameView().initGameComponents();
            startMenuView.getParent().getGameViewController().assignActionListenerToGameComponents();
            game.run();
        });
    }

    /**
     * assign action handler to color choice panel
     * @param colorChoicePanel color choice panel
     */
    private void assignActionHandlerToColorChoicePanel(ColorChoicePanel colorChoicePanel) {
        colorChoicePanel.getWhiteButton().setOnAction(e -> {
            startMenuView.getParent().getGameViewController().
                    setGameSettings(0, "1");
            ClockChoicePanel clockChoicePanel = new ClockChoicePanel(startMenuView);
            startMenuView.setScenePanel(clockChoicePanel.asPanel());
            assignActionHandlerToClockChoicePanel(clockChoicePanel);
        });
        colorChoicePanel.getBlackButton().setOnAction(e -> {
            startMenuView.getParent().getGameViewController().
                    setGameSettings(0, "0");
            ClockChoicePanel clockChoicePanel = new ClockChoicePanel(startMenuView);
            startMenuView.setScenePanel(clockChoicePanel.asPanel());
            assignActionHandlerToClockChoicePanel(clockChoicePanel);
        });
    }

    /**
     * assign action handler to LANGame panel
     * @param lanGamePanel LANGame panel
     */
    private void assignActionHandlerToLANGamePanel(LANGamePanel lanGamePanel) {
        lanGamePanel.getJoinButton().setOnAction(e -> {
            JoinHostPanel joinHostPanel = new JoinHostPanel(startMenuView);
            startMenuView.setScenePanel(joinHostPanel.asPanel());
            assignActionHandlerToJoinHostPanel(joinHostPanel);

        });
        lanGamePanel.getHostButton().setOnAction(e -> {
            HostSettingsPanel hostSettingsPanel = new HostSettingsPanel(startMenuView);
            startMenuView.setScenePanel(hostSettingsPanel.asPanel());
            assignActionHandlerToHostSettingsPanel(hostSettingsPanel);
        });
    }


    /**
     * assign action handler to join host panel
     * @param joinHostPanel join host panel
     */
    private void assignActionHandlerToJoinHostPanel(JoinHostPanel joinHostPanel) {
        AtomicReference<String> ip = new AtomicReference<>("");
        joinHostPanel.getIpNrField().setOnKeyTyped(e -> {
            ip.set(joinHostPanel.getIpNrField().getText());
            joinHostPanel.getJoinGameButton().setDisable(false);
        });
        joinHostPanel.getJoinGameButton().setOnAction(e -> {
            LANGameController lanGameController = new LANGameController(startMenuView
                    .getParent().getGameView());
            lanGameController.setGameSettings(3, "1");
            lanGameController.joinLANGame(ip);
            startMenuView.getParent().getGameViewController().setGame(lanGameController.getGame());
            startMenuView.getParent().setGame(lanGameController.getGame());
            //Create the move controller
            new MoveController().setGame(lanGameController.getGame());
            startMenuView
                    .getParent().getGameView().initGameComponents();
            startMenuView
                    .getParent().getGameViewController()
                    .assignActionListenerToGameComponents();
            lanGameController.getGame().run();
        });
    }

    /**
     * assign action handler to host settings panel
     * @param hostSettingsPanel host settings panel
     */
    private void assignActionHandlerToHostSettingsPanel(HostSettingsPanel hostSettingsPanel) {
        LANGameController lanGameController = new LANGameController(startMenuView
                .getParent().getGameView());
        hostSettingsPanel.getChooseColorButton().setOnAction(e -> {
            lanGameController.setGameSettings(0,
                    hostSettingsPanel.getChooseColorButton()
                            .getValue().equals("White") ?
                    "1" : "0");
            if (hostHasInputAllSetting(hostSettingsPanel)) {
                hostSettingsPanel.getStartLANGameButton().setDisable(false);
            }
        });
        hostSettingsPanel.getEnableRedoButton().setOnAction(e -> {
            lanGameController.setGameSettings(1,
                    hostSettingsPanel.getEnableRedoButton().getValue().equals("Yes") ?
                    "1" : "0");
            if (hostHasInputAllSetting(hostSettingsPanel)) {
                hostSettingsPanel.getStartLANGameButton().setDisable(false);
            }
        });
        hostSettingsPanel.getEnableTimeButton().setOnAction(e -> {
            createDurationFields(hostSettingsPanel, lanGameController);
        });
        hostSettingsPanel.getStartLANGameButton().setOnAction(e -> {
            lanGameController.setGameSettings(3, "1");
            lanGameController.createLANGame(28513);
            startMenuView.getParent().getGameViewController()
                    .setGame(lanGameController.getGame());
            startMenuView.getParent().setGame(lanGameController.getGame());
            //Create the move controller
            new MoveController().setGame(lanGameController.getGame());
            startMenuView.getParent().getGameView().initGameComponents();
            startMenuView.getParent().getGameViewController()
                    .assignActionListenerToGameComponents();
            lanGameController.getGame().run();
        });
    }

    /**
     * Creating the duration fields
     * @param hostSettingsPanel the host panel
     * @param lanGameController the lan game controller
     */
    private void createDurationFields(HostSettingsPanel hostSettingsPanel, LANGameController lanGameController) {
        TextField whiteDuration = new TextField();
        TextField BlackDuration = new TextField();
        if (hostSettingsPanel.getEnableTimeButton().getValue().equals("Yes")) {
            whiteDuration.setPromptText("White Player Time in sec");
            BlackDuration.setPromptText("Black Player Time in sec");
            whiteDuration.setMinSize(50, 18);
            BlackDuration.setMinSize(50, 18);
            VBox.setMargin(whiteDuration, new Insets(0, 0, 0, 0));
            VBox.setMargin(BlackDuration, new Insets(0, 0, 0, 0));
            hostSettingsPanel.asPanel().getChildren().add(4, whiteDuration);
            hostSettingsPanel.asPanel().getChildren().add(5, BlackDuration);
            assignActionHandlerToDurationField(lanGameController, whiteDuration,
                    BlackDuration, hostSettingsPanel.getStartLANGameButton());
        } else if(hostSettingsPanel.getEnableTimeButton().getValue().equals("No")){
            hostSettingsPanel.asPanel().getChildren().remove(whiteDuration);
            hostSettingsPanel.asPanel().getChildren().remove(BlackDuration);
            lanGameController.setGameSettings(2, "0");
            lanGameController.setGameSettings(9, "0");
        }
        if (hostHasInputAllSetting(hostSettingsPanel)) {
            hostSettingsPanel.getStartLANGameButton().setDisable(false);
        }
    }
    /**
     * assign action handler to duration field
     * @param lanGameController controller of the LANGame
     * @param whiteDuration the white duration
     * @param blackDuration the black duration
     * @param startLANGameButton Button start LANGame
     */
    private void assignActionHandlerToDurationField(LANGameController lanGameController, TextField whiteDuration,
                                                    TextField blackDuration, Button startLANGameButton) {
        whiteDuration.setOnKeyTyped(e -> {
            String wD = whiteDuration.getText();
            if (wD.matches("[0-9]+")) {
                startLANGameButton.setDisable(false);
                lanGameController.setGameSettings(2, wD);
            }
        });
        blackDuration.setOnKeyTyped(e -> {
            String bD = blackDuration.getText();
            if (bD.matches("[0-9]+")) {
                startLANGameButton.setDisable(false);
                lanGameController.setGameSettings(9, bD);
            }
        });
    }

    /**
     * input of all settings of the host
     * @param hostSettingsPanel panel of the host settings
     * @return boolean
     */
    private boolean hostHasInputAllSetting(HostSettingsPanel hostSettingsPanel) {
        return hostSettingsPanel.getChooseColorButton().getValue() != null
                && hostSettingsPanel.getEnableRedoButton().getValue() != null
                && hostSettingsPanel.getEnableTimeButton() != null;
    }

    @Override
    public void notifyView(Attributes.GameStatus status, Player player) {}
}
