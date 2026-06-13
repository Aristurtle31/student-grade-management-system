import java.util.Scanner;

public class InputValidator {
    private static final Scanner scanner = new Scanner(System.in);
    public static String getNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("  [!] This field cannot be empty. Please try again.");
        }
    }

    public static String getFileSafeString(String prompt) {
        while (true) {
            String value = getNonEmptyString(prompt);
            if (!value.contains(",") && !value.contains(";") && !value.contains("=")) {
                return value;
            }
            System.out.println("  [!] Please avoid the characters , ; = in this field.");
        }
    }

    public static String getValidStudentId(String prompt) {
        while (true) {
            String id = getNonEmptyString(prompt);
            if (id.replace("-", "").matches("[A-Za-z0-9]+")) {
                return id.toUpperCase();
            }
            System.out.println("  [!] IDs may only contain letters, numbers, and dashes.");
        }
    }

    public static double getValidScore(String prompt) {
        while (true) {
            System.out.print(prompt);
            String raw = scanner.nextLine().trim();
            double score;
            try {
                score = Double.parseDouble(raw);
            } catch (NumberFormatException e) {
                System.out.println("  [!] '" + raw + "' is not a number. Enter a score like 87.5.");
                continue;
            }
            if (score >= 0 && score <= 100) {
                return score;
            }
            System.out.println("  [!] Scores must be between 0 and 100.");
        }
    }

    public static String getMenuChoice(String prompt, String[] validChoices) {
        while (true) {
            System.out.print(prompt);
            String choice = scanner.nextLine().trim();
            for (String valid : validChoices) {
                if (choice.equals(valid)) {
                    return choice;
                }
            }
            System.out.println("  [!] Invalid option. Choose one of: " + String.join(", ", validChoices));
        }
    }

    public static boolean confirm(String prompt) {
        System.out.print(prompt + " (y/n): ");
        String answer = scanner.nextLine().trim().toLowerCase();
        return answer.equals("y");
    }
}
