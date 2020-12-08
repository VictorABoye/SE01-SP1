package worldofzuul;

import UI.PlayerControl;
import dk.sdu.mmmi.t3.g1.*;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;


public class Room {
    private HashMap<String, Room> exits;
    private Inventory inventory;
    private boolean isVisited;
    private String infoBox;
    private Quests quest;
    private ArrayList<Teleport> teleports;
    private Barrier wall;
    private String name;

    public Room (Quests quest, String name)
    {
        exits = new HashMap<String, Room>();
        inventory = new Inventory();
        isVisited = false;
        this.quest = quest;
        this.infoBox = this.quest.getDescription();
        teleports = new ArrayList<>();
        this.name = name;

    }
    public Room (Quests quest)
    {
        exits = new HashMap<String, Room>();
        inventory = new Inventory();
        isVisited = false;
        this.quest = quest;
        this.infoBox = this.quest.getDescription();
        teleports = new ArrayList<>();
        name = "default";

    }

    public Inventory getInventory(){
        return inventory;
    }

    public boolean isVisited(){
        return isVisited;
    }

    public void setVisited(){
        isVisited = true;
    }

    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public void addItemToRoom(Item item){
        inventory.addItem(item);
    }

    public void removeItemFromRoom(Item item){
        inventory.removeItem(item);
    }

    public Item getItem(String itemName){
        return inventory.getItem(itemName);
    }

    public void showInventory(){
        try{
            if (inventory.getSize()!=0) {
                System.out.println("You can see:");
                inventory.showInventory();
            }
            else {
                System.out.println("There are no items here");
            }
        }catch (NullPointerException e){
            System.out.println("Room is empty");
        }
    }

    public String getExitString() {
        String returnString = "You can go:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    public int getRoomInventorySize() {
        return inventory.getSize();
    }

    public Item getItem(int index){
        return inventory.getItem(index);
    }

    public String getInfoBox() {
        return infoBox;
    }

    public boolean hasItem(String itemName) {
        return inventory.hasItem(itemName);
    }

    public Quests getQuest() {
        return quest;
    }

    public void addTeleporterToRoom(Teleport tp) {
        teleports.add(tp);
    }

    public int getAmountOfTeleports() {
        return teleports.size();
    }

    public Teleport getTP(int index) {
        return teleports.get(index);
    }

    public void addWall(Barrier wall){
        this.wall = wall;
    }

    public boolean hasWalls(){
        return wall != null;
    }

    public Barrier getWall(){
        return wall;
    }

    public String getName() {
        return name;
    }
}

