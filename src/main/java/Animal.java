import java.util.List;
import org.sql2o.*;

public class Animal implements DatabaseManagement {
  private int id;
  private String name;
  private static final String DATABASE_TYPE = "animal";

  public Animal(String name) {
    this.name = name;
  }

  public int getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void save() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO animals (name, type) VALUES (:name, :type);";
        this.id = (int) con.createQuery(sql, true)
          .addParameter("name", this.name)
          .addParameter("type", DATABASE_TYPE)
          .executeUpdate()
          .getKey();
      }
    }

  public void update() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE animals SET name = :name WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .addParameter("name", this.name)
        .executeUpdate();
    }
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
      return this.getName().equals(otherAnimal.getName());
    }
  }

}
