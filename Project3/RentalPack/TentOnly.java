package RentalPack;

import java.util.GregorianCalendar;

/*****************************************************************
 Class that creates all Tent campsites

 @author Jake Irons
 @version Winter 2020
 *****************************************************************/
public class TentOnly extends CampSite {
    /** the number of people in a tent*/
    private int numberOfTenters;

    /*****************************************************************
     Default constructor
     *****************************************************************/
    public TentOnly() {
    }

    /*****************************************************************
     Constructor that sets guest name, check in date, estimated checkout,
     number of tenters, and actual checkout
     @param guestName name of guest
     @param checkIn date of checkin
     @param estimatedCheckOut date of estimated check out
     @param actualCheckOut date of check out
     @param numberOfTenters the number of people in a tent
     *****************************************************************/
    public TentOnly(String guestName,
                    GregorianCalendar checkIn,
                    GregorianCalendar estimatedCheckOut,
                    GregorianCalendar actualCheckOut,
                    int numberOfTenters) {
        super(guestName, checkIn, estimatedCheckOut, actualCheckOut);
        this.numberOfTenters = numberOfTenters;
    }

    /*****************************************************************
     Returns the number of tenters
     @return the number of tenters
     *****************************************************************/
    public int getNumberOfTenters() {
        return numberOfTenters;
    }

    /*****************************************************************
     Sets the number of tenters
     @param numberOfTenters the number of tenters
     *****************************************************************/
    public void setNumberOfTenters(int numberOfTenters) {
        this.numberOfTenters = numberOfTenters;
    }

    /*****************************************************************
     Gets the estimated/actual cost of camping trip
     @param checkOut the estimated/actual checkout date
     *****************************************************************/
    @Override
    public double getCost(GregorianCalendar checkOut) {
        double cost = 0;
        int numDays = numDays(checkIn, checkOut);
        if(this.getNumberOfTenters() < 10){
            cost = numDays * 10;
        }
        else{
            cost = numDays * 20;
        }
        return cost;
    }

    /*****************************************************************
     Displays all information regarding tent only campsite
     @return all information for tent campsite
     *****************************************************************/
    @Override
    public String toString() {
        return "TentOnly{" +
                "numberOfTenters=" + numberOfTenters + ", " +
                super.toString() +
                '}';
    }



}
