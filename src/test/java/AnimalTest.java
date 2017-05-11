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

  // used to save animals in the database
  @Test
  public void save_returnsTrueIfDescriptionsAretheSame() {
    Animal testAnimal = new Animal("Lion", 1);
    testAnimal.save();
    assertTrue(Animal.all().get(0).equals(testAnimal));
  }

  //assigning id to the animals
  @Test
  public void save_assignsIdToAnimal() {
    Animal testAnimal = new Animal("Lion", 1);
    testAnimal.save();
    Animal savedAnimal = Animal.all().get(0);
    assertEquals(savedAnimal.getId(), testAnimal.getId());
  }

  // test to return all instances of animals as true
  @Test
  public void all_returnsAllInstancesOfAnimal_true() {
    Animal firstAnimal = new Animal("Lion", 1);
    firstAnimal.save();
    Animal secondAnimal = new Animal("Deer", 1);
    secondAnimal.save();
    assertEquals(true, Animal.all().get(0).equals(firstAnimal));
    assertEquals(true, Animal.all().get(1).equals(secondAnimal));
  }
}
