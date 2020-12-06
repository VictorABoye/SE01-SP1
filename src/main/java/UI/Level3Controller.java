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
    public ImageView tpEast, player;

    private ArrayList<ImageView> itemsInRoom = new ArrayList<>();

    public void populateArraylist(){


        //itemsInRoom.add(itemCan1);
        //itemsInRoom.add(itemCan2);
    }

    @FXML
    public void initialize(){
        populateArraylist();
        window.getChildren().addAll(ScoreBar.createScoreBar());
        if (itemsInRoom.size() > 0 && Game.getWorldPlayer().getInventory().getSize() > 0) {
            for (ImageView roomImageView : itemsInRoom) {
                String roomItemId = roomImageView.getId();
                for (int j = 0; j < Game.getWorldPlayer().getInventory().getSize(); j++) {
                    ImageView playerImageView = Game.getWorldPlayer().getInventory().getItem(j).getImageView();
                    String playerItemID = playerImageView.getId();
                    if (playerItemID.equals(roomItemId)) {
                        roomImageView.setVisible(false);
                    }
                }
            }
        }
    }
}
