package dk.sdu.mmmi.t3.g1;

import java.util.ArrayList;
import java.util.HashMap;

public class Quests {
    protected ArrayList<String> choices;
    private HashMap<String, Integer> choiceWeight;
    private Quests nextQuest;

    public Quests(ArrayList choices, HashMap correctChoices){
        this.choices = choices;
        this.choiceWeight = correctChoices;
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

    public void addChoice(String choice){
        this.choices.add(choice);
    }

    public void setChoiceWeight(String choice, Integer amount) {
        choiceWeight.put(choice, amount);
    }


    public Quests getNextQuest() {
        return nextQuest;
    }

    public void setNextQuest(Quests nextQuest) {
        this.nextQuest = nextQuest;
    }
}
