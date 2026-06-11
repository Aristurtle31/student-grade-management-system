import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * Pop-up dialog versions of the input validation rules. Same rules as
 * the console InputValidator: nothing empty, no file-separator
 * characters, IDs are letters/numbers/dashes, scores are 0-100.
 *
 * Every prompt returns null if the user presses Cancel, so callers can
 * back out of any operation safely.
 *
 * Suggested owner: Member 5 (GUI), or shared.
 */
public class DialogHelper {

    /** Keeps asking until the user types something that isn't blank. */
    public static String promptNonEmpty(Component parent, String message) {
        while (true) {
            String value = JOptionPane.showInputDialog(parent, message);
            if (value == null) {
                return null;
            }
            value = value.trim();
            if (!value.isEmpty()) {
                return value;
            }
            warn(parent, "This field cannot be empty.");
        }
    }

    /** Also rejects , ; = because those are separators in the save file. */
    public static String promptFileSafe(Component parent, String message) {
        while (true) {
            String value = promptNonEmpty(parent, message);
            if (value == null) {
                return null;
            }
            if (!value.contains(",") && !value.contains(";") && !value.contains("=")) {
                return value;
            }
            warn(parent, "Please avoid the characters , ; = in this field.");
        }
    }

    /** Student IDs may contain letters, numbers, and dashes (e.g. 2026-0001). */
    public static String promptStudentId(Component parent, String message) {
        while (true) {
            String id = promptNonEmpty(parent, message);
            if (id == null) {
                return null;
            }
            if (id.replace("-", "").matches("[A-Za-z0-9]+")) {
                return id.toUpperCase();
            }
            warn(parent, "IDs may only contain letters, numbers, and dashes.");
        }
    }

    /** Keeps asking until the user enters a number between 0 and 100. */
    public static Double promptScore(Component parent, String message) {
        while (true) {
            String raw = promptNonEmpty(parent, message);
            if (raw == null) {
                return null;
            }
            double score;
            try {
                score = Double.parseDouble(raw);
            } catch (NumberFormatException e) {
                warn(parent, "'" + raw + "' is not a number. Enter a score like 87.5.");
                continue;
            }
            if (score >= 0 && score <= 100) {
                return score;
            }
            warn(parent, "Scores must be between 0 and 100.");
        }
    }

    /** Asks a yes/no question. Returns true only for an explicit Yes. */
    public static boolean confirm(Component parent, String message) {
        int answer = JOptionPane.showConfirmDialog(parent, message, "Please confirm",
                JOptionPane.YES_NO_OPTION);
        return answer == JOptionPane.YES_OPTION;
    }

    public static void info(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void warn(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Invalid input", JOptionPane.WARNING_MESSAGE);
    }
}
