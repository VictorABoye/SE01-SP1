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

public class PauseController implements FXMLLoading{

    final String launchFile = "/fxml/Launcher.fxml";

    @FXML
    public Button backToMenu, quit, resume;

    public void goToMenu(ActionEvent event) {
        Stage aStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        aStage.close();
        try {
            Parent launcher = FXMLLoader.load(getClass().getResource(launchFile));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(launcher));
            stage.setTitle("World of Cool");
            stage.show();
        } catch (IOException e){
            System.out.println("Cannot find fxml file");
        }
    }


    @Override
    public void closeGame(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        System.exit(1337);
    }

    public void closePause(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
