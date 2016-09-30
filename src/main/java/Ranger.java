public class Ranger {
  private int id;
  private String firstName;
  private String lastName;
  private int badge;

  public Ranger(String firstName, String lastName, int badge, long phone) {
    if(DatabaseManagement.nameValidation(firstName)) {
      this.firstName = firstName;
    }
    if(DatabaseManagement.nameValidation(lastName)) {
      this.lastName = lastName;
    }
    this.badge = badge;

  }

  public int getId() {
    return this.id;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    if(DatabaseManagement.nameValidation(firstName)) {
      this.firstName = firstName;
    }
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    if(DatabaseManagement.nameValidation(lastName)) {
      this.lastName = lastName;
    }
  }

  public int getBadge() {
    return this.badge;
  }

  public void setBadge(int badge) {
    this.badge = badge;
  }

}
