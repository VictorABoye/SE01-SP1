package UI;

import dk.sdu.mmmi.t3.g1.Entity;
import dk.sdu.mmmi.t3.g1.NonFoodItem;
import dk.sdu.mmmi.t3.g1.Player;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import worldofzuul.Game;

import java.io.IOException;

public class LaunchController implements FXMLLoading {

    final String firstLevelFile = "/fxml/Level1.fxml";
    final String aboutFile = "/fxml/About.fxml";

    Game game;

    @FXML
    public Button startButton, aboutButton, exitButton;

    @FXML
    public void startGame(ActionEvent actionEvent) {
        game = new Game();
        game.play();
        try {
            Parent firstLevel = FXMLLoader.load(getClass().getResource(firstLevelFile));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(firstLevel);
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ESCAPE) Level1Controller.back(event, stage);
                else Level1Controller.playerMovement(event, game.getWorldPlayer());
            });
            stage.setAlwaysOnTop(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e){
            System.out.println("Cannot find fxml file");
            e.printStackTrace();
        }

    }

    @FXML
    public void aboutPage(ActionEvent actionEvent) {
        try {
            Parent aboutWindow = FXMLLoader.load(getClass().getResource(aboutFile));
            Stage stage = new Stage();
            stage.setTitle("About the Game");
            stage.setScene(new Scene(aboutWindow));
            stage.show();
        } catch (IOException e){
            System.out.println("Cannot find fxml file");
        }
    }

    @Override
    public void closeGame(ActionEvent event) {
        Platform.exit();
        System.exit(1337);
    }
}
