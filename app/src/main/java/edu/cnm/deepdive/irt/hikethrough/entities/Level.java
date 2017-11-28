package edu.cnm.deepdive.irt.hikethrough.entities;


import android.graphics.Point;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Level class that defines the terrain for each level using the method defined in GameView class.
 */
@DatabaseTable(tableName = "LEVEL")
public class Level {

  @DatabaseField(columnName = "TERRAIN_ID", generatedId = true)
  private int id;

  @DatabaseField(columnName = "CREATED", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", format = "yyyy-MM-dd HH:mm:ss", canBeNull = false, readOnly = true)



  private Tile [][] terrain;

  /**
   *
   * @param rings Number of rings in the hexagon-shaped map
   * @param rng Randomly generated selection of map tiles based on weight
   * @param start Start of game when game has the function of being linear and a levels 1-7 structure
   * @param finish The game finishes when you land on home which currently is not implemented but would
   *               level 7
   */
  public Level (int rings, Random rng, MapTileType start, MapTileType finish) {
    terrain = new Tile[rings][];
    for (int i = 0; i < rings; i++) {
      terrain [i] = new Tile [(i == 0) ? 1 : 6 * i];
      for (int j = 0; j < terrain[i].length; j++) {
        MapTileType type = MapTileType.random(rng);
        terrain[i][j] = Tile.fromRingStep(i, j, type);
      }
    }

    terrain [rings - 1][0].setType(finish);
    terrain [rings - 1][terrain[rings - 1].length / 2].setType(start);
  }

  /**
   *
   * @return Returns a hexagon map of hexagons "terrain"
   */
  public Tile[][] getTerrain() {
    return terrain;
  }

  /**
   * Location class "Tile" to determine where the map tiles begin to be place and iterate in a
   * spiral fashion creating a hexagon-shaped map
   */
  public static class Tile {

    private static final int [] START_P = {1, 1, 0, -1, -1, 0};
    private static final int [] START_Q = {0, -1, -1, 0, 1, 1};
    private static final int [] INCREMENT_P = {0, -1, -1, 0, 1, 1};
    private static final int [] INCREMENT_Q = {-1, 0, 1, 1, 0, -1};

    private int p;
    private int q;
    private int r;
    private int s;
    private MapTileType type;

    private Tile (int p, int q, int r, int s, MapTileType type) {
      this.p = p;
      this.q = q;
      this.r = r;
      this.s = s;
      this.type = type;
    }

    /**
     * This method currently is not used.
     * @param p Parameter p is x is this case
     * @param q Parameter q is y is this case
     * @param type Type is the type of map tile, e.g. alpine, mountain, etc.
     * @return Returns a map type tile and places at position at correct position on map
     */
    public static Tile fromPq(int p, int q, MapTileType type) {
      Point rs = Tile.pqToRs(p, q);
      return new Tile(p, q, rs.x, rs.y, type);
    }

    /**
     * This method determines where on the map we are based on number of steps moving in a counter-
     * clockwise fashion from x = 1 out to the right of (0, 0)
     * @param r Parameter p is x is this case
     * @param s Parameter q is y is this case
     * @param type Type is the type of map tile, e.g. alpine, mountain, etc.
     * @return
     */
    public static Tile fromRingStep(int r, int s, MapTileType type) {
      Point pq = Tile.rsToPq(r, s);
      return new Tile(pq.x, pq.y, r, s, type);
    }

    /**
     * Main logic for determining position on the hexagonal game board through conversion of p & q
     * to rings and steps.
     * @param p Parameter p is x is this case
     * @param q Parameter q is y is this case
     * @return Returns position of hexagon tile on map
     */
    public static Point pqToRs(int p, int q) {
      int ring;
      int step;
      if (p >= 0) {
        if (q <= 0) {
          if (-q < p) {
            ring = p;
            step = -q;
          } else {
            ring = -q;
            step = 2 * ring - p;
          }
        } else {
          ring = p + q;
          step = 6 * ring - q;
        }
      } else {
        if (q <= 0) {
          ring = -p - q;
          step = 3 * ring + q;
        } else {
          if (q <= -p) {
            ring = -p;
            step = 3 * ring + q;
          } else {
            ring = q;
            step = 5 * ring + p;
          }
        }
      }
      return new Point(ring, step);
    }

    private Point pq() {
      return new Point(p, q);
    }

    /**
     * Conversion of ring and steps back to pq
     *
     * @param ring Starting ring 0 - n
     * @param step Step increments
     * @return Returns the x and y of hexagon tile
     */
    public static Point rsToPq(int ring, int step) {
      int segment = step / ((ring > 0) ? ring : 1);
      int stepAlongSegment = step % ((ring > 0) ? ring : 1);
      int p = START_P [segment] * ring + INCREMENT_P [segment] * stepAlongSegment;
      int q = START_Q [segment] * ring + INCREMENT_Q [segment] * stepAlongSegment;
      return new Point(p, q);
    }

    /**
     *
     * @return Returns position p
     */
    public int getP() {
      return p;
    }

    /**
     *
     * @return Returns position q
     */
    public int getQ() {
      return q;
    }

    /**
     *
     * @return Returns the ring we are in
     */
    public int getR() {
      return r;
    }

    /**
     *
     * @return Returns the number of steps on the ring
     */
    public int getS() {
      return s;
    }

    /**
     *
     * @return Returns the map tile type
     */
    public MapTileType getType() {
      return type;
    }

    /**
     *
     * @param type Map tile type
     */
    public void setType(MapTileType type) {
      this.type = type;
    }

    @DatabaseField(columnName = "NAME", canBeNull = false)
    private String name;

    /**
     *
     * @return This is an attempt to get the current map tile type position and store the data in an
     * image file
     */
    public String getName() {
      return name;
    }

    /**
     *
     * @param name Name parameter
     */
    public void setName(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      Map<String, Object> map = new HashMap<>();
      map.put("id", r);
      map.put("name", s);
      map.put("id", type);
      return map.toString();
    }

  }


}
