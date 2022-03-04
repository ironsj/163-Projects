package changeJarPack;

import java.io.*;
import java.lang.Math;
import java.text.*;
import java.util.*;
import java.math.BigDecimal;
import javax.swing.*;

/*****************************************************************
 A change jar with various methods to manipulate the jar

 @author Jake Irons
 @version Winter 2020
 *****************************************************************/

public class ChangeJar{
    /** number of quarters */
    private int quarters;

    /** number of dimes */
    private int dimes;

    /** number of nickels */
    private int nickels;

    /** number of pennies */
    private int pennies;

    /** value that determines whether or not the jar can be changed */
    private static boolean mutateOn = true;

    /*****************************************************************
     Default constructor creates a jar with values of 0 for each coin
     *****************************************************************/
    public ChangeJar() {
        quarters = 0;
        dimes = 0;
        nickels = 0;
        pennies = 0;
    }

    /*****************************************************************
     Constructor creates a jar with a specified amount of money
     @param amount the amount of money that is in the jar
     *****************************************************************/
    public ChangeJar(double amount) {
        if((amount < 0) || !isValid(amount)){
            throw new IllegalArgumentException();
        }
        else {
            double tempAmount = amount;
            quarters = (int) (amount / 0.25);
            tempAmount -= 0.25 * quarters;
            tempAmount = Math.round(tempAmount * 100D) / 100D;
            dimes = (int) (tempAmount / 0.10);
            tempAmount = tempAmount - 0.10 * dimes;
            tempAmount = Math.round(tempAmount * 100D) / 100D;
            nickels = (int) (tempAmount / 0.05);
            tempAmount -= 0.05 * nickels;
            tempAmount = Math.round(tempAmount * 100D) / 100D;
            pennies = (int) (tempAmount / 0.01);
        }

    }

    /*****************************************************************
     Constructor creates a jar with a specified amount of money
     @param amount the amount of money that is in the jar
     *****************************************************************/
    public ChangeJar(String amount){
        double tempAmount = Double.valueOf(amount);
        if((tempAmount < 0) || !isValid(tempAmount)){
            throw new IllegalArgumentException();
        }
        quarters = (int) (tempAmount / 0.25);
        tempAmount -= 0.25 * quarters;
        tempAmount = Math.round(tempAmount * 100D) / 100D;
        dimes = (int) (tempAmount / 0.10);
        tempAmount = tempAmount - 0.10 * dimes;
        tempAmount = Math.round(tempAmount * 100D) / 100D;
        nickels = (int) (tempAmount / 0.05);
        tempAmount -= 0.05 * nickels;
        tempAmount = Math.round(tempAmount * 100D) / 100D;
        pennies = (int) (tempAmount / 0.01);
    }

    /*****************************************************************
     Constructor creates a jar that is equal to another jar
     @param other the other jar in which this jar will equal
     *****************************************************************/
    public ChangeJar(ChangeJar other) {
        this.quarters = other.quarters;
        this.dimes = other.dimes;
        this.nickels = other.nickels;
        this.pennies = other.pennies;
    }

    /*****************************************************************
     Constructor creates a jar with a specified number for each type of coin
     @param quarters the number of quarters that is in the jar
     @param dimes the number of dimes that is in the jar
     @param nickels the number of nickels that is in the jar
     @param pennies the number of pennies that is in the jar
     *****************************************************************/
    public ChangeJar(int quarters, int dimes, int nickels, int pennies) {
        super();

        if (quarters < 0 || dimes < 0 || nickels < 0 || pennies < 0) {
            throw new IllegalArgumentException();
        }

        this.quarters = quarters;
        this.dimes = dimes;
        this.nickels = nickels;
        this.pennies = pennies;
    }

    /*****************************************************************
     Checks if this jar is equal to another jar
     @param other the other jar in which we check if it is equal
     @return the value if the jars are equal, equal=true, not equal=false
     *****************************************************************/
    public boolean equals(Object other) {
        boolean isEqual = false;
        ChangeJar newJar = (ChangeJar) other;
        if(this.getAmount() == newJar.getAmount()) {
            isEqual = true;
        }
        else{
            isEqual = false;
        }
        return isEqual;
    }

    /*****************************************************************
     Checks if two jars are equal
     @param jar1 the first jar
     @param jar2 the second jar
     @return the value if the jars are equal, equal=true, not equal=false
     *****************************************************************/
    public static boolean equals(ChangeJar jar1, ChangeJar jar2) {
        boolean isEqual = false;
        if(jar1.equals(jar2)) {
            isEqual = true;
        }
        else {
            isEqual = false;
        }
        return isEqual;
    }

    /*****************************************************************
     Checks if this jar is equal, greater than, or less than another jar
     @param other the jar being compared
     @return returns 1 if this jar is greater, -1 if less than, 0 if equal
     *****************************************************************/
    public int compareTo(ChangeJar other) {
        int tmp = -2;
        if((this.getAmount() - other.getAmount()) > 0) {
            tmp = 1;
        }
        else if((this.getAmount() - other.getAmount()) < 0) {
            tmp = -1;
        }
        else{
            tmp = 0;
        }
        return tmp;
    }

    /*****************************************************************
     Checks if this jar is equal, greater than, or less than another jar
     @param jar1 the first jar being compared
     @param jar2 the second jar being compared
     @return returns 1 if the first jar is greater, -1 if less than, 0 if equal
     *****************************************************************/
    public static int compareTo(ChangeJar jar1, ChangeJar jar2) {
        int tmp = -2;
        if((jar1.getAmount() - jar2.getAmount()) > 0) {
            tmp = 1;
        }
        else if((jar1.getAmount() - jar2.getAmount()) < 0) {
            tmp = -1;
        }
        else{
            tmp = 0;
        }
        return tmp;
    }

    /*****************************************************************
     Subtracts specified amount of each type of coin from jar
     @param quarters the number of quarters being subtracted
     @param dimes the number of dimes being subtracted
     @param nickels the number of nickels being subtracted
     @param pennies the number of pennies being subtracted
     *****************************************************************/
    public void subtract(int quarters, int dimes, int nickels, int pennies){
        if(mutateOn) {
            if (quarters < 0 || dimes < 0 || nickels < 0 || pennies < 0) {
                throw new IllegalArgumentException();
            }
            if ((this.quarters - quarters < 0) || (this.dimes - dimes < 0) ||
                    (this.nickels - nickels < 0) || (this.pennies - pennies < 0)) {
                throw new IllegalArgumentException();
            }
            this.quarters -= quarters;
            this.dimes -= dimes;
            this.nickels -= nickels;
            this.pennies -= pennies;
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "Cannot change jar. Unselect suspend to take this action.");
        }
    }

    /*****************************************************************
     Subtracts number of each coin in another jar from this jar
     @param other the other jar being subtracted
     *****************************************************************/
    public void subtract(ChangeJar other){
        if(mutateOn) {
            if ((this.quarters - other.quarters < 0) || (this.dimes - other.dimes < 0) ||
                    (this.nickels - other.nickels < 0) || (this.pennies - other.pennies < 0)) {
                throw new IllegalArgumentException();
            }
            this.quarters -= other.quarters;
            this.dimes -= other.dimes;
            this.nickels -= other.nickels;
            this.pennies -= other.pennies;
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "Cannot change jar. Unselect suspend to take this action.");
        }
    }

    /*****************************************************************
     Decreases number of pennies by one
     *****************************************************************/
    public void dec() {
        if(mutateOn) {
            if (this.getPennies() == 0) {
                throw new IllegalArgumentException();
            }
            else {
                pennies -= 1;
            }
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "Cannot change jar. Unselect suspend to take this action.");
        }
    }

    /*****************************************************************
     Adds specified amount of each type of coin from jar
     @param quarters the number of quarters being added
     @param dimes the number of dimes being added
     @param nickels the number of nickels being added
     @param pennies the number of pennies being added
     *****************************************************************/
    public void add(int quarters, int dimes, int nickels, int pennies){
        if(mutateOn) {
            if (quarters < 0 || dimes < 0 || nickels < 0 || pennies < 0) {
                throw new IllegalArgumentException();
            }
            this.quarters += quarters;
            this.dimes += dimes;
            this.nickels += nickels;
            this.pennies += pennies;
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "Cannot change jar. Unselect suspend to take this action.");
        }
    }

    /*****************************************************************
     Adds number of each coin in another jar from this jar
     @param other the other jar being added
     *****************************************************************/
    public void add(ChangeJar other) {
        if(mutateOn) {
            this.quarters += other.quarters;
            this.dimes += other.dimes;
            this.nickels += other.nickels;
            this.pennies += other.pennies;
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "Cannot change jar. Unselect suspend to take this action.");
        }
    }

    /*****************************************************************
     Increases number of pennies by one
     *****************************************************************/
    public void inc() {
        if(mutateOn) {
            pennies += 1;
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "Cannot change jar. Unselect suspend to take this action.");
        }
    }

    /*****************************************************************
     Displays the number of each type of coin in the jar
     @return the number of each coin
     *****************************************************************/
    public String toString() {
        String stringJar = this.quarters + " Quarter" + ((quarters != 1) ? "s" : "") + "\n" +
                this.dimes + " Dime" + ((dimes != 1) ? "s" : "") + "\n" +
                this.nickels + " Nickel" + ((nickels != 1) ? "s" : "") + "\n" +
                this.pennies + " Penn" + ((pennies != 1) ? "ies" : "") + ((pennies == 1) ? "y" : "") + "\n";
        return stringJar;
    }

    /*****************************************************************
     Saves the jar to a file
     @param fileName the name of the saved file
     *****************************************************************/
    public void save(String fileName) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(
                    fileName)));
            out.write(this.quarters + " " + this.dimes + " " + this.nickels + " " + this.pennies);
        }
        catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"File could not be saved");
        }
        out.close();
    }

    /*****************************************************************
     Loads jar from a saved file
     @param fileName the name of the file being loaded
     *****************************************************************/
    public void load(String fileName) {
        if (mutateOn){
            Scanner scanner = null;
            try {
                scanner = new Scanner(new File(fileName));
                while (scanner.hasNext()) {
                    // reading the fields from the record one at the time
                    this.setQuarters(scanner.nextInt());
                    this.setDimes(scanner.nextInt());
                    this.setNickels(scanner.nextInt());
                    this.setPennies(scanner.nextInt());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"File not found");
            }
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "Cannot change jar. Unselect suspend to take this action.");
        }
    }

    /*****************************************************************
     Takes a specified amount out of jar given that it is possible with the available coins
     @param amount the amount being taken out of the jar
     @return returns jar with the amount taken out, and if not possible returns null
     *****************************************************************/
    public ChangeJar takeOut(double amount){
        if(mutateOn) {
            int change = (int) (Math.ceil(amount * 100));
            int dollars = Math.round((int) change / 100);
            change = change % 100;
            int q = Math.round((int) change / 25);
            change = change % 25;
            int d = Math.round((int) change / 10);
            change = change % 10;
            int n = Math.round((int) change / 5);
            change = change % 5;
            int p = Math.round((int) change / 1);
            q += (dollars * 4);
            ChangeJar tempJar = new ChangeJar(q, d, n, p);
            if (!isValid(amount) || amount < 0 || (this.pennies - tempJar.pennies < 0) ||
                    (this.dimes - tempJar.dimes < 0) || (this.nickels - tempJar.nickels < 0) ||
                    (this.quarters - tempJar.quarters < 0)) {
                throw new IllegalArgumentException();
            }
            if ((this.getAmount() >= amount) && (this.pennies - tempJar.pennies >= 0) &&
                    (this.dimes - tempJar.dimes >= 0) && (this.nickels - tempJar.nickels >= 0)
                    && (this.quarters - tempJar.quarters >= 0)) {
                this.subtract(tempJar);
                return tempJar;
            } else {
                return null;
            }
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "Cannot change jar. Unselect suspend to take this action.");
            return null;
        }
    }

    /*****************************************************************
     Takes specified amount of each type of coin out of jar
     @param quarters the number of quarters being taken out
     @param dimes the number of dimes being taken out
     @param nickels the number of nickels being taken out
     @param pennies the number of pennies being taken out
     *****************************************************************/
    public void takeOut(int quarters, int dimes, int nickels, int pennies) {
        if(mutateOn) {
            ChangeJar tempJar = new ChangeJar(quarters, dimes, nickels, pennies);
            if (quarters < 0 || dimes < 0 || nickels < 0 || pennies < 0 || this.compareTo(tempJar) == -1) {
                throw new IllegalArgumentException();
            }
            if ((this.quarters - tempJar.quarters < 0) || (this.dimes - tempJar.dimes < 0) ||
                    (this.nickels - tempJar.nickels < 0) || (this.pennies - tempJar.pennies < 0)) {
                throw new IllegalArgumentException();
            } else if (this.compareTo(tempJar) == 1 && (this.quarters - quarters >= 0) &&
                    (this.dimes - dimes >= 0) && (this.nickels - nickels >= 0) && (this.pennies - pennies >= 0)) {
                this.subtract(tempJar);
            }
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "Cannot change jar. Unselect suspend to take this action.");
        }
    }

    /*****************************************************************
     Takes the amount in another jar out of this jar given that it is possible with the available coins
     @param other the other jar that we are taking out of this jar
     *****************************************************************/
    public void takeOut(ChangeJar other) {
        if(mutateOn) {
            if (this.compareTo(other) == -1 || (this.quarters - other.quarters < 0) ||
                    (this.dimes - other.dimes < 0) || (this.nickels - other.nickels < 0)
                    || (this.pennies - other.pennies < 0)) {
                throw new IllegalArgumentException();
            }
            else if ((this.compareTo(other) == 1) && (this.quarters - other.quarters >= 0) &&
                    (this.dimes - other.dimes >= 0) && (this.nickels - other.nickels >= 0)
                    && (this.pennies - other.pennies >= 0)) {
                this.subtract(other);
            }
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "Cannot change jar. Unselect suspend to take this action.");
        }
    }

    /*****************************************************************
     Turns on and off the ability to change the jar
     @param on value that determines if jar can be changed, on=true, off=false
     *****************************************************************/
    public static void mutate(boolean on) {
        on = !on;
        if(on){
            mutateOn = true;
        }
        else{
            mutateOn = false;
        }
    }

    /*****************************************************************
     Checks to make sure inputs of dollar amounts have two or less decimal places
     @param amount the dollar amount being checked
     @return the value of whether or not inputted number has the proper amount of decimal places
     *****************************************************************/
    public boolean isValid(double amount) {
        boolean validDecimal = false;
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(amount));
        int intValue = bigDecimal.intValue();
        String decimalPart = bigDecimal.subtract(new BigDecimal(intValue)).toPlainString();
        if (decimalPart.length() == 4 || decimalPart.length() == 3) {
            validDecimal = true;
        }
        else {
            validDecimal = false;
        }
        return validDecimal;
    }

    /*****************************************************************
     Changes the amount of money in the jar to pennies
     @param temp the jar that is being converted to pennies
     *****************************************************************/
    private static int convertToPennies (ChangeJar temp) {
        return (temp.quarters * 25) + (temp.dimes * 10) + (temp.nickels * 5) + temp.pennies;
    }

    /*****************************************************************
     Gives the number of quarters in jar
     @return the number of quarters
     *****************************************************************/
    public int getQuarters() {
        return quarters;
    }

    /*****************************************************************
     Sets the number of quarters in the jar
     @param quarters the desired number of quarters
     *****************************************************************/
    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    /*****************************************************************
     Gives the number of dimes in jar
     @return the number of dimes
     *****************************************************************/
    public int getDimes() {
        return dimes;
    }

    /*****************************************************************
     Sets the number of dimes in the jar
     @param dimes the desired number of dimes
     *****************************************************************/
    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    /*****************************************************************
     Gives the number of nickels in jar
     @return the number of nickels
     *****************************************************************/
    public int getNickels() {
        return nickels;
    }

    /*****************************************************************
     Sets the number of nickels in the jar
     @param nickels the desired number of nickels
     *****************************************************************/
    public void setNickels(int nickels) {
        this.nickels = nickels;
    }

    /*****************************************************************
     Gives the number of pennies in jar
     @return the number of pennies
     *****************************************************************/
    public int getPennies() {
        return pennies;
    }

    /*****************************************************************
     Sets the number of pennies in the jar
     @param pennies the desired number of pennies
     *****************************************************************/
    public void setPennies(int pennies) {
        this.pennies = pennies;
    }

    /*****************************************************************
     Gives the amount of money in the jar
     @return the amount of money in the jar
     *****************************************************************/
    public double getAmount() {
        return convertToPennies(this) / 100.0;
    }


    public static void main(String[] args) {
        //testing constructors with string
        ChangeJar s = new ChangeJar("2.82");
        System.out.println("2.82 Amount: \n" + s.getAmount());
        s = new ChangeJar("8");
        System.out.println("8.00 Amount: \n" + s.getAmount());
        s = new ChangeJar(".28");
        System.out.println("0.28 Amount: \n" + s.getAmount());

        //testing add method and constructor
        ChangeJar s1 = new ChangeJar();
        System.out.println("0.00 Amount: \n" + s1.getAmount());
        s1.add(1,1,1,100);
        System.out.println("1,1,1,100 Amount: \n" + s1);

        //testing dec and inc methods and constructor with double
        ChangeJar s2 = new ChangeJar(41.99);
        s2.add(0,0,0,99);
        for (int i = 0; i < 100; i++){
            s2.dec();
        }
        System.out.println("41.98 amount: \n" + s2.getAmount());
        for(int i = 0; i < 100; i++){
            s2.inc();
        }
        System.out.println("42.98 amount: \n" + s2.getAmount());
        ChangeJar temp1 = new ChangeJar(1.00);
        s2.add(temp1);
        System.out.println("43.98 amount: \n" + s2.getAmount());

        //testing subtract method and constructor with values for change
        ChangeJar s3 = new ChangeJar(1,1,1,1);
        System.out.println("1,1,1,1: \n" + s3.toString());
        s3.subtract(1,1,1,1);
        System.out.println("0,0,0,0: \n" + s3.toString());
        s3.add(6,0,0,0);
        ChangeJar temp2 = new ChangeJar(0.50);
        s3.subtract(temp2);
        System.out.println("1.00 amount: \n" + s3.getAmount());

        //testing load and save methods and constructor with another object
        ChangeJar s4 = new ChangeJar(s3);
        System.out.println("4,0,0,0: \n" + s4.toString());
        s4.save("testMain");
        ChangeJar s5 = new ChangeJar(4,1,1,1);
        System.out.println("1.16 amount: \n" + s5.getAmount());
        s5.load("testMain");
        System.out.println("1.00 amount: \n" + s5.getAmount());

        //testing equal methods
        ChangeJar s6 = new ChangeJar(6.00);
        ChangeJar s7 = new ChangeJar(7.00);
        ChangeJar s8 = new ChangeJar(6.00);
        ChangeJar s9 = new ChangeJar(0.75);
        System.out.println("false value:\n" + s6.equals(s9));
        System.out.println("true value:\n" + s6.equals(s8));
        System.out.println("false value:\n" + s6.equals(s7, s9));

        //testing compare methods
        ChangeJar t = new ChangeJar(6.00);
        ChangeJar t1 = new ChangeJar(7.00);
        ChangeJar t2 = new ChangeJar(6.00);
        ChangeJar t3 = new ChangeJar(0.75);
        System.out.println("0 value:\n" + t.compareTo(t2));
        System.out.println("-1 value:\n" + t.compareTo(t1));
        System.out.println("1 value:\n" + t.compareTo(t3));
        System.out.println("0 value:\n" + t.compareTo(t, t2));
        System.out.println("-1 value:\n" + t.compareTo(t, t1));
        System.out.println("1 value:\n" + t.compareTo(t, t3));

        //testing takeout methods
        ChangeJar t4= new ChangeJar(5,5,5,5);
        ChangeJar t5 = new ChangeJar(1,1,1,1);
        t4.takeOut(3,3,3,3);
        System.out.println("2,2,2,2\n" + t4.toString());
        t4.takeOut(t5);
        System.out.println("1,1,1,1\n" + t4.toString());
        t4.takeOut(0.40);
        System.out.println("0,0,0,1\n" + t4.toString());

    }






}