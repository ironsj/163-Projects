package surroundpack;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.*;

/*******************
 *
 *This class is the GUI for the Surround4 game
 *
 * @author Jake Irons, Jacob Merda
 * @version Winter 2020
 *
 */

public class Surround4Panel extends JPanel {

    /** JButtons that make the board itself*/
    private JButton[][] board;

    /** The main JPanel*/
    private JPanel panel1;

    /** The upper menu*/
    private JPanel upperPanel;

    /** The message that pops up when someone wins*/
    private JPanel winPanel;

    /** The players number*/
    private int player;

    /** Default listener*/
    private ButtonListener listen;

    /** Quits out of program*/
    private JMenuItem quitItem;

    /** Starts a new game*/
    private JMenuItem newGameItem;

    /** Info about what the colors mean*/
    private JMenuItem colorInfoItem;

    /** An object from the Surround4Game class*/
    private Surround4Game game;

    /** The size of the board*/
    public int boardSize;

    /** Allows the user to undo a previous action*/
    private JButton undoButton;

    /** The number of player wins*/
    private JLabel[] playerWins;

    /** The time the player has on each turn*/
    private JLabel turnTimer;

    /** An object from the timer class*/
    private Timer timer;

    /** The amount of time the player has to choose a square*/
    private static int time = 15;

    /** The amount of undos that can occur*/
    private int undoCounter = 0;

    /** Used for undoCounter*/
    private static int undoIndicator;

    /** The previous player*/
    private static int oldPlayer;


    /*******************
     *
     *This constructor instantiates quitItem, newGameItem, colorInfoItem, jPanels, etc.
     *It also contains the actionPerformed method for the timer object
     * Assigns a listener to various JButtons
     * @param pQuitItem is the quitItem
     * @param pNewGameItem is the newGameItem
     * @param pColorInfoItem is the colorInfoItem
     */

    public Surround4Panel(JMenuItem pQuitItem, JMenuItem pNewGameItem, JMenuItem pColorInfoItem) {
        quitItem = pQuitItem;
        newGameItem = pNewGameItem;
        colorInfoItem = pColorInfoItem;
        listen = new ButtonListener();
        game = new Surround4Game();


        setLayout(new BorderLayout());
        panel1 = new JPanel();
        upperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        winPanel = new JPanel(new GridLayout(0, 1, 20, 20));
        undoButton = new JButton("Undo");
        undoButton.addActionListener(listen);
        upperPanel.add(undoButton);
        turnTimer = new JLabel("Pick a cell within 15 seconds!");
        upperPanel.add(turnTimer);
        playerWins = new JLabel[98];
        for(int i = 1; i <= game.getNumPlayers(); i++) {
            playerWins[i] = new JLabel("Player " + i + ": 0");
            winPanel.add(playerWins[i]);
        }

        undoIndicator = undoCounter;
        oldPlayer = game.getCurrentPlayer();
        javax.swing.Timer t = new javax.swing.Timer(1000, new ActionListener() {

            /*******************
             *
             *This method is for the timer object which will end the current players turn if they take to long to move
             * @param e the actionEvent
             *
             */
            public void actionPerformed(ActionEvent e) {
                if(time>0){
                    time--;
                    turnTimer.setText("Player " + game.getCurrentPlayer() + ", pick a cell within " + time + " seconds!");
                }
                else if (time == 0){
                    int input = JOptionPane.showConfirmDialog(null,
                            game.getCurrentPlayer() + ", you lost your turn!", "Lost turn!", JOptionPane.DEFAULT_OPTION);
                    time = 16;
                    oldPlayer = game.getCurrentPlayer();
                    game.nextPlayer();
                }

                if(game.getCurrentPlayer() != oldPlayer){
                    oldPlayer = game.getCurrentPlayer();
                    time = 16;
                }

                if(undoIndicator != undoCounter){
                    time = 16;
                    oldPlayer = game.getCurrentPlayer();
                    undoIndicator = undoCounter;
                }





            }
        });t.start();





        add(panel1, BorderLayout.CENTER);
        add(upperPanel, BorderLayout.NORTH);
        add(winPanel, BorderLayout.EAST);
        boardSize = game.getSize();
        createBoard();
        quitItem.addActionListener(listen);
        newGameItem.addActionListener(listen);
        colorInfoItem.addActionListener(listen);

    }



    /*******************
     *
     *This class does various tasks such as undo, create the board, repaint, displays winner, etc.
     *
     */
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // quits game
            if (e.getSource() == quitItem) {
                System.exit(1);
            }

            // displays info about colors
            if(e.getSource() == colorInfoItem){
                JOptionPane.showMessageDialog(null, "No color = no risk\nGreen = low risk\nYellow = medium risk\nRed = high risk");
            }

            //creates new game
            if(e.getSource() == newGameItem){
                for (int i = 0; i < boardSize; i++){
                    for (int j = 0; j < boardSize; j++){
                        panel1.remove(board[i][j]);
                    }
                }
                int oldNumPlayers = game.getNumPlayers();
                game.reset();
                if(game.getNumPlayers() < oldNumPlayers) {
                    for(int i = game.getNumPlayers() + 1; i <= oldNumPlayers; i++){
                        winPanel.remove(playerWins[i]);
                    }
                }
                else if(oldNumPlayers < game.getNumPlayers()){
                    for(int i = oldNumPlayers + 1; i <= game.getNumPlayers(); i++) {
                        playerWins[i] = new JLabel("Player " + i + ": 0");
                        winPanel.add(playerWins[i]);
                    }
                }
                boardSize = game.getSize();
                createBoard();
                revalidate();
                repaint();
            }

            // undos previous action
            if(e.getSource() == undoButton){
                if(game.canUndo()) {
                    ++undoCounter;
                    game.undo();
                }
                else{
                    JOptionPane.showMessageDialog(null, "No moves to undo");
                }
            }

            // for picking a square
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[0].length; col++) {
                    if (board[row][col] == e.getSource())
                        if (game.select(row, col)) {
                            board[row][col].setText("" + game.getCurrentPlayer());
                            player = game.nextPlayer();
                        } else {
                            JOptionPane.showMessageDialog(null, "Not a valid square! Pick again.");
                        }
                }
            }

            // setting the colors
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[0].length; col++) {
                    if (board[row][col] == e.getSource()) {
                        for(int i = 0; i < board.length; i++){
                            for(int j = 0; j < board[0].length; j++){
                                if(game.getRiskLevel(i, j) == 0){
                                    board[i][j].setBackground(Color.GREEN);
                                    board[i][j].setOpaque(true);
                                }
                                else if(game.getRiskLevel(i, j) == 1) {
                                    board[i][j].setBackground(Color.red);
                                    board[i][j].setOpaque(true);
                                }
                                else if(game.getRiskLevel(i, j) == 2){
                                    board[i][j].setBackground(Color.yellow);
                                    board[i][j].setOpaque(true);
                                }
                                else if(game.getRiskLevel(i, j) == -1){
                                    board[i][j].setBackground(null);
                                }
                            }
                        }
                    }
                }
            }



            displayBoard();
            int winner = game.getWinner();
            if (winner != -1) {
                for(int i = 1; i <= game.getNumPlayers(); i++){
                    String c = playerWins[i].getText().substring(10);
                    int wins = Integer.parseInt(c);
                    if(i == winner){
                        wins = wins + 1;
                        String d = Integer.toString(wins);
                        playerWins[i].setText("Player " + i + ": " + d);
                    }
                }
                JOptionPane.showMessageDialog(null, "Player " + winner + " Wins!");
                int result = JOptionPane.showConfirmDialog(null, "New Game?");
                switch (result) {
                    case JOptionPane.YES_OPTION:
                        for (int i = 0; i < boardSize; i++){
                            for (int j = 0; j < boardSize; j++){
                                panel1.remove(board[i][j]);
                            }
                        }
                        int oldNumPlayers = game.getNumPlayers();
                        game.reset();
                        if(game.getNumPlayers() < oldNumPlayers) {
                            for(int i = game.getNumPlayers() + 1; i <= oldNumPlayers; i++){
                                winPanel.remove(playerWins[i]);
                            }
                        }
                        else if(oldNumPlayers < game.getNumPlayers()){
                            for(int i = oldNumPlayers + 1; i <= game.getNumPlayers(); i++) {
                                playerWins[i] = new JLabel("Player " + i + ": 0");
                                winPanel.add(playerWins[i]);
                            }
                        }
                        boardSize = game.getSize();
                        createBoard();
                        revalidate();
                        repaint();
                        displayBoard();
                        break;
                    case JOptionPane.NO_OPTION:
                        System.exit(1);
                        break;
                    case JOptionPane.CANCEL_OPTION:
                    case JOptionPane.CLOSED_OPTION:
                        break;
                }


            }
        }
    }

    /*******************
     *
     *This method creates a JButton board thats as big as the boardSize
     *
     */
    private void createBoard() {
        board = new JButton[boardSize][boardSize];
        panel1.setLayout(new GridLayout(boardSize, boardSize));


        for (int i = 0; i < boardSize; i++){ // rows
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = new JButton("");
                board[i][j].addActionListener(listen);
                panel1.add(board[i][j]);
            }
        }
    }

    /*******************
     *
     *This method will set the players number to the board
     *
     */
    private void displayBoard() {
        for (int row = 0; row < boardSize; row++)
            for (int col = 0; col < boardSize; col++) {
                Cell c = game.getCell(row, col);
                if (c != null)
                    board[row][col].setText("" + c.getPlayerNumber());
                else
                    board[row][col].setText("");
            }


    }







}