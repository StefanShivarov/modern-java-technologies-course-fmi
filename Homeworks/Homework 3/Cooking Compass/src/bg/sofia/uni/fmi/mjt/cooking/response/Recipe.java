package bg.sofia.uni.fmi.mjt.cooking.response;

import bg.sofia.uni.fmi.mjt.cooking.enums.CuisineType;
import bg.sofia.uni.fmi.mjt.cooking.enums.Diet;
import bg.sofia.uni.fmi.mjt.cooking.enums.DishType;
import bg.sofia.uni.fmi.mjt.cooking.enums.Health;
import bg.sofia.uni.fmi.mjt.cooking.enums.MealType;

import java.util.List;

public record Recipe(
        String label,
        List<Diet> dietLabels,
        List<Health> healthLabels,
        List<String> ingredientLines,
        double totalWeight,
        List<CuisineType> cuisineType,
        List<MealType> mealType,
        List<DishType> dishType
) {

}
