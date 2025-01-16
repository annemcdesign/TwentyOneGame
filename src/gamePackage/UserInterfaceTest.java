package gamePackage;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserInterfaceTest {
	
	private UserInterface ui = new UserInterface();
	private Game game = new Game("Game 1", ui);

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testUserInterface() {
		assertNotNull(ui);
	}

	@Test
	void testShowNewGameButton() {
		assertEquals("New Game", ui.newGameButton.getText());
	}

	@Test
	void testCloseUI() {
		ui.closeUI();
		assertFalse(ui.isVisible());
	}

	@Test
	void testCallStartNewGame() {
		ui.callStartNewGame();
		assertNotNull(ui);
		assertNotNull(game);
	}

	@Test
	void testSetBackground() {
		ui.setBackground();
		Color customGreen = new Color(0,100,0);
		assertEquals(ui.getContentPane().getBackground(), customGreen);
	}

	@Test
	void testShowCenterMessage() {
		ui.showCenterMessage("Welcome to the game!");
		assertEquals("Welcome to the game!", ui.centerLabel.getText());
	}

	@Test
	void testUpdateLabelText() {
		ui.showCenterMessage("Test message");
		assertEquals("Test message", ui.centerLabel.getText());
		ui.updateLabelText(ui.centerLabel, "52 Cards in a deck");
		assertEquals("52 Cards in a deck", ui.centerLabel.getText());
	}
	
	@Test
	void testInputField() {
		//Not implemented due to the nature of inputField() involving variable user input.
	}
	
	@Test
	void testShowPlayerName() {
		//Not implemented due to the nature of showPlayerName() outputs.
	}

	@Test
	void testScoreTotalMessage() {
		game.setPlayers("Ryan");
		Player computerPlayer = game.getPlayers().get(0);
		Player humanPlayer = game.getPlayers().get(1);
		ui.scoreTotalMessage("Total is 16", computerPlayer);
		ui.scoreTotalMessage("Total is 21", humanPlayer);
		assertEquals("Total is 16.", ui.computerTotalLabel.getText());
		assertEquals("Total is 21.", ui.humanTotalLabel.getText());
	}

	@Test
	void testComputerTotalMessage() {
		game.setPlayers("Ryan");
		Player computerPlayer = game.getPlayers().get(0);
		ui.computerTotalMessage("Total is 13", computerPlayer);
		assertEquals("Total is 13.", ui.computerTotalLabel.getText());
	}

	@Test
	void testHumanTotalMessage() {
		game.setPlayers("Ryan");
		Player humanPlayer = game.getPlayers().get(1);
		ui.scoreTotalMessage("Total is 25", humanPlayer);
		assertEquals("Total is 25.", ui.humanTotalLabel.getText());
	}

	@Test
	void testComputerStatusMessage() {
		game.setPlayers("Ryan");
		Player computerPlayer = game.getPlayers().get(0);
		ui.computerStatusMessage("Computer has drawn another card.", computerPlayer);
		assertEquals("Computer has drawn another card.", ui.computerStatusLabel.getText());
	}

	@Test
	void testStoodMessage() {
		game.setPlayers("Ryan");
		Player computerPlayer = game.getPlayers().get(0);
		Player humanPlayer = game.getPlayers().get(1);
		computerPlayer.setStood(true);
		humanPlayer.setStood(true);
		ui.stoodMessage(humanPlayer, "(Standing)");
		ui.stoodMessage(computerPlayer, "(Standing)");
		assertEquals("(Standing)", ui.computerStoodLabel.getText());
		assertEquals("(Standing)", ui.humanStoodLabel.getText());
	}

	@Test
	void testComputerStoodMessage() {
		game.setPlayers("Ryan");
		Player computerPlayer = game.getPlayers().get(0);
		computerPlayer.setStood(true);
		ui.computerStoodMessage(computerPlayer, "(Standing)");
		assertEquals("(Standing)", ui.computerStoodLabel.getText());
	}

	@Test
	void testHumanStoodMessage() {
		game.setPlayers("Ryan");
		Player humanPlayer = game.getPlayers().get(1);
		humanPlayer.setStood(true);
		ui.humanStoodMessage(humanPlayer, "(Standing)");
		assertEquals("(Standing)", ui.humanStoodLabel.getText());
	}

	@Test
	void testDisplayImage() {
		// Not implemented due to the nature of displayImage() outputs being graphical.
	}

	@Test
	void testShowAceChoiceOptions() {
		ArrayList<Integer> options = new ArrayList<Integer>(); // requires input choice of 3 or 13
		options.add(13);
		options.add(3);
		assertTrue(ui.showAceChoiceOptions(options) == 13 || ui.showAceChoiceOptions(options) == 3);
	}

	@Test
	void testShowHitStandChoiceOptions() {
		 // requires input choice of "Hit" or "Stand"
		assertTrue(ui.showHitStandChoiceOptions() == "Hit" || ui.showHitStandChoiceOptions() == "Stand");
	}

	@Test
	void testComputerCardReveal() {
		//Not implemented due to the nature of computerCardReveal() outputs being graphical.
	}

}
