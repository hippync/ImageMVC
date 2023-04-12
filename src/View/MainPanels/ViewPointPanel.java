package View.MainPanels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;

import Controler.Controler;
import View.Components.Toolbar;
import App.*;
import Model.ImageViewPoint;

/**
 * Panneau de réprésentation d'une perspective
 *
 * @author
 * @version H2023
 */
public class ViewPointPanel extends JPanel implements View {

    private Controler c;                      //control variable
    private ImageViewPoint image;             //image displayed
    private Point pointClicked;               //last click position
    private int ix, iy;                       //current X and Y (user)
    private boolean isActivated;              //if the panel enabled for toolbar

    public ViewPointPanel(Controler c, ImageViewPoint image) {

        this.c = c;
        this.image = image;

        //ajout de la vue la liste d'observateur
        this.image.attachView(this);
        System.out.println(image.getPerspective() + " Image Attached to views!");

        addListeners();
    }

    /**
     * Ajout des écouteurs
     */
    public void addListeners() {

        addMouseListener(new MyMouseListener());
        addMouseMotionListener(new MyMotionListener());
        addMouseWheelListener(new MyMouseWheelListener());
        //addKeyListener(new KeyboardListener());
    }

    /**
     * Painture le panneau avec l'image
     *
     * @param g Graphics
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        String path = image.getPath();
        BufferedImage imageB = null;

        if(image.getPath() != null) {

            try {
                imageB = ImageIO.read(new File(path));

            } catch (Exception e) {
                e.printStackTrace();
            }

            int dx1 = 0;
            int dy1 = 0;
            int dx2 = getWidth();
            int dy2 = getHeight();

            int zoomRatio = image.getZoomRatio();

            int sx1 = image.getCurrentX();
            int sy1 = image.getCurrentY();
            int sx2 = image.getCurrentX() + zoomRatio;
            int sy2 = image.getCurrentY() + zoomRatio;

            g.drawImage(imageB,dx1,dy1,dx2,dy2,sx1,sy1,sx2,sy2,null);

            if(isActivated){
                setBorder(BorderFactory.createMatteBorder(
                        4, 4, 4, 4,Color.BLUE));
            } else {
                setBorder(null);
            }
        }
    }

    @Override
    public void updateView() {

        System.out.println("REPAINTED VIEW POINT PANEL");
        repaint();
    }

    //Getters & Setters
    public boolean getIsActivated() {

        return isActivated;
    }

    public ImageViewPoint getImage() {
        return image;
    }

    /**
     * Écouteur des bouttons de la souris
     *
     * @author Hansy Pierre
     * @version H2023
     */
    public class MyMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            //already Activated
            if(isActivated) {
                isActivated = false;
                System.out.println(isActivated);
                repaint();
            } else {
                isActivated = true;
                System.out.println(isActivated);
                repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

            //update coordinates where user last clicked before dragging
            //change cursor
            setCursor(new Cursor(Cursor.MOVE_CURSOR));
            pointClicked = e.getPoint();
            ix = pointClicked.x;
            iy = pointClicked.y;
        }

        @Override
        public void mouseReleased(MouseEvent e) {

            //change back to normal cursor
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            pointClicked = null;


            System.out.println("ADDED RELEASE");
            c.release();
            Toolbar.undoButtonRegulate();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }
        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    /**
     * Écouteur du mouvement de la souris
     *
     * @author Hansy Pierre
     * @version H2023
     */
    public class MyMotionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {

            LinkedList<ImageViewPoint> images = new LinkedList<ImageViewPoint>();
            images.add(image);

            c.scroll(ix,iy,e.getX(),e.getY(),image);

            //reset les coordonne ou l'utlisateur focus
            ix = ix - (ix - e.getX());
            iy = iy - (iy - e.getY());

            Toolbar.undoButtonRegulate();
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

    /**
     * Écouteur de la roulette de la souris
     *
     * @author Hansy Pierre
     * @version H2023
     */
    public class MyMouseWheelListener implements MouseWheelListener {

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {

            LinkedList<ImageViewPoint> images = new LinkedList<ImageViewPoint>();
            images.add(image);

            //Zoom In
            if(e.getPreciseWheelRotation() == -1.0) {
               c.zoomIn(e.getScrollAmount(), images);
            }
            //Zoom Out
            else {
               c.zoomOut(e.getScrollAmount(), images);
            }

            Toolbar.undoButtonRegulate();
        }
    }
}
