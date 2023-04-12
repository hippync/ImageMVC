package Controler.Commands;

import Controler.Controler;
import Model.AppFile;
import Model.ImageThumbnail;
import Model.ImageViewPoint;

import java.io.*;

/**
 * Commande pour la serialisation des objets de l'application, et aussi de
 * la de-serialisation
 *
 * @author
 * @version H2023
 */
public class Serialize implements Command, Serializable {

    private AppFile appFile;
    private String path;

    private Controler c;

    public Serialize(Controler c, ImageThumbnail thumbnail, ImageViewPoint leftImage,
                     ImageViewPoint rightImage, String path) {

        appFile = new AppFile(thumbnail,leftImage,rightImage);

        this.c = c;
        this.path = path;
    }

    @Override
    public void execute() {

        /**
         * Code modifiée du site web :
         * https://www.tutorialspoint.com/java/java_serialization.htm
         */
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(path+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(appFile);
            out.close();
            fileOut.close();
            System.out.printf("File saved at : " + path + ".ser");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void undo() {

        /**
         * Code modifiée du site web :
         * https://www.tutorialspoint.com/java/java_serialization.htm
         */
        AppFile af;

        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            af = (AppFile) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("AppFile class not found");
            c.printStackTrace();
            return;
        }

        c.thumbnail = af.getImageThumbnail();
        c.leftImage = af.getLeftViewPoint();
        c.rightImage = af.getRightViewPoint();
    }
}
