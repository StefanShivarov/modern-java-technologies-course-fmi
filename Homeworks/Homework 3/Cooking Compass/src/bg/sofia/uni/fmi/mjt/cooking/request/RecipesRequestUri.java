package bg.sofia.uni.fmi.mjt.cooking.request;

import bg.sofia.uni.fmi.mjt.cooking.enums.RequiredField;
import bg.sofia.uni.fmi.mjt.cooking.exception.InvalidUriException;

import java.net.URI;
import java.util.Arrays;
import java.util.Set;

public class RecipesRequestUri {

    private static final String API_SCHEME = "https";
    private static final String API_HOST = "api.edamam.com";
    private static final String API_PATH = "/api/recipes/v2";
    private static final String TYPE_QUERY = "type=public";
    private static final String APP_ID_QUERY = "&app_id=" + System.getenv("EDAMAM_APP_ID");
    private static final String APP_KEY_QUERY = "&app_key=" + System.getenv("EDAMAM_APP_KEY");
    private static final String MEAL_TYPE_QUERY_PREFIX = "&mealType=";
    private static final String HEALTH_QUERY_PREFIX = "&health=";
    private static final String KEYWORDS_QUERY_PREFIX = "&q=";
    private static final String FIELDS_QUERY_PREFIX = "&field=";
    private final String keywordsQuery;
    private final String mealTypesQuery;
    private final String healthQuery;

    private RecipesRequestUri(RecipesRequestUriBuilder builder) {
        this.keywordsQuery = builder.keywordsQuery;
        this.mealTypesQuery = builder.mealTypesQuery;
        this.healthQuery = builder.healthQuery;
    }

    public URI getUri() throws InvalidUriException {
        try {
            return new URI(API_SCHEME, API_HOST, API_PATH, getQuery(), null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new InvalidUriException("Uri is invalid!");
        }
    }

    public String getQuery() {
        StringBuilder result = new StringBuilder()
                .append(TYPE_QUERY)
                .append(APP_ID_QUERY)
                .append(APP_KEY_QUERY);

        if (keywordsQuery != null && !keywordsQuery.isBlank()) {
            result.append(KEYWORDS_QUERY_PREFIX).append(keywordsQuery);
        }

        if (healthQuery != null && !healthQuery.isBlank()) {
            result.append(HEALTH_QUERY_PREFIX).append(healthQuery);
        }

        if (mealTypesQuery != null && !mealTypesQuery.isBlank()) {
            result.append(MEAL_TYPE_QUERY_PREFIX).append(mealTypesQuery);
        }

        appendRequiredFieldsToQuery(result);

        return result.toString();
    }

    private void appendRequiredFieldsToQuery(StringBuilder sb) {
        Arrays.stream(RequiredField.values())
                .map(RequiredField::getValue)
                .forEach(field -> sb.append(FIELDS_QUERY_PREFIX).append(field));
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
            if (keywordsQuery != null) {
                this.keywordsQuery = keywordsQuery;
            }
            return this;
        }

        public RecipesRequestUriBuilder mealTypes(Set<String> mealTypes) {
            if (mealTypes != null) {
                this.mealTypesQuery = String.join(MEAL_TYPE_QUERY_PREFIX, mealTypes);
            }
            return this;
        }

        public RecipesRequestUriBuilder health(Set<String> health) {
            if (health != null) {
                this.healthQuery = String.join(HEALTH_QUERY_PREFIX, health);
            }
            return this;
        }

        public RecipesRequestUri build() {
            return new RecipesRequestUri(this);
        }

    }

}


