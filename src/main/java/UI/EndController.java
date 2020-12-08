package UI;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import worldofzuul.Game;

public class EndController {

    @FXML
    public Text endText;

    @FXML
    public Button closeGameButton;

    public String checkEndScore(){
        double endScore = Game.getWorldPlayer().getClimateScore();
        String endString  = "default";
        if (endScore == 100){
            endString = "Gz bro, very nice!";
        }else{
            endString = "Rip dig alligevel :( ";

        }
        return endString;
    }
    public void initialize(){
        endText.setText(checkEndScore());
    }


    public void closeGame(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(3);

    }

}
