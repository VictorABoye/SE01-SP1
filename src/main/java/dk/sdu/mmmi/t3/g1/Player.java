package dk.sdu.mmmi.t3.g1;

public class Player {

    private int Klimaindsats;
    private Inventory inventory;

    public Player(){
        this.Klimaindsats = 50;
    }

    public void pickUp(String item){
        // Needs to get item from room and add to inventory inventory.addItem();
        // Needs a method for removing the item from the room when picking up.
    }

    public void useItem(){
        //Use an item
    }

    public void place(String item){
        //Needs to place item into room inventory and remove from player inventory inventory.removeItem(item);
        //Needs a method to add the item to the room when placing
    }

    public void showInventory(){
        try{
            inventory.showInventory();
        }catch (NullPointerException e){
            System.out.println("You don't have any items");
        }
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
        if(item == null){
            System.out.println("You need to choose an item to sort");
        }
        else if(type == null){
            System.out.println("You need to choose how you want to sort the item");
        }
        else {
            for (Item invItem : inventory.items) {
                if (invItem.getName().toUpperCase().equals(item.toUpperCase())) {
                    if (invItem.getType().toUpperCase().equals(type.toUpperCase())) {
                        System.out.println("You sorted " + item + " as " + type);
                        incKlimaindsats(5);
                    } else {
                        System.out.println(" You sorted " + item + " as " + type);
                        decKlimaindsats(5);
                    }
                } else {
                    System.out.println("You dont have " + item + " in your inventory");
                    System.out.println("You can use 'bag' to check your inventory");
                }
            }
        }
    }
}
