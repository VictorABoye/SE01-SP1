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

    public int checkChoice(int x){
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

    // Checks if the quest has a choice Weight, in case it does it will return true otherwise false. (Can be used before function getChoiceWeight to be sure the quest has ChoiceWeight)
    public boolean hasChoiceWeight() {
        return !choiceWeight.isEmpty();
    }

    //Returns Choice Weight based on the choice entered.
    public int getChoiceWeight(String choice){
        return choiceWeight.get(choice);
    }

    //Return True if the answer has Weight of 1 (the best answer)
    public boolean isBestAnswer(String answer){
        if (choiceWeight.get(answer) == 5)
            return true;
        return false;
    }

    public void showChoices(){
        System.out.println("You have the following options: ");
        int i = 1;
        for(String choice: choices){
            System.out.println(i + " " + choice);
            i++;
        }
        System.out.println("Write 'choose' and a number to choose an option");
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
