package edu.cnm.deepdive.irt.hikethrough;

import edu.cnm.deepdive.irt.hikethrough.entities.Level;
import edu.cnm.deepdive.irt.hikethrough.entities.MapTileType;
import java.util.Random;


/**
 * Class for defining number of levels in a game and for placing requisite tiles based on current
 * level player is at. Creates the random composition of the map.
 */
public class Game {

  private static final int NUM_LEVELS = 7;


  private Level[] levels = new Level[NUM_LEVELS];
  private int currentLevel;
  private Random rng = new Random();

  /**
   * Method for determining the number of levels completed and defining start and end map tiles for each level
   */
  public Game() {
    for (int i = 0; i < NUM_LEVELS; i++) {
      levels[i] = new Level(6, rng, MapTileType.START, (i < NUM_LEVELS - 1) ? MapTileType.NEXTLEVEL : MapTileType.HOME);
    }
  }

  /**
   *
   * @return Returns current level
   */
  public Level getLevel() {
    return levels[currentLevel];
  }

}
