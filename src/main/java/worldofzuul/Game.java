package worldofzuul;

import dk.sdu.mmmi.t3.g1.Item;
import dk.sdu.mmmi.t3.g1.NonFoodItem;
import dk.sdu.mmmi.t3.g1.Player;
import dk.sdu.mmmi.t3.g1.Quests;

import javax.sound.midi.SysexMessage;
import java.awt.*;
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
        parser = new Parser();
        player = new Player();
        createRooms();
        createQuests();

    }


    private void createRooms()
    {
        Room house, park, shop, road, parking, beach, recycling;
        NonFoodItem can, cup, paperbag;
      
        house = new Room("at your home","info");
        park = new Room("in a park","info");
        shop = new Room("in Netto","info");
        road = new Room("on the road","info");
        parking = new Room("at the parking lot","info");
        beach = new Room("at the beach","info");
        recycling = new Room("at the recycling plant","info");

        can = new NonFoodItem("can", "metal");
        cup = new NonFoodItem("cup", "plastic");
        paperbag = new NonFoodItem("paperbag", "paper");

        house.setExit("north", parking);
        house.addItemToRoom(can);
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
        // For at teste sorting tingeling

        player.pickUp(can);
        player.pickUp(can);
        player.pickUp(can);
        player.pickUp(cup);
        player.pickUp(cup);
        player.pickUp(paperbag);


        currentRoom = house; //Starting
    }

    private void createQuests(){
        Quests breakfast, transport, road, groceries, recycling, factory, quiz;;

        breakfast = new Quests(new ArrayList<>(), new HashMap<>(), "You wake up and are feeling hungry");
        breakfast.setConsequence("");
        breakfast.addChoice("Oatmeal with some fresh fruit on top");
        breakfast.addChoice("Triple-Beef Cheeseburger!");
        breakfast.setChoiceWeight("Oatmeal with some fresh fruit on top", 5);
        breakfast.setChoiceWeight("Triple-Beef Cheeseburger!", -5);


        // Creating new quest, "Transport", currentRoom is parking
        transport = new Quests(new ArrayList<>(), new HashMap<>(), "Choose the most environmental-friendly transport method");
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

        // Creating a new quest, "road", currentRoom is road

        road = new Quests(new ArrayList<>(), new HashMap<>(), "You can pick up trash or keep going");
        road.addChoice("Do you want to stop and pick it up?");
        road.addChoice("Continue your route without stopping");
        road.setChoiceWeight("Do you want to stop and pick it up?", 10);
        road.setChoiceWeight("Continue your route without stopping", -10);

        // Creating a new quest, "Groceries"

        groceries = new Quests(new ArrayList<>(), new HashMap<>(), "Choose the groceries, which you desire");
        groceries.setDescription("Did you know that what you eat have an affect on climate change? It does! About one-quarter of the planet-warming greenhouse gases are generated from raising and harvesting plants, animals and animal products we eat - beef, chicken, fish and so on... But, some food categories requires more ressources to make, which hurts the climate.");
        //groceries.addChoice("");
        //groceries.addChoice("");
        //groceries.setChoiceWeight();
        //groceries.setChoiceWeight();

        // Creating a new quest, "recycling"
        recycling = new Quests(new ArrayList<>(), new HashMap<>(), "You've collected trash throughout your journey. Time to sort it!");


        // Creating a new quest, "factory"
        factory = new Quests(new ArrayList<>(), new HashMap<>(), "A local factory pours nuclear waste into the sea. Time to make a choice.");
        factory.addChoice("Report it to the authorities");
        factory.addChoice("Ignore it");
        factory.setChoiceWeight("Report it to the authorities", 10);
        factory.setChoiceWeight("Ignore it", -10);

        //Creating a last quest, quiz
        //quiz...

        //Passing on to next quest
        breakfast.setNextQuest(transport);
        transport.setNextQuest(road);
        road.setNextQuest(groceries);
        groceries.setNextQuest(recycling);
        recycling.setNextQuest(factory);

        currentQuest = breakfast; //starting quest
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
        //Could be: "World of Cool" is new a game, which can improve the general knowledge of the climate issues and the actions a person, as an individual, can do about the climate change.         System.out.println("World of Cool is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getInfoBox());
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
                if(player.sort(command.getSecondWord(), command.getThirdWord())){
                    if (currentQuest.getDescription().equals("You've collected trash throughout your journey. Time to sort it!")){
                        currentQuest = currentQuest.getNextQuest();
                    }
                }
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
                    currentQuest.getConsequence(value);
                    if(value!=0) {
                        currentQuest = currentQuest.getNextQuest();
                    }
                }catch (NullPointerException e){
                    System.out.println("You have completed all the quests!");
                }
            }catch (NumberFormatException e){
                System.out.println("Choose the number corresponding to the option");
            }
            //Input from user. When the user starts next quest, the new "answer" will be entered. Scanner is needed.
            String tempAnswer = "Car";
            //

            if (currentQuest.hasChoiceWeight() ){

                if (currentQuest.isBestAnswer(tempAnswer)){
                    System.out.println("Yes. You've taken the right choice!");
                }
                else if (currentQuest.getChoiceWeight(tempAnswer) == 2) {
                    System.out.println("Not bad but there's a better choice");
                }
                else {
                    System.out.println("Worst choice....");
                }
            }
            
        }
        else if (commandWord == CommandWord.SCORE){
            player.checkKlimaindsats();
        }
        else if (commandWord == commandWord.ROOMINVENTORY){
            System.out.println(currentRoom.getLongDescription());
            currentRoom.showInventory();
        }
        else if (commandWord == CommandWord.QUEST){
            //Display current quest
            System.out.println(currentQuest.getDescription());
            // and options
            currentQuest.showChoices();

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
        String items = command.getThirdWord();

        if (itemName.equals("all")){
            if (items == null) {
                for (int i = currentRoom.getRoomInventorySize() - 1; i >= 0; i--) {
                    Item movingItem = currentRoom.getItem(i);
                    player.pickUp(movingItem);
                    currentRoom.removeItemFromRoom(movingItem);
                }
                System.out.println("You took all the item");
            }
            else {
                if (currentRoom.hasItem(items)) {
                    for (int i = currentRoom.getRoomInventorySize() - 1; i >= 0; i--) {
                        Item movingItem = currentRoom.getItem(i);
                        if (movingItem.getName().equals(items)) {
                            player.pickUp(movingItem);
                            currentRoom.removeItemFromRoom(movingItem);
                        }
                    }
                    System.out.println("You took all the " + items + "s");
                }
                else {
                    System.out.println("There are no " + items + "s");
                }
            }
            return;
        }

        Item movingItem = currentRoom.getItem(itemName);

        if (movingItem == null){
            System.out.println("There is no " + itemName);
        }
        else {
            player.pickUp(movingItem);
            currentRoom.removeItemFromRoom(movingItem);
            System.out.println("You picked up " + movingItem.getName());
        }
    }

    private void itemPlayerToRoom(Command command){
        if (!command.hasSecondWord()){
            System.out.println("What item?");
            return;
        }

        String itemName = command.getSecondWord();
        String items = command.getThirdWord();

        if (itemName.equals("all")){
            if (items == null) {
                for (int i = player.getPlayerInventorySize() - 1; i >= 0; i--) {
                    Item movingItem = player.getItem(i);
                    player.place(movingItem);
                    currentRoom.addItemToRoom(movingItem);
                }
                System.out.println("You dropped all your items");
            }
            else {
                if (player.hasItem(items)) {
                    for (int i = player.getPlayerInventorySize() - 1; i >= 0; i--) {
                        Item movingItem = player.getItem(i);
                        if (movingItem.getName().equals(items)) {
                            player.place(movingItem);
                            currentRoom.addItemToRoom(movingItem);
                        }
                    }
                    System.out.println("You dropped all your " + items + "s");
                }
                else {
                    System.out.println("You do not have any " + items + "s");
                }
            }
            return;
        }

        Item movingItem = player.getItem(itemName);

        if (movingItem == null){
            System.out.println("You cannot drop " + itemName);
        }
        else {
            player.place(movingItem);
            currentRoom.addItemToRoom(movingItem);
            System.out.println("You dropped " + movingItem.getName());
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
            if(!currentRoom.getVisited()){
                System.out.println(currentRoom.getInfoBox());
            }
            currentRoom.setVisited();
            System.out.println("You can choose between your car, bicycle and walking");
        }
        else {
            currentRoom = nextRoom;
            if(!currentRoom.getVisited()){
                System.out.println(currentRoom.getInfoBox());
            }
            currentRoom.setVisited();
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
