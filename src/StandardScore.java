public class StandardScore {
    private final String standardCode;
    private final String standardName;
    private double masteryLevel; // 1.0 = Emerging, 2.0 = Developing, 3.0 = Proficient, 4.0 = Exemplary

    public StandardScore(String standardCode, String standardName, double masteryLevel) {
        if (masteryLevel < 1.0 || masteryLevel > 4.0) {
            throw new IllegalArgumentException("Mastery level must be between 1.0 and 4.0.");
        }
        this.standardCode = standardCode;
        this.standardName = standardName;
        this.masteryLevel = masteryLevel;
    }

    public String getStandardCode() {
        return standardCode;
    }

    public String getStandardName() {
        return standardName;
    }

    public double getMasteryLevel() {
        return masteryLevel;
    }

    public void updateScore(double newLevel) {
        if (newLevel < 1.0 || newLevel > 4.0) {
            throw new IllegalArgumentException("Mastery level must be between 1.0 and 4.0.");
        }
        // Standards-based grading tracks growth by retaining the highest demonstrated mastery
        if (newLevel > this.masteryLevel) {
            this.masteryLevel = newLevel;
        }
    }

    public String getProficiencyLabel() {
        if (masteryLevel >= 3.5) return "Exemplary";
        if (masteryLevel >= 3.0) return "Proficient";
        if (masteryLevel >= 2.0) return "Developing";
        return "Emerging";
    }

    @Override
    public String toString() {
        return String.format("[%s] %-22s : %.1f (%s)",
                standardCode, standardName, masteryLevel, getProficiencyLabel());
    }
}
