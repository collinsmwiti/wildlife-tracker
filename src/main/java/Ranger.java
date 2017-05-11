// class ranger
public class Ranger {
  // attributes and properties of class ranger
  private String name;
  private String image;
  private String email;
  private int contacts;

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
}
