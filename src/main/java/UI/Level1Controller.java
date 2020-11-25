package UI;

import dk.sdu.mmmi.t3.g1.Moving;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


import java.io.IOException;

public class Level1Controller{

    final String launchFile = "/fxml/Launcher.fxml";
    private int speed = 15;

    @FXML
    public Button backButton;

    @FXML
    private AnchorPane Window;

    @FXML
    private Rectangle Player;

    @FXML
    public void goToMenu(ActionEvent actionEvent) {
        try {
            Parent launcher = FXMLLoader.load(getClass().getResource(launchFile));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(launcher));
            stage.show();
        } catch (IOException e){
            System.out.println("Cannot find fxml file");
        }
    }

    public void move(Event event){
        if(event instanceof KeyEvent) {
            if(event.getEventType().toString() == "KEY_PRESSED") {
                keyPressed((KeyEvent) event);
            }

            System.out.println(event);
        }
    }

    public void keyPressed(KeyEvent e) {
        KeyCode code = e.getCode();

        //System.out.println(e);

        //Fix all this
        if (code == KeyCode.W){
            if(Player.getY()>-1*(Window.getHeight())+260){
                Player.setY(Player.getY()-speed);
            }
        }
        if (code == KeyCode.S){
            if(Player.getY()<Window.getHeight()-700){
                Player.setY(Player.getY()+speed);
            }
        }
        if(code == KeyCode.DOWN){
            System.out.println("Test");
        }
        if (code == KeyCode.A || code == KeyCode.LEFT) {
            if (Player.getX() > 0) {
                Player.setX(Player.getX() - speed);
            }
        }
        if (code == KeyCode.D || code == KeyCode.RIGHT){
            if(Player.getX()<Window.getWidth()-220){
                Player.setX(Player.getX()+speed);
            }
        }
        if (code == KeyCode.SPACE)
            System.out.println("space");
        if (code == KeyCode.SHIFT)
            System.out.println("Shift");
        if (code == KeyCode.CONTROL)
            System.out.println("Ctrl");
    }
/*
    @Override
    public void handle(Event event) {
        System.out.println(event.getEventType().toString());

        if(event instanceof KeyEvent) {
            if(event.getSource() == getOnKeyPressed()) {
                keyPressed((KeyEvent) event);
            }
            if(event.getSource() == getOnKeyReleased()) {
                keyReleased((KeyEvent) event);
            }

            System.out.println(((KeyEvent) event).getCode());
        }
        if(event instanceof MouseEvent) {
            if(event.getSource() == getOnMouseMoved()) {
                mouseMoved((MouseEvent) event);
            }

            System.out.println("mouse at x: " + mouseX + " y: " + mouseY);
        }
    }

 */
}
