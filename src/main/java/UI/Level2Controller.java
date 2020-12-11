package UI;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class Level2Controller extends PlayerControl{

    @FXML
    public AnchorPane window;
    public ImageView tpNorth, tpSouth, tpEast, tpWest, player, glassBottle1, glassBottle2;

    private final ArrayList<ImageView> itemsInRoom = new ArrayList<>();

    public void populateArraylist(){
        //Add All Items to the Arraylist;

        //itemsInRoom.add(itemCan1);
        //itemsInRoom.add(itemCan2);
        itemsInRoom.add(glassBottle1);
        itemsInRoom.add(glassBottle2);
    }

    @FXML
    public void initialize(){
        populateArraylist();
        window.getChildren().addAll(createScoreBar());
        setImages(itemsInRoom);
    }
}
