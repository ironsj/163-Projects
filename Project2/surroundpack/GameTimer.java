package surroundpack;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/*******************
 *
 *This class is used to time each players turn
 *
 * @author Jake Irons, Jacob Merda
 * @version Winter 2020
 *
 */

public class GameTimer {
    /** The interval for the class*/
    static int interval;

    /** A timer object from the Timer class*/
    static Timer timer;

    /** A 1000 millisecond delay*/
    int delay = 1000;

    /** A 1000 millisecond period*/
    int period = 1000;

    /*******************
     *
     * The main constructor for GameTimer
     */
    public GameTimer() {
        String f = "12";
        int time;
        timer = new Timer();
        interval = 10;
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                int t = (setInterval());
            }
        }, delay, period);
    }

    /*******************
     *
     *  This method will decrement interval until it reaches 1, then the timer stops
     *
     * @return interval
     *
     */
    public int setInterval() {
        if (interval == 1)
            timer.cancel();
        return --interval;
    }
    public void getTime(){

    }

    /*******************
     *
     * This main method creates a new GameTimer object and calls the setInterval() method
     *
     */
    public static void main(String[] args){
        GameTimer g = new GameTimer();
        System.out.println(g.setInterval());
    }
}