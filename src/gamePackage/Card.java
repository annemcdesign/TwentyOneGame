package gamePackage;
/**
 * The Card class represents a playing card with a suit, a title, a value in the game of blackjack
 * an ID number and a status of if its in the deck.
 * It provides methods to retrieve and set the card's information.
 *
 * @author Anne McCubbin
 * @version 15.01.2025
 */

public class Card {
	private String suit;
    private String title;
    private int value;
    private int cardID;
    private boolean inDeck;

    /**
     * Constructor for objects of class Card
     * @param suit The card's suit
     * @param title The card's title
     * @param value The card's value in the game
     * @param cardID The card's ID number
     */
    public Card(String suit, String title, int value, int cardID)
    {
        this.suit = suit;
        this.setTitle(title);
        this.value = value;
        this.cardID = cardID;
        this.inDeck = true;
    }
    
    /**
     * Sets the card's suit.
     * @param suit 
     */
    public void setSuit(String suit)
    {
        this.suit = suit;
    }
    
    /**
     * Returns the card's suit.
     * @return suit 
     */
    public String getSuit()
    {
        return suit;
    }
    
    /**
     * Sets the value the card has in
     * the game of blackjack.
     * @param value The value of the card.
     */
    public void setValue(int value)
    {
        this.value = value;
    }
    
    /**
     * Returns the card's value in
     * the game of blackjack.
     * @return value The value of the card.
     */
    public int getValue()
    {
        return value;
    }
    
    /**
     * Sets the card's title from options:
     * Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King.
     * @param title The title of the card.
     */
	public void setTitle(String title) {
		this.title = title;
	}
	
    /**
     * Returns the card's title.
     * @return title The title of the card.
     */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the ID number for the card.
	 * @param cardID The ID number for the card.
	 */
	public void setCardID(int cardID) {
		this.cardID = cardID;
	}

	/**
	 * Returns the card's ID number.
	 * @return cardID The card's ID number.
	 */
	public int getCardID() {
		return cardID;
	}

	/**
	 * Sets the status of whether the card is 
	 * currently in the deck (true) or not (false).
	 * @param inDeck Boolean - if the card is/isn't in the deck.
	 */
	public void setInDeck(boolean inDeck) {
		this.inDeck = inDeck;
	}
	
	/**
	 * Returns whether or not the card is currently in the deck.
	 * @return inDeck Boolean - if the card is/isn't in the deck.
	 */
	public boolean getInDeck() {
		return inDeck;
	}

}


