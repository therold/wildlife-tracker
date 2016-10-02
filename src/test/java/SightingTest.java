import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class SightingTest {
  private RegularAnimal testAnimal;
  private Location testLocation;

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Before
  public void setUp() {
    testAnimal = new RegularAnimal("Rabbit");
    testAnimal.save();
    testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.save();
  }

  // Instantiation
  @Test
  public void sighting_instantiatesCorrectly_true() {
    Sighting testSighting = new Sighting(testAnimal.getId(), testLocation.getId(), 1);
    assertTrue(testSighting instanceof Sighting);
  }

  @Test
  public void sighting_instantiatesWithoutId_0() {
    Sighting testSighting = new Sighting(testAnimal.getId(), testLocation.getId(), 1);
    assertEquals(0, testSighting.getId());
  }

  // animalId
  @Test
  public void sighting_instantiatesWithAnimalId_AnimalId() {
    Sighting testSighting = new Sighting(testAnimal.getId(), testLocation.getId(), 1);
    assertEquals(testAnimal.getId(), testSighting.getAnimalId());
  }

  @Test(expected = IllegalArgumentException.class)
  public void sighting_cannotInstantiateInvalidAnimalId_IllegalArgumentException() {
    Sighting testSighting = new Sighting(-1, testLocation.getId(), 1);
  }

  // locationId
  @Test
  public void sighting_instantiatesWithLocationId_LocationId() {
    Sighting testSighting = new Sighting(testAnimal.getId(), testLocation.getId(), 1);
    assertEquals(testLocation.getId(), testSighting.getLocationId());
  }

  @Test(expected = IllegalArgumentException.class)
  public void sighting_cannotInstantiateInvalidLocationId_IllegalArgumentException() {
    Sighting testSighting = new Sighting(testAnimal.getId(), -1, 1);
  }



}
