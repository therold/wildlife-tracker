public class Location {
  private int id;
  private String name;
  private double xCoord;

  public Location(String name, double xCoord, double yCoord) {
    if(DatabaseManagement.nameValidation(name)) {
      this.name = name;
    }
    this.xCoord = xCoord;
  }

  public int getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    if(DatabaseManagement.nameValidation(name)) {
      this.name = name;
    }
  }

  public double getXCoord() {
    return this.xCoord;
  }

  public void setXCoord(double xCoord) {
    this.xCoord = xCoord;
  }

}
