package bg.sofia.uni.fmi.mjt.cooking.request;

import bg.sofia.uni.fmi.mjt.cooking.exception.RequiredQueryMissingException;

import java.util.Set;

public class RecipesRequestValidator {

    public static void validate(String keywords, Set<String> mealTypes, Set<String> healthLabels)
            throws RequiredQueryMissingException
    {
        if ((keywords == null || keywords.isBlank())
                && (mealTypes == null || mealTypes.isEmpty())
                && (healthLabels == null || healthLabels.isEmpty())) {

            throw new RequiredQueryMissingException("Keywords query is required when mealTypes and health are missing!");
        }
    }

}
