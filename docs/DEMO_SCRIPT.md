# Recorded Demo Script (read-aloud, 3 members, 10 minutes)

Everything in **"quotes"** is meant to be read out loud, word for word.
Anything in **[brackets]** is a stage direction — what to click or do, not
something to say. Swap in your real names where it says _(Name)_.

---

## 1. Project Overview — 1:00 (Leader)

> "Hi, we're _(Name 1)_, _(Name 2)_, and _(Name 3)_, and this is our
> Student Grade Management System, written in Java.
>
> The program lets a teacher store student records, record their grades,
> automatically compute their averages and pass-or-fail status, and save
> everything to a file so the data is still there the next time you open
> it.
>
> It uses all the core programming concepts we learned: variables,
> conditions, loops, lists, methods, input validation, and file handling.
>
> It also has two versions that share the same code — a text version and a
> simple window version. We'll demonstrate the window version now."

---

## 2. Live System Demonstration — 3:00 (Member 3 clicks, members narrate)

[Run `java -cp out MainGUI`. The main window opens.]

> "This is the main window. Each row is one student, and the columns show
> their average, letter grade, and pass-or-fail status."

[Click **Add Student**. Add the first student.]

> "We'll add a student. It asks for an ID, a name, and a course."

[Click **Add Student** again, type the SAME ID as before.]

> "Now watch what happens if we try to add a student with an ID that
> already exists — the program rejects it instead of creating a duplicate."

[Select a student, click **Record Grade**. Type `abc` for the score.]

> "When we record a grade, we'll type letters instead of a number on
> purpose — the program catches it and asks again."

[Now type `150`.]

> "If we type a score above 100, it also refuses it."

[Now type a valid score like `90`, add one or two more.]

> "Now we'll enter valid scores, and they're saved."

[Click **View Details**.]

> "View Details shows the student's full breakdown, with their average and
> letter grade."

[Click **Class Stats**.]

> "Class Stats shows the highest, the lowest, and the class average."

[Type a name in the search box, then click **Delete** on a student and confirm.]

> "We can search for a student by name, and delete one — notice it asks us
> to confirm first."

[Close the window. Reopen with `java -cp out MainGUI`.]

> "Finally, we'll close the program completely and open it again — and our
> students are still here, because everything was saved to a file."

---

## 3. Code — Data & Validation — 1:30 (Member 1)

> "I worked on the four files that store the data and check the user's input.
>
> `Grade.java` holds one subject and its score. `Student.java` holds one
> student's ID, name, course, and a **list** of all their grades — that's
> the lists concept.
>
> `InputValidator.java` is for the text version and `DialogHelper.java` is
> for the window version. Both do the same job: they check every input
> using a **loop** that keeps asking until the answer is valid, so the
> program never crashes on a wrong entry.
>
> For example, when you type a score, it checks two **conditions** — that
> it's actually a number, and that it's between 0 and 100.
>
> One decision we made: we block commas and semicolons in names, because
> those symbols are used to separate data in our save file."

---

## 4. Code — Logic & Computation — 1:30 (Member 2)

> "I worked on the three files that do the thinking and the saving.
>
> `StudentManager.java` keeps the **list** of all students and handles
> adding, updating, deleting, and searching. To find a student it uses a
> **loop** that checks each one until it finds a matching ID.
>
> `ReportGenerator.java` does the math. It uses a **loop** to add up all of
> a student's scores for the average, and a chain of **if-else conditions**
> to turn that average into a letter grade and a pass-or-fail result.
>
> `FileHandler.java` saves everything to a file called students.csv and
> loads it back when the program starts. If the file doesn't exist yet on
> the very first run, it simply starts empty instead of crashing — that's
> the file handling concept.
>
> One decision: we recompute the average each time instead of storing it,
> so it's always correct after a grade changes."

---

## 5. Code — User Interfaces — 1:30 (Member 3)

> "I worked on the three files the user actually sees.
>
> `Main.java` is the text version. It shows a menu and uses a **loop** to
> keep showing it, with a **switch** that runs the right action for whatever
> number the user picks.
>
> `MainGUI.java` is the window version. It connects each button to an
> action, and — this is the nice part — it reuses the exact same manager,
> report, and file code as the text version, so both share one set of logic.
>
> `StudentTableModel.java` is what fills the table — it turns each student
> into a row and calculates their average, letter, and status for each cell.
>
> One decision: the window version saves automatically after every change,
> so you can never lose your work."

---

## 6. Closing — 1:00 (Leader)

> "To wrap up — _(Name 1)_ learned about validating input, _(Name 2)_ about
> doing calculations and saving files, and _(Name 3)_ about building the
> interface.
>
> We tested the program with the cases listed in our test plan, including
> one bug we found and fixed where _(describe the bug in one sentence)_.
>
> We used an AI tool to help with parts of the code, and every member
> reviewed and understands the part they presented.
>
> Thanks for watching."
