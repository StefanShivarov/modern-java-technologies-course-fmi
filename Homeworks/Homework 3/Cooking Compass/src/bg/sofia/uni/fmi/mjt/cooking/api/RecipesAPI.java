package bg.sofia.uni.fmi.mjt.cooking.api;

import bg.sofia.uni.fmi.mjt.cooking.exception.InvalidUriException;
import bg.sofia.uni.fmi.mjt.cooking.exception.RequestFailedException;
import bg.sofia.uni.fmi.mjt.cooking.exception.RequiredQueryMissingException;
import bg.sofia.uni.fmi.mjt.cooking.response.Recipe;

import java.util.Collection;
import java.util.Set;

public interface RecipesAPI {

    Collection<Recipe> getRecipes(String keywords, Set<String> health, Set<String> mealTypes)
            throws RequiredQueryMissingException, InvalidUriException, RequestFailedException;

    String convertRecipesToJson(Collection<Recipe> recipes);

}
