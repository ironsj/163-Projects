package surroundpack;

import java.util.Stack;

/*******************
 *
 *This class allows the Surround4Game to be able to undo previous actions
 *
 * @author Jake Irons, Jacob Merda
 * @version Winter 2020
 *
 */
public class CommandManager {

    /** Stores information about previous turn*/
    private Stack<Command> undos = new Stack<Command>();

    /*******************
     *
     *The Default Constructor
     *
     */
    public CommandManager() {}

    /*******************
     *
     *This method does the execute command and stores the information in the Stack<Command> undo
     *
     */
    public void executeCommand(Command c) {
        c.execute();
        undos.push(c);
    }

    /*******************
     *
     *This method is used to see if being able to undo is possible
     * @return true if it is possible, false if it is not
     *
     */
    public boolean isUndoAvailable() {
        return !undos.empty();
    }

    /*******************
     *
     *This method goes back to the previously stored data from undos
     *
     */
    public void undo() {
        assert(!undos.empty());
        Command command = undos.pop();
        command.undo();
    }
}