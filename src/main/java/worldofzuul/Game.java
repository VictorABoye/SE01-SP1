package worldofzuul;

import dk.sdu.mmmi.t3.g1.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Game{

    private static WorldPlayer player;
    private static Room currentRoom;
    private static boolean finished;
    private static ArrayList<Room> rooms;
    private final Parser parser;
    private final ArrayList<ArrayList> questStrings = new ArrayList<>();

    public Game() throws IOException, ParseException {
        player = new WorldPlayer();
        finished = false;
        rooms = new ArrayList<>();
        parser = new Parser();
        createWorld();
    }

    //===Initial methods================================================================================================

    private void createWorld() throws IOException, ParseException {
        //FXML files
        String FXML1 = "/fxml/Level1.fxml";
        String FXML2 = "/fxml/Level2.fxml";
        String FXML3 = "/fxml/Level3.fxml";
        String FXML4 = "/fxml/Level4.fxml";
        String FXML5 = "/fxml/Level5.fxml";
        String FXML6 = "/fxml/Level6.fxml";
        String FXML7 = "/fxml/Level7.fxml";


        Quests breakfast, transport, roadQuest, groceries, recyclingQuest, factory, parkQuest;
        Data quests = new Data();
        questStrings.add(quests.questString("breakfast"));
        questStrings.add(quests.questString("transport"));
        questStrings.add(quests.questString("roadQuest"));
        questStrings.add(quests.questString("groceries"));
        questStrings.add(quests.questString("recyclingQuest"));
        questStrings.add(quests.questString("factory"));
        questStrings.add(quests.questString("parkQuest"));

        breakfast = new Quests(new ArrayList<>(), new HashMap<>(), parser.parseString((String)questStrings.get(0).get(0)));
        transport = new Quests(new ArrayList<>(), new HashMap<>(), parser.parseString((String)questStrings.get(1).get(0)));
        roadQuest = new Quests(new ArrayList<>(), new HashMap<>(), parser.parseString((String)questStrings.get(2).get(0)));
        groceries = new Quests(new ArrayList<>(), new HashMap<>(), parser.parseString((String)questStrings.get(3).get(0)));
        recyclingQuest = new Quests(new ArrayList<>(), new HashMap<>(), parser.parseString((String)questStrings.get(4).get(0)));
        factory = new Quests(new ArrayList<>(), new HashMap<>(), parser.parseString((String)questStrings.get(5).get(0)));
        parkQuest = new Quests(new ArrayList<>(), new HashMap<>(), parser.parseString((String)questStrings.get(6).get(0)));

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

        recyclingQuest.addChoice(parser.parseString((String)questStrings.get(4).get(1)), 5, parser.parseString((String)questStrings.get(4).get(3)));
        recyclingQuest.addChoice(parser.parseString((String)questStrings.get(4).get(2)), -5, parser.parseString((String)questStrings.get(4).get(4)));

        // Creating a new quest, "factory"
        factory.addChoice(parser.parseString((String)questStrings.get(5).get(1)), 5, parser.parseString((String)questStrings.get(5).get(3)));
        factory.addChoice(parser.parseString((String)questStrings.get(5).get(2)), -5, parser.parseString((String)questStrings.get(5).get(4)));

        parkQuest.addChoice(parser.parseString((String)questStrings.get(6).get(1)), 5, parser.parseString((String)questStrings.get(6).get(3)) );
        parkQuest.addChoice(parser.parseString((String)questStrings.get(6).get(2)), -5, parser.parseString((String)questStrings.get(6).get(4)) );



        try {
            //===Home lvl===============================================================================================

            //Loader
            Parent window1 = FXMLLoader.load(getClass().getResource(FXML1));

            //Create Items
            Item can1 = new Item((ImageView) window1.lookup("#itemCan1"),"can","metal");
            Item can2 = new Item((ImageView) window1.lookup("#itemCan2"),"can","metal");

            //Create Teleports
            Teleport homeTPNorth = new Teleport((ImageView) window1.lookup("#tpNorth"),FXML2);

            //Create Room
            Room home = new Room(breakfast);

            //Add Items and Teleporters to Room
            home.addItemToRoom(can1);
            home.addItemToRoom(can2);
            home.addTeleporterToRoom(homeTPNorth);

            //===Parking lvl============================================================================================

            //Loader
            Parent window2 = FXMLLoader.load(getClass().getResource(FXML2));

            //Create Items
            Item bottle1 = new Item((ImageView) window2.lookup("#glassBottle1"), "bottle", "glass");
            Item bottle2 = new Item((ImageView) window2.lookup("#glassBottle2"), "bottle", "glass");

            //Create Teleporters
            Teleport parkingTPNorth = new Teleport((ImageView) window2.lookup("#tpNorth"),FXML4);
            Teleport parkingTPSouth = new Teleport((ImageView) window2.lookup("#tpSouth"),FXML1);
            Teleport parkingTPEast = new Teleport((ImageView) window2.lookup("#tpEast"),FXML3);
            Teleport parkingTPWest = new Teleport((ImageView) window2.lookup("#tpWest"),FXML5);

            //Create Room
            Room parking = new Room(transport);

            //Add Items and Teleporters to Room
            parking.addTeleporterToRoom(parkingTPNorth);
            parking.addTeleporterToRoom(parkingTPSouth);
            parking.addTeleporterToRoom(parkingTPEast);
            parking.addTeleporterToRoom(parkingTPWest);
            parking.addItemToRoom(bottle1); //marc er sej :)
            parking.addItemToRoom(bottle2); //marc er sej igen :/


            //===Beach lvl==============================================================================================

            //Loader
            Parent window3 = FXMLLoader.load(getClass().getResource(FXML3));

            //Create Items
            Item paperbag1 = new Item((ImageView) window3.lookup("#paperbag1"), "paperbag", "paper");
            Item paperbag2 = new Item((ImageView) window3.lookup("#paperbag2"), "paperbag", "paper");

            //Create Teleporters
            Teleport beachTPWest = new Teleport((ImageView) window3.lookup("#tpWest"),FXML2);

            //Create Barriers
            Barrier beachBarrier = new Barrier((ImageView) window3.lookup("#wall"));

            //Create Room
            Room beach = new Room(factory);

            //Add Items and Teleporters to Room
            beach.addTeleporterToRoom(beachTPWest);
            beach.addWall(beachBarrier);
            beach.addItemToRoom(paperbag1);
            beach.addItemToRoom(paperbag2);

            //===Park lvl===============================================================================================

            //Loader
            Parent window4 = FXMLLoader.load(getClass().getResource(FXML4));

            //Create Items
            Item metalcan1 = new Item((ImageView) window4.lookup("#metalcan1"), "metalcan", "metal");
            Item metalcan2 = new Item((ImageView) window4.lookup("#metalcan2"), "metalcan", "metal");

            //Create Teleporters
            Teleport parkTPSouth = new Teleport((ImageView) window4.lookup("#tpSouth"),FXML2);

            //Create Barriers
            Barrier parkBarrier = new Barrier((ImageView) window4.lookup("#wall"));

            //Create Room
            Room park = new Room(parkQuest);

            //Add Items and Teleporters to Room
            park.addTeleporterToRoom(parkTPSouth);
            park.addWall(parkBarrier);
            park.addItemToRoom(metalcan1);
            park.addItemToRoom(metalcan2);

            //===Road lvl===============================================================================================

            //Loader
            Parent window5 = FXMLLoader.load(getClass().getResource(FXML5));

            //Create Items
            Item plastBottle = new Item((ImageView) window5.lookup("#plastBottle"), "plastBottle", "plast");
            Item battery = new Item((ImageView) window5.lookup("#battery"), "battery", "battery");

            //Create Teleporters
            Teleport roadTPNorth = new Teleport((ImageView) window5.lookup("#tpNorth"),FXML6);
            Teleport roadTPEast = new Teleport((ImageView) window5.lookup("#tpEast"),FXML2);
            Teleport roadTPWest = new Teleport((ImageView) window5.lookup("#tpWest"),FXML7);

            //Create Room
            Room road = new Room(roadQuest);

            //Add Items and Teleporters to Room
            road.addTeleporterToRoom(roadTPNorth);
            road.addTeleporterToRoom(roadTPEast);
            road.addTeleporterToRoom(roadTPWest);
            road.addItemToRoom(battery);
            road.addItemToRoom(plastBottle);

            //===Shop lvl===============================================================================================

            //Loader
            Parent window6 = FXMLLoader.load(getClass().getResource(FXML6));

            //Create Items
            //Create Teleporters
            Teleport shopTPSouth = new Teleport((ImageView) window6.lookup("#tpSouth"),FXML5);

            //Create Room
            Room shop = new Room(groceries);

            //Add Items and Teleporters to Room
            shop.addTeleporterToRoom(shopTPSouth);

            //===Recycling Plant lvl====================================================================================

            //Loader
            Parent window7 = FXMLLoader.load(getClass().getResource(FXML7));

            //Create Items

            //Create Teleporters
            Teleport recyclingTPEast = new Teleport((ImageView) window7.lookup("#tpEast"),FXML5);

            //Create Barrier
            Barrier recyclingBarrier = new Barrier((ImageView) window7.lookup("#wall"));

            //Create Room
            Room recycling = new Room(recyclingQuest, "recycling");

            //Add Items and Teleporters to Room
            recycling.addTeleporterToRoom(recyclingTPEast);
            recycling.addWall(recyclingBarrier);

            //===Link rooms=============================================================================================
            home.getTP(0).linkTeleport(parking);

            parking.getTP(0).linkTeleport(park);
            parking.getTP(1).linkTeleport(home);
            parking.getTP(2).linkTeleport(beach);
            parking.getTP(3).linkTeleport(road);

            beach.getTP(0).linkTeleport(parking);

            park.getTP(0).linkTeleport(parking);

            road.getTP(0).linkTeleport(shop);
            road.getTP(1).linkTeleport(parking);
            road.getTP(2).linkTeleport(recycling);

            shop.getTP(0).linkTeleport(road);

            recycling.getTP(0).linkTeleport(road);

            //Adding rooms to arraylist
            rooms.add(home);
            rooms.add(beach);
            rooms.add(park);
            rooms.add(parking);
            rooms.add(road);
            rooms.add(shop);
            rooms.add(recycling);

            //Starting Room
            currentRoom = home;


        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    //May not need
    public void play() {
        System.out.println("Play the game");
    }

    //===Utility methods================================================================================================

    public static Room getCurrentRoom() {
        return currentRoom;
    }

    public static void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    public static WorldPlayer getWorldPlayer() {
        return player;
    }

    public static boolean isFinished(){
        return finished;
    }

    public static void setFinished(){
        finished = true;
    }

    public static boolean playerCollidesItem(Player player){
        Item currentItem = getClosestItemToPlayer(player);
        if (currentItem != null) {
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
        if (currentRoom.getRoomInventorySize() == 0) return null;
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
                //Item on screen
                double ix1 = currentItem.getX();
                double ix2 = currentItem.getW() + ix1;
                double iy1 = currentItem.getY();
                double iy2 = currentItem.getH() + iy1;
                double imx = ix2 / 2.0;
                double imy = iy2 / 2.0;
                //Distance between player and item
                double mpx = Math.abs(pmx - imx);
                double mpy = Math.abs(pmy - imy);
                double dist = Math.sqrt((mpx+mpy)*(mpx+mpy));

                if (dist < shortestDist){
                    shortestDist = dist;
                    itemIndex = i;
                }
            }
        return currentRoom.getItem(itemIndex);
    }

    public static boolean playerCollidesTeleport(Player player){
        Teleport currentTP = getClosestTeleporterToPlayer(player);
        if (currentTP != null) {
            double ix1 = currentTP.getX();
            double iy1 = currentTP.getY();
            double ix2 = currentTP.getW() + ix1;
            double iy2 = currentTP.getH() + iy1;
            double px1 = player.getX();
            double py1 = player.getY();
            double px2 = player.getW() + px1;
            double py2 = player.getH() + py1;
            return px2 >= ix1 && py1 <= iy2 && !(px1 >= ix2) && !(py2 <= iy1);
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
            //Item on screen
            double ix1 = currentTP.getX();
            double ix2 = currentTP.getW() + ix1;
            double iy1 = currentTP.getY();
            double iy2 = currentTP.getH() + iy1;
            double imx = ix2 / 2.0;
            double imy = iy2 / 2.0;
            //Distance between player and item
            double mpx = Math.abs(pmx - imx);
            double mpy = Math.abs(pmy - imy);
            double dist = Math.sqrt((mpx+mpy)*(mpx+mpy));

            if (dist < shortestDist){
                shortestDist = dist;
                itemIndex = i;
            }
        }
        return currentRoom.getTP(itemIndex);
    }

    public static boolean itemsInRooms(){
        for(Room room: rooms) {
            if (room.getRoomInventorySize() > 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean allQuestsCompleted(){
        for(Room room: rooms){
            if(!room.getQuest().getCompleted()){
                return false;
            }
        }
        return true;
    }
}