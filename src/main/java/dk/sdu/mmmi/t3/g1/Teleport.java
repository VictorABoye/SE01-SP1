package dk.sdu.mmmi.t3.g1;

import UI.Level1Controller;
import UI.Level2Controller;
import UI.PlayerControl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import worldofzuul.Game;

import java.io.IOException;

public class Teleport extends Entity{

    private String linkedRoomFXML;

    public Teleport(ImageView image, String type, String linkedRoomFXML) {
        super(image, type);
        this.linkedRoomFXML = linkedRoomFXML;
    }

    public void teleportToRoom(KeyEvent event, Stage stage){
        try {
            Parent firstLevel = FXMLLoader.load(getClass().getResource(linkedRoomFXML));
            Scene scene = new Scene(firstLevel);
            scene.setOnKeyPressed(anEvent -> {
                Level1Controller.playerMovement(anEvent, Game.getWorldPlayer(), stage);
            });
            stage.setScene(scene);
            stage.show();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
