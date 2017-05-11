//imports
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

//class Animal
public class Animal {
  private String name;
  private int rangerId;
  public int id;

  //constructor animal
  public Animal(String name, int rangerId) {
    this.name = name;
    this.rangerId = rangerId;
  }

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
      String sql = "INSERT INTO animals (name, rangerid) VALUES (:name, :rangerId)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("rangerId", this.rangerId)
      .executeUpdate()
      .getKey();
    }
  }

  // to list animals within the database
  public static List<Animal> all() {
    String sql = "SELECT * FROM animals";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Animal.class);
    }
  }

  // placing a find method in our class in order to find animals according to its id
  public static Animal find(int id)  {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals where id=:id";
      Animal animal = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Animal.class);
      return animal;
    }
  }

}
