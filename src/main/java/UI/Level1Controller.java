package UI;

import dk.sdu.mmmi.t3.g1.Item;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import worldofzuul.Game;


import java.io.IOException;

public class Level1Controller extends PlayerControl{

    @FXML
    public ImageView itemCan1, itemCan2, player, tpNorth, tp1;

    @FXML
    public void initialize(){
        //Set items
        System.out.print(Game.getRooms().get(0).getRoomInventorySize());
    }

}
