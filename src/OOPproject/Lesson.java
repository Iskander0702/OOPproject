package OOPproject;

public class Lesson {
    private Course course;
    private String type;
    private DayOfWeek dayOfWeek;
    private String time;
    private Teacher teacher;
    private int roomNumber;

    public Lesson(Course course,
                  String type,
                  DayOfWeek dayOfWeek,
                  String time,
                  Teacher teacher,
                  int roomNumber) {
        this.course = course;
        this.type = type;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
        this.teacher = teacher;
        this.roomNumber = roomNumber;
    }
}
