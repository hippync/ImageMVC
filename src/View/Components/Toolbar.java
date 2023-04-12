package View.Components;

import Controler.Controler;
import View.MainPanels.ViewPointPanel;
import Model.ImageViewPoint;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Barre d'outils pour des commandes par bouttons
 *
 * @author
 * @version H2023
 */
public class Toolbar extends JToolBar {

    private static Controler c;

    //Views selected by user (Highlighted)
    private LinkedList<ImageViewPoint> currentActivatedImages = new LinkedList<ImageViewPoint>();

    private ViewPointPanel leftPanel;
    private ViewPointPanel rightPanel;

    private static JButton[] buttonArray = new JButton[7];

    public Toolbar(Controler c, ViewPointPanel leftPanel, ViewPointPanel rightPanel) {

        this.c = c;

        this.leftPanel = leftPanel;
        this.rightPanel = rightPanel;

        //barre d'outils fixe
        setFloatable(false);
        setRollover(true);

        //les bouttons
        buttonArray[0] = new JButton("Right");
        buttonArray[1] = new JButton("Left");
        buttonArray[2] = new JButton("Up");
        buttonArray[3] = new JButton("Down");
        buttonArray[4] = new JButton("ZoomIn");
        buttonArray[5] = new JButton("ZoomOut");
        buttonArray[6] = new JButton("Undo");
        buttonArray[6].setEnabled(false);

        addButtons();
        addListeners();
    }

    /**
     * Ajout des ecouteurs
     */
    private void addListeners() {

        for (int i = 0; i < buttonArray.length; i++) {

            add(buttonArray[i]);
        }
    }

    /**
     * Ajout des bouttons
     */
    private void addButtons() {

        for (int i = 0; i < buttonArray.length; i++) {

            buttonArray[i].addActionListener(new ToolBarActionListener());
            buttonArray[i].setFocusable(false);
        }
    }

    /**
     * Décide si le button Undo doit être activé
     */
    public static void undoButtonRegulate() {

        if(c.getCommandNumber() <= 0) {
            buttonArray[6].setEnabled(false);
        } else {
            buttonArray[6].setEnabled(true);
        }
    }

    /**
     * Obtient le ou les images activé par l'utilisateur
     */
    private void getCurrentActivatedImages() {

        if(leftPanel.getIsActivated()) {
            currentActivatedImages.add(leftPanel.getImage());
        }
        if(rightPanel.getIsActivated()) {
            currentActivatedImages.add(rightPanel.getImage());
        }
    }

    /**
     * Écouteur des bouttons dans la barre d'outils
     *
     * @author
     * @version H2023
     */
    public class ToolBarActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            getCurrentActivatedImages();

            JButton button = (JButton) e.getSource();
            String buttonperspective = button.getText();


            /**
             * Chaque boutton envoie un event vers le controleur
             */
            switch (buttonperspective) {

                case "Right":
                    if(currentActivatedImages.size() != 0) {
                        c.moveRight(currentActivatedImages);
                    }
                    break;
                case "Left":
                    if(currentActivatedImages.size() != 0) {
                        c.moveLeft(currentActivatedImages);
                    }
                    break;
                case "Up":
                    if(currentActivatedImages.size() != 0) {
                        c.moveUp(currentActivatedImages);
                    }
                    break;
                case "Down":
                    if(currentActivatedImages.size() != 0) {
                        c.moveDown(currentActivatedImages);
                    }
                    break;
                case "ZoomIn":
                    if(currentActivatedImages.size() != 0) {
                        c.zoomIn(3,currentActivatedImages);
                    }
                    break;
                case "ZoomOut":
                    if(currentActivatedImages.size() != 0) {
                        c.zoomOut(3,currentActivatedImages);
                    }
                    break;
                case "Undo":
                        c.undo();
                    break;
            }

            currentActivatedImages.clear();
            undoButtonRegulate();
        }
    }
}
