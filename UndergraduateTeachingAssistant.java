// fixed formatting

public class UndergraduateTeachingAssistant extends TeachingAssistant {
  private int yearLevel;

  public UndergraduateTeachingAssistant(String name, String id, String expertise, Schedule availability, int maxCourses, int yearLevel) {
    super(name, id, expertise, availability, maxCourses);

    if (yearLevel < 1 || yearLevel > 6) {
      throw new IllegalArgumentException("Year level must be from 1 to 6.");
    }

    this.yearLevel = yearLevel;
  }

  @Override
  public String getRole() {
    return "Undergraduate Teaching Assistant";
  }

  public int getYearLevel() {
    return yearLevel;
  }
}