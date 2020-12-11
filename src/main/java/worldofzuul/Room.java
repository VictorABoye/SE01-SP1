package worldofzuul;

import dk.sdu.mmmi.t3.g1.*;

import java.util.ArrayList;


public class Room {
    private Inventory inventory;
    private Quests quest;
    private ArrayList<Teleport> teleports;
    private Barrier wall;
    private String name;

    public Room (Quests quest, String name)
    {
        inventory = new Inventory();
        this.quest = quest;
        teleports = new ArrayList<>();
        this.name = name;
    }
    public Room (Quests quest)
    {
        inventory = new Inventory();
        this.quest = quest;
        teleports = new ArrayList<>();
        name = "default";
    }

    public Inventory getInventory(){
        return inventory;
    }

    public void addItemToRoom(Item item){
        inventory.addItem(item);
    }

    public void removeItemFromRoom(Item item){
        inventory.removeItem(item);
    }

    public int getRoomInventorySize() {
        return inventory.getSize();
    }

    public Item getItem(int index){
        return inventory.getItem(index);
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

