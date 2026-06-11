import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Feeds student data into the JTable on the main window. Each row is one
 * student; the average, letter grade, and status columns are computed
 * live from ReportGenerator so the table always matches the console output.
 *
 * Suggested owner: Member 5 (GUI), or shared.
 */
public class StudentTableModel extends AbstractTableModel {
    private static final String[] COLUMNS =
            {"ID", "Name", "Course", "Grades", "Average", "Letter", "Status"};

    private ArrayList<Student> visibleStudents = new ArrayList<>();

    /** Replaces the rows shown in the table (used for search filtering too). */
    public void setStudents(ArrayList<Student> students) {
        visibleStudents = students;
        fireTableDataChanged();
    }

    public Student getStudentAt(int row) {
        return visibleStudents.get(row);
    }

    @Override
    public int getRowCount() {
        return visibleStudents.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Student student = visibleStudents.get(row);
        boolean noGrades = student.getGrades().isEmpty();
        double average = ReportGenerator.computeAverage(student);
        switch (column) {
            case 0: return student.getId();
            case 1: return student.getName();
            case 2: return student.getCourse();
            case 3: return student.getGrades().size();
            case 4: return noGrades ? "--" : String.format("%.1f", average);
            case 5: return noGrades ? "--" : ReportGenerator.letterGrade(average);
            default: return noGrades ? "--" : ReportGenerator.passOrFail(average);
        }
    }
}
