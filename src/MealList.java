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

    //removes a food item from MealList
    public void removeItem(FoodItem foodItem) {
        if (foodItem == null) throw new NullPointerException();
        if (mealList.isEmpty()) return;
        mealList.remove(mealList.indexOf(foodItem));
    }

    //traverses the entire list and adds up all nutrition
    public void analyzeMeal() {
        nutritionSummary.put("calories", sumNutrition("calories"));
        nutritionSummary.put("fat", sumNutrition("fat"));
        nutritionSummary.put("carbohydrate", sumNutrition("carbohydrate"));
        nutritionSummary.put("fiber", sumNutrition("fiber"));
        nutritionSummary.put("protein", sumNutrition("protein"));
    }

    private double sumNutrition(String nutrition) {
        if (mealList.isEmpty()) return 0;

        double sumNutrition = 0;
        for (FoodItem curItem : mealList) {
            double curNutrition = curItem.getNutrientValue(nutrition);
            sumNutrition += curNutrition;
        }

        return sumNutrition;
    }

    //return the current MealList
    public List<FoodItem> getMealList() {
        return mealList;
    }
}