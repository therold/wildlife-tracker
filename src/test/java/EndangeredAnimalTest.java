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
  public void animal_instantiatesWithoutId_0() {
    Animal testAnimal = new EndangeredAnimal("Rhino");
    assertEquals(0, testAnimal.getId());
  }

  @Test(expected = IllegalArgumentException.class)
  public void animal_cannotInstantiateEmptyName_IllegalArgumentException() {
    Animal testAnimal = new EndangeredAnimal("");
    testAnimal.setName("");
  }


}
