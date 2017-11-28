package edu.cnm.deepdive.irt.hikethrough.views;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.VectorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import edu.cnm.deepdive.irt.hikethrough.R;
import edu.cnm.deepdive.irt.hikethrough.entities.Level;
import edu.cnm.deepdive.irt.hikethrough.entities.Level.Tile;
import edu.cnm.deepdive.irt.hikethrough.entities.MapTileType;
import edu.cnm.deepdive.irt.hikethrough.fragments.GameFragment;
import java.util.Random;

/**
 * GameView class that defines methods for drawing the hexagon map on screen. Houses the methods for
 * drawing the map and drawing the avatar on the screen at runtime.
 */
public class GameView extends View implements OnClickListener, OnTouchListener {

  /**
   * Defines the constant for number of rings in hexagon map
   */
//  public static final int RINGS = 6;
  /**
   * Defines the constant for math formula for calculating radius of hexagon
   */
  public static final float INV_ROOT_3 = (float) (1 / Math.sqrt(3));
  /**
   * Defines the constant for math formula for calculating radius of hexagon
   */
  public static final float ROOT_3 = (float) (Math.sqrt(3));


  private static final int[] START_P = {1, 1, 0, -1, -1, 0};
  private static final int[] START_Q = {0, -1, -1, 0, 1, 1};
  private static final int[] INCREMENT_P = {0, -1, -1, 0, 1, 1};
  private static final int[] INCREMENT_Q = {-1, 0, 1, 1, 0, -1};

  private static final int[][] NEIGHBOR_OFFSETS = {
      {1, 0},
      {1, -1},
      {0, -1},
      {-1, 0},
      {-1, 1},
      {0, 1}
  };

  private Level level;
  private Point position;
  private PointF touch = new PointF();
  private float centerX;
  private float centerY;
  private float scale;
  private GameFragment fragment;

  /**
   *
   * @return Returns a position in point form
   */
  public Point getPosition() {
    return position;
  }

  /**
   *
   * @param position Sets the pq position
   */
  public void setPosition(Point position) {
    this.position = position;
    Log.d("WTF position PQ", position.toString());
  }
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

  {
    setWillNotDraw(false);
    setDrawingCacheEnabled(true);
    setOnTouchListener(this);
    setOnClickListener(this);
  }

  /**
   * GameView method to draw hexagon game board based on given parameters
   *
   * @param context Map tile type data
   * @param attrs   Attributes of map tiles
   */
  public GameView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }


  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.getWidth();
    centerX = canvas.getWidth() / 2f;
    centerY = canvas.getHeight() / 2f;
    scale = Math.min(canvas.getWidth(), canvas.getHeight()) / (2f * level.getTerrain().length - 1);
    RectF hexBox = new RectF();
    canvas.translate(centerX, centerY);
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
    Point avatarRs = pqToRs(position.x, position.y);
    PointF avatarCenter = hexCenter(avatarRs.x, avatarRs.y);
    Point[] neighbors = neighbors(position.x, position.y);
    Paint paint = new Paint();
    paint.setColor(Color.RED);
    paint.setStrokeWidth(3);
    for (Point point : neighbors) {
      Log.d("WTF drawing neighbor PQ", point.toString());
      Point rs = Level.Tile.pqToRs(point.x, point.y);
      Log.d("WTF drawing neighbor RS", rs.toString());
      if (rs.x < level.getTerrain().length) {
        PointF neighborCenter = hexCenter(rs.x, rs.y);
        Log.d("WTF draw neighbor cntr", neighborCenter.toString());
        canvas.drawLine(avatarCenter.x * scale, avatarCenter.y * scale,
            neighborCenter.x * scale, neighborCenter.y * scale, paint);
      }
    }
  }

  @Override
  public boolean onTouch(View view, MotionEvent event) {
    switch (event.getActionMasked()) {
      case MotionEvent.ACTION_UP:
        touch.set(event.getX(), event.getY());
    }
    return false;
  }


  @Override
  public void onClick(View view) {
    float clickX = (touch.x - centerX) / scale;
    float clickY = (touch.y - centerY) / scale;
    Log.d("WTF touch", new PointF(touch.x, touch.y).toString());
    RectF neighborBox = new RectF();
    double bestDistance = Double.MAX_VALUE;
    Point closestHex = null;
    Log.d("WTF avatar PQ", position.toString());
    for (Point neighbor : neighbors(position.x, position.y)) {
      Log.d("WTF neighbor PQ", neighbor.toString());
      Point neighborRS = pqToRs(neighbor.x, neighbor.y);
      Log.d("WTF neighbor RS", neighborRS.toString());
      if (neighborRS.x < level.getTerrain().length) {
        hexBox(neighborBox, neighborRS.x, neighborRS.y);
        Log.d("WTF neighbor box", neighborBox.toString());
        if (neighborBox.contains(clickX, clickY)) {
          PointF hexCenter = hexCenter(neighborRS.x, neighborRS.y);
          Log.d("WTF neighbor center", hexCenter.toString());
          double testDistance = Math.hypot(hexCenter.x - clickX, hexCenter.y - clickY);
          if (testDistance < bestDistance) {
            bestDistance = testDistance;
            closestHex = neighbor;
          }
        }
      }
    }
    if (closestHex != null) {
      fragment.setAvatarPosition(closestHex);
    }
  }

  /**
   *
   * @return Returns current fragment state
   */
  public GameFragment getFragment() {
    return fragment;
  }

  /**
   *
   * @param fragment Sets the fragment view so that the map composition does not change when you navigate away
   */
  public void setFragment(GameFragment fragment) {
    this.fragment = fragment;
  }

  /**
   * @return returns current level
   */
  public Level getLevel() {
    return level;
  }

  /**
   * @param level Sets level as current
   */
  public void setLevel(Level level) {
    this.level = level;
  }

  private static PointF hexCenter(int ring, int step) {
    int segment = step / ((ring > 0) ? ring : 1);
    int stepAlongSegment = step % ((ring > 0) ? ring : 1);
    float x = (float) (ring * Math.cos(Math.PI * segment / 3)
        + stepAlongSegment * Math.cos(Math.PI * (segment + 2) / 3));
    float y = -(float) (ring * Math.sin(Math.PI * segment / 3)
        + stepAlongSegment * Math.sin(Math.PI * (segment + 2) / 3));
    return new PointF(x, y);
  }

  private static void hexBox(RectF box, int ring, int step) {
    int segment = step / ((ring > 0) ? ring : 1);
    int stepAlongSegment = step % ((ring > 0) ? ring : 1);
    float x = (float) (ring * Math.cos(Math.PI * segment / 3)
        + stepAlongSegment * Math.cos(Math.PI * (segment + 2) / 3));
    float y = -(float) (ring * Math.sin(Math.PI * segment / 3)
        + stepAlongSegment * Math.sin(Math.PI * (segment + 2) / 3));
    box.set((x - 0.5f), (y - INV_ROOT_3), (x + 0.5f), (y + INV_ROOT_3));
  }

  // p = x axis q = y axis which equals
  private static Point pqToRs(int p, int q) {
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

  private static Point rsToPq(int ring, int step) {
    int segment = step / ring;
    int stepAlongSegment = step % ring;
    int p = START_P[segment] * ring + INCREMENT_P[segment] * stepAlongSegment;
    int q = START_Q[segment] * ring + INCREMENT_Q[segment] * stepAlongSegment;
    return new Point(p, q);
  }

  private static Point[] neighbors(int p, int q) {
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
