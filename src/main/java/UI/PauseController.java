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

import java.io.IOException;

public class PauseController implements FXMLLoading{

    final String launchFile = "/fxml/Launcher.fxml";
    final String aboutFile = "/fxml/About.fxml";

    @FXML
    public Button backToMenu, quit, resume, aboutButton;

    public void goToMenu(ActionEvent event) {
        Stage aStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        aStage.close();
        try {
            Parent launcher = FXMLLoader.load(getClass().getResource(launchFile));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(launcher));
            stage.setTitle("World of Cool");
            stage.setResizable(false);
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

    public void closePause(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void aboutPage(ActionEvent actionEvent) {
        try {
            Parent aboutWindow = FXMLLoader.load(getClass().getResource(aboutFile));
            Stage stage = new Stage();
            stage.setTitle("About the Game");
            Scene scene = new Scene(aboutWindow);
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
