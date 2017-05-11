// imports
import org.junit.rules.ExternalResource;
import org.sql2o.*;

// to hold the database rules in order to enhance implementation of DRY
public class DatabaseRule extends ExternalResource {

  // connection of the testdatabase should be before tasks and tests are done
  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "collins", "password");
  }

// to enhance tasks are done after every test is done
  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteRangersQuery = "DELETE FROM rangers *;";
      con.createQuery(deleteRangersQuery).executeUpdate();
    }
  }
}
