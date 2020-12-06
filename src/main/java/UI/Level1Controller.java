package UI;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import worldofzuul.Game;

import java.util.ArrayList;

public class Level1Controller extends PlayerControl{

    @FXML
    public AnchorPane window;

    @FXML
    public ImageView itemCan1, itemCan2, player, tpNorth, tp1;

    private ArrayList<ImageView> itemsInRoom = new ArrayList<>();

    public void populateArraylist(){
        //Add All Items to the Arraylist;

        itemsInRoom.add(itemCan1);
        itemsInRoom.add(itemCan2);
    }

    @FXML
    public void initialize(){
        populateArraylist();
        window.getChildren().addAll(createScoreBar());
        setImages(itemsInRoom);
    }
}
