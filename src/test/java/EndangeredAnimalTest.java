import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

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

  @Test
  public void update_preservesOriginalName_Rhino() {
    Animal testAnimal = new EndangeredAnimal("Rhino");
    testAnimal.save();
    testAnimal.update();
    Animal savedAnimal = EndangeredAnimal.find(testAnimal.getId());
    assertEquals("Rhino", savedAnimal.getName());
  }

  @Test
  public void update_savesNewNameToDB_Panda() {
    Animal testAnimal = new EndangeredAnimal("Rabbit");
    testAnimal.save();
    testAnimal.setName("Panda");
    testAnimal.update();
    Animal savedAnimal = EndangeredAnimal.find(testAnimal.getId());
    assertEquals("Panda", savedAnimal.getName());
  }

  // Database methods
  @Test
  public void save_setsTheId_int() {
    Animal testAnimal = new EndangeredAnimal("Rhino");
    testAnimal.save();
    assertTrue(testAnimal.getId() > 0);
  }

  @Test
  public void save_insertsObjectIntoDB_true() {
    Animal testAnimal = new EndangeredAnimal("Rhino");
    testAnimal.save();
    Animal savedAnimal = EndangeredAnimal.find(testAnimal.getId());
    assertTrue(testAnimal.equals(savedAnimal));
  }

  @Test(expected = IllegalArgumentException.class)
  public void save_cannotSaveIfNameAlreadyExists_IllegalArgumentException() {
    Animal firstAnimal = new EndangeredAnimal("Rhino");
    firstAnimal.save();
    Animal secondAnimal = new EndangeredAnimal("Rhino");
    secondAnimal.save();
  }

  @Test
  public void update_preservesOriginalId_true() {
    Animal testAnimal = new EndangeredAnimal("Rhino");
    testAnimal.save();
    testAnimal.update();
    Animal savedAnimal = EndangeredAnimal.find(testAnimal.getId());
    assertEquals(testAnimal.getId(), savedAnimal.getId());
  }

  @Test(expected = IllegalArgumentException.class)
  public void update_cannotSaveIfNameAlreadyExists_IllegalArgumentException() {
    Animal firstAnimal = new EndangeredAnimal("Rhino");
    firstAnimal.save();
    Animal secondAnimal = new EndangeredAnimal("Panda");
    secondAnimal.save();
    secondAnimal.setName("Rhino");
    secondAnimal.update();
  }

  @Test
  public void delete_removesObjectFromDB_null() {
    Animal testAnimal = new EndangeredAnimal("Rhino");
    testAnimal.save();
    testAnimal.delete();
    Animal savedAnimal = EndangeredAnimal.find(testAnimal.getId());
    assertEquals(null, savedAnimal);
  }

  @Test
  public void all_getsAllObjectsFromDatabase_true() {
    EndangeredAnimal firstAnimal = new EndangeredAnimal("Rhino");
    firstAnimal.save();
    EndangeredAnimal secondAnimal = new EndangeredAnimal("Panda");
    secondAnimal.save();
    Animal[] expected = { firstAnimal, secondAnimal };
    assertTrue(EndangeredAnimal.all().containsAll(Arrays.asList(expected)));
  }


  //Other methods
  @Test
  public void equals_objectIsEqualIfAllPropertiesAreEqual_true() {
    Animal firstAnimal = new EndangeredAnimal("Rabbit");
    Animal secondAnimal = new EndangeredAnimal("Rabbit");
    assertTrue(firstAnimal.equals(secondAnimal));
  }


}
