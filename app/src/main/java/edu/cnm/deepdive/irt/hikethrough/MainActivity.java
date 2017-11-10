package edu.cnm.deepdive.irt.hikethrough;



import static com.j256.ormlite.android.apptools.OpenHelperManager.releaseHelper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import edu.cnm.deepdive.irt.hikethrough.fragments.GameFragment;
import edu.cnm.deepdive.irt.hikethrough.fragments.InventoryFragment;
import edu.cnm.deepdive.irt.hikethrough.fragments.MapsFragment;
import edu.cnm.deepdive.irt.hikethrough.fragments.WalkTrackerFragment;
import edu.cnm.deepdive.irt.hikethrough.helpers.OrmHelper;

public class MainActivity extends AppCompatActivity implements OrmHelper.OrmInteraction {

  private OrmHelper helper = null;

  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
      = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      FragmentManager fragmentManager = getSupportFragmentManager();
      FragmentTransaction transaction = fragmentManager.beginTransaction();
      switch (item.getItemId()) {
        case R.id.navigation_game:
          transaction.replace(R.id.content, new GameFragment()).commit();
          return true;

        case R.id.navigation_walkconverter:
          transaction.replace(R.id.content, new WalkTrackerFragment()).commit();
          return true;

        case R.id.navigation_inventory:
          transaction.replace(R.id.content, new InventoryFragment()).commit();
          return true;

        case R.id.navigation_maps:
          transaction.replace(R.id.content, new MapsFragment()).commit();
          return true;
      }
      return false;
    }

  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getHelper().getWritableDatabase().close();
    setContentView(R.layout.activity_main);

    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.replace(R.id.content, new GameFragment()).commit();
  }

  @Override
  protected void onStart() {
    super.onStart();
    getHelper();
  }
  // releases the database from hogging memory and probably battery drain
  @Override
  protected void onStop() {
    super.onStop();
    releaseHelper();
  }

  @Override
  public synchronized OrmHelper getHelper() {
    if (helper == null) {
      helper = OpenHelperManager.getHelper(this, OrmHelper.class);
    }
    return helper;
  }

  public synchronized void releaseHelper() {
    if (helper != null) {
      OpenHelperManager.releaseHelper();
      helper = null;
    }
  }

}
