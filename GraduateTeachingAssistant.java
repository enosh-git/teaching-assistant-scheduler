public class GraduateTeachingAssistant extends TeachingAssistant {
  private String degreeProgram;

  public GraduateTeachingAssistant(String name, String id, String expertise, Schedule availability, int maxCourses, String degreeProgram) {
    super(name, id, expertise, availability, maxCourses);

    if (degreeProgram == null || degreeProgram.trim().isEmpty()) {
      throw new IllegalArgumentException("Degree program is required.");
    }

    this.degreeProgram = degreeProgram.trim();
  }

  // getters
  @Override
  public String getRole() {
    return "Graduate Teaching Assistant";
  }

  public String getDegreeProgram() {
    return degreeProgram;
  }
}