package gamePackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HumanTest {
	
	private Game game;
	private UserInterface ui = new UserInterface();

	@BeforeEach
	void setUp() throws Exception {
		game = new Game("Game 1", ui);
		game.setCards();
		game.setPlayers("Edward");	
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAceChoice() {
		Player player = game.getPlayers().get(1);
		player.setHand(game.getCards().get(0)); //gives player an ace
		player.setHand(game.getCards().get(1)); //gives player a two
		game.revealCards();
		assertTrue(player.getHandTotalValue() == 13 || player.getHandTotalValue() == 3);
	}

}
