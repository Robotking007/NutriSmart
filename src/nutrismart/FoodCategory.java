package nutrismart;

/**
 * Enum representing the category of a food item.
 */
public enum FoodCategory {
    FRUIT("Fruit"),
    PROTEIN("Protein"),
    VEGETABLE("Vegetable"),
    GRAIN("Grain"),
    DAIRY("Dairy");

    private final String displayName;

    FoodCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
