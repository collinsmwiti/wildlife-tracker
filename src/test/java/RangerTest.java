// imports
import org.junit.*;
import static org.junit.Assert.*;

//rangertest class
public class RangerTest {

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
}
