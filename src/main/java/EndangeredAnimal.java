import org.sql2o.*;
import java.util.List;

public class EndangeredAnimal extends Animal implements DatabaseManagement {
  private double age;
  private static final String DATABASE_TYPE = "endangered_animal";

  public EndangeredAnimal(String name, double age) {
    if(DatabaseManagement.nameValidation(name)) {
      this.name = name;
    }
    if(DatabaseManagement.ageValidation(age)) {
      this.age = age;
    }
  }

  public double getAge() {
    return this.age;
  }

  public void setAge(double age) {
    if(DatabaseManagement.ageValidation(age)) {
      this.age = age;
    }
  }

  @Override
  public void save() {
    if (Animal.nameExists(this.name, this.id)) {
      throw new IllegalArgumentException("Error: Name already exists.");
    } else {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO animals (name, type, age) VALUES (:name, :type, :age);";
        this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("age", this.age)
        .addParameter("type", DATABASE_TYPE)
        .executeUpdate()
        .getKey();
      }
    }
  }

  public void update() {
    if (Animal.nameExists(this.name, this.id)) {
      throw new IllegalArgumentException("Error: Name already exists.");
    } else {
      try(Connection con = DB.sql2o.open()) {
        String sql = "UPDATE animals SET name = :name, age = :age WHERE id = :id;";
        con.createQuery(sql)
          .addParameter("id", this.id)
          .addParameter("name", this.name)
          .addParameter("age", this.age)
          .executeUpdate();
      }
    }
  }

  public static List<EndangeredAnimal> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE type = :type;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("type", DATABASE_TYPE)
        .executeAndFetch(EndangeredAnimal.class);
    }
  }

  public static EndangeredAnimal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE id = :id AND type = :type;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("id", id)
        .addParameter("type", DATABASE_TYPE)
        .executeAndFetchFirst(EndangeredAnimal.class);
    }
  }

  public static List<EndangeredAnimal> search(String search) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE name ~* :search AND type = :type;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("search", ".*" + search + ".*")
        .addParameter("type", DATABASE_TYPE)
        .executeAndFetch(EndangeredAnimal.class);
    }
  }


  @Override
  public boolean equals(Object otherObject) {
    if (!(otherObject instanceof EndangeredAnimal)) {
      return false;
    } else {
      EndangeredAnimal otherAnimal = (EndangeredAnimal) otherObject;
      return this.getName().equals(otherAnimal.getName()) &&
             this.getId() == otherAnimal.getId();
    }
  }

}
