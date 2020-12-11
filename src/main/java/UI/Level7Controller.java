package UI;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class Level7Controller extends PlayerControl{

    @FXML
    public AnchorPane window;
    public ImageView tpEast, player;

    private final ArrayList<ImageView> itemsInRoom = new ArrayList<>();

    @FXML
    public void initialize(){
        window.getChildren().addAll(createScoreBar());
        setImages(itemsInRoom);
    }
}
