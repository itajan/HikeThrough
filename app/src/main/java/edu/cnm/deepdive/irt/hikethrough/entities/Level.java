package edu.cnm.deepdive.irt.hikethrough.entities;


import android.graphics.Point;
import java.util.Random;

public class Level {

  private Tile [][] terrain;

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

  public Tile[][] getTerrain() {
    return terrain;
  }

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

    public static Tile fromPq(int p, int q, MapTileType type) {
      Point rs = Tile.pqToRs(p, q);
      return new Tile(p, q, rs.x, rs.y, type);
    }

    public static Tile fromRingStep(int r, int s, MapTileType type) {
      Point pq = Tile.rsToPq(r, s);
      return new Tile(pq.x, pq.y, r, s, type);
    }

    public static Point pqToRs(int p, int q) {
      int ring;
      int step;
      if (p >= 0) {
        if (q <= 0) {
          if (-q < p) {
            ring = p;
            step = q;
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
            step = 3 * ring + 2 * (q + p) + 1;
          }
        }
      }
      return new Point(ring, step);
    }

    private Point pq() {
      return new Point(p, q);
    }

    private static Point rsToPq(int ring, int step) {
      int segment = step / ((ring > 0) ? ring : 1);
      int stepAlongSegment = step % ((ring > 0) ? ring : 1);
      int p = START_P [segment] * ring + INCREMENT_P [segment] * stepAlongSegment;
      int q = START_Q [segment] * ring + INCREMENT_Q [segment] * stepAlongSegment;
      return new Point(p, q);
    }

    public int getP() {
      return p;
    }

    public int getQ() {
      return q;
    }

    public int getR() {
      return r;
    }


    public int getS() {
      return s;
    }


    public MapTileType getType() {
      return type;
    }

    public void setType(MapTileType type) {
      this.type = type;
    }

  }


}
