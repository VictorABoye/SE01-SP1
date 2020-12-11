package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class StartUI extends Application {

    final String launchFile = "/fxml/Launcher.fxml";

    public static void main(String[] arg) {
        launch(arg);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(launchFile));
        stage.setTitle("World of Cool");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.getIcons().add(new Image("/images/utility/gameLogoIcon.png"));
        stage.show();
    }
}
