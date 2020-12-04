package UI;

import dk.sdu.mmmi.t3.g1.Item;
import dk.sdu.mmmi.t3.g1.WorldPlayer;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class InventoryController {

    @FXML
    public ImageView item01;

    @FXML
    public void initialize(){

    }

    public static void addItemToView(Item item, int index)
    {
        index++;
        String imageString = "#item" + (index < 10 ? "0"+index : index);
        String url = item.getImageView().getImage().getUrl();

        System.out.println(imageString);
        System.out.println("old url:" + url);

        try {
            Parent window = FXMLLoader.load(InventoryController.class.getResource("/fxml/Inventory.fxml"));
            ImageView imageView = (ImageView) window.lookup(imageString);
            //File file = new File(item.getImageView().getImage().getUrl());
            System.out.println(item.getImageView().getImage().getUrl());
            //File file = new File("D:/SDU/Projekt/SE01-SP1/src/main/resource/images/can.png");
            //BufferedImage bufferedImage = ImageIO.read(file);
            //Image image = SwingFXUtils.toFXImage(bufferedImage,null);
            //imageView.setImage(image);
            FileInputStream inputStream = new FileInputStream(url);
            Image image = new Image(inputStream);
            imageView.setImage(image);
            System.out.println("new url:" + imageView.getImage().getUrl());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void makeInventory(WorldPlayer worldPlayer)
    {
        for (int i = 0; i < worldPlayer.getInventory().getSize(); i++)
        {
            addItemToView(worldPlayer.getItemByIndex(i),i);
        }
    }
}
