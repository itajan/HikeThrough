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


public class GameView extends View {

  public static final int UNIT_SIZE = 90;
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


  VectorDrawable alpine;
  VectorDrawable cave;
  VectorDrawable desert;
  VectorDrawable forest;
  VectorDrawable grassland;
  VectorDrawable home;
  VectorDrawable mountain;
  VectorDrawable nextlevel;
  VectorDrawable riverland;
  VectorDrawable start;
  VectorDrawable tradingpost;
  VectorDrawable underground;


  public GameView(Context context, AttributeSet attrs) {
    super(context, attrs);

    alpine = (VectorDrawable) context.getResources().getDrawable(R.drawable.ic_map_alpine);
    cave = (VectorDrawable) context.getResources().getDrawable(R.drawable.ic_map_cave);
    desert = (VectorDrawable) context.getResources().getDrawable(R.drawable.ic_map_desert);
    forest = (VectorDrawable) context.getResources().getDrawable(R.drawable.ic_map_forest);
    grassland = (VectorDrawable) context.getResources().getDrawable(R.drawable.ic_map_grassland);
    home = (VectorDrawable) context.getResources().getDrawable(R.drawable.ic_map_home);
    mountain = (VectorDrawable) context.getResources().getDrawable(R.drawable.ic_map_mountain);
    nextlevel = (VectorDrawable) context.getResources().getDrawable(R.drawable.ic_map_nextlevel);
    riverland = (VectorDrawable) context.getResources().getDrawable(R.drawable.ic_map_riverland);
    start = (VectorDrawable) context.getResources().getDrawable(R.drawable.ic_map_start);
    tradingpost = (VectorDrawable) context.getResources()
        .getDrawable(R.drawable.ic_map_tradingpost);
    underground = (VectorDrawable) context.getResources()
        .getDrawable(R.drawable.ic_map_underground);

  }

  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.getWidth();
    float centerX = canvas.getWidth() / 2f;
    float centerY = canvas.getHeight() / 2f;
    float scale = Math.min(canvas.getWidth(), canvas.getHeight()) / (2f * RINGS + 1);
    RectF hexBox = new RectF();
    canvas.translate(centerX, centerY);
//    canvas.scale(scale, scale);
    for (int r = 0; r <= RINGS; r++) {
      mountain.setBounds(
          Math.round(-scale / 2),
          Math.round(-scale * INV_ROOT_3),
          Math.round(scale / 2),
          Math.round(scale * INV_ROOT_3));
      mountain.draw(canvas);
      for (int s = 0; s < 6 * r; s++) {
        hexBox(hexBox, r, s);
        mountain.setBounds(
            Math.round(scale * hexBox.left),
            Math.round(scale * hexBox.top),
            Math.round(scale * hexBox.right),
            Math.round(scale * hexBox.bottom));
        mountain.draw(canvas);
      }
    }

//    for (int i = 0; i < 3; i++) {
//      for (int j = 0; j < 3; j++) {
//        float top = hexTop(i, j);
//        float bottom = top + (float) Math.sqrt(3) * UNIT_SIZE;
//        float left = hexLeft(i, j);
//        float right = left + 2 * UNIT_SIZE;
//
//        if (j + i > 1 && j + i < 11) {
//          mountain.setBounds((int) left, (int) top, (int) right, (int) bottom);
//          mountain.draw(canvas);
//        }
//      }
//    }
  }


  private static float hexLeft(int row, int col) {
    return (1.5f * col) * UNIT_SIZE;
  }

  private static float hexTop(int row, int col) {
    return (float) ((Math.sqrt(3) * row) * UNIT_SIZE) + (col % 2 == 1 ? (float) (Math.sqrt(3.0) / 2
        * UNIT_SIZE) : 0);
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
    int segment = step / ring;
    int stepAlongSegment = step % ring;
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
