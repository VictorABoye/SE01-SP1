package dk.sdu.mmmi.t3.g1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        //System.out.println("Inventory:");
        //for(Item item: items){
        //    System.out.println(item.getName());
        //}

        // Print: amount x item

        // Hashmap to store the frequency of element
        Map<Item, Integer> hm = new HashMap<Item, Integer>();

        for (Item i : items) {
            Integer j = hm.get(i);
            hm.put(i, (j == null) ? 1 : j + 1);
        }

        // Displaying the occurrence of elements in the arraylist
        for (Map.Entry<Item, Integer> val : hm.entrySet()) {
            System.out.println(val.getValue() + " x " + val.getKey().getName());
        }
    }

    public int getSize() {
        return items.size();
    }

    public boolean hasItem(String itemName) {
        for (Item i : items){
            if (i.getName().equals(itemName)){
                return true;
            }
        }
        return false;
    }
}

