package edu.cnm.deepdive.irt.hikethrough.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Public class for storing inventory items using ormlite.
 */
@DatabaseTable (tableName = "ITEM")
public class Inventory {

  @DatabaseField(columnName = "ITEM_ID", generatedId = true)
  private int id;

  @DatabaseField(columnName = "CREATED", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", format = "yyyy-MM-dd HH:mm:ss", canBeNull = false, readOnly = true)
  private Timestamp created;

//  @DatabaseField(columnName = "COUNTDOWN", columnDefinition = "COUNTDOWN TIMER", format = )
//  private Timestamp created;

  @DatabaseField(columnName = "NAME", canBeNull = false)
  private String name;

  /**
   * Gets an integer
   * @return Returns integer "id"
   */
  public int getId() {
    return id;
  }

  /**
   *
   * @return Returns string field "name"
   */
  public String getName() {
    return name;
  }

  /**
   *
   * @return returns field "created"
   */
  public Timestamp getCreated() {
    return created;
  }

  /**
   *
   * @param name sets name in the database
   */
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    Map<String, Object> map = new HashMap<>();
    map.put("id", id);
    map.put("name", created);
    return map.toString();
  }

}
