package dk.sdu.mmmi.t3.g1;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Item extends Entity {
    private String name;
    private String material;
    private boolean sorted;

    public Item(ImageView image, String name, String material){
        super(image);
        type = "item";
        this.name = name;
        this.material = material;
        sorted = false;
    }

    public String getName(){
        return name;
    }
    public String getMaterial(){
        return material;
    }

    public boolean isSorted() {
        return sorted;
    }

    public void setSorted() {
        this.sorted = true;
    }
}
