package bg.sofia.uni.fmi.mjt.cooking.api;

import bg.sofia.uni.fmi.mjt.cooking.exception.InvalidUriException;
import bg.sofia.uni.fmi.mjt.cooking.exception.RequestFailedException;
import bg.sofia.uni.fmi.mjt.cooking.exception.RequiredQueryMissingException;
import bg.sofia.uni.fmi.mjt.cooking.json.GsonConfig;
import bg.sofia.uni.fmi.mjt.cooking.request.RecipesRequestUri;
import bg.sofia.uni.fmi.mjt.cooking.request.RecipesRequestValidator;
import bg.sofia.uni.fmi.mjt.cooking.response.Hit;
import bg.sofia.uni.fmi.mjt.cooking.response.Recipe;
import bg.sofia.uni.fmi.mjt.cooking.response.RecipesResponse;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.Set;

public class RecipesAPIClient implements RecipesAPI {

    private static final HttpClient httpClient = HttpClient.newHttpClient();

    @Override
    public Collection<Recipe> getRecipes(String keywords, Set<String> health, Set<String> mealTypes)
            throws RequiredQueryMissingException, InvalidUriException, RequestFailedException {
        RecipesRequestValidator.validate(keywords, health, mealTypes);

        URI requestUri = RecipesRequestUri.builder()
                .keywords(keywords)
                .health(health)
                .mealTypes(mealTypes)
                .build()
                .getUri();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(requestUri)
                .build();

        HttpResponse<String> httpResponse;
        try {
            httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RequestFailedException("There was an error while sending the request!", e.getCause());
        }

        //TODO: validate response

        //System.out.println(httpResponse.body());

        RecipesResponse recipesResponse = GsonConfig.getGson().fromJson(httpResponse.body(), RecipesResponse.class);

        return recipesResponse.hits().stream()
                .map(Hit::recipe)
                .toList();
    }

    @Override
    public String convertRecipesToJson(Collection<Recipe> recipes) {
        return GsonConfig.getGson().toJson(recipes);
    }

}
