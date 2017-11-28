package edu.cnm.deepdive.irt.hikethrough.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import edu.cnm.deepdive.irt.hikethrough.entities.Inventory;
import edu.cnm.deepdive.irt.hikethrough.entities.Level;
import edu.cnm.deepdive.irt.hikethrough.entities.MapTileType;
import java.io.Serializable;
import java.sql.SQLException;

/**
 * OrmHelper class that extends SQLite data storage native to Android.
 *
 * List of inventory items that may be utilized in future builds of the game to augment play elements.
 */
public class OrmHelper extends OrmLiteSqliteOpenHelper {

  private static final String DATABASE_NAME = "inventory.db";
  private static final int DATABASE_VERSION = 1;


  // Data access object(Dao)
  private Dao<Inventory, Integer> inventoryDao = null;
  private Dao<Level, Serializable> levelDao = null;

  /**
   * OrmHelper constructor
   * @param context Context of data
   */
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

  /**
   *
   * @return Returns inventory data access object
   * @throws SQLException Throws exception if no data is found
   */
  public synchronized Dao<Inventory, Integer> getInventoryDao() throws SQLException {
    if (inventoryDao == null) {
      inventoryDao = getDao(Inventory.class);
    }
    return inventoryDao;
  }

  /**
   *
   * @return Returns level data access object
   * @throws SQLException Throws exception if no data is found
   */
  public synchronized  Dao<Level, Serializable> getLevelDao() throws SQLException {
    if (levelDao == null) {
      levelDao = getDao(Level.class);
    }
    return levelDao;
  }

  private void populateDatabase() throws SQLException {
    Inventory inventory = new Inventory();
    inventory.setName("Balthazar The Horse");
    getInventoryDao().create(inventory);

    inventory = new Inventory();
    inventory.setName("Super Burly Gnarly Boots");
    getInventoryDao().create(inventory);

    inventory = new Inventory();
    inventory.setName("Iron Oak Hiking Pole-Spear");
    getInventoryDao().create(inventory);

    inventory = new Inventory();
    inventory.setName("Benny the Bear's Coat");
    getInventoryDao().create(inventory);

    inventory = new Inventory();
    inventory.setName("Moro The Wolf");
    getInventoryDao().create(inventory);

    inventory = new Inventory();
    inventory.setName("Crystal Clear Water");
    getInventoryDao().create(inventory);

    inventory = new Inventory();
    inventory.setName("Cosworth's Cozy Tent");
    getInventoryDao().create(inventory);

    inventory = new Inventory();
    inventory.setName("Ancient Gold Coin");
    getInventoryDao().create(inventory);

    inventory = new Inventory();
    inventory.setName("Blue Crystal");
    getInventoryDao().create(inventory);

  }

  /**
   * Interface method used in other classes, OrmHelper
   */
  public interface OrmInteraction {
    OrmHelper getHelper();
  }


}
