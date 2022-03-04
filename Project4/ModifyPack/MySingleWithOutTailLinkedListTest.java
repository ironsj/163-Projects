package ModifyPack;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/*****************************************************************
 Tests all functions of the MySingleWithOutTailLinkedList class
 with various variables

 @author Jake Irons
 @version Winter 2020
 *****************************************************************/
public class MySingleWithOutTailLinkedListTest {

    /** a MySingleWithoutTailLinkedList to be used for rest of tests*/
    MySingleWithOutTailLinkedList test = new MySingleWithOutTailLinkedList();

    /**
     * Makes sure the size is 0 when default constructor is called
     */
    @Test
    public void size1() {
        assertTrue(test.size() == 0);
    }

    /**
     * Adds 4 campsites and makes sure the size is 4
     */
    @Test
    public void size2() throws ParseException {
        assertTrue(test.size() == 0);
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        Date d1 = df.parse("1/1/2020");
        g1.setTime(d1);
        Date d2 = df.parse("1/4/2020");
        g2.setTime(d2);

        TentOnly tentOnly1 = new TentOnly("T1", g1, g2,g2,4);
        TentOnly tentOnly2 = new TentOnly("T2", g1,g2,g2, 8);

        RV RV1 = new RV("RV1",g1,g2,g2, 1000);
        RV RV2 = new RV("RV2",g1,g2,g1, 1000);

        test.add(tentOnly1);
        test.add(tentOnly2);

        test.add(RV1);
        test.add(RV2);
        assertTrue(test.size() == 4);
    }

    /**
     * Adds campsites, clears the list, and checks size is 0 after it is cleared
     */
    @Test
    public void clear() throws ParseException {
        assertTrue(test.size() == 0);
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        Date d1 = df.parse("1/1/2020");
        g1.setTime(d1);
        Date d2 = df.parse("1/4/2020");
        g2.setTime(d2);
        TentOnly tentOnly1 = new TentOnly("T1", g1, g2,g2,4);
        TentOnly tentOnly2 = new TentOnly("T2", g1,g2,g2, 8);
        RV RV1 = new RV("RV1",g1,g2,g2, 1000);
        RV RV2 = new RV("RV2",g1,g2,g1, 1000);

        test.add(tentOnly1);
        test.add(tentOnly2);

        test.add(RV1);
        test.add(RV2);
        assertTrue(test.size() == 4);
        test.clear();
        assertTrue(test.size() == 0);

    }

    /**
     * Calls add function three times and makes sure the size is 3
     */
    @Test
    public void add() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g3 = new GregorianCalendar();
        Date d1 = df.parse("1/1/2020");
        g1.setTime(d1);
        Date d2 = df.parse("1/4/2020");
        g2.setTime(d2);
        Date d3 = df.parse("3/31/2020");
        g3.setTime(d3);
        TentOnly Jake = new TentOnly("Jake", g2, g3,g3,4);
        TentOnly Paige = new TentOnly("Paige", g1,g2,g2, 8);
        RV Reese = new RV("Reese",g1,g2,g2, 1000);

        test.add(Reese);
        test.add(Jake);
        test.add(Paige);

        assertTrue(test.size() == 3);
        assertTrue(test.get(0).guestName.equals("Paige"));
        assertTrue(test.get(1).guestName.equals("Jake"));
        assertTrue(test.get(2).guestName.equals("Reese"));
        test.display();
    }

    /**
     * Removes the first campsite in the list
     */
    @Test
    public void removeFirst() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g3 = new GregorianCalendar();
        Date d1 = df.parse("1/1/2020");
        g1.setTime(d1);
        Date d2 = df.parse("1/4/2020");
        g2.setTime(d2);
        Date d3 = df.parse("3/31/2020");
        g3.setTime(d3);
        TentOnly Jake = new TentOnly("Jake", g2, g3,g3,4);
        TentOnly Paige = new TentOnly("Paige", g1,g2,g2, 8);
        RV Reese = new RV("Reese",g1,g2,g2, 1000);

        test.add(Reese);
        test.add(Jake);
        test.add(Paige);

        test.remove(0);
        assertTrue(test.get(0).guestName.equals("Jake"));
        assertTrue(test.get(1).guestName.equals("Reese"));
        assertTrue(test.size() == 2);
        test.display();
    }

    /**
     * Removes the campsite in the middle of the list
     */
    @Test
    public void removeMiddle() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g3 = new GregorianCalendar();
        Date d1 = df.parse("1/1/2020");
        g1.setTime(d1);
        Date d2 = df.parse("1/4/2020");
        g2.setTime(d2);
        Date d3 = df.parse("3/31/2020");
        g3.setTime(d3);
        TentOnly Jake = new TentOnly("Jake", g2, g3,g3,4);
        TentOnly Paige = new TentOnly("Paige", g1,g2,g2, 8);
        RV Reese = new RV("Reese",g1,g2,g2, 1000);

        test.add(Reese);
        test.add(Jake);
        test.add(Paige);

        test.remove(1);
        assertTrue(test.get(0).guestName.equals("Paige"));
        assertTrue(test.get(1).guestName.equals("Reese"));
        assertTrue(test.size() == 2);
        test.display();
    }

    /**
     * Removes the campsite at the end of the list
     */
    @Test
    public void removeEnd() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g3 = new GregorianCalendar();
        Date d1 = df.parse("1/1/2020");
        g1.setTime(d1);
        Date d2 = df.parse("1/4/2020");
        g2.setTime(d2);
        Date d3 = df.parse("3/31/2020");
        g3.setTime(d3);
        TentOnly Jake = new TentOnly("Jake", g2, g3,g3,4);
        TentOnly Paige = new TentOnly("Paige", g1,g2,g2, 8);
        RV Reese = new RV("Reese",g1,g2,g2, 1000);

        test.add(Reese);
        test.add(Jake);
        test.add(Paige);

        test.remove(2);
        assertTrue(test.get(0).guestName.equals("Paige"));
        assertTrue(test.get(1).guestName.equals("Jake"));
        assertTrue(test.size() == 2);
        test.display();
    }

    /**
     * Tries removing a campsite in an empty list
     */
    @Test
            (expected = IndexOutOfBoundsException.class)
    public void removeNothing(){
        test.remove(1);
    }

    /**
     * Gets various information from a campsite in a list
     */
    @Test
    public void get() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g3 = new GregorianCalendar();
        Date d2 = df.parse("1/4/2020");
        g2.setTime(d2);
        Date d3 = df.parse("3/31/2020");
        g3.setTime(d3);
        TentOnly Jake = new TentOnly("Jake", g2, g3,g3,4);
        test.add(Jake);
        assertTrue(test.get(0).guestName.equals("Jake"));
        assertTrue(test.get(0).estimatedCheckOut == g3);
        assertTrue(test.get(0).checkIn == g2);
        assertTrue(test.get(0).getClass().getName().equals("ModifyPack.TentOnly"));
    }
}
