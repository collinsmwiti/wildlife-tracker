// imports
import org.sql2o.*;
import java.util.List;

//class EndangeredAnimal extending parent class Animal
public class EndangeredAnimal extends Animal {
  public static final String DATABASE_TYPE = "endangered";

  //constructor EndangeredAnimal
  public EndangeredAnimal(String name, int rangerId) {
    this.name = name;
    this.rangerId = rangerId;
    this.healthLevel = MIN_HEALTH_LEVEL;
    this.ageLevel = MIN_AGE_LEVEL;
    type = DATABASE_TYPE;
  }

  // to list EndangeredAnimal within the database
  public static List<EndangeredAnimal> all() {
    String sql = "SELECT * FROM animals WHERE type='endangered';";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(EndangeredAnimal.class);
    }
  }

  // placing a find method in our class in order to find EndangeredAnimals according to its id
  public static EndangeredAnimal find(int id)  {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals where id=:id";
      EndangeredAnimal endangeredAnimal = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(EndangeredAnimal.class);
      return endangeredAnimal;
    }
  }
}
