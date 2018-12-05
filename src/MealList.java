import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MealList {

    private List<FoodItem> mealList;
    private HashMap<String, Double> nutritionSummary;

    public MealList() {
        mealList = new ArrayList<>();
        nutritionSummary = new HashMap<>();
    }

    //adds a food item to MealList
    public void addItem(FoodItem foodItem) {
        if (foodItem == null) throw new NullPointerException();
        mealList.add(foodItem);
    }
    //DICK WAYNE
    //removes a food item from MealList
    public void removeItem(FoodItem foodItem) {
        if (foodItem == null) throw new NullPointerException();
        if (mealList.isEmpty()) return;
        mealList.remove(mealList.indexOf(foodItem));
    }

    //traverses the entire list and adds up all nutrition
    public void analyzeMeal() {

    }

    //return the current MealList
    public List<FoodItem> getMealList() {
        return mealList;
    }
    //Wayne edit
}