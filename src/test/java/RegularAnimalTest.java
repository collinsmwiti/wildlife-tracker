//imports
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.Timestamp;

//class RegularAnimalTest
public class RegularAnimalTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  // Is true if the animal is instantiated correctly
  @Test
  public void animal_instantiatesCorrectly_true() {
    RegularAnimal testAnimal = new RegularAnimal("Rabbit");
    assertTrue(testAnimal instanceof RegularAnimal);
  }

//new regular animals should inatantiate without an id
  @Test
  public void animal_instantiatesWithoutId_0() {
    RegularAnimal testAnimal = new RegularAnimal("Rabbit");
    assertEquals(0, testAnimal.getId());
  }

//throws an exception if the ranger tries to instantiate a regular animal with an empty name
  @Test(expected = IllegalArgumentException.class)
  public void animal_cannotInstantiateEmptyName_IllegalArgumentException() {
    RegularAnimal testAnimal = new RegularAnimal("");
  }

  // regular animal should be instantiated with the name
  @Test
  public void animal_instantiatesWithName_Rabbit() {
    RegularAnimal testAnimal = new RegularAnimal("Rabbit");
    assertEquals("Rabbit", testAnimal.getName());
  }

// set a new regular animal name
  @Test
  public void setName_setsANewName_Goat() {
    RegularAnimal testAnimal = new RegularAnimal("Rabbit");
    testAnimal.setName("Goat");
    assertEquals("Goat", testAnimal.getName());
  }






















}
