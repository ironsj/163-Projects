package surroundpack;

import javax.swing.*;

/*******************
 *
 * This class is used for one of the main components of the whole project
 * This class gets the players numbers and set cells to player numbers
 *
 * @author Jake Irons, Jacob Merda
 * @version Winter 2020
 *
 */

public class Cell {

    /** The number of the player*/
    private int playerNumber;

    /*******************
     *
     *This method sets the player number to that Cell
     *
     * @param playerNumber is the number of the player
     *
     */
    public Cell(int playerNumber) {
        super();
        this.playerNumber = playerNumber;
    }

    /*******************
     *
     *This method sets the player number to that Cell
     *
     * @return int is the players number
     *
     */
    public int getPlayerNumber() {
        return playerNumber;
    }
}