package Controler.Commands;

import Model.ImageViewPoint;
import java.util.LinkedList;

/**
 * Commande pour se déplacer ver la droite
 *
 * @author
 * @version H2023
 */
public class TranslateRight implements Command {

    LinkedList<ImageViewPoint> images;

    public TranslateRight(LinkedList<ImageViewPoint> images) {

        this.images = images;
    }

    @Override
    public void execute() {

        for (int i = 0; i < images.size(); i++) {
            images.get(i).setCurrentX(images.get(i).getCurrentX()+50);
        }
    }

    @Override
    public void undo() {

        for (int i = 0; i < images.size(); i++) {
            images.get(i).setCurrentX(images.get(i).getCurrentX()-50);
        }
    }
}
