package dk.sdu.mmmi.t3.g1;

import java.util.ArrayList;

public class Inventory {
    protected ArrayList<Item> items;

    public Inventory(){
        items = new ArrayList<>();
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public Item getItem(String itemName){
        for (int i = 0; i < items.size(); i++){
            if (items.get(i).getName().equals(itemName)) return items.get(i);
        }
        return null;
    }

    public Item getItem(int index){
        return items.get(index);
    }

    public void showInventory(){
        System.out.println("Inventory:");
        for(Item item: items){
            System.out.println(item.getName());
        }
    }

    public int getSize() {
        return items.size();
    }
}
