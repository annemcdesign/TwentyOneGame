package gamePackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {
	
	private Game game;
	private UserInterface ui = new UserInterface();

	@BeforeEach
	void setUp() throws Exception {
		game = new Game("Game 1", ui);
		game.setCards();
		game.setPlayers("Anne");
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSetPlayerName() {
		Player player = game.getPlayers().get(1);
		player.setPlayerName("Anne");
		assertEquals("Anne", player.getPlayerName());
	}

	@Test
	void testGetPlayerName() {
		Player player = game.getPlayers().get(1);
		player.setPlayerName("Jeff");
		assertEquals("Jeff", player.getPlayerName());
	}

	@Test
	void testSetHand() {
		Player player = game.getPlayers().get(1);
		player.setHand(game.getCards().get(0)); // deals player an ace
		player.setHand(game.getCards().get(33)); // deals player an eight
		assertEquals(2, player.getHand().size());
		player.setHand(game.getCards().get(27)); // deals player a two
		assertEquals(3, player.getHand().size());
	}

	@Test
	void testGetHand() {
		Player player = game.getPlayers().get(1);
		player.setHand(game.getCards().get(14)); // deals player a two
		player.setHand(game.getCards().get(33)); // deals player an eight
		assertEquals(2, player.getHand().size());
		player.setHand(game.getCards().get(0)); // deals player an ace
		player.setHand(game.getCards().get(51)); // deals player a king
		assertEquals(4, player.getHand().size());
	}

	@Test
	void testSetHandTotalValue() {
		Player player = game.getPlayers().get(0);
		player.setHandTotalValue(17);
		assertEquals(17, player.getHandTotalValue());
	}

	@Test
	void testGetHandTotalValue() {
		Player player = game.getPlayers().get(0);
		player.setHandTotalValue(12);
		assertEquals(12, player.getHandTotalValue());
	}

	@Test
	void testSetStood() {
		Player player = game.getPlayers().get(0);
		player.setStood(true);
		assertTrue(player.getStood());
	}

	@Test
	void testGetStood() {
		Player player = game.getPlayers().get(0);
		player.setStood(true);
		assertTrue(player.getStood());
	}

	@Test
	void testSetHandHasAce() {
		Player player = game.getPlayers().get(0);
		player.setHandHasAce(true);
		assertTrue(player.getHandHasAce());
	}

	@Test
	void testGetHandHasAce() {
		Player player = game.getPlayers().get(0);
		player.setHandHasAce(true);
		assertTrue(player.getHandHasAce());
	}

	@Test
	void testSetHasBlackjack() {
		Player player = game.getPlayers().get(0);
		player.setHasBlackjack(true);
		assertTrue(player.getHasBlackjack());
	}

	@Test
	void testGetHasBlackjack() {
		Player player = game.getPlayers().get(0);
		player.setHasBlackjack(true);
		assertTrue(player.getHasBlackjack());
	}

	@Test
	void testSetPossibleValues() {
		Player player = game.getPlayers().get(1);
		player.setHand(game.getCards().get(13)); // deals player an ace
		player.setHand(game.getCards().get(0)); // deals player an ace
		player.setHand(game.getCards().get(2)); // deals player a three
		game.revealCards();
		assertTrue(player.getPossibleValues().contains(5));
		assertTrue(player.getPossibleValues().contains(15));
		assertTrue(player.getPossibleValues().contains(25));
	}
	
	@Test
	void testGetPossibleValues() {
		Player player = game.getPlayers().get(1);
		player.setHand(game.getCards().get(13)); // deals player an ace
		player.setHand(game.getCards().get(3)); // deals player an four
		player.setHand(game.getCards().get(51)); // deals player a king
		game.revealCards();
		assertTrue(player.getPossibleValues().contains(25));
		assertTrue(player.getPossibleValues().contains(15));
	}
}
