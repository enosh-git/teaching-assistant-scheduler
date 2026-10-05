public class Person {
  private String name;
  private String id;

  public Person(String name, String id) {
    setName(name);
    setId(id);
  }

  // getters
  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }

  // setters
  public void setName(String name) {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name is required.");
    }

    this.name = name.trim();
  }

  public void setId(String id) {
    if (id == null || id.trim().isEmpty()) {
        throw new IllegalArgumentException("ID is required.");
    }

    this.id = id.trim();
  }


  // additional methods
  public String getDescription() {
    return name + " (" + id + ")";
  }
}