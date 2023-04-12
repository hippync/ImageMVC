package Model;

import java.io.Serializable;

/**
 * Objet type pour un enregistrement des données de l'application
 *
 * @author
 * @version H2023
 */

public class AppFile implements Serializable {

    private ImageThumbnail imageThumbnail;
    private ImageViewPoint leftViewPoint;
    private ImageViewPoint rightViewPoint;

    public AppFile(ImageThumbnail imageThumbnail, ImageViewPoint leftViewPoint,
           ImageViewPoint rightViewPoint) {

        this.imageThumbnail = imageThumbnail;
        this.leftViewPoint = leftViewPoint;
        this.rightViewPoint = rightViewPoint;
    }

    //Getters & Setters
    public ImageThumbnail getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(ImageThumbnail imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public ImageViewPoint getLeftViewPoint() {
        return leftViewPoint;
    }

    public void setLeftViewPoint(ImageViewPoint leftViewPoint) {
        this.leftViewPoint = leftViewPoint;
    }

    public ImageViewPoint getRightViewPoint() {
        return rightViewPoint;
    }

    public void setRightViewPoint(ImageViewPoint rightViewPoint) {
        this.rightViewPoint = rightViewPoint;
    }
}
