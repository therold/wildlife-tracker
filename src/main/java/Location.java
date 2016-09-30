import org.sql2o.*;

public class Location {
  private int id;
  private String name;
  private double xCoord;
  private double yCoord;

  public Location(String name, double xCoord, double yCoord) {
    if(DatabaseManagement.nameValidation(name)) {
      this.name = name;
    }
    this.xCoord = xCoord;
    this.yCoord = yCoord;
  }

  public int getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    if(DatabaseManagement.nameValidation(name)) {
      this.name = name;
    }
  }

  public double getXCoord() {
    return this.xCoord;
  }

  public void setXCoord(double xCoord) {
    this.xCoord = xCoord;
  }

  public double getYCoord() {
    return this.yCoord;
  }

  public void setYCoord(double yCoord) {
    this.yCoord = yCoord;
  }

  public void save() {
    if (Location.nameExists(this.name, this.id)) {
      throw new IllegalArgumentException("Error: Name already exists.");
    } else {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO locations (name, x_coord, y_coord) VALUES (:name, :x_coord, :y_coord);";
        this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("x_coord", this.xCoord)
        .addParameter("y_coord", this.yCoord)
        .executeUpdate()
        .getKey();
      }
    }
  }

  public static boolean nameExists(String name, int id) {
    Integer count = 0;
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT count(name) FROM locations WHERE name = :name AND id != :id;";
      count = con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeScalar(Integer.class);
    }
    return count != 0;
  }

  public static Location find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM locations WHERE id = :id;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("id", id)
        .addColumnMapping("x_coord", "xCoord")
        .addColumnMapping("y_coord", "yCoord")
        .executeAndFetchFirst(Location.class);
    }
  }

  @Override
  public boolean equals(Object otherObject) {
    if (!(otherObject instanceof Location)) {
      return false;
    } else {
      Location otherLocation = (Location) otherObject;
      return this.getId() == otherLocation.getId() &&
        this.getName().equals(otherLocation.getName()) &&
        this.getXCoord() == otherLocation.getXCoord() &&
        this.getYCoord() == otherLocation.getYCoord();
    }
  }

}
