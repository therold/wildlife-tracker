import java.util.List;
import org.sql2o.*;

public abstract class Animal implements DatabaseManagement {
  protected int id;
  protected String name;
  private static final int MIN_NAME_LENGTH = 1;

  public int getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    if(Animal.nameValidation(name)) {
      this.name = name;
    }
  }

  protected static boolean nameValidation(String name) {
    if(name.length() < MIN_NAME_LENGTH) {
      throw new IllegalArgumentException("Error: Name cannot be empty");
    } else {
      return true;
    }
  }

  public static boolean nameExists(String name, int id) {
    Integer count = 0;
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT count(name) FROM animals WHERE name = :name AND id != :id;";
      count = con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("name", name)
        .addParameter("id", id)
        .executeScalar(Integer.class);
    }
    return count != 0;
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM animals WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

}
