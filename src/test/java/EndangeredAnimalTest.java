//imports
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.sql.Timestamp;
import java.util.Date;
import java.text.DateFormat;

//class test EndangeredAnimal
public class EndangeredAnimalTest {

  //rules used to add functionality before tests takes place to enable DRY
  @Rule
  public DatabaseRule database = new DatabaseRule();

  //test used to instantiate an EndangeredAnimal correctly
  @Test
  public void endangeredAnimal_instantiatesCorrectly_true() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", 1);
    assertEquals(true, testEndangeredAnimal instanceof EndangeredAnimal);
  }

  //test to instantiate an EndangeredAnimal with a name
  @Test
  public void endangeredAnimal_instantiatesWithName_String() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", 1);
    assertEquals("Lion", testEndangeredAnimal.getName());
  }

  //test to instantiate an EndangeredAnimal with the ranger id
  public void endangeredAnimal_instantiatesWithRangerId_int() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", 1);
    assertEquals(1, testEndangeredAnimal.getRangerId());
  }

  //test for returning true if the EndangeredAnimal name corresponding to the ranger's id are same
  @Test
  public void equals_returnsTrueIfNameAndPersonIdAreSame_true() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", 1);
    EndangeredAnimal anotherEndangeredAnimal = new EndangeredAnimal("Lion", 1);
    assertTrue(testEndangeredAnimal.equals(anotherEndangeredAnimal));
  }

  // used to save EndangeredAnimals in the database
  @Test
  public void save_returnsTrueIfDescriptionsAretheSame() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", 1);
    testEndangeredAnimal.save();
    assertTrue(EndangeredAnimal.all().get(0).equals(testEndangeredAnimal));
  }

  //assigning id to the EndangeredAnimals
  @Test
  public void save_assignsIdToEndangeredAnimal() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", 1);
    testEndangeredAnimal.save();
    EndangeredAnimal savedEndangeredAnimal = EndangeredAnimal.all().get(0);
    assertEquals(savedEndangeredAnimal.getId(), testEndangeredAnimal.getId());
  }

  // test to return all instances of EndangeredAnimals as true
  @Test
  public void all_returnsAllInstancesOfEndangeredAnimal_true() {
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Lion", 1);
    firstEndangeredAnimal.save();
    EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Deer", 1);
    secondEndangeredAnimal.save();
    assertEquals(true, EndangeredAnimal.all().get(0).equals(firstEndangeredAnimal));
    assertEquals(true, EndangeredAnimal.all().get(1).equals(secondEndangeredAnimal));
  }

  //test used to find EndangeredAnimals with their ids
  @Test
  public void find_returnsEndangeredAnimalWithSameId_secondEndangeredAnimal() {
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Lion", 1);
    firstEndangeredAnimal.save();
    EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Deer", 3);
    secondEndangeredAnimal.save();
    assertEquals(EndangeredAnimal.find(secondEndangeredAnimal.getId()), secondEndangeredAnimal);
  }

  //test to create relationship between rangers and EndangeredAnimals
  @Test
  public void save_savesRangerIdIntoDB_true() {
    Ranger testRanger = new Ranger("Sniper", "image", "sniper@sniper.com", 0700000000);
    testRanger.save();
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", testRanger.getId());
    testEndangeredAnimal.save();
    EndangeredAnimal savedEndangeredAnimal = EndangeredAnimal.find(testEndangeredAnimal.getId());
    assertEquals(savedEndangeredAnimal.getRangerId(), testRanger.getId());
  }

  //test to check the EndangeredAnimal's health levels
  @Test
  public void endangeredAnimal_instantiatesWithHealthLevel() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", 1);
    assertEquals(testEndangeredAnimal.getHealthLevel(), (EndangeredAnimal.MIN_HEALTH_LEVEL));
  }

  //test to check an EndangeredAnimal age levels
  @Test
  public void endangeredAnimal_instantiatesWithAgeLevel() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", 1);
    assertEquals(testEndangeredAnimal.getAgeLevel(), (EndangeredAnimal.MIN_AGE_LEVEL));
  }

  //test to notify if the EndangeredAnimal is dead or alive
  @Test
  public void isAlive_confirmsEndangeredAnimalIsAliveIfAllLevelsAboveMinimum_true(){
   EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", 1);
   assertEquals(testEndangeredAnimal.isAlive(), true);
 }

 //test to determine accurately if the EndangeredAnimal is dead
 public void depleteLevels_reducesAllLevels() {
   EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", 1);
   testEndangeredAnimal.depleteLevels();
   assertEquals(testEndangeredAnimal.getHealthLevel(), (EndangeredAnimal.MIN_HEALTH_LEVEL)- 1);
   assertEquals(testEndangeredAnimal.getAgeLevel(), (EndangeredAnimal.MIN_AGE_LEVEL) - 1);
 }

 //test to recognize if an EndangeredAnimal is dead when its level reaches minimum
 @Test
 public void isAlive_recognizesEndangeredAnimalIsDeadWhenLevelIsReachMinimum_false() {
   EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", 1);
   for(int i = EndangeredAnimal.MIN_ALL_LEVELS; i <= EndangeredAnimal.MIN_HEALTH_LEVEL; i++) {
     testEndangeredAnimal.depleteLevels();
   }
   assertEquals(testEndangeredAnimal.isAlive(), false);
 }

 //test to increase health levels if an EndangeredAnimal is in a condusive environment
 @Test
 public void health_increasesEndangeredAnimalHealthLevel() {
   EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", 1);
   testEndangeredAnimal.health();
   assertTrue(testEndangeredAnimal.getHealthLevel() > (EndangeredAnimal.MIN_HEALTH_LEVEL));
 }

 //test to increase age levels of an EndangeredAnimal if the environment does not affect the EndangeredAnimal negatively
  @Test
  public void age_increasesEndangeredAnimalAgeLevel() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", 1);
    testEndangeredAnimal.age();
    assertTrue(testEndangeredAnimal.getAgeLevel() > (EndangeredAnimal.MIN_AGE_LEVEL));
  }

  //test to save EndangeredAnimal date of birth
  @Test
  public void save_recordsTimeOfCreationInDatabase() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", 1);
    testEndangeredAnimal.save();
    Timestamp savedEndangeredAnimalBirthday = EndangeredAnimal.find(testEndangeredAnimal.getId()).getBirthday();
    Timestamp rightNow = new Timestamp(new Date().getTime());
    assertEquals(rightNow.getDay(), savedEndangeredAnimalBirthday.getDay());
  }

  //test to determine the health of an EndangeredAnimal
  @Test
  public void health_recordsTimeLastHealthInDatabase() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", 1);
    testEndangeredAnimal.save();
    testEndangeredAnimal.health();
    Timestamp savedEndangeredAnimalLastHealth = EndangeredAnimal.find(testEndangeredAnimal.getId()).getLastHealth();
    Timestamp rightNow = new Timestamp(new Date().getTime());
    assertEquals(DateFormat.getDateTimeInstance().format(rightNow), DateFormat.getDateTimeInstance().format(savedEndangeredAnimalLastHealth));
  }

  //test to check the last age of an EndangeredAnimal
  @Test
  public void age_recordsTimeLastAgeInDatabase() {
    EndangeredAnimal testEndangeredAnimal = new EndangeredAnimal("Lion", 1);
    testEndangeredAnimal.save();
    testEndangeredAnimal.age();
    Timestamp savedEndangeredAnimalLastAge = EndangeredAnimal.find(testEndangeredAnimal.getId()).getLastAge();
    Timestamp rightNow = new Timestamp(new Date().getTime());
    assertEquals(DateFormat.getDateTimeInstance().format(rightNow), DateFormat.getDateTimeInstance().format(savedEndangeredAnimalLastAge));
  }

}
