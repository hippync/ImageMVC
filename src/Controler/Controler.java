package Controler;

import Controler.Commands.*;
import Model.*;
import java.util.LinkedList;

/**
 * Classe controlleur s'occupe de recevoir des events et renvoie des commandes
 *
 * @author
 * @version H2023
 */
public class Controler implements java.io.Serializable{

    //les trois vues
    public ImageViewPoint leftImage;
    public ImageViewPoint rightImage;
    public ImageThumbnail thumbnail;

    CommandManager cm;          //Command Manager

    public Controler() {

        cm = CommandManager.getInstance();

        thumbnail = new ImageThumbnail();
        leftImage = new ImageViewPoint("Left");
        rightImage = new ImageViewPoint("right");

        System.out.println("3 Images created in Controler (2 ViewPoint & 1 Thumbnail");
    }

    /**
     * Charge les images à partir d'une image
     *
     * @param path chemin de l'image
     */
    public void loadImages(String path) {

        thumbnail.setPath(path);
        thumbnail.notifyViews();

        leftImage.setPath(path);
        leftImage.notifyViews();

        rightImage.setPath(path);
        rightImage.notifyViews();
    }

    /**
     * Sauvegarde l'état des objects dans un fichier .ser
     *
     * @param path chemin ou sauvergarder le fichier
     */
    public void serialize(String path) {

        Command com = new Serialize(this,thumbnail,leftImage,rightImage,path);
        com.execute();
    }

    /**
     * Charge l'état des objects d'un fichier .ser
     *
     * @param path chemin du fichier .set
     */
    public void deSerialize(String path) {

        Command com = new Serialize(this,thumbnail,leftImage,rightImage,path);
        com.undo();

        System.out.println("deSerialize");

        System.out.println(thumbnail.getPath());
        System.out.println(leftImage.getPath());

        loadImages(thumbnail.getPath());
    }

    /**
     * Méthode qui démarre une commande pour "Zoomer vers l'avant"
     *
     * @param scrollAmount quantité de zoom
     * @param images    les images a zoom
     */
    public void zoomIn(int scrollAmount, LinkedList<ImageViewPoint> images) {

       Command com = new ZoomIn(scrollAmount, images);
        com.execute();
        cm.addCommand(com);
    }

    /**
     * Méthode qui démarre une commande pour "Zoomer vers l'arrière"
     *
     * @param scrollAmount quantité de zoom
     * @param images    les images a zoom
     */
    public void zoomOut(int scrollAmount, LinkedList<ImageViewPoint> images) {

        Command com = new ZoomOut(scrollAmount, images);
        com.execute();
        cm.addCommand(com);
    }

    /**
     * Méthode qui démarre une commande pour déplacer vers la droite l'image
     *
     * @param images les images a modifié
     */
    public void moveRight(LinkedList<ImageViewPoint> images) {

        Command com = new TranslateRight(images);
        com.execute();
        cm.addCommand(com);
    }

    /**
     * Méthode qui démarre une commande pour déplacer vers la gauche l'image
     *
     * @param images les images a modifié
     */
    public void moveLeft(LinkedList<ImageViewPoint> images) {

        Command com = new TranslateLeft(images);
        com.execute();
        cm.addCommand(com);
    }

    /**
     * Méthode qui démarre une commande pour déplacer vers le haut l'image
     *
     * @param images les images a modifié
     */
    public void moveUp(LinkedList<ImageViewPoint> images) {

        Command com = new TranslateUp(images);
        com.execute();
        cm.addCommand(com);
    }

    /**
     * Méthode qui démarre une commande pour déplacer vers le bas l'image
     *
     * @param images les images a modifié
     */
    public void moveDown(LinkedList<ImageViewPoint> images) {

        Command com = new TranslateDown(images);
        com.execute();
        cm.addCommand(com);
    }

    /**
     * Déplacement d'une image avec la souris
     *
     * @param ix    x initial
     * @param iy    y initial
     * @param fx    x final
     * @param fy    y final
     * @param image     l'image à executer la commande
     */
    public void scroll(int ix, int iy, int fx, int fy, ImageViewPoint image) {

        Command com = new Scroll(ix,iy,fx,fy,image);
        com.execute();
        cm.addCommand(com);
    }

    /**
     * Command lache la souris
     */
    public void release() {

        Command com = new Release();
        cm.addCommand(com);
    }

    /**
     * Méthode qui démarre une commande pour undo la dernière commande
     */
    public void undo() {

        cm.UndoLastCommand();
    }

    //Getters & Setters
    public ImageViewPoint getLeftImage() {

        return leftImage;
    }

    public void setLeftImage(ImageViewPoint leftImage) {

        this.leftImage = leftImage;
    }

    public ImageViewPoint getRightImage() {

        return rightImage;
    }

    public void setRightImage(ImageViewPoint rightImage) {

        this.rightImage = rightImage;
    }

    public ImageThumbnail getThumbnail() {

        return thumbnail;
    }

    public void setThumbnail(ImageThumbnail thumbnail) {

        this.thumbnail = thumbnail;
    }

    public int getCommandNumber() {

        return cm.getNumberCommand();
    }
}
