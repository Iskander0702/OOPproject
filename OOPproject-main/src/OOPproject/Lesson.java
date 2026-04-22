package OOPproject;

import java.util.Objects;

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

    public Course getCourse() {
        return course;
    }

    public String getType() {
        return type;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public String getTime() {
        return time;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "Course: " + course +
                "\nType: " + type +
                "\nDay of week: " + dayOfWeek +
                "\nTime: " + time +
                "\nTeacher: " + teacher +
                "\nRoom number: " + roomNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return Objects.equals(course, lesson.course) &&
                Objects.equals(type, lesson.type) &&
                dayOfWeek == lesson.dayOfWeek &&
                Objects.equals(time, lesson.time) &&
                Objects.equals(teacher, lesson.teacher) &&
                roomNumber == lesson.roomNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, type, dayOfWeek, time, teacher, roomNumber);
    }
}
