public class Sighting {
  private int id;
  private int animalId;

  public Sighting (int animalId, int locationId, int rangerId) {
    if(!Animal.idExists(animalId)) {
      throw new IllegalArgumentException("Error: invalid animalId");
    }
    this.animalId = animalId;
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

}
