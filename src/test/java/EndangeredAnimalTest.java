import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class EndangeredAnimalTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  // Instantiation
  @Test
  public void endangeredAnimal_instantiatesCorrectly_true() {
    Animal testAnimal = new EndangeredAnimal("Rhino");
    assertTrue(testAnimal instanceof EndangeredAnimal);
  }

  @Test
  public void endangeredAnimal_instantiatesWithoutId_0() {
    Animal testAnimal = new EndangeredAnimal("Rhino");
    assertEquals(0, testAnimal.getId());
  }

  @Test(expected = IllegalArgumentException.class)
  public void endangeredAnimal_cannotInstantiateEmptyName_IllegalArgumentException() {
    Animal testAnimal = new EndangeredAnimal("");
    testAnimal.setName("");
  }

  // Name
  @Test
  public void endangeredAnimal_instantiatesWithName_Rhino() {
    Animal testAnimal = new EndangeredAnimal("Rhino");
    assertEquals("Rhino", testAnimal.getName());
  }

  @Test
  public void setName_setsANewName_Panda() {
    Animal testAnimal = new EndangeredAnimal("Rhino");
    testAnimal.setName("Panda");
    assertEquals("Panda", testAnimal.getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void setName_cannotSetEmptyName_IllegalArgumentException() {
    Animal testAnimal = new EndangeredAnimal("Rabbit");
    testAnimal.setName("");
  }

  @Test
  public void save_savesNameToDB_Rhino() {
    Animal testAnimal = new EndangeredAnimal("Rhino");
    testAnimal.save();
    Animal savedAnimal = EndangeredAnimal.find(testAnimal.getId());
    assertEquals("Rhino", savedAnimal.getName());
  }

  // Database methods
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

  @Test(expected = IllegalArgumentException.class)
  public void save_cannotSaveIfNameAlreadyExists_IllegalArgumentException() {
    Animal firstAnimal = new Animal("Rabbit");
    firstAnimal.save();
    Animal secondAnimal = new Animal("Rabbit");
    secondAnimal.save();
  }

  //Other methods
  @Test
  public void equals_objectIsEqualIfAllPropertiesAreEqual_true() {
    Animal firstAnimal = new Animal("Rabbit");
    Animal secondAnimal = new Animal("Rabbit");
    assertTrue(firstAnimal.equals(secondAnimal));
  }


}
