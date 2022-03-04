package changeJarPack;

import javax.swing.*;

/*****************************************************************
 Graphical representation of a change jar with various controls over the contents of the jar.

 @author Jake Irons
 @version Winter 2020
 *****************************************************************/

public class ChangeJarGUI {
    public static void main(String arg[]){

        /** menu for fileMenu*/
        JMenu fileMenu;

        /** menu item for quitItem*/
        JMenuItem quitItem;

        /** check box menu item for suspendItem*/
        JCheckBoxMenuItem suspendItem;

        /** menu bar for menus*/
        JMenuBar menus;

        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        suspendItem = new JCheckBoxMenuItem ("Suspend");
        fileMenu.add(suspendItem);
        fileMenu.add(quitItem);
        menus = new JMenuBar();

        //adds fileMenu to menu bar
        menus.add(fileMenu);

        //JFrame for gui and sets close operation
        JFrame gui = new JFrame("Change Jar");
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //adds main panel to gui
        ChangeJarPanelMain panel = new ChangeJarPanelMain(quitItem, suspendItem);
        gui.getContentPane().add(panel);

        //sets size of gui and makes visible
        gui.setSize(1200,800);
        gui.setJMenuBar(menus);
        gui.pack();
        gui.setVisible(true);
    }
}
