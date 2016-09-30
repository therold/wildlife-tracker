import org.sql2o.*;

public class EndangeredAnimal extends Animal implements DatabaseManagement {
  private static final String DATABASE_TYPE = "endangered_animal";

  public EndangeredAnimal(String name) {
    super(name);
  }

  @Override
  public void save() {
    if (Animal.nameExists(this.name, this.id)) {
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
