import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Student {
    private final String studentId;
    private final String fullName;
    private final Map<String, StandardScore> standards;

    public Student(String studentId, String fullName) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.standards = new LinkedHashMap<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void recordAssessment(String code, String name, double level) {
        if (standards.containsKey(code)) {
            standards.get(code).updateScore(level);
        } else {
            standards.put(code, new StandardScore(code, name, level));
        }
    }

    public Collection<StandardScore> getAllScores() {
        return standards.values();
    }

    public double calculateOverallAverage() {
        if (standards.isEmpty()) return 0.0;
        double sum = 0.0;
        for (StandardScore score : standards.values()) {
            sum += score.getMasteryLevel();
        }
        return sum / standards.size();
    }

    public boolean needsIntervention() {
        for (StandardScore score : standards.values()) {
            if (score.getMasteryLevel() < 2.5) {
                return true;
            }
        }
        return false;
    }
}
