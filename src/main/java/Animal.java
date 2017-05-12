//imports
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

//class Animal
public abstract class Animal {
  public String name;
  public int rangerId;
  public int id;
  //constants
  public int healthLevel;
  public int ageLevel;

  public static final int MIN_HEALTH_LEVEL = 1;
  public static final int MIN_AGE_LEVEL = 1;
  public static final int MIN_ALL_LEVELS = 0;

  public Timestamp birthday;
  public Timestamp lastHealth;
  public Timestamp lastAge;
  public String type;

  //getter methods
  public String getName() {
    return name;
  }

  public int getRangerId() {
    return rangerId;
  }

  public int getId() {
    return id;
  }

  public int getHealthLevel() {
    return healthLevel;
  }

  public int getAgeLevel() {
    return ageLevel;
  }

  public Timestamp getLastHealth() {
    return lastHealth;
  }

  public Timestamp getLastAge() {
    return lastAge;
  }

  //constants methods
  public void depleteLevels() {
    healthLevel--;
    ageLevel--;
  }

  public void health() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE animals SET lastHealth = now() WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
    healthLevel++;
  }

  public void age() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE animals SET lastage = now() WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
    ageLevel++;
  }

  public Timestamp getBirthday() {
    return birthday;
  }

  // an override method to return true if the animal name corresponding to the ranger's id are same
  @Override
  public boolean equals(Object otherAnimal) {
    if(!(otherAnimal instanceof Animal)) {
      return false;
    } else {
      Animal newAnimal = (Animal) otherAnimal;
      return this.getName().equals(newAnimal.getName()) &&
            this.getRangerId() == newAnimal.getRangerId();
    }
  }
  //save method
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO animals (name, rangerid, birthday, type) VALUES (:name, :rangerId, now(), :type)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("rangerId", this.rangerId)
      .addParameter("type", this.type)
      .executeUpdate()
      .getKey();
    }
  }

  // to check if an animal is dead or alive
  public boolean isAlive() {
    if (healthLevel <= MIN_ALL_LEVELS ||
    ageLevel <= MIN_ALL_LEVELS) {
      return false;
    }
    return true;
  }

  //delete method
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM animals WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

}
