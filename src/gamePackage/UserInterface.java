package gamePackage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.Image;
import java.util.ArrayList;

/**
 * The UserInterface class is used to create a user interface for the game.
 * It is created using a JFrame, and contains many JLabel components to display
 * messages, scores and images to the player. JOptionPane components are used
 * to give a player choices, and a JButton appears on the completion of one game
 * to offer a "New Game" choice.
 * It has functions: to draw the interface, set the background colour and 
 * bring up a playerName input field. There are functions to display: a central 
 * message, player names, total scores, a computer status, a stood status, and to
 * update a label. It has functions to show images of the cards, and to reveal
 * computer's cards upon completion of the game. There are functions: to provide 
 * choices for ace values and hit/stand choice, to show a new game button, to close
 * the user interface, and to call to start a new game.
 * @author Anne McCubbin
 * @version 15.01.2025
 */
public class UserInterface extends JFrame{
	
	private Game game;
	URL imagePath = getClass().getResource("/resources" + "0.png");
	JLabel centerLabel = new JLabel("");
	JLabel computerTotalLabel = new JLabel("");
	JLabel humanTotalLabel = new JLabel("");
	JLabel computerStoodLabel = new JLabel("");
	JLabel humanStoodLabel = new JLabel("");
	JLabel computerStatusLabel = new JLabel("");
	JButton newGameButton = new JButton("New Game");
	int targeCardtWidth = (57*3);
	int targetCardHeight = (89*3);
	Color LIGHT_YELLOW = new Color(225,225,100);
	Color LIGHT_BLUE = new Color(173, 210, 200);
	Color LIGHT_PURPLE = new Color(191, 128, 228);
	
	/**
	 * Function to create the user interface. 
	 * Sets up and draws the window.
	 */
	public UserInterface() {
		setTitle("UserInterface");
		setSize(1600, 1000);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setBackground();
	}
	
	/**
	 * Function to create a "New Game" button with an 
	 * action listener. When clicked, closeUI() is called 
	 * to close the user interface, and callStartNewGame()
	 * is called to organise a new game.
	 */
	public void showNewGameButton() {
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeUI();
				callStartNewGame();
			}
		});
		newGameButton.setBounds(100, (1000/2-80), 200, 100);
		newGameButton.setFont(new Font("Arial", Font.BOLD, 24));
		newGameButton.setBackground(Color.WHITE);
		add(newGameButton);
		revalidate();
		repaint();
	}
	
	/**
	 * Function to close the current user interface.
	 */
	public void closeUI() {
		dispose();
	}
	
	/**
	 * Function to start a new game.
	 * Calls startNewGame() from the Main Class.
	 */
	public void callStartNewGame() {
		Main.startNewGame();
	}
	
	/**
	 * Function to set the background colour to green.
	 */
	public void setBackground() {
		getContentPane().setBackground(new Color(0, 100, 0));
	}
	
	/**
	 * Function to create a large message label in the centre of the screen.
	 * Sets text, positioning, font, size and colour of the message.
	 * @param centerMessage The message to display.
	 */
	public void showCenterMessage(String centerMessage) {
		centerLabel.setText(centerMessage);
		centerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		centerLabel.setBounds((1800/2-600), (1000/2-60), 1400, 60); // x y width height
		centerLabel.setFont(new Font("Arial", Font.BOLD, 48));
		centerLabel.setForeground(Color.WHITE);
		add(centerLabel);
	}
	
	/**
	 * Function to update the text of a label.
	 * @param label The label to update,
	 * @param newText The new text message to display.
	 */
	public void updateLabelText(JLabel label, String newText) {
		label.setText(newText);
		revalidate();
		repaint();
	}
	
	/**
	 * Function to bring up a pop-up text input field.
	 * Used to set the players name. If there is no input or
	 * player shuts the box, player name becomes "Unnamed".
	 * @param inputMessage The message on the box instructing player to input their name.
	 * @return input The name that the player inputed.
	 */
	public String inputField(String inputMessage) {
		String input = JOptionPane.showInputDialog(this, inputMessage);
			if (input == null) {
				input = "Unnamed";
			}
			return input;
	}
	
	/**
	 * Function to show the names of the players on the screen. Creates two instances
	 * of playerNameLabel on different y-positions on the screen. 
	 * Sets colours and font for these labels.
	 * @param player The player whose name is to be displayed.
	 */
	public void showPlayerName(Player player) {
		JLabel playerNameLabel = new JLabel("Player: " + player.getPlayerName());
		if (player instanceof Computer) {
			playerNameLabel.setBounds(100, 35, 300, 30); // x y width height
			playerNameLabel.setForeground(LIGHT_BLUE);
		} else {
			playerNameLabel.setBounds(100, 550, 300, 30); // x y width height
			playerNameLabel.setForeground(LIGHT_PURPLE);
		}
		playerNameLabel.setFont(new Font("Arial", Font.BOLD, 24));
		add(playerNameLabel);
		revalidate();
		repaint();
	}
	
	/**
	 * Function to take a message of a player's total hand value, determine if the player
	 * is a computer or human player and call upon either computerTotalMessage() or
	 * humanTotalMessage() to display the message in the appropriate location.
	 * @param totalMessage The message to be displayed with players total hand value.
	 * @param player The player that the score belongs to, that this message will be displayed next to.
	 */
	public void scoreTotalMessage(String totalMessage, Player player) {
		if (player instanceof Computer) {
			computerTotalMessage(totalMessage, player);
		} else {
			humanTotalMessage(totalMessage, player);
		}
	}
	
	/**
	 * Function to show a message telling the computer's total hand value.
	 * For the computer player, this will be "secret" until revealed at 
	 * the end of the game. Sets colours and font for this message label.
	 * @param totalMessage The message to be displayed with players total hand value.
	 * @param player The player that the score belongs to, that this message will be displayed next to.
	 */
	public void computerTotalMessage(String totalMessage, Player player) {
		computerTotalLabel.setText(totalMessage + ".");
		computerTotalLabel.setBounds(650, 35, 600, 30); // x y width height
		computerTotalLabel.setFont(new Font("Arial", Font.BOLD, 24));
		computerTotalLabel.setForeground(LIGHT_BLUE);
		add(computerTotalLabel);
		revalidate();
		repaint();
	}
	
	/**
	 * Function to show a message telling the human player's total hand value.
	 * Sets colours and font for this message label.
	 * @param totalMessage The message to be displayed with players total hand value.
	 * @param player The player that the score belongs to, that this message will be displayed next to.
	 */
	public void humanTotalMessage(String totalMessage, Player player) {
		humanTotalLabel.setText(totalMessage + ".");
		humanTotalLabel.setBounds(650, 550, 600, 30); // x y width height
		humanTotalLabel.setFont(new Font("Arial", Font.BOLD, 24));
		humanTotalLabel.setForeground(LIGHT_PURPLE);
		add(humanTotalLabel);
		revalidate();
		repaint();
	}
	
	/**
	 * Function to show a message under the computer's hand of cards, showing
	 * status of what the computer is doing (such as 'making a choice' or
	 * 'drawing another card'). Sets colours and font for this message label.
	 * @param statusMessage The message to be displayed.
	 * @param player The player the message corresponds to.
	 */
	public void computerStatusMessage(String statusMessage, Player player) {
		if (player instanceof Computer) {
			computerStatusLabel.setText(statusMessage + "");
			computerStatusLabel.setBounds(100, 380, 700, 30); // x y width height
			computerStatusLabel.setFont(new Font("Arial", Font.BOLD, 24));
			computerStatusLabel.setForeground(LIGHT_BLUE);
			add(computerStatusLabel);
			revalidate();
			repaint();
		}
	}
	
	/**
	 * Function to display a message next to a player's name if they 
	 * have chosen to "stand". Determines type of player and calls upon
	 * either computerStoodMessage() or humanStoodMessage().
	 * @param player The player the message corresponds to.
	 * @param message The message to be displayed.
	 */
	public void stoodMessage(Player player, String message) {
		if (player instanceof Computer) {
			computerStoodMessage(player, message);
		} else {
			humanStoodMessage(player, message);
		}
	}
	
	/**
	 * Function to display a message next to a computer's name if they 
	 * have chosen to "stand". Sets colours and font for this message label.
	 * @param player The player the message corresponds to.
	 * @param message The message to be displayed.
	 */
	public void computerStoodMessage(Player player, String message) {
		if (player.getStood() == true) {
			computerStoodLabel.setText(message);
			computerStoodLabel.setBounds(400, 35, 600, 30); // x y width height
			computerStoodLabel.setFont(new Font("Arial", Font.BOLD, 24));
			computerStoodLabel.setForeground(LIGHT_YELLOW);
			add(computerStoodLabel);
			revalidate();
			repaint();
		}		
	}
	
	/**
	 * Function to display a message next to a human player's name if they 
	 * have chosen to "stand". Sets colours and font for this message label.
	 * @param player The player the message corresponds to.
	 * @param message The message to be displayed.
	 */
	public void humanStoodMessage(Player player, String message) {
		if (player.getStood() == true) {
			humanStoodLabel.setText(message);
			humanStoodLabel.setBounds(400, 550, 600, 30); // x y width height
			humanStoodLabel.setFont(new Font("Arial", Font.BOLD, 24));
			humanStoodLabel.setForeground(LIGHT_YELLOW);
			add(humanStoodLabel);
			revalidate();
			repaint();
		}
	}
	
	/**
	 * Function to display a card image. Called whenever a player is dealt a card.
	 * The computer player's cards are shown upside down while the game is in play,
	 * so that the human player cannot see them - imageNo 99 refers to the card back.
	 * Otherwise the imageNo passed through the parameters is used.
	 * Scales down the card image and puts it on an JLabel for display.
	 * Location for card to be displayed is determined dynamically based on the size
	 * of the players hand, so each newly drawn card is placed one spot on from the previous.
	 * @param imageNo The number of the card image to be displayed.
	 * @param player The player who the card belongs to.
	 * @param game The game in play.
	 */
	public void displayImage(int imageNo, Player player, Game game) {
		if (player instanceof Computer && game.getGameOver() == false) {
			imageNo = 99;
		}
		URL imagePath = getClass().getResource("/resources/" + imageNo + ".png");
		ImageIcon originalIcon = new ImageIcon(imagePath);
		Image resizedImage = originalIcon.getImage().getScaledInstance(targeCardtWidth, targetCardHeight, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImage);
		JLabel imageLabel = new JLabel(resizedIcon);	
		int x = (player.getHand().size()*200-100);
		if (player instanceof Computer) {
			imageLabel.setBounds(x, 100, targeCardtWidth, targetCardHeight);
		} else {
			imageLabel.setBounds(x, 600, targeCardtWidth, targetCardHeight);
		}
		add(imageLabel);
		revalidate();
		repaint();
	}
	
	/**
	 * Function to show a player their options when they have an ace 
	 * and allow them to make a choice. Takes an ArrayList of options,
	 * converts it into an Array of options, and brings up a JOptionPane
	 * with the choices that the player can choose from. A player can have 
	 * up to four aces, choice pop-up will expand accordingly.
	 * @param optionsList The ArrayList of player's possible hand values.
	 * @return optionsList.get(choice) The player's choice.
	 */
	public int showAceChoiceOptions(ArrayList<Integer> optionsList) {
        Object[] options = optionsList.toArray(new Integer[0]);
		int choice = JOptionPane.showOptionDialog(
				null, // parent component
				"Choose an option:", // message
				"Ace Choice", //title
				JOptionPane.YES_NO_OPTION, //option type
				JOptionPane.QUESTION_MESSAGE, // message type
				null, // icon, can be null
				options, // options array
				options[0] // initial value
				);
		if (choice == JOptionPane.CLOSED_OPTION) {
			showAceChoiceOptions(optionsList);
		}
		return optionsList.get(choice); // returns the users choice
	}
	
	/**
	 * Function to allow a player to choose between "Hit" and "Stand".
	 * Brings up a JOptionPane with the two choices and returns the 
	 * player's choice.
	 * @return The player's choice between "Hit" or "Stand".
	 */
	public String showHitStandChoiceOptions() {    
		Object[] options = {"Hit", "Stand"}; 
		int choice = JOptionPane.showOptionDialog(
				null, // parent component
				"Do you wish to hit (draw another card) or stand with your existing cards?:", // message
				"Hit or Stand", //title
				JOptionPane.YES_NO_OPTION, //option type
				JOptionPane.QUESTION_MESSAGE, // message type
				null, // icon, can be null
				options, // options array
				options[0] // initial value
				);
		if (choice == JOptionPane.CLOSED_OPTION) {
			showHitStandChoiceOptions();
		}
		if (choice == JOptionPane.YES_OPTION) {
			return "Hit";
		} else {
			return "Stand";
		}
	}
	

	/**
	 * Function to redraw all of the computer's cards the right
	 * way up, which is called when the game is over, to let the
	 * player see what cards the computer had. Called from the
	 * gameOver() function in the Game Class.
	 * For every card in the computer's hand, the corresponding image
	 * is retrieved, resized and displayed over the existing card backs.
	 * @param player The computer player.
	 * @param game The game in play.
	 */
	public void computerCardReveal(Player player, Game game) {
		int index = 1;
		for (Card card : player.getHand()) {			
			URL imagePath = getClass().getResource("/resources/" + card.getCardID() + ".png");
			ImageIcon originalIcon = new ImageIcon(imagePath);
			Image resizedImage = originalIcon.getImage().getScaledInstance(targeCardtWidth, targetCardHeight, Image.SCALE_SMOOTH);
			ImageIcon resizedIcon = new ImageIcon(resizedImage);
			JLabel imageLabel = new JLabel(resizedIcon);
			int x = (index*200-100);
			if (player instanceof Computer) {
				imageLabel.setBounds(x, 100, targeCardtWidth, targetCardHeight);
			}
			index++;
			add(imageLabel, 0); // the zero fixes the order to put it on top of the earlier cards
			revalidate();
			repaint();
		}
	}
 
}
