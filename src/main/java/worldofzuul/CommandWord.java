package worldofzuul;

public enum CommandWord
{
    GO("go"), QUIT("quit"), HELP("help"), SORT("sort"), PICKUP("take"),
    INVENTORY("bag"), PLACE("drop"), SCORE("score"), CHOOSE("choose"),
    ROOMINVENTORY("room") UNKNOWN("?");
    
    private String commandString;
    
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    public String toString()
    {
        return commandString;
    }
}
