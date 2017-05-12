//imports
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

// class Location
public class Location {
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

  //Override method
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

  }
