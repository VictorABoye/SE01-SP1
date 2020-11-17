package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUI extends Application {

    FXMLLoader loader = new FXMLLoader();

    public static void main(String[] arg){
        launch(arg);
    }

    @Override
    public void start(Stage stage) throws Exception {
        loader.setLocation(getClass().getResource("/fxml/test.fxml"));
        Parent root = loader.load();
        stage.setTitle("World of Cool");
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }
}
