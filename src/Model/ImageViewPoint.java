package Model;

import App.Subject;
import App.View;
import java.util.LinkedList;

/**
 * Classe type pour une persepective
 *
 * @author
 * @version H2023
 */
public class ImageViewPoint extends Image implements Subject, java.io.Serializable {

    //ObserverList
    private LinkedList<View> viewList = new LinkedList<View>();

    private String perspective;        //nom de la perspective
    private int currentX;       //coordonée X courante (coin haut-gauche)
    private int currentY;       //coordonée Y courante (coin haut-gauche)
    private int zoomRatio;      //Ratio de zoom

    //Constructeur principal
    public ImageViewPoint(String perspective) {

        this.perspective = perspective;

        currentX = 0;
        currentY = 0;
        zoomRatio = 550;
    }

    @Override
    public void notifyViews() {

        for (int i = 0; i < viewList.size(); i++) {

            viewList.get(i).updateView();
        }
    }

    @Override
    public void attachView(View v) {

        viewList.add(v);
    }

    @Override
    public void dettachView(View v) {

        viewList.remove(v);
    }

    //Getters and Setters
    public int getCurrentX() {
        return currentX;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
        notifyViews();
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
        notifyViews();
    }

    public int getZoomRatio() {
        return zoomRatio;
    }

    public void setZoomRatio(int zoomRatio) {
        this.zoomRatio = zoomRatio;
        notifyViews();
    }
    public String getPerspective() {
        return perspective;
    }

    public void setPerspective(String perspective) {
        this.perspective = perspective;
    }
}
