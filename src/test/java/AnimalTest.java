//imports
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.sql.Timestamp;
import java.util.Date;

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

  //test used to find animals with their ids
  @Test
  public void find_returnsAnimalWithSameId_secondAnimal() {
    Animal firstAnimal = new Animal("Lion", 1);
    firstAnimal.save();
    Animal secondAnimal = new Animal("Deer", 3);
    secondAnimal.save();
    assertEquals(Animal.find(secondAnimal.getId()), secondAnimal);
  }

  //test to create relationship between rangers and animals
  @Test
  public void save_savesRangerIdIntoDB_true() {
    Ranger testRanger = new Ranger("Sniper", "image", "sniper@sniper.com", 0700000000);
    testRanger.save();
    Animal testAnimal = new Animal("Lion", testRanger.getId());
    testAnimal.save();
    Animal savedAnimal = Animal.find(testAnimal.getId());
    assertEquals(savedAnimal.getRangerId(), testRanger.getId());
  }

  //test to check the animal's health levels
  @Test
  public void animal_instantiatesWithHealthLevel() {
    Animal testAnimal = new Animal("Lion", 1);
    assertEquals(testAnimal.getHealthLevel(), (Animal.MIN_HEALTH_LEVEL));
  }

  //test to check an animals age levels
  @Test
  public void animal_instantiatesWithAgeLevel() {
    Animal testAnimal = new Animal("Lion", 1);
    assertEquals(testAnimal.getAgeLevel(), (Animal.MIN_AGE_LEVEL));
  }

  //test to notify if the animal is dead or alive
  @Test
  public void isAlive_confirmsAnimalIsAliveIfAllLevelsAboveMinimum_true(){
   Animal testAnimal = new Animal("Lion", 1);
   assertEquals(testAnimal.isAlive(), true);
 }

 //test to determine accurately if the animal is dead
 public void depleteLevels_reducesAllLevels() {
   Animal testAnimal = new Animal("Lion", 1);
   testAnimal.depleteLevels();
   assertEquals(testAnimal.getHealthLevel(), (Animal.MIN_HEALTH_LEVEL)- 1);
   assertEquals(testAnimal.getAgeLevel(), (Animal.MIN_AGE_LEVEL) - 1);
 }

 //test to recognize if an animal is dead when its level reaches minimum
 @Test
 public void isAlive_recognizesAnimalIsDeadWhenLevelIsReachMinimum_false() {
   Animal testAnimal = new Animal("Lion", 1);
   for(int i = Animal.MIN_ALL_LEVELS; i <= Animal.MIN_HEALTH_LEVEL; i++) {
     testAnimal.depleteLevels();
   }
   assertEquals(testAnimal.isAlive(), false);
 }

 //test to increase health levels if an animal is in a condusive environment
 @Test
 public void health_increasesAnimalHealthLevel() {
   Animal testAnimal = new Animal("Lion", 1);
   testAnimal.health();
   assertTrue(testAnimal.getHealthLevel() > (Animal.MIN_HEALTH_LEVEL));
 }

 //test to increase age levels of an animal if the environment does not affect the animal negatively
  @Test
  public void age_increasesAnimalAgeLevel() {
    Animal testAnimal = new Animal("Lion", 1);
    testAnimal.age();
    assertTrue(testAnimal.getAgeLevel() > (Animal.MIN_AGE_LEVEL));
  }

  //test to save animals date of birthday
  @Test
  public void save_recordsTimeOfCreationInDatabase() {
    Animal testAnimal = new Animal("Lion", 1);
    testAnimal.save();
    Timestamp savedAnimalBirthday = Animal.find(testAnimal.getId()).getBirthday();
    Timestamp rightNow = new Timestamp(new Date().getTime());
    assertEquals(rightNow.getDay(), savedAnimalBirthday.getDay());
  }

}
