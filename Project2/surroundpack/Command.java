package surroundpack;

/*******************
 *
 *This interface is to help with the CommandManager class
 *
 */
public interface Command {
    public void execute();
    public void undo();
}