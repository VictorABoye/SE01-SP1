package UI;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class Level6Controller extends PlayerControl{

    @FXML
    public AnchorPane window;
    public ImageView tpSouth, player, wall;

    private final ArrayList<ImageView> itemsInRoom = new ArrayList<>();

    @FXML
    public void initialize(){
        window.getChildren().addAll(createScoreBar());
        setImages(itemsInRoom);
    }
}
