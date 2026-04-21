package OOPproject;

import java.util.ArrayList;

public class Course {
    private String componentType;
    private String code;
    private String name;
    private String category;

    private int ECTScredits;
    private int KZcredits;

    private record HoursDistribution(int lectureHours, int labHours, int practiceHours) {}
    private HoursDistribution hoursDistribution;

    private ArrayList<String> prerequisites;

    private Mark mark;

    public Course(String componentType,
                  String code,
                  String name,
                  String category,
                  int ECTScredits,
                  int KZcredits,
                  int lectureHours,
                  int labHours,
                  int practiceHours,
                  ArrayList<String> prerequisites,
                  Mark mark) {
        this.componentType = componentType;
        this.code = code;
        this.name = name;
        this.category = category;
        this.ECTScredits = ECTScredits;
        this.KZcredits = KZcredits;
        this.hoursDistribution = new HoursDistribution(lectureHours, labHours, practiceHours);
        this.prerequisites = prerequisites;
        this.mark = mark;
    }


 }
