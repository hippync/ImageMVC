package Controler.Commands;

import Model.ImageViewPoint;
import java.util.LinkedList;

/**
 * Commande pour se déplacer ver le haut
 *
 * @author
 * @version H2023
 */
public class TranslateUp implements Command {

    LinkedList<ImageViewPoint> images;

    public TranslateUp(LinkedList<ImageViewPoint> images) {

        this.images = images;
    }

    @Override
    public void execute() {

        for (int i = 0; i < images.size(); i++) {
            images.get(i).setCurrentY(images.get(i).getCurrentY()-50);
        }
    }

    @Override
    public void undo() {

        for (int i = 0; i < images.size(); i++) {
            images.get(i).setCurrentY(images.get(i).getCurrentY()+50);
        }
    }
}