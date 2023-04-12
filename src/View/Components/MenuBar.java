package View.Components;

import Controler.Controler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Barre de menu pour le contrôle des fichiers par l'utilisateur
 *
 * @author
 * @version H2023
 */
public class MenuBar extends JMenuBar {

    Controler c;

    //3 options
    JMenuItem load = new JMenuItem("Load Image");
    JMenuItem loadS = new JMenuItem("Load Serializable Image");
    JMenuItem saveS = new JMenuItem("Save Serializable Image");

    public static final String LOAD_PERSPECTIVE = "load";
    public static final String LOAD_S_PERSPECTIVE = "loadS";
    public static final String SAVE_S_PERSPECTIVE = "saveS";


    public MenuBar(Controler c) {

        this.c = c;
        JMenu m = new JMenu("File");

        //adding options
        m.add(load);
        m.addSeparator();
        m.add(loadS);
        m.add(saveS);

        load.setName(LOAD_PERSPECTIVE);
        loadS.setName(LOAD_S_PERSPECTIVE);
        saveS.setName(SAVE_S_PERSPECTIVE);

        load.addActionListener(new MenuBarActionListener());
        loadS.addActionListener(new MenuBarActionListener());
        saveS.addActionListener(new MenuBarActionListener());

        add(m);
    }

    /**
     * Écouteurs des bouttons dans la barre de menu
     *
     * @author Hansy Pierre
     * @version H2023
     */
    class MenuBarActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            JMenuItem button = (JMenuItem) e.getSource();

            /**
             * Dans chacune des options, une fenêtre de sélection aparrait
             * et envoie le "path" vers le controleur
             */
            switch(button.getName()){

                case LOAD_PERSPECTIVE :
                    System.out.println("LOAD BUTTON CLICKED...");

                    JFileChooser fc = new JFileChooser();

                    //A ENLEVER
                    fc.setCurrentDirectory(new File("/Users/hansypierre/Documents/ETS/H23/LOG121/ImageMVC"));

                    fc.setDialogTitle("Choose an image");
                    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    fc.showOpenDialog(load);

                    c.loadImages(fc.getSelectedFile().getAbsolutePath());
                    break;

                case LOAD_S_PERSPECTIVE :
                    System.out.println("LOAD SERIALIZABLE BUTTON CLICKED...");

                    JFileChooser fc2 = new JFileChooser();
                    fc2.setCurrentDirectory(new File("/Users/hansypierre/Documents/ETS/H23/LOG121/ImageMVC"));
                    fc2.setDialogTitle("Choose a .ser file");
                    fc2.showOpenDialog(loadS);

                    c.deSerialize(fc2.getSelectedFile().getAbsolutePath());

                    break;

                case SAVE_S_PERSPECTIVE :
                    System.out.println("SAVE SERIALIZABLE BUTTON CLICKED...");

                    JFileChooser fc3 = new JFileChooser();
                    fc3.setCurrentDirectory(new File("/Users/hansypierre/Documents/ETS/H23/LOG121/ImageMVC"));
                    fc3.setDialogTitle("Choose a location to save");

                    int userSelection = fc3.showSaveDialog(saveS);

                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        File save = fc3.getSelectedFile();

                        System.out.println(save.getAbsolutePath());

                            c.serialize(save.getAbsolutePath());
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + button.getName());
            }
        }
    }
}