package gamePackage;
import java.util.ArrayList;

/**
 * The Computer class represents a player that is a computer player.
 * This allows the computer player to be identified and make decisions.
 * This sub-class provides methods to make the decisions for the computer player.
 *
 * @author Anne McCubbin
 * @version 15.01.2025
 */

public class Computer extends Player{

	/**
     * Constructor for objects of sub-class Computer
     * @param playerName The player's name
     */
	public Computer(String playerName) {
		super(playerName);
	}
	
	/**
	 * Determines computer player's choice when they have an Ace, or several aces.
	 * Aces are automatically valued at 11, so a players possibleValues will be
	 * (the total of their non-ace cards) + (aceCount*11). A player could have up to
	 * four aces (default valued at 44), but for any number of aces, only the smallest
	 * two options could be under 21.
	 * If the larger of these two options is less than or equal to 21, this is chosen.
	 * Otherwise the smallest option is chosen, which is with each ace valued at 1.
	 * The player's handTotalValue is set to their choice.
	 * @param possibleValues An ArrayList of the possible totals of the player's hand.
	 * @param aceCount The number of aces the player has.
	 */
	public void aceChoice(ArrayList<Integer> possibleValues, int aceCount) {
		int choice = 0;
		if(getHandTotalValue()-(aceCount*10) +10 <=21) {
			choice = (getHandTotalValue()-(aceCount*10) +10);
		} else {
			choice = getHandTotalValue()-(aceCount*10);
		}		
		setHandTotalValue(choice);
	}
	
	/**
	 * Determines computer player's choice between hit' or 'stand'.
	 * If the computer's hand is valued at 17 or over, they 'stand'.
	 * Otherwise they choose to 'hit'. 
	 * @param value The total value of the player's hand.
	 * @return choice The player's choice between 'stand' or 'hit.
	 */
	public String hitStandChoice(int value) {
		String choice;
		if (value >= 17) {
			choice = "stand";
		} else {
			choice = "hit";
		}
		try {
			Thread.sleep(2*1000);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}
		return choice;
	}

}
