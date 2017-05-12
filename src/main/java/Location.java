//imports
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

// class Location
public class Location implements DatabaseManagement {
  private String image;
  private String locationName;
  private int endangeredAnimalid;
  private String rangerName;
  private int id;

  //constructor Location
  public Location(String image, String locationName, int endangeredAnimalid, String rangerName) {
    this.image = image;
    this.locationName = locationName;
    this.endangeredAnimalid = endangeredAnimalid;
    this.rangerName = rangerName;

  }

    //get methods
    public String getImage() {
      return image;
    }

    public String getLocationName() {
      return locationName;
    }

    public int getEndangeredAnimalId() {
      return endangeredAnimalid;
    }

    public String getRangerName() {
      return rangerName;
    }

    public int getId() {
      return id;
    }

  //Override method
  @Override
  public boolean equals(Object otherLocation) {
    if(!(otherLocation instanceof Location)) {
        return false;
    } else {
      Location newLocation = (Location) otherLocation;
      return this.getImage().equals(newLocation.getImage()) &&
            this.getLocationName().equals(newLocation.getLocationName()) &&
            this.getEndangeredAnimalId() == (newLocation.getEndangeredAnimalId()) &&
            this.getRangerName().equals(newLocation.getRangerName());
    }
  }

    // method used for saving data in the database
    @Override
    public void save() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO locations (image, locationName, endangeredAnimalid, rangerName) VALUES (:image, :locationName, :endangeredAnimalid, :rangerName)";
        this.id= (int) con.createQuery(sql, true)
        .addParameter("image", this.image)
        .addParameter("locationName", this.locationName)
        .addParameter("endangeredAnimalid", this.endangeredAnimalid)
        .addParameter("rangerName", this.rangerName)
        .executeUpdate()
        .getKey();
      }
    }

    // method used to return all the data in the location class as a list
    public static List<Location> all() {
      String sql = "SELECT * FROM locations";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).executeAndFetch(Location.class);
      }
    }

    //method used to add data to our joint table
    public void addRanger(Ranger ranger) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO sightings (locationName, endangeredAnimalid, rangerName) VALUES (:locationName, :endangeredAnimalid, :rangerName)";
        con.createQuery(sql)
        .addParameter("locationName", this.getLocationName())
        .addParameter("endangeredAnimalid", this.getEndangeredAnimalId())
        .addParameter("rangerName", this.getRangerName())
        .executeUpdate();
      }
    }

    public List<Ranger> getRangers() {
      try(Connection con = DB.sql2o.open()) {
        String joinQuery = "SELECT rangerName FROM sightings WHERE locationName = :locationName";
        List<String> rangerNames = con.createQuery(joinQuery)
        .addParameter("locationName", this.getLocationName())
        .executeAndFetch(String.class);

        List<Ranger> rangers = new ArrayList<Ranger>();

        for (String rangerName : rangerNames) {
          String rangerQuery = "SELECT * FROM rangers WHERE name = :rangerName";
          Ranger ranger = con.createQuery(rangerQuery)
          .addParameter("rangerName", rangerName)
          .executeAndFetchFirst(Ranger.class);
          rangers.add(ranger);
        }
        return rangers;
      }
    }

    // method to add delete functionality in class Location
    @Override
    public void delete() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "DELETE FROM locations WHERE locationName = :locationName;";
        con.createQuery(sql)
        .addParameter("locationName", this.locationName)
        .executeUpdate();
        String joinDeleteQuery = "DELETE FROM sightings WHERE locationName = :locationName";
        con.createQuery(joinDeleteQuery)
        .addParameter("locationName", this.getLocationName())
        .executeUpdate();
      }
    }

    //method to remove a ranger from the location
    public void removeRanger(Ranger ranger) {
      try(Connection con = DB.sql2o.open()) {
        String joinRemovalQuery = "DELETE FROM sightings WHERE locationName = :locationName AND rangerName = :rangerName;";
        con.createQuery(joinRemovalQuery)
        .addParameter("locationName", this.getLocationName())
        .addParameter("rangerName", ranger.getName())
        .executeUpdate();
      }
    }
  }
