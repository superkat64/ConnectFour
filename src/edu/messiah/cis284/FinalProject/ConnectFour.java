package edu.messiah.cis284.FinalProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Katherine Piette & Jonathan Isaac 
 *
 * Basic skeleton of source code and initial GUI gameboard
 * From http://www.coderanch.com/t/642941/GUI/java/Connect-game-edited-version
 * Post Author: Lennart Van Ham
 * 
 */
	@SuppressWarnings("serial")
	public class ConnectFour extends JFrame {
	    
		//------------------------------------------------------------ 
		// Name-constants to represent the seeds and cell contents  
	    public static final int EMPTY = 0;  
	    public static final int PLAYER1 = 1;  
	    public static final int PLAYER2 = 2;  

	    // The game board and the game status
	    public static boolean playing = false;     
	    private int currentPlayer; // the current player (PLAYER1 or PLAYER2)  
	    private int Rows = 7, Columns = 8, AmountToWin = 5;  
	    private int[][] board = new int[Rows][Columns];  
	    MouseAdapter me;
	    ArcsPanel[][] arcs;

	   //------------------------------------------------------------
	   /**
	    * Constructor
	    * 
	    */ 
	    public ConnectFour(){  
	       initGame();
	       
	       final JPanel p1 = new JPanel();  
	       p1.setLayout(new GridLayout(Rows, Columns));
	       
	       arcs = new ArcsPanel[Rows][Columns];
	       me = new MouseAdapter() {
	          @Override
	          public void mouseClicked(MouseEvent me) {
	             ArcsPanel panel = (ArcsPanel) me.getSource();
	             int y = panel.column;
	             int x = availableRow(y);
	             if (x == -1) return;
	             
	             if (playing == true) {
	            	 board[x][y] = currentPlayer;
	            	 if (currentPlayer == PLAYER1) currentPlayer = PLAYER2;
	            	 else currentPlayer = PLAYER1; 
	            	 arcs[x][y].repaint();

	            	 int winner = checkForResult(x, y);
	            	 if (winner == 1) {
	            		new WinFrame(1);
	            		dispose();
	            	 }
	            	 if (winner == 2) {
	            		new WinFrame(2);
	            		dispose();
	            	 }
	             } 
	          }

	       };
	       
	       for (int i=0; i<Rows; i++) { 
	           for (int j = 0; j < Columns; j++) {
	              arcs[i][j] = new ArcsPanel(i, j);
	              p1.add(arcs[i][j]);  
	              arcs[i][j].addMouseListener(me);
	           }
	       }  
	       add(p1, BorderLayout.CENTER); 
	    
	       this.pack();
	       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       this.setLocationRelativeTo(null);
	       this.setVisible(true);
	    } 
	    
	     
	   //------------------------------------------------------------
	   /**
	    * 
	    */
	    public void initGame() {       
	        for (int row = 0; row < Rows; ++row) {  
	            for (int col = 0; col < Columns; ++col) {  
	                board[row][col] = EMPTY;  // all cells empty  
	            }  
	        }  
	        playing = true; // ready to play  
	        currentPlayer = PLAYER1;  // player 1 plays first  
	    }  
	     
	   //------------------------------------------------------------
	    /**
	     * Checks the board for a win. 
	     * 
	     * Takes the specific spot on the board (board[row][col]) and 
	     * checks left and right, up and down, and the diagonals if 
	     * those spots have the same value as the original. If they do,
	     * a counter keeps track and then when there are 4 matches, 
	     * the value from the original spot is returned and the boolean 
	     * value playing becomes false.
	     * 
	     * @param row 
	     * @param col
	     * @return
	     */
	    private int checkForResult(int row, int col) { 	

	    	int currentPlayer = board[row][col];
	    	int currentPlayerScoreHorizontal = 0;
	    	int currentPlayerScoreVertical = 0;
	    	int currentPlayerScoreNegative = 0;
	    	int currentPlayerScorePositive = 0;
	    	int new_col, new_row;

	    	// Check for Horizonal wins
	    	new_col = col;
	    	while (new_col != 8 && currentPlayer == board[row][new_col]) {
	    		new_col++;
	    		currentPlayerScoreHorizontal++;
	    	}
	    	new_col = col;
	    	while (new_col != 0 && currentPlayer == board[row][new_col]) {
	    		new_col--;
	    		currentPlayerScoreHorizontal++;
	    	}

	    	if (currentPlayerScoreHorizontal == AmountToWin) {
	    		playing = false;
	    		return currentPlayer;
	    	}

	    	// Check for Vertical Wins
	    	new_row = row;
	    	while (new_row != 7 && currentPlayer == board[new_row][col]) {
	    		new_row++;
	    		currentPlayerScoreVertical++;
	    	}
	    	new_row = row;
	    	while (new_row != 0 && currentPlayer == board[new_row][col]) {
	    		new_row--;
	    		currentPlayerScoreVertical++;	
	    	}

	    	if (currentPlayerScoreVertical == AmountToWin) {
	    		playing = false;
	    		return currentPlayer;
	    	}

	    	// Check for Negative Diagonal Wins
	    	currentPlayerScoreNegative = 0;
	    	new_row = row;
	    	new_col = col;
	    	while (new_row != 7 && new_col != 8 && currentPlayer == 
	    			board[new_row][new_col]) {
	    		new_row++;
	    		new_col++;
	    		currentPlayerScoreNegative++;
	    	}
	    	new_row = row;
	    	new_col = col;
	    	while (new_row != 0 && new_col != 0 && currentPlayer == 
	    			board[new_row][new_col]) {
	    		new_row--;
	    		new_col--;
	    		currentPlayerScoreNegative++;
	    	}

	    	if (currentPlayerScoreNegative == AmountToWin) {
	    		playing = false;
	    		return currentPlayer;
	    	}

	    	// Check for Positive Diagonal Wins
	    	currentPlayerScorePositive = 0;
	    	new_row = row;
	    	new_col = col;
	    	while (new_row != 7 && new_col != 0 && currentPlayer == 
	    			board[new_row][new_col]) {
	    		new_row++;
	    		new_col--;
	    		currentPlayerScorePositive++;
	    	}
	    	new_row = row;
	    	new_col = col;
	    	while (new_row != 0 && new_col != 8 && currentPlayer == 
	    			board[new_row][new_col]) {
	    		new_row--;
	    		new_col++;
	    		currentPlayerScorePositive++;
	    	}
	    	
	    	if (currentPlayerScorePositive == AmountToWin) {
	    		playing = false;
	    		return currentPlayer;
	    	}
	    	
	    return 0;
	    
	    }

	    //------------------------------------------------------------
	    /** Finds the first empty space in a column starting at the bottom.*/ 
	    public int availableRow(int col){   
	    	for( int row = Rows -1; row >= 0; row--){  
	    		if(board[row][col] == EMPTY){  
	    			return row;              
	    		}  
	    	}
	    	return -1;    
	    }    
	     
	   //------------------------------------------------------------
	    /**
	     * 
	     * @author Katie
	     *
	     */
	    class ArcsPanel extends JPanel {   
	       int row, column;
	       
	       public ArcsPanel(int r, int c) {
	          row = r;
	          column = c;
	          this.setBackground(Color.BLUE);
	          this.setPreferredSize(new Dimension(100, 100));  // try commenting out this line!
	       }
	   
	   //------------------------------------------------------------
	       public void paintComponent(Graphics g) {  
	            super.paintComponent(g);  
	            Color c = 
	                board[row][column] == EMPTY   ? Color.WHITE :
	                board[row][column] == PLAYER1 ? Color.RED   :
	                                                Color.YELLOW;
	            g.setColor(c);
	            Graphics2D g2d = (Graphics2D) g.create();
	            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	            g2d.fillOval(10, 10, 90, 90);  
	            g2d.dispose();
	        }  
	    } // end of nested class ArcsPanel 
	     
	   //------------------------------------------------------------
	    
	}  // end of class ConnectFour
