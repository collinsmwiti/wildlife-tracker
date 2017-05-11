// imports
import org.sql2o.*;

//connecting to postgresql database in class DB
public class DB {
  public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "collins", "password");
}
