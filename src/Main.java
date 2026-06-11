/**
 * Student Grade Management System
 * -------------------------------
 * A console application for keeping student records, recording grades,
 * computing averages and class statistics, and saving everything to a
 * file so the data persists between runs.
 *
 * Entry point and main menu loop.
 *
 * Suggested owner: Member 5 (menu + integration), or shared if the
 * group has fewer members.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("      STUDENT GRADE MANAGEMENT SYSTEM");
        System.out.println("==============================================");

        StudentManager manager = new StudentManager();
        manager.getStudents().addAll(FileHandler.load());

        String[] validChoices = {"1", "2", "3", "4", "5", "6", "7", "8", "0"};
        boolean running = true;

        while (running) {
            printMenu();
            String choice = InputValidator.getMenuChoice("Choose an option: ", validChoices);

            switch (choice) {
                case "1":
                    manager.addStudent();
                    break;
                case "2":
                    manager.recordGrades();
                    break;
                case "3":
                    ReportGenerator.printStudentTable(manager.getStudents());
                    break;
                case "4":
                    viewOneStudent(manager);
                    break;
                case "5":
                    ReportGenerator.printClassStatistics(manager.getStudents());
                    break;
                case "6":
                    manager.updateStudent();
                    break;
                case "7":
                    manager.deleteStudent();
                    break;
                case "8":
                    manager.searchStudents();
                    break;
                case "0":
                    FileHandler.save(manager.getStudents());
                    System.out.println("Goodbye!");
                    running = false;
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n----------- MAIN MENU -----------");
        System.out.println("1. Add a student");
        System.out.println("2. Record grades");
        System.out.println("3. View all students");
        System.out.println("4. View one student's record");
        System.out.println("5. Class statistics");
        System.out.println("6. Update a student");
        System.out.println("7. Delete a student");
        System.out.println("8. Search students");
        System.out.println("0. Save and exit");
        System.out.println("---------------------------------");
    }

    private static void viewOneStudent(StudentManager manager) {
        System.out.println("\n--- View Student ---");
        if (manager.getStudents().isEmpty()) {
            System.out.println("  [!] No students on record yet. Add a student first.");
            return;
        }
        String id = InputValidator.getValidStudentId("Student ID: ");
        Student student = manager.findById(id);
        if (student == null) {
            System.out.println("  [!] No student found with ID " + id + ".");
        } else {
            ReportGenerator.printStudentDetails(student);
        }
    }
}
