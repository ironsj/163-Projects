package RentalPack;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import static RentalPack.ScreenDisplay.CurrentParkStatus;


/*****************************************************************
 A list of CampSite objects to be put in a table with various
 functions that change the values depending on what screen is being
 viewed.

 @author Jake Irons
 @version Winter 2020
 *****************************************************************/
public class ListModel extends AbstractTableModel {
    /** list of all camp sites */
    private ArrayList<CampSite> listCampSites;

    /** list that shows campsites that meet certain conditions */
    private ArrayList<CampSite> fileredListCampSites;

    /** the current screen being displayed */
    private ScreenDisplay display;

    /** column names on the current park screen */
    private String[] columnNamesCurrentPark = {"Guest Name", "Est. Cost",
            "Check in Date", "EST. Check out Date", "Max Power", "Num of Tenters"};

    /** column names on the check out screen */
    private String[] columnNamesforCheckouts = {"Guest Name", "Est. Cost",
            "Check in Date", "ACTUAL Check out Date ", " Real Cost"};

    /** column names on the overdue screen */
    private String[] columnNamesOverDue = {"Guest Name", "Est. Cost", "Est. Checkout", "Days Overdue"};

    /** column names on the sort rv tent screen */
    private String[] columnNamesSortRVTent = {"Guest Name", "Est. Cost",
            "Check in Date", "EST. Check out Date ", "Max Power", "Num of Tenters"};

    /** column names on the tent rv screen */
    private String[] columnNamesSortTentRV = {"Guest Name", "Est. Cost",
            "Check in Date", "EST. Check out Date ", "Max Power", "Num of Tenters"};

    /** the format of all dates that are in the program */
    private DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    /** reference point for the overdue screen */
    public String overDueDate;

    /*****************************************************************
     Default constructor creates a default list and displays the
     current park screen
     *****************************************************************/
    public ListModel() {
        super();
        display = CurrentParkStatus;
        listCampSites = new ArrayList<CampSite>();
        UpdateScreen();
        createList();
    }

    /*****************************************************************
     changes the current screen being displayed in program
     *****************************************************************/
    public void setDisplay(ScreenDisplay selected) {
        display = selected;
        UpdateScreen();
    }

    /*****************************************************************
     updates the campsites that will be shown and sorts them depending
     on the screen being displayed
     *****************************************************************/
    private void UpdateScreen() {
        switch (display) {
            case CurrentParkStatus:
                //filters campsites that do not have a checkout date
                fileredListCampSites = (ArrayList<CampSite>) listCampSites.stream().
                        filter(n -> n.actualCheckOut == null).collect(Collectors.toList());

                // sorts by guest name
                Collections.sort(fileredListCampSites, (n1, n2) -> n1.getGuestName()
                        .compareToIgnoreCase(n2.guestName));
                break;

            case CheckOutGuest:
                //filters campsites that do have checkout date
                fileredListCampSites = (ArrayList<CampSite>) listCampSites.stream().
                        filter(n -> n.getActualCheckOut() != null).collect(Collectors.toList());

                // sorts by guest name
                Collections.sort(fileredListCampSites, new Comparator<CampSite>() {
                    @Override
                    public int compare(CampSite n1, CampSite n2) {
                        return n1.getGuestName().compareToIgnoreCase(n2.guestName);
                    }
                });

                break;

            case OverDueGuests:
                //filters by guests that have not checked out but are past their estimated checkout
                fileredListCampSites = (ArrayList<CampSite>) listCampSites.stream().
                        filter(n -> n.actualCheckOut == null && n.getNumDaysOverDue() > 0)
                        .collect(Collectors.toList());
                //sorts guests by the number of days overdue
                Collections.sort(fileredListCampSites, (n1,n2) -> n2.numDaysOverDue - n1.numDaysOverDue);
                break;

            case SortRVTent:
                //places ALL campsites in filtered list
                fileredListCampSites = (ArrayList<CampSite>) listCampSites.stream().
                        filter(n -> n.checkIn != null).collect(Collectors.toList());
                //sorts RVs first by guest name and then tents by guest name
                // LAMBDA FUNCTION USED HERE
                Collections.sort(fileredListCampSites, (n1, n2) ->{
                    int val1 = (n1.getClass().getName().compareTo(n2.getClass().getName()));
                    if(val1 == 0){
                        return n1.getGuestName().compareToIgnoreCase(n2.guestName);
                    }
                    return val1;
                });
                break;

            case SortTentRV:
                //puts ALL campsites in filtered list
                fileredListCampSites = (ArrayList<CampSite>) listCampSites.stream().
                        filter(n -> n.checkIn != null).collect(Collectors.toList());

                //sorts tents first by guest name and then RV by guest name
                //if names are the same, sorts by their estimated check out date
                // ANONYMOUS CLASS USED HERE
                Collections.sort(fileredListCampSites, new Comparator<CampSite>() {
                    @Override
                    public int compare(CampSite n1, CampSite n2) {
                        int val1 = n2.getClass().getName().compareTo(n1.getClass().getName());
                        if(val1 == 0){
                            int val2 =n1.getGuestName().compareToIgnoreCase(n2.guestName);
                            if (val2 == 0){
                                return n1.getEstimatedCheckOut().compareTo(n2.estimatedCheckOut);
                            }
                            else{
                                return val2;
                            }
                        }
                        return val1;
                    }
                });

                break;

            default:
                throw new RuntimeException("upDate is in undefined state: " + display);
        }
        fireTableStructureChanged();
    }

    /*****************************************************************
     Returns the name of a specific column
     @param col the number of the column
     @return the name of the column
     *****************************************************************/
    @Override
    public String getColumnName(int col) {
        switch (display) {
            case CurrentParkStatus:
                return columnNamesCurrentPark[col];
            case CheckOutGuest:
                return columnNamesforCheckouts[col];
            case OverDueGuests:
                return columnNamesOverDue[col];
            case SortRVTent:
                return columnNamesSortRVTent[col];
            case SortTentRV:
                return columnNamesSortTentRV[col];
        }
        throw new RuntimeException("Undefined state for Col Names: " + display);
    }

    /*****************************************************************
     Returns the number of columns on screen
     @return the number of columns
     *****************************************************************/
    @Override
    public int getColumnCount() {
        switch (display) {
            case CurrentParkStatus:
                return columnNamesCurrentPark.length;
            case CheckOutGuest:
                return columnNamesforCheckouts.length;
            case OverDueGuests:
                return columnNamesOverDue.length;
            case SortRVTent:
                return columnNamesSortRVTent.length;
            case SortTentRV:
                return columnNamesSortTentRV.length;
        }
        throw new IllegalArgumentException();
    }

    /*****************************************************************
     Returns the number of campsites on screen
     @return the number of campsites
     *****************************************************************/
    @Override
    public int getRowCount() {
        return fileredListCampSites.size();
    }

    /*****************************************************************
     Returns the values under a column for a campsite
     @param row the number of the row
     @param col the number of the column
     @return the value for the specific campsite and column
     *****************************************************************/
    @Override
    public Object getValueAt(int row, int col) {
        switch (display) {
            case CurrentParkStatus:
                return currentParkScreen(row, col);
            case CheckOutGuest:
                return checkOutScreen(row, col);
            case OverDueGuests:
                try {
                    return overDueScreen(row, col);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            case SortRVTent:
                return sortRVTentScreen(row, col);
            case SortTentRV:
                return sortTentRVScreen(row, col);
        }
        throw new IllegalArgumentException();
    }

    /*****************************************************************
     Returns the value on the current park screen
     @param row the number of the row
     @param col the number of the column
     @return the value in the specific row and column
     *****************************************************************/
    public Object currentParkScreen(int row, int col) {
        switch (col) {
            case 0:
                return (fileredListCampSites.get(row).guestName);

            case 1:
                return (fileredListCampSites.get(row).getCost(fileredListCampSites.
                        get(row).estimatedCheckOut));

            case 2:
                return (formatter.format(fileredListCampSites.get(row).checkIn.getTime()));

            case 3:
                if (fileredListCampSites.get(row).estimatedCheckOut == null)
                    return "-";

                return (formatter.format(fileredListCampSites.get(row).estimatedCheckOut.
                        getTime()));

            case 4:
            case 5:
                if (fileredListCampSites.get(row) instanceof RV)
                    if (col == 4)
                        return (((RV) fileredListCampSites.get(row)).getPower());
                    else
                        return "";

                else {
                    if (col == 5)
                        return (((TentOnly) fileredListCampSites.get(row)).
                                getNumberOfTenters());
                    else
                        return "";
                }
            default:
                throw new RuntimeException("Row,col out of range: " + row + " " + col);
        }
    }

    /*****************************************************************
     Returns the value on the check out screen
     @param row the number of the row
     @param col the number of the column
     @return the value in the specific row and column
     *****************************************************************/
    public Object checkOutScreen(int row, int col) {
        switch (col) {
            case 0:
                return (fileredListCampSites.get(row).guestName);

            case 1:
                return (fileredListCampSites.
                        get(row).getCost(fileredListCampSites.get(row).
                        estimatedCheckOut));
            case 2:
                return (formatter.format(fileredListCampSites.get(row).checkIn.
                        getTime()));

            case 3:
                return (formatter.format(fileredListCampSites.get(row).actualCheckOut.
                        getTime()));

            case 4:
                return (fileredListCampSites.
                        get(row).getCost(fileredListCampSites.get(row).
                        actualCheckOut
                ));

            default:
                throw new RuntimeException("Row,col out of range: " + row + " " + col);
        }
    }

    /*****************************************************************
     Returns the value on the overdue screen
     @param row the number of the row
     @param col the number of the column
     @return the value in the specific row and column
     *****************************************************************/
    public Object overDueScreen(int row, int col) throws ParseException {
        switch(col){
            case 0:
                return (fileredListCampSites.get(row).guestName);
            case 1:
                return (fileredListCampSites.
                        get(row).getCost(fileredListCampSites.get(row).
                        estimatedCheckOut));
            case 2:
                if (fileredListCampSites.get(row).estimatedCheckOut == null)
                    return "-";

                return (formatter.format(fileredListCampSites.get(row).estimatedCheckOut.
                        getTime()));
            case 3:
                return (fileredListCampSites.get(row).numDaysOverDue);

            default:
                throw new RuntimeException("Row,col out of range: " + row + " " + col);
        }
    }

    /*****************************************************************
     Returns the value on the sort rv tent screen
     @param row the number of the row
     @param col the number of the column
     @return the value in the specific row and column
     *****************************************************************/
    public Object sortRVTentScreen(int row, int col){
        switch (col) {
            case 0:
                return (fileredListCampSites.get(row).guestName);

            case 1:
                return (fileredListCampSites.get(row).getCost(fileredListCampSites.
                        get(row).estimatedCheckOut));

            case 2:
                return (formatter.format(fileredListCampSites.get(row).checkIn.getTime()));

            case 3:
                if (fileredListCampSites.get(row).estimatedCheckOut == null)
                    return "-";

                return (formatter.format(fileredListCampSites.get(row).estimatedCheckOut.
                        getTime()));

            case 4:
            case 5:
                if (fileredListCampSites.get(row) instanceof RV)
                    if (col == 4)
                        return (((RV) fileredListCampSites.get(row)).getPower());
                    else
                        return "";

                else {
                    if (col == 5)
                        return (((TentOnly) fileredListCampSites.get(row)).
                                getNumberOfTenters());
                    else
                        return "";
                }
            default:
                throw new RuntimeException("Row,col out of range: " + row + " " + col);
        }
    }

    /*****************************************************************
     Returns the value on the sort tent rv screen
     @param row the number of the row
     @param col the number of the column
     @return the value in the specific row and column
     *****************************************************************/
    public Object sortTentRVScreen(int row, int col){
        switch (col) {
            case 0:
                return (fileredListCampSites.get(row).guestName);

            case 1:
                return (fileredListCampSites.get(row).getCost(fileredListCampSites.
                        get(row).estimatedCheckOut));

            case 2:
                return (formatter.format(fileredListCampSites.get(row).checkIn.getTime()));

            case 3:
                if (fileredListCampSites.get(row).estimatedCheckOut == null)
                    return "-";

                return (formatter.format(fileredListCampSites.get(row).estimatedCheckOut.
                        getTime()));

            case 4:
            case 5:
                if (fileredListCampSites.get(row) instanceof RV)
                    if (col == 4)
                        return (((RV) fileredListCampSites.get(row)).getPower());
                    else
                        return "";

                else {
                    if (col == 5)
                        return (((TentOnly) fileredListCampSites.get(row)).
                                getNumberOfTenters());
                    else
                        return "";
                }
            default:
                throw new RuntimeException("Row,col out of range: " + row + " " + col);
        }
    }

    /*****************************************************************
     Prompts the user to input a date as a reference point for the
     overdue screen and finds the number of days guests are overdue
     *****************************************************************/
    public void OverDue(){
        Date d1 = null;
        Date d2 = null;
        overDueDate = JOptionPane.showInputDialog("Enter Date(e.g. 01/21/2020)");
        //make sure year for date overdue date is appropriate
        try{
            String[] parts = overDueDate.split("/");
            int year = Integer.parseInt(parts[2]);
            //make sure the rest of the date input is appropriate and set overdue date if so
            try {
                int numDays = 0;
                for (CampSite site : listCampSites) {
                    String estimated = site.estimatedCheckOut.get(GregorianCalendar.MONTH) + 1 +
                            "/" + site.estimatedCheckOut.get(GregorianCalendar.DAY_OF_MONTH) +
                            "/" + (site.estimatedCheckOut.get(GregorianCalendar.YEAR));
                    SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy");
                    d1 = myFormat.parse(estimated);
                    d2 = myFormat.parse(overDueDate);
                    double difference = d2.getTime() - d1.getTime();
                    double daysBetween = Math.ceil(difference / (1000 * 60 * 60 * 24));
                    numDays = (int) daysBetween;
                    site.setOverDueDays(numDays);
                }
            } catch (ParseException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Improper input for date!");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Improper input for date!");
        }
    }

    /*****************************************************************
     Adds a campsite to the list of all campsites and updates the screen
     *****************************************************************/
    public void add(CampSite a) {
        listCampSites.add(a);
        UpdateScreen();
    }

    /*****************************************************************
     Removes a campsite from the list of all campsites and updates
     the screen
     *****************************************************************/
    public void remove(CampSite a){
        listCampSites.remove(a);
        UpdateScreen();
    }

    /*****************************************************************
     Returns a specified campsite on the screen
     @return the campsite in the particular row
     *****************************************************************/
    public CampSite get(int i) {
        return fileredListCampSites.get(i);
    }

    /*****************************************************************
     Updates the screen after a user checks out
     *****************************************************************/
    public void upDate(int index, CampSite unit) {
        UpdateScreen();
    }

    /*****************************************************************
     Saves a serialized file with the current status of the camp
     reservation
     @param filename the name of the file being saved
     *****************************************************************/
    public void saveDatabase(String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(listCampSites);
            os.close();
            for(CampSite site: listCampSites){
                System.out.println(site.toString());

            }
        } catch (IOException ex) {
            throw new RuntimeException("Saving problem! " + display);

        }
    }

    /*****************************************************************
     Loads a previously saved serialized file and restores that
     camp reservation
     @param filename the name of the file being loaded
     *****************************************************************/
    public void loadDatabase(String filename) {
        listCampSites.clear();
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream is = new ObjectInputStream(fis);

            listCampSites = (ArrayList<CampSite>) is.readObject();
            for(CampSite site: listCampSites){
                System.out.println(site.toString());
            }
            UpdateScreen();
            is.close();
        } catch (Exception ex) {
            throw new RuntimeException("Loading problem: " + display);

        }
    }

    /*****************************************************************
     Saves a text file with the current status of the camp
     reservation
     @param fileName the name of the text file being saved
     *****************************************************************/
    public void saveText(String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName + ".txt");
        for(CampSite site: listCampSites){
            writer.write(site.toString() + "\n");
        }
        writer.close();
    }

    /*****************************************************************
     Loads a previously saved text file to restore that camp reservation
     @param fileName the name of the text file being loaded
     *****************************************************************/
    public void loadText(String fileName){
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        listCampSites.clear();
        try {
            FileInputStream fileByteStream = new FileInputStream(fileName);
            Scanner scnr = new Scanner(fileByteStream);
            //separates text file into different parts at following characters
            scnr.useDelimiter("[ {,}?!='\n]+");
            while(scnr.hasNext()){
                GregorianCalendar checkIn = new GregorianCalendar();
                GregorianCalendar estCheckOut = new GregorianCalendar();
                GregorianCalendar actCheckOut = new GregorianCalendar();
                CampSite t;
                CampSite rv;
                // reading the fields from the record one at the time
                String campType = scnr.next();
                //enter this if it scans "TentOnly"
                if(campType.equals("TentOnly")) {
                    scnr.next();
                    int numberOfTenters = Integer.parseInt(scnr.next());
                    scnr.next();
                    scnr.next();
                    String guestName = scnr.next();
                    scnr.next();
                    Date d1 = df.parse(scnr.next());
                    checkIn.setTime(d1);
                    scnr.next();
                    Date d2 = df.parse(scnr.next());
                    estCheckOut.setTime(d2);
                    scnr.next();
                    String out = scnr.next();
                    //if there is no checkout date
                    if(out.equals("null")){
                        t = new TentOnly(guestName, checkIn, estCheckOut, null, numberOfTenters);
                    }
                    //if there is a checkout date
                    else{
                        Date d3 = df.parse(out);
                        actCheckOut.setTime(d3);
                        t = new TentOnly(guestName, checkIn, estCheckOut, actCheckOut, numberOfTenters);
                    }
                    listCampSites.add(t);
                }
                //enters this if it scans "RV"
                else{
                    scnr.next();
                    int power = Integer.parseInt(scnr.next());
                    scnr.next();
                    scnr.next();
                    String guestName = scnr.next();
                    scnr.next();
                    Date d1 = df.parse(scnr.next());
                    checkIn.setTime(d1);
                    scnr.next();
                    Date d2 = df.parse(scnr.next());
                    estCheckOut.setTime(d2);
                    scnr.next();
                    String out = scnr.next();
                    //if there is no checkout date
                    if(out.equals("null")){
                        rv = new RV(guestName, checkIn, estCheckOut, null, power);
                    }
                    //if there is a checkout date
                    else{
                        Date d3 = df.parse(out);
                        actCheckOut.setTime(d3);
                        rv = new RV(guestName, checkIn, estCheckOut, actCheckOut, power);
                    }
                    listCampSites.add(rv);
                }
            }
            for(CampSite site: listCampSites){
                System.out.println(site.toString());
            }
            //updates screen with all loaded campsites
            UpdateScreen();
            fileByteStream.close();
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*****************************************************************
     Creates a default list of campsites that the program will begin with
     *****************************************************************/
    public void createList() {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g3 = new GregorianCalendar();
        GregorianCalendar g4 = new GregorianCalendar();
        GregorianCalendar g5 = new GregorianCalendar();
        GregorianCalendar g6 = new GregorianCalendar();

        try {
            Date d1 = df.parse("1/20/2020");
            g1.setTime(d1);
            Date d2 = df.parse("12/22/2020");
            g2.setTime(d2);
            Date d3 = df.parse("12/20/2019");
            g3.setTime(d3);
            Date d4 = df.parse("3/25/2020");
            g4.setTime(d4);
            Date d5 = df.parse("1/20/2010");
            g5.setTime(d5);
            Date d6 = df.parse("3/29/2020");
            g6.setTime(d6);

            TentOnly tentOnly1 = new TentOnly("T1", g3, g2,null,4);
            TentOnly tentOnly11 = new TentOnly("T1", g3,g1, null, 8);
            TentOnly tentOnly111 = new TentOnly("T1", g5,g3, null, 8);
            TentOnly tentOnly2 = new TentOnly("T2", g5, g3,null, 5);
            TentOnly tentOnly3 = new TentOnly("T3", g3, g1, g1,7);

            RV RV1 = new RV("RV1",g4,g6,null, 1000);
            RV RV2 = new RV("RV2",g5,g3,null, 1000);
            RV RV22 = new RV("RV2", g3,g1,null, 2000);
            RV RV222 = new RV("RV2", g3,g6,null, 2000);
            RV RV3 = new RV("RV3",g5,g4,g3, 1000);

            add(tentOnly1);
            add(tentOnly2);
            add(tentOnly3);
            add(tentOnly11);
            add(tentOnly111);

            add(RV1);
            add(RV2);
            add(RV3);
            add(RV22);
            add(RV222);

        } catch (ParseException e) {
            throw new RuntimeException("Error in testing, creation of list");
        }
    }
}