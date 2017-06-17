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

// set a new regula animal name
  @Test
  public void setName_setsANewName_Goat() {
    RegularAnimal testAnimal = new RegularAnimal("Rabbit");
    testAnimal.setName("Goat");
    assertEquals("Goat", testAnimal.getName());
  }

//throws an exception if the ranger tries to set an empty name of the regular animal
  @Test(expected = IllegalArgumentException.class)
  public void setName_cannotSetEmptyName_IllegalArgumentException() {
    RegularAnimal testAnimal = new RegularAnimal("Rabbit");
    testAnimal.setName("");
  }

//saves the name of the regular animal
  @Test
  public void save_savesNameToDB_Rabbit() {
    RegularAnimal testAnimal = new RegularAnimal("Rabbit");
    testAnimal.save();
    RegularAnimal savedRegularAnimal = RegularAnimal.find(testAnimal.getId());
    assertEquals("Rabbit", savedRegularAnimal.getName());
  }

//updates the name of the regular animal
  @Test
  public void update_preservesOriginalName_Rabbit() {
    RegularAnimal testAnimal = new RegularAnimal("Rabbit");
    testAnimal.save();
    testAnimal.update();
    RegularAnimal savedRegularAnimal = RegularAnimal.find(testAnimal.getId());
    assertEquals("Rabbit", savedRegularAnimal.getName());
  }

//updates the new regular animal name
  @Test
  public void update_savesNewNameToDB_Goat() {
    RegularAnimal testAnimal = new RegularAnimal("Rabbit");
    testAnimal.save();
    testAnimal.setName("Goat");
    testAnimal.update();
    RegularAnimal savedRegularAnimal = RegularAnimal.find(testAnimal.getId());
    assertEquals("Goat", savedRegularAnimal.getName());
  }




















}
