package bg.sofia.uni.fmi.mjt.cooking.request;

import java.util.List;

public class RecipesRequestUri {

    private static final String API_ENDPOINT = "https://api.edamam.com/api/recipes/v2?type=public";
    private static final String APP_ID = System.getenv("EDAMAM_APP_ID");
    private static final String APP_KEY = System.getenv("EDAMAM_APP_KEY");
    private static final String APP_ID_QUERY = "&app_id=" + APP_ID;
    private static final String APP_KEY_QUERY = "&app_key=" + APP_KEY;
    private static final String MEAL_TYPE_QUERY_PREFIX = "&mealType=";
    private static final String HEALTH_QUERY_PREFIX = "&health=";
    private static final String KEYWORDS_QUERY_PREFIX = "&q=";
    private final String keywordsQuery;
    private final String mealTypesQuery;
    private final String healthQuery;

    private RecipesRequestUri(RecipesRequestUriBuilder builder) {
        this.keywordsQuery = builder.keywordsQuery;
        this.mealTypesQuery = builder.mealTypesQuery;
        this.healthQuery = builder.healthQuery;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(API_ENDPOINT + APP_ID_QUERY + APP_KEY_QUERY);
        result.append(KEYWORDS_QUERY_PREFIX).append(keywordsQuery);
        if (mealTypesQuery != null && !mealTypesQuery.isBlank()) {
            result.append(MEAL_TYPE_QUERY_PREFIX).append(mealTypesQuery);
        }
        if (healthQuery != null && !healthQuery.isBlank()) {
            result.append(HEALTH_QUERY_PREFIX).append(healthQuery);
        }

        return result.toString();
    }

    public static RecipesRequestUriBuilder builder() {
        return new RecipesRequestUriBuilder();
    }

    public static class RecipesRequestUriBuilder {

        private String keywordsQuery;
        private String mealTypesQuery;
        private String healthQuery;

        private RecipesRequestUriBuilder() {}

        public RecipesRequestUriBuilder keywords(String keywordsQuery) {
            this.keywordsQuery = keywordsQuery;
            return this;
        }

        public RecipesRequestUriBuilder mealTypes(List<String> mealTypes) {
            this.mealTypesQuery = String.join(MEAL_TYPE_QUERY_PREFIX, mealTypes);
            return this;
        }

        public RecipesRequestUriBuilder health(List<String> health) {
            this.healthQuery = String.join(HEALTH_QUERY_PREFIX, health);
            return this;
        }

        public RecipesRequestUri build() {
            return new RecipesRequestUri(this);
        }

    }

}


