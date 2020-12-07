package UI;

import dk.sdu.mmmi.t3.g1.Inventory;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import worldofzuul.Game;


public class InventoryController {

    @FXML
    public AnchorPane window;

    @FXML
    public Text text;

    final private int padding = 10;
    final private int size = 50;

    @FXML
    public void initialize(){
        Text text = (Text) window.lookup("#text");
        Inventory playerInventory = Game.getWorldPlayer().getInventory();
        if (playerInventory.getSize() > 0)
        {
            for (int i = 0; i < playerInventory.getSize(); i++)
            {
                String url = playerInventory.getItem(i).getImageView().getImage().getUrl();
                ImageView itemView = new ImageView(new Image(url));
                itemView.setFitHeight(size);
                itemView.setFitWidth(size);
                itemView.setLayoutY(text.getLayoutY()+padding);
                itemView.setLayoutX((size*i)+padding);
                window.getChildren().add(itemView);
            }
        }
        else
        {
            Text label = new Text();
            label.setText("Empty");
            label.setLayoutX(text.getLayoutX());
            label.setLayoutY(text.getLayoutY()+text.getFont().getSize()+padding);
            label.setFont(text.getFont());
            window.getChildren().add(label);
        }
    }
}