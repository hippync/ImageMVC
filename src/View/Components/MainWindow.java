package View.Components;

import Controler.Controler;
import Model.ImageViewPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

/**
 * Fenetre principale appelée du main
 *
 * @author
 * @version H2023
 */
public class MainWindow extends JFrame implements Runnable {

    JPanel mainPanel;           //ContentPane

    //Views selected by user (Highlighted)
    private LinkedList<ImageViewPoint> currentActivatedImages = new LinkedList<ImageViewPoint>();

    //Panels
    LowerPanel lowerPanel;
    TopPanel topPanel;

    Controler c;        //Controler that operate the software

    //Runnable from main
    public void run() {

        createControler();

        setFocusable(true);
        requestFocus();

        createGUI();
        addMenuBar();
        addMainPanel();
        addTopPanel();
        addLowerPanels();
        addToolbar();

        //keyboardListene
        addKeyListener(new MyKeyListener());

        setVisible(true);
    }

    /**
     * Controler creation
     */
    private void createControler() {

        c = new Controler();
    }


    /**
     * View Creation
     */
    private void createGUI() {

        //Title
        setTitle("ImageViewer 2.0");

        // 1200 * 800
        setSize(1200,800);

        //can't expand
        setResizable(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Menu bar
     */
    private void addMenuBar() {

        setJMenuBar(new MenuBar(c));
    }

    /**
     * Main Panel
     */
    private void addMainPanel() {

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);
    }

    /**
     * Top Panel
     */
    private void addTopPanel() {

        topPanel = new TopPanel(c);
        mainPanel.add(topPanel);
    }

    /**
     * Lower Panel
     */
    private void addLowerPanels() {

        lowerPanel = new LowerPanel(c);
        mainPanel.add(lowerPanel,BorderLayout.PAGE_END);
    }

    /**
     * Toolbar
     */
    private void addToolbar() {

        mainPanel.add(new Toolbar(c,lowerPanel.getLeftPanel(),
                lowerPanel.getRightPanel()), BorderLayout.PAGE_START);
    }

    /**
     * Obtient le ou les images activé par l'utilisateur
     */
    private void getCurrentActivatedImages() {

        if(lowerPanel.getLeftPanel().getIsActivated()) {
            currentActivatedImages.add(lowerPanel.getLeftPanel().getImage());
        }
        if(lowerPanel.getRightPanel().getIsActivated()) {
            currentActivatedImages.add(lowerPanel.getRightPanel().getImage());
        }
    }

    public class MyKeyListener implements KeyListener {

        public final int KEY_W = 87;
        public final int KEY_A = 65;
        public final int KEY_D = 68;
        public final int KEY_S = 83;

        public final int KEY_UP = 38;
        public final int KEY_LEFT = 37;
        public final int KEY_RIGHT = 39;
        public final int KEY_DOWN = 40;

        public final int KEY_PLUS = 61;
        public final int KEY_MINUS = 45;

        public final int KEY_CTRL = 17;
        public final int KEY_Z = 90;

        private LinkedList<Integer> keysPressed = new LinkedList<Integer>();


        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

            keysPressed.add(e.getKeyCode());

            getCurrentActivatedImages();

            int key = e.getKeyCode();

            switch (key) {

                case KEY_D : case KEY_RIGHT:
                    if(currentActivatedImages.size() != 0) {
                        c.moveRight(currentActivatedImages);
                    }
                    break;

                case KEY_A: case KEY_LEFT:
                    if(currentActivatedImages.size() != 0) {
                        c.moveLeft(currentActivatedImages);
                    }
                    break;

                case KEY_W: case KEY_UP:
                    if(currentActivatedImages.size() != 0) {
                        c.moveUp(currentActivatedImages);
                    }
                    break;

                case KEY_S: case KEY_DOWN:
                    if(currentActivatedImages.size() != 0) {
                        c.moveDown(currentActivatedImages);
                    }
                    break;
                case KEY_PLUS:
                    if(currentActivatedImages.size() != 0) {
                        c.zoomIn(3,currentActivatedImages);
                    }
                    break;
                case KEY_MINUS:
                    if(currentActivatedImages.size() != 0) {
                        c.zoomOut(3,currentActivatedImages);
                    }
                    break;
            }

            //CTRL+Z (undo)
            if(key == KEY_Z) {
                if(keysPressed.getFirst() == KEY_CTRL){
                    c.undo();
                }
            }

            currentActivatedImages.clear();
            Toolbar.undoButtonRegulate();

        }

        @Override
        public void keyReleased(KeyEvent e) {

            keysPressed.remove();
        }
    }
}
