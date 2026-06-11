# Test Plan & Testing Evidence

Each test was performed manually against the compiled program.
Add a screenshot for each case as testing evidence.

| # | Test Case | Input | Expected Result | Actual Result | Pass? |
|---|---|---|---|---|---|
| 1 | First run with no data file | Start program with no `students.csv` | "No saved data found" message; empty list; no crash | As expected | ✅ |
| 2 | Add a valid student | ID `2026-0001`, name `Maria Santos`, course `BSIT 1A` | Student added confirmation | As expected | ✅ |
| 3 | Duplicate student ID | Add another student with ID `2026-0001` | Rejected with "already exists" message | As expected | ✅ |
| 4 | Empty input field | Press Enter on the name prompt | "Cannot be empty" message, re-prompt | As expected | ✅ |
| 5 | Non-numeric grade | Score `abc` | "Not a number" message, re-prompt | As expected | ✅ |
| 6 | Out-of-range grade | Score `150` | "Must be between 0 and 100" message, re-prompt | As expected | ✅ |
| 7 | Boundary scores | Scores `0` and `100` | Both accepted | As expected | ✅ |
| 8 | Grade for unknown student | Record grades for ID `9999-9999` | "No student found" message, return to menu | As expected | ✅ |
| 9 | Invalid menu choice | Menu option `9` / random text | "Invalid option" message, re-prompt | As expected | ✅ |
| 10 | Average & letter grade | Scores 92 and 85.5 | Average 88.75, letter B, PASS | As expected | ✅ |
| 11 | Class statistics | Two students with different averages | Correct highest, lowest, class average | As expected | ✅ |
| 12 | Statistics with no grades | Run statistics before any grades exist | "Nothing to compute" message, no crash | As expected | ✅ |
| 13 | Delete with confirmation | Delete a student, answer `n` then `y` | `n` cancels; `y` removes the student | As expected | ✅ |
| 14 | Search partial match | Search `maria` | Matching students shown in table | As expected | ✅ |
| 15 | Data persistence | Exit with option 0, restart program | Records reloaded from `students.csv` | As expected | ✅ |
| 16 | Student with no grades in table | View all with a freshly added student | Average and status shown as `--`, not 0/FAIL | As expected | ✅ |
| 17 | GUI: invalid score dialog | In Record Grade dialog enter `abc`, then `150` | Warning pop-up each time, dialog re-asks | _run on a machine with a display_ | ⬜ |
| 18 | GUI: cancel backs out | Press Cancel in the middle of Add Student | No partial student is created | _run on a machine with a display_ | ⬜ |
| 19 | GUI: button with no row selected | Click Record Grade with nothing selected | "Select a student first" message, no crash | _run on a machine with a display_ | ⬜ |
| 20 | GUI/console share data | Add a student in the GUI, close it, open the console version | Student appears in the console table | _run on a machine with a display_ | ⬜ |

## Edge Cases Considered

- Missing data file on first run (handled — starts empty).
- Corrupted line in the data file (handled — line is skipped with a warning).
- Names containing `,` `;` `=` are rejected at input time because those characters are file separators.
