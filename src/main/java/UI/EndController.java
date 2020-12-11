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
    public Button closeGameButton;

    public String checkEndScore(){
        double endScore = Game.getWorldPlayer().getClimateScore();
        String endString  = "default";
        if (endScore >= 80){
            endString = "Congratulations, you win!\n" +
                    "You've picked the right options and in doing so, you are helping the world stop climate change.\n" +
                    "We hope that you might have learned something along the way and that you continue\nchoosing the climate friendly options real life.\n" +
                    "Thanks for playing!\nYour score was " + endScore;
        }else if(endScore < 80 && endScore > 20) {
            endString = "Not bad, not great...\n" +
                    "You've done about as much good as you have bad. We hope that you have read the consequences of your choices\n" +
                    "and learned why they might have been the wrong choice.\n" +
                    "You might want to try the game again to see if you can improve.\n" +
                    "Thanks for playing!\nYour score was " + endScore;
        }
        else{
            endString = "You lost!\n" +
                    "Remember to read the consequences that popup after you have chosen an action.\n" +
                    "Picking the right choice will increase your score and if you choose the right enough times, you win!\n" +
                    "We hope that you have learned something from this game\nwhich might make you choose more climate friendly options in real life as well.\n" +
                    "Thanks for playing!\nYour score was " + endScore;

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
