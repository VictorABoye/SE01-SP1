package worldofzuul;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Parser 
{
    private Scanner reader;

    public Parser() 
    {
        reader = new Scanner(System.in);
    }

    public String parseString(String string){
        StringTokenizer tokenizer = new StringTokenizer(string, "\n");
        ArrayList<String> temp = new ArrayList<>();
        while(tokenizer.hasMoreTokens()){
            temp.add(tokenizer.nextToken());
        }
        String str = new String();
        for(String st: temp){
            //str += "\n"+ st;
            str += st + "\n";
        }
        return str;
    }
}
