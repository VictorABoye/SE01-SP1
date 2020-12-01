package UI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import worldofzuul.Game;

import java.net.URL;
import java.util.ResourceBundle;

public class PopUpController{

    @FXML
    public Text questInfo;

    @FXML
    public Button choice1, choice2, choice3, choice4;

    @FXML
    public void initialize() {
        questInfo.setText("This is the newest text");
        choice1.setText("This is choice 1");
        choice2.setText("This is choice 2");
        choice3.setText("This is choice 3");
        choice4.setText("This is choice 4");

    }


    public void getChoice(ActionEvent event){
        System.out.println("this");
        Stage cStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        cStage.close();
    }



    /*
    If button pushed, give value for choice.
     */
}
