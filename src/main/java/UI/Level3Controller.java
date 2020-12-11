package UI;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class Level3Controller extends PlayerControl{

    @FXML
    public AnchorPane window;
    public ImageView tpWest, player, paperbag1, paperbag2;

    private final ArrayList<ImageView> itemsInRoom = new ArrayList<>();

    public void populateArraylist(){


        //itemsInRoom.add(itemCan1);
        //itemsInRoom.add(itemCan2);
        itemsInRoom.add(paperbag1);
        itemsInRoom.add(paperbag2);
    }

    @FXML
    public void initialize(){
        populateArraylist();
        window.getChildren().addAll(createScoreBar());
        setImages(itemsInRoom);
    }
}
