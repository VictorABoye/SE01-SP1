package dk.sdu.mmmi.t3.g1;

import java.util.ArrayList;
import java.util.Date;

public class FoodItem extends Item  {
    private Date expires;

    public FoodItem(String name, String type) {
        super(name, type);
        expires = new Date((long)Math.random()*10000);
    }

@Override
    public boolean isExpired () {
        Date now = new Date();
        if (now.compareTo(expires)>0){
            return true;
        }else{
            return false;
        }
    }
    public void checkExpired (){
        Date now = new Date();
        ArrayList<Item> expiredItems = new ArrayList<>();
        for (Item item: items){
            if (item.isExpired()){
                expiredItems.add(item);
            }
        }
        if (expiredItems.isEmpty()) {
            System.out.println("You do not have expired foods in your inventory");
        }else{
            System.out.println("You have expired items in your inventory.");
            System.out.println("These are");
            for (Item item: expiredItems){
                System.out.println(item.getName());
            }
        }
    }

    public void removeExpired (){
        int countExpired = 0;
        for (Item item : items){
            if (item.isExpired()){
                items.remove(item);
                countExpired++;
            }

        }
        System.out.println("You have removed " + countExpired + " expired items from your inventory");
    }



    public Date getExpires(){
        return expires;
    }
}
