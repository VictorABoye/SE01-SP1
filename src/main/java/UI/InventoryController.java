package UI;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import worldofzuul.Game;


public class InventoryController {

    @FXML
    public AnchorPane window;

    @FXML
    public void initialize(){
        if (Game.getWorldPlayer().getInventory().getSize() > 0) {
            for (int i = 0; i < Game.getWorldPlayer().getInventory().getSize(); i++) {
                String url = Game.getWorldPlayer().getInventory().getItem(0).getImageView().getImage().getUrl();
                ImageView itemView = new ImageView(new Image(url));
                itemView.setFitHeight(50);
                itemView.setFitWidth(50);
                itemView.setLayoutY(75);
                itemView.setLayoutX(50*i+10);
                window.getChildren().add(itemView);
            }
        }
    }
}
