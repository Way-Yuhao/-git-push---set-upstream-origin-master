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
        this.nutrients = new HashMap<String, Double>();
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
        this.nutrients = new HashMap<String, Double>();
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
    public void addNutrient(String name, Double value) {
    	nutrients.put(name, value);
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
    
    @Override
    public String toString() {
    	return id + "," + name + "," 
    			+ "calories" + "," + nutrients.get("calories").toString() + "," 
    			+ "fat" + "," + nutrients.get("fat").toString() + "," 
    			+ "carbohydrate" + "," + nutrients.get("carbohydrate").toString() + "," 
    			+ "fiber" + "," + nutrients.get("fiber") .toString() + "," 
    			+ "protein" + "," + nutrients.get("protein").toString();
    }
}