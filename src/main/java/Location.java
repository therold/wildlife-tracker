public class Location {
  private int id;
  private String name;
  private double xCoord;
  private double yCoord;

  public Location(String name, double xCoord, double yCoord) {
    if(DatabaseManagement.nameValidation(name)) {
      this.name = name;
    }
    this.xCoord = xCoord;
    this.yCoord = yCoord;
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

  public double getYCoord() {
    return this.yCoord;
  }

  public void setYCoord(double yCoord) {
    this.yCoord = yCoord;
  }


}
