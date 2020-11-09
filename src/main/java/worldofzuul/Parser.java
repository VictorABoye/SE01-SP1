package worldofzuul;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Parser 
{
    private CommandWords commands;
    private Scanner reader;

    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    public Command getCommand() 
    {
        String inputLine;
        String word1 = null;
        String word2 = null;
        String word3 = null;

        System.out.print("> "); 

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();
                if(tokenizer.hasNext()) {
                    word3 = tokenizer.next();
                }
            }
        }

        return new Command(commands.getCommandWord(word1), word2, word3);
    }

    public void showCommands()
    {
        commands.showAll();
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
