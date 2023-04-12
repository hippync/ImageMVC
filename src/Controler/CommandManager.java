package Controler;

import Controler.Commands.Command;
import Controler.Commands.Release;
import Controler.Commands.Scroll;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Classe singleton qui gère les commandes
 *
 * @author
 * @version H2023
 */
public class CommandManager implements Serializable {

    private static CommandManager instance = new CommandManager();  //Singleton
    private static LinkedList<Command> commandList; //Commands list

    private CommandManager() {

        commandList =  new LinkedList<Command>();
    }

    /**
     * Retourne le nombre de commandes dans la collection
     *
     * @return
     */
    public static int getNumberCommand() {

        return commandList.size();
    }

    /**
     * Ajoute une commande dans la collection
     *
     * @param com commande a ajouter
     */
    public static void addCommand(Command com) {

        commandList.add(com);
    }

    /**
     * Enleverla derniere commande dans la collection
     */
    public static void UndoLastCommand() {

        Command c = commandList.removeLast();

        //si c'est un release suivi d'un scroll
        if (c.getClass().equals(new Release().getClass())) {

            c = commandList.getLast();

            //le prochain est un scroll
            if (c.getClass().equals(new Scroll().getClass())) {

                //enelever tout les scrolls
                while (c.getClass().equals(new Scroll().getClass())) {

                    System.out.println("SHIT SHIT SHIT");
                    c.undo();
                    c = commandList.removeLast();
                }
            } else {

                c.undo();
            }
        } else {

            c.undo();
        }
    }

    /**
     * Retourne l'instance de la classe
     *
     * @return l'instance (SINGLETON)
     */
    public static CommandManager getInstance() {
        return instance;
    }
}
