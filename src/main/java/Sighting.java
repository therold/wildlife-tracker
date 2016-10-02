import org.sql2o.*;

public class Sighting {
  private int id;
  private int animalId;
  private int locationId;
  private int rangerId;

  public Sighting (int animalId, int locationId, int rangerId) {
    if(!Animal.idExists(animalId)) {
      throw new IllegalArgumentException("Error: invalid animalId");
    }
    this.animalId = animalId;
    if(!Location.idExists(locationId)) {
      throw new IllegalArgumentException("Error: invalid locationId");
    }
    this.locationId = locationId;
    if(!Ranger.idExists(rangerId)) {
      throw new IllegalArgumentException("Error: invalid locationId");
    }
    this.rangerId = rangerId;
  }

  public int getId() {
    return this.id;
  }

  public int getAnimalId() {
    return this.animalId;
  }

  public void setAnimalId(int animalId) {
    if(!Animal.idExists(animalId)) {
      throw new IllegalArgumentException("Error: invalid animalId");
    }
    this.animalId = animalId;
  }

  public int getLocationId() {
    return this.locationId;
  }

  public void setLocationId(int locationId) {
    if(!Location.idExists(locationId)) {
      throw new IllegalArgumentException("Error: invalid locationId");
    }
    this.locationId = locationId;
  }

  public int getRangerId() {
    return this.rangerId;
  }

  public void setRangerId(int rangerId) {
    if(!Ranger.idExists(rangerId)) {
      throw new IllegalArgumentException("Error: invalid rangerId");
    }
    this.rangerId = rangerId;
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO sightings (animal_id, location_id, ranger_id) VALUES (:animal_id, :location_id, :ranger_id);";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("animal_id", this.animalId)
      .addParameter("location_id", this.locationId)
      .addParameter("ranger_id", this.rangerId)
      .executeUpdate()
      .getKey();
    }
  }

  public static Sighting find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE id = :id;";
      return con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("id", id)
        .addColumnMapping("animal_id", "animalId")
        .addColumnMapping("location_id", "locationId")
        .addColumnMapping("ranger_id", "rangerId")
        .executeAndFetchFirst(Sighting.class);
    }
  }

  @Override
  public boolean equals(Object otherObject) {
    if (!(otherObject instanceof Sighting)) {
      return false;
    } else {
      Sighting otherSighting = (Sighting) otherObject;
      return this.getId() == otherSighting.getId(); // &&
        // this.getAnimalId() == otherSighting.getAnimalId() &&
        // this.getLocationId() == otherSighting.getLocationId() &&
        // this.getRangerId() == otherSighting.getRangerId();
    }
  }

}
