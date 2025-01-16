# TwentyOneGame
This project simulates a game of the card game Blackjack (also known as Twenty-One), made with Java.

# Description

The goal of the game is for a player to have their hand of cards get as close as possible to a value of 21 without going over.
Initially, a player is dealt two cards and told their total value. The player will then decide, based on their total value and 
how close it is to 21, whether they would like to "hit" and draw another card or "stand" with their current cards.
After a card is drawn the player's total is updated and checked for values over 21 which would make the player "bust". 

If a player draws an ace, its value is either 1 or 11 (player's choice). In the case of one ace, they are asked to choose from
the two possible total values of their hand. (In the case of two or more aces, more options are provided.) A player retains the 
option to re-choose the value of their ace(s), if, for example, they had valued it at 11, then drew a card with a large value which
would put them over 21, they can revalue the ace at 1.

If a player goes "bust", they lose, and the game is over. Otherwise, the game will wait until all players have chosen to "stand" with 
their cards before reviewing their scores. 
- If both players have the same total value, which is not 21, the game is a draw.
- If both players have 21, the game is a draw, unless a single player has Blackjack (11+10), in which case the player with Blackjack wins.
- If one player has 21, and the other does not, the player with 21 wins.
- Otherwise, the player who has stood with the highest value under 21 wins.

In this game, the user operates one player, and the other player is a "Computer" player, which makes its own decisions, and keeps its
hand of cards hidden from the other player until the game is over.

# Why Java?

The task description for this project allowed me to use any programming language or program I felt appropriate. With Java being an
object-oriented language, I could easily visualise making the basic structure of this game using Objects and Classes
(with a Game Class, 52 Card Class Objects, a Player Class, etc). By designing it in this way, this allows the same code to be used
to create multiple Objects, and some functions to be called several times throughout a game, resulting in code being efficient and reusable.

Additionally, I felt that out of my experience with different programming languages in the past, that in terms of the logic involved in this
program, my experience with Java was most relevant to the task. Despite not having made a user interface with Java before, between my
experience in Java-based language ‘Processing’ and my previous (non-UI-based) Java experience, I was able to add this feature with
only minimal extra learning.

# User Interface

I developed the program first without the user interface, having all game information displayed and all user inputs entered through the
IDE terminal. This was ‘version one’, which I believe would have satisfied all the task’s requirements, but with having a design background,
a dedication to doing high-quality work, and a willingness to keep learning, I decided to implement a user interface to handle all of the
game inputs and outputs.

The user interface has a green background, similar to a classic card table, and will display, for each player, their cards in a graphical
format. I designed 52 PNG images to represent the 52 cards in the deck, using Affinity Designer, a vector graphics program. I also designed
a card back, which is used to keep the Computer Player’s cards secret while the game is in play. The interface utilises JLabel objects to
display messages, scores and images to the player. Several instances of JOptionPane are used to provide the player with the ability to input
their name, to allow them to choose the value of an ace, and to provide the choice between “Hit” and “Stand”.

The interface also gives the user the option, when a game is over, to start a new game, using a “New Game” JButton that closes the current
game and starts up a new one.

# What's Included

- All Java Project files and resources
- Unit tests
- Javadocs
- A runnable JAR file

# Installation Instructions

-	Project files can be downloaded, opened as a project with a Java IDE such as Eclipse, and run from the Main ( src/gamePackage/Main.java )
  as a Java Application.
-	The Runnable JAR file can be downloaded and run with Java.


