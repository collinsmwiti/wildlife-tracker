// imports
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

//rangertest class
public class RangerTest {

  //adding rule functionality within the test
  @Rule
  public DatabaseRule database = new DatabaseRule();

  // if a ranger is truly instantiated
  @Test
  public void ranger_instantiatesCorrectly_true() {
    Ranger testRanger = new Ranger("Sniper", "image", "[email protected]", 0700000000);
    assertEquals(true, testRanger instanceof Ranger);
  }

  //test to get the name
  @Test
  public void getName_rangerInstantiatesWithName_Sniper() {
    Ranger testRanger = new Ranger("Sniper", "image", "[email protected]", 0700000000);
    assertEquals("Sniper", testRanger.getName());
  }

  //test to get the image
  @Test
  public void getImage_rangerInstantiatesWithImage_String() {
    Ranger testRanger = new Ranger("Sniper", "image", "[email protected]", 0700000000);
    assertEquals("image", testRanger.getImage());
  }

  // test to get the email
  @Test
  public void getEmail_rangerInstantiatesWithEmail_String() {
    Ranger testRanger = new Ranger("Sniper", "image", "[email protected]", 0700000000);
    assertEquals("[email protected]", testRanger.getEmail());
  }

  //test for ranger's contacts
  @Test
  public void getContacts_rangerInstantiatesWithContacts_Int() {
    Ranger testRanger = new Ranger("Sniper", "image", "[email protected]", 0700000000);
    assertEquals(0700000000, testRanger.getContacts());
  }

  // test to check if the rangers details are same and true
  @Test
  public void equals_returnsTrueIfNameImageEmailAndContactsAreSame_true() {
  Ranger firstRanger = new Ranger("Sniper", "image", "[email protected]", 0700000000);
  Ranger anotherRanger = new Ranger("Sniper", "image", "[email protected]", 0700000000);
  assertTrue(firstRanger.equals(anotherRanger));
  }

  //test to save a ranger in the database
  @Test
  public void save_insertsObjectIntoDatabase_Ranger() {
    Ranger testRanger = new Ranger("Sniper", "image", "[email protected]", 0700000000);
    testRanger.save();
    assertTrue(Ranger.all().get(0).equals(testRanger));
  }

  // test to return all database entries
  @Test
  public void all_returnsAllInstancesOfRanger_true() {
    Ranger firstRanger = new Ranger("Sniper", "image", "sniper@sniper.com", 0700000000);
    firstRanger.save();
    Ranger secondRanger = new Ranger("Drone", "image", "drone@drone.com", 0700000001);
    secondRanger.save();
    assertEquals(true, Ranger.all().get(0).equals(firstRanger));
    assertEquals(true, Ranger.all().get(1).equals(secondRanger));
  }

  // test to assign id to rangers
  @Test
  public void save_assignsIdToObject() {
    Ranger testRanger = new Ranger("Sniper", "image", "sniper@sniper.com", 0700000000);
    testRanger.save();
    Ranger savedRanger = Ranger.all().get(0);
    assertEquals(testRanger.getId(), savedRanger.getId());
  }

  //test to find rangers based on their id
  @Test
  public void find_returnsRangerWithSameId_secondRanger() {
    Ranger firstRanger = new Ranger("Sniper", "image", "sniper@sniper.com", 0700000000);
    firstRanger.save();
    Ranger secondRanger = new Ranger("Drone", "image", "drone@drone.com", 0700000001);
    secondRanger.save();
    assertEquals(Ranger.find(secondRanger.getId()), secondRanger);
  }
}
