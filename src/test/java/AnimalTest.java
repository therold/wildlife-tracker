import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class AnimalTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  // Instantiation
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


    @Test(expected = IllegalArgumentException.class)
    public void animal_cannotInstantiateEmptyName_IllegalArgumentException() {
      Animal testAnimal = new Animal("");
      testAnimal.setName("");
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

  @Test(expected = IllegalArgumentException.class)
  public void setName_cannotSetEmptyName_IllegalArgumentException() {
    Animal testAnimal = new Animal("Rabbit");
    testAnimal.setName("");
  }

  @Test
  public void save_savesNameToDB_Rabbit() {
    Animal testAnimal = new Animal("Rabbit");
    testAnimal.save();
    Animal savedAnimal = Animal.find(testAnimal.getId());
    assertEquals("Rabbit", savedAnimal.getName());
  }

  @Test
  public void update_preservesOriginalName_Rabbit() {
    Animal testAnimal = new Animal("Rabbit");
    testAnimal.save();
    testAnimal.update();
    Animal savedAnimal = Animal.find(testAnimal.getId());
    assertEquals("Rabbit", savedAnimal.getName());
  }

  @Test
  public void update_savesNewNameToDB_Goat() {
    Animal testAnimal = new Animal("Rabbit");
    testAnimal.save();
    testAnimal.setName("Goat");
    testAnimal.update();
    Animal savedAnimal = Animal.find(testAnimal.getId());
    assertEquals("Goat", savedAnimal.getName());
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

  @Test
  public void delete_removesObjectFromDB_null() {
    Animal testAnimal = new Animal("Rabbit");
    testAnimal.save();
    testAnimal.delete();
    Animal savedAnimal = Animal.find(testAnimal.getId());
    assertEquals(null, savedAnimal);
  }

  @Test
  public void update_preservesOriginalId_true() {
    Animal testAnimal = new Animal("Rabbit");
    testAnimal.save();
    testAnimal.update();
    Animal savedAnimal = Animal.find(testAnimal.getId());
    assertEquals(testAnimal.getId(), savedAnimal.getId());
  }

  @Test(expected = IllegalArgumentException.class)
  public void update_cannotSaveIfNameAlreadyExists_IllegalArgumentException() {
    Animal firstAnimal = new Animal("Rabbit");
    firstAnimal.save();
    Animal secondAnimal = new Animal("Goat");
    secondAnimal.save();
    secondAnimal.setName("Rabbit");
    secondAnimal.update();
  }

  @Test
  public void all_getsAllObjectsFromDatabase_true() {
    Animal firstAnimal = new Animal("Rabbit");
    firstAnimal.save();
    Animal secondAnimal = new Animal("Goat");
    secondAnimal.save();
    Animal[] expected = { firstAnimal, secondAnimal };
    assertTrue(Animal.all().containsAll(Arrays.asList(expected)));
  }

  @Test
  public void search_returnsNothingForUnknownValue_emptyList() {
    Animal firstAnimal = new Animal("Rabbit");
    firstAnimal.save();
    Animal secondAnimal = new Animal("Goat");
    secondAnimal.save();
    List<Animal> foundAnimals = Animal.search("fox");
    assertEquals(Collections.<Animal>emptyList(), foundAnimals);
  }

  @Test
  public void search_returnsAllMatchingObjects_true() {
    Animal firstAnimal = new Animal("Bobcat");
    firstAnimal.save();
    Animal secondAnimal = new Animal("House Cat");
    secondAnimal.save();
    Animal thirdAnimal = new Animal("Rabbit");
    thirdAnimal.save();
    List<Animal> foundAnimals = Animal.search("cat");
    Animal[] expected = { firstAnimal, secondAnimal };
    assertEquals(Arrays.asList(expected), foundAnimals);
  }

  //Other methods
  @Test
  public void equals_objectIsEqualIfAllPropertiesAreEqual_true() {
    Animal firstAnimal = new Animal("Rabbit");
    Animal secondAnimal = new Animal("Rabbit");
    assertTrue(firstAnimal.equals(secondAnimal));
  }

}
