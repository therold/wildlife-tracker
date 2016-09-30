public class Animal implements DatabaseManagement {
  private String name;

  public Animal(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void save() {
    //TODO
  }

  public void update() {
    //TODO
  }

  public void delete() {
    //TODO
  }

  @Override
  public boolean equals(Object otherObject) {
    //TODO
    return false;
  }

}
