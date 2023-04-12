package View.Components;

import Controler.Controler;
import View.MainPanels.InfoPanel;
import View.MainPanels.ThumbnailPanel;
import javax.swing.*;
import java.awt.*;

/**
 * Panneau du haut
 *
 * @author
 * @version H2023
 */
public class TopPanel extends JPanel {

    Controler c;
    JPanel thumbnailPanel;      //Vignette
    InfoPanel infoPanel;        //panneau d'information

    public TopPanel(Controler c) {

        this.c = c;

        setLayout(new BorderLayout());
        thumbnailPanel = new ThumbnailPanel(c);
        add(thumbnailPanel,BorderLayout.LINE_START);
        infoPanel = new InfoPanel(c);
        add(infoPanel);

        setBackground(new Color(104, 104, 104));
    }
}
