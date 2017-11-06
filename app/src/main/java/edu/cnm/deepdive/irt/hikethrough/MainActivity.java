package edu.cnm.deepdive.irt.hikethrough;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  private TextView mTextMessage;

  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
      = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.navigation_game:
          mTextMessage.setText(R.string.title_game);
          return true;
        case R.id.navigation_startwalk:
          mTextMessage.setText(R.string.title_startwalk);
          return true;
        case R.id.navigation_items:
          mTextMessage.setText(R.string.title_items);
          return true;
        case R.id.navigation_maps:
          mTextMessage.setText(R.string.title_maps);
          return true;
      }
      return false;
    }

  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
  }

}
