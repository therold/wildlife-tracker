import java.util.Arrays;
import java.util.List;
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

  @Test
  public void setAnimalId_setsANewAnimalId_NewAnimalId() {
    Sighting testSighting = new Sighting(testAnimal.getId(), testLocation.getId(), testRanger.getId());
    RegularAnimal newAnimal = new RegularAnimal("Cat");
    newAnimal.save();
    testSighting.setAnimalId(newAnimal.getId());
    assertEquals(newAnimal.getId(), testSighting.getAnimalId());
  }

  @Test(expected = IllegalArgumentException.class)
  public void setAnimalId_cannotSetUnknown_IllegalArgumentException() {
    Sighting testSighting = new Sighting(testAnimal.getId(), testLocation.getId(), testRanger.getId());
    testSighting.setAnimalId(-1);
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

  @Test
  public void setLocationId_setsANewLocationId_NewLocationId() {
    Sighting testSighting = new Sighting(testAnimal.getId(), testLocation.getId(), testRanger.getId());
    Location newLocation = new Location("New Location", 1.525, -2.311);
    newLocation.save();
    testSighting.setLocationId(newLocation.getId());
    assertEquals(newLocation.getId(), testSighting.getLocationId());
  }

  @Test(expected = IllegalArgumentException.class)
  public void setLocationId_cannotSetUnknown_IllegalArgumentException() {
    Sighting testSighting = new Sighting(testAnimal.getId(), testLocation.getId(), testRanger.getId());
    testSighting.setLocationId(-1);
  }

  // rangerId
  @Test
  public void sighting_instantiatesWithRangerId_RangerId() {
    Sighting testSighting = new Sighting(testAnimal.getId(), testLocation.getId(), testRanger.getId());
    assertEquals(testRanger.getId(), testSighting.getRangerId());
  }

  @Test(expected = IllegalArgumentException.class)
  public void sighting_cannotInstantiateInvalidRangerId_IllegalArgumentException() {
    Sighting testSighting = new Sighting(testAnimal.getId(), testLocation.getId(), -1);
  }

  @Test
  public void setRangerId_setsANewRangerId_NewRangerId() {
    Sighting testSighting = new Sighting(testAnimal.getId(), testLocation.getId(), testRanger.getId());
    Ranger newRanger = new Ranger("NewUser", "Tommy", "Smith", 1, 5035550000L);
    newRanger.save();
    testSighting.setRangerId(newRanger.getId());
    assertEquals(newRanger.getId(), testSighting.getRangerId());
  }

  @Test(expected = IllegalArgumentException.class)
  public void setRangerId_cannotSetUnknown_IllegalArgumentException() {
    Sighting testSighting = new Sighting(testAnimal.getId(), testLocation.getId(), testRanger.getId());
    testSighting.setRangerId(-1);
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

  @Test
  public void update_preservesOriginalId_true() {
    Sighting testSighting = new Sighting(testAnimal.getId(), testLocation.getId(), testRanger.getId());
    testSighting.save();
    testSighting.update();
    Sighting savedSighting = Sighting.find(testSighting.getId());
    assertEquals(testSighting.getId(), savedSighting.getId());
  }

  @Test
  public void all_getsAllObjectsFromDatabase_true() {
    Sighting firstSighting = new Sighting(testAnimal.getId(), testLocation.getId(), testRanger.getId());
    firstSighting.save();
    Sighting secondSighting = new Sighting(testAnimal.getId(), testLocation.getId(), testRanger.getId());
    secondSighting.save();
    Sighting[] expected = { firstSighting, secondSighting };
    assertTrue(Sighting.all().containsAll(Arrays.asList(expected)));
  }


  @Test
  public void delete_removesObjectFromDB_null() {
    Sighting testSighting = new Sighting(testAnimal.getId(), testLocation.getId(), testRanger.getId());
    testSighting.save();
    testSighting.delete();
    Sighting savedSighting = Sighting.find(testSighting.getId());
    assertEquals(null, savedSighting);
  }


  //Other methods
  @Test
  public void equals_objectIsEqualIfAllPropertiesAreEqual_true() {
    Sighting firstSighting = new Sighting(testAnimal.getId(), testLocation.getId(), testRanger.getId());
    Sighting secondSighting = new Sighting(testAnimal.getId(), testLocation.getId(), testRanger.getId());
    assertTrue(firstSighting.equals(secondSighting));
  }




}
