package changeJarPack;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChangeJarTest {

    /*****************************************************************
     Tests all methods of the ChangeJar class

     @author Jake Irons
     @version Winter 2020
     *****************************************************************/


    // Testing valid constructors with wide range of values
    @Test
    public void testConstructor() {
        ChangeJar jar1 = new ChangeJar(6, 5, 4, 2);

        assertEquals (6, jar1.getQuarters());
        assertEquals (5, jar1.getDimes());
        assertEquals (4, jar1.getNickels());
        assertEquals (2, jar1.getPennies());

        ChangeJar jar2 = new ChangeJar();
        assertEquals (0, jar2.getQuarters());
        assertEquals (0, jar2.getDimes());
        assertEquals (0, jar2.getNickels());
        assertEquals (0, jar2.getPennies());

        ChangeJar jar3 = new ChangeJar(jar1);
        assertEquals (6, jar3.getQuarters());
        assertEquals (5, jar3.getDimes());
        assertEquals (4, jar3.getNickels());
        assertEquals (2, jar3.getPennies());

        ChangeJar jar4 = new ChangeJar("3.49");
        assertEquals (13, jar4.getQuarters());
        assertEquals (2, jar4.getDimes());
        assertEquals (0, jar4.getNickels());
        assertEquals (4, jar4.getPennies());

        ChangeJar jar5 = new ChangeJar(3.49);
        assertEquals (13, jar5.getQuarters());
        assertEquals (2, jar5.getDimes());
        assertEquals (0, jar5.getNickels());
        assertEquals (4, jar5.getPennies());
    }

    //Testing constructor with negative dimes
    @Test
            (expected = IllegalArgumentException.class)
    public void invalidConstructor1(){
        ChangeJar jar1 = new ChangeJar(5, -1, 3, 4);
    }

    //Testing constructor with too many decimal places
    @Test
            (expected = IllegalArgumentException.class)
    public void invalidConstructor2(){
        ChangeJar jar2 = new ChangeJar(1.015);
    }

    //Testing constructor with negative amount
    @Test
            (expected = IllegalArgumentException.class)
    public void invalidConstructor3(){
        ChangeJar jar3 = new ChangeJar (-2.02);
    }

    //Testing constructor with too many decimal places
    @Test
            (expected = IllegalArgumentException.class)
    public void invalidConstructor4(){
        ChangeJar jar4 = new ChangeJar ("1.015");
    }

    //Testing constructor with negative amount in string
    @Test
            (expected = IllegalArgumentException.class)
    public void invalidConstructor5(){
        ChangeJar jar5 = new ChangeJar ("-2.02");
    }

    //Testing constructor with negative quarters
    @Test
            (expected = IllegalArgumentException.class)
    public void invalidConstructor6(){
        ChangeJar jar1 = new ChangeJar(-12, 1, 3, 4);
    }

    //Testing constructor with negative nickels
    @Test
            (expected = IllegalArgumentException.class)
    public void invalidConstructor7(){
        ChangeJar jar1 = new ChangeJar(5, 1, -16, 4);
    }

    //Testing constructor with negative pennies
    @Test
            (expected = IllegalArgumentException.class)
    public void invalidConstructor8(){
        ChangeJar jar1 = new ChangeJar(5, 1, 3, -7);
    }

    // Testing equals for valid numbers
    @Test
    public void testEqual () {
        ChangeJar jar1 = new ChangeJar(2, 5, 4, 2);
        ChangeJar jar2 = new ChangeJar(6, 5, 4, 2);
        ChangeJar jar3 = new ChangeJar(2, 5, 4, 2);
        ChangeJar jar4 = new ChangeJar();

        assertFalse(jar1.equals(jar2));
        assertTrue(jar1.equals(jar3));

        assertTrue(jar4.equals(jar1, jar3));
        assertFalse(jar4.equals(jar1, jar2));
    }

    // testing compareTo all returns
    @Test
    public void testCompareTo () {
        ChangeJar jar1 = new ChangeJar(2, 5, 4, 2);
        ChangeJar jar2 = new ChangeJar(6, 5, 4, 2);
        ChangeJar jar3 = new ChangeJar(2, 3, 4, 2);
        ChangeJar jar4 = new ChangeJar(2, 5, 4, 2);
        ChangeJar jar5 = new ChangeJar();

        assertTrue(jar2.compareTo(jar1) > 0);
        assertTrue(jar3.compareTo(jar1) < 0);
        assertTrue(jar1.compareTo(jar4) == 0);
        assertFalse(jar2.compareTo(jar1) == 0);
        assertFalse(jar3.compareTo(jar1) > 0);
        assertFalse(jar1.compareTo(jar4) != 0);

        assertTrue(jar5.compareTo(jar2, jar1) > 0);
        assertTrue(jar5.compareTo(jar3, jar1) < 0);
        assertTrue(jar5.compareTo(jar4, jar1) == 0);
        assertFalse(jar5.compareTo(jar2, jar1) == 0);
        assertFalse(jar5.compareTo(jar3, jar1) > 0);
        assertFalse(jar5.compareTo(jar1, jar4) != 0);
    }

    //testing subtract method with range of values
    @Test
    public void testSubtract(){
        ChangeJar jar1 = new ChangeJar(2, 3, 6, 1);
        ChangeJar jar2 = new ChangeJar(1, 2, 3, 4);
        ChangeJar jar3 = new ChangeJar(1, 1, 1, 1);

        jar1.subtract(1, 1, 1, 1);
        assertEquals(1, jar1.getQuarters());
        assertEquals(2, jar1.getDimes());
        assertEquals(5, jar1.getNickels());
        assertEquals(0, jar1.getPennies());

        jar2.subtract(jar3);
        assertEquals(0, jar2.getQuarters());
        assertEquals(1, jar2.getDimes());
        assertEquals(2, jar2.getNickels());
        assertEquals(3, jar2.getPennies());
    }

    //testing subtract method with invalid values
    @Test
            (expected = IllegalArgumentException.class)
    public void invalidSubtract1(){
        ChangeJar jar1 = new ChangeJar(2, 3, 6, 1);

        jar1.subtract(3, 3, 4, 2);
    }

    //testing subtract method with invalid values
    @Test
            (expected = IllegalArgumentException.class)
    public void invalidSubtract2(){
        ChangeJar jar1 = new ChangeJar(2, 3, 6, 1);
        ChangeJar jar2 = new ChangeJar(1, 2, 3, 4);

        jar1.subtract(jar2);
    }

    //testing subtract method with negative quarters
    @Test
            (expected = IllegalArgumentException.class)
    public void invalidSubtract3(){
        ChangeJar jar1 = new ChangeJar(2, 3, 6, 1);

        jar1.subtract(-1, 1, 1, 1);
    }

    //testing subtract method with negative dimes
    @Test
            (expected = IllegalArgumentException.class)
    public void invalidSubtract4(){
        ChangeJar jar1 = new ChangeJar(2, 3, 6, 1);

        jar1.subtract(1, -1, 1, 1);
    }

    //testing subtract method with negative nickels
    @Test
            (expected = IllegalArgumentException.class)
    public void invalidSubtract5(){
        ChangeJar jar1 = new ChangeJar(2, 3, 6, 1);

        jar1.subtract(1, 1, -1, 1);
    }

    //testing add method with negative pennies
    @Test
            (expected = IllegalArgumentException.class)
    public void invalidSubtract6(){
        ChangeJar jar1 = new ChangeJar(2, 3, 6, 1);

        jar1.subtract(1, 1, 1, -1);
    }

    @Test
    //testing decrement method
    public void testDec(){
        ChangeJar jar1 = new ChangeJar(2, 3, 6, 6);
        ChangeJar jar2 = new ChangeJar(12, 25, 100, 1000);
        ChangeJar jar3 = new ChangeJar(4, 1, 88, 50);

        jar1.dec();
        assertEquals(5, jar1.getPennies());

        jar2.dec();
        assertEquals(12, jar2.getQuarters());
        assertEquals(25, jar2.getDimes());
        assertEquals(100, jar2.getNickels());
        assertEquals(999, jar2.getPennies());

        jar3.dec();
        assertEquals(4, jar3.getQuarters());
        assertEquals(1, jar3.getDimes());
        assertEquals(88, jar3.getNickels());
        assertEquals(49, jar3.getPennies());
    }

    //testing decrement with 0 pennies
    @Test
            (expected = IllegalArgumentException.class)
    public void invalidDec(){
        ChangeJar jar1 = new ChangeJar();
        jar1.dec();
    }

    //testing add method with range of values
    @Test
    public void testAdd() {
        ChangeJar jar1 = new ChangeJar(2, 3, 6, 1);
        ChangeJar jar2 = new ChangeJar(1, 2, 3, 4);
        ChangeJar jar3 = new ChangeJar(1, 1, 1, 1);

        jar1.add(100, 1, 36, 1);
        assertEquals(102, jar1.getQuarters());
        assertEquals(4, jar1.getDimes());
        assertEquals(42, jar1.getNickels());
        assertEquals(2, jar1.getPennies());

        jar2.add(jar3);
        assertEquals(2, jar2.getQuarters());
        assertEquals(3, jar2.getDimes());
        assertEquals(4, jar2.getNickels());
        assertEquals(5, jar2.getPennies());
    }

    //testing add method with negative quarters
    @Test
            (expected = IllegalArgumentException.class)
    public void invalidAdd(){
        ChangeJar jar1 = new ChangeJar(2, 3, 6, 1);

        jar1.add(-3, 3, 4, 2);
    }

    //testing add method with negative dimes
    @Test
            (expected = IllegalArgumentException.class)
    public void invalidAdd2(){
        ChangeJar jar1 = new ChangeJar(2, 3, 6, 1);

        jar1.add(1, -1, 1, 1);
    }

    //testing add method with negative nickels
    @Test
            (expected = IllegalArgumentException.class)
    public void invalidAdd3(){
        ChangeJar jar1 = new ChangeJar(2, 3, 6, 1);

        jar1.add(1, 1, -1, 1);
    }

    //testing add method with negative pennies
    @Test
            (expected = IllegalArgumentException.class)
    public void invalidAdd4(){
        ChangeJar jar1 = new ChangeJar(2, 3, 6, 1);

        jar1.add(1, 1, 1, -1);
    }

    //testing increment method
    @Test
    public void testInc(){
        ChangeJar jar1 = new ChangeJar(2, 3, 6, 1);
        ChangeJar jar2 = new ChangeJar(0, 9, 0, 9);
        ChangeJar jar3 = new ChangeJar(8, 1, 0, 4);

        jar1.inc();
        assertEquals(2, jar1.getQuarters());
        assertEquals(3, jar1.getDimes());
        assertEquals(6, jar1.getNickels());
        assertEquals(2, jar1.getPennies());

        jar2.inc();
        assertEquals(0, jar2.getQuarters());
        assertEquals(9, jar2.getDimes());
        assertEquals(0, jar2.getNickels());
        assertEquals(10, jar2.getPennies());

        jar3.inc();
        assertEquals(8, jar3.getQuarters());
        assertEquals(1, jar3.getDimes());
        assertEquals(0, jar3.getNickels());
        assertEquals(5, jar3.getPennies());
    }

    @Test
    //testing toString method
    public void testToString(){
        ChangeJar jar1 = new ChangeJar(1.19);
        ChangeJar jar2 = new ChangeJar(1, 3, 6, 1);

        assertEquals("4 Quarters\n1 Dime\n1 Nickel\n4 Pennies\n", jar1.toString());
        assertEquals("1 Quarter\n3 Dimes\n6 Nickels\n1 Penny\n", jar2.toString());

    }

    // load and save combined.
    @Test
    public void testLoadSave() {
        ChangeJar jar1 = new ChangeJar(6, 5, 4, 2);
        ChangeJar jar2 = new ChangeJar(6, 5, 4, 2);

        jar1.save("file1");
        jar1 = new ChangeJar();  // resets to zero

        jar1.load("file1");
        assertTrue(jar1.equals(jar2));
    }


    // testing valid takeOut with wide range of
    // quarters, dimes, nickels, pennies
    @Test
    public void testTakeOut1() {
        ChangeJar jar = new ChangeJar(3,3,2,2);
        jar.takeOut(1,1,1,1);
        assertEquals (2, jar.getQuarters());
        assertEquals (2, jar.getDimes());
        assertEquals (1, jar.getNickels());
        assertEquals (1, jar.getPennies());
    }

    // testing valid takeOut with wide range of amounts
    @Test
    public void testTakeOut2() {
        ChangeJar jar1 = new ChangeJar(5,3,4,3);
        ChangeJar jar2 = jar1.takeOut(1.22);

        assertEquals (1, jar1.getQuarters());
        assertEquals (1, jar1.getDimes());
        assertEquals (4, jar1.getNickels());
        assertEquals (1, jar1.getPennies());

        assertEquals (4, jar2.getQuarters());
        assertEquals (2, jar2.getDimes());
        assertEquals (0, jar2.getNickels());
        assertEquals (2, jar2.getPennies());
    }

    // testing takeOut with invalid values
    @Test
            (expected = IllegalArgumentException.class)
    public void testInvalidTakeOut1() {
        ChangeJar jar1 = new ChangeJar(5,3,4,3);
        ChangeJar jar2 = jar1.takeOut(-1.22);
    }

    // testing takeOut with invalid values
    @Test
            (expected = IllegalArgumentException.class)
    public void testInvalidTakeOut2() {
        ChangeJar jar1 = new ChangeJar(5,3,4,3);
        jar1.takeOut(1, -3, 4, 6);
    }

    // testing takeOut with not enough change
    @Test
            (expected = IllegalArgumentException.class)
    public void testInvalidTakeOut3() {
        ChangeJar jar1 = new ChangeJar(5,3,4,3);
        ChangeJar jar2 = new ChangeJar(6,1,1,1);
        jar1.takeOut(jar2);
    }

}