# Project Documentation

> This file is formatted to follow Section 7 (Documentation Guidelines).
> Fill in every **[blank]**, paste your screenshots where marked, and put
> in your real names before submitting. You can paste this straight into
> Microsoft Word and apply your school's required font and spacing.

---

## 1. Project Title

**Student Grade Management System**
A Java application with both a console and a graphical (GUI) interface.

Submitted by: **[Name 1], [Name 2], [Name 3]**
Course / Section: **[fill in]**
Instructor: **[fill in]**
Date: **[fill in]**

---

## 2. Introduction

Teachers handle many students and many grades, and doing this by hand or
on scattered paper is slow and easy to get wrong. Our project, the Student
Grade Management System, is a simple program that keeps all student records
in one place.

The user can add students, record their grades, and the program
automatically computes each student's average, letter grade, and whether
they passed or failed. All the data is saved to a file, so the records are
still there the next time the program is opened.

We built the program in Java in two versions that share the same underlying
code: a text-based console version and a simple window-based (GUI) version.
The project demonstrates the core programming concepts we studied:
variables, conditions, loops, lists, methods, input validation, and file
handling.

---

## 3. Objectives

**General objective:** To design and build a working program that stores
and manages student grades while applying fundamental programming concepts.

**Specific objectives:**
1. To store student records (ID, name, course) and their grades.
2. To validate all user input so the program does not crash on wrong entries.
3. To automatically compute each student's average, letter grade, and
   pass/fail status.
4. To produce class-wide statistics (highest, lowest, and class average).
5. To save records to a file and load them back so data is not lost.
6. To provide a clear, easy-to-use interface for the user.

---

## 4. Scope and Limitations

**Scope (what the program can do):**
- Add, view, update, delete, and search student records.
- Record multiple grades per student.
- Compute averages, letter grades, pass/fail, and class statistics.
- Validate all input (no empty fields, no duplicate IDs, scores 0–100 only).
- Save and load data automatically using a file (`students.csv`).
- Run in either a console or a graphical window.

**Limitations (what the program does not do):**
- It is a single-computer program; it does not connect to the internet or a
  database server.
- It does not have user accounts, passwords, or login security.
- Data is stored in a simple text file, not encrypted.
- Grades are treated equally (no weighting for exams vs. quizzes).
- It is designed for one class at a time, not an entire school.

---

## 5. System Features

| Feature | What it does |
|---|---|
| Add Student | Saves a new student with a unique ID, name, and course. |
| Record Grade | Adds one or more subject scores (0–100) to a student. |
| View All Students | Shows a table of all students with average and status. |
| View Details | Shows one student's full grade breakdown and letter grade. |
| Class Statistics | Shows the highest, lowest, and class-average grade. |
| Update Student | Changes a student's name or course. |
| Delete Student | Removes a student after a confirmation prompt. |
| Search | Finds students by part of their ID or name. |
| Input Validation | Rejects empty fields, duplicate IDs, and invalid scores. |
| Save / Load | Stores all records in a file and reloads them on startup. |
| Two Interfaces | Runs as a console program or a simple window (GUI). |

---

## 6. Flowchart / Algorithm

### Algorithm (main program, in plain steps)

1. START
2. Load saved students from the file (if the file exists).
3. Show the main menu.
4. Ask the user to choose an option.
5. IF the choice is **Add**, ask for ID/name/course, check the ID is not a
   duplicate, and add the student.
6. IF the choice is **Record Grade**, ask for a subject and a score,
   validate the score, and add it to the chosen student.
7. IF the choice is **View / Stats / Search**, display the requested data.
8. IF the choice is **Update / Delete**, change or remove the chosen
   student (Delete asks for confirmation first).
9. IF the choice is **Exit**, save all students to the file and stop.
10. Otherwise, show "invalid option" and go back to step 3.
11. After any action, go back to step 3.
12. END

### Flowchart (main loop)

```
        +-------------------+
        |      START        |
        +-------------------+
                 |
                 v
        +-------------------+
        | Load data from    |
        | students.csv      |
        +-------------------+
                 |
                 v
        +-------------------+
   +--->|  Show main menu   |
   |    +-------------------+
   |             |
   |             v
   |    +-------------------+
   |    | Get user's choice |
   |    +-------------------+
   |             |
   |             v
   |    +-------------------+      No
   |    | Is choice "Exit"? |--------------+
   |    +-------------------+              |
   |             | Yes                     v
   |             v             +-----------------------+
   |    +-------------------+  | Run the chosen action |
   |    | Save data to file |  | (add / grade / view / |
   |    +-------------------+  | update / delete / etc)|
   |             |             +-----------------------+
   |             v                         |
   |    +-------------------+              |
   |    |       END         |              |
   |    +-------------------+              |
   |                                       |
   +---------------------------------------+
```

---

## 7. Source Code

The full source code is in the `src/` folder. It is organized into 10 Java
files, each with one clear responsibility:

| File | Responsibility |
|---|---|
| `Grade.java` | Stores one subject and its score. |
| `Student.java` | Stores a student's ID, name, course, and list of grades. |
| `InputValidator.java` | Validates all console input. |
| `DialogHelper.java` | Validates all GUI (pop-up) input. |
| `StudentManager.java` | Adds, updates, deletes, and searches students. |
| `ReportGenerator.java` | Computes averages, letter grades, and statistics. |
| `FileHandler.java` | Saves records to and loads them from the file. |
| `Main.java` | Runs the console (text) interface. |
| `MainGUI.java` | Runs the graphical (window) interface. |
| `StudentTableModel.java` | Fills the GUI table with student rows. |

**How to compile and run:**

```
javac -d out src/*.java
java -cp out MainGUI     (graphical version)
java -cp out Main        (console version)
```

> For the printed documentation, paste the contents of each `.java` file
> here, or attach the `src/` folder. Keep the formatting (indentation) so
> the code stays readable.

---

## 8. Screenshots

> Paste a screenshot under each caption. These also serve as part of the
> testing evidence.

1. **Main window / menu on startup** — [paste screenshot]
2. **Adding a student** — [paste screenshot]
3. **Duplicate ID being rejected** — [paste screenshot]
4. **Invalid score (`abc` or `150`) being rejected** — [paste screenshot]
5. **Viewing one student's details with average and letter grade** — [paste screenshot]
6. **Class statistics** — [paste screenshot]
7. **Delete confirmation prompt** — [paste screenshot]
8. **The `students.csv` file contents after saving** — [paste screenshot]

---

## 9. Testing Results

We tested the program against the cases below. The full test plan is in
`docs/TEST_PLAN.md`. Add a screenshot for each as evidence.

| # | Test Case | Input | Expected Result | Pass? |
|---|---|---|---|---|
| 1 | First run, no data file | Open program | Starts empty, no crash | ✅ |
| 2 | Add a valid student | ID, name, course | Student is added | ✅ |
| 3 | Duplicate student ID | Re-use an existing ID | Rejected with a message | ✅ |
| 4 | Empty field | Leave a field blank | Rejected, asks again | ✅ |
| 5 | Non-numeric grade | `abc` | Rejected, asks again | ✅ |
| 6 | Out-of-range grade | `150` | Rejected, asks again | ✅ |
| 7 | Boundary scores | `0` and `100` | Both accepted | ✅ |
| 8 | Average & letter grade | Scores 92 and 85.5 | Average 88.75, letter B, PASS | ✅ |
| 9 | Class statistics | Several students | Correct high/low/average | ✅ |
| 10 | Delete confirmation | Choose delete, confirm | Student removed | ✅ |
| 11 | Data persistence | Exit and reopen | Records reloaded | ✅ |

**Result:** All listed cases passed. The program handled both correct and
incorrect input without crashing.

---

## 10. Individual Contributions

Full details are in `docs/CONTRIBUTION_REPORT.md`. Summary:

| Member | Part | Main Files |
|---|---|---|
| **[Name 1]** | Data & validation | `Grade`, `Student`, `InputValidator`, `DialogHelper` |
| **[Name 2]** | Logic & computation | `StudentManager`, `ReportGenerator`, `FileHandler` |
| **[Name 3]** | User interfaces & integration | `Main`, `MainGUI`, `StudentTableModel` |

> Fill in each member's exact tasks and contribution percentage (must total
> 100%) in the contribution report.

---

## 11. Problems Encountered

> Replace these with the real problems your group ran into. Examples based
> on this project:

- **Two Java versions on one computer.** Running the program gave an
  "UnsupportedClassVersion" error because an old Java 8 was installed
  alongside the new one. We fixed it by removing the old version so the
  new `java` command was used.
- **The GUI would not open in the online editor.** It gave a "No X11
  DISPLAY" error because cloud editors have no screen. We learned the
  graphical version must be run on a real computer.
- **[Add your own problem and how you solved it.]**

---

## 12. Conclusion

Through this project we built a working Student Grade Management System that
stores records, validates input, computes results, and saves data to a
file. We successfully applied the programming concepts from our course —
variables, conditions, loops, lists, methods, input validation, and file
handling — in a real, usable program.

Just as importantly, we learned how to divide work, keep our code organized
into clear parts, and test our program against both correct and incorrect
input. If we continued the project, we would add features such as login
security, weighted grades, and storing data in a proper database.
