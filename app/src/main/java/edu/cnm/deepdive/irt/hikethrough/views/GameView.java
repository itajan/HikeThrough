package edu.cnm.deepdive.irt.hikethrough.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.VectorDrawable;
import android.util.AttributeSet;
import android.view.View;
import edu.cnm.deepdive.irt.hikethrough.R;

public class GameView extends View {

  private static int UNIT_SIZE;

  // private static final int UNIT_SIZE = 100;

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
    tradingpost = (VectorDrawable) context.getResources().getDrawable(R.drawable.ic_map_tradingpost);
    underground = (VectorDrawable) context.getResources().getDrawable(R.drawable.ic_map_underground);

  }

  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    UNIT_SIZE = canvas.getWidth() / 10;




    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        float top = hexTop(i, j);
        float bottom = top + (float) Math.sqrt(3) * UNIT_SIZE;
        float left = hexLeft(i, j);
        float right = left + 2 * UNIT_SIZE;

        if (j + i > 3 && j + i < 10) {
          mountain.setBounds((int) left, (int) top, (int) right, (int) bottom);
          mountain.draw(canvas);
        }
      }
    }



//  protected void onDraw(Canvas canvas) {
//    super.onDraw(canvas);
//    canvas.getWidth();
//
//
//    for (int i = 0; i < 12; i++) {
//      float top = hexTop(i);
//      float bottom = top + (float) Math.sqrt(3) * UNIT_SIZE;
//      for (int j = 0; j < 12; j++) {
//        float left = hexLeft(i, j);
//        float right = left + 2 * UNIT_SIZE;
//
//        home.setBounds((int) left,(int) top,(int) right,(int) bottom);
//        home.draw(canvas);
//      }
//    }


//    alpine.setBounds(0,0,100, 100);
//    alpine.draw(canvas);
//
//
//    cave.setBounds(0,0,100, 100);
//    cave.draw(canvas);
//
//
//    desert.setBounds(0,0,100, 100);
//    desert.draw(canvas);
//
//
//    forest.setBounds(0,0,100, 100);
//    forest.draw(canvas);
//
//
//    grassland.setBounds(0,0,100, 100);
//    grassland.draw(canvas);
//
//
//    home.setBounds(0,0,100, 100);
//    home.draw(canvas);
//
//
//    mountain.setBounds(0,0,100, 100);
//    mountain.draw(canvas);
//
//
//    nextlevel.setBounds(0,0,100, 100);
//    nextlevel.draw(canvas);
//
//
//    riverland.setBounds(0,0,100, 100);
//    riverland.draw(canvas);
//
//
//    start.setBounds(0,0,100, 100);
//    start.draw(canvas);
//
//
//    tradingpost.setBounds(0,0,100, 100);
//    start.draw(canvas);
//
//
//    underground.setBounds(0,0,100, 100);
//    start.draw(canvas);



  }

  private static float hexLeft(int row, int col) {
    return (1.5f * col) * UNIT_SIZE;
  }

  private static float hexTop(int row, int col) {
    return (float) ((Math.sqrt(3) * row) * UNIT_SIZE) + (col % 2 == 1 ? (float) (Math.sqrt(3.0)/2 * UNIT_SIZE) : 0);
  }

//  private static float hexLeft(int row, int col) {
//    return ((((row & 1) == 1) ? 1.5f : 0) + 3 * col) * UNIT_SIZE;
//  }
//
//  private static float hexTop(int row) {
//    return (float) ((Math.sqrt(3) / 2 * row) * UNIT_SIZE);
//  }


}
