package bg.sofia.uni.fmi.mjt.cooking.api;

import bg.sofia.uni.fmi.mjt.cooking.exception.BadRequestException;
import bg.sofia.uni.fmi.mjt.cooking.exception.ForbiddenErrorException;
import bg.sofia.uni.fmi.mjt.cooking.exception.InvalidAppCredentialsException;
import bg.sofia.uni.fmi.mjt.cooking.exception.InvalidUriException;
import bg.sofia.uni.fmi.mjt.cooking.exception.RequestFailedException;
import bg.sofia.uni.fmi.mjt.cooking.exception.RequiredQueryMissingException;
import bg.sofia.uni.fmi.mjt.cooking.json.GsonConfig;
import bg.sofia.uni.fmi.mjt.cooking.request.RecipesRequestUri;
import bg.sofia.uni.fmi.mjt.cooking.request.RecipesRequestValidator;
import bg.sofia.uni.fmi.mjt.cooking.response.ErrorData;
import bg.sofia.uni.fmi.mjt.cooking.response.ErrorsResponse;
import bg.sofia.uni.fmi.mjt.cooking.response.Hit;
import bg.sofia.uni.fmi.mjt.cooking.response.NextPageData;
import bg.sofia.uni.fmi.mjt.cooking.response.Recipe;
import bg.sofia.uni.fmi.mjt.cooking.response.RecipesResponse;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class RecipesAPIClient implements RecipesAPI {

    private static final int MAX_PAGES = 2;
    private final HttpClient httpClient;

    public RecipesAPIClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public RecipesAPIClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public Collection<Recipe> getRecipes(String keywords, Set<String> health, Set<String> mealTypes)
            throws RequiredQueryMissingException, InvalidUriException,
            RequestFailedException, BadRequestException, ForbiddenErrorException,
            InvalidAppCredentialsException {

        RecipesRequestValidator.validate(keywords, health, mealTypes);

        URI requestUri = RecipesRequestUri.builder()
                .keywords(keywords)
                .health(health)
                .mealTypes(mealTypes)
                .build()
                .getUri();

        List<Recipe> result = new ArrayList<>();

        fetchRecipesFromEachPage(requestUri, result);

        return result;
    }

    @Override
    public String convertRecipesToJson(Collection<Recipe> recipes) {
        return GsonConfig.getGson().toJson(recipes);
    }

    private void fetchRecipesFromEachPage(URI requestUri, List<Recipe> result)
            throws RequestFailedException, ForbiddenErrorException,
            BadRequestException, InvalidAppCredentialsException {

        for (int currentPage = 1; currentPage <= MAX_PAGES; currentPage++) {
            HttpResponse<String> httpResponse = sendRequestTo(requestUri);

            validateResponse(httpResponse);

            RecipesResponse recipesResponse = GsonConfig.getGson().fromJson(httpResponse.body(),
                    RecipesResponse.class);
            List<Recipe> recipes = recipesResponse.hits().stream()
                    .map(Hit::recipe)
                    .toList();

            result.addAll(recipes);

            NextPageData nextPageData = recipesResponse._links().next();
            if (nextPageData != null) {
                requestUri = URI.create(nextPageData.href());
            } else {
                break;
            }
        }
    }

    private HttpResponse<String> sendRequestTo(URI uri) throws RequestFailedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RequestFailedException("There was an error while sending the request!",
                    e.getCause());
        }
    }

    private void validateResponse(HttpResponse<String> httpResponse)
            throws RequestFailedException, BadRequestException,
            ForbiddenErrorException, InvalidAppCredentialsException {
        if (httpResponse == null) {
            throw new RequestFailedException("Request failed as there is no response!");
        }

        if (httpResponse.statusCode() != 200) {
            ErrorsResponse errorsResponse = GsonConfig.getGson().fromJson(httpResponse.body(),
                    ErrorsResponse.class);
            ErrorData error = errorsResponse.errors().getFirst();

            if (httpResponse.statusCode() == HttpURLConnection.HTTP_BAD_REQUEST) {
                throw new BadRequestException(error.error());
            }

            if (httpResponse.statusCode() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                throw new InvalidAppCredentialsException("Your app_id or your app_key is invalid!");
            }

            if (httpResponse.statusCode() == HttpURLConnection.HTTP_FORBIDDEN) {
                throw new ForbiddenErrorException(error.error());
            }
        }
    }

}
