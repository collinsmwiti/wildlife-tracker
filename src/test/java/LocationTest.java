//imports
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

//class LocationTest
public class LocationTest {

  //rules to be set which is an added functionality to the tests
  @Rule
  public DatabaseRule database = new DatabaseRule();

  //test to instantiate the location correctly
  @Test
  public void location_instantiatesCorrectly_true() {
    Location testLocation = new Location("image", "Forest", 1, "Sniper");
    assertEquals(true, testLocation instanceof Location);
  }

  //test to get the image of the Location
  @Test
  public void getImage_locationInstantiatesWithImage_true() {
    Location testLocation = new Location("image", "Forest", 1, "Sniper");
    assertEquals("image", testLocation.getImage());
  }

  //test to get the location name
  @Test
  public void getLocationName_locationInstantiatesWithLocationName_true() {
    Location testLocation = new Location("image", "Forest", 1, "Sniper");
    assertEquals("Forest", testLocation.getLocationName());
  }

  //test to get the EndangeredAnimal id
  @Test
  public void getEndangeredAnimalId_locationInstantiatesWithEndangeredAnimalId_true() {
    Location testLocation = new Location("image", "Forest", 1, "Sniper");
    assertEquals(1, testLocation.getEndangeredAnimalId());
  }

  //test to get the ranger's name
  @Test
  public void getRangerName_locationInstantiatesWithRangerName_true() {
    Location testLocation = new Location("image", "Forest", 1, "Sniper");
    assertEquals("Sniper", testLocation.getRangerName());
  }

  //test to return true if location description is the same
  @Test
  public void equals_returnsTrueIfImageLocationNameEndangeredAnimalIdRangerNameAreSame_true() {
    Location testLocation = new Location("image", "Forest", 1, "Sniper");
    Location anotherLocation = new Location("image", "Forest", 1, "Sniper");
    assertTrue(testLocation.equals(anotherLocation));
  }

  //test used for saving our data into the database
  @Test
  public void save_insertsObjectIntoDatabase_Location() {
    Location testLocation = new Location("image", "Forest", 1, "Sniper");
    testLocation.save();
    assertEquals(true, Location.all().get(0).equals(testLocation));
  }

  //test to return all instances as true
  @Test
  public void all_returnsAllInstancesOfLocation_true() {
    Location firstLocation = new Location("image", "Forest", 1, "Sniper");
    firstLocation.save();
    Location secondLocation = new Location("image", "Forest", 1, "Sniper");
    secondLocation.save();
    assertEquals(true, Location.all().get(0).equals(firstLocation));
    assertEquals(true, Location.all().get(1).equals(secondLocation));
  }
}
