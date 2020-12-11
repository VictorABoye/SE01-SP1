package UI;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;
import worldofzuul.Game;

import java.io.IOException;

public class LaunchController {

    final String firstLevelFile = "/fxml/Level1.fxml";
    final String aboutFile = "/fxml/About.fxml";

    @FXML
    public Button startButton, aboutButton, exitButton;

    @FXML
    public void startGame(ActionEvent actionEvent) {
        try {
            Game game = new Game();
            game.play();
            Parent firstLevel = FXMLLoader.load(getClass().getResource(firstLevelFile));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(firstLevel);
            scene.setOnKeyPressed(event -> Level1Controller.playerMovement(event,stage));
            stage.setScene(scene);
            stage.show();
        } catch (IOException | ParseException e){
            System.out.println(e.getMessage());
        }

    }

    @FXML
    public void aboutPage(ActionEvent actionEvent) {
        openAbout();
    }

    @FXML
    public void closeGame(ActionEvent event) {
        Platform.exit();
        System.exit(1337);
    }

    public void initialize(){
        openAbout();

    }
    @FXML
    public void openAbout(){
        try {
            Parent aboutWindow = FXMLLoader.load(getClass().getResource(aboutFile));
            Stage stage = new Stage();
            stage.setTitle("About the Game");
            Scene scene = new Scene(aboutWindow);
            stage.setAlwaysOnTop(true);
            stage.requestFocus();
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ESCAPE) stage.close();
            });
            stage.setScene(scene);
            stage.show();
        } catch (IOException e){
            System.out.println("Cannot find fxml file");
        }
    }
}
