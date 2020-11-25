package dk.sdu.mmmi.t3.g1;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Moving extends AnchorPane implements EventHandler {

    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;
    private boolean spacebar = false;
    private boolean shift = false;
    private boolean ctrl = false;

    private double mouseX;
    private double mouseY;

    public Moving(){

        System.out.println("...");
        setOnKeyTyped(this);
        setOnKeyReleased(this);

        setOnMouseMoved(this);
        setOnMouseClicked(this);
        setOnMouseDragged(this);

        System.out.println(getOnKeyPressed());
    }

    @Override
    public void handle(Event event) {
        //System.out.println(event.getEventType().toString());

        if(event instanceof KeyEvent) {
            if(event.getEventType().toString() == "KEY_PRESSED") {
                keyPressed((KeyEvent) event);
            }
            if(event.getEventType().toString() == "KEY_RELEASED") {
                keyReleased((KeyEvent) event);
            }

            //System.out.println(((KeyEvent) event).getSource());
        }
        if(event instanceof MouseEvent) {
            if(event.getSource() == getOnMouseMoved()) {
                mouseMoved((MouseEvent) event);
            }

            //System.out.println("mouse at x: " + mouseX + " y: " + mouseY);
        }
    }

    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public void keyPressed(KeyEvent e) {
        KeyCode code = e.getCode();

        if (code == KeyCode.W)
            System.out.println("Up");
            up = true;
        if (code == KeyCode.S)
            System.out.println("Down");
            down = true;
        if (code == KeyCode.A)
            System.out.println("Left");
            left = true;
        if (code == KeyCode.D)
            System.out.println("Right");
            right = true;
        if (code == KeyCode.SPACE)
            System.out.println("space");
            spacebar = true;
        if (code == KeyCode.SHIFT)
            System.out.println("Shift");
            shift = true;
        if (code == KeyCode.CONTROL)
            System.out.println("Ctrl");
            ctrl = true;
    }

    public void keyReleased(KeyEvent e) {
        KeyCode code = e.getCode();

        if (code == KeyCode.UP)
            up = false;
        if (code == KeyCode.DOWN)
            down = false;
        if (code == KeyCode.LEFT)
            left = false;
        if (code == KeyCode.RIGHT)
            right = false;
        if (code == KeyCode.SPACE)
            spacebar = false;
        if (code == KeyCode.SHIFT)
            shift = false;
        if (code == KeyCode.CONTROL)
            ctrl = false;
    }


}
