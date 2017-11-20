# Hike Through - A Game of Walks #

## Background ##
The act of walking and exploration is a central theme to _Hike Through_. Through the act of earning "travel currency" by actual walking in the real world, I intend to make the exploration od the game world analogous to the exploration of the real world. Using the Google Fit API to pull information about recent walks to convert in to in-game movement currency. 

The game itself is an experiment in gamefying the act of going on a walk but with the intention to not have to look down at your smartphone screen while you're walking, so that you're free to enjoy your walk. The distance you cover by walking is logged and tracked by Google Fit over the history of gameplay and stored in _Hike Through_. The statistics can be seen on a user profile as a credential of their expertise and time in the game.

The user starts each game session with only one life (Rogue-like) and can continue to play and explore the game through 7 levels with randomly placed hexagon land-tiles. Death starts you over at level 1.

You can collect items that can augment both the conversion rate of actual distance into travel currency and lower the cost to pass through or land on moutain/alpine tiles. In addition there are equipment items that all have 24hr timers before they expire and are removed from your inventory, that can are expended when presented with a random event. These random events are dependent upon tile type. The type of equipment you can acquire is dependent upon the tile type you choose to land on.

## References ##
Many thanks to Amit Patel at [Red Blob Games](https://www.redblobgames.com// "Red Blob Games") for creating an incredible repository of information on the math behind and the designing of different map types, most specifically hexagon maps.

## Goals ##
1. To get the 7 hexagon shaped maps to draw correctly on screen and then randomly populate all the land tile types as each level is begun. 
2. To wire in the avatar and implement a pathfinding function using Diikstra's algorithm.
3. To wire in the the usage of travel currency and assign each tile type a cost, and store in database.
4. To create interface with Google Fit to acquire walk information and convert into in-game travel currency.
4. To wire in the random events for each tile type and the requisite equipment solution for random tile type.
5. To set up and store completed game maps as shareable image files.
