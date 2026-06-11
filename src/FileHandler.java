import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Saves student records to a file and loads them back, so data survives
 * between runs of the program.
 *
 * File format (one student per line):
 *   id,name,course,subject=score;subject=score
 * Example:
 *   2026-0001,Maria Santos,BSIT 1A,Math=90.0;Science=85.5
 *
 * Suggested owner: Member 4 (file handling + persistence)
 */
public class FileHandler {
    public static final String DATA_FILE = "students.csv";

    /** Writes every student (and their grades) to the data file. */
    public static void save(ArrayList<Student> students) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Student student : students) {
                StringBuilder line = new StringBuilder();
                line.append(student.getId()).append(",");
                line.append(student.getName()).append(",");
                line.append(student.getCourse()).append(",");
                ArrayList<Grade> grades = student.getGrades();
                for (int i = 0; i < grades.size(); i++) {
                    if (i > 0) {
                        line.append(";");
                    }
                    line.append(grades.get(i).getSubject())
                        .append("=")
                        .append(grades.get(i).getScore());
                }
                writer.println(line);
            }
            System.out.println("  [OK] Saved " + students.size() + " record(s) to " + DATA_FILE + ".");
        } catch (IOException e) {
            System.out.println("  [!] Could not save data: " + e.getMessage());
        }
    }

    /**
     * Reads the data file and rebuilds the student list. If the file does
     * not exist yet (first run), we simply start with an empty list.
     */
    public static ArrayList<Student> load() {
        ArrayList<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Student student = parseLine(line);
                if (student != null) {
                    students.add(student);
                }
            }
            System.out.println("Loaded " + students.size() + " student record(s) from " + DATA_FILE + ".");
        } catch (IOException e) {
            System.out.println("No saved data found. Starting with an empty record list.");
        }
        return students;
    }

    /** Turns one saved line back into a Student, or null if the line is broken. */
    private static Student parseLine(String line) {
        String[] parts = line.split(",", 4);
        if (parts.length < 3) {
            System.out.println("  [!] Skipping unreadable line: " + line);
            return null;
        }
        Student student = new Student(parts[0], parts[1], parts[2]);
        if (parts.length == 4 && !parts[3].isEmpty()) {
            for (String entry : parts[3].split(";")) {
                String[] pair = entry.split("=");
                if (pair.length != 2) {
                    continue;
                }
                try {
                    student.addGrade(new Grade(pair[0], Double.parseDouble(pair[1])));
                } catch (NumberFormatException e) {
                    System.out.println("  [!] Skipping unreadable grade '" + entry
                            + "' for " + student.getId() + ".");
                }
            }
        }
        return student;
    }
}
