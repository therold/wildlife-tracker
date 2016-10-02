import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class CreateObjectsRule extends ExternalResource {

  @Override
  protected void before() {
    RegularAnimal testAnimal = new RegularAnimal("Rabbit");
    testAnimal.save();
  }
}
