import org.sql2o.*;

public class Ranger {
  private int id;
  private String userName;
  private String firstName;
  private String lastName;
  private int badge;
  private long phone;

  public Ranger(String userName, String firstName, String lastName, int badge, long phone) {
    if(DatabaseManagement.nameValidation(userName)) {
      this.userName = userName;
    }
    if(DatabaseManagement.nameValidation(firstName)) {
      this.firstName = firstName;
    }
    if(DatabaseManagement.nameValidation(lastName)) {
      this.lastName = lastName;
    }
    this.badge = badge;
    this.phone = phone;
  }

  public int getId() {
    return this.id;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    if(DatabaseManagement.nameValidation(userName)) {
      this.userName = userName;
    }
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

  public long getPhone() {
    return this.phone;
  }

  public void setPhone(long phone) {
    this.phone = phone;
  }

  public void save() {
    if (Ranger.userNameExists(this.userName, this.id)) {
      throw new IllegalArgumentException("Error: Name already exists.");
    } else {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO rangers (username, firstname, lastname, badge, phone) VALUES (:username, :firstname, :lastname, :badge, :phone);";
        this.id = (int) con.createQuery(sql, true)
        .addParameter("username", this.userName)
        .addParameter("firstname", this.firstName)
        .addParameter("lastname", this.lastName)
        .addParameter("badge", this.badge)
        .addParameter("phone", this.phone)
        .executeUpdate()
        .getKey();
      }
    }
  }

  public void update() {
    if (Ranger.userNameExists(this.userName, this.id)) {
      throw new IllegalArgumentException("Error: Name already exists.");
    } else {
      try(Connection con = DB.sql2o.open()) {
        String sql = "UPDATE rangers SET username = :username, firstname = :firstname, lastname = :lastname, badge = :badge, phone = :phone WHERE id = :id;";
        con.createQuery(sql)
          .addParameter("id", this.id)
          .addParameter("username", this.userName)
          .addParameter("firstname", this.firstName)
          .addParameter("lastname", this.lastName)
          .addParameter("badge", this.badge)
          .addParameter("phone", this.phone)
          .executeUpdate();
      }
    }
  }

  public static boolean userNameExists(String userName, int id) {
    Integer count = 0;
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT count(username) FROM rangers WHERE username = :username AND id != :id;";
      count = con.createQuery(sql)
        .throwOnMappingFailure(false)
        .addParameter("username", userName)
        .addParameter("id", id)
        .executeScalar(Integer.class);
    }
    return count != 0;
  }

    public static Ranger find(int id) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM rangers WHERE id = :id;";
        return con.createQuery(sql)
          .throwOnMappingFailure(false)
          .addParameter("id", id)
          .executeAndFetchFirst(Ranger.class);
      }
    }


  @Override
  public boolean equals(Object otherObject) {
    if (!(otherObject instanceof Ranger)) {
      return false;
    } else {
      Ranger otherRanger = (Ranger) otherObject;
      return this.getId() == otherRanger.getId() &&
        this.getFirstName().equals(otherRanger.getFirstName()) &&
        this.getLastName().equals(otherRanger.getLastName()) &&
        this.getBadge() == otherRanger.getBadge() &&
        this.getPhone() == otherRanger.getPhone();
    }
  }

}
