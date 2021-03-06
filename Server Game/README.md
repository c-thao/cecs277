# Server Game
## Overview
This is a program which simulates a game of rock, papers, scissors. The server hosts the game, a computer AI, and a socket for a client to connect to. The client hosts the player and a GUI interface for the player and connects to the server to initiate the game. The player clicks on one of the four buttons on the GUI to play a rock, paper, scissor, or quit during their turn of the game. The computer tries to predict the player's next play and picks one of the three options of rock, paper, or scissors before a player's turn. The computer determines the player's next turn base on a string pattern mirroring the patterns the player has played during the game. The computer stores and retrieves the pattern in a hashmap to maintain uniqueness and associate a counter for the number of times the pattern has been played by the player. At the start of each game the computer checks to see if a file of pattern data collected from previous games exists and loads its hashmap with the data. After the game is finished the computer saves the pattern data collected into a file for retrieval for the next game.

## Tools & Technology
Language: Java<br>
Environment: Eclipse<br>
