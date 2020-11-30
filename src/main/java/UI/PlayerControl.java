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

import java.io.IOException;

public abstract class PlayerControl {

    final static private String launchFile = "/fxml/Launcher.fxml";
    final static private String inventoryFile = "/fxml/Inventory.fxml";
    static final private String pauseFile = "/fxml/Pause.fxml";


    public static void playerMovement(KeyEvent event, WorldPlayer worldPlayer){
        KeyCode code = event.getCode();
        Scene window = (Scene) event.getSource();
        Player player = new Player((ImageView) window.lookup("#player"),"player","/images/obama.png");

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
            if (playerCollidesItem(event,player))
            {
                Item currentItem = new NonFoodItem(((ImageView)((Scene)event.getSource()).lookup("#itemCan")),"item","can","/images/can.png");
                worldPlayer.addItem(currentItem);
            }
            System.out.println("Pick up");
        }
        if (code == KeyCode.I)
        {
            try {
                Parent inventoryWindow = FXMLLoader.load(PlayerControl.class.getResource(inventoryFile));
                Stage stage = new Stage();
                stage.setTitle("Player Inventory");
                stage.setScene(new Scene(inventoryWindow));
                InventoryController.makeInventory(worldPlayer);
                stage.show();
                //System.out.println(keyEvent.getCode());
                //if (keyEvent.getCode() == KeyCode.ESCAPE) stage.close();
                inventoryWindow.setOnKeyPressed(InventoryController::closeWindow);
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
        System.out.println("X: " + player.getX() + "; Y: " + player.getY());
    }

    public static boolean playerCollidesItem(KeyEvent event, Player player /*, ImageView item*/){
        //Room currentRoom = event.getSource();
        //Item currentItem = currentRoom.getItem(item);
        //Player player = currentRoom.getPlayer();
        Item currentItem = new NonFoodItem(((ImageView)((Scene)event.getSource()).lookup("#itemCan")),"item","can","@../images/can.png");
        //Player player = new Player(((ImageView)((Scene)event.getSource()).lookup("#player")),"player","@../images/obama.png");
        double ix1 = currentItem.getX(); double iy1 = currentItem.getY(); double ix2 = currentItem.getW()+ix1; double iy2 = currentItem.getH()+iy1;
        double px1 = player.getX(); double py1 = player.getY(); double px2 = player.getW()+px1; double py2 = player.getH()+py1;
        if (px2 >= ix1 && py1 <= iy2 && !(px1 >= ix2) && !(py2 <= iy1)){
            currentItem.getImageView().setVisible(false);
            return true;
        }
        return false;
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
