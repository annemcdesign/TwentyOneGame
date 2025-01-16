package gamePackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardTest {
	
	private Game game;
	private UserInterface ui;
	private Card card1;

	@BeforeEach
	void setUp() throws Exception {
		game = new Game("Game 1", ui);
		card1 = new Card("Hearts", "Ace", 11, 0);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSetSuit() {
		card1.setSuit("Hearts");
		assertEquals("Hearts", card1.getSuit());
	}

	@Test
	void testGetSuit() {
		card1.setSuit("Diamonds");
		assertEquals("Diamonds", card1.getSuit());
	}

	@Test
	void testSetValue() {
		card1.setValue(11);
		assertEquals(11, card1.getValue());
	}

	@Test
	void testGetValue() {
		card1.setValue(9);
		assertEquals(9, card1.getValue());
	}

	@Test
	void testSetTitle() {
		card1.setTitle("King");
		assertEquals("King", card1.getTitle());
	}

	@Test
	void testGetTitle() {
		card1.setTitle("Queen");
		assertEquals("Queen", card1.getTitle());
	}

	@Test
	void testSetCardID() {
		card1.setCardID(0);
		assertEquals(0, card1.getCardID());
	}

	@Test
	void testGetCardID() {
		card1.setCardID(12);
		assertEquals(12, card1.getCardID());
	}

	@Test
	void testSetInDeck() {
		card1.setInDeck(false);
		assertFalse(card1.getInDeck());
	}

	@Test
	void testGetInDeck() {
		card1.setInDeck(true);
		assertTrue(card1.getInDeck());
	}

}
