package View.Components;

import Controler.Controler;
import View.MainPanels.ViewPointPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Fenetre du bas qui contient les deux persepectives
 *
 * @author
 * @version H2023
 */
public class LowerPanel extends JPanel {

    private Controler c;        //Controler variable

    //both lower Panels
    private ViewPointPanel rightPanel;
    private ViewPointPanel leftPanel;

    public LowerPanel(Controler c) {

        this.c = c;

        rightPanel = new ViewPointPanel(c,c.getRightImage());
        leftPanel = new ViewPointPanel(c,c.getLeftImage());

        //ViewPoint panel size
        leftPanel.setPreferredSize(new Dimension(600,550));
        rightPanel.setPreferredSize(new Dimension(600,550));

        //this panel size
        setPreferredSize(new Dimension(1200,550));
        setLayout(new BorderLayout());

        splitPanels();
    }

    private void splitPanels() {

        rightPanel.setBackground(new Color(120, 120, 120));
        add(rightPanel);

        leftPanel.setBackground(new Color(120, 120, 120));
        add(leftPanel,BorderLayout.LINE_START);
    }

    //Getters and setters
    public ViewPointPanel getRightPanel() {

        return rightPanel;
    }

    public ViewPointPanel getLeftPanel() {

        return leftPanel;
    }
}
