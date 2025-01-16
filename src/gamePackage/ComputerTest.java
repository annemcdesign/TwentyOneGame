package gamePackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComputerTest {
	
	private Game game;
	private UserInterface ui = new UserInterface();

	@BeforeEach
	void setUp() throws Exception {
		game = new Game("Game 1", ui);
		game.setCards();
		game.setPlayers("Julie");	
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAceChoice() {
		
		Player player = game.getPlayers().get(0);
		player.setHand(game.getCards().get(0)); //gives player an ace
		player.setHand(game.getCards().get(1)); //gives player a two
		game.revealCards();
		assertEquals(13, player.getHandTotalValue());
	}
	
	@Test
	void testAceChoice2() {
		Player player = game.getPlayers().get(0);
		player.setHand(game.getCards().get(0)); //gives player an ace
		player.setHand(game.getCards().get(4)); //gives player a five
		player.setHand(game.getCards().get(7)); //gives player a eight
		game.revealCards();
		assertEquals(14, player.getHandTotalValue());
	}

	@Test
	void testHitStandChoice() {
		Player player = game.getPlayers().get(0);
		player.setHand(game.getCards().get(4)); //gives player a five
		player.setHand(game.getCards().get(7)); //gives player a eight
		game.revealCards();
		game.hitOrStand(player);
		assertEquals("hit", ((Computer) player).hitStandChoice(player.getHandTotalValue()));
	}
	
	@Test
	void testHitStandChoice2() {
		Player player = game.getPlayers().get(0);
		player.setHand(game.getCards().get(21)); //gives player a nine
		player.setHand(game.getCards().get(7)); //gives player a eight
		game.revealCards();
		game.hitOrStand(player);
		assertEquals("stand", ((Computer) player).hitStandChoice(player.getHandTotalValue()));
	}

}
