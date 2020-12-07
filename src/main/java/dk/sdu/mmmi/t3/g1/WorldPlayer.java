package dk.sdu.mmmi.t3.g1;

import worldofzuul.Game;

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

        if (climateScore == 100){
            Game.setFinished();
            System.out.println("You win!!!"); //End the game
        }
        if (climateScore == 0){
            Game.setFinished();
            System.out.println("You lose!!!"); //End the game
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
}
