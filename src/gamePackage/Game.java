package gamePackage;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * The Game class represents a game of blackjack.
 * All games have a gameName, a user interface, a deck of cards, and some players.
 * Each game also has a gameOver status.
 * A game may also have a winner and a loser.
 * The Game class provides functions: to set and get the game information,
 * set and get the deck of cards and set and get the players.
 * It has functions: to deal cards (both for the initial hand of 2, and for
 * additional cards), to reveal cards and the total value of a players hand.
 * It has functions: to handle the choice between ace values, counting of aces,
 * checking if players have bust or stood and the choice between "hit" and "stand".
 * It has functions: to compare values when all players have chosen to "stand", to
 * check for draws, and to compare player's hands to check for blackjack if there is 
 * a draw at 21.
 * Finally, there are functions: to handle what happens when the game is over and 
 * identifying the winner and loser.
 *
 * @author Anne McCubbin
 * @version 15.01.2025
 */

public class Game {
	private String gameName;
	private UserInterface ui;
    private ArrayList<Card> cards;
    private ArrayList<Player> players;
    private boolean gameOver;
    private Player winner;
    private Player loser;

    /**
     * Constructor for objects of class Game
     * @param gameName The name of the game.
     */
    public Game(String gameName, UserInterface ui)
    {
        this.gameName = gameName;
        this.ui = ui;
        cards = new ArrayList<>();
        players = new ArrayList<>();
        this.gameOver = false;
    }
    
    /**
     * Sets the name of the game.
     * @param gameName The name of the game.
     */
    public void setGameName(String gameName)
    {
        this.gameName = gameName;
    }
    
    /**
     * Returns the name of the game.
     * @return gameName The name of the game.
     */
    public String getGameName()
    {
        return gameName;
    }
    
    /**
     * Sets the winner of the game.
     * @param winner The winner of the game.
     */
    public void setWinner(Player winner)
    {
        this.winner = winner;
    }
    
    /**
     * Returns the winner of the game.
     * @return winner The winner of the game.
     */
    public Player getWinner()
    {
        return winner;
    }
    
    /**
     * Sets the loser of the game.
     * @param loser The loser of the game.
     */
    public void setLoser(Player loser)
    {
        this.loser = loser;
    }
    
    /**
     * Returns the loser of the game.
     * @return loser The loser of the game.
     */
    public Player getLoser()
    {
        return loser;
    }
    
    /**
     * Sets the cards in play to a regular pack of 52 cards by creating
     * a Card object to represent each playing card, with its suit, title, 
     * value in the game of blackjack, and an card ID number.
     */
    public void setCards()
    {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        int cardID = 0;
        for (int i = 1; i < 5; i++){
            String currentSuit = suits[i-1];
            String cardTitle = "";
            int cardValue = 0;
            for (int j = 1; j < 14; j++){
                if (j == 1) {
                	cardTitle = "Ace";
                	cardValue = 11;
                }
                else if (j > 1 && j < 11) {
                	cardTitle = Integer.toString(j);
                	cardValue = j;
                }
                else if (j == 11) {
                	cardTitle = "Jack";
                	cardValue = 10;
                }
                else if (j == 12) {
                	cardTitle = "Queen";
                	cardValue = 10;
                }
                else if (j == 13) {
                	cardTitle = "King";
                	cardValue = 10;
                }
                cards.add(new Card(currentSuit, cardTitle, cardValue, cardID));
                cardID++;
            }
        }
    }
    
    /**
     * Returns the ArrayList of all Card objects.
     * @return cards The ArrayList of all cards in the game.
     */
    public ArrayList<Card> getCards()
    {
        return cards;
    }
    

    /**
     * Sets the players of the game: one computer player, 
     * and one human player who is invited to input their name.
     * Adds players to an ArrayList of players.
     */
    public void setPlayers(String name) {
    	Player computer = new Computer("Computer");
    	players.add(computer);
    	ui.showPlayerName(computer);
    	Player player1 = new Human(name);
    	players.add(player1);
		ui.showPlayerName(player1);
    }
    
    /**
     * Returns the ArrayList of players.
     * @return players The ArrayList of players.
     */
    public ArrayList<Player> getPlayers() {
    	return players;
    }
    
    /**
     * Sets whether or not the game is over (true) or still in progress (false).
     * @param gameOver A boolean true or false.
     */
    public void setGameOver(boolean gameOver) {
 	   this.gameOver = gameOver;
    }
    
    
    /**
     * Returns whether the game is over (true) or still in progress (false).
     * @return gameOver A boolean true or false.
     */
    public boolean getGameOver() {
	   return gameOver;
	}
   
    /**
     * Generates a random number between 0 and 51, to select a random card (from deck of 52)
     * to issue to the player. If card was not in the deck (dealt to another player), another 
     * card is chosen. Card is dealt to player through player's setHand() function and
     * its inDeck status is set to false.
     * @param player The player a card is to be dealt to.
     */
    public void dealCard(Player player) {
    	int dealCardID = (int)(Math.random()*52);
		while (getCards().get(dealCardID).getInDeck() == false) {
			dealCardID = (int)(Math.random()*52);
		}
		player.setHand(getCards().get(dealCardID));
		ui.displayImage(dealCardID, player, this);	
		getCards().get(dealCardID).setInDeck(false);
    }
    
    /**
     * Deals the initial cards at the start of the game. For every player in the game, 
     * dealCard() function is called twice to deal two cards to each player.
     */
    public void dealCards() {
    	ui.updateLabelText(ui.centerLabel, "Dealing cards...");
    	for (Player player : players) {
    		for (int j=0; j<2; j++) {
    			dealCard(player);
    		}
    	}
    }
    
    /**
     * Reveals the cards to the players, and calculates total hand value. 
     * If a player has an ace, handleAce() function called to deal with the decision between 1 and 11.
     * Hand is not re-revealed or total re-calculated if a player has already stood.
     */
    public void revealCards() {
    	for (Player player : players) {
    		String playerName = player.getPlayerName();
    		String message;
    		ArrayList<Card> hand = player.getHand();
    		if (player.getStood() == false) {
        		player.setHandTotalValue(0);
    			for (int j= 0; j<hand.size(); j++) {
                	if (hand.get(j).getValue() == 11) {
                		player.setHandHasAce(true);
                	}
                	player.setHandTotalValue(player.getHandTotalValue() + hand.get(j).getValue()); 
                }
    		}	
        	if (player.getHandHasAce() == true  && player.getStood() == false) {
        		handleAce(player);
        	} else {
        		if (player instanceof Computer) {
        			message = ("Total is secret");
        		} else {
        			message = ("Total is " + player.getHandTotalValue());
        		}
            	ui.scoreTotalMessage(message, player);
        	}
    	}
    }
    
    /**
     * If a player has one or multiple aces, determines all possible values
     * of their hand of cards, and if at least one value falls under 21, an 
     * ace Choice function is called to ask them to choose. 
     * Possible values are calculated from the player's handTotalValue - 10
     * (repeated for each ace that the player has in their hand.)
     * If all possible values are over 21, the player is bust automatically.
     * @param player The player who needs to make a choice.
     */
    public void handleAce(Player player) {
    	String playerName = player.getPlayerName();
    	int playerValue = player.getHandTotalValue();
    	ArrayList<Integer> possibleValues = new ArrayList<>();
    	possibleValues.add(playerValue);
    	int aceCount = aceCount(player.getHand());
    	for (int i = 1; i < 5; i++) {
    		if (aceCount >= i) {
    			possibleValues.add(playerValue-(i*10));
    		}
    	}
    	player.setPossibleValues(possibleValues);
    	String message = ("Total is any of: " + possibleValues.toString());
    	ui.scoreTotalMessage(message, player);
    	if ((player.getHandTotalValue()-(aceCount*10)) <= 21){
	    	if(player instanceof Human) {
	    		// ace choice for human
	    		humanAceChoice(player, possibleValues);
	    		message = ("Total is now " + player.getHandTotalValue());
	    	}
	    	else {
	    		// ace choice on computer class
	    		((Computer) player).aceChoice(possibleValues, aceCount);
	    		message = ("Total is secret");
	    	}
        	ui.scoreTotalMessage(message, player);
    	} else {
    		String newMessage = (playerName + " is bust and has lost the game.");
    		ui.updateLabelText(ui.centerLabel, newMessage);
			player.setStood(true);
			gameOver("loser", player);
			message = ("Total was any of: " + possibleValues.toString());
        	ui.scoreTotalMessage(message, player);   	
    	}
		  		
    }
    
	/**
	 * Asks for the human player's choice when they have an Ace, or several aces.
	 * The player is given the list of all possible values for their hand of cards.
	 * The player's handTotalValue is set to their choice.
	 * @param possibleValues The list of possible totals for their hand.
	 */
	public void humanAceChoice(Player player, ArrayList<Integer> possibleValues) {	
		int userChoice = ui.showAceChoiceOptions(possibleValues);
		player.setHandTotalValue(userChoice);
	} 
    
    /**
     * Calculates the number of aces in a players hand.
     * @param hand The hand of cards requiring its aces counted.
     * @return aceCount The number of aces in the hand.
     */
    public int aceCount(ArrayList<Card> hand) {
    	int aceCount = 0;
    	for (Card card : hand) {
    		if(card.getValue() == 11) {
    			aceCount++;
    		}
    	}
    	return aceCount;
    }
    
    /**
     * Checks to see if any of the players have bust, stood, or won.
     * If player has over 21, they are bust, and game is over.
     * If all players have stood:
     * - gameDraw() is used to check if they stood with the same value.
     * - If there is a 'draw', and all players have 21, it will be a draw unless a single
     *   player has blackjack (11+10), in which case, the player with blackjack wins.
     * - If there is a draw at any value under 21, the game is a draw.
     * - If a single player has 21, and the others do not, that player wins.
     * - Otherwise, getHighestStood() gets the player who has stood with the highest value under 21, who wins.
     * If nobody has yet won or gone bust, the hitOrStand() function is called for the player.
     */  
    public void checkBustStood() {
    	for(Player player : players) {
    		String playerName = player.getPlayerName();
    		String newMessage;
    		int playerValue = player.getHandTotalValue();
    		if (playerValue > 21) { //check for bust
    			newMessage = (playerName + " is bust and has lost the game.");
        		ui.updateLabelText(ui.centerLabel, newMessage);
    			player.setStood(true);
    			gameOver("loser", player);
    			break;
    		} else if (allPlayersStood() == true) {
    			boolean draw = gameDraw();    			
    			if (draw == true) {
    				if (playerValue == 21) {
    					isBlackjack();
    					if(allPlayersSameHandSize() == false && player.getHasBlackjack() == true) {
                			gameOver("winner", player);
                			newMessage = ("Congratulations " + playerName + ", who has won with blackjack!");
    		        		ui.updateLabelText(ui.centerLabel, newMessage);
                			break;
    					} else {
    						newMessage = ("Game is a draw.");
    		        		ui.updateLabelText(ui.centerLabel, newMessage);
    		        		gameOver("draw", player);
                			break;
    					}
    				} else {
						newMessage = ("Game is a draw.");
		        		ui.updateLabelText(ui.centerLabel, newMessage);
		        		gameOver("draw", player);
            			break;
    				}
    			} else {
    				newMessage = ("Congratulations " + getHighestStood().getPlayerName() + ", you have won!");
	        		ui.updateLabelText(ui.centerLabel, newMessage);
	        		gameOver("winner", getHighestStood());
        			break;
    			}
    		} else if(player.getStood() == false){
    			hitOrStand(player);
    		}	
    	}
    }
    
    
    /**
     * Checks to see if all players have stood.
     * @return Boolean true (if all players have stood) or false (if not all have stood).
     */
    public boolean allPlayersStood() {
    	for (Player player : players) {
    		if(!player.getStood()) {
    			return false;
    		}
    	}
    	return true;
    }

    /**
     * Finds the player who has chosen to 'stand' with the largest value under 21.
     * @return highestStood The player who has stood with the highest value under 21.
     */
    public Player getHighestStood() {
    	Player highestStood = players.get(0);
    	int currentHighestStoodValue = 0;
    	for (Player player: players) {
    		int playerValue = player.getHandTotalValue();
    		if (player.getStood() == true && playerValue <= 21 && playerValue > currentHighestStoodValue) {
    			currentHighestStoodValue = playerValue;
    			highestStood = player;
    		}
    	}
    	return highestStood;
    }

    /**
     * Asks players, if they are a human player, if they want to 'hit' (and draw another card)
     * or 'stand' with the cards they have. 
     * If the player is a computer, it calls a hitStandChoice() function for the computer to make its choice.
     * It is then announced for all players if they chose to 'stand' or draw another card.
     * @param player The player who is to make a choice between 'hit' and 'stand'
     */
    public void hitOrStand(Player player) {
    	String playerName = player.getPlayerName();
		String choice = "";
    	if(player instanceof Human) {
			while (!choice.equals("hit") && !choice.equals("stand")) {
				choice = ui.showHitStandChoiceOptions().toLowerCase();
    		}
    	} else {
    		ui.computerStatusMessage("The computer is making a choice.", player);
    		choice = ((Computer) player).hitStandChoice(player.getHandTotalValue());
    		try {
    			Thread.sleep(2*1000);
    		} catch (InterruptedException ie) {
    			Thread.currentThread().interrupt();
    		}
    	}
		if (choice.equals("stand")) {
			ui.computerStatusMessage(playerName + " has chosen to stand with their current cards.", player);
			player.setStood(true);
			ui.stoodMessage(player, "Standing");
		}
		if (choice.equals("hit")) {
			ui.computerStatusMessage(playerName + " has chosen to draw another card.", player);
			dealAnother(player);
		}   		   		
    }
         
    /**
     * Calls dealCard() function for the player.
     * @param player The player who is needing another card.
     */
    public void dealAnother(Player player) {
    	dealCard(player);
    }
    
   /**
    * Function to check if game is a draw, which is called if all players
    * have chosen to 'stand'. Compares the total hand value of all players hands
    * to see if they are the same.
    * @return Boolean true or false. Whether there is a draw (true) or not (false).
    */
    public boolean gameDraw() {
    	int firstPlayerVal = players.get(0).getHandTotalValue();
    	for (Player player : players) {
    		if (player.getHandTotalValue() != firstPlayerVal) {
    			return false;
    		}
    	}
    	return true;
    }
    
    
    /**
     * Function to check if a score of 21 is blackjack (a 10 and an ace).
     * Called when there is a draw and player has a score of 21.
     */

    public void isBlackjack(){
    	for (Player player : players) {
    		if (player.getHand().size() == 2) {
    			//player has blackjack
    			player.setHasBlackjack(true);
    		} else {
    			//player has not got blackjack
    			player.setHasBlackjack(false);
    		}
    	}
    }
    
    /**
     * Function to check whether all players have the same number of cards
     * in their hands. Called when multiple players have a score of 21 to
     * check if they have the same number of cards, as a 21 from two cards 
     * will always win over any other 21.
     * @return
     */
    public boolean allPlayersSameHandSize() {
    	int firstPlayerHandSize = players.get(0).getHand().size();
    	for (Player player : players) {
    		if (player.getHand().size() != firstPlayerHandSize) {
    			return false;
    		}
    	}
    	return true;
    }
    
    /**
     * Function called to end the game when a player has won or gone bust.
     * Sets gameOver status to true.
     * Calls winnerOrLoser() function to identify winner and loser.
     * Reveals computer's cards and score.
     * Calls the newGameButton to appear on the screen.
     * @param winnerOrLoser A String "winner" "loser" of "draw".
     * @param who The player who has ended the game.
     */
    public void gameOver(String winnerOrLoser, Player who) {
    	setGameOver(true);
    	winnerOrLoser(winnerOrLoser, who);
    	Player computer = players.get(0);
    	String message = "";
    	if (computer.getHandHasAce()) {
    		message = ("Total was any of " + computer.getPossibleValues().toString());
    	} else {
    		message = ("Total was " + computer.getHandTotalValue());
    	}
    	ui.scoreTotalMessage(message, players.get(0));
    	ui.computerCardReveal(players.get(0), this);
    	ui.computerStatusMessage("", computer);
    	ui.showNewGameButton();
    }
    
    /**
     * Function called from gameOver() to identify winner and loser.
     * If the game was ended by the winner, the other player becomes the loser.
     * If the game was ended by a loser, the other player will become winner unless they're bust.
     * Labels are updated next to the players names to show this.
     * If the game was a draw, neither loser or winner is set, and labels are updated to show 'Draw'.
     * @param winnerOrLoser A String "winner" "loser" of "draw".
     * @param who The player who has ended the game.
     */
    public void winnerOrLoser(String winnerOrLoser, Player who) {
    	if (winnerOrLoser == "winner") {
	    	if(who == players.get(0)) { // if computer is winner
	    		setWinner(players.get(0)); // winner is computer
	    		setLoser(players.get(1)); // loser is human player
	    	} else { // the computer is not winner
	    		setLoser(players.get(0)); // loser is computer
	    		setWinner(players.get(1)); // player is winner
	    	}
	    ui.stoodMessage(getWinner(), "(Winner)");
	    ui.stoodMessage(getLoser(), "(Loser)");
    	} else if (winnerOrLoser == "loser") {   		
    		if(who == players.get(0)) { // if computer is loser
	    		setLoser(players.get(0)); // loser is computer
	    		if (players.get(1).getHandTotalValue() <= 21) { 
	    			setWinner(players.get(1)); // winner is human player, if not also bust
	    			ui.stoodMessage(getWinner(), "(Winner)");
	    		} else {
	    			ui.stoodMessage(players.get(1), "(Also Loser)"); // if human is bust too
	    		}
	    	} else { // if computer is not loser
	    		setLoser(players.get(1)); // loser is human player
	    		if (players.get(0).getHandTotalValue() <= 21) {
	    			setWinner(players.get(0)); // winner is computer, if not also bust
	    			ui.stoodMessage(getWinner(), "(Winner)");
	    		} else {
	    			ui.stoodMessage(players.get(0), "(Also Loser)"); // if computer is bust too
	    		}
	    	}		
    	    ui.stoodMessage(getLoser(), "(Loser)");
    	} else if (winnerOrLoser == "draw") {
    		// game is a draw
    		for (Player player : players) {
        	    ui.stoodMessage(player, "(Draw)");
    		}
    	}	
    }
}
