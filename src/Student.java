import java.util.ArrayList;

/**
 * Holds everything we know about one student: their ID, name,
 * course/section, and the list of grades they have earned so far.
 *
 * Suggested owner: Member 1 (data model + validation)
 */
public class Student {
    private final String id;
    private String name;
    private String course;
    private final ArrayList<Grade> grades = new ArrayList<>();

    public Student(String id, String name, String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }
}
