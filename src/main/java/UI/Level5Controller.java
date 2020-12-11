package UI;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class Level5Controller extends  PlayerControl{

    @FXML
    public AnchorPane window;
    public ImageView tpNorth, tpEast, tpWest, player,battery, plastBottle;

    private ArrayList<ImageView> itemsInRoom = new ArrayList<>();

    public void populateArraylist(){
        //Add All Items to the Arraylist;

        //itemsInRoom.add(itemCan1);
        //itemsInRoom.add(itemCan2);
        itemsInRoom.add(battery);
        itemsInRoom.add(plastBottle);

    }

    @FXML
    public void initialize(){
        populateArraylist();
        window.getChildren().addAll(createScoreBar());
        setImages(itemsInRoom);
    }
}
