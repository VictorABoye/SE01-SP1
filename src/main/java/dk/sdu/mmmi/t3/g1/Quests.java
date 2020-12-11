package dk.sdu.mmmi.t3.g1;

import java.util.ArrayList;
import java.util.HashMap;

public class Quests {
    protected ArrayList<String> choices;
    private ArrayList<String> consequence = new ArrayList<>();
    private HashMap<String, Integer> choiceWeight;
    private Quests nextQuest;
    private String description;
    private boolean isCompleted;

    public Quests(ArrayList<String> choices, HashMap<String, Integer> correctChoices, String description){
        this.choices = choices;
        this.choiceWeight = correctChoices;
        this.description = description;
        isCompleted = false;
    }

    public boolean getCompleted(){
        return isCompleted;
    }

    public void setCompleted(){
        isCompleted = true;
    }

    public void addChoice(String choice, Integer amount, String consequence){
        this.choices.add(choice);
        choiceWeight.put(choice, amount);
        this.consequence.add(consequence);
    }

    //Returns Choice Weight based on the choice entered.
    public int getChoiceWeight(String choice){
        return choiceWeight.get(choice);
    }

    public ArrayList<String> showChoices(){
        ArrayList<String> arr = new ArrayList<>();
        for(String choice: choices){
            arr.add(choice);
        }
        return arr;
    }

    public void setNextQuest(Quests nextQuest) {
        this.nextQuest = nextQuest;
    }

    public String getDescription() {
        return description;
    }

    public String getConsequence(int i) {
        return consequence.get(i-1);
    }
}
