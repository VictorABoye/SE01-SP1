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
import worldofzuul.Room;

import java.io.IOException;

public class Teleport extends Entity{

    final private String linkedRoomFXML;
    private Room linkedRoom;

    public Teleport(ImageView image, String linkedRoomFXML) {
        super(image);
        type = "teleport";
        this.linkedRoomFXML = linkedRoomFXML;
    }

    public void teleportToRoom(Stage stage){

        try {
            if (linkedRoom != null) {
                Parent firstLevel = FXMLLoader.load(getClass().getResource(linkedRoomFXML));
                Scene scene = new Scene(firstLevel);
                stage.setScene(scene);
                stage.show();
                scene.setOnKeyPressed(anEvent -> {
                    Level1Controller.playerMovement(anEvent, stage);
                });
                Game.setCurrentRoom(linkedRoom);
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    public void linkTeleport(Room room){
        linkedRoom = room;
    }
}
