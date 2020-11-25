package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LaunchController {

    final String firstLevelFile = "/fxml/Level1.fxml";
    final String aboutFile = "/fxml/About.fxml";

    @FXML
    public Button startButton, aboutButton, exitButton;

    @FXML
    public void startGame(ActionEvent actionEvent) {
        try {
            Parent firstLevel = FXMLLoader.load(getClass().getResource(firstLevelFile));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(firstLevel));
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

    @FXML
    public void closeGame(ActionEvent actionEvent) {
        System.exit(1337);
    }
}
