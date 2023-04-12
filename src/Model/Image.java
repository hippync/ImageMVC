package Model;

/**
 * Classe parent d'une image à afficher dans l'application
 *
 * @author
 * @version H2023
 */
public abstract class Image implements java.io.Serializable {

    protected String path;          //Chemin de l'image

    //Getters & Setters
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
