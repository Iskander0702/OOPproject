package OOPproject;

import java.util.ArrayList;
import java.util.Objects;

public class Course {
    private String componentType;
    private String code;
    private String name;
    private String category;

    private int ECTScredits;
    private int KZcredits;

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

    public String getComponentType() {
        return componentType;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getECTScredits() {
        return ECTScredits;
    }

    public int getKZcredits() {
        return KZcredits;
    }

    public HoursDistribution getHoursDistribution() {
        return hoursDistribution;
    }

    public ArrayList<String> getPrerequisites() {
        return prerequisites;
    }

    public Mark getMark() {
        return mark;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setECTScredits(int ECTScredits) {
        this.ECTScredits = ECTScredits;
    }

    public void setKZcredits(int KZcredits) {
        this.KZcredits = KZcredits;
    }

    public void setHoursDistribution(HoursDistribution hoursDistribution) {
        this.hoursDistribution = hoursDistribution;
    }

    public void setPrerequisites(ArrayList<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Component type: " + componentType +
                "\nCode: " + code +
                "\nName: " + name +
                "\nCategory: " + category +
                "\nECTScredits: " + ECTScredits +
                "\nKZcredits: " + KZcredits +
                "\nLecture hours: " + hoursDistribution.lectureHours() +
                "\nLab hours: " + hoursDistribution.labHours() +
                "\nPractice hours: " + hoursDistribution.practiceHours() +
                "\nPrerequisites: " + prerequisites.toString() +
                "\n1st attestation mark: " + mark.getFirstAttestationMark() +
                "\n2st attestation mark: " + mark.getSecondAttestationMark() +
                "\nFinal mark: " + mark.getFinalMark();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(componentType, course.componentType) &&
                Objects.equals(code, course.code) &&
                Objects.equals(name, course.name) &&
                Objects.equals(category, course.category) &&
                ECTScredits == course.ECTScredits &&
                KZcredits == course.KZcredits &&
                Objects.equals(hoursDistribution, course.hoursDistribution) &&
                Objects.equals(prerequisites, course.prerequisites) &&
                Objects.equals(mark, course.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(componentType, code, name, category, ECTScredits, KZcredits, hoursDistribution, prerequisites, mark);
    }
}
