//imports
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

// class ranger
public class Ranger implements DatabaseManagement {
  // attributes and properties of class ranger
  private String name;
  private String image;
  private String email;
  private int contacts;
  private int id;

  //constructor ranger
  public Ranger(String name, String image, String email, int contacts) {
    this.name = name;
    this.image = image;
    this.email = email;
    this.contacts = contacts;
  }

  //getter methods
  public String getName() {
    return name;
  }

  public String getImage() {
    return image;
  }

  public String getEmail() {
    return email;
  }

  public int getContacts() {
    return contacts;
  }

  public int getId() {
    return id;
  }

  //an override used to check if an object equals to another object
  @Override
  public boolean equals(Object otherRanger) {
    if (!(otherRanger instanceof Ranger)) {
      return false;
    } else {
      Ranger newRanger = (Ranger) otherRanger;
      return this.getName().equals(newRanger.getName()) &&
            this.getImage().equals(newRanger.getImage()) &&
            this.getEmail().equals(newRanger.getEmail()) &&
            this.getContacts()== (newRanger.getContacts());
    }
  }

  // to save rangers details within the database
  @Override
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO rangers (name, image, email, contacts) VALUES (:name, :image, :email, :contacts)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("image", this.image)
      .addParameter("email", this.email)
      .addParameter("contacts", this.contacts)
      .executeUpdate()
      .getKey();
    }
  }

  // to show lists of rangers within the database
  public static List<Ranger> all() {
    String sql = "SELECT * FROM rangers";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Ranger.class);
    }
  }
  // used to find rangers according to their id
  public static Ranger find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM rangers where id=:id";
      Ranger ranger = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Ranger.class);
      return ranger;
    }
  }

  // method used to display animals from the database as a list
  public List<Object> getAnimals() {
    List<Object> allAnimals = new ArrayList<Object>();

    try(Connection con = DB.sql2o.open()) {
      String sql ="SELECT * FROM animals where rangerId=:id AND type='endangered';";
      List<EndangeredAnimal> endangeredAnimals = con.createQuery(sql)
      .addParameter("id", this.id)
      .executeAndFetch(EndangeredAnimal.class);
      allAnimals.addAll(endangeredAnimals);
    }
    return allAnimals;
  }

  // methods to indicate locations watched by the ranger
  public List<Location> getLocations() {
    try(Connection con = DB.sql2o.open()) {
      String joinQuery = "SELECT locationName FROM sightings WHERE rangerName = :rangerName";
      List<String> locationNames = con.createQuery(joinQuery)
      .addParameter("rangerName", this.getName())
      .executeAndFetch(String.class);

      List<Location> locations = new ArrayList<Location>();

      for (String locationName : locationNames) {
        String locationQuery = "SELECT * FROM locations WHERE locationName = :locationName";
        Location location = con.createQuery(locationQuery)
        .addParameter("locationName", locationName)
        .executeAndFetchFirst(Location.class);
        locations.add(location);
      }
      return locations;
    }
  }

  //method to enable a ranger to leave the wildlife area incase if he/she has retired, quited or demoted
  public void leaveLocation(Location location) {
    try(Connection con = DB.sql2o.open()) {
      String joinRemovalQuery = "DELETE FROM sightings WHERE locationName = :locationName AND rangerName = :rangerName;";
      con.createQuery(joinRemovalQuery)
      .addParameter("locationName", location.getLocationName())
      .addParameter("rangerName", this.getName())
      .executeUpdate();
    }
  }

  //method used to delete a ranger within the class
  @Override
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM rangers WHERE name = :rangerName;";
      con.createQuery(sql)
      .addParameter("rangerName", this.name)
      .executeUpdate();
      String joinDeleteQuery = "DELETE FROM sightings WHERE rangerName = :rangerName";
      con.createQuery(joinDeleteQuery)
      .addParameter("rangerName", this.getName())
      .executeUpdate();
    }
  }
}
