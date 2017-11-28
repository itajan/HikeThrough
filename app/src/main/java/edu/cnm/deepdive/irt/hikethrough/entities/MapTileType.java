package edu.cnm.deepdive.irt.hikethrough.entities;

import android.content.Context;
import android.graphics.drawable.VectorDrawable;
import edu.cnm.deepdive.irt.hikethrough.R;
import java.util.Random;


/**
 * Enumerator class that defines a selection of map tiles with specific weights with respect to their placement on the map
 * and costs to be calculated when Dijkstras Movement class is defined.
 */
public enum MapTileType {

  START(R.drawable.ic_map_start, 0, 0),
  NEXTLEVEL(R.drawable.ic_map_nextlevel, 0, 0),
  HOME(R.drawable.ic_map_home, 0, 0),

  ALPINE(R.drawable.ic_map_alpine, 2, 4),
  MOUNTAIN(R.drawable.ic_map_mountain, 2, 3),
  CAVE(R.drawable.ic_map_cave, 1, 5),
  DESERT(R.drawable.ic_map_desert, 4, 1),
  FOREST(R.drawable.ic_map_forest, 3, 3),
  GRASSLAND(R.drawable.ic_map_grassland, 4, 1),
  RIVERLAND(R.drawable.ic_map_riverland, 3, 3),
  UNDERGROUND(R.drawable.ic_map_underground, 1, 5);

//  Will not be using this tile until I further build out the game.
//  TRADINGPOST(R.drawable.ic_map_tradingpost, 1, 0);

  /**
   * Defines an integer "drawable"
   */
  public final int drawable;
  /**
   * Defines a float "weight"
   */
  public final float weight;
  /**
   * Defines a float "cost"
   */
  public final float cost;


  /**
   *
   * @param drawable an integer of vector drawables
   * @param weight float of weights for each hexagon tile type and how often they draw on the board
   * @param cost Cost of each hexagon tile to move into
   */
  MapTileType (int drawable, float weight, float cost) {
    this.drawable = drawable;
    this.weight = weight;
    this.cost = cost;

  }

  /**
   * Method to assign map tiles randomly based on float "weight"
   * @param rng Random generator
   * @return Returns a map of randomly generated map tiles based on weight.
   */
  public static MapTileType random (Random rng) {
    float totalWeight = 0;
    for (MapTileType type : MapTileType.values()) {
      totalWeight += type.weight;
    }
    double selector = rng.nextDouble() * totalWeight;

    for (MapTileType type : MapTileType.values()) {
      if (selector < type.weight) {
        return type;
      }
      selector -= type.weight;
    }
    return MapTileType.values() [MapTileType.values().length - 1];
  }

  /**
   * Draws the .svg map tile files randomly in a hexagon shaped pattern
   * @param context Parameters of created map tiles
   * @return Returns a vector map of map tiles, randomly placed based on weight
   */
  public VectorDrawable drawable (Context context) {
    return (VectorDrawable) context.getResources().getDrawable(drawable);
  }



}
