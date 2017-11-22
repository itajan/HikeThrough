package edu.cnm.deepdive.irt.hikethrough.entities;

import android.content.Context;
import android.graphics.drawable.VectorDrawable;
import edu.cnm.deepdive.irt.hikethrough.R;
import java.util.Random;

public enum MapTileType {

  START(R.drawable.ic_map_start, 0, 0),
  NEXTLEVEL(R.drawable.ic_map_nextlevel, 0, 0),
  HOME(R.drawable.ic_map_home, 0, 0),

  ALPINE(R.drawable.ic_map_alpine, 2, 4),
  MOUNTAIN(R.drawable.ic_map_mountain, 2, 3),
  CAVE(R.drawable.ic_map_cave, 1, 4),
  DESERT(R.drawable.ic_map_desert, 4, 1),
  FOREST(R.drawable.ic_map_forest, 3, 3),
  GRASSLAND(R.drawable.ic_map_grassland, 4, 1),
  RIVERLAND(R.drawable.ic_map_riverland, 3, 3),
  UNDERGROUND(R.drawable.ic_map_underground, 0, 0),
  TRADINGPOST(R.drawable.ic_map_tradingpost, 1, 0);


  public final int drawable;
  public final float weight;
  public final float cost;




  MapTileType (int drawable, float weight, float cost) {
    this.drawable = drawable;
    this.weight = weight;
    this.cost = cost;

  }

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

  public VectorDrawable drawable (Context context) {
    return (VectorDrawable) context.getResources().getDrawable(drawable);
  }



}
