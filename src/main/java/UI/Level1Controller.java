package UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import worldofzuul.Game;

import java.io.IOException;
import java.util.ArrayList;

public class Level1Controller extends PlayerControl{

    final String questFile = "/fxml/PopUp.fxml";

    @FXML
    public AnchorPane window;

    @FXML
    public ImageView itemCan1, itemCan2, player, tpNorth, tp1;

    private ArrayList<ImageView> itemsInRoom = new ArrayList<>();

    public void populateArraylist(){
        //Add All Items to the Arraylist;

        itemsInRoom.add(itemCan1);
        itemsInRoom.add(itemCan2);
    }

    @FXML
    public void initialize(){
        populateArraylist();
        window.getChildren().addAll(createScoreBar());
        setImages(itemsInRoom);
        try{
            Parent popUp = FXMLLoader.load(Level1Controller.class.getResource(questFile));
            Stage stage = new Stage();
            Scene scene = new Scene(popUp);
            stage.setScene(scene);
            stage.setAlwaysOnTop(true);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
