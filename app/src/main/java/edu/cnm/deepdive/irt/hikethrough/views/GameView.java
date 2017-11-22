package edu.cnm.deepdive.irt.hikethrough.views;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.VectorDrawable;
import android.util.AttributeSet;
import android.view.View;
import edu.cnm.deepdive.irt.hikethrough.R;
import edu.cnm.deepdive.irt.hikethrough.entities.Level;
import edu.cnm.deepdive.irt.hikethrough.entities.Level.Tile;
import edu.cnm.deepdive.irt.hikethrough.entities.MapTileType;
import java.util.Random;


public class GameView extends View {

  public static final int RINGS = 4;
  public static final float INV_ROOT_3 = (float) (1 / Math.sqrt(3));
  public static final float ROOT_3 = (float) (Math.sqrt(3));


  private static final int [] START_P = {1, 1, 0, -1, -1, 0};
  private static final int [] START_Q = {0, -1, -1, 0, 1, 1};
  private static final int [] INCREMENT_P = {0, -1, -1, 0, 1, 1};
  private static final int [] INCREMENT_Q = {-1, 0, 1, 1, 0, -1};

  private static final int [][] NEIGHBOR_OFFSETS = {
      {1, 0},
      {1, -1},
      {0, -1},
      {-1, 0},
      {-1, 1},
      {0, 1}
  };

  private Level level;

//  VectorDrawable alpine;
//  VectorDrawable cave;
//  VectorDrawable desert;
//  VectorDrawable forest;
//  VectorDrawable grassland;
//  VectorDrawable home;
//  VectorDrawable mountain;
//  VectorDrawable nextlevel;
//  VectorDrawable riverland;
//  VectorDrawable start;
//  VectorDrawable tradingpost;
//  VectorDrawable underground;


  public GameView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.getWidth();
    float centerX = canvas.getWidth() / 2f;
    float centerY = canvas.getHeight() / 2f;
    float scale = Math.min(canvas.getWidth(), canvas.getHeight()) / (2f * RINGS - 1);
    RectF hexBox = new RectF();
    canvas.translate(centerX, centerY);
    // canvas.scale(1, -1);
     Level level = new Level(RINGS, new Random(), MapTileType.START, MapTileType.NEXTLEVEL);
    for (Tile[] ring : level.getTerrain()) {
      for (Tile tile : ring) {
        hexBox(hexBox, tile.getR(), tile.getS());
        VectorDrawable drawable = tile.getType().drawable(getContext());
        drawable.setBounds(
            Math.round(scale * hexBox.left),
            Math.round(scale * hexBox.top),
            Math.round(scale * hexBox.right),
            Math.round(scale * hexBox.bottom));
        drawable.draw(canvas);
      }
    }
  }

  public Level getLevel() {
    return level;
  }

  public void setLevel(Level level) {
    this.level = level;
  }

  private static PointF hexCenter(int ring, int step) {
    int segment = step / ring;
    int stepAlongSegment = step % ring;
    float x = (float) (ring * Math.cos(Math.PI * segment / 3)
        + stepAlongSegment * Math.cos(Math.PI * (segment + 2) / 3));
    float y = (float) (ring * Math.sin(Math.PI * segment / 3)
        + stepAlongSegment * Math.sin(Math.PI * (segment + 2) / 3));
    return new PointF(x, y);
  }

  private static void hexBox(RectF box, int ring, int step) {
    int segment = step / ((ring > 0) ? ring : 1);
    int stepAlongSegment = step % ((ring > 0) ? ring : 1);
    float x = (float) (ring * Math.cos(Math.PI * segment / 3)
        + stepAlongSegment * Math.cos(Math.PI * (segment + 2) / 3));
    float y = (float) (ring * Math.sin(Math.PI * segment / 3)
        + stepAlongSegment * Math.sin(Math.PI * (segment + 2) / 3));
    box.set((x - 0.5f), (y - INV_ROOT_3), (x + 0.5f), (y + INV_ROOT_3));
  }

  // p = x axis q = y axis which equals
  private static Point pqToRs(int p, int q) {
    int ring;
    int step;
    if (p > 0) {
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

  private static Point rsToPq(int ring, int step) {
    int segment = step / ring;
    int stepAlongSegment = step % ring;
    int p = START_P [segment] * ring + INCREMENT_P [stepAlongSegment];
    int q = START_Q [segment] * ring + INCREMENT_Q [stepAlongSegment];
    return new Point(p, q);
  }

  private static Point[] neighbors (int p, int q) {
    Point[] neighbors = new Point[6];
    for (int i = 0; i < 6; i++) {
      neighbors[i] = new Point(p + NEIGHBOR_OFFSETS[i][0], q + NEIGHBOR_OFFSETS[i][1]);
    }
    return neighbors;
  }

//  private static float hexLeft(int row, int col) {
//    return ((((row & 1) == 1) ? 1.5f : 0) + 3 * col) * UNIT_SIZE;
//  }
//
//  private static float hexTop(int row) {
//    return (float) ((Math.sqrt(3) / 2 * row) * UNIT_SIZE);
//  }


}
