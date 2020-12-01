package UI;

import dk.sdu.mmmi.t3.g1.Item;
import dk.sdu.mmmi.t3.g1.NonFoodItem;
import dk.sdu.mmmi.t3.g1.WorldPlayer;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import dk.sdu.mmmi.t3.g1.Player;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import worldofzuul.Game;
import worldofzuul.Room;

import java.io.IOException;

public abstract class PlayerControl {

    final static private String launchFile = "/fxml/Launcher.fxml";
    final static private String inventoryFile = "/fxml/Inventory.fxml";
    static final private String pauseFile = "/fxml/Pause.fxml";


    public static void playerMovement(KeyEvent event, WorldPlayer worldPlayer){
        KeyCode code = event.getCode();
        Scene window = (Scene) event.getSource();
        Player player = new Player((ImageView) window.lookup("#player"),"player");

        //Fix all this
        if (code == KeyCode.W){
            if(player.getY() > 0){
                player.moveUp();
            }
            else {
                player.setY(0);
            }
        }
        if (code == KeyCode.S){
            if(player.getY()+player.getH() < window.getHeight()){
                player.moveDown();
            }
            else {
                player.setY(window.getHeight());
            }
        }
        if (code == KeyCode.A || code == KeyCode.LEFT) {
            if (player.getX() > 0) {
                player.moveRight();
            }
            else {
                player.setX(0);
            }
        }
        if (code == KeyCode.D || code == KeyCode.RIGHT){
            if(player.getX()+player.getW() < window.getWidth()){
                player.moveLeft();
            }
            else {
                player.setY(window.getWidth());
            }
        }
        if(code == KeyCode.E){
            // Add functionality to pick up items
            //System.out.println(playerCollidesItem(event));
            if (Game.playerCollidesItem(player))
            {
                Item currentItem = Game.getClosestItemToPlayer(player);
                worldPlayer.addItem(currentItem);
                ImageView imageView = (ImageView) window.lookup("#" + currentItem.getImageView().getId());
                imageView.setVisible(false);
                Game.getCurrentRoom().removeItemFromRoom(currentItem);
            }
            System.out.println("Pick up");
        }
        if (code == KeyCode.I)
        {
            try {
                Parent inventoryWindow = FXMLLoader.load(PlayerControl.class.getResource(inventoryFile));
                Stage stage = new Stage();
                stage.setTitle("Player Inventory");
                Scene scene = new Scene(inventoryWindow);
                stage.setScene(scene);
                InventoryController.makeInventory(worldPlayer);
                stage.show();
                //System.out.println(keyEvent.getCode());
                //if (keyEvent.getCode() == KeyCode.ESCAPE) stage.close();
                scene.setOnKeyPressed(event1 -> {
                    stage.close();
                });
            } catch (IOException e){
                System.out.println("Cannot find fxml file");
            }
        }

        /*
        if(code== KeyCode.ESCAPE){
            Stage theWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            theWindow.close();
            try {
                Parent pauseWindow = FXMLLoader.load(PlayerControl.class.getResource(pauseFile));
                Stage stage = new Stage();
                stage.setTitle("Game Paused");
                stage.setScene(new Scene(pauseWindow));
                stage.show();
            } catch (IOException e){
                System.out.println("Cannot find fxml file");
            }
            //goToMenu(event);
        }

         */

        //Dunno how these if statements function
        if (code == KeyCode.SPACE)
            System.out.println("space");
        if (code == KeyCode.SHIFT)
            System.out.println("Shift");
        if (code == KeyCode.CONTROL)
            System.out.println("Ctrl");
        else System.out.println(code.toString());
        //System.out.println("X: " + player.getX() + "; Y: " + player.getY());
    }

/*
    public void goToMenu(Event actionEvent) {
        try {
            Parent launcher = FXMLLoader.load(getClass().getResource(launchFile));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(launcher));
            stage.show();
        } catch (IOException e){
            System.out.println("Cannot find fxml file");
        }
    }

 */
}
