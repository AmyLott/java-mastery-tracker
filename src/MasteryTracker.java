import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MasteryTracker {
    private final List<Student> roster = new ArrayList<>();

    public void seedSampleData() {
        Student s1 = new Student("CS-101", "Jordan Taylor");
        s1.recordAssessment("CS.1", "Variables & Data Types", 4.0);
        s1.recordAssessment("CS.2", "Boolean Logic & Conditionals", 3.5);
        s1.recordAssessment("CS.3", "Iteration & Loops", 3.0);

        Student s2 = new Student("CS-102", "Maya Patel");
        s2.recordAssessment("CS.1", "Variables & Data Types", 3.0);
        s2.recordAssessment("CS.2", "Boolean Logic & Conditionals", 2.0);
        s2.recordAssessment("CS.3", "Iteration & Loops", 1.5);

        Student s3 = new Student("CS-103", "Liam Brooks");
        s3.recordAssessment("CS.1", "Variables & Data Types", 3.5);
        s3.recordAssessment("CS.2", "Boolean Logic & Conditionals", 3.0);
        s3.recordAssessment("CS.3", "Iteration & Loops", 2.5);

        roster.add(s1);
        roster.add(s2);
        roster.add(s3);
    }

    public void printRosterReport() {
        System.out.println("\n=== CLASSROOM MASTERY REPORT ===");
        for (Student s : roster) {
            System.out.printf("\n%s (%s) | Avg: %.2f | Intervention Needed: %s%n",
                    s.getFullName(), s.getStudentId(),
                    s.calculateOverallAverage(),
                    s.needsIntervention() ? "YES" : "No");
            for (StandardScore score : s.getAllScores()) {
                System.out.println("   " + score);
            }
        }
    }

    public void exportToCsv(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("StudentID,StudentName,StandardCode,StandardName,MasteryLevel,Proficiency");
            for (Student s : roster) {
                for (StandardScore score : s.getAllScores()) {
                    writer.printf("%s,\"%s\",%s,\"%s\",%.1f,%s%n",
                            s.getStudentId(),
                            s.getFullName(),
                            score.getStandardCode(),
                            score.getStandardName(),
                            score.getMasteryLevel(),
                            score.getProficiencyLabel());
                }
            }
            System.out.println("Exported mastery report to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing CSV file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        MasteryTracker tracker = new MasteryTracker();
        tracker.seedSampleData();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Java Standards-Based Mastery Tracker ---");
            System.out.println("1. View Full Classroom Report");
            System.out.println("2. Record / Update Student Mastery");
            System.out.println("3. Export Report to CSV");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    tracker.printRosterReport();
                    break;
                case "2":
                    System.out.print("Enter Student ID (e.g., CS-102): ");
                    String id = scanner.nextLine().trim();
                    Student target = tracker.roster.stream()
                            .filter(s -> s.getStudentId().equalsIgnoreCase(id))
                            .findFirst()
                            .orElse(null);
                    if (target == null) {
                        System.out.println("Student ID not found.");
                        break;
                    }
                    System.out.print("Standard Code (e.g., CS.3): ");
                    String code = scanner.nextLine().trim().toUpperCase();
                    System.out.print("Standard Name (e.g., Iteration & Loops): ");
                    String name = scanner.nextLine().trim();
                    System.out.print("New Mastery Level (1.0 - 4.0): ");
                    try {
                        double level = Double.parseDouble(scanner.nextLine().trim());
                        target.recordAssessment(code, name, level);
                        System.out.println("Record updated for " + target.getFullName() + ".");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid input: " + e.getMessage());
                    }
                    break;
                case "3":
                    tracker.exportToCsv("mastery_report.csv");
                    break;
                case "4":
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please choose 1–4.");
            }
        }
    }
}
