import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class SightingTest {
  private RegularAnimal testAnimal;
  private Location testLocation;
  private Ranger testRanger;

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Before
  public void setUp() {
    testAnimal = new RegularAnimal("Rabbit");
    testAnimal.save();
    testLocation = new Location("Near bridge", 1.525, -2.311);
    testLocation.save();
    testRanger = new Ranger("User", "Bob", "Smith", 1, 5035550000L);
    testRanger.save();
  }

  // Instantiation
  @Test
  public void sighting_instantiatesCorrectly_true() {
    Sighting testSighting = new Sighting(testAnimal.getId(), testLocation.getId(), testRanger.getId());
    assertTrue(testSighting instanceof Sighting);
  }

  @Test
  public void sighting_instantiatesWithoutId_0() {
    Sighting testSighting = new Sighting(testAnimal.getId(), testLocation.getId(), testRanger.getId());
    assertEquals(0, testSighting.getId());
  }

  // animalId
  @Test
  public void sighting_instantiatesWithAnimalId_AnimalId() {
    Sighting testSighting = new Sighting(testAnimal.getId(), testLocation.getId(), testRanger.getId());
    assertEquals(testAnimal.getId(), testSighting.getAnimalId());
  }

  @Test(expected = IllegalArgumentException.class)
  public void sighting_cannotInstantiateInvalidAnimalId_IllegalArgumentException() {
    Sighting testSighting = new Sighting(-1, testLocation.getId(), testRanger.getId());
  }

  // locationId
  @Test
  public void sighting_instantiatesWithLocationId_LocationId() {
    Sighting testSighting = new Sighting(testAnimal.getId(), testLocation.getId(), testRanger.getId());
    assertEquals(testLocation.getId(), testSighting.getLocationId());
  }

  @Test(expected = IllegalArgumentException.class)
  public void sighting_cannotInstantiateInvalidLocationId_IllegalArgumentException() {
    Sighting testSighting = new Sighting(testAnimal.getId(), -1, testRanger.getId());
  }

  // locationId
  @Test
  public void sighting_instantiatesWithRangerId_RangerId() {
    Sighting testSighting = new Sighting(testAnimal.getId(), testLocation.getId(), testRanger.getId());
    assertEquals(testRanger.getId(), testSighting.getRangerId());
  }

  @Test(expected = IllegalArgumentException.class)
  public void sighting_cannotInstantiateInvalidRangerId_IllegalArgumentException() {
    Sighting testSighting = new Sighting(testAnimal.getId(), testLocation.getId(), -1);
  }

  // Database methods
  @Test
  public void save_setsTheId_int() {
    Sighting testSighting = new Sighting(testAnimal.getId(), testLocation.getId(), testRanger.getId());
    testSighting.save();
    assertTrue(testSighting.getId() > 0);
  }

  @Test
  public void save_insertsObjectIntoDB_true() {
    Sighting testSighting = new Sighting(testAnimal.getId(), testLocation.getId(), testRanger.getId());
    testSighting.save();
    Sighting savedSighting = Sighting.find(testSighting.getId());
    assertTrue(testSighting.equals(savedSighting));
  }

  //Other methods
  @Test
  public void equals_objectIsEqualIfAllPropertiesAreEqual_true() {
    Sighting firstSighting = new Sighting(testAnimal.getId(), testLocation.getId(), testRanger.getId());
    Sighting secondSighting = new Sighting(testAnimal.getId(), testLocation.getId(), testRanger.getId());
    assertTrue(firstSighting.equals(secondSighting));
  }




}
