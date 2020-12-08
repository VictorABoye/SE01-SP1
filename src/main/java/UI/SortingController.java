package UI;

import dk.sdu.mmmi.t3.g1.Inventory;
import dk.sdu.mmmi.t3.g1.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import worldofzuul.Game;

import java.util.ArrayList;

public class SortingController {

    @FXML
    public AnchorPane window;

    @FXML
    public Button sortButtonMetal, sortButtonPlast, sortButtonBattery, sortButtonGlass, sortButtonPaper;

    private int padding = 10;
    private int size = 50;

    private ArrayList<ImageView> selected = new ArrayList<>();

    @FXML
    public void initialize(){
        render();
    }

    public void sortItems(ActionEvent actionEvent) {
        for (int i = 0; i < selected.size(); i++) {
            String itemId = selected.get(i).getId();
            System.out.println(itemId);
            for (int j = 0; j < Game.getWorldPlayer().getInventory().getSize(); j++) {
                Item currentItem = Game.getWorldPlayer().getInventory().getItem(j);
                String inventoryId = currentItem.getImageView().getId();
                System.out.println(inventoryId);
                System.out.println(itemId);
                if (itemId.equals(inventoryId)) {
                    currentItem.setSorted();
                    selected.get(i).setVisible(false);
                    System.out.println(selected.get(i).getId());

                    //checker lige hurtig
                    if(actionEvent.getTarget().equals(sortButtonMetal)){
                        if (currentItem.getMaterial().equals("metal")){
                            Game.getWorldPlayer().addToClimateScore(5);
                        }else {
                            Game.getWorldPlayer().addToClimateScore(-5);
                        }

                    }
                    if(actionEvent.getTarget().equals(sortButtonGlass)){
                        if (currentItem.getMaterial().equals("glass")){
                            Game.getWorldPlayer().addToClimateScore(5);
                        }else {
                            Game.getWorldPlayer().addToClimateScore(-5);
                        }

                    }
                    if(actionEvent.getTarget().equals(sortButtonBattery)){
                        if (currentItem.getMaterial().equals("battery")){
                            Game.getWorldPlayer().addToClimateScore(5);
                        }else {
                            Game.getWorldPlayer().addToClimateScore(-5);
                        }

                    }
                    if(actionEvent.getTarget().equals(sortButtonPaper)){
                        if (currentItem.getMaterial().equals("paper")){
                            Game.getWorldPlayer().addToClimateScore(5);
                        }else {
                            Game.getWorldPlayer().addToClimateScore(-5);
                        }

                    }
                    if(actionEvent.getTarget().equals(sortButtonPlast)){
                        if (currentItem.getMaterial().equals("plast")){
                            Game.getWorldPlayer().addToClimateScore(5);
                        }else {
                            Game.getWorldPlayer().addToClimateScore(-5);
                        }

                    }


                }
            }
        }
        selected.clear();
    }


    private void render(){
        Text text = (Text) window.lookup("#text");
        Inventory playerInventory = Game.getWorldPlayer().getInventory();
        if (playerInventory.getSize() > 0)
        {
            int viggo = 0;
            for (int i = 0; i < playerInventory.getSize(); i++)
            {
                if (!playerInventory.getItem(i).isSorted()) {

                    String url = playerInventory.getItem(i).getImageView().getImage().getUrl();
                    ImageView itemView = new ImageView(new Image(url));
                    itemView.setId(playerInventory.getItem(i).getImageView().getId());
                    itemView.setFitHeight(size);
                    itemView.setFitWidth(size);
                    itemView.setLayoutY(text.getLayoutY() + padding);
                    itemView.setLayoutX((size * viggo + padding * viggo) + padding);
                    itemView.setOnMouseClicked(this::select);
                    window.getChildren().add(itemView);
                    viggo++;
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

    private void select(MouseEvent event){
        ImageView selectedItem = (ImageView)event.getSource();
        selectedItem.setEffect(new Bloom(0.0));
        selected.add(selectedItem);
        selectedItem.setOnMouseClicked(this::unSelect);
    }

    private void unSelect(MouseEvent event){
        ImageView unSelectedItem = (ImageView)event.getSource();
        unSelectedItem.setEffect(new Bloom(1.0));
        selected.remove(unSelectedItem);
        unSelectedItem.setOnMouseClicked(this::select);
    }
}
