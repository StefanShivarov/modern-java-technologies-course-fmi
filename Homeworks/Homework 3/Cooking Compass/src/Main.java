import bg.sofia.uni.fmi.mjt.cooking.api.RecipesAPI;
import bg.sofia.uni.fmi.mjt.cooking.api.RecipesAPIClient;
import bg.sofia.uni.fmi.mjt.cooking.response.Recipe;

import java.util.Collection;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        RecipesAPI recipesAPIClient = new RecipesAPIClient();

        try {
            Collection<Recipe> recipes = recipesAPIClient.getRecipes(
                    "chocolate",
                    Set.of("low-sugar"),
                    Set.of("Breakfast", "Dinner"));

            recipes.forEach(System.out::println);
            System.out.println(recipesAPIClient.convertRecipesToJson(recipes));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}