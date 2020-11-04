package dk.sdu.mmmi.t3.g1;

import java.util.ArrayList;

public class Inventory {
    protected ArrayList<Item> items;

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public void showInventory(){
        System.out.println("Inventory:");
        for(Item item: items){
            System.out.println(item.getName());
        }
    }
}
