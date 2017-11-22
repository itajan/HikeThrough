package edu.cnm.deepdive.irt.hikethrough;

import edu.cnm.deepdive.irt.hikethrough.entities.Level;
import edu.cnm.deepdive.irt.hikethrough.entities.MapTileType;
import java.util.Random;

// TODO - develop method for spatial relationships with regards to dijkstras
public class Game {

  private static final int NUM_LEVELS = 7;

  private Level[] levels = new Level[NUM_LEVELS];
  private int currentLevel;
  private Random rng = new Random();

  public Game() {
    for (int i = 0; i < NUM_LEVELS; i++) {
      levels[i] = new Level(4, rng, MapTileType.START, (i < NUM_LEVELS - 1) ? MapTileType.NEXTLEVEL : MapTileType.HOME);
    }
  }

  public Level getLevel() {
    return levels[currentLevel];
  }

}
