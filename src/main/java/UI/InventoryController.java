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
    public Text text;

    final private int padding = 10;
    final private int size = 50;

    @FXML
    public void initialize() {
        render();
    }

    public void render(){
        Text text = (Text) window.lookup("#text");
        Inventory playerInventory = Game.getWorldPlayer().getInventory();
        if (playerInventory.getSize() > 0)
        {
            int xLayoutIndex = 0;
            int yLayoutIndex = 0;
            int rowLimit = 6;
            for (int i = 0; i < playerInventory.getSize(); i++)
            {
                if (i % rowLimit == 0) {
                    yLayoutIndex++;
                    xLayoutIndex = 0;
                }
                if (!playerInventory.getItem(i).isSorted()) {

                    String url = playerInventory.getItem(i).getImageView().getImage().getUrl();
                    ImageView itemView = new ImageView(new Image(url));
                    itemView.setId(playerInventory.getItem(i).getImageView().getId());
                    itemView.setFitHeight(size);
                    itemView.setFitWidth(size);
                    itemView.setLayoutY((size * yLayoutIndex) + text.getLayoutY() + padding);
                    itemView.setLayoutX((size * xLayoutIndex + padding * xLayoutIndex) + padding);
                    window.getChildren().add(itemView);
                    xLayoutIndex++;
                }else{
                    String url = playerInventory.getItem(i).getImageView().getImage().getUrl();
                    ImageView itemView = new ImageView(new Image(url));
                    itemView.setId(playerInventory.getItem(i).getImageView().getId());
                    itemView.setLayoutX(padding);
                }
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
