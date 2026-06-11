# Recorded Demo Script (10–15 minutes)

Every member appears on camera/voice for their own section.

## 1. Project Overview (~2 min) — whole group / leader
- Introduce the team and the project: a Student Grade Management System in Java.
- State the problem it solves: tracking student records and grades with persistent storage.
- List the programming concepts it demonstrates: variables, conditionals, loops, ArrayLists, methods, input validation, file handling, data management.

## 2. Live System Demonstration (~4 min)
Run the program and show, in order:
1. First launch (empty data) and the main menu.
2. Adding two students — deliberately try a **duplicate ID** and an **empty name** to show validation.
3. Recording grades — deliberately enter `abc` and `150` to show score validation, then valid scores.
4. View all students (table), view one student (breakdown with letter grade).
5. Class statistics.
6. Search, update, and delete (show the confirmation prompt).
7. Exit with save, then **restart the program** to prove the data persisted.

## 3. Code Walkthrough (~1.5 min per member)
Each member explains their own file(s): what it does, one interesting
decision, and where a required concept appears.

- **Member 1** — `Grade.java`, `Student.java`, `InputValidator.java`: the data model and why every input goes through a validation loop.
- **Member 2** — `StudentManager.java`: the ArrayList of students, linear search in `findById`, and the add/update/delete operations.
- **Member 3** — `ReportGenerator.java`: the average loop, the if/else-if chain for letter grades, and `printf` table formatting.
- **Member 4** — `FileHandler.java`: the CSV-style file format, try-with-resources, and how a missing file is handled on first run.
- **Member 5** — `Main.java`: the menu loop, the switch statement, and how the pieces connect.

(For groups smaller than 5, combine sections per the contribution report.)

## 4. Closing (~1 min)
- Summarize what was learned (teamwork, validation, persistence).
- Mention testing: point to the test plan and one bug you found and fixed.
- Disclose AI assistance per the course policy.
