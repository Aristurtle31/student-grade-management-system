import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class MainGUI {
    private final StudentManager manager = new StudentManager();
    private final StudentTableModel tableModel = new StudentTableModel();
    private final JTable table = new JTable(tableModel);
    private final JTextField searchField = new JTextField(16);
    private JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI().show());
    }

    private void show() {
        manager.getStudents().addAll(FileHandler.load());

        frame = new JFrame("Student Grade Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                FileHandler.save(manager.getStudents());
            }
        });

        frame.add(buildSearchBar(), BorderLayout.NORTH);
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(buildButtonBar(), BorderLayout.SOUTH);

        table.getSelectionModel().setSelectionMode(
                javax.swing.ListSelectionModel.SINGLE_SELECTION);
        refreshTable();

        frame.setSize(820, 420);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel buildSearchBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bar.add(new JLabel("Search ID or name:"));
        bar.add(searchField);
        JButton searchButton = new JButton("Search");
        JButton showAllButton = new JButton("Show All");
        searchButton.addActionListener(e -> refreshTable());
        searchField.addActionListener(e -> refreshTable());
        showAllButton.addActionListener(e -> {
            searchField.setText("");
            refreshTable();
        });
        bar.add(searchButton);
        bar.add(showAllButton);
        return bar;
    }

    private JPanel buildButtonBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addButton(bar, "Add Student", this::addStudent);
        addButton(bar, "Record Grade", this::recordGrade);
        addButton(bar, "View Details", this::viewDetails);
        addButton(bar, "Update", this::updateStudent);
        addButton(bar, "Delete", this::deleteStudent);
        addButton(bar, "Class Stats", this::showClassStats);
        return bar;
    }

    private void addButton(JPanel panel, String label, Runnable action) {
        JButton button = new JButton(label);
        button.addActionListener(e -> action.run());
        panel.add(button);
    }

    private void refreshTable() {
        String query = searchField.getText().trim().toLowerCase();
        ArrayList<Student> visible = new ArrayList<>();
        for (Student student : manager.getStudents()) {
            boolean matches = query.isEmpty()
                    || student.getId().toLowerCase().contains(query)
                    || student.getName().toLowerCase().contains(query);
            if (matches) {
                visible.add(student);
            }
        }
        tableModel.setStudents(visible);
    }

    private void saveAndRefresh() {
        FileHandler.save(manager.getStudents());
        refreshTable();
    }

    private Student getSelectedStudent() {
        int row = table.getSelectedRow();
        if (row < 0) {
            DialogHelper.info(frame, "Select a student in the table first.");
            return null;
        }
        return tableModel.getStudentAt(row);
    }

    private void addStudent() {
        String id = DialogHelper.promptStudentId(frame, "Student ID (e.g. 2026-0001):");
        if (id == null) {
            return;
        }
        if (manager.findById(id) != null) {
            DialogHelper.warn(frame, "A student with ID " + id + " already exists.");
            return;
        }
        String name = DialogHelper.promptFileSafe(frame, "Full name:");
        if (name == null) {
            return;
        }
        String course = DialogHelper.promptFileSafe(frame, "Course/Section:");
        if (course == null) {
            return;
        }
        manager.getStudents().add(new Student(id, name, course));
        saveAndRefresh();
    }

    private void recordGrade() {
        Student student = getSelectedStudent();
        if (student == null) {
            return;
        }
        boolean addMore = true;
        while (addMore) {
            String subject = DialogHelper.promptFileSafe(frame,
                    "Subject/assessment name for " + student.getName() + ":");
            if (subject == null) {
                break;
            }
            Double score = DialogHelper.promptScore(frame, "Score for " + subject + " (0-100):");
            if (score == null) {
                break;
            }
            student.addGrade(new Grade(subject, score));
            addMore = DialogHelper.confirm(frame, "Recorded " + subject + " = " + score
                    + ".\nAdd another grade for " + student.getName() + "?");
        }
        saveAndRefresh();
    }

    private void viewDetails() {
        Student student = getSelectedStudent();
        if (student == null) {
            return;
        }
        StringBuilder text = new StringBuilder();
        text.append("Record for ").append(student.getName())
            .append(" (").append(student.getId()).append(")\n");
        text.append("Course/Section: ").append(student.getCourse()).append("\n\n");
        if (student.getGrades().isEmpty()) {
            text.append("No grades recorded yet.");
        } else {
            text.append(String.format("%-28s %8s%n", "Subject/Assessment", "Score"));
            text.append("-".repeat(38)).append("\n");
            for (Grade grade : student.getGrades()) {
                text.append(String.format("%-28s %8.1f%n", grade.getSubject(), grade.getScore()));
            }
            double average = ReportGenerator.computeAverage(student);
            text.append("-".repeat(38)).append("\n");
            text.append(String.format("Average: %.2f   Letter: %s   Status: %s",
                    average, ReportGenerator.letterGrade(average),
                    ReportGenerator.passOrFail(average)));
        }
        showMonospaceDialog("Student Record", text.toString());
    }

    private void updateStudent() {
        Student student = getSelectedStudent();
        if (student == null) {
            return;
        }
        String[] options = {"Name", "Course/Section"};
        int choice = JOptionPane.showOptionDialog(frame,
                "What do you want to update for " + student.getName() + "?",
                "Update Student", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (choice == 0) {
            String name = DialogHelper.promptFileSafe(frame, "New name:");
            if (name != null) {
                student.setName(name);
            }
        } else if (choice == 1) {
            String course = DialogHelper.promptFileSafe(frame, "New course/section:");
            if (course != null) {
                student.setCourse(course);
            }
        }
        saveAndRefresh();
    }

    private void deleteStudent() {
        Student student = getSelectedStudent();
        if (student == null) {
            return;
        }
        boolean sure = DialogHelper.confirm(frame, "Really delete " + student.getName()
                + " (" + student.getId() + ") and all their grades?");
        if (sure) {
            manager.getStudents().remove(student);
            saveAndRefresh();
        }
    }

    private void showClassStats() {
        ArrayList<Student> graded = new ArrayList<>();
        for (Student student : manager.getStudents()) {
            if (!student.getGrades().isEmpty()) {
                graded.add(student);
            }
        }
        if (graded.isEmpty()) {
            DialogHelper.info(frame, "No grades recorded yet, so there is nothing to compute.");
            return;
        }
        Student highest = graded.get(0);
        Student lowest = graded.get(0);
        double totalOfAverages = 0;
        for (Student student : graded) {
            double average = ReportGenerator.computeAverage(student);
            totalOfAverages += average;
            if (average > ReportGenerator.computeAverage(highest)) {
                highest = student;
            }
            if (average < ReportGenerator.computeAverage(lowest)) {
                lowest = student;
            }
        }
        String text = String.format(
                "Students with grades: %d%n"
                + "Class average:        %.2f%n"
                + "Highest average:      %.2f (%s)%n"
                + "Lowest average:       %.2f (%s)",
                graded.size(), totalOfAverages / graded.size(),
                ReportGenerator.computeAverage(highest), highest.getName(),
                ReportGenerator.computeAverage(lowest), lowest.getName());
        showMonospaceDialog("Class Statistics", text);
    }

    private void showMonospaceDialog(String title, String text) {
        JTextArea area = new JTextArea(text);
        area.setEditable(false);
        area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        JOptionPane.showMessageDialog(frame, new JScrollPane(area), title,
                JOptionPane.PLAIN_MESSAGE);
    }
}
