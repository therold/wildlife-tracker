import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class SightingTest {
  RegularAnimal testAnimal;

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Before
  public void setUp() {
    testAnimal = new RegularAnimal("Rabbit");
    testAnimal.save();
  }

  // Instantiation
  @Test
  public void sighting_instantiatesCorrectly_true() {
    Sighting testSighting = new Sighting(testAnimal.getId(), 1, 1);
    assertTrue(testSighting instanceof Sighting);
  }

  @Test
  public void sighting_instantiatesWithoutId_0() {
    Sighting testSighting = new Sighting(testAnimal.getId(), 1, 1);
    assertEquals(0, testSighting.getId());
  }

  // animalId
  @Test
  public void sighting_instantiatesWithName_User() {
    Sighting testSighting = new Sighting(testAnimal.getId(), 1, 1);
    assertEquals(testAnimal.getId(), testSighting.getAnimalId());
  }

  @Test(expected = IllegalArgumentException.class)
  public void sighting_cannotInstantiateInvalidAnimalId_IllegalArgumentException() {
    Sighting testSighting = new Sighting(-1, 1, 1);
  }



}
