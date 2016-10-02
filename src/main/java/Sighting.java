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
    if(!Animal.idExists(locationId)) {
      throw new IllegalArgumentException("Error: invalid locationId");
    }
    this.locationId = locationId;
  }

  public int getRangerId() {
    return this.rangerId;
  }

  public void setRangerId(int rangerId) {
    if(!Animal.idExists(rangerId)) {
      throw new IllegalArgumentException("Error: invalid rangerId");
    }
    this.rangerId = rangerId;
  }

}
