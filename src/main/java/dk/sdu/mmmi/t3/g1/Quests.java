package dk.sdu.mmmi.t3.g1;

import java.util.ArrayList;
import java.util.HashMap;

public class Quests {
    protected ArrayList<String> choices;
    private final ArrayList<String> consequence = new ArrayList<>();
    private final HashMap<String, Integer> choiceWeight;
    private final String description;
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
        arr.addAll(choices);
        return arr;
    }

    public String getDescription() {
        return description;
    }

    public String getConsequence(int i) {
        return consequence.get(i-1);
    }
}
