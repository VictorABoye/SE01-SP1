package worldofzuul;

import dk.sdu.mmmi.t3.g1.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Game{

    private static WorldPlayer player;
    private static Room currentRoom;

    public Game(){
        player = new WorldPlayer();
        createWorld();
    }

    public static Room getCurrentRoom() {
        return currentRoom;
    }

    private void createWorld() {
        //Create all the Rooms


        try {
            //FXML File
            String FXML = "/fxml/Level1.fxml";

            //Loader
            Parent window = FXMLLoader.load(getClass().getResource(FXML));

            //Create Items
            Item can1 = new NonFoodItem((ImageView) window.lookup("#itemCan1"),"item","can");
            Item can2 = new NonFoodItem((ImageView) window.lookup("#itemCan2"),"item","can");

            //Create Teleport
            Teleport tpNorth = new Teleport((ImageView) window.lookup("#tpNorth"),"teleport","/fxml/Level2.fxml");

            //Create Room
            Room level1 = new Room("info",new Quests(new ArrayList<>(), new HashMap<>(), "String"));
            level1.addItemToRoom(can1);
            level1.addItemToRoom(can2);
            level1.addTeleporterToRoom(tpNorth);

            //Starting Room
            currentRoom = level1;

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void play() {
        System.out.println("Play the game");
    }

    public static WorldPlayer getWorldPlayer()
    {
        return player;
    }


    public static boolean playerCollidesItem(Player player){
        //Room currentRoom = event.getSource();
        //Item currentItem = currentRoom.getItem(item);
        //Player player = currentRoom.getPlayer();
        Item currentItem = getClosestItemToPlayer(player);
        if (currentItem != null) {
            //Player player = new Player(((ImageView)((Scene)event.getSource()).lookup("#player")),"player","@../images/obama.png");
            double ix1 = currentItem.getX();
            double iy1 = currentItem.getY();
            double ix2 = currentItem.getW() + ix1;
            double iy2 = currentItem.getH() + iy1;
            double px1 = player.getX();
            double py1 = player.getY();
            double px2 = player.getW() + px1;
            double py2 = player.getH() + py1;
            if (px2 >= ix1 && py1 <= iy2 && !(px1 >= ix2) && !(py2 <= iy1)) {
                currentItem.getImageView().setVisible(false);
                return true;
            }
        }
        return false;
    }

    public static Item getClosestItemToPlayer(Player player){
        if (currentRoom.getRoomInventorySize() <= 0) return null;
        Item currentItem;
        double shortestDist = 999999;
        int itemIndex = 0;

            for (int i = 0; i < currentRoom.getRoomInventorySize(); i++) {
                currentItem = currentRoom.getItem(i);
                //Player on screen
                double px1 = player.getX();
                double px2 = player.getW() + px1;
                double py1 = player.getY();
                double py2 = player.getH() + py1;
                double pmx = px2 / 2.0;
                double pmy = py2 / 2.0;
                System.out.println("Player X: " + pmx + " Y: " + pmy);
                //Item on screen
                double ix1 = currentItem.getX();
                double ix2 = currentItem.getW() + ix1;
                double iy1 = currentItem.getY();
                double iy2 = currentItem.getH() + iy1;
                double imx = ix2 / 2.0;
                double imy = iy2 / 2.0;
                System.out.println("Item X: " + imx + " Y: " + imy);
                //Distance between player and item
                double mpx = Math.abs(pmx - imx);
                double mpy = Math.abs(pmy - imy);
                double dist = Math.sqrt((mpx+mpy)*(mpx+mpy));

                if (dist < shortestDist){
                    shortestDist = dist;
                    itemIndex = i;
                }
                System.out.println(shortestDist);
            }
        return currentRoom.getItem(itemIndex);
    }

    public static boolean playerCollidesTeleport(Player player){
        Teleport currentTP = getClosestTeleporterToPlayer(player);
        if (currentTP != null) {
            //Player player = new Player(((ImageView)((Scene)event.getSource()).lookup("#player")),"player","@../images/obama.png");
            double ix1 = currentTP.getX();
            double iy1 = currentTP.getY();
            double ix2 = currentTP.getW() + ix1;
            double iy2 = currentTP.getH() + iy1;
            double px1 = player.getX();
            double py1 = player.getY();
            double px2 = player.getW() + px1;
            double py2 = player.getH() + py1;
            if (px2 >= ix1 && py1 <= iy2 && !(px1 >= ix2) && !(py2 <= iy1)) {
                currentTP.getImageView().setVisible(false);
                return true;
            }
        }
        return false;
    }

    public static Teleport getClosestTeleporterToPlayer(Player player){
        if (currentRoom.getAmountOfTeleports() <= 0) return null;
        Teleport currentTP;
        double shortestDist = 999999;
        int itemIndex = 0;

        for (int i = 0; i < currentRoom.getAmountOfTeleports(); i++) {
            currentTP = currentRoom.getTP(i);
            //Player on screen
            double px1 = player.getX();
            double px2 = player.getW() + px1;
            double py1 = player.getY();
            double py2 = player.getH() + py1;
            double pmx = px2 / 2.0;
            double pmy = py2 / 2.0;
            System.out.println("Player X: " + pmx + " Y: " + pmy);
            //Item on screen
            double ix1 = currentTP.getX();
            double ix2 = currentTP.getW() + ix1;
            double iy1 = currentTP.getY();
            double iy2 = currentTP.getH() + iy1;
            double imx = ix2 / 2.0;
            double imy = iy2 / 2.0;
            System.out.println("Item X: " + imx + " Y: " + imy);
            //Distance between player and item
            double mpx = Math.abs(pmx - imx);
            double mpy = Math.abs(pmy - imy);
            double dist = Math.sqrt((mpx+mpy)*(mpx+mpy));

            if (dist < shortestDist){
                shortestDist = dist;
                itemIndex = i;
            }
            System.out.println(shortestDist);
        }
        return currentRoom.getTP(itemIndex);
    }
}




/*
 *
 * Legacy Game Class
 *
 */
/*
import dk.sdu.mmmi.t3.g1.Item;
import dk.sdu.mmmi.t3.g1.NonFoodItem;
import dk.sdu.mmmi.t3.g1.Player;
import dk.sdu.mmmi.t3.g1.Quests;
import dk.sdu.mmmi.t3.g1.Data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Game
{
    private Parser parser;
    private Room currentRoom;
    private Player player;
    private Quests currentQuest;
    private ArrayList<ArrayList> questStrings = new ArrayList<>();

    public Game() throws IOException, ParseException {
        parser = new Parser();
        //player = new Player();
        createRooms();
    }

    private void createRooms() throws IOException, ParseException {
        Room house, park, shop, road, parking, beach, recycling;
        //NonFoodItem can, cup, straw, water_bottle, paper_bag, glass_bottle, battery;
        Quests breakfast, transport, roadQuest, groceries, recyclingQuest, factory, quiz, parkQuest;
        Data quests = new Data();
        questStrings.add(quests.questString("breakfast"));
        questStrings.add(quests.questString("transport"));
        questStrings.add(quests.questString("roadQuest"));
        questStrings.add(quests.questString("groceries"));
        questStrings.add(quests.questString("recyclingQuest"));
        questStrings.add(quests.questString("factory"));
        questStrings.add(quests.questString("parkQuest"));
        questStrings.add(quests.questString("quiz"));

        breakfast = new Quests(new ArrayList<>(), new HashMap<>(), parser.parseString((String)questStrings.get(0).get(0)));
        transport = new Quests(new ArrayList<>(), new HashMap<>(), parser.parseString((String)questStrings.get(1).get(0)));
        roadQuest = new Quests(new ArrayList<>(), new HashMap<>(), parser.parseString((String)questStrings.get(2).get(0)));
        groceries = new Quests(new ArrayList<>(), new HashMap<>(), parser.parseString((String)questStrings.get(3).get(0)));
        recyclingQuest = new Quests(new ArrayList<>(), new HashMap<>(), parser.parseString((String)questStrings.get(4).get(0)));
        factory = new Quests(new ArrayList<>(), new HashMap<>(), parser.parseString((String)questStrings.get(5).get(0)));
        parkQuest = new Quests(new ArrayList<>(), new HashMap<>(), parser.parseString((String)questStrings.get(6).get(0)));
        quiz = new Quests(new ArrayList<>(), new HashMap<>(), parser.parseString((String)questStrings.get(7).get(0)));
        //Breakfast quest
        breakfast.addChoice(parser.parseString((String) questStrings.get(0).get(1)),5,parser.parseString((String) questStrings.get(0).get(3)));
        breakfast.addChoice(parser.parseString((String) questStrings.get(0).get(2)),-5,parser.parseString((String) questStrings.get(0).get(4)));

        // Creating new quest, "Transport", currentRoom is parkingtransport = new Quests(new ArrayList<>(), new HashMap<>(), "Choose the most environmental-friendly transport method");
        transport.addChoice(parser.parseString((String) questStrings.get(1).get(1)), -10, parser.parseString((String) questStrings.get(1).get(5)));
        transport.addChoice(parser.parseString((String) questStrings.get(1).get(2)), -5, parser.parseString((String) questStrings.get(1).get(6)));
        transport.addChoice(parser.parseString((String) questStrings.get(1).get(3)), -5, parser.parseString((String) questStrings.get(1).get(7)));
        transport.addChoice(parser.parseString((String) questStrings.get(1).get(4)), 5, parser.parseString((String) questStrings.get(1).get(8)));


        // Creating a new quest, "road", currentRoom is road
        roadQuest.addChoice(parser.parseString((String)questStrings.get(2).get(1)),10,parser.parseString((String)questStrings.get(2).get(3)));
        roadQuest.addChoice(parser.parseString((String)questStrings.get(2).get(2)),-10,parser.parseString((String)questStrings.get(2).get(4)));

        // Creating a new quest, "Groceries"
        groceries.addChoice(parser.parseString((String)questStrings.get(3).get(1)), 10, parser.parseString((String)questStrings.get(3).get(3)));
        groceries.addChoice(parser.parseString((String)questStrings.get(3).get(2)), -10, parser.parseString((String)questStrings.get(3).get(4)) );

        recyclingQuest.addChoice(parser.parseString((String)questStrings.get(4).get(1)), 5, parser.parseString((String)questStrings.get(4).get(2)));

        // Creating a new quest, "factory"
        factory.addChoice(parser.parseString((String)questStrings.get(5).get(1)), 5, parser.parseString((String)questStrings.get(5).get(3)));
        factory.addChoice(parser.parseString((String)questStrings.get(5).get(2)), -5, parser.parseString((String)questStrings.get(5).get(4)));

        parkQuest.addChoice(parser.parseString((String)questStrings.get(6).get(1)), 5, parser.parseString((String)questStrings.get(6).get(3)) );
        parkQuest.addChoice(parser.parseString((String)questStrings.get(6).get(2)), -5, parser.parseString((String)questStrings.get(6).get(4)) );

        // Creating the final quests, "quiz". True/False and should be seperated into multiple quests?
        /* To be changed
        quiz.addChoice(parser.parseString((String)questStrings.get(7).get(1)), 5, parser.parseString((String)questStrings.get(7).get(9)) );
        quiz.addChoice(parser.parseString((String)questStrings.get(7).get(2)), 5, parser.parseString((String)questStrings.get(7).get(10)) );
        quiz.addChoice(parser.parseString((String)questStrings.get(7).get(2)), 5, parser.parseString((String)questStrings.get(7).get(11)) );
        quiz.addChoice(parser.parseString((String)questStrings.get(7).get(3)), 5, parser.parseString((String)questStrings.get(7).get(12)) );
        quiz.addChoice(parser.parseString((String)questStrings.get(7).get(4)), 5, parser.parseString((String)questStrings.get(7).get(13)) );
        quiz.addChoice(parser.parseString((String)questStrings.get(7).get(5)), 5, parser.parseString((String)questStrings.get(7).get(14)) );
        quiz.addChoice(parser.parseString((String)questStrings.get(7).get(6)), 5, parser.parseString((String)questStrings.get(7).get(15)) );
        quiz.addChoice(parser.parseString((String)questStrings.get(7).get(7)), 5, parser.parseString((String)questStrings.get(7).get(16)) );
        quiz.addChoice(parser.parseString((String)questStrings.get(7).get(8)), 5, parser.parseString((String)questStrings.get(7).get(17)) );


        //Passing on to next quest
        breakfast.setNextQuest(transport);
        transport.setNextQuest(roadQuest);
        roadQuest.setNextQuest(groceries);
        groceries.setNextQuest(recyclingQuest);
        recyclingQuest.setNextQuest(factory);


        house = new Room("at your home","info", breakfast);
        park = new Room("in a park","info", parkQuest);
        shop = new Room("in Netto","info", groceries);
        road = new Room("on the road","info", roadQuest);
        parking = new Room("at the parking lot","info", transport);
        beach = new Room("at the beach","info", factory);
        recycling = new Room("at the recycling plant","info", recyclingQuest);


        can = new NonFoodItem("can", "metal");
        cup = new NonFoodItem("cup", "plastic");
        straw = new NonFoodItem("straw", "plastic");
        water_bottle = new NonFoodItem("waterbottle", "plastic");
        paper_bag = new NonFoodItem("paperbag", "paper");
        glass_bottle = new NonFoodItem("glassbottle","glass");
        battery = new NonFoodItem("battery","battery");



        house.setExit("north", parking);

        house.addItemToRoom(battery);
        house.addItemToRoom(battery);
        house.addItemToRoom(can);


        house.setVisited();

        park.setExit("south", parking);

        park.addItemToRoom(paper_bag);
        park.addItemToRoom(water_bottle);
        park.addItemToRoom(cup);
        park.addItemToRoom(glass_bottle);



        shop.setExit("south", road);

        road.setExit("north", shop);
        road.setExit("east", parking);
        road.setExit("west", recycling);
        //road.addItemToRoom(can);

        parking.setExit("west", road);
        parking.setExit("south", house);
        parking.setExit("north", park);
        parking.setExit("east", beach);
        //parking.addItemToRoom(paper_bag);

        beach.setExit("west", parking);

        beach.addItemToRoom(can);
        beach.addItemToRoom(cup);
        beach.addItemToRoom(water_bottle);
        beach.addItemToRoom(water_bottle);
        beach.addItemToRoom(paper_bag);
        beach.addItemToRoom(straw);



        recycling.setExit("east", road);
        // For at teste sorting tingeling


        player.pickUp(can);
        player.pickUp(can);
        player.pickUp(can);
        player.pickUp(cup);
        player.pickUp(cup);
        player.pickUp(paper_bag);



        currentRoom = house; //Starting
        currentQuest = currentRoom.getQuest();
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
        System.out.println("World of Cool is an informative game, which has a purpose to inform YOU about the rising consequences coming from climate change.\nWe believe that by the end of your journey, you will know more about the climate and how to act accordingly in regards to the nature");
        System.out.println("You will be taken on a journey where the choices you take matters.\nYou'll be giving a climate score starting at 50. Make a wrong choice and it will be decreased, make the right one and it will be increased. Every choice you make is vital!");
        System.out.println();
        System.out.println("Reach a climate score of 0 and you will lose, reach 100 and you will win. Good Luck!");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help or want to your commands. Be aware that some commands might not be available in certain places.");
        System.out.println();
        printQuest();
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            System.out.println("You can type 'help' to see your options");
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
                        currentRoom.getQuest().setCompleted();
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
            if (!currentQuest.getCompleted()) {
                try {
                    try {
                        int value = currentQuest.checkChoice(Integer.parseInt(command.getSecondWord()));
                        player.incKlimaindsats(value);
                        // currentQuest.getConsequence(Integer.parseInt(command.getSecondWord()));
                        if (value != 0) {
                            currentQuest.setCompleted();
                            System.out.println(currentRoom.getExitString());
                        }
                    } catch (NullPointerException e) {
                        System.out.println("You have completed all the quests!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Choose the number corresponding to the option");
                }
            }
            else {
                System.out.println("You do not have a task");
            }
            //Input from user. When the user starts next quest, the new "answer" will be entered. Scanner is needed.
            /*
            Scanner myObj = new Scanner(System.in);
            String answer = myObj.nextLine();
            //

            if (currentQuest.hasChoiceWeight() ){

                if (currentQuest.isBestAnswer(answer)){
                    System.out.println("Yes. You've taken the right choice!");
                }
                else if (currentQuest.getChoiceWeight(answer) == 2) {
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
        else if (commandWord == CommandWord.ROOMINVENTORY){
            System.out.println(currentRoom.getLongDescription());
            currentRoom.showInventory();
        }
        else if (commandWord == CommandWord.QUEST){
            //Display current quest
            printQuest();
        }
        return wantToQuit;
    }

    private void printQuest(){
        if(!currentQuest.getCompleted()) {
            System.out.println(currentQuest.getDescription());
            // and options
            currentQuest.showChoices();
        }
        else {
            System.out.println("You do not have any task");
        }
    }

    private void printHelp() 
    {
        System.out.println("You are lost. You need help.");
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
                if (currentRoom.getRoomInventorySize() > 0) {
                    for (int i = currentRoom.getRoomInventorySize() - 1; i >= 0; i--) {
                        Item movingItem = currentRoom.getItem(i);
                        player.pickUp(movingItem);
                        currentRoom.removeItemFromRoom(movingItem);
                    }
                    System.out.println("You took all the items");
                }
                else {
                    System.out.println("There are no items");
                }
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
                if (player.getPlayerInventorySize() > 0) {
                    for (int i = player.getPlayerInventorySize() - 1; i >= 0; i--) {
                        Item movingItem = player.getItem(i);
                        player.place(movingItem);
                        currentRoom.addItemToRoom(movingItem);
                    }
                    System.out.println("You dropped all your items");
                }
                else {
                    System.out.println("You do not have any items");
                }
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
            player.place(movingItem);
            currentRoom.addItemToRoom(movingItem);
            System.out.println("You dropped " + movingItem.getName());
        }
    }

    private void goRoom(Command command) 
    {
        if(!currentRoom.getQuest().getCompleted()){
            System.out.println("You haven't completed the task in this room yet! \nComplete it in order to move on");
            return;
        }

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
            System.out.println(currentRoom.getLongDescription());
            currentQuest = currentRoom.getQuest();
            if(!currentRoom.getVisited()){
                printQuest();
                currentRoom.setVisited();
                if(currentRoom.getRoomInventorySize()!=0){
                    System.out.println("There are items on the floor.\nYou can pick them up using the 'take all' command.");
                }
            }
            else{
                System.out.println(currentRoom.getExitString());
            }

        }
        else {
            currentRoom = nextRoom;
            currentQuest = currentRoom.getQuest();
            System.out.println(currentRoom.getLongDescription());
            if(!currentRoom.getVisited()){
                printQuest();
                currentRoom.setVisited();
                if(currentRoom.getRoomInventorySize()!=0){
                    System.out.println("There are items on the floor.\nYou can pick them up using the 'take all' command.");
                }
            }
            else{
                System.out.println(currentRoom.getExitString());
            }
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

 */

