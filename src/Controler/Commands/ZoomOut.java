package Controler.Commands;

import Model.*;
import java.util.LinkedList;

/**
 * Commande pour le Zoom vers l'extérieur
 *
 * @author
 * @version H2023
 */
public class ZoomOut implements Command {

    int scrollAmount;
    LinkedList<ImageViewPoint> images;

    public ZoomOut(int scrollAmount, LinkedList<ImageViewPoint> images) {

        this.scrollAmount = scrollAmount;
        this.images = images;
    }

    @Override
    public void execute() {

        for (int i = 0; i < images.size(); i++) {
            images.get(i).setZoomRatio(images.get(i).getZoomRatio() + (15 * scrollAmount));
            images.get(i).setCurrentX(images.get(i).getCurrentX() - (15 * scrollAmount / 2));
            images.get(i).setCurrentY(images.get(i).getCurrentY() - (15 * scrollAmount / 2));
        }
    }

    @Override
    public void undo() {

        for (int i = 0; i < images.size(); i++) {
            images.get(i).setZoomRatio(images.get(i).getZoomRatio() - (15 * scrollAmount));
            images.get(i).setCurrentX(images.get(i).getCurrentX() + (15 * scrollAmount / 2));
            images.get(i).setCurrentY(images.get(i).getCurrentY() + (15 * scrollAmount / 2));
        }
    }
}