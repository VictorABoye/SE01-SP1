package UI;

import dk.sdu.mmmi.t3.g1.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import worldofzuul.Game;

import java.io.IOException;
import java.util.ArrayList;

public abstract class PlayerControl {

    final static private String launchFile = "/fxml/Launcher.fxml";
    final static private String inventoryFile = "/fxml/Inventory.fxml";
    final static private String pauseFile = "/fxml/Pause.fxml";


    //===Used by "setOnKeyPressed"======================================================================================

    //Control based on player input
    public static void playerMovement(KeyEvent event, Stage stage){
        WorldPlayer worldPlayer = Game.getWorldPlayer();
        KeyCode code = event.getCode();
        Scene window = (Scene) event.getSource();
        Player player = new Player((ImageView) window.lookup("#player"));
        player.getImageView().setRotationAxis(Rotate.Y_AXIS);
        Rectangle scoreBar = (Rectangle) window.lookup("#scoreBar");
        Rectangle scoreBackGround = (Rectangle) window.lookup("#scoreBackGround");

        //Add player animation
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
            player.getImageView().setRotate(180);
            if (player.getX() > 0)
            {
                //player.getImageView().setImage(left);
                player.moveRight();
            }
            else {
                player.setX(0);
            }
        }
        if (code == KeyCode.D || code == KeyCode.RIGHT){
            player.getImageView().setRotate(0);
            if(player.getX()+player.getW() < window.getWidth()){
                player.moveLeft();
            }
            else {
                player.setY(window.getWidth());
            }
        }

        if(code == KeyCode.E){
            if (Game.playerCollidesItem(player))
            {
                Item currentItem = Game.getClosestItemToPlayer(player);
                if (currentItem != null) {
                    worldPlayer.addItem(currentItem);
                    ImageView imageView = (ImageView) window.lookup("#" + currentItem.getImageView().getId());
                    imageView.setVisible(false);
                    Game.getCurrentRoom().removeItemFromRoom(currentItem);
                    System.out.println(Game.getWorldPlayer().getInventory().getSize());
                }
            }
        }
        if (code == KeyCode.I)
        {
            try {
                Parent inventoryWindow = FXMLLoader.load(PlayerControl.class.getResource(inventoryFile));
                Stage popup = new Stage();
                popup.setTitle("Player Inventory");
                Scene scene = new Scene(inventoryWindow);
                popup.setScene(scene);
                popup.getIcons().add(new Image("/images/test.jpg"));
                popup.show();
                scene.setOnKeyPressed(event1 -> {
                    if (event1.getCode() == KeyCode.ESCAPE) popup.close();
                });
            } catch (IOException e){
                System.out.println("Cannot find fxml file");
            }
        }

        if(code== KeyCode.ESCAPE){
            try {
                Parent pauseWindow = FXMLLoader.load(PlayerControl.class.getResource(pauseFile));
                Stage pauseStage = new Stage();
                pauseStage.setTitle("Game Paused");
                pauseStage.setScene(new Scene(pauseWindow));
                pauseStage.getIcons().add(new Image("/images/test.jpg"));
                pauseStage.show();
            } catch (IOException e){
                System.out.println("Cannot find fxml file");
            }
        }
        if (Game.playerCollidesTeleport(player)){
            Teleport currentTP = Game.getClosestTeleporterToPlayer(player);
            if (currentTP != null) {
                currentTP.teleportToRoom(stage);
                return;
            }
            System.out.println("No TP");
        }
        double climateScore = Game.getWorldPlayer().getClimateScore();
        scoreBar.setWidth(climateScore/100.0*1200);
        scoreBar.setFill(getColor(climateScore));


        //For testing
        if (code == KeyCode.K) Game.getWorldPlayer().addToClimateScore(0.5);
        if (code == KeyCode.L) Game.getWorldPlayer().addToClimateScore(-0.5);

        if (code == KeyCode.SPACE)
            System.out.println("space");
        if (code == KeyCode.SHIFT)
            System.out.println("Shift");
        if (code == KeyCode.CONTROL)
            System.out.println("Ctrl");
    }


    //===Used by the controllers "Initialize"===========================================================================

    //Creates the score bar and its background, and sets their placement
    public static Rectangle[] createScoreBar(){
        Rectangle[] scoreSet = new Rectangle[2];
        Rectangle scoreBar = new Rectangle();
        scoreBar.setId("scoreBar");
        scoreBar.setLayoutX(40);
        scoreBar.setLayoutY(680);
        scoreBar.setHeight(20);
        double score = Game.getWorldPlayer().getClimateScore();
        scoreBar.setWidth(score/100.0*1200);
        scoreBar.setFill(getColor(score));
        Rectangle scoreBackGround = new Rectangle();
        scoreBackGround.setId("scoreBackGround");
        scoreBackGround.setLayoutX(40);
        scoreBackGround.setLayoutY(680);
        scoreBackGround.setHeight(20);
        scoreBackGround.setWidth(1200);
        scoreBackGround.setFill(Color.BLACK);
        scoreSet[0] = scoreBackGround;
        scoreSet[1] = scoreBar;
        return scoreSet;
    }


    //Determines the color of the score bar
    public static Color getColor(double value){
        if (value < 10) return Color.DARKRED;
        else if (value < 20) return Color.RED;
        else if (value < 30) return Color.DARKORANGE;
        else if (value < 40) return Color.ORANGE;
        else if (value < 50) return Color.YELLOW;
        else if (value < 60) return Color.YELLOWGREEN;
        else if (value < 70) return Color.DARKGREEN;
        else if (value < 80) return Color.GREEN;
        else if (value < 90) return Color.LIGHTGREEN;
        else if (value < 100) return Color.CYAN;
        else if (value == 100) return Color.BLUE;
        else return Color.BLACK;
    }


    //Hides Images if they are in the players inventory
    public static void setImages(ArrayList<ImageView> itemsInRoom){
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
