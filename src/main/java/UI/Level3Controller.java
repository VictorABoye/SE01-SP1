package UI;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import worldofzuul.Game;

import java.util.ArrayList;

public class Level3Controller extends PlayerControl{

    @FXML
    public AnchorPane window;

    @FXML
    public ImageView tpWest, player;

    private ArrayList<ImageView> itemsInRoom = new ArrayList<>();

    public void populateArraylist(){


        //itemsInRoom.add(itemCan1);
        //itemsInRoom.add(itemCan2);
    }

    @FXML
    public void initialize(){
        populateArraylist();
        window.getChildren().addAll(createScoreBar());
        setImages(itemsInRoom);
    }
}
