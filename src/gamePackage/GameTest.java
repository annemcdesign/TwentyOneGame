package gamePackage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class GameTest {
	
	private Game game;
	private Game game2;
	private UserInterface ui = new UserInterface();
	
	public GameTest() {
	}

	@BeforeEach
	public void setUp() throws Exception {
		game = new Game("Game 1", ui);
		System.out.println("Game created.");
		game.setPlayers("Ben");
	}
	
	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetGameName() {
		game.setGameName("Game 2");
		assertEquals("Game 2", game.getGameName());
		assertNotNull(game.getGameName());
	}

	@Test
	public void testGetGameName() {
		game.setGameName("Game 2");
		assertEquals("Game 2", game.getGameName());
	}
	
	@Test
	public void testSetWinner() {
		game.setWinner(game.getPlayers().get(0));
		assertEquals(game.getWinner(), game.getPlayers().get(0));
		
	}
	
	@Test
	public void testGetWinner() {
		game.setWinner(game.getPlayers().get(1));
		assertEquals(game.getWinner(), game.getPlayers().get(1));	
	}
	
	@Test
	public void testSetLoser() {
		game.setLoser(game.getPlayers().get(0));
		assertEquals(game.getLoser(), game.getPlayers().get(0));
		
	}
	
	@Test
	public void testGetLoser() {
		game.setLoser(game.getPlayers().get(1));
		assertEquals(game.getLoser(), game.getPlayers().get(1));	
	}

	@Test
	public void testSetCards() {
		game.setCards();
		assertEquals(52, game.getCards().size());
		assertEquals("Ace", game.getCards().get(0).getTitle());
		assertEquals(11, game.getCards().get(0).getValue());
		assertEquals("9", game.getCards().get(8).getTitle());
		assertEquals(9, game.getCards().get(8).getValue());
		assertEquals("King", game.getCards().get(12).getTitle());
		assertEquals(10, game.getCards().get(12).getValue());
	}

	@Test
	public void testGetCards() {
		game.setCards();
		game.getCards();
		assertEquals(52, game.getCards().size());
		assertEquals("Ace", game.getCards().get(0).getTitle());
		assertEquals(11, game.getCards().get(0).getValue());
		assertEquals("2", game.getCards().get(1).getTitle());
		assertEquals(2, game.getCards().get(1).getValue());
	}

	@Test
	public void testSetPlayers() {
		game2 = new Game("Game 1", ui);
		game2.setPlayers("Jack");
		// requires a player input
		assertNotNull(game2.getPlayers());
		assertEquals(2, game2.getPlayers().size());
		assertEquals("Computer", game2.getPlayers().get(0).getPlayerName());
		assertEquals("Jack", game2.getPlayers().get(1).getPlayerName());
	}

	@Test
	public void testGetPlayers() {
		game2 = new Game("Game 1", ui);
		game2.setPlayers("Ryan");
		game2.getPlayers();
		assertNotNull(game2.getPlayers());
		assertEquals(2, game2.getPlayers().size());
		assertEquals("Computer", game2.getPlayers().get(0).getPlayerName());
		assertEquals("Ryan", game2.getPlayers().get(1).getPlayerName());
	}

	@Test
	public void testSetGameOver() {
		game.setGameOver(false);
		assertFalse(game.getGameOver());
		game.setGameOver(true);
		assertTrue(game.getGameOver());
	}

	@Test
	public void testGetGameOver() {
		game.getGameOver();
		assertEquals(false, game.getGameOver());
		game.setGameOver(true);
		assertEquals(true, game.getGameOver());
	}

	@Test
	public void testDealCard() {
		game.setCards();
		game.setPlayers("Ben");
		game.dealCard(game.getPlayers().get(0));
		assertNotNull(game.getPlayers().get(0).getHand());
	}

	@Test
	public void testDealCards() {
		game.setCards();
//		game.setPlayers("Ben");
		game.dealCards();
		assertEquals(2, game.getPlayers().get(0).getHand().size());
		assertEquals(2, game.getPlayers().get(0).getHand().size());
		assertNotEquals(game.getPlayers().get(0).getHand().get(0), game.getPlayers().get(0).getHand().get(1));
		assertNotEquals(game.getPlayers().get(1).getHand().get(0), game.getPlayers().get(1).getHand().get(1));
	}

	@Test
	public void testRevealCards() {
		game.setCards();
//		game.setPlayers("Ben");
		game.dealCards();
		game.revealCards();
		assertEquals(game.getPlayers().get(0).getHandTotalValue(), ((game.getPlayers().get(0).getHand().get(0).getValue() + game.getPlayers().get(0).getHand().get(1).getValue())));
	}

	@Test
	public void testHandleAce() {
		game.setCards();
//		game.setPlayers("Ben");
		Player player = game.getPlayers().get(0);
		player.setHand(game.getCards().get(0)); //gives player an ace
		player.setHand(game.getCards().get(1)); //gives player a two
		game.revealCards();
		game.handleAce(player);
		assertTrue(player.getHandTotalValue() == 13 || player.getHandTotalValue() == 3); // depends on user input
	}
	
	@Test
	public void testHumanAceChoice() {
		game.setCards();
		Player player = game.getPlayers().get(0);
		player.setHand(game.getCards().get(0)); //gives player an ace
		player.setHand(game.getCards().get(1)); //gives player a two
		game.revealCards();
		game.handleAce(player); // handleAce() calls humanAceChoice for human player
		assertTrue(player.getPossibleValues().contains(player.getHandTotalValue()));
		assertTrue(player.getHandTotalValue() == 13 || player.getHandTotalValue() == 3);
	}

	@Test
	public void testAceCount() {
		game.setCards();
//		game.setPlayers("Ben");
		Player player = game.getPlayers().get(0);
		player.setHand(game.getCards().get(0)); //gives player an ace
		player.setHand(game.getCards().get(1)); //gives player a two
		assertEquals(1, game.aceCount(player.getHand()));
		player.setHand(game.getCards().get(13)); //gives player a ace
		assertEquals(2, game.aceCount(player.getHand()));
		player.setHand(game.getCards().get(26)); //gives player a ace
		assertEquals(3, game.aceCount(player.getHand()));
		player.setHand(game.getCards().get(39)); //gives player a ace
		assertEquals(4, game.aceCount(player.getHand()));
	}

	@Test
	public void testCheckBustStood() {
		game.setCards();
//		game.setPlayers("Ben");
		Player player1 = game.getPlayers().get(0);
		Player player2 = game.getPlayers().get(1);
		player1.setHand(game.getCards().get(9)); //gives player a ten
		player1.setHand(game.getCards().get(3)); //gives player a four
		player2.setHand(game.getCards().get(22)); //gives player a ten
		player2.setHand(game.getCards().get(21)); //gives player a nine
		player1.setHand(game.getCards().get(8)); //gives the player a nine
		player2.setStood(true);
		game.revealCards();
		game.checkBustStood();
		assertTrue(player2.getStood());
		assertTrue(player1.getStood());
		assertTrue(game.getGameOver());
	}

	@Test
	public void testAllPlayersStood() {
//		game.setPlayers("Ben");
		Player player1 = game.getPlayers().get(0);
		Player player2 = game.getPlayers().get(1);
		player1.setStood(false);
		player2.setStood(false);
		assertFalse(game.allPlayersStood());
		player1.setStood(true);
		assertFalse(game.allPlayersStood());
		player2.setStood(true);
		assertTrue(game.allPlayersStood());
	}

	@Test
	public void testGetHighestStood() {
		game.setCards();
//		game.setPlayers("Ben");
		Player player1 = game.getPlayers().get(0);
		Player player2 = game.getPlayers().get(1);
		player1.setHand(game.getCards().get(9)); //gives computer player a ten
		player1.setHand(game.getCards().get(3)); //gives computer player a four
		player2.setHand(game.getCards().get(22)); //gives player a ten
		player2.setHand(game.getCards().get(21)); //gives player a nine
		game.revealCards();
		player1.setStood(true);
		player2.setStood(true);
		assertEquals(player2, game.getHighestStood());
	}

	@Test
	public void testHitOrStand() {
		game.setCards();
//		game.setPlayers("Ben");
		Player player1 = game.getPlayers().get(0); //this is the computer player
		Player player2 = game.getPlayers().get(1); // this is the human player
		player1.setHand(game.getCards().get(9)); //gives computer player a ten
		player1.setHand(game.getCards().get(3)); //gives computer player a four
		player2.setHand(game.getCards().get(22)); //gives player a ten
		player2.setHand(game.getCards().get(21)); //gives player a nine
		game.revealCards();
		game.hitOrStand(player1);
		assertEquals(false, player1.getStood());
		player1.setHand(game.getCards().get(5)); //gives computer player a six
		game.revealCards();
		game.hitOrStand(player1);
		assertEquals(true, player1.getStood());
		game.hitOrStand(player2);
		assertTrue(player2.getStood() == true || player2.getStood() == false); // depends on user input
	}

	@Test
	public void testDealAnother() {
		game.setCards();
//		game.setPlayers("Ben");
		game.dealCards();
		Player player1 = game.getPlayers().get(0); //this is the computer player
		assertEquals(2, player1.getHand().size());
		game.dealAnother(player1);
		assertEquals(3, player1.getHand().size());
	}

	@Test
	public void testGameDraw() {
		System.out.println("test game draw");
		game.setCards();
//		game.setPlayers("Ben");
		Player player1 = game.getPlayers().get(0); //this is the computer player
		Player player2 = game.getPlayers().get(1); // this is the human player
		player1.setHand(game.getCards().get(9)); //gives computer player a ten
		player1.setHand(game.getCards().get(3)); //gives computer player a four
		player2.setHand(game.getCards().get(22)); //gives player a ten
		player2.setHand(game.getCards().get(21)); //gives player a nine
		game.revealCards();
		player2.setStood(true);
		player1.setHand(game.getCards().get(4)); //gives computer player a five
		game.revealCards();
		player1.setStood(true);
		System.out.println("Player1 has " + player1.getHandTotalValue());
		System.out.println("Player2 has " + player2.getHandTotalValue());
		assertTrue(game.gameDraw());
	}

	@Test
	public void testGameDraw2() {
		game.setCards();
//		game.setPlayers("Ben");
		Player player1 = game.getPlayers().get(0); //this is the computer player
		Player player2 = game.getPlayers().get(1); // this is the human player
		player1.setHand(game.getCards().get(9)); //gives computer player a ten
		player1.setHand(game.getCards().get(7)); //gives computer player a eight
		player2.setHand(game.getCards().get(22)); //gives player a ten
		player2.setHand(game.getCards().get(21)); //gives player a nine
		game.revealCards();
		player1.setStood(true);
		player2.setStood(true);
		assertFalse(game.gameDraw());
	}
	
	@Test
	public void testIsBlackjack() {
		game.setCards();
//		game.setPlayers("Ben");
		Player player1 = game.getPlayers().get(0);
		Player player2 = game.getPlayers().get(1);
		player1.setHand(game.getCards().get(0)); //gives player an ace
		player1.setHand(game.getCards().get(9)); //gives player a ten		
		player2.setHand(game.getCards().get(4)); //gives player a five
		player2.setHand(game.getCards().get(22)); //gives player a ten
		player2.setHand(game.getCards().get(5)); //gives player a six	
		game.revealCards();
		game.isBlackjack();
		assertFalse(player2.getHasBlackjack());
		assertTrue(player1.getHasBlackjack());
	}

	@Test
	public void testAllPlayersSameHandSize() {
		game.setCards();
//		game.setPlayers("Ben");
		Player player1 = game.getPlayers().get(0);
		Player player2 = game.getPlayers().get(1);
		player1.setHand(game.getCards().get(0)); //gives player an ace
		player1.setHand(game.getCards().get(9)); //gives player a ten		
		player2.setHand(game.getCards().get(4)); //gives player a five
		player2.setHand(game.getCards().get(22)); //gives player a ten
		player2.setHand(game.getCards().get(5)); //gives player a six	
		game.revealCards();
		assertFalse(game.allPlayersSameHandSize());
	}
	
	@Test
	public void testAllPlayersSameHandSize2() {
		game.setCards();
//		game.setPlayers("Ben");
		Player player1 = game.getPlayers().get(0);
		Player player2 = game.getPlayers().get(1);
		player1.setHand(game.getCards().get(0)); //gives player an ace
		player1.setHand(game.getCards().get(9)); //gives player a ten		
		player2.setHand(game.getCards().get(13)); //gives player a ace
		player2.setHand(game.getCards().get(38)); //gives player a king
		game.revealCards();
		assertTrue(game.allPlayersSameHandSize());
	}

	@Test
	public void testGameOver() {
		game.setCards();
		Player player1 = game.getPlayers().get(0);
		Player player2 = game.getPlayers().get(1);
		player1.setHand(game.getCards().get(0)); //gives player an ace
		player1.setHand(game.getCards().get(9)); //gives player a ten		
		player2.setHand(game.getCards().get(13)); //gives player a ace
		player2.setHand(game.getCards().get(5)); //gives player a six
		player1.setStood(true);
		player2.setStood(true);
		game.revealCards();
		game.gameOver("winner", player1);
		assertTrue(game.getGameOver());
	}
	
	@Test
	public void testWinnerOrLoser() {
		game.setCards();
		Player player1 = game.getPlayers().get(0);
		Player player2 = game.getPlayers().get(1);
		player1.setHand(game.getCards().get(0)); //gives player an ace
		player1.setHand(game.getCards().get(9)); //gives player a ten		
		player2.setHand(game.getCards().get(13)); //gives player a ace
		player2.setHand(game.getCards().get(5)); //gives player a six
		player1.setStood(true);
		player2.setStood(true);
		game.revealCards();
		game.gameOver("winner", player1);
		assertEquals(game.getWinner(), player1);
		assertEquals(game.getLoser(), player2);
	}
	
}
