package RentalPack;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/*****************************************************************
 Class that creates all RV campsites

 @author Jake Irons
 @version Winter 2020
 *****************************************************************/
public class RV extends CampSite {
    /** the max power for a rv*/
    private int power;

    /*****************************************************************
     Default constructor
     *****************************************************************/
    public RV() {
    }

    /*****************************************************************
     Constructor that sets guest name, check in date, estimated checkout,
     max power, and actual checkout
     @param guestName name of guest
     @param checkIn date of checkin
     @param estimatedCheckOut date of estimated check out
     @param actualCheckOut date of check out
     @param power the max power
     *****************************************************************/
    public RV(String guestName, GregorianCalendar checkIn, GregorianCalendar estimatedCheckOut, GregorianCalendar actualCheckOut, int power) {
        super(guestName, checkIn, estimatedCheckOut, actualCheckOut);
        this.power = power;
    }

    /*****************************************************************
     Returns the max power
     @return the max power
     *****************************************************************/
    public int getPower() {
        return power;
    }

    /*****************************************************************
     Sets the max power
     @param power the max power
     *****************************************************************/
    public void setPower(int power) {
        this.power = power;
    }

    /*****************************************************************
     Gets the estimated/actual cost of camping trip
     @param checkOut the estimated/actual checkout date
     *****************************************************************/
    @Override
    public double getCost(GregorianCalendar checkOut) {
        double cost = 10;
        int numDays = numDays(checkIn, checkOut);
        if(this.power > 1000){
            cost += (numDays * 30);
        }
        else{
            cost += numDays * 20;
        }
        return cost;
    }

    /*****************************************************************
     Displays all information regarding RV campsite
     @return all information for RV campsite
     *****************************************************************/
    @Override
    public String toString() {
        return "RV{" +
                "power=" + power + ", " +
                super.toString() +
                '}';
    }


}
