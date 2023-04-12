package View.MainPanels;

import Controler.Controler;
import App.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;

/**
 * Panneau de réprésentation des informations de la vignette
 *
 * @author
 * @version H2023
 */
public class InfoPanel extends JPanel implements View {

    Controler c;                //Controler variable

    //Labels
    JLabel heightLabel = new JLabel("Height : ");
    JLabel widthLabel = new JLabel("Width : ");
    JLabel lastModifiedLabel = new JLabel("Last modified :");

    public InfoPanel(Controler c) {

        this.c = c;
        c.getThumbnail().attachView(this);
        //setBackground(new Color(90, 90, 90));
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10,10,10,10));

        //add labels
        addLabels();
    }

    /**
     * Ajout des label dans le panneau
     */
    private void addLabels() {

        this.add(heightLabel, BorderLayout.PAGE_START);
        this.add(widthLabel, BorderLayout.CENTER);
        this.add(lastModifiedLabel, BorderLayout.PAGE_END);
    }

    /**
     * Met à jour les informations des labels
     */
    private void updateLabels() {

        String path = c.getThumbnail().getPath();
        BufferedImage image = null;

        if (path != null) {

            try {
                image = ImageIO.read(new File(path));

            } catch (Exception e) {
                e.printStackTrace();
            }

            int width = image.getWidth();
            int height = image.getHeight();

            /**
             * Code modifiée de
             * https://www.mkyong.com/java/how-to-get-the-file-last-modified-date-in-java/
             */
            File file = new File(path);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

            String lastModified = sdf.format(file.lastModified());

            heightLabel.setText("Height : "+height);
            widthLabel.setText("Width : "+width);
            lastModifiedLabel.setText("Last modified : "+lastModified);
        }
    }

    @Override
    public void updateView() {

        System.out.println("UPDATE INFO PANEL");
        updateLabels();
        repaint();
    }
}
