import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * The main class representing a simple card game application.
 */
public class SimpleCardGame {
	JFrame frame;
    JTextField txt_inputbet;
	JButton btn_rpcard1; 
	JButton btn_rpcard2;
	JButton btn_rpcard3;
	JButton btn_start;
	JButton btn_result;
	JLabel label_bet;
	JLabel label_info;
	ImageIcon Image1;
	ImageIcon Image2;
	ImageIcon Image3;
	ImageIcon Image4;
	ImageIcon Image5;
	ImageIcon Image6;
	JLabel label_Image1;
	JLabel label_Image2;
	JLabel label_Image3;
	JLabel label_Image4;
	JLabel label_Image5;
	JLabel label_Image6;
	card Cards;

	/**
     * The main method that initializes and starts the simple card game.
     */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleCardGame cardgame = new SimpleCardGame();
		cardgame.go();		
	}
	
	/**
     * Initializes and sets up the components of the card game GUI.
     */
	public void go() {
		
		Cards = new card();
		
		//6 image labels
		label_Image1 = new JLabel();
		label_Image2 = new JLabel();
		label_Image3 = new JLabel();
		label_Image4 = new JLabel();
		label_Image5 = new JLabel();
		label_Image6 = new JLabel();
		
		//5 buttons
		btn_rpcard1 = new JButton("Replace Card 1");
		btn_rpcard2 = new JButton("Replace Card 2");
		btn_rpcard3 = new JButton("Replace Card 3");
		btn_start = new JButton("Start");
		btn_result = new JButton("Result");
		
		//2 labels for information at the bottom panel
		label_bet = new JLabel("Please place your bet!");
		label_info = new JLabel("Amount of money you have $" + Cards.getmoney());
		
		//1 label and 1 textfield for inputting bet
		JLabel label_inputbet = new JLabel("Bet:$");
		txt_inputbet = new JTextField(10);
		
		//6 images
		Image1 = new ImageIcon("Images/card_back.gif");
		Image2 = new ImageIcon("Images/card_back.gif");
		Image3 = new ImageIcon("Images/card_back.gif");
		Image4 = new ImageIcon("Images/card_back.gif");
		Image5 = new ImageIcon("Images/card_back.gif");
		Image6 = new ImageIcon("Images/card_back.gif");
		
		//6 panels
		JPanel MainPanel = new JPanel();
		JPanel DealerPanel = new JPanel();
		JPanel PlayerPanel = new JPanel();
		JPanel RpCardBtnPanel = new JPanel();
		JPanel ButtonPanel = new JPanel();
		JPanel InfoPanel = new JPanel();
		
		//adding action listener to the 5 buttons
		btn_start.addActionListener(new Start());
		btn_rpcard1.addActionListener(new Replace1());
		btn_rpcard2.addActionListener(new Replace2());
		btn_rpcard3.addActionListener(new Replace3());
		btn_result.addActionListener(new Result());
		
		//setting images to their designated labels
		label_Image1.setIcon(Image1);
		label_Image2.setIcon(Image2);
		label_Image3.setIcon(Image3);
		label_Image4.setIcon(Image4);
		label_Image5.setIcon(Image5);
		label_Image6.setIcon(Image6);
		
		//adding the elements to the panels
		DealerPanel.add(label_Image1);
		DealerPanel.add(label_Image2);
		DealerPanel.add(label_Image3);
		PlayerPanel.add(label_Image4);
		PlayerPanel.add(label_Image5);
		PlayerPanel.add(label_Image6);
		
		RpCardBtnPanel.add(btn_rpcard1);
		RpCardBtnPanel.add(btn_rpcard2);
		RpCardBtnPanel.add(btn_rpcard3);
		
		ButtonPanel.add(label_inputbet);
		ButtonPanel.add(txt_inputbet);
		ButtonPanel.add(btn_start);
		ButtonPanel.add(btn_result);
		
		InfoPanel.add(label_bet);
		InfoPanel.add(label_info);
		
		MainPanel.setLayout(new GridLayout(5,1));
		MainPanel.add(DealerPanel);
		MainPanel.add(PlayerPanel);
		MainPanel.add(RpCardBtnPanel);
		MainPanel.add(ButtonPanel);
		MainPanel.add(InfoPanel);
		
		// Optional background color setting
		DealerPanel.setBackground(Color.green);
		PlayerPanel.setBackground(Color.green);
		RpCardBtnPanel.setBackground(Color.green);
		
		//setting the frame
		frame = new JFrame(); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		//adding panel to the frame
		frame.getContentPane().add(MainPanel); 
		
		//initialize menubar
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Control"); 
		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(new ExitButton());
		menu.add(menuItem); 
		menuBar.add(menu); 
		frame.setJMenuBar(menuBar);
		frame.setTitle("A Simple Card Game"); 
		frame.setSize(400, 700);
		frame.setVisible(true);
		
		//button
		btn_start.setEnabled(true);
		btn_result.setEnabled(false);
		btn_rpcard1.setEnabled(false);
		btn_rpcard2.setEnabled(false);
		btn_rpcard3.setEnabled(false);
		
		
	}
	
	/**
	 * ActionListener implementation for handling the exit button action.
	 */
	class ExitButton implements ActionListener{
		/**
		 * Invoked when the exit button is clicked. Disposes of the frame.
		 * @param event The ActionEvent representing the Exit button has been clicked.
		 */
		public void actionPerformed(ActionEvent event) {
			frame.dispose();
		}
	}
	
	/**
	 * ActionListener implementation for handling the start button action.
	 */
	class Start implements ActionListener{
		/**
		 * Invoked when the start button is clicked. 
		 * Processes the user's bet and initializes the game.
		 * @param event The ActionEvent representing the Start button has been clicked.
		 */
		public void actionPerformed(ActionEvent event) {
			int bet = Integer.parseInt(txt_inputbet.getText());
			if (bet>0 && bet<=Cards.getmoney()) {
				label_bet.setText("Your current bet is: $" + String.valueOf(bet));
				label_info.setText("Your current money is: $" + Cards.getmoney());
				btn_start.setEnabled(false);
				btn_result.setEnabled(true);
				btn_rpcard1.setEnabled(true);
				btn_rpcard2.setEnabled(true);
				btn_rpcard3.setEnabled(true);
				Cards.shuffle();
				ArrayList<String> DrawnCards = Cards.getcard();
				Image1 = new ImageIcon("Images/card_back.gif");
				Image2 = new ImageIcon("Images/card_back.gif");
				Image3 = new ImageIcon("Images/card_back.gif");
				Image4 = new ImageIcon(DrawnCards.get(0));
				Image5 = new ImageIcon(DrawnCards.get(1));
				Image6 = new ImageIcon(DrawnCards.get(2));
				label_Image1.setIcon(Image1);
				label_Image2.setIcon(Image2);
				label_Image3.setIcon(Image3);
				label_Image4.setIcon(Image4);
				label_Image5.setIcon(Image5);
				label_Image6.setIcon(Image6);
				
			}
			else {
				label_bet.setText("Please input a valid amount.");
			}
		}
	}
	
	/**
	 * ActionListener implementation for handling the replacement of the first card.
	 */
	class Replace1 implements ActionListener{
		/**
		 * Invoked when the first replace button is clicked. 
		 * Replaces the first card in the game.
		 * @param event The ActionEvent representing the Replace 1 button has been clicked.
		 */
		public void actionPerformed(ActionEvent event) {
			Cards.replace(0);
			ArrayList<String> DrawnCards = Cards.getcard();
			Image4 = new ImageIcon(DrawnCards.get(0));
			label_Image4.setIcon(Image4);
			btn_rpcard1.setEnabled(false);
			if (btn_rpcard1.isEnabled()==false && btn_rpcard3.isEnabled()==false) {
				btn_rpcard2.setEnabled(false);
			}
			if (btn_rpcard1.isEnabled()==false && btn_rpcard2.isEnabled()==false) {
				btn_rpcard3.setEnabled(false);
			}
		}
	}
	
	/**
	 * ActionListener implementation for handling the replacement of the second card.
	 */
	class Replace2 implements ActionListener{
		/**
		 * Invoked when the second replace button is clicked. 
		 * Replaces the second card in the game.
		 * @param event The ActionEvent representing the Replace 2 button has been clicked.
		 */
		public void actionPerformed(ActionEvent event) {
			Cards.replace(1);
			ArrayList<String> DrawnCards = Cards.getcard();
			Image5 = new ImageIcon(DrawnCards.get(1));
			label_Image5.setIcon(Image5);
			btn_rpcard2.setEnabled(false);
			if (btn_rpcard1.isEnabled()==false && btn_rpcard2.isEnabled()==false) {
				btn_rpcard3.setEnabled(false);
			}
			if (btn_rpcard2.isEnabled()==false && btn_rpcard3.isEnabled()==false) {
				btn_rpcard1.setEnabled(false);
			}
		}
	}
	
	/**
	 * ActionListener implementation for handling the replacement of the third card.
	 */
	class Replace3 implements ActionListener{
		/**
		 * Invoked when the third replace button is clicked. 
		 * Replaces the third card in the game.
		 * @param event The ActionEvent representing the Replace 3 button has been clicked.
		 */
		public void actionPerformed(ActionEvent event) {
			Cards.replace(2);
			ArrayList<String> DrawnCards = Cards.getcard();
			Image6 = new ImageIcon(DrawnCards.get(2));
			label_Image6.setIcon(Image6);
			btn_rpcard3.setEnabled(false);
			if (btn_rpcard2.isEnabled()==false && btn_rpcard3.isEnabled()==false) {
				btn_rpcard1.setEnabled(false);
			}
			if (btn_rpcard1.isEnabled()==false && btn_rpcard3.isEnabled()==false) {
				btn_rpcard2.setEnabled(false);
			}
		}
		
	}
	
	/**
	 * ActionListener implementation for handling the result button action.
	 */
	class Result implements ActionListener{
		/**
	     * Invoked when the result button is clicked. 
	     * Processes the game result, updates UI, and provides feedback to the player.
	     * @param event The ActionEvent representing the Result button has been clicked.
	     */
		public void actionPerformed(ActionEvent event) {
			int bet = Integer.parseInt(txt_inputbet.getText());
			ArrayList<String> DrawnCards = Cards.getcard();
			Image1 = new ImageIcon(DrawnCards.get(6));
			Image2 = new ImageIcon(DrawnCards.get(7));
			Image3 = new ImageIcon(DrawnCards.get(8));
			label_Image1.setIcon(Image1);
			label_Image2.setIcon(Image2);
			label_Image3.setIcon(Image3);
			Boolean player = Cards.getresult(bet);
			if (Cards.checklose()) {
				label_bet.setText("You have no more money!");
				label_info.setText("Please start a new game!");
				JOptionPane.showMessageDialog(null, "Game over!\nYou have no more money!\nPlease start a new game!", "Message", JOptionPane.INFORMATION_MESSAGE);
				btn_start.setEnabled(false);
				btn_result.setEnabled(false);
				btn_rpcard1.setEnabled(false);
				btn_rpcard2.setEnabled(false);
				btn_rpcard3.setEnabled(false);
			}
			else {
				if (player) {
					JOptionPane.showMessageDialog(null, "Congratulations! You win this round!", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "Sorry! The Dealer wins this round!", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
				label_bet.setText("Your current bet is: $" + String.valueOf(bet));
				label_info.setText("Your current money is: $" + Cards.getmoney());
				btn_start.setEnabled(true);
				btn_result.setEnabled(false);
				btn_rpcard1.setEnabled(false);
				btn_rpcard2.setEnabled(false);
				btn_rpcard3.setEnabled(false);
			}
		}
		
		
	}
	
		
		

}
