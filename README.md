# Student Grade Management System

A Java application for managing student records and grades, with **two
interfaces sharing the same logic and data file**: a console version and
a simple Swing GUI. Built as a group programming project demonstrating
fundamental concepts: variables, conditionals, loops, ArrayLists,
methods, input validation, file handling, and basic data management.

## Features

1. **Add a student** — name, unique student ID, and course/section.
2. **Record grades** — subject/assessment scores, validated to be numeric and 0–100.
3. **View all students** — formatted table with grade count, average, and pass/fail status.
4. **View one student** — full grade breakdown with average and letter grade.
5. **Class statistics** — highest average, lowest average, and class average.
6. **Update a student** — change a name or course/section.
7. **Delete a student** — with confirmation before anything is removed.
8. **Search** — by partial ID or name.
9. **File handling** — records are saved to `students.csv` on exit and loaded on startup, so data persists between runs.

Every input is validated: empty fields, non-numeric grades, out-of-range
scores, duplicate IDs, and invalid menu choices all produce a friendly
message and a re-prompt — the program never crashes on bad input.

## How to Run

Requires Java 8 or newer (tested on Java 21). Compile once:

```
javac -d out src/*.java
```

Then run **either** interface:

```
java -cp out MainGUI    # graphical version (recommended for the demo)
java -cp out Main       # console version
```

Both versions read and write the same `students.csv`, so records added
in one appear in the other. On the first run there is no saved data, so
the system starts empty. The GUI saves automatically after every change
and when the window is closed; the console saves when you choose
**0 (Save and exit)**.

### GUI quick guide

- The table shows every student with their average, letter grade, and status.
- **Add Student** asks for ID, name, and course through pop-up dialogs.
- For **Record Grade / View Details / Update / Delete**, click a row in
  the table first, then click the button.
- Type in the search box and press Enter (or click **Search**) to filter;
  **Show All** clears the filter.
- Invalid input (empty fields, duplicate IDs, non-numeric or out-of-range
  scores) pops up a warning and asks again; Cancel backs out safely.

## Project Structure

| File | Responsibility | Suggested owner |
|---|---|---|
| `src/Grade.java` | One subject + score entry | Member 1 |
| `src/Student.java` | Student data model | Member 1 |
| `src/InputValidator.java` | All input validation | Member 1 |
| `src/StudentManager.java` | Add / update / delete / search / record grades | Member 2 |
| `src/ReportGenerator.java` | Averages, letter grades, tables, class statistics | Member 3 |
| `src/FileHandler.java` | Saving and loading `students.csv` | Member 4 |
| `src/Main.java` | Console menu loop and program flow | Member 5 / shared |
| `src/MainGUI.java` | Swing window, buttons, and search bar | Member 5 / shared |
| `src/StudentTableModel.java` | Feeds student rows into the GUI table | Member 5 / shared |
| `src/DialogHelper.java` | Pop-up dialogs with the same validation rules | Member 5 / shared |

## Grading Logic

- **Average** = mean of all recorded scores for the student.
- **Letter grade**: A ≥ 90, B ≥ 80, C ≥ 70, D ≥ 60, F below 60.
- **Pass/fail**: PASS when the average is 75 or higher.

## Screenshots

> _Insert screenshots here for the documentation requirement:_
> 1. Main menu on startup
> 2. Adding a student (including a rejected duplicate ID)
> 3. Recording grades (including a rejected invalid score)
> 4. The "View all students" table
> 5. Class statistics
> 6. The contents of `students.csv` after exiting

## Other Documents

- [`docs/TEST_PLAN.md`](docs/TEST_PLAN.md) — test cases and results (testing evidence)
- [`docs/DEMO_SCRIPT.md`](docs/DEMO_SCRIPT.md) — outline for the recorded presentation
- [`docs/CONTRIBUTION_REPORT.md`](docs/CONTRIBUTION_REPORT.md) — member contribution template

## AI Use Disclosure

Portions of this project were developed with the assistance of an AI tool,
in line with the course policy on responsible AI use. All members have
reviewed the code and can explain their assigned parts.
