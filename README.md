# Hike Through - A Game of Walks #

## Background ##
*Update 11/24/17* The game of Hike Through is one in which each play-through is a careful consideration of current "HikeCoin" amount and the number of moves it will take to traverse the hexagonal game board from start to home. Each turn will consist of one move and a game lasts as long as the player has moves or HikeCoin left, whichever is exhausted first. The challenge is in determining the number of HikeCoin that will be used and the number of moves needed to reach your goal. Map tiles are randomly generated and no two game maps have the same layout of tiles.

*Update 11/24/17* **This is the original idea for the game that may eventually get implemented in future builds of the app.** *The act of walking and exploration is a central theme to _Hike Through_. Through the act of earning "travel currency" by actual walking in the real world, I intend to make the exploration od the game world analogous to the exploration of the real world. Using the Google Fit API to pull information about recent walks to convert in to in-game movement currency. This element of the game is now a stretch goal. While the game still will make the user consider every move on the game board and carefully plan out a route to judicially utilize their "HikeCoin", real-world tracking will now be a stretch goal for future implementation.*

*The game itself is an experiment in gamefying the act of going on a walk but with the intention to not have to look down at your smartphone screen while you're walking, so that you're free to enjoy your walk. The distance you cover by walking is logged and tracked by Google Fit over the history of gameplay and stored in _Hike Through_. The statistics can be seen on a user profile as a credential of their expertise and time in the game.*

*The user starts each game session with only one life (Rogue-like) and can continue to play and explore the game through 7 levels with randomly placed hexagon land-tiles. Death starts you over at level 1.*

*You can collect items that can augment both the conversion rate of actual distance into travel currency and lower the cost to pass through or land on moutain/alpine tiles. In addition there are equipment items that all have 24hr timers before they expire and are removed from your inventory, that can are expended when presented with a random event. These random events are dependent upon tile type. The type of equipment you can acquire is dependent upon the tile type you choose to land on.*

## Acknowledgements & References ##
Many thanks to Amit Patel at [Red Blob Games](https://www.redblobgames.com// "Red Blob Games") for creating an incredible repository of information on the math behind and the designing of different map types, most specifically hexagon maps. 

Big Thanks to [Nick Bennett](https://github.com/nick-bennett// "Nick Bennett") and [Chris Hughes](https://github.com/cfhughes// "Chris Hughes") for their help on this project.

## Goals ##
1. ~~To get the 7 hexagon shaped maps to draw correctly on screen and then randomly populate all the land tile types as each level is begun.~~ 
2. ~~To wire in the the usage of travel currency and assign each tile type a cost, and store in database.~~
3. Set up and store completed game maps as shareable image files.
4. Create game/puzzle element and show visible line with the path across the map.
5. Pretty much make something playable.

### Stretch Goals ###
1. Implement Google Fit interface and convert completed walks into in-game HikeCoin.
2. Implement random events on specific land tiles.
3. Pivot the game from being a puzzler to a linear style game with 7 game level maps.
4. Implement social media sharing of completed games/maps
5. To wire in the avatar and implement a pathfinding function using Diikstra's algorithm.
6. Pretty much make something closer to the initial idea.
