package surroundpack;

import static org.junit.Assert.*;

import org.junit.Test;

public class testingSurrGame {
    /*****************************************************************
     Tests all methods of the ChangeJar class
     @author Jake Irons, Jacob Merda
     @version Winter 2020
     *****************************************************************/
    //for all tests: board size = 10, number of players = 2, player 1 goes first

    //testing a win in the upper-left corner
    @Test
    public void testCornersUL() {
        Surround4Game g = new Surround4Game();
        g.select(0, 1);
        g.nextPlayer();
        g.select(0,0);
        g.nextPlayer();
        g.select(1, 0);
        assertTrue(g.getWinner() == 1);
    }

    //testing a win in the upper-right corner
    @Test
    public void testCornersUR() {
        Surround4Game g = new Surround4Game();
        g.select(0, 8);
        g.nextPlayer();
        g.select(0,9);
        g.nextPlayer();
        g.select(1, 9);
        assertTrue(g.getWinner() == 1);
    }

    //testing a win in the bottom-right corner
    @Test
    public void testCornersBR() {
        Surround4Game g = new Surround4Game();
        g.select(9, 8);
        g.nextPlayer();
        g.select(9,9);
        g.nextPlayer();
        g.select(8, 9);
        assertTrue(g.getWinner() == 1);
    }

    //testing a corner in the bottom-left corner
    @Test
    public void testCornersBL() {
        Surround4Game g = new Surround4Game();
        g.select(8, 0);
        g.nextPlayer();
        g.select(9,0);
        g.nextPlayer();
        g.select(9, 1);
        assertTrue(g.getWinner() == 1);
    }

    //testing a player 1 win along the left edge
    @Test
    public void testEdgesL1() {
        Surround4Game g = new Surround4Game();
        g.select(0, 0);
        g.nextPlayer();
        g.select(1,0);
        g.nextPlayer();
        g.select(2,0);
        g.nextPlayer();
        g.select(5,5);
        g.nextPlayer();
        g.select(1, 1);
        assertTrue(g.getWinner() == 1);
    }

    //testing a player 2 win along the left edge
    @Test
    public void testEdgesL2() {
        Surround4Game g = new Surround4Game();
        g.select(9,9);
        g.nextPlayer();
        g.select(4, 0);
        g.nextPlayer();
        g.select(5,0);
        g.nextPlayer();
        g.select(6,0);
        g.nextPlayer();
        g.select(5,5);
        g.nextPlayer();
        g.select(5, 1);
        assertTrue(g.getWinner() == 2);
    }

    //testing a player 1 win on the upper edge
    @Test
    public void testEdgesU1() {
        Surround4Game g = new Surround4Game();
        g.select(0, 0);
        g.nextPlayer();
        g.select(0,1);
        g.nextPlayer();
        g.select(0,2);
        g.nextPlayer();
        g.select(5,5);
        g.nextPlayer();
        g.select(1, 1);
        assertTrue(g.getWinner() == 1);
    }

    //testing a player 2 win on the upper edge
    @Test
    public void testEdgesU2() {
        Surround4Game g = new Surround4Game();
        g.select(4,4);
        g.nextPlayer();
        g.select(0, 7);
        g.nextPlayer();
        g.select(0,8);
        g.nextPlayer();
        g.select(0,9);
        g.nextPlayer();
        g.select(5,5);
        g.nextPlayer();
        g.select(1, 8);
        assertTrue(g.getWinner() == 2);
    }

    //testing a player 1 win on the bottom edge
    @Test
    public void testEdgesB1() {
        Surround4Game g = new Surround4Game();
        g.select(9, 0);
        g.nextPlayer();
        g.select(9,1);
        g.nextPlayer();
        g.select(9,2);
        g.nextPlayer();
        g.select(5,5);
        g.nextPlayer();
        g.select(8, 1);
        assertTrue(g.getWinner() == 1);
    }

    //testing a player 2 win on the bottom edge
    @Test
    public void testEdgesB2() {
        Surround4Game g = new Surround4Game();
        g.select(0, 9);
        g.nextPlayer();
        g.select(9, 6);
        g.nextPlayer();
        g.select(9,7);
        g.nextPlayer();
        g.select(9,8);
        g.nextPlayer();
        g.select(5,5);
        g.nextPlayer();
        g.select(8, 7);
        assertTrue(g.getWinner() == 2);
    }

    //testing a player 1 win on the right edge
    @Test
    public void testEdgesR1() {
        Surround4Game g = new Surround4Game();
        g.select(0, 9);
        g.nextPlayer();
        g.select(1,9);
        g.nextPlayer();
        g.select(2,9);
        g.nextPlayer();
        g.select(5,5);
        g.nextPlayer();
        g.select(1, 8);
        assertTrue(g.getWinner() == 1);
    }

    //testing a player 2 win on the right edge
    @Test
    public void testEdgesR2() {
        Surround4Game g = new Surround4Game();
        g.select(9,9);
        g.nextPlayer();
        g.select(3, 9);
        g.nextPlayer();
        g.select(4,9);
        g.nextPlayer();
        g.select(5,9);
        g.nextPlayer();
        g.select(5,5);
        g.nextPlayer();
        g.select(4, 8);
        assertTrue(g.getWinner() == 2);
    }

    //testing a player 2 win in the middle squares
    @Test
    public void testMiddle1() {
        Surround4Game g = new Surround4Game();
        g.select(5, 5);
        g.nextPlayer();
        g.select(5,6);
        g.nextPlayer();
        g.select(4,6);
        g.nextPlayer();
        g.select(4,5);
        g.nextPlayer();
        g.select(4, 4);
        g.nextPlayer();
        g.select(5, 4);
        g.nextPlayer();
        g.select(6,4);
        g.nextPlayer();
        g.select(6,5);
        assertTrue(g.getWinner() == 2);
    }

    //testing a player 1 win in the middle squares
    @Test
    public void testMiddle2() {
        Surround4Game g = new Surround4Game();
        g.select(0,0);
        g.nextPlayer();
        g.select(8, 8);
        g.nextPlayer();
        g.select(8,9);
        g.nextPlayer();
        g.select(7,9);
        g.nextPlayer();
        g.select(7,8);
        g.nextPlayer();
        g.select(7, 7);
        g.nextPlayer();
        g.select(8, 7);
        g.nextPlayer();
        g.select(9,7);
        g.nextPlayer();
        g.select(9,8);
        assertTrue(g.getWinner() == 1);
    }

    //testing a player 2 win in the middle squares
    @Test
    public void testMiddle3() {
        Surround4Game g = new Surround4Game();
        g.select(3, 4);
        g.nextPlayer();
        g.select(3,5);
        g.nextPlayer();
        g.select(2,5);
        g.nextPlayer();
        g.select(2,4);
        g.nextPlayer();
        g.select(2, 3);
        g.nextPlayer();
        g.select(3, 3);
        g.nextPlayer();
        g.select(4,3);
        g.nextPlayer();
        g.select(4,4);
        assertTrue(g.getWinner() == 2);
    }

}