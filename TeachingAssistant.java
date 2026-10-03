// fixed formatting

public class TeachingAssistant extends Person {
  private String expertise;
  private Schedule availability;
  private int assignedCourses;
  private int maxCourses;

  public TeachingAssistant(String name, String id, String expertise, Schedule availability, int maxCourses) {
    super(name, id);
    addExpertise(expertise);

    if (availability == null || maxCourses < 1) {
      throw new IllegalArgumentException(
          "Invalid availability or workload."
      );
    }

    this.availability = availability;
    this.maxCourses = maxCourses;
    this.assignedCourses = 0;
  }

  public void addExpertise(String skill) {
    if (skill == null || skill.trim().isEmpty()) {
      throw new IllegalArgumentException("Expertise is required.");
    }

    expertise = skill.trim();
  }

  public boolean hasExpertise(String skill) {
    return skill != null && expertise.equalsIgnoreCase(skill.trim());
  }

  public boolean isAvailable(Schedule schedule) {
    return schedule != null && availability.containsTime(schedule);
  }

  public boolean canAcceptCourse() {
      return assignedCourses < maxCourses;
  }

  public void addCourse() {
    if (!canAcceptCourse()) {
      throw new IllegalStateException("Workload limit reached.");
    }

    assignedCourses++;
  }

  public int getAssignedCourses() {
    return assignedCourses;
  }

  public int getMaxCourses() {
    return maxCourses;
  }

  public String getExpertise() {
    return expertise;
  }

  public Schedule getAvailability() {
    return availability;
  }

  public String getRole() {
    return "Teaching Assistant";
  }
}