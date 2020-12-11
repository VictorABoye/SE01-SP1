package UI;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class Level7Controller extends PlayerControl{

    @FXML
    public AnchorPane window;
    public ImageView tpEast, player;

    private ArrayList<ImageView> itemsInRoom = new ArrayList<>();

    public void populateArraylist(){
        //Add All Items to the Arraylist;

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
