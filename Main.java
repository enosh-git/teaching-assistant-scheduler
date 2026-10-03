// Fixed formatting

import java.util.Scanner;

public class Main {
  private static Scanner input = new Scanner(System.in);
  private static TeachingAssistantScheduler scheduler = new TeachingAssistantScheduler();

  public static void main(String[] args) {
    int choice;

    do {
      displayMenu();
      choice = readInt("Enter choice: ");

      try {
        switch (choice) {
          case 1:
            addTA();
            break;

          case 2:
            addCourse();
            break;

          case 3:
            scheduler.displayAssistants();
            break;

          case 4:
            scheduler.displayCourses();
            break;

          case 5:
            assignTA();
            break;

          case 6:
            scheduler.displayAssignments();
            break;

          case 7:
            System.out.println("Exiting Teaching Assistant Scheduler. Goodbye!");
            break;

          default:
            System.out.println("Invalid choice. Select 1 to 7.");
        }
      } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
      } catch (IllegalStateException e) {
        System.out.println("Error: " + e.getMessage());
      }

    } while (choice != 7);

    input.close();
  }

  private static void displayMenu() {
    System.out.println("\n========================================" + "\n   TEACHING ASSISTANT SCHEDULER" + "\n========================================" + "\n1. Add Teaching Assistant" + "\n2. Add Course" + "\n3. Display Teaching Assistants" + "\n4. Display Courses" + "\n5. Assign TA to Course" + "\n6. Display Assignments" + "\n7. Exit");
  }

  private static void addTA() {
    System.out.println("\n--- ADD TEACHING ASSISTANT ---");
    System.out.println("1. Graduate Teaching Assistant");
    System.out.println("2. Undergraduate Teaching Assistant");

    int type = readInt("Enter choice: ");

    if (type != 1 && type != 2) {
      System.out.println("Invalid TA type.");
      return;
    }

    String name = readText("Enter name: ");
    String id = readText("Enter ID: ");
    String expertise = readText("Enter expertise: ");

    Schedule availability = readSchedule("Enter available");

    int maxCourses = readPositive("Enter maximum courses: ");

    TeachingAssistant ta;

    if (type == 1) {
      String degreeProgram = readText("Enter degree program: ");

      ta = new GraduateTeachingAssistant(name, id, expertise, availability, maxCourses, degreeProgram);
    } else {
      int yearLevel = readInt("Enter year level (1-6): ");
      ta = new UndergraduateTeachingAssistant(name, id, expertise, availability, maxCourses, yearLevel);
    }

    scheduler.addAssistant(ta);
    System.out.println("Teaching Assistant added successfully!");
  } 

  private static void addCourse() {
    System.out.println("\n--- ADD COURSE ---");

    String courseCode = readText("Enter course code: ");
    String courseName = readText("Enter course name: ");
    String expertise = readText("Enter required expertise: ");
    Schedule schedule = readSchedule("Enter course");
    int requiredTAs = readPositive("Enter required number of TAs: ");

    Course course = new Course(courseCode, courseName, expertise, schedule, requiredTAs);
    scheduler.addCourse(course);

    System.out.println("Course added successfully!");
  }

  private static void assignTA() {
    System.out.println("\n--- ASSIGN TA TO COURSE ---");
    String id = readText("Enter TA ID: ");
    String courseCode = readText("Enter Course Code: ");

    TeachingAssistant ta = scheduler.findAssistantById(id);

    if (ta == null) {
      System.out.println("Teaching Assistant not found: " + id);
      return;
    }

    try {
      Course course = scheduler.findCourseByCode(courseCode);

      System.out.println("\nChecking assignment...");

      scheduler.assignTA(ta, course);

      System.out.println("[✓] Expertise requirement satisfied.");
      System.out.println("[✓] TA is available.");
      System.out.println("[✓] No schedule conflict.");
      System.out.println("[✓] Workload limit not exceeded.");

      System.out.println("\nTA " + ta.getName() + " successfully assigned to " + course.getCourseCode() + ".");
    } catch (CourseNotFoundException e) {
      System.out.println("Assignment failed: " + e.getMessage());
    } catch (SchedulingException e) {
        System.out.println("Assignment failed: " + e.getMessage());
    } catch (WorkloadExceededException e) {
        System.out.println("Assignment failed: " + e.getMessage());
    }
  }

  private static Schedule readSchedule(String label) {
    String day = readText(label + " day: ");
    int startTime = readInt("Enter start time (0-23): ");
    int endTime = readInt("Enter end time (1-24): ");

    return new Schedule(day, startTime, endTime);
  }

  private static String readText(String prompt) {
    System.out.print(prompt);
    return input.nextLine().trim();
  }

  private static int readInt(String prompt) {
    while (true) {
      System.out.print(prompt);

      try {
        return Integer.parseInt(input.nextLine().trim());
      } catch (NumberFormatException e) {
          System.out.println("Please enter a whole number.");
      }
    }
  }

  private static int readPositive(String prompt) {
    while (true) {
      int number = readInt(prompt);

      if (number > 0) {
        return number;
      }
      System.out.println("Value must be greater than zero.");
    }
  }
}