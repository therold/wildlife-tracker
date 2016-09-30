import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class RangerTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  // Instantiation
  @Test
  public void ranger_instantiatesCorrectly_true() {
    Ranger testRanger = new Ranger("Bob", "Smith", 1, 5035550000L);
    assertTrue(testRanger instanceof Ranger);
  }

  @Test
  public void ranger_instantiatesWithoutId_0() {
    Ranger testRanger = new Ranger("Bob", "Smith", 1, 5035550000L);
    assertEquals(0, testRanger.getId());
  }

  @Test(expected = IllegalArgumentException.class)
  public void ranger_cannotInstantiateEmptyFirstName_IllegalArgumentException() {
    Ranger testRanger = new Ranger("", "Smith", 1, 5035550000L);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ranger_cannotInstantiateEmptyLastName_IllegalArgumentException() {
    Ranger testRanger = new Ranger("Bob", "", 1, 5035550000L);
  }

}
