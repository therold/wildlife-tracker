import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class AnimalTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void animal_instantiatesCorrectly_true() {
    Animal testAnimal = new Animal("Rabbit");
    assertTrue(testAnimal instanceof Animal);
  }

  @Test
  public void animal_instantiatesWithoutId_0() {
    Animal testAnimal = new Animal("Rabbit");
    assertEquals(0, testAnimal.getId());
  }

  // Name
  @Test
  public void animal_instantiatesWithName_Rabbit() {
    Animal testAnimal = new Animal("Rabbit");
    assertEquals("Rabbit", testAnimal.getName());
  }

  @Test
  public void setName_setsANewName_Goat() {
    Animal testAnimal = new Animal("Rabbit");
    testAnimal.setName("Goat");
    assertEquals("Goat", testAnimal.getName());
  }

  @Test
  public void equals_objectIsEqualIfAllPropertiesAreEqual_true() {
    Animal firstAnimal = new Animal("Rabbit");
    Animal secondAnimal = new Animal("Rabbit");
    assertTrue(firstAnimal.equals(secondAnimal));
  }


  @Test
  public void save_setsTheId_int() {
    Animal testAnimal = new Animal("Rabbit");
    testAnimal.save();
    assertTrue(testAnimal.getId() > 0);
  }

  @Test
  public void save_insertsObjectIntoDB_true() {
    Animal testAnimal = new Animal("Rabbit");
    testAnimal.save();
    Animal savedAnimal = Animal.find(testAnimal.getId());
    assertTrue(testAnimal.equals(savedAnimal));
  }

}
