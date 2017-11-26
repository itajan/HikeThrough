package edu.cnm.deepdive.irt.hikethrough.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import edu.cnm.deepdive.irt.hikethrough.R;
import edu.cnm.deepdive.irt.hikethrough.entities.Inventory;
import edu.cnm.deepdive.irt.hikethrough.entities.Level;
import edu.cnm.deepdive.irt.hikethrough.helpers.OrmHelper.OrmInteraction;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the
 * {@link MapsFragment.OnFragmentInteractionListener} interface to handle interaction events. Use the {@link
 * MapsFragment} factory method to create an instance of this fragment.
 */
public class MapsFragment extends Fragment {

  private OnFragmentInteractionListener mListener;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    try {
      GridView rootView = (GridView) inflater
          .inflate(R.layout.fragment_maps, container, false);
      Dao<Level, Serializable> dao = ((OrmInteraction) getActivity()).getHelper().getLevelDao();
      QueryBuilder<Level, Serializable> builder = dao.queryBuilder();
      builder.orderBy("CREATED", false);
      List<Level> items = dao.query(builder.prepare());
      rootView.setAdapter(new LevelViewAdapter(getContext(), items));
      return rootView;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  /**
   *
   * @param uri Unused parameter
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
      Toast.makeText(context, "Saved Maps", Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  /**
   * Adapter interface to create view in Map fragment
   */
  public class LevelViewAdapter
      extends ArrayAdapter<Level> {

    /**
     *
     * @param context Context of data
     * @param objects Object type
     */
    public LevelViewAdapter(Context context, List<Level> objects) {
      super(context, R.layout.level_map_content, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      Context context = getContext();
      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.level_map_content, null);
      Level item = getItem(position);
      TextView created = (TextView) layout.findViewById(R.id.map_created);
      DateFormat format = new SimpleDateFormat("MM/dd/yy h:mm a");
      created.setText(format.format(item.getTerrain()));

      return layout;

    }
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