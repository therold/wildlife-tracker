import java.util.List;
import org.sql2o.*;

public class Animal implements DatabaseManagement {
  private int id;
  private String name;

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
        String sql = "INSERT INTO animals (name) VALUES (:name);";
        this.id = (int) con.createQuery(sql, true)
          .addParameter("name", this.name)
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
      String sql = "SELECT * FROM animals WHERE id = :id;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("id", id)
        .executeAndFetchFirst(Animal.class);
    }
  }

  public static List<Animal> search(String search) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE name ~* :search;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("search", ".*" + search + ".*")
        .executeAndFetch(Animal.class);
    }
  }

  public static List<Animal> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
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
