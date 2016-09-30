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


}
