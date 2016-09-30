public class Location {
  private int id;
  private String name;

  public Location(String name, double xCoord, double yCoord) {
    if(DatabaseManagement.nameValidation(name)) {
      this.name = name;
    }
  }

  public int getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

}
