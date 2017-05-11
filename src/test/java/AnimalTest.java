//imports
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

//class test animal
public class AnimalTest {

  //rules used to add functionality before tests takes place to enable DRY
  @Rule
  public DatabaseRule database = new DatabaseRule();

  //test used to instantiate an animal correctly
  @Test
  public void animal_instantiatesCorrectly_true() {
    Animal testAnimal = new Animal("Lion", 1);
    assertEquals(true, testAnimal instanceof Animal);
  }

  //test to instantiate an animal with a name
  @Test
  public void Animal_instantiatesWithName_String() {
    Animal testAnimal = new Animal("Lion", 1);
    assertEquals("Lion", testAnimal.getName());
  }

  //test to instantiate an animal with the ranger id
  public void Animal_instantiatesWithRangerId_int() {
    Animal testAnimal = new Animal("Lion", 1);
    assertEquals(1, testAnimal.getRangerId());
  }

  //test for returning true if the animal name corresponding to the ranger's id are same
  @Test
  public void equals_returnsTrueIfNameAndPersonIdAreSame_true() {
    Animal testAnimal = new Animal("Lion", 1);
    Animal anotherAnimal = new Animal("Lion", 1);
    assertTrue(testAnimal.equals(anotherAnimal));
  }
}
