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
    //TODO
  }

  public void delete() {
    //TODO
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
