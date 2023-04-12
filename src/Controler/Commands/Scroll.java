package Controler.Commands;

import Model.ImageViewPoint;

/**
 * Commande d'un déplacement par souris
 *
 * @author
 * @version H2023
 */
public class Scroll implements Command {

    private ImageViewPoint image;

    private int ix;
    private int iy;
    private int fx;
    private int fy;

    //default
    public Scroll() {

    }

    public Scroll(int ix, int iy, int fx, int fy, ImageViewPoint image) {

        this.ix = ix;
        this.iy = iy;
        this.fx = fx;
        this.fy = fy;

        this.image = image;
    }

    @Override
    public void execute() {

        //difference
        int mx = ix - fx;
        int my = iy - fy;

        //nouveau points top gauche
        int newX = (image.getCurrentX() + mx);
        int newY = (image.getCurrentY() + my);

        image.setCurrentX(newX);
        image.setCurrentY(newY);
    }

    @Override
    public void undo() {

        //difference
        int mx = ix - fx;
        int my = iy - fy;

        //nouveau points top gauche
        int newX = (image.getCurrentX() - mx);
        int newY = (image.getCurrentY() - my);

        image.setCurrentX(newX);
        image.setCurrentY(newY);
    }
}
