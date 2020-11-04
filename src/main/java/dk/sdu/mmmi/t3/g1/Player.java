package dk.sdu.mmmi.t3.g1;

public class Player {

    private int Klimaindsats;
    private Inventory inventory;

    public Player(){
        this.Klimaindsats = 50;
    }

    public void pickUp(String item){
        // Needs to get item from room and add to inventory inventory.addItem();
        //Needs a method for removing the item from the room when picking up.
    }

    public void useItem(){
        //Use an item
    }

    public void place(String item){
        //Needs to place item into room inventory and remove from player inventory inventory.removeItem(item);
        //Needs a method to add the item to the room when placing
    }

    public void showInventory(){
        inventory.showInventory();
    }

    public void incKlimaindsats(int x){
        Klimaindsats += x;
    }

    public void decKlimaindsats(int x){
        Klimaindsats -= x;
    }

    public int getKlimaindsats() {
        return Klimaindsats;
    }

    public void CheckKlimaindsats(){
        System.out.println(getKlimaindsats());
    }

    public void sort(String item, String type){
        //Sorting based on items from player inventory and type.
        //Needs to check if player is in recycling.
        //Also needs to check if player has any items in inventory that correspond to player request
    }
}
