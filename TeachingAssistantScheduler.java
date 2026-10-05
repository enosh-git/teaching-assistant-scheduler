public class TeachingAssistantScheduler {
  private TeachingAssistant[] assistants;
  private Course[] courses;

  private TeachingAssistant[] assignedTAs;
  private Course[] assignedCourses;

  private int assistantCount;
  private int courseCount;
  private int assignmentCount;

  public TeachingAssistantScheduler() {
    assistants = new TeachingAssistant[100];
    courses = new Course[100];

    assignedTAs = new TeachingAssistant[200];
    assignedCourses = new Course[200];

    assistantCount = 0;
    courseCount = 0;
    assignmentCount = 0;
  }

  // methods
  public void addAssistant(TeachingAssistant ta) {
    if (ta == null || assistantCount == assistants.length) {
      throw new IllegalStateException("Cannot add teaching assistant.");
    }

    for (int i = 0; i < assistantCount; i++) {
      if (assistants[i].getId().equalsIgnoreCase(ta.getId())) {
          throw new IllegalArgumentException("Duplicate TA ID.");
      }
    }

    assistants[assistantCount] = ta;
    assistantCount++;
  }

  public void addCourse(Course course) {
    if (course == null || courseCount == courses.length) {
      throw new IllegalStateException("Cannot add course.");
    }

    for (int i = 0; i < courseCount; i++) {
      if (courses[i].getCourseCode().equalsIgnoreCase(course.getCourseCode())) {
        throw new IllegalArgumentException("Duplicate course code.");
      }
    }

    courses[courseCount] = course;
    courseCount++;
  }

  public Course findCourseByCode(String code) throws CourseNotFoundException {
    if (code != null) {
      for (int i = 0; i < courseCount; i++) {
        if (courses[i].getCourseCode().equalsIgnoreCase(code.trim())) {
          return courses[i];
        }
      }
    }

    throw new CourseNotFoundException("Course not found: " + code);
  }

  public TeachingAssistant findAssistantById(String id) {
    if (id == null) {
      return null;
    }

    for (int i = 0; i < assistantCount; i++) {
      if (assistants[i].getId().equalsIgnoreCase(id.trim())) {
        return assistants[i];
      }
    }

    return null;
  }

  public boolean isQualified(TeachingAssistant ta, Course course) {
      return ta != null && course != null && ta.hasExpertise(course.getRequiredExpertise());
  }

  public boolean hasConflict(TeachingAssistant ta, Course course) {
    for (int i = 0; i < assignmentCount; i++) {
      if (assignedTAs[i] == ta && assignedCourses[i].getSchedule().overlaps(course.getSchedule())) {
        return true;
      }
    }

    return false;
  }

  public void assignTA(TeachingAssistant ta, Course course) throws SchedulingException, WorkloadExceededException {
      // 1. Check expertise.
    if (!isQualified(ta, course)) {
      throw new SchedulingException("TA does not have the required expertise.");
    }

    // 2. Check availability.
    if (!ta.isAvailable(course.getSchedule())) {
      throw new SchedulingException("TA is not available during the course schedule.");
    }

    // 3. Check schedule conflicts.
    if (hasConflict(ta, course)) {
      throw new SchedulingException("TA has a schedule conflict.");
    }

    // 4. Check workload.
    if (!ta.canAcceptCourse()) {
      throw new WorkloadExceededException("TA has reached the maximum course workload.");
    }

    // Check assignment storage capacity.
    if (assignmentCount == assignedTAs.length) {
      throw new IllegalStateException("Assignment storage is full.");
    }

    // Store the successful assignment.
    assignedTAs[assignmentCount] = ta;
    assignedCourses[assignmentCount] = course;
    assignmentCount++;

    ta.addCourse();
  }

  public void displayAssistants() {
    System.out.println("\n--- REGISTERED TEACHING ASSISTANTS ---");

    if (assistantCount == 0) {
      System.out.println("No teaching assistants registered.");
      return;
    }

    for (int i = 0; i < assistantCount; i++) {
      TeachingAssistant ta = assistants[i];

      System.out.println(ta.getDescription() + " | " + ta.getRole() + " | Expertise: " + ta.getExpertise() + " | Availability: " + ta.getAvailability() + " | Courses: " + ta.getAssignedCourses() + "/" + ta.getMaxCourses());
    }
  }

  public void displayCourses() {
    System.out.println("\n--- REGISTERED COURSES ---");

    if (courseCount == 0) {
      System.out.println("No courses registered.");
      return;
    }

    for (int i = 0; i < courseCount; i++) {
      System.out.println(courses[i]);
    }
  }

  public void displayAssignments() {
    System.out.println("\n--- CURRENT ASSIGNMENTS ---");

    if (assignmentCount == 0) {
      System.out.println("No assignments yet.");
      return;
    }

    for (int i = 0; i < assignmentCount; i++) {
      Course course = assignedCourses[i];
      TeachingAssistant ta = assignedTAs[i];

      System.out.println("Course: " + course.getCourseCode() + " - " + course.getCourseName());

      System.out.println("Schedule: " + course.getSchedule());

      System.out.println("Assigned TA: " + ta.getName() + " (" + ta.getId() + ")");

      System.out.println("Role: " + ta.getRole());
      System.out.println("----------------------------------------");
    }

    System.out.println("Total Assignments: " + assignmentCount);
  }
}