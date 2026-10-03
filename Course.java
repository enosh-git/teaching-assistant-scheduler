// fixed formatting

public class Course {
  private String courseCode;
  private String courseName;
  private String requiredExpertise;
  private Schedule schedule;
  private int requiredTAs;

  public Course(String courseCode, String courseName, String requiredExpertise, Schedule schedule, int requiredTAs) {
    if (isBlank(courseCode) || isBlank(courseName) || isBlank(requiredExpertise) || schedule == null || requiredTAs < 1) {
      throw new IllegalArgumentException("Invalid course details.");
    }

    this.courseCode = courseCode.trim();
    this.courseName = courseName.trim();
    this.requiredExpertise = requiredExpertise.trim();
    this.schedule = schedule;
    this.requiredTAs = requiredTAs;
  }

  private boolean isBlank(String value) {
    return value == null || value.trim().isEmpty();
  }

  public String getCourseCode() {
    return courseCode;
  }

  public String getCourseName() {
    return courseName;
  }

  public String getRequiredExpertise() {
    return requiredExpertise;
  }

  public Schedule getSchedule() {
    return schedule;
  }

  public int getRequiredTAs() {
    return requiredTAs;
  }

  @Override
  public String toString() {
      return courseCode + " - " + courseName + " | Expertise: " + requiredExpertise + " | " + schedule + " | TAs required: " + requiredTAs;
  }
}