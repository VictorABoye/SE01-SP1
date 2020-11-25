package dk.sdu.mmmi.t3.g1;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Data {

    public ArrayList<String> questString(String string) throws IOException, ParseException {
        //Opens JSON file
        Object obj = new JSONParser().parse(new FileReader("Quests.json"));
        //Casts to JSONObject in order to use it as JSON
        JSONObject Strings = (JSONObject) obj;
        //Create temporary array to store strings in before adding to questStrings array
        ArrayList<String> arr = new ArrayList<>();
        //Strings are saved under each quest, creating a new JSON object for the quest
        JSONObject object = (JSONObject) Strings.get(string);
        arr.add((String) object.get("description"));
        //Choices are saved as JSON arrays, creating iterator to add whole array to temp array
        JSONArray choices = (JSONArray) object.get("choices");
        Iterator itr1 = choices.iterator();
        while (itr1.hasNext()){
            arr.add((String)itr1.next());
        }
        //Same as choices
        JSONArray consequences = (JSONArray) object.get("consequences");
        Iterator itr2 = consequences.iterator();
        while (itr2.hasNext()){
            arr.add((String)itr2.next());
        }
        //Adding temporary array to questStrings to access later
        return arr;
    }

}
