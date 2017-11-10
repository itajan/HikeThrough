package edu.cnm.deepdive.irt.hikethrough.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import edu.cnm.deepdive.irt.hikethrough.entities.Inventory;
import java.sql.SQLException;

public class OrmHelper extends OrmLiteSqliteOpenHelper {

  private static final String DATABASE_NAME = "inventory.db";
  private static final int DATABASE_VERSION = 1;

  // Data access object(Dao)
  private Dao<Inventory, Integer> inventoryDao = null;


  public OrmHelper (Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
    try {
      TableUtils.createTable(connectionSource, Inventory.class);
      populateDatabase();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion,
      int newVersion) {

  }

  @Override
  public void close() {
    inventoryDao = null;
    super.close();
  }

  public synchronized Dao<Inventory, Integer> getInventoryDao() throws SQLException {
    if (inventoryDao == null) {
      inventoryDao = getDao(Inventory.class);
    }
    return inventoryDao;
  }

  private void populateDatabase() throws SQLException {
    Inventory inventory = new Inventory();
    inventory.setName("Balthazar The Horse");
    getInventoryDao().create(inventory);
    inventory = new Inventory();
    inventory.setName("Super Burly Gnarly Boots");
    getInventoryDao().create(inventory);
  }

  public interface OrmInteraction {
    OrmHelper getHelper();
  }


}
