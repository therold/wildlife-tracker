import java.util.List;
import org.sql2o.*;

public class Animal implements DatabaseManagement {
  protected int id;
  protected String name;
  private static final String DATABASE_TYPE = "animal";
  private static final int MIN_NAME_LENGTH = 1;

  public Animal(String name) {
    if(nameValidation(name)) {
      this.name = name;
    }
  }

  private boolean nameValidation(String name) {
    if(name.length() < MIN_NAME_LENGTH) {
      throw new IllegalArgumentException("Error: Name cannot be empty");
    } else {
      return true;
    }
  }

  public int getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    if(nameValidation(name)) {
      this.name = name;
    }
  }

  public void save() {
    if (nameExists(this.name, this.id)) {
      throw new IllegalArgumentException("Error: Name already exists.");
    } else {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO animals (name, type) VALUES (:name, :type);";
        this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("type", DATABASE_TYPE)
        .executeUpdate()
        .getKey();
      }
    }
  }

  public void update() {
    if (nameExists(this.name, this.id)) {
      throw new IllegalArgumentException("Error: Name already exists.");
    } else {
      try(Connection con = DB.sql2o.open()) {
        String sql = "UPDATE animals SET name = :name WHERE id = :id;";
        con.createQuery(sql)
          .addParameter("id", this.id)
          .addParameter("name", this.name)
          .executeUpdate();
      }
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

  public static Animal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE id = :id AND type = :type;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("id", id)
        .addParameter("type", DATABASE_TYPE)
        .executeAndFetchFirst(Animal.class);
    }
  }

  public static List<Animal> search(String search) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE name ~* :search AND type = :type;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("search", ".*" + search + ".*")
        .addParameter("type", DATABASE_TYPE)
        .executeAndFetch(Animal.class);
    }
  }

  public static List<Animal> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE type = :type;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("type", DATABASE_TYPE)
        .executeAndFetch(Animal.class);
    }
  }

  @Override
  public boolean equals(Object otherObject) {
    if (!(otherObject instanceof Animal)) {
      return false;
    } else {
      Animal otherAnimal = (Animal) otherObject;
      return this.getName().equals(otherAnimal.getName()) &&
             this.getId() == otherAnimal.getId();
    }
  }

}
