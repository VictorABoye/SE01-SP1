package dk.sdu.mmmi.t3.g1;


import javafx.scene.image.ImageView;

public class Player extends Entity implements Movement{

    private int klimaIndsats;
    //private Inventory inventory;
    private double speed = 25;

    public Player(ImageView image){
        super(image);
        type = "player";
        dead = false;
        this.klimaIndsats = 50;
        //inventory = new Inventory();
    }

    public void incKlimaindsats(int x){
        int pre = klimaIndsats;
        klimaIndsats += x;
        if (klimaIndsats <= 0) {
            System.out.println("You lost...");
            // End game();
        }
        else if (pre > klimaIndsats) {
            System.out.println("You can do better");
        }
        else if (pre < klimaIndsats) {
            System.out.println("You are doing well");
        }
        else if (klimaIndsats >= 100){
            System.out.print("You win!!!");
        }
        checkKlimaindsats();
    }

    public int getKlimaindsats() {
        return klimaIndsats;
    }

    public void checkKlimaindsats(){
        System.out.println("Your current score is: " + getKlimaindsats());
    }



    @Override
    public void moveUp(){
        image.setLayoutY(y-speed);
    }

    @Override
    public void moveDown(){
        image.setLayoutY(y+speed);
    }

    @Override
    public void moveRight(){
        image.setLayoutX(x-speed);
    }

    @Override
    public void moveLeft(){
        image.setLayoutX(x+speed);
    }



    /*
     *
     * Legacy Code
     *
     */
      /*
        public Item getItem(String itemName){
        return inventory.getItem(itemName);
    }

    public Item getItem(int index){
        return inventory.getItem(index);
    }

    public void pickUp(Item item){
        // Needs to get item from room and add to inventory inventory.addItem();
        // Needs a method for removing the item from the room when picking up.
        inventory.addItem(item);
    }

    public void useItem(){
        //Use an item
    }

    public void place(Item item){
        //Needs to place item into room inventory and remove from player inventory inventory.removeItem(item);
        //Needs a method to add the item to the room when placing
        inventory.removeItem(item);
    }

    public void showInventory(){
        try{
            if (inventory.getSize()!=0) {
                System.out.println("Inventory:");
                inventory.showInventory();
            }
            else {
                System.out.println("Your bag is empty");
            }
        }catch (NullPointerException e){
            System.out.println("You don't have any items");
        }
    }


    public int getPlayerInventorySize() {
        return inventory.getSize();
    }

    public boolean hasItem(String items) {
        return inventory.hasItem(items);
    }

    public boolean sort(String item, String type){
        if(item == null){
            System.out.println("You need to choose an item to sort");
        }
        else if(type == null){
            System.out.println("You need to choose how you want to sort the item");
        }
        else {
            if(inventory.items.size()==0){
                System.out.println("You don't have any items");
            }
            boolean hasItem = false;
            int counter = 0;
            for(Item item1: inventory.items){
                if(item1.getName().equals(item)){
                    hasItem = true;
                    break;
                }
                counter++;
            }

            if(!hasItem){
                System.out.println("You dont have " + item + " in your inventory");
                System.out.println("You can use 'bag' to check your inventory");
            }
            while(hasItem) {
                Item invItem = inventory.items.get(counter);;
                if (invItem.getName().toUpperCase().equals(item.toUpperCase())) {
                    if (invItem.getType().toUpperCase().equals(type.toUpperCase())) {
                        System.out.println("You sorted " + item + " as " + type);
                        inventory.items.remove(counter);
                        incKlimaindsats(5);
                        hasItem = false;
                        return true;
                    } else {
                        System.out.println(" You sorted " + item + " as " + type);
                        inventory.items.remove(counter);
                        incKlimaindsats(-5);
                        hasItem = false;
                        return true;
                    }
                }
            }
        }
        return false;
    }
     */
}
