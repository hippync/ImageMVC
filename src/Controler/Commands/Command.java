package Controler.Commands;

/**
 * Interface des commandes
 *
 * @author
 * @version H2023
 */
public interface Command {

    public void execute();
    public void undo();    
}
