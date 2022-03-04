package RentalPack;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/*****************************************************************
 Class that is the parent of all Campsites, both RV and Tent.
 Contains functions that set and get values for all Campsites

 @author Jake Irons
 @version Winter 2020
 *****************************************************************/
public abstract class CampSite implements Serializable {
    /** universal version identifier for a Serializable class */
    private static final long serialVersionUID = 1L;

    /** guest name for camp site */
    protected String guestName;

    /** date of check in */
    protected GregorianCalendar checkIn;

    /** estimated date of check out*/
    protected GregorianCalendar estimatedCheckOut;

    /** actual checkout date */
    protected GregorianCalendar actualCheckOut;

    /** number of days guest is overdue */
    protected int numDaysOverDue;

    /*****************************************************************
     Default constructor
     *****************************************************************/
    public CampSite() {
    }

    /*****************************************************************
     abstract method to determine cost depending if it is an RV or Tent
     @param checkOut the estimated/actual date of checkout
     @return the total cost
     *****************************************************************/
    public abstract double getCost(GregorianCalendar checkOut);

    /*****************************************************************
     Method to find the number of days between two dates
     @param checkIn the date the guest checks in
     @param checkOut the estimated/actual date of checkout
     @return the total cost
     *****************************************************************/
    public int numDays(GregorianCalendar checkIn, GregorianCalendar checkOut){
        checkIn = this.getCheckIn();
        int numDays = 0;
        SimpleDateFormat myFormat = new SimpleDateFormat("MM dd yyyy");
        String checkOutFormat = checkOut.get(GregorianCalendar.MONTH) + 1 +  " " + checkOut.get(GregorianCalendar.DAY_OF_MONTH) +
                " " + (checkOut.get(GregorianCalendar.YEAR));
        String checkInFormat = checkIn.get(GregorianCalendar.MONTH) + 1 + " " + checkIn.get(GregorianCalendar.DAY_OF_MONTH) +
                " " + (checkIn.get(GregorianCalendar.YEAR));
        try {
            Date checkInDate = myFormat.parse(checkInFormat);
            Date checkOutDate = myFormat.parse(checkOutFormat);
            double difference = checkOutDate.getTime() - checkInDate.getTime();
            double daysBetween = Math.ceil(difference / (1000*60*60*24));
            numDays = (int)daysBetween;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return numDays;
    }

    /*****************************************************************
     Constructor that sets guest name, check in date, estimated checkout,
     and actual checkout
     @param guestName name of guest
     @param checkIn date of checkin
     @param estimatedCheckOut date of estimated check out
     @param actualCheckOut date of check out
     *****************************************************************/
    public CampSite(String guestName,
                    GregorianCalendar checkIn,
                    GregorianCalendar estimatedCheckOut,
                    GregorianCalendar actualCheckOut) {
        this.guestName = guestName;
        this.checkIn = checkIn;
        this.estimatedCheckOut = estimatedCheckOut;
        this.actualCheckOut = actualCheckOut;
    }

    /*****************************************************************
     Returns name of guest at campsite
     @return name of guest
     *****************************************************************/
    public String getGuestName() {
        return guestName;
    }

    /*****************************************************************
     Sets name of guest at campsite
     @param guestName name of the guest
     *****************************************************************/
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    /*****************************************************************
     Returns the date of check in
     @return the date of check in
     *****************************************************************/
    public GregorianCalendar getCheckIn() {
        return checkIn;
    }

    /*****************************************************************
     sets date of check in
     @param checkIn the date of check in
     *****************************************************************/
    public void setCheckIn(GregorianCalendar checkIn) {
        this.checkIn = checkIn;
    }

    /*****************************************************************
     Returns the date of estimated check out
     @return the date of estimated check out
     *****************************************************************/
    public GregorianCalendar getEstimatedCheckOut() {
        return estimatedCheckOut;
    }

    /*****************************************************************
     Sets the estimated check out date
     @param estimatedCheckOut the date of estimated check out
     *****************************************************************/
    public void setEstimatedCheckOut(GregorianCalendar estimatedCheckOut) {
        this.estimatedCheckOut = estimatedCheckOut;
    }

    /*****************************************************************
     Returns the actual checkout date
     @return the actual checkout date
     *****************************************************************/
    public GregorianCalendar getActualCheckOut() {
        return actualCheckOut;
    }

    /*****************************************************************
     Sets the actual checkout date
     @param actualCheckOut the actual checkout date
     *****************************************************************/
    public void setActualCheckOut(GregorianCalendar actualCheckOut) {
        this.actualCheckOut = actualCheckOut;
    }

    /*****************************************************************
     Sets the number of days a guest is over due
     @param numDaysOverDue the number of days the guest is over due
     *****************************************************************/
    public void setOverDueDays(int numDaysOverDue){
        this.numDaysOverDue = numDaysOverDue;
    }

    /*****************************************************************
     Returns the number of days the guest is overdue
     @return the number of days the guest is overdue
     *****************************************************************/
    public int getNumDaysOverDue(){
        return this.numDaysOverDue;
    }

    /*****************************************************************
     Displays all information for campsite
     @return the information for campsite
     *****************************************************************/
    @Override
    public String toString() {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        String checkInOnDateStr;
        if (getCheckIn() == null)
            checkInOnDateStr = "";
        else
            checkInOnDateStr = formatter.format(getCheckIn().getTime());

        String  estCheckOutStr;
        if (getEstimatedCheckOut() == null)
            estCheckOutStr = "";
        else
            estCheckOutStr = formatter.format(getEstimatedCheckOut().getTime());

        String checkOutStr;
        if (getActualCheckOut() == null)
            checkOutStr = null;
        else
            checkOutStr = formatter.format(getActualCheckOut().getTime());

        return "CampSite{" +
                "guestName='" + guestName + '\'' +
                ", checkIn=" + checkInOnDateStr +
                ", estimatedCheckOut=" + estCheckOutStr +
                ", actualCheckOut=" + checkOutStr +
                '}';
    }
}
