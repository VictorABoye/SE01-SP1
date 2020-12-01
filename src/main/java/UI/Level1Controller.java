package UI;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


import java.io.IOException;

public class Level1Controller extends PlayerControl{

    final static String pauseFile = "/fxml/Pause.fxml";

    @FXML
    public ImageView itemCan1, itemCan2, player, tpNorth;



    public static void back(KeyEvent event, Stage preStage) {
        if (event.getCode() == KeyCode.ESCAPE){
            try {
                Parent pauseWindow = FXMLLoader.load(Level1Controller.class.getResource(pauseFile));
                Stage stage = new Stage();
                stage.setTitle("Game Paused");
                stage.setScene(new Scene(pauseWindow));
                stage.show();
            } catch (IOException e){
                System.out.println("Cannot find fxml file");
            }
        }
    }

    /*
    public void move(Event event){
        if(event instanceof KeyEvent) {
            if(event.getEventType().toString().equals("KEY_PRESSED")) {
                keyPressed((KeyEvent) event);
            }


        }
        // Used for finding KeyCode and relating key info
        //System.out.println(event);
    }



    public void keyPressed(KeyEvent e) {
        KeyCode code = e.getCode();


        //Fix all this
        if (code == KeyCode.W){
            if(player.getY()>-1*(Window.getHeight())+260){
                player.setY(player.getY()-speed);
                System.out.println(Window.getHeight());
            }
        }
        if (code == KeyCode.S){
            if(player.getY()<Window.getHeight()-700){
                player.setY(player.getY()+speed);
            }
        }
        if (code == KeyCode.A || code == KeyCode.LEFT) {
            if (player.getX() > 0) {
                player.setX(player.getX() - speed);
            }
        }
        if (code == KeyCode.D || code == KeyCode.RIGHT){
            if(player.getX()<Window.getWidth()-220){
                player.setX(player.getX()+speed);
            }
            System.out.println(player.getY());
        }
        if(code == KeyCode.E){
            // Add functionality to pick up items
            System.out.println("Pick up");
        }
        if(code== KeyCode.ESCAPE){
            goToMenu(e);
        }
        //Dunno how these if statements function
        if (code == KeyCode.SPACE)
            System.out.println("space");
        if (code == KeyCode.SHIFT)
            System.out.println("Shift");
        if (code == KeyCode.CONTROL)
            System.out.println("Ctrl");
    }

     */
}
