//class Animal
public class Animal {
  private String name;
  private int rangerId;

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
}
