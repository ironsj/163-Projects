package surroundpack;

import javax.swing.*;

public class Surround4 {
    /**
     * This main method is used to be able to play the Surround4 game itself
     * @param args needed for java
     * @author Jake Irons, Jacob Merda
     * @version Winter 2020
     */
    public static void main (String[] args)
    {

        JMenuBar menus;
        JMenu fileMenu;
        JMenuItem quitItem;
        JMenuItem newGameItem;
        JMenuItem colorInfoItem;

        JFrame frame = new JFrame ("Surround");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        newGameItem = new JMenuItem("New Game");
        colorInfoItem = new JMenuItem("Color Info");

        fileMenu.add(quitItem);
        fileMenu.add(newGameItem);
        fileMenu.add(colorInfoItem);

        menus = new JMenuBar();
        menus.add(fileMenu);

        frame.setJMenuBar(menus);

        Surround4Panel panel = new Surround4Panel(quitItem, newGameItem, colorInfoItem);
        frame.add(panel);
        frame.setSize(905, 905);
        frame.setVisible(true);
    }
}