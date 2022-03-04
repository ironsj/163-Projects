package changeJarPack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.*;
import java.text.NumberFormat;
import java.util.Random;

/*****************************************************************
 Creates panels within the ChangeJarGui in which ChangeJar objects exist

 @author Joanne Programmer
 @version Winter 2020
 *****************************************************************/


public class MyChangeJarPanel extends JPanel {

    /** a change jar */
    private ChangeJar jar;

    /** formats currency */
    NumberFormat fmt = NumberFormat.getCurrencyInstance();

    /** buttons for various controls of the change jar */
    JButton takeOutButton;
    JButton decButton;
    JButton incButton;
    JButton saveButton;
    JButton loadButton;
    JButton addButton;
    JButton takeOutAmountButton;
    JButton specificsButton;

    /** text fields for quarters, dimes, nickels and pennies */
    JTextField qField, dField, nField, pField;

    /** label for credits, qLabel, dLabel, nLabel, pLabel*/
    JLabel credits;
    JLabel qLabel, dLabel, nLabel, pLabel;

    /*****************************************************************
     Constructor creates a panel for the GUI
     *****************************************************************/
    public MyChangeJarPanel(){

        /** creates random number which will be parameters for object */
        Random rand = new Random();
        int q = rand.nextInt(1001);
        int d = rand.nextInt(1001);
        int n = rand.nextInt(1001);
        int p = rand.nextInt(1001);
        // create the  object as well as the ChangeJarGUI Frame
        jar = new ChangeJar(q,d,n,p);





        // set the layout to GridBag
        setLayout(new GridLayout(10,2));
        setBackground(Color.lightGray);

        // get labels with corresponding amounts and place on ChangeJarGUI
        qField = new JTextField("0", 3);
        add(qField);
        qLabel = new JLabel();
        qLabel.setText("Quarters: " + Integer.toString(jar.getQuarters()));
        add(qLabel);


        dField = new JTextField("0", 3);
        add(dField);
        dLabel = new JLabel();
        dLabel.setText("Dimes: " + Integer.toString(jar.getDimes()));
        add(dLabel);

        nField = new JTextField("0", 3);
        add(nField);
        nLabel = new JLabel();
        nLabel.setText("Nickels: " + Integer.toString(jar.getNickels()));
        add(nLabel);

        pField = new JTextField("0", 3);
        add(pField);
        pLabel = new JLabel();
        pLabel.setText("Pennies: " + Integer.toString(jar.getPennies()));
        add(pLabel);

        //places buttons for various controls on ChangeJarGUI
        addButton = new JButton("Add");
        add(addButton);

        incButton = new JButton("Increment");
        add(incButton);

        decButton = new JButton("Decrement");
        add(decButton);

        takeOutButton = new JButton("Take Out");
        add(takeOutButton);

        takeOutAmountButton = new JButton("Take Out Specified Amount");
        add(takeOutAmountButton);

        saveButton = new JButton("Save Class");
        add(saveButton);

        loadButton = new JButton("Load Class");
        add(loadButton);

        specificsButton = new JButton("Set to Specfic Amounts");
        add(specificsButton);

        // gets label for amount with corresponding value and places on ChangeJarGUI
        credits = new JLabel();
        credits.setText(fmt.format(jar.getAmount()));
        add(new JLabel("Total:"));
        add(credits);

        // register the listeners
        takeOutButton.addActionListener(new ButtonListener());
        decButton.addActionListener(new ButtonListener());
        incButton.addActionListener(new ButtonListener());
        saveButton.addActionListener(new ButtonListener());
        loadButton.addActionListener(new ButtonListener());
        addButton.addActionListener(new ButtonListener());
        takeOutAmountButton.addActionListener((new ButtonListener()));
        specificsButton.addActionListener((new ButtonListener()));
    }


    /****************************************************************
     Inner class to respond to the user action

     ****************************************************************/
    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event){

            int quarters, dimes, nickels, pennies;
            //responds to takeOutButton
            if (event.getSource() == takeOutButton){
                try{
                    quarters = Integer.parseInt(qField.getText());
                    dimes = Integer.parseInt(dField.getText());
                    nickels = Integer.parseInt(nField.getText());
                    pennies = Integer.parseInt(pField.getText());
                    jar.takeOut(quarters,dimes,nickels,pennies);
                }catch(NumberFormatException io){
                    JOptionPane.showMessageDialog(null,"Enter an integer in all fields");
                }catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Not enough specified coins for this operation/Enter a positive integer");
                }
            }
            //responds to decButton
            if(event.getSource() == decButton){
                try{
                    jar.dec();
                }catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Not enough specified coins for this operation");
                }
            }
            //responds to incButton
            if(event.getSource() == incButton){
                try{
                    jar.inc();
                }catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Cannot increment");
                }
            }
            //responds to saveButton
            if(event.getSource() == saveButton){
                String fileName;
                fileName = JOptionPane.showInputDialog("What would you like to name the file? ");
                jar.save(fileName);
            }
            //responds to loadButton
            if(event.getSource() == loadButton){
                String fileName;
                fileName = JOptionPane.showInputDialog("What is the name of the file? ");
                jar.load(fileName);
            }
            //responds to addButton
            if(event.getSource() == addButton){
                try{
                    quarters = Integer.parseInt(qField.getText());
                    dimes = Integer.parseInt(dField.getText());
                    nickels = Integer.parseInt(nField.getText());
                    pennies = Integer.parseInt(pField.getText());
                    jar.add(quarters,dimes,nickels,pennies);
                }catch(NumberFormatException io){
                    JOptionPane.showMessageDialog(null,"Enter an integer in all fields");
                }catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Cannot have negative integer in field");
                }
            }
            //responds to takeOutAmountButton
            if (event.getSource() == takeOutAmountButton){
                try{
                    String stringAmount = JOptionPane.showInputDialog("How much would you like to take out of the jar? ");
                    double amount = Double.parseDouble(stringAmount);
                    jar.takeOut(amount);

                }catch(NumberFormatException io){
                    JOptionPane.showMessageDialog(null,"Enter an appropriate dollar amount");
                }catch(IllegalArgumentException e){
                    JOptionPane.showMessageDialog(null,"Not appropriate dollar amount/ too large an amount/ can't make amount out of available change");
                }
            }
            //responds to specificsButton
            if (event.getSource() == specificsButton) {
                    String qAmount = JOptionPane.showInputDialog("How many quarters? ");
                    int twoFive = Integer.parseInt(qAmount);
                    jar.setQuarters(twoFive);
                    String dAmount = JOptionPane.showInputDialog("How many dimes? ");
                    int ten = Integer.parseInt(dAmount);
                    jar.setDimes(ten);
                    String nAmount = JOptionPane.showInputDialog("How many nickels? ");
                    int five = Integer.parseInt(nAmount);
                    jar.setNickels(five);
                    String pAmount = JOptionPane.showInputDialog("How many pennies? ");
                    int one = Integer.parseInt(pAmount);
                    jar.setPennies(one);
            }



            // updates the labels
            credits.setText(fmt.format(jar.getAmount()));
            qLabel.setText("Quarters: " + Integer.toString(jar.getQuarters()));
            dLabel.setText("Dimes: " + Integer.toString(jar.getDimes()));
            nLabel.setText("Nickels: " + Integer.toString(jar.getNickels()));
            pLabel.setText("Pennies: " + Integer.toString(jar.getPennies()));
        }

    }

}
