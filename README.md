# Java Standards-Based Mastery Tracker

A Java command-line application built to track high school Computer Science student proficiency across specific programming standards rather than relying on single-exam averages.

## Key Features
- **Growth-Oriented Grading Logic:** Retains a student's highest demonstrated mastery level (`1.0` to `4.0`) as they reassess on core CS concepts.
- **Automated Intervention Flagging:** Identifies students scoring below `2.5` on any foundational standard for targeted small-group support.
- **CSV Data Export:** Generates clean, spreadsheet-ready reports (`mastery_report.csv`) for gradebook integration.
- **Clean Object-Oriented Architecture:** Separates domain models (`StandardScore`, `Student`) from application logic (`MasteryTracker`).

## How to Compile & Run
```bash
git clone [https://github.com/AmyLott/java-mastery-tracker.git](https://github.com/AmyLott/java-mastery-tracker.git)
cd java-mastery-tracker/src
javac *.java
java MasteryTracker
