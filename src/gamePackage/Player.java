package gamePackage;
import java.util.ArrayList;
/**
 * The Player class represents a player that will have a sub-type of 
 * either Computer or Human. All players have a name, a hand of cards,
 * and a total value of their hand. Three boolean variables indicate
 * if the player has stood, has an ace, or has blackjack. Additionally,
 * players with an ace may have a list of possible totals for their hand.
 * It provides functions to retrieve and set the player's information and 
 * the information relating to their hand of cards.
 *
 * @author Anne McCubbin
 * @version 15.01.2025
 */
public class Player
{
    private String playerName;
    private ArrayList<Card> hand;
    int handTotalValue;
    boolean stood;
    boolean handHasAce;
    boolean hasBlackjack;
    ArrayList<Integer> possibleValues = new ArrayList<>();

    /**
     * Constructor for objects of class Player
     * @param playerName The player's name
     */
    public Player(String playerName)
    {
        this.playerName = playerName;
        hand = new ArrayList<>();
        handTotalValue = 0;
        stood = false;
        handHasAce = false;
    }
    
    /**
     * Sets the player's name.
     * @param playerName The player's name.
     */
    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }
    
    /**
     * Returns the player's names.
     * @return playerName The player's name.
     */
    public String getPlayerName(){
        return playerName;
    }
    
    /**
     * Sets the player's hand of cards by adding new
     * cards to the 'hand' ArrayList.
     * @param cardID The ID number of the card to add.
     */
    public void setHand(Card cardID){
        hand.add(cardID);
    }
    
    /**
     * Returns the player's hand of cards.
     * @return hand The ArrayList of cards in the player's hand.
     */
    public ArrayList<Card> getHand(){
        return hand;
    }
    
    /**
     * Sets the total value of the player's hand.
     * @param handTotalValue The total value of the player's hand.
     */
    public void setHandTotalValue(int handTotalValue) {
    	this.handTotalValue = handTotalValue;
    }
    
    /**
     * Returns the total value of the player's hand.
     * @return handTotalValue The total value of the player's hand.
     */
    public int getHandTotalValue() {
    	return handTotalValue;
    }
    
    /**
     * Sets whether the player has chosen to stand (true)
     * or has not yet stood (false).
     * @param stood Boolean if the player has stood.
     */
    public void setStood(boolean stood){
    	this.stood = stood;
    }
    
    /**
     * Returns whether the player has chosen to stand (true)
     * or has not yet stood (false).
     * @return stood Boolean if the player has stood.
     */
    public boolean getStood() {
    	return stood;
    }
    
    /**
     * Sets if the player has an ace (true) or not (false).
     * @param handHasAce Boolean if the player has an ace.
     */
    public void setHandHasAce(boolean handHasAce) {
    	this.handHasAce = handHasAce;
    }
    
    /**
     * Returns if the player has an ace (true) or not (false).
     * @return handHasAce Boolean if the player has an ace.
     */
    public boolean getHandHasAce() {
    	return handHasAce;
    }
    
    /**
     * Sets if the player has (10 + Ace) blackjack (true) or not (false). 
     * @param hasBlackjack Boolean if the player has blackjack.
     */
    public void setHasBlackjack(boolean hasBlackjack) {
    	this.hasBlackjack = hasBlackjack;
    }
    
    /**
     * Sets if the player has (10 + Ace) blackjack (true) or not (false). 
     * @return hasBlackjack Boolean if the player has blackjack.
     */
    public boolean getHasBlackjack() {
    	return hasBlackjack;
    }
    
    /**
     * Sets the list of possible total values for a player's hand if they
     * have an ace.
     * @param possibleValues The list of possible values.
     */
    public void setPossibleValues(ArrayList<Integer> possibleValues){
    	this.possibleValues = possibleValues;
    }
    
    /**
     * Gets the list of possible total values for a player's hand if they
     * have an ace.
     * @return possibleValues The list of possible values.
     */
    public ArrayList<Integer> getPossibleValues(){
    	return possibleValues;
    }
}
