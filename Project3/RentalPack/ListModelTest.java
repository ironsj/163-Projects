package RentalPack;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import static org.junit.Assert.*;


public class ListModelTest {
    /*****************************************************************
     Tests all methods of the ListModel class
     @author Jake Irons
     @version Winter 2020
     *****************************************************************/
    //ENTER "01/21/2020" FOR DATE WHEN PROMPTED

    /** a change jar */
    public ListModel l;

    //instantiates ListModel to be used for rest of test class
    @Before
    public void setUp() throws Exception {
        l = new ListModel();
    }

    //testing setDisplay() by changing display and checking the number of rows on each display
    @Test
    public void setDisplay() {
        l.setDisplay(ScreenDisplay.CurrentParkStatus);
        assertTrue(l.getRowCount() == 8);
        l.setDisplay(ScreenDisplay.CheckOutGuest);
        assertTrue(l.getRowCount() == 2);
        l.setDisplay(ScreenDisplay.SortRVTent);
        assertTrue(l.getRowCount() == 10);
    }

    //testing the number of columns on the CurrentParkStatus display
    @Test
    public void getColumnCount() {
        assertTrue(l.getColumnCount() == 6);
    }

    //testing the number of rows on the CurrentParkStatus display
    @Test
    public void getRowCount() {
        assertTrue(l.getRowCount() == 8);
    }

    //testing for values on various places on current park screen
    @Test
    public void currentParkScreen(){
        assertTrue(l.currentParkScreen(0, 1).equals(90.0));
        assertTrue(l.currentParkScreen(7,5).equals(5));
    }

    //testing for values on various places on checkout screen
    @Test
    public void checkOutScreen(){
        l.setDisplay(ScreenDisplay.CheckOutGuest);
        assertTrue(l.checkOutScreen(1, 0).equals("T3"));
        assertTrue(l.checkOutScreen(0, 4).equals(72430.0));
    }

    //testing for values on various places on overdue screen
    @Test
    public void overDueScreen() throws ParseException {
        l.OverDue();
        l.setDisplay(ScreenDisplay.OverDueGuests);
        assertTrue(l.overDueScreen(2,0).equals("RV2"));
        assertTrue(l.overDueScreen(4,1).equals(940.0));
    }

    //testing for values on various places on sort rv tent screen
    @Test
    public void sortRVTentScreen(){
        l.setDisplay(ScreenDisplay.SortRVTent);
        assertTrue(l.sortRVTentScreen(4,4).equals(1000));
        assertTrue(l.sortRVTentScreen(9,1).equals(310.0));
    }

    //testing for values on various places on sort tent rv screen
    @Test
    public void sortTentRVScreen(){
        l.setDisplay(ScreenDisplay.SortTentRV);
        assertTrue(l.sortTentRVScreen(4,5).equals(7));
        assertTrue(l.sortTentRVScreen(9,1).equals(74350.0));
    }

    //testing for values on various screens
    @Test
    public void getValueAt() {
        assertTrue(l.getValueAt(0,2).equals(l.currentParkScreen(0, 2)));
        l.setDisplay(ScreenDisplay.SortTentRV);
        assertTrue(l.getValueAt(9, 4).equals(l.sortTentRVScreen(9,4)));
    }

    //testing for each column name on current park screen
    @Test
    public void getColumnName() {
        assertTrue(l.getColumnName(0).equals("Guest Name"));
        assertTrue(l.getColumnName(1).equals("Est. Cost"));
        assertTrue(l.getColumnName(2).equals("Check in Date"));
        assertTrue(l.getColumnName(3).equals("EST. Check out Date"));
        assertTrue(l.getColumnName(4).equals("Max Power"));
        assertTrue(l.getColumnName(5).equals("Num of Tenters"));
    }

    //testing add method
    @Test
    public void add(){
        l.add(l.get(0));
        l.add(l.get(1));
        assertTrue(l.getRowCount() == 10);
    }

    //testing remove method
    @Test
    public void remove() {
        l.remove(l.get(1));
        assertTrue(l.getRowCount() == 7);
    }

    //testing get method
    @Test
    public void get() {
        assertTrue(l.get(0).getGuestName().equals("RV1"));
        assertTrue(l.get(1).getGuestName().equals("RV2"));
        assertTrue(l.get(2).getGuestName().equals("RV2"));
        assertTrue(l.get(3).getGuestName().equals("RV2"));
        assertTrue(l.get(4).getGuestName().equals("T1"));
        assertTrue(l.get(5).getGuestName().equals("T1"));
        assertTrue(l.get(6).getGuestName().equals("T1"));
        assertTrue(l.get(7).getGuestName().equals("T2"));
    }

    //testing update method by checking out a campsite
    @Test
    public void upDate() throws ParseException {
        Date d;
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar gTemp = new GregorianCalendar();
        d = df.parse("03/26/2020");
        gTemp.setTime(d);
        l.get(0).setActualCheckOut(gTemp);
        l.upDate(0, l.get(0));
        assertTrue(l.getRowCount() == 7);

    }

    //testing load and save combined
    @Test
    public void loadSaveDatabase() {
        assertTrue(l.getRowCount() == 8);
        l.saveDatabase("file");
        l.remove(l.get(0));
        l.remove(l.get(1));
        assertTrue(l.getRowCount() == 6);
        l.loadDatabase("file");
        assertTrue(l.getRowCount() == 8);
    }

    //testing saving to a text file and loading combined
    @Test
    public void loadSaveAsText() throws IOException {
        assertTrue(l.getRowCount() == 8);
        l.saveText("file.txt");
        l.remove(l.get(0));
        l.remove(l.get(1));
        assertTrue(l.getRowCount() == 6);
        l.loadText("file.txt");
        assertTrue(l.getRowCount() == 8);
    }

    //testing createList method by adding another identical list to the ListModel
    @Test
    public void createList() {
        l.createList();
        assertTrue(l.getRowCount() == 16);
    }
}
