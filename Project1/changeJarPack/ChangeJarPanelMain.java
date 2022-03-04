package changeJarPack;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*****************************************************************
 Creates the file menu within the ChangeJarGUI

 @author Joanne Programmer
 @version Winter 2020
 *****************************************************************/


public class ChangeJarPanelMain extends JPanel {
    /** menu items for quitItem and suspendItem */
    private JMenuItem quitItem;
    private JMenuItem suspendItem;

    /*****************************************************************
     Constructor creates a main panel for the GUI
     *****************************************************************/
    public ChangeJarPanelMain (JMenuItem quitItem, JMenuItem suspendItem) {
        JPanel panel = new JPanel();
        panel.add(new MyChangeJarPanel());
        panel.add(new MyChangeJarPanel());
        panel.add(new MyChangeJarPanel());
        add(panel);

        this.quitItem = quitItem;
        this.suspendItem = suspendItem;

        //registers listeners
        quitItem.addActionListener(new Mylistener());
        suspendItem.addActionListener(new Mylistener());
    }

    /****************************************************************
     Inner class to respond to the user action

     ****************************************************************/
    private class Mylistener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == quitItem){
                if(suspendItem.isSelected()){
                    ChangeJar.mutate(suspendItem.isSelected());
                }
                System.exit(1);
            }
            if(e.getSource() == suspendItem){
                ChangeJar.mutate(suspendItem.isSelected());
            }
        }

    }
}
