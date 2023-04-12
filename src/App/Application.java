package App;

import View.Components.MainWindow;

/**
 * Panneau de réprésentation d'une vignette
 *
 * @author Hansy Pierre
 * @version H2023
 */
public class Application {

    //Main application
    public static void main(String[]args) {

        MainWindow mainWindow = new MainWindow();
        Thread t = new Thread(mainWindow);
        t.start();
    }
}
