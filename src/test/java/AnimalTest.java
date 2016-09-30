import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class AnimalTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void animal_instantiatesCorrectly_true() {
    Animal animal = new Animal("Rabbit");
    assertTrue(animal instanceof Animal);
  }

  // Name
  @Test
  public void animal_instantiatesWithName_Rabbit() {
    Animal animal = new Animal("Rabbit");
    assertEquals("Rabbit", animal.getName());
  }

}
