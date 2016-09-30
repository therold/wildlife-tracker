import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class RangerTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  // Instantiation
  @Test
  public void ranger_instantiatesCorrectly_true() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    assertTrue(testRanger instanceof Ranger);
  }

  @Test
  public void ranger_instantiatesWithoutId_0() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    assertEquals(0, testRanger.getId());
  }

  // User Name
  @Test
  public void ranger_instantiatesWithName_User() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    assertEquals("User", testRanger.getUserName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void ranger_cannotInstantiateEmptyUserName_IllegalArgumentException() {
    Ranger testRanger = new Ranger("", "Bob", "Smith", 1, 5035550000L);
  }

  @Test
  public void setUserName_setsANewName_NewUser() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.setUserName("NewUser");
    assertEquals("NewUser", testRanger.getUserName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void setUserName_cannotSetEmptyName_IllegalArgumentException() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.setUserName("");
  }


  // First Name
  @Test
  public void ranger_instantiatesWithName_Bob() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    assertEquals("Bob", testRanger.getFirstName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void ranger_cannotInstantiateEmptyFirstName_IllegalArgumentException() {
    Ranger testRanger = new Ranger("User", "", "Smith", 1, 5035550000L);
  }

  @Test
  public void setFirstName_setsANewName_Tom() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.setFirstName("Tom");
    assertEquals("Tom", testRanger.getFirstName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void setName_cannotSetEmptyName_IllegalArgumentException() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.setFirstName("");
  }


  // Last Name
  @Test
  public void ranger_instantiatesWithName_Smith() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    assertEquals("Smith", testRanger.getLastName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void ranger_cannotInstantiateEmptyLastName_IllegalArgumentException() {
    Ranger testRanger = new Ranger("User", "Bob", "", 1, 5035550000L);
  }

  @Test
  public void setFirstName_setsANewName_Johnson() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.setLastName("Johnson");
    assertEquals("Johnson", testRanger.getLastName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void setLastName_cannotSetEmptyName_IllegalArgumentException() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.setLastName("");
  }


  // Badge
  @Test
  public void ranger_instantiatesWithBadge_1() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    assertEquals(1, testRanger.getBadge());
  }

  @Test
  public void setBadge_setsANewBadge_2() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.setBadge(2);
    assertEquals(2, testRanger.getBadge());
  }

  // Phone
  @Test
  public void ranger_instantiatesWithPhone_1() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    assertEquals(5035550000L, testRanger.getPhone());
  }

  @Test
  public void setBadge_setsANewPhone_3601234567() {
    Ranger testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.setPhone(3601234567L);
    assertEquals(3601234567L, testRanger.getPhone());
  }

  //Other methods
  @Test
  public void equals_objectIsEqualIfAllPropertiesAreEqual_true() {
    Ranger firstRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    Ranger secondRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    assertTrue(firstRanger.equals(secondRanger));
  }


}
