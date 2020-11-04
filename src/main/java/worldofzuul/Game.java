package worldofzuul;

import dk.sdu.mmmi.t3.g1.Item;
import dk.sdu.mmmi.t3.g1.Player;
import dk.sdu.mmmi.t3.g1.Quests;

import java.util.ArrayList;
import java.util.HashMap;

public class Game
{
    private Parser parser;
    private Room currentRoom;
    private Player player;
    private Quests currentQuest;

    public Game() 
    {
        createRooms();
        createQuests();
        parser = new Parser();
        player = new Player();
    }


    private void createRooms()
    {
        Room house, park, shop, road, parking, beach, recycling;
        Item can, cup, paperbag;
      
        house = new Room("at your home");
        park = new Room("in a park");
        shop = new Room("in Netto");
        road = new Room("on the road");
        parking = new Room("at the parking lot");
        beach = new Room("at the beach");
        recycling = new Room("at the recycling plant");

        can = new Item("can", "metal");
        cup = new Item("cup", "plasitc");
        paperbag = new Item("paperbag", "paper");

        house.setExit("north", parking);
        house.addItemToRoom(can);
        house.addItemToRoom(cup);

        park.setExit("south", parking);
        park.addItemToRoom(paperbag);

        shop.setExit("south", road);

        road.setExit("north", shop);
        road.setExit("east", parking);
        road.setExit("west", recycling);

        parking.setExit("west", road);
        parking.setExit("south", house);
        parking.setExit("north", park);
        parking.setExit("east", beach);

        beach.setExit("west", parking);

        recycling.setExit("east", road);

        currentRoom = house;
    }

    private void createQuests(){
        Quests breakfast, transport, road;

        breakfast = new Quests(new ArrayList<>(), new HashMap<>());

        breakfast.addChoice("Pizza");
        breakfast.addChoice("Netto");
        breakfast.setChoiceWeight("Netto", 0);
        breakfast.setChoiceWeight("Pizza", -10);


        // Creating new quest, "Transport", currentRoom is parking
        transport = new Quests(new ArrayList<>(), new HashMap<>());
        transport.addChoice("Car");
        transport.addChoice("Bike");
        transport.addChoice("Walk");
        transport.addChoice("City bus");
        transport.addChoice("Metro/Tram/Train");
        transport.setChoiceWeight("Car", 1);
        transport.setChoiceWeight("Bike",1 );
        transport.setChoiceWeight("Walk", 1);
        transport.setChoiceWeight("City Bus", 1);
        transport.setChoiceWeight("Metro/tram/Train",1 );

        // Creating a new quest, "Route to Netto", currentRoom is road

        road = new Quests(new ArrayList<>(), new HashMap<>());
        road.addChoice("Do you want to stop and pick it up?");
        road.addChoice("Continue your route without stopping");
        road.setChoiceWeight("Do you want to stop and pick it up?", 10);
        road.setChoiceWeight("Continue your route without stopping", -10);


        breakfast.setNextQuest(transport);
        transport.setNextQuest(road);
        currentQuest = breakfast;
    }

    public void play() 
    {            
        printWelcome();

                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Goodbye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Cool!");
        System.out.println("World of Cool is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        else if (commandWord == CommandWord.PICKUP){
            //Pick up method from player instance
            itemRoomToPlayer(command);
        }
        else if (commandWord == CommandWord.SORT){
            if(!currentRoom.getShortDescription().equals("at the recycling plant")){
                System.out.println("You are not at the recycling plant, so you can't sort items.");
            }
            else{
                player.sort(command.getSecondWord(), command.getThirdWord());
            }
        }
        else if (commandWord == CommandWord.INVENTORY){
            player.showInventory();
        }
        else if (commandWord == CommandWord.PLACE){
            //Place item method from player instance
            itemPlayerToRoom(command);
        }
        else if (commandWord == CommandWord.CHOOSE){
            try{
                try {
                    int value = currentQuest.checkChoice(Integer.parseInt(command.getSecondWord()));
                    player.incKlimaindsats(value);
                    if(value!=0) {
                        currentQuest = currentQuest.getNextQuest();
                    }
                }catch (NullPointerException e){
                    System.out.println("You have completed all the quests!");
                }
            }catch (NumberFormatException e){
                System.out.println("Choose the number corresponding to the option");
            }


        }
        else if (commandWord == CommandWord.SCORE){
            player.CheckKlimaindsats();
        }
        else if (commandWord == commandWord.ROOMINVENTORY){
            currentRoom.showInventory();
        }
        return wantToQuit;
    }

    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void itemRoomToPlayer(Command command){
        if (!command.hasSecondWord()){
            System.out.println("What item?");
            return;
        }

        String itemName = command.getSecondWord();

        Item movingItem = currentRoom.getItem(itemName);

        if (movingItem == null){
            System.out.println("There is no " + itemName);
        }
        else {
            player.pickUp(movingItem);
            currentRoom.removeItemFromRoom(movingItem);
        }
    }

    private void itemPlayerToRoom(Command command){
        if (!command.hasSecondWord()){
            System.out.println("What item?");
            return;
        }

        String itemName = command.getSecondWord();

        Item movingItem = player.getItem(itemName);

        if (movingItem == null){
            System.out.println("You cannot drop " + itemName);
        }
        else {
            player.place(movingItem);
            currentRoom.addItemToRoom(movingItem);
        }
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else if(currentRoom.getShortDescription().equals("at your home")){
            currentRoom = nextRoom;
            System.out.println("You can choose between your car, bicycle and walking");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
}
