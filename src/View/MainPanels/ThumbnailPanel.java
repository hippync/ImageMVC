package View.MainPanels;

import Controler.Controler;
import Model.ImageThumbnail;
import App.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


/**
 * Panneau de réprésentation d'une vignette
 *
 * @author
 * @version H2023
 */
public class ThumbnailPanel extends JPanel implements View {

    Controler c;                //control variable
    ImageThumbnail image;       //image displayed

    public ThumbnailPanel(Controler c) {

        this.c = c;

        setPreferredSize(new Dimension(300,200));

        image = c.getThumbnail();

        //ajout de la vue la liste d'observateur
        image.attachView(this);
        System.out.println("Thumbnail Image Attached to views!");
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

            //Pleine image
            g.drawImage(imageB, 0, 0,getWidth(),getHeight(),null);
        }
    }

    @Override
    public void updateView() {

        System.out.println("REPAINTED THUMBNAIL");
        repaint();
    }
}
