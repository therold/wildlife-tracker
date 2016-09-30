import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class LocationTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  // Instantiation
  @Test
  public void ranger_instantiatesCorrectly_true() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    assertTrue(testLocation instanceof Location);
  }

  @Test
  public void ranger_instantiatesWithoutId_0() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    assertEquals(0, testLocation.getId());
  }

  // Name
  @Test
  public void ranger_instantiatesWithName_User() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    assertEquals("Near bridge", testLocation.getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void ranger_cannotInstantiateEmptyUserName_IllegalArgumentException() {
    Location testLocation = new Location("", 1.525, -2.311);
  }

  @Test
  public void setUserName_setsANewName_NewName() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.setName("NewName");
    assertEquals("NewName", testLocation.getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void setUserName_cannotSetEmptyName_IllegalArgumentException() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.setName("");
  }


  // xCoord
  @Test
  public void ranger_instantiatesWithXCoord_1_525() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    assertEquals(1.525, testLocation.getXCoord(), 0);
  }

  @Test
  public void setXCoord_setsANewXCoord_3_885() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.setXCoord(3.885);
    assertEquals(3.885, testLocation.getXCoord(), 0);
  }

  // yCoord
  @Test
  public void ranger_instantiatesWithYCoord_2_311() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    assertEquals(-2.311, testLocation.getYCoord(), 0);
  }

  @Test
  public void setYCoord_setsANewYCoord_4_243() {
    Location testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.setYCoord(4.243);
    assertEquals(4.243, testLocation.getYCoord(), 0);
  }

  //Other methods
  @Test
  public void equals_objectIsEqualIfAllPropertiesAreEqual_true() {
    Location firstLocation = new Location("Near bridge", 1.525, -2.311);
    Location secondLocation = new Location("Near bridge", 1.525, -2.311);
    assertTrue(firstLocation.equals(secondLocation));
  }


}
