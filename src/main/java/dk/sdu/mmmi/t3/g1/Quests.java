package dk.sdu.mmmi.t3.g1;

import java.util.ArrayList;
import java.util.HashMap;

public class Quests {
    protected ArrayList<String> choices;
    private ArrayList<String> consequence;
    private HashMap<String, Integer> choiceWeight;
    private Quests nextQuest;
    private String description;
    private boolean isCompleted;

    public Quests(ArrayList choices, HashMap correctChoices, String description){
        this.choices = choices;
        this.choiceWeight = correctChoices;
        this.description = description;
        isCompleted = false;
    }

    public int checkChoice(int x){
        while (true){
            try {
                if (choiceWeight.containsKey(choices.get(x - 1))) {
                    System.out.println("You chose " + choices.get(x - 1));
                    return choiceWeight.get(choices.get(x - 1));
                }
                return 0;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("You don't have that many choices");
                return 0;
            }
        }
    }

    public boolean getCompleted(){
        return isCompleted;
    }

    public void setCompleted(){
        isCompleted = true;
    }

    public void addChoice(String choice){
        this.choices.add(choice);
    }

    public void setChoiceWeight(String choice, Integer amount) {
        choiceWeight.put(choice, amount);
    }

    public void showChoices(){
        System.out.println("You have the following options: ");
        int i = 1;
        for(String choice: choices){
            System.out.println(i + " " + choice);
            i++;
        }
    }

    public Quests getNextQuest() {
        return nextQuest;
    }

    public void setNextQuest(Quests nextQuest) {
        this.nextQuest = nextQuest;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConsequence(int i) {
        return consequence.get(i-1);
    }

    public void setConsequence(String consequence) {
        this.consequence.add(consequence);
    }
}
