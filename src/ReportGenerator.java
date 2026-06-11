import java.util.ArrayList;

/**
 * Everything related to computing and displaying results: averages,
 * letter grades, the formatted student table, one student's full
 * breakdown, and class-wide statistics.
 *
 * Suggested owner: Member 3 (computation + reporting)
 */
public class ReportGenerator {

    /** Returns the average of all of a student's scores, or 0 if none yet. */
    public static double computeAverage(Student student) {
        ArrayList<Grade> grades = student.getGrades();
        if (grades.isEmpty()) {
            return 0;
        }
        double total = 0;
        for (Grade grade : grades) {
            total += grade.getScore();
        }
        return total / grades.size();
    }

    /** Converts a numeric average into a letter grade. */
    public static String letterGrade(double average) {
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    /** A student passes when their average is 75 or higher. */
    public static String passOrFail(double average) {
        return average >= 75 ? "PASS" : "FAIL";
    }

    /** Prints all students as a formatted table with averages and status. */
    public static void printStudentTable(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("  No students on record yet.");
            return;
        }
        String format = "%-12s %-24s %-16s %7s %7s %7s%n";
        System.out.println();
        System.out.printf(format, "ID", "Name", "Course", "Grades", "Avg", "Status");
        System.out.println("-".repeat(78));
        for (Student student : students) {
            int gradeCount = student.getGrades().size();
            String avg = gradeCount == 0 ? "--" : String.format("%.1f", computeAverage(student));
            String status = gradeCount == 0 ? "--" : passOrFail(computeAverage(student));
            System.out.printf(format, student.getId(), student.getName(),
                    student.getCourse(), gradeCount, avg, status);
        }
    }

    /** Prints one student's full grade breakdown, average, and letter grade. */
    public static void printStudentDetails(Student student) {
        System.out.println("\n--- Record for " + student.getName() + " (" + student.getId() + ") ---");
        System.out.println("Course/Section: " + student.getCourse());
        ArrayList<Grade> grades = student.getGrades();
        if (grades.isEmpty()) {
            System.out.println("No grades recorded yet.");
            return;
        }
        System.out.printf("%-28s %8s%n", "Subject/Assessment", "Score");
        System.out.println("-".repeat(38));
        for (Grade grade : grades) {
            System.out.printf("%-28s %8.1f%n", grade.getSubject(), grade.getScore());
        }
        double average = computeAverage(student);
        System.out.println("-".repeat(38));
        System.out.printf("Average: %.2f   Letter: %s   Status: %s%n",
                average, letterGrade(average), passOrFail(average));
    }

    /** Prints class-wide statistics: highest, lowest, and class average. */
    public static void printClassStatistics(ArrayList<Student> students) {
        System.out.println("\n--- Class Statistics ---");
        ArrayList<Student> graded = new ArrayList<>();
        for (Student student : students) {
            if (!student.getGrades().isEmpty()) {
                graded.add(student);
            }
        }
        if (graded.isEmpty()) {
            System.out.println("  No grades recorded yet, so there is nothing to compute.");
            return;
        }
        Student highest = graded.get(0);
        Student lowest = graded.get(0);
        double totalOfAverages = 0;
        for (Student student : graded) {
            double average = computeAverage(student);
            totalOfAverages += average;
            if (average > computeAverage(highest)) {
                highest = student;
            }
            if (average < computeAverage(lowest)) {
                lowest = student;
            }
        }
        System.out.printf("Students with grades: %d%n", graded.size());
        System.out.printf("Class average:        %.2f%n", totalOfAverages / graded.size());
        System.out.printf("Highest average:      %.2f (%s)%n", computeAverage(highest), highest.getName());
        System.out.printf("Lowest average:       %.2f (%s)%n", computeAverage(lowest), lowest.getName());
    }
}
