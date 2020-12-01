package UI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import worldofzuul.Game;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class PopUpController implements Initializable{

    @FXML
    public Text questInfo;

    @FXML
    public Button choice1, choice2, choice3, choice4;


    public void getChoice(ActionEvent event){
        System.out.println("this");
        Stage cStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        cStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        questInfo.setText("This is the newest text");
        choice1.setText("This is choice 1");
        choice2.setText("This is choice 2");
        choice3.setText("This is choice 3");
        choice4.setText("This is choice 4");

    }





    /*
    If button pushed, give value for choice.
     */
}
