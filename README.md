# Hike Through - A Game of Walks #

## Background ##
*Update 11/27/17* The game of Hike Through is one in which each play-through is a careful consideration of current "HikeCoin" amount and the number of moves it will take to traverse the hexagonal game board from start to home. Each turn will consist of one move and a game lasts as long as the player has moves or HikeCoin remaining, whichever is exhausted first. The challenge is in determining the number of HikeCoin that will be used and the number of moves needed to reach your goal. Map tiles are randomly generated and no two game maps have the same layout of tiles. I think a game of this type, and with the inclusion of some of the stretch goals, would be beneficial to both reasoning and physical health through walking.

I have always been fascinated with game theory and how to incite interactions in the physical world (very similar to Niantic's game *Ingress*). My main goal was to create a situation where a player is **NOT** encouraged to walk and game at the same time but rather for these actions to be done separately. The distinction is that both systems of play feed in to the overall concept of the game.

I believe that by encouraging players to walk in order to further their progress in the game, that this promotes both critical thinking skills, health from physical exertion, and knowledge of the built environment.

### Original Idea ###
*Update 11/24/17* **This is the original idea for the game that may eventually get implemented in future builds of the app.** *The act of walking and exploration is a central theme to _Hike Through_. Through the act of earning "travel currency" by actual walking in the real world, I intend to make the exploration od the game world analogous to the exploration of the real world. Using the Google Fit API to pull information about recent walks to convert in to in-game movement currency. This element of the game is now a stretch goal. While the game still will make the user consider every move on the game board and carefully plan out a route to judicially utilize their "HikeCoin", real-world tracking will now be a stretch goal for future implementation.*

*The game itself is an experiment in gamefying the act of going on a walk but with the intention to not have to look down at your smartphone screen while you're walking, so that you're free to enjoy your walk. The distance you cover by walking is logged and tracked by Google Fit over the history of gameplay and stored in _Hike Through_. The statistics can be seen on a user profile as a credential of their expertise and time in the game.*

*The user starts each game session with only one life (Rogue-like) and can continue to play and explore the game through 7 levels with randomly placed hexagon land-tiles. Death starts you over at level 1.*

*You can collect items that can augment both the conversion rate of actual distance into travel currency and lower the cost to pass through or land on moutain/alpine tiles. In addition there are equipment items that all have 24hr timers before they expire and are removed from your inventory, that can are expended when presented with a random event. These random events are dependent upon tile type. The type of equipment you can acquire is dependent upon the tile type you choose to land on.*

## Current State ##
The current state of the game is that I have a method that randomly generates a group of hexagon-shaped map tiles on to a hexagon-shaped game board consisting of six rings. The navigational framework is there for storing created maps but I'm struggling with wiring the data into a GridView. I have buttons laid out for begining a game, resetting and redoing a move. I also have text fields for number of HikeCoin and Moves remaining.

The game currently is not a game a game but an *interest* app that provides randomly generated maps. The menu layout is ready for addition of stretch goal features should they be included in the future. This has been an extremely trying project and I feel like I bit off way more than I could chew as a first Android project but I am proud of the visual elements of the app and hope that I can accomplish my initial vision at some point.

### MVR Hit List ###

1. Implement Dijkstras algorithm and create draw method to allow players to navigate the game board.
2. Wire movement to buttons and create method to account for HikeCoin amount and current Moves remaining. 
3. Store current game map so that it doesn't refresh when you navigate away from the game tab.
4. Wire in database storage of completed game maps.

### Android Versions Tested ###

- Nexus 5x, Android ver. 5.1.1, API 22
- Pixel XL, Android ver. 7.1, API 22

### Aesthetic Upgrades ###

1. Uniform layout of game butons and text.
2. Change of font to roboto.
3.

### Stretch Goals ###

1. Implement social media sharing of completed games/maps
2. Pivot the game from being a puzzler to a linear style game with 7 game level maps.
3. Implement Google Fit interface and convert completed walks into in-game HikeCoin.
4. Implement random events on specific land tiles.


## Acknowledgements & References ##

Many thanks to Amit Patel at [Red Blob Games](https://www.redblobgames.com// "Red Blob Games") for creating an incredible repository of information on the math behind and the designing of different map types, most specifically hexagon maps. 

Big Thanks to [Nick Bennett](https://github.com/nick-bennett// "Nick Bennett") for helping make sense of the math for randomly generating a hexagon shape of hexagons game map and [Chris Hughes](https://github.com/cfhughes// "Chris Hughes") for being able to field questions about Android development.

### Javadoc ###

HikeThrough Javadoc


