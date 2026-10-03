// fixed formatting

public class Schedule {
  private String day;
  private int startTime;
  private int endTime;

  public Schedule(String day, int startTime, int endTime) {
    if (day == null || day.trim().isEmpty() || startTime < 0 || startTime > 23 || endTime < 1 || endTime > 24 || startTime >= endTime) {
      throw new IllegalArgumentException("Invalid schedule. Use 24-hour times, such as 8 to 12.");
    }

    String formattedDay = day.trim().toLowerCase();

    this.day = Character.toUpperCase(formattedDay.charAt(0)) + formattedDay.substring(1);
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public boolean overlaps(Schedule other) {
    if (other == null) {
      return false;
    }

    return day.equalsIgnoreCase(other.day) && startTime < other.endTime && other.startTime < endTime;
  }

  public boolean containsTime(Schedule other) {
    if (other == null) {
      return false;
    }

    return day.equalsIgnoreCase(other.day) && startTime <= other.startTime && endTime >= other.endTime;
  }

  public int getDuration() {
    return endTime - startTime;
  }

  private String formatTime(int hour) {
    if (hour == 24) {
      return "24:00";
    }

    return String.format("%02d:00", hour);
  }

  @Override
  public String toString() {
      return day + " " + formatTime(startTime) + " - " + formatTime(endTime);
  }
}