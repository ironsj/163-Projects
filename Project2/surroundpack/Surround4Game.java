package surroundpack;

import javax.swing.*;

/*******************
 *
 *  This class is the backbone of the Surround4 Project
 *  This class determines what the size of the board is, amount of players, etc.
 *
 * @author Jake Irons, Jacob Merda
 * @version Winter 2020
 *
 */

public class Surround4Game {
    /** The number of rows and columns of cells respectively*/
    private Cell[][] board;

    /** The number of the player*/
    private int player;

    /** The amount of players, determined by String*/
    private String numPlayersString = "";

    /** The number of players*/
    private int numPlayers = -1;

    /** Determines what player goes first, Determined by a String*/
    private String firstPlayerString = "";

    /** Determines what player goes first*/
    private int firstPlayer;

    /** Determines the size of the board through a String*/
    private String boardSizeString = "";

    /** The size of the board*/
    int boardSize;

    /** An object from the CommandManager class that is used to undo previous moves*/
    private CommandManager commandManager;

    public Surround4Game() {
        super();

        //Used for while loop to get correct user input
        boolean isInteger1 = false;

        // Determines the size of the board through user input, if input is incorrect boardSize = 10
        while(!isInteger1)
            try {
                boardSizeString = JOptionPane.showInputDialog("What would you like the size of the board to be? (Between 4 and 19 inclusive)");
                boardSize = Integer.parseInt(boardSizeString);
                isInteger1 = true;
            } catch(NumberFormatException io) {
                JOptionPane.showMessageDialog(null,"Invalid input for size of board. Board size set to 10.");
                boardSize = 10;
                break;
            }
        if(boardSize <= 3 || boardSize >= 20){
            boardSize = 10;
            JOptionPane.showMessageDialog(null,"Invalid input for size of board. Board size set to 10.");
        }

        //Used for while loop to get correct user input
        boolean isInteger2 = false;

        // Determines the amount of players through user input, if input is incorrect numPlayers = 2
        while(!isInteger2)
            try {
                numPlayersString = JOptionPane.showInputDialog("How many players? (Between 2 and 99 players)");
                numPlayers = Integer.parseInt(numPlayersString);
                isInteger2 = true;
            } catch(NumberFormatException io) {
                JOptionPane.showMessageDialog(null,"Invalid input for number of players. Set to 2 players");
                numPlayers = 2;
                break;
            }
        if(numPlayers <= 1 || numPlayers >= 100){
            numPlayers = 2;
            JOptionPane.showMessageDialog(null,"Invalid input for number of players. Set to 2 players");
        }

        //Used for while loop to get correct user input
        boolean validFirst = false;

        // Determines who goes first through user input, if input is incorrect player 1 goes first
        while(!validFirst)
            try {
                firstPlayerString = JOptionPane.showInputDialog("Which player goes first?");
                firstPlayer = Integer.parseInt(firstPlayerString);
                validFirst = true;
            } catch(NumberFormatException io) {
                firstPlayer = 1;
                JOptionPane.showMessageDialog(null,"Invalid input for first player. Player 1 goes first.");
                break;
            }
        if(firstPlayer < 1 || firstPlayer > numPlayers){
            firstPlayer = 1;
            JOptionPane.showMessageDialog(null,"Invalid input for first player. Player 1 goes first.");
        }


        board = new Cell[boardSize][boardSize];

        //Creates an object from the CommandManager class and assigns it to commandManager
        commandManager = new CommandManager();
        this.player = firstPlayer;
    }

    /*******************
     *
     *  This class is used to undo a a move that was made previously
     *
     */
    private class undoCommand implements Command{
        /** The new board*/
        private Surround4Game board2;

        /** The previous value of board*/
        private Cell previousValue;

        /** The previous plauers turn*/
        private int previousTurn;

        /** Previous row*/
        private int row;

        /** Previous col*/
        private int col;

        /** The current turn player*/
        private int currPlayer = getCurrentPlayer();

        /** Creates a cell that is equal to currPlayer*/
        private Cell c = new Cell(currPlayer);

        /*******************
         *
         *  This method is used to assign values to board2, row, and col through input parameters
         *  Which is used to save previous board
         * @param board2 the board
         * @param row desired row
         * @param col desired col
         */
        private undoCommand(Surround4Game board2, int row, int col){
            this.board2 = board2;
            this.row = row;
            this.col = col;
            this.previousValue = board2.board[row][col];
            this.previousTurn = board2.getCurrentPlayer();
        }

        /*******************
         *
         *  This method is used to save the cell of the board
         */
        public void execute() {
            board2.board[row][col] = c;
        }

        /*******************
         *
         *  This method is used to undo a previous move
         */
        public void undo() {
            board2.board[row][col] = previousValue;
            board2.prevPlayer();
        }
    }

    /*******************
     *
     *  This method is used to reset the board for a new game
     *  It will ask the user to input boardSize, numPlayers, and who goes first
     *
     */
    public void reset() {
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                board[r][c] = null;
            }
        }

        //Used for while loop to get correct user input
        boolean isInteger1 = false;

        // Determines the size of the board through user input, if input is incorrect boardSize = 10
        while(!isInteger1)
            try {
                boardSizeString = JOptionPane.showInputDialog("What would you like the size of the board to be? (Between 4 and 19 inclusive)");
                boardSize = Integer.parseInt(boardSizeString);
                isInteger1 = true;
            } catch(NumberFormatException io) {
                JOptionPane.showMessageDialog(null,"Invalid input for size of board. Board size set to 10.");
                boardSize = 10;
                break;
            }
        if(boardSize <= 3 || boardSize >= 20){
            boardSize = 10;
            JOptionPane.showMessageDialog(null,"Invalid input for size of board. Board size set to 10.");
        }

        //Used for while loop to get correct user input
        boolean isInteger2 = false;

        // Determines the amount of players through user input, if input is incorrect numPlayers = 2
        while(!isInteger2)
            try {
                numPlayersString = JOptionPane.showInputDialog("How many players? (Between 2 and 99 players)");
                numPlayers = Integer.parseInt(numPlayersString);
                isInteger2 = true;
            } catch(NumberFormatException io) {
                JOptionPane.showMessageDialog(null,"Invalid input for number of players. Set to 2 players");
                numPlayers = 2;
                break;
            }
        if(numPlayers <= 1 || numPlayers >= 100){
            numPlayers = 2;
            JOptionPane.showMessageDialog(null,"Invalid input for number of players. Set to 2 players");
        }

        //Used for while loop to get correct user input
        boolean validFirst = false;

        // Determines who goes first through user input, if input is incorrect player 1 goes first
        while(!validFirst)
            try {
                firstPlayerString = JOptionPane.showInputDialog("Which player goes first?");
                firstPlayer = Integer.parseInt(firstPlayerString);
                validFirst = true;
            } catch(NumberFormatException io) {
                firstPlayer = 1;
                JOptionPane.showMessageDialog(null,"Invalid input for first player. Player 1 goes first.");
                break;
            }
        if(firstPlayer < 1 || firstPlayer > numPlayers){
            firstPlayer = 1;
            JOptionPane.showMessageDialog(null,"Invalid input for first player. Player 1 goes first.");
        }


        board = new Cell[boardSize][boardSize];
        this.player = firstPlayer;
    }

    /*******************
     *
     *  This method is to return the current cell on the board
     * @param row the desired row
     * @param col the desired col
     * @return The cell at that row and col
     *
     */
    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    /*******************
     *
     *  This method is to return the current Player
     * @return an int which is the current player
     *
     */
    public int getCurrentPlayer() {
        return player;
    }

    /*******************
     *
     *  This method is to go to the next players turn
     *  It keeps going to the next highest player until it reaches the last and then goes back to player 1
     * @return an int which is the the next player
     *
     */
    public int nextPlayer() {
        player += 1;
        if (player == numPlayers + 1) {
            player = 1;
        }
        return player;
    }

    /*******************
     *
     *  This method is to go to the previous players turn
     *  It keeps decrementing by 1 until it gets to player 1, then it goes to the last player
     * @return an int which is the the previous player
     *
     */
    public int prevPlayer(){
        player -= 1;
        if(player == 0){
            player = numPlayers;
        }
        return player;
    }

    /*******************
     *
     *  This method is used to see if there has been a previous move made
     * @return true if board[row][col] is not null, else return false
     *
     */
    public boolean select(int row, int col) {
        if (board[row][col] == null ) {
            commandManager.executeCommand(new undoCommand(this, row, col));
            return true;
        }
        else
            return false;
    }

    /*******************
     *
     *  This method calls in the isUndoAvailable method from the commandManager Class
     *
     */
    public boolean canUndo(){
        return commandManager.isUndoAvailable();
    }

    /*******************
     *
     *  This method calls in the undo method from the commandManager Class
     *
     */
    public void undo(){
        commandManager.undo();
    }

    /*******************
     *
     *  This method is used to see if there are any current winners of Surround4
     *  This is done through various ways by scanning the board and checking if any win conditions are met
     * @return an int which is the player number who won
     */
    public int getWinner() {
        for (int row = 0; row < boardSize; row++)
            for (int col = 0; col < boardSize; col++)
                if (board[row][col] != null) {
                    //top-left corner
                    if(row == 0 && col == 0){
                        if(board[0][1] != null && board[1][0] != null){
                            if(board[0][1].getPlayerNumber() == board [1][0].getPlayerNumber() && board[row][col].getPlayerNumber() != board[0][1].getPlayerNumber()){
                                return board[0][1].getPlayerNumber();
                            }
                        }
                    }
                    //left-border
                    if(row != 0 && row != board.length - 1 && col == 0){
                        if(board[row-1][col] != null && board[row][col+1] != null && board[row+1][col] != null){
                            if (board[row-1][col].getPlayerNumber() == board[row][col+1].getPlayerNumber() && board[row-1][col].getPlayerNumber() == board[row+1][col].getPlayerNumber() && board[row][col].getPlayerNumber() != board[row+1][col].getPlayerNumber()){
                                return board[row-1][col].getPlayerNumber();
                            }
                        }
                    }
                    //bottom-left corner
                    if(row == board.length - 1 && col == 0){
                        if(board[row-1][col] != null && board[row][col+1] != null){
                            if(board[row-1][col].getPlayerNumber() == board[row][col+1].getPlayerNumber() && board[row][col].getPlayerNumber() != board[row][col+1].getPlayerNumber()){
                                return board[row-1][col].getPlayerNumber();
                            }
                        }
                    }
                    //middle squares
                    if(row != 0 && col != 0 && row != board.length - 1 && col != board[0].length - 1){
                        if(board[row-1][col] != null && board[row+1][col] != null && board[row][col-1] != null && board[row][col+1] !=null){
                            if(board[row-1][col].getPlayerNumber() == board[row+1][col].getPlayerNumber() && board[row-1][col].getPlayerNumber() == board[row][col+1].getPlayerNumber() && board[row-1][col].getPlayerNumber() == board[row][col-1].getPlayerNumber() && board[row][col].getPlayerNumber() != board[row][col-1].getPlayerNumber()){
                                return board[row-1][col].getPlayerNumber();
                            }
                        }
                    }
                    //upper-right corner
                    if(row == 0 && col == board[0].length - 1){
                        if(board[0][col-1] != null && board[1][col] != null){
                            if(board[0][col-1].getPlayerNumber() == board [1][col].getPlayerNumber() && board[row][col].getPlayerNumber() != board[1][col].getPlayerNumber()){
                                return board[0][col-1].getPlayerNumber();
                            }
                        }
                    }
                    //right-border
                    if(row != 0 && row != board.length - 1 && col == board[0].length - 1){
                        if(board[row-1][col] != null && board[row][col-1] != null && board[row+1][col] != null){
                            if (board[row-1][col].getPlayerNumber() == board[row][col-1].getPlayerNumber() && board[row-1][col].getPlayerNumber() == board[row+1][col].getPlayerNumber() && board[row][col].getPlayerNumber() != board[row+1][col].getPlayerNumber()){
                                return board[row-1][col].getPlayerNumber();
                            }
                        }
                    }
                    //bottom-right corner
                    if(row == board.length - 1 && col == board[0].length - 1){
                        if(board[row-1][col] != null && board[row][col-1] != null){
                            if(board[row-1][col].getPlayerNumber() == board[row][col-1].getPlayerNumber() && board[row][col].getPlayerNumber() != board[row][col-1].getPlayerNumber()){
                                return board[row-1][col].getPlayerNumber();
                            }
                        }
                    }
                    //upper-border
                    if(row == 0 && col != 0 && col != board[0].length - 1){
                        if(board[row][col-1] != null && board[row][col+1] != null && board[row+1][col] != null){
                            if (board[row][col-1].getPlayerNumber() == board[row][col+1].getPlayerNumber() && board[row][col-1].getPlayerNumber() == board[row+1][col].getPlayerNumber() && board[row][col].getPlayerNumber() != board[row+1][col].getPlayerNumber()){
                                return board[row][col-1].getPlayerNumber();
                            }
                        }
                    }
                    //lower-border
                    if(row == board.length - 1 && col != board[0].length - 1 && col != 0){
                        if(board[row][col-1] != null && board[row][col+1] != null && board[row-1][col] != null){
                            if (board[row][col-1].getPlayerNumber() == board[row][col+1].getPlayerNumber() && board[row][col-1].getPlayerNumber() == board[row-1][col].getPlayerNumber() && board[row][col].getPlayerNumber() != board[row-1][col].getPlayerNumber()){
                                return board[row][col-1].getPlayerNumber();
                            }
                        }
                    }
                }
        return -1;
    }

    /*******************
     *
     *  This is used to get the boardSize
     * @return an int which is the boardSize
     *
     */
    public int getSize(){

        return boardSize;
    }

    /*******************
     *
     *  This is used to get the number of Players
     * @return an int which is the numPlayers
     *
     */
    public int getNumPlayers(){

        return numPlayers;
    }

    /*******************
     *
     *  This is used to get the player number of a cell
     * @return an int which is the player Number
     *
     */
    public int getCellNumber(int row, int col){
        if (board[row][col] != null) {
            return board[row][col].getPlayerNumber();
        }
        return -1;
    }

    /*******************
     *
     *  This is used to see how risky the players next move is
     *  Green = low risk, Yellow = medium risk, Red = high risk
     * @return an int risk which determines the threat level
     *
     */
    public int getRiskLevel(int row, int col){
        int risk = -1;
        if(board[row][col] != null) {
            //top left corner
            if (row == 0 && col == 0) {
                if (board[0][1] == null && board[1][0] == null) {
                    risk = 0;
                }
                else if ((getCellNumber(0,0) != getCellNumber(1,0) &&  board[0][1] == null) ||
                        (getCellNumber(0,0) != getCellNumber(0,1) &&  board[1][0] == null)) {
                    risk = 1;
                }
                else if((getCellNumber(0,0) == getCellNumber(1,0) && board[0][1] == null) ||
                        (getCellNumber(0,0) == getCellNumber(0,1) && board[1][0] == null)){
                    risk = -1;
                }
            }
            //top right corner
            if (row == 0 && col == board.length - 1) {
                if (board[0][col-1] == null && board[1][col] == null) {
                    risk = 0;
                }
                else if ((getCellNumber(row, col) != getCellNumber(row+1, col) &&  board[row][col-1] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row, col-1) &&  board[row+1][col] == null)) {
                    risk = 1;
                }
                else if((getCellNumber(row,col) == getCellNumber(row+1,col) && board[row][col-1] == null) ||
                        (getCellNumber(row,col) == getCellNumber(row,col-1) && board[row+1][col] == null)){
                    risk = -1;
                }
            }
            //bottom left corner
            if (row == board.length - 1 && col == 0){
                if (board[row-1][col] == null && board[row][col+1] == null) {
                    risk = 0;
                }
                else if ((getCellNumber(row, col) != getCellNumber(row-1, col) &&  board[row][col+1] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row, col+1) &&  board[row-1][col] == null)) {
                    risk = 1;
                }
                else if((getCellNumber(row,col) == getCellNumber(row-1,col) && board[row][col+1] == null) ||
                        (getCellNumber(row,col) == getCellNumber(row,col+1) && board[row-1][col] == null)){
                    risk = -1;
                }
            }
            //bottom right corner
            if(row == board.length - 1 && col == board.length - 1) {
                if (board[row - 1][col] == null && board[row][col - 1] == null) {
                    risk = 0;
                } else if ((getCellNumber(row, col) != getCellNumber(row - 1, col) && board[row][col - 1] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row, col - 1) && board[row - 1][col] == null)) {
                    risk = 1;
                } else if ((getCellNumber(row, col) == getCellNumber(row - 1, col) && board[row][col - 1] == null) ||
                        (getCellNumber(row, col) == getCellNumber(row, col - 1) && board[row - 1][col] == null)) {
                    risk = -1;
                }
            }
            //left-border
            if(row != 0 && row != board.length - 1 && col == 0){
                if(board[row-1][col] == null && board[row][col+1] == null && board[row+1][col] == null){
                    risk = 0;
                }
                else if((getCellNumber(row, col) != getCellNumber(row-1, col) && board[row][col+1] == null && board[row+1][col] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row+1, col) && board[row][col+1] == null && board[row-1][col] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row, col+1) && board[row-1][col] == null && board[row+1][col] == null)){
                    risk = 2;
                }
                else if((getCellNumber(row, col) != getCellNumber(row-1, col) && (getCellNumber(row, col) != getCellNumber(row, col+1)) && board[row+1][col] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row+1, col) && (getCellNumber(row, col) != getCellNumber(row, col+1)) && board[row-1][col] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row+1, col) && (getCellNumber(row, col) != getCellNumber(row-1, col)) && board[row][col+1] == null)){
                    risk = 1;
                }
                else{
                    risk = -1;
                }
            }
            //right-border
            if(row != 0 && row != board.length - 1 && col == board[0].length - 1){
                if(board[row-1][col] == null && board[row][col-1] == null && board[row+1][col] == null){
                    risk = 0;
                }
                else if((getCellNumber(row, col) != getCellNumber(row-1, col) && board[row][col-1] == null && board[row+1][col] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row+1, col) && board[row][col-1] == null && board[row-1][col] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row, col-1) && board[row-1][col] == null && board[row+1][col] == null)){
                    risk = 2;
                }
                else if((getCellNumber(row, col) != getCellNumber(row-1, col) && (getCellNumber(row, col) != getCellNumber(row, col-1)) && board[row+1][col] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row+1, col) && (getCellNumber(row, col) != getCellNumber(row, col-1)) && board[row-1][col] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row+1, col) && (getCellNumber(row, col) != getCellNumber(row-1, col)) && board[row][col-1] == null)){
                    risk = 1;
                }
                else{
                    risk = -1;
                }
            }
            //upper-border
            if(row == 0 && col != 0 && col != board[0].length - 1){
                if(board[row][col+1] == null && board[row][col-1] == null && board[row+1][col] == null){
                    risk = 0;
                }
                else if((getCellNumber(row, col) != getCellNumber(row+1, col) && board[row][col-1] == null && board[row][col+1] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row, col-1) && board[row][col+1] == null && board[row+1][col] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row, col+1) && board[row+1][col] == null && board[row][col-1] == null)){
                    risk = 2;
                }
                else if((getCellNumber(row, col) != getCellNumber(row+1, col) && (getCellNumber(row, col) != getCellNumber(row, col-1)) && board[row][col+1] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row+1, col) && (getCellNumber(row, col) != getCellNumber(row, col+1)) && board[row][col-1] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row, col+1) && (getCellNumber(row, col) != getCellNumber(row, col-1)) && board[row+1][col] == null)){
                    risk = 1;
                }
                else{
                    risk = -1;
                }
            }
            //lower-border
            if(row == board.length - 1 && col != board[0].length - 1 && col != 0){
                if(board[row][col+1] == null && board[row][col-1] == null && board[row-1][col] == null){
                    risk = 0;
                }
                else if((getCellNumber(row, col) != getCellNumber(row-1, col) && board[row][col-1] == null && board[row][col+1] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row, col-1) && board[row][col+1] == null && board[row-1][col] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row, col+1) && board[row-1][col] == null && board[row][col-1] == null)){
                    risk = 2;
                }
                else if((getCellNumber(row, col) != getCellNumber(row-1, col) && (getCellNumber(row, col) != getCellNumber(row, col-1)) && board[row][col+1] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row-1, col) && (getCellNumber(row, col) != getCellNumber(row, col+1)) && board[row][col-1] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row, col+1) && (getCellNumber(row, col) != getCellNumber(row, col-1)) && board[row-1][col] == null)){
                    risk = 1;
                }
                else{
                    risk = -1;
                }
            }
            //middle square
            if(row != 0 && col != 0 && row != board.length - 1 && col != board[0].length - 1){
                if(board[row][col+1] == null && board[row][col-1] == null && board[row-1][col] == null && board[row+1][col] == null){
                    risk = 0;
                }
                else if((getCellNumber(row, col) != getCellNumber(row-1, col) && board[row+1][col] == null && board[row][col-1] == null && board[row][col+1] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row+1, col) && board[row-1][col] == null && board[row][col-1] == null && board[row][col+1] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row, col+1) && board[row+1][col] == null && board[row][col-1] == null && board[row-1][col] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row, col-1) && board[row+1][col] == null && board[row-1][col] == null && board[row][col+1] == null)){
                    risk = 0;
                }
                else if(((getCellNumber(row, col) != getCellNumber(row - 1, col)) && (getCellNumber(row, col) != getCellNumber(row+1, col)) && (board[row][col+1] == null) && (board[row][col-1] == null)) ||
                        ((getCellNumber(row, col) != getCellNumber(row - 1, col)) && (getCellNumber(row, col) != getCellNumber(row, col-1)) && (board[row][col+1] == null) && (board[row+1][col] == null)) ||
                        ((getCellNumber(row, col) != getCellNumber(row - 1, col)) && (getCellNumber(row, col) != getCellNumber(row, col+1)) && (board[row][col-1] == null) && (board[row+1][col] == null)) ||
                        ((getCellNumber(row, col) != getCellNumber(row + 1, col)) && (getCellNumber(row, col) != getCellNumber(row, col-1)) && (board[row][col+1] == null) && (board[row-1][col] == null)) ||
                        ((getCellNumber(row, col) != getCellNumber(row + 1, col)) && (getCellNumber(row, col) != getCellNumber(row, col+1)) && (board[row][col-1] == null) && (board[row-1][col] == null)) ||
                        ((getCellNumber(row, col) != getCellNumber(row, col-1)) && (getCellNumber(row, col) != getCellNumber(row, col+1)) && (board[row-1][col] == null) && (board[row+1][col] == null))){
                    risk = 2;
                }
                else if((getCellNumber(row, col) != getCellNumber(row-1, col) && getCellNumber(row, col) != getCellNumber(row+1, col) && getCellNumber(row, col) != getCellNumber(row, col-1) && board[row][col+1] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row-1, col) && getCellNumber(row, col) != getCellNumber(row, col-1) && getCellNumber(row, col) != getCellNumber(row, col+1) && board[row+1][col] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row-1, col) && getCellNumber(row, col) != getCellNumber(row+1, col) && getCellNumber(row, col) != getCellNumber(row, col+1) && board[row][col-1] == null) ||
                        (getCellNumber(row, col) != getCellNumber(row+1, col) && getCellNumber(row, col) != getCellNumber(row, col+1) && getCellNumber(row, col) != getCellNumber(row, col-1) && board[row-1][col] == null)){
                    risk = 1;
                }
                else{
                    risk = -1;
                }
            }
        }
        return risk;
    }


}