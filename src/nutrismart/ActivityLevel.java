package nutrismart;

/**
 * Enum representing user activity level, used for daily calorie goal calculation.
 */
public enum ActivityLevel {
    SEDENTARY("Sedentary (little or no exercise)", 1.2),
    LIGHT("Lightly active (light exercise 1-3 days/week)", 1.375),
    MODERATE("Moderately active (moderate exercise 3-5 days/week)", 1.55),
    ACTIVE("Very active (hard exercise 6-7 days/week)", 1.725),
    EXTRA_ACTIVE("Extra active (very hard exercise & physical job)", 1.9);

    private final String description;
    private final double multiplier;

    ActivityLevel(String description, double multiplier) {
        this.description = description;
        this.multiplier = multiplier;
    }

    public String getDescription() {
        return description;
    }

    public double getMultiplier() {
        return multiplier;
    }

    @Override
    public String toString() {
        return description;
    }
}
