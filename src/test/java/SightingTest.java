import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class SightingTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  // Instantiation
  @Test
  public void sighting_instantiatesCorrectly_true() {
    Sighting testSighting = new Sighting(1, 1, 1);
    assertTrue(testSighting instanceof Sighting);
  }

  @Test
  public void ranger_instantiatesWithoutId_0() {
    Sighting testSighting = new Sighting(1, 1, 1);
    assertEquals(0, testSighting.getId());
  }


}
