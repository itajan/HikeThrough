package edu.cnm.deepdive.irt.hikethrough.fragments;

import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.Toast;
import edu.cnm.deepdive.irt.hikethrough.R;
import edu.cnm.deepdive.irt.hikethrough.entities.Level;
import edu.cnm.deepdive.irt.hikethrough.entities.MapTileType;
import edu.cnm.deepdive.irt.hikethrough.views.GameView;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the
 * {@link GameFragment.OnFragmentInteractionListener} interface to handle interaction events. Use
 * the {@link GameFragment#} factory method to create an instance of this fragment.
 */
public class GameFragment extends Fragment {

  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  private static final int NUM_LEVELS = 7;
  private static final int NUM_RINGS = 6;

  private Level[] levels = new Level[NUM_LEVELS];
  private int currentLevel;
  private Random rng = new Random();
  private Point avatarPosition = null;
  private GameView view = null;

  private OnFragmentInteractionListener mListener;

  /**
   * Increase the level by 1 when you reach the NEXTLEVEL tile, up to 7 levels until the player
   * reaches the HOME hexagon tile.
   */
  public GameFragment() {
    for (int i = 0; i < NUM_LEVELS; i++) {
      levels[i] = new Level(NUM_RINGS, rng, MapTileType.START,
          (i < NUM_LEVELS - 1) ? MapTileType.NEXTLEVEL : MapTileType.HOME);
    }
  }


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {

    }
  }

  /**
   *
   * @return Returns the position of the avatar on the game board
   */
  public Point getAvatarPosition() {
    return avatarPosition;
  }

  /**
   *
   * @param avatarPosition Sets the avatar position at the starting point
   */
  public void setAvatarPosition(Point avatarPosition) {
    this.avatarPosition = avatarPosition;
    view.setPosition(avatarPosition);
    view.invalidate();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_game, container, false);
    view = (GameView) root.findViewById(R.id.gameView);
    view.setLevel(levels[0]);
    avatarPosition = new Point(1 - NUM_RINGS, 0);
    view.setPosition(avatarPosition);
    view.setFragment(this);
    return root;
  }

  /**
   *
   * @param uri Unassigned parameter
   */
  public void onButtonPressed(Uri uri) {
    if (mListener != null) {
      mListener.onFragmentInteraction(uri);
    }
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
      mListener = (OnFragmentInteractionListener) context;
    } else {
      Toast.makeText(context, "Hike Through", Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  public void onStart() {
    super.onStart();
  }

  @Override
  public void onStop() {
    super.onStop();
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  /**
   * This interface must be implemented by activities that contain this fragment to allow an
   * interaction in this fragment to be communicated to the activity and potentially other fragments
   * contained in that activity.
   * <p>
   * See the Android Training lesson <a href= "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */
  public interface OnFragmentInteractionListener {

    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }
}
