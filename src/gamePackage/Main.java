package gamePackage;
import javax.swing.SwingUtilities;

/**
 * The Main class is the entry point of the program.
 * The program will always have a game and a user interface.
 * The class contains the main method which starts the execution
 * of the program, and one other method to start a new game,
 * which is called from the main method, or alternatively 
 * from a "New Game" button in the user interface.
 * @author Anne McCubbin
 * @version 15.01.2025
 *
 */
public class Main {

	private static Game game;
	private static UserInterface userInterface;
	
	/**
	 * The entry point for the program.
	 * @param args
	 */
	public static void main(String[] args) {
		startNewGame();
	}
	
	/**
	 * Function to start a new game.
	 * Creates a new user interface.
	 * Creates a new game and calls functions from the Game class
	 * to initialise and run the game, until the game is over.
	 * 
	 */
	public static void startNewGame() {
		UserInterface userInterface = new UserInterface();
		SwingUtilities.invokeLater(() -> {
			userInterface.setVisible(true);
			userInterface.showCenterMessage("Welcome to the game!");
		});
		new Thread(() ->{
			Game game = new Game("Game 1", userInterface);		
			game.setCards();
			String inputMessage = "Player, please enter your name: ";
			game.setPlayers(userInterface.inputField(inputMessage));
			game.dealCards();
			while (game.getGameOver() == false) {
				game.revealCards();
				try {
					Thread.sleep(1*1000);
				} catch (InterruptedException ie) {
					Thread.currentThread().interrupt();
				}
				game.checkBustStood();
			}
		}).start();		
	}
	
}
