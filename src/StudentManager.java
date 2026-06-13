import java.util.ArrayList;

public class StudentManager {
    private final ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getStudents() {
        return students;
    }

    public Student findById(String id) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                return student;
            }
        }
        return null;
    }

    public void addStudent() {
        System.out.println("\n--- Add Student ---");
        String id = InputValidator.getValidStudentId("Student ID (e.g. 2026-0001): ");
        if (findById(id) != null) {
            System.out.println("  [!] A student with ID " + id + " already exists.");
            return;
        }
        String name = InputValidator.getFileSafeString("Full name: ");
        String course = InputValidator.getFileSafeString("Course/Section: ");
        students.add(new Student(id, name, course));
        System.out.println("  [OK] Added " + name + " (" + id + ").");
    }
    
    public void recordGrades() {
        System.out.println("\n--- Record Grades ---");
        Student student = promptForExistingStudent();
        if (student == null) {
            return;
        }
        boolean addMore = true;
        while (addMore) {
            String subject = InputValidator.getFileSafeString("Subject/assessment name: ");
            double score = InputValidator.getValidScore("Score (0-100): ");
            student.addGrade(new Grade(subject, score));
            System.out.println("  [OK] Recorded " + subject + " = " + score + " for " + student.getName() + ".");
            addMore = InputValidator.confirm("Add another grade for this student?");
        }
    }

    public void updateStudent() {
        System.out.println("\n--- Update Student ---");
        Student student = promptForExistingStudent();
        if (student == null) {
            return;
        }
        System.out.println("Updating " + student.getName() + " (" + student.getId() + ").");
        String[] options = {"1", "2"};
        String choice = InputValidator.getMenuChoice("Update (1) name or (2) course? ", options);
        if (choice.equals("1")) {
            student.setName(InputValidator.getFileSafeString("New name: "));
        } else {
            student.setCourse(InputValidator.getFileSafeString("New course/section: "));
        }
        System.out.println("  [OK] Record updated.");
    }

    public void deleteStudent() {
        System.out.println("\n--- Delete Student ---");
        Student student = promptForExistingStudent();
        if (student == null) {
            return;
        }
        boolean sure = InputValidator.confirm(
                "Really delete " + student.getName() + " (" + student.getId() + ") and all their grades?");
        if (sure) {
            students.remove(student);
            System.out.println("  [OK] Student deleted.");
        } else {
            System.out.println("  Deletion cancelled.");
        }
    }

    public void searchStudents() {
        System.out.println("\n--- Search ---");
        String query = InputValidator.getNonEmptyString("Search by ID or name: ").toLowerCase();
        ArrayList<Student> matches = new ArrayList<>();
        for (Student student : students) {
            boolean idMatches = student.getId().toLowerCase().contains(query);
            boolean nameMatches = student.getName().toLowerCase().contains(query);
            if (idMatches || nameMatches) {
                matches.add(student);
            }
        }
        if (matches.isEmpty()) {
            System.out.println("  No students matched '" + query + "'.");
        } else {
            ReportGenerator.printStudentTable(matches);
        }
    }

    private Student promptForExistingStudent() {
        if (students.isEmpty()) {
            System.out.println("  [!] No students on record yet. Add a student first.");
            return null;
        }
        String id = InputValidator.getValidStudentId("Student ID: ");
        Student student = findById(id);
        if (student == null) {
            System.out.println("  [!] No student found with ID " + id + ".");
        }
        return student;
    }
}
