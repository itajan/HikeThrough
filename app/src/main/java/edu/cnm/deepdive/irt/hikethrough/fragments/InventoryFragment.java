package edu.cnm.deepdive.irt.hikethrough.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import edu.cnm.deepdive.irt.hikethrough.R;
import edu.cnm.deepdive.irt.hikethrough.entities.Inventory;
import edu.cnm.deepdive.irt.hikethrough.helpers.OrmHelper.OrmInteraction;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the
 * {@link InventoryFragment.OnFragmentInteractionListener} interface to handle interaction events. Use the {@link
 * InventoryFragment} factory method to create an instance of this fragment.
 */
public class InventoryFragment extends Fragment {

  private OnFragmentInteractionListener mListener;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    try {
      ListView rootView = (ListView) inflater
          .inflate(R.layout.fragment_inventory_list, container, false);
      Dao<Inventory, Integer> dao = ((OrmInteraction) getActivity()).getHelper().getInventoryDao();
      QueryBuilder<Inventory, Integer> builder = dao.queryBuilder();
      builder.orderBy("CREATED", false);
      List<Inventory> items = dao.query(builder.prepare());
      rootView.setAdapter(new InventoryViewAdapter(getContext(), items));
      return rootView;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

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
      Toast.makeText(context, "Check your inventory", Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  /**
   * Adapter to create an array of inventory in the ListView
   */
  public class InventoryViewAdapter
      extends ArrayAdapter<Inventory> {

    /**
     *
     * @param context Context of data
     * @param objects Object type
     */
    public InventoryViewAdapter(Context context, List<Inventory> objects) {
      super(context, R.layout.inventory_list_content, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      Context context = getContext();
      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.inventory_list_content, null);
      Inventory item = getItem(position);
      TextView name = (TextView) layout.findViewById(R.id.inventory_name);
      TextView created = (TextView) layout.findViewById(R.id.inventory_created);
      name.setText(item.getName());
      DateFormat format = new SimpleDateFormat("MM/dd/yy h:mm a");
      created.setText(format.format(item.getCreated()));

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
