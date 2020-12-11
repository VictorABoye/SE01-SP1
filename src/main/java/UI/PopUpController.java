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
import java.util.ArrayList;
import java.util.ResourceBundle;
import worldofzuul.Game;

public class PopUpController{

    @FXML
    public Text questInfo;

    @FXML
    public Button choice1, choice2, choice3, choice4;

    private int padding = 10;

    @FXML
    public void initialize() {
        questInfo.setText(Game.getCurrentRoom().getQuest().getDescription());
        ArrayList<String> choices = Game.getCurrentRoom().getQuest().showChoices();
        int amount = choices.size();
        if(amount==2){
            choice1.setText(choices.get(0));
            choice2.setVisible(false);
            choice3.setText(choices.get(1));
            choice4.setVisible(false);
        }
        if(amount==3){
            choice1.setText(choices.get(0));
            choice2.setText(choices.get(1));
            choice3.setText(choices.get(2));
            choice4.setVisible(false);
        }
        if(amount==4){
            choice1.setText(choices.get(0));
            choice2.setText(choices.get(1));
            choice3.setText(choices.get(2));
            choice4.setText(choices.get(3));
        }

    }


    public void getChoice(ActionEvent event){
        //Probably a better way to find consequnce for the button pressed..
        int size = Game.getCurrentRoom().getQuest().showChoices().size();
        System.out.println(((Button) event.getSource()).getId());
        if(((Button) event.getSource()).getId().equals("choice1") && size == 2){
            size--;
        }
        if(((Button) event.getSource()).getId().equals("choice1") && size == 4){
            size-= 3;
        }
        if(((Button) event.getSource()).getId().equals("choice2") && size == 4){
            size-= 2;
        }
        if(((Button) event.getSource()).getId().equals("choice3") && size == 4){
            size--;
        }
        Game.getCurrentRoom().getQuest().setCompleted();
        Game.getWorldPlayer().addToClimateScore(Game.getCurrentRoom().getQuest().getChoiceWeight(((Button) event.getSource()).getText()));
        questInfo.setText(Game.getCurrentRoom().getQuest().getConsequence(size));
        choice1.setOnAction(this::closePopUp);
        choice1.setText("Close window");
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);
    }

    public void closePopUp(ActionEvent event){
        Stage cStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        cStage.close();
    }
}