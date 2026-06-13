# Recorded Demo Script (10-minute hard limit, 3 members)

This version is written for a **3-person team** and a strict **10-minute**
recording. Every member appears on camera/voice for their own section.
The three code segments are split so each person owns a clear area and
can explain it without overlapping the others.

## Timing Table (totals 9:30, leaving ~0:30 cushion)

| Segment | Who | Time | Running total |
|---|---|---|---|
| 1. Project overview | All / leader | 1:00 | 1:00 |
| 2. Live demonstration | Member 3 drives, all narrate | 3:00 | 4:00 |
| 3. Code: data & validation | Member 1 | 1:30 | 5:30 |
| 4. Code: logic & computation | Member 2 | 1:30 | 7:00 |
| 5. Code: user interfaces | Member 3 | 1:30 | 8:30 |
| 6. Closing | All / leader | 1:00 | 9:30 |

> Tip: rehearse once with a timer. If a segment runs long, the easiest
> cut is to show **one** validation example in the demo instead of two.

## 1. Project Overview — 1:00 (all / leader)
- Introduce the team (3 members) and the project: a Student Grade Management System in Java.
- State the problem it solves: storing student records and grades, computing results, and saving everything to a file so data survives between runs.
- One sentence on the concepts used: variables, conditionals, loops, arrays/lists, methods, input validation, and file handling.
- Mention it has **two interfaces** (console and a simple GUI) that share the same logic — a good selling point.

## 2. Live System Demonstration — 3:00 (Member 3 drives, each member narrates their own part)
Run the GUI (`java -cp out MainGUI`) and show, in order:
1. First launch and the main window/table.
2. **Add Student** — add two students; deliberately try a **duplicate ID** to show it gets rejected.
3. **Record Grade** — deliberately enter `abc` and `150` to show score validation, then valid scores.
4. **View Details** — show one student's breakdown with average and letter grade.
5. **Class Stats** — show highest, lowest, and class average.
6. **Search**, then **Delete** (show the confirmation prompt).
7. Close the window, then **reopen the program** to prove the data persisted to `students.csv`.

## 3. Code Segment — Data & Validation — 1:30 (Member 1)
Owns: `Grade.java`, `Student.java`, `InputValidator.java`, `DialogHelper.java`
- `Grade` and `Student`: the data model. Point out the **ArrayList of grades** inside each student (arrays/lists concept).
- `InputValidator` / `DialogHelper`: explain the **validation loop** — every input keeps asking until it is valid, so the program never crashes on bad input.
- Show one concrete rule: scores must parse as a number **and** fall between 0 and 100 (conditionals).
- One design decision: why a single shared `Scanner` is used, or why names with `,` `;` `=` are blocked (they are file separators).

## 4. Code Segment — Logic & Computation — 1:30 (Member 2)
Owns: `StudentManager.java`, `ReportGenerator.java`, `FileHandler.java`
- `StudentManager`: the **ArrayList of students** and the **linear search** in `findById` (loop + conditional); the add/update/delete/search operations.
- `ReportGenerator`: the **loop** that sums scores for an average, and the **if/else-if chain** that turns an average into a letter grade and pass/fail.
- `FileHandler`: the CSV-style save format (`id,name,course,subject=score;...`) and how a **missing file on first run** is handled gracefully (file handling).
- One design decision: why the average and letter grade are computed on the fly instead of being stored.

## 5. Code Segment — User Interfaces — 1:30 (Member 3)
Owns: `Main.java`, `MainGUI.java`, `StudentTableModel.java`
- `Main`: the console **menu loop** and the **switch statement** that routes each menu choice (loop + conditionals).
- `MainGUI`: how the Swing window wires buttons to actions, and that it reuses the exact same `StudentManager`, `ReportGenerator`, and `FileHandler` — so both interfaces share one set of logic.
- `StudentTableModel`: how each student becomes a row in the table, with average/letter/status computed per cell.
- One design decision: why data auto-saves after every change in the GUI.

## 6. Closing — 1:00 (all / leader)
- Each member says one sentence on what they learned (teamwork, validation, persistence).
- Mention testing: point to `docs/TEST_PLAN.md` and one bug you found and fixed.
- Disclose AI assistance per the course's academic integrity policy.
- Thank the viewer.
