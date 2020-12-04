package dk.sdu.mmmi.t3.g1;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public abstract class Item extends Entity {
    private String name;

    public Item(ImageView image, String name){
        super(image);
        type = "item";
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
