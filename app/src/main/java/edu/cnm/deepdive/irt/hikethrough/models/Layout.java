package edu.cnm.deepdive.irt.hikethrough.models;

import android.graphics.PointF;
import java.util.ArrayList;

public class Layout {
  public Layout(Orientation orientation, PointF size, PointF origin) {
    this.orientation = orientation;
    this.size = size;
    this.origin = origin;
  }

  public final Orientation orientation;
  public final PointF size;
  public final PointF origin;

  static public Orientation pointy = new Orientation(Math.sqrt(3.0), Math.sqrt(3.0) / 2.0, 0.0, 3.0 / 2.0,
          Math.sqrt(3.0) / 3.0, -1.0 / 3.0, 0.0, 2.0 / 3.0, 0.5);
  static public Orientation flat = new Orientation(3.0 / 2.0, 0.0, Math.sqrt(3.0) / 2.0, Math.sqrt(3.0), 2.0 / 3.0,
      0.0, -1.0 / 3.0, Math.sqrt(3.0) / 3.0, 0.0);

  static public PointF hexToPixel(Layout layout, Hex h) {
    Orientation M = layout.orientation;
    PointF size = layout.size;
    PointF origin = layout.origin;
    double x = (M.f0 * h.q + M.f1 * h.r) * size.x;
    double y = (M.f2 * h.q + M.f3 * h.r) * size.y;
    return new PointF((float) (x + origin.x), (float) (y + origin.y));
  }

  static public FractionalHex pixelToHex(Layout layout, PointF p) {
    Orientation M = layout.orientation;
    PointF size = layout.size;
    PointF origin = layout.origin;
    PointF pt = new PointF((p.x - origin.x) / size.x, (p.y - origin.y) / size.y);
    double q = M.b0 * pt.x + M.b1 * pt.y;
    double r = M.b2 * pt.x + M.b3 * pt.y;
    return new FractionalHex(q, r, -q - r);
  }

  static public PointF hexCornerOffset(Layout layout, int corner) {
    Orientation M = layout.orientation;
    PointF size = layout.size;
    double angle = 2.0 * Math.PI * (M.start_angle - corner) / 6;
    return new PointF((float) (size.x * Math.cos(angle)), (float) (size.y * Math.sin(angle)));
  }

  static public ArrayList<PointF> polygonCorners(Layout layout, Hex h) {
    ArrayList<PointF> corners = new ArrayList<PointF>() {
      {
      }
    };
    PointF center = Layout.hexToPixel(layout, h);
    for (int i = 0; i < 6; i++) {
      PointF offset = Layout.hexCornerOffset(layout, i);
      corners.add(new PointF(center.x + offset.x, center.y + offset.y));
    }
    return corners;
  }

}
