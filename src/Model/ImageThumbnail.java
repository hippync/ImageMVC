package Model;

import App.*;
import java.util.LinkedList;

/**
 * Classe type pour une vignette
 *
 * @author
 * @version H2023
 */
public class ImageThumbnail extends Image implements Subject, java.io.Serializable {

    //ObserverList
    private LinkedList<View> viewList = new LinkedList<View>();

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
}

