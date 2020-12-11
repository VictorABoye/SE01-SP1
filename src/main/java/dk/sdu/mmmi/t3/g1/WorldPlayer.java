package dk.sdu.mmmi.t3.g1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import worldofzuul.Game;

import java.io.IOException;

public class WorldPlayer {

    private double climateScore;
    private Inventory inventory;

    public WorldPlayer()
    {
        inventory = new Inventory();
        climateScore = 50;
    }

    public double getClimateScore() {
        return climateScore;
    }

    public void addToClimateScore(double gain){
        climateScore += gain;
        if (climateScore > 100) climateScore = 100;
        if (climateScore < 0) climateScore = 0;

        if (climateScore == 100 && Game.allQuestsCompleted()){
            Game.setFinished();
            System.out.println("You win!!!"); //End the game
        }
        if (climateScore == 0 && Game.allQuestsCompleted()){
            Game.setFinished();
            System.out.println("You lose!!!"); //End the game
        }

        if(Game.allQuestsCompleted() && !Game.itemsInRooms() && allSorted()){
            Game.setFinished();
            System.out.println("You didn't win or lose...");
        }

        if(Game.isFinished()) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/End.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setAlwaysOnTop(true);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();

            }catch ( IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public Item getItemByName(String name)
    {
        return inventory.getItem(name);
    }

    public Item getItemByIndex(int index)
    {
        return inventory.getItem(index);
    }

    public Inventory getInventory()
    {
        return inventory;
    }

    public void addItem(Item item)
    {
        inventory.addItem(item);
    }

    private boolean allSorted(){
        for(Item item: inventory.items){
            if(!item.isSorted()){
                return false;
            }
        }
        return true;
    }
}
