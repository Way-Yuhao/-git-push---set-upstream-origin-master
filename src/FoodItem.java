import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class represents a food item with all its properties.
 *
 * @author aka
 */
public class FoodItem {
    // The name of the food item.
    private String name;

    // The id of the food item.
    private String id;

    // Map of nutrients and value.
    private HashMap<String, Double> nutrients;

    // location in foodItemList, for later relocate item
    private Integer index;

    /**
     * Constructor
     *
     * @param name name of the food item
     * @param id   unique id of the food item
     */
    public FoodItem(String id, String name) {
        this.id = id;
        this.name = name;
        this.index = null;

        // Initialize the nutrients HashMap by setting value null
        nutrients.put("calories", null);
        nutrients.put("fat", null);
        nutrients.put("carbohydrate", null);
        nutrients.put("fiber", null);
        nutrients.put("protein", null);
    }

    /**
     * Constructor
     *
     * @param name name of the food item
     * @param id   unique id of the food item
     */
    public FoodItem(String id, String name, int index) {
        this.id = id;
        this.name = name;
        this.index = index;

        // Initialize the nutrients HashMap by setting value null
        nutrients.put("calories", null);
        nutrients.put("fat", null);
        nutrients.put("carbohydrate", null);
        nutrients.put("fiber", null);
        nutrients.put("protein", null);
    }


    /**
     * Gets the name of the food item
     *
     * @return name of the food item
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the unique id of the food item
     *
     * @return id of the food item
     */
    public String getID() {
        return id;
    }

    /**
     * Gets the nutrients of the food item
     *
     * @return nutrients of the food item
     */
    public HashMap<String, Double> getNutrients() {
        return nutrients;
    }

    /**
     * Adds a nutrient and its value to this food.
     * If nutrient already exists, updates its value.
     */
    public void addNutrient(String name, double value) {
        nutrients.replace(name, nutrients.get(name), value);
    }

    /**
     * Returns the value of the given nutrient for this food item.
     * If not present, then returns 0.
     */
    public double getNutrientValue(String name) {
        return nutrients.get(name);
    }

    /**
     * Set index according to location in foodItemList passed in
     *
     * @param index index in foodItemList ArrayList
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * return index for relocating foodItem
     *
     * @return index of the foodItem
     */
    public Integer getIndex() {
        return index;
    }
}