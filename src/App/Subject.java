package App;

/**
 * interface Subject pour la patron Observateur
 *
 * @author
 * @version H2023
 */
public interface Subject {

    public void notifyViews();
    public void attachView(View v);
    public void dettachView(View v);
}
