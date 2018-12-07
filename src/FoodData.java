import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents the backend for managing all 
 * the operations associated with FoodItems
 * 
 * @author sapan (sapan@cs.wisc.edu)
 */
public class FoodData implements FoodDataADT<FoodItem> {
    
    // List of all the food items.
    private List<FoodItem> foodItemList;

    // Map of nutrients and their corresponding index
    private HashMap<String, BPTree<Double, FoodItem>> indexes;
    
    
    /**
     * Public constructor
     */
    public FoodData() {
        this.foodItemList = new LinkedList<FoodItem>();
        this.indexes = new HashMap<String, BPTree<Double, FoodItem>>();	
    }
    
    
    /**
     * Loads the data in the .csv file
     * 
     * file format:
     * <id1>,<name>,<nutrient1>,<value1>,<nutrient2>,<value2>,...
     * <id2>,<name>,<nutrient1>,<value1>,<nutrient2>,<value2>,...
     * 
     * Example:
     * 556540ff5d613c9d5f5935a9,Stewarts_PremiumDarkChocolatewithMintCookieCrunch,calories,280,fat,18,carbohydrate,34,fiber,3,protein,3
     * 
     * Note:
     *     1. All the rows are in valid format.
     *  2. All IDs are unique.
     *  3. Names can be duplicate.
     *  4. All columns are strictly alphanumeric (a-zA-Z0-9_).
     *  5. All food items will strictly contain 5 nutrients in the given order:    
     *     calories,fat,carbohydrate,fiber,protein
     *  6. Nutrients are CASE-INSENSITIVE. 
     * 
     * @param filePath path of the food item data file 
     *        (e.g. folder1/subfolder1/.../foodItems.csv) 
     */
    @Override
    public void loadFoodItems(String filePath) {
        File inputFile = new File(filePath);
        try {
        	Scanner sc = new Scanner(inputFile);
        	while (sc.hasNextLine()) {
        		int listIndex = 0;
        		String[] rawDataSet = sc.nextLine().split(",");
        		if (rawDataSet.length == 0) break;
        		FoodItem food = new FoodItem(rawDataSet[0], rawDataSet[1]);
        		int i = 2;
        		//add nutrition data
        		while (i <= 11) {
        			food.addNutrient(rawDataSet[i++], Double.parseDouble(rawDataSet[i++]));
        		}
        		//add the new FoodItem to foodItemList with the assigned index
        		insertionSort(food);
        	}
        	globalUpdateIndex();
        } catch(FileNotFoundException e) {
        	System.err.println("Input file not found.");
        }
    }
    
    private void insertionSort(FoodItem newFood) {
    	if (foodItemList.size() == 0) 
    		foodItemList.add(newFood);
    	else {
    		int listIndex = 0;
	    	for (FoodItem food: foodItemList) {
	    		if (newFood.getName().compareTo(food.getName()) <= 0) {
	    			break;
	    		} else {
	    			listIndex++;
	    		}
	    	}
	    	foodItemList.add(listIndex, newFood);
	    	return;
    	}
    }
    
    private void globalUpdateIndex() {
    	int listIndex = 0;
    	for (FoodItem food: foodItemList) {
    		food.setIndex(listIndex++);
    	}
    }

    /**
     * Gets all the food items that have name containing the substring.
     * 
     * Example:
     *     All FoodItem
     *         51c38f5d97c3e6d3d972f08a,Similac_FormulaSoyforDiarrheaReadytoFeed,calories,100,fat,0,carbohydrate,0,fiber,0,protein,3
     *         556540ff5d613c9d5f5935a9,Stewarts_PremiumDarkChocolatewithMintCookieCrunch,calories,280,fat,18,carbohydrate,34,fiber,3,protein,3
     *     Substring: soy
     *     Filtered FoodItem
     *         51c38f5d97c3e6d3d972f08a,Similac_FormulaSoyforDiarrheaReadytoFeed,calories,100,fat,0,carbohydrate,0,fiber,0,protein,3
     * 
     * Note:
     *     1. Matching should be CASE-INSENSITIVE.
     *     2. The whole substring should be present in the name of FoodItem object.
     *     3. substring will be strictly alphanumeric (a-zA-Z0-9_)
     * 
     * @param substring substring to be searched
     * @return list of filtered food items; if no food item matched, return empty list
     */
    @Override
    public List<FoodItem> filterByName(String substring) {
        if (substring == null) {
        	return null;
        } else {
        	List<FoodItem> matches = new ArrayList<FoodItem>();
        	for (FoodItem food: foodItemList) {
        		if (food.getName().contains(substring))
        			matches.add(food);
        	}
        	return matches;
        }
    }

    /**
     * Gets all the food items that fulfill ALL the provided rules
     *
     * Format of a rule:
     *     "<nutrient> <comparator> <value>"
     * 
     * Definition of a rule:
     *     A rule is a string which has three parts separated by a space:
     *         1. <nutrient>: Name of one of the 5 nutrients [CASE-INSENSITIVE]
     *         2. <comparator>: One of the following comparison operators: <=, >=, ==
     *         3. <value>: a double value
     * 
     * Note:
     *     1. Multiple rules can contain the same nutrient.
     *         E.g. ["calories >= 50.0", "calories <= 200.0", "fiber == 2.5"]
     *     2. A FoodItemADT object MUST satisfy ALL the provided rules i
     *        to be returned in the filtered list.
     *
     * @param rules list of rules
     * @return list of filtered food items; if no food item matched, return empty list
     */
    @Override
    public List<FoodItem> filterByNutrients(List<String> rules) {
       return this.indexes.get(rules.get(0)).rangeSearch(Double.parseDouble(rules.get(2)), rules.get(1));
    }

    /**
     * Adds a food item to the loaded data.
     * @param foodItem the food item instance to be added
     */
    @Override
    public void addFoodItem(FoodItem foodItem) {
    	//TODO: duplicate id's?
    	//TODO: is there a need for randomly generating id
    	foodItem.setIndex(this.foodItemList.size());
        this.foodItemList.add(foodItem);
    }

    /**
     * Gets the list of all food items.
     * @return list of FoodItem
     */
    @Override
    public List<FoodItem> getAllFoodItems() {
        return this.foodItemList;
    }

    /**
     * Save the list of food items in ascending order by name
     * 
     * @param filename name of the file where the data needs to be saved 
     */
	@Override
	public void saveFoodItems(String filename) {
		File file = new File(filename);
		if (file.exists())
			System.err.println("File already exists. Please change the file name.");
		else {
			try {
				PrintWriter output = new PrintWriter(file);
				for (FoodItem food: foodItemList) {
					output.println(food.toString());
				}
			} catch (FileNotFoundException e) {
				// TODO why this exception?
				e.printStackTrace();
			}
		}
		
	}
	
	///////////////////FOR TESTING PURPOSES//////////////////////
	public static void main(String[] args) {
		FoodData fd = new FoodData();
		fd.loadFoodItems("foodItems.csv");
		System.out.println("");
		fd.saveFoodItems("sampleOut.txt");
	}
}
