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

public class Level1Controller {

    final String launchFile = "/fxml/Launcher.fxml";

    @FXML
    public Button backButton;

    @FXML
    public void goToMenu(ActionEvent actionEvent) {
        try {
            Parent launcher = FXMLLoader.load(getClass().getResource(launchFile));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(launcher));
            stage.show();
        } catch (IOException e){
            System.out.println("Cannot find fxml file");
        }
    }
}
