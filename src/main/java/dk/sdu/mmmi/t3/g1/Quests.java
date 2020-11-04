package dk.sdu.mmmi.t3.g1;

import java.util.ArrayList;
import java.util.HashMap;

public class Quests {
    protected ArrayList<String> choices;
    private HashMap<String, Integer> choiceWeight;

    public Quests(ArrayList choices, HashMap correctChoices){
        this.choices = choices;
        this.choiceWeight = correctChoices;
    }

    public int checkChoice(int x){
        if(choiceWeight.containsKey(choices.get(x))){
            return choiceWeight.get(choices.get(x));
        }
        return 0;
    }

    public void addChoice(String choice){
        this.choices.add(choice);
    }

    public void setChoiceWeight(String choice, Integer amount) {
        choiceWeight.put(choice, amount);
    }
}
