package worldofzuul;

import dk.sdu.mmmi.t3.g1.Inventory;
import dk.sdu.mmmi.t3.g1.Item;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private Inventory inventory;
    private boolean isVisited;
    private String infoBox;

    public Room(String description, String infoBox)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        inventory = new Inventory();
        isVisited = false;
        this.infoBox = infoBox;
    }

    public boolean getVisited(){
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

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    private String getExitString()
    {
        String returnString = "Exits:";
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
}

