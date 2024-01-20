package bg.sofia.uni.fmi.mjt.cooking.api;

import bg.sofia.uni.fmi.mjt.cooking.exception.BadRequestException;
import bg.sofia.uni.fmi.mjt.cooking.exception.ForbiddenErrorException;
import bg.sofia.uni.fmi.mjt.cooking.exception.InvalidAppCredentialsException;
import bg.sofia.uni.fmi.mjt.cooking.exception.InvalidUriException;
import bg.sofia.uni.fmi.mjt.cooking.exception.RequestFailedException;
import bg.sofia.uni.fmi.mjt.cooking.exception.RequiredQueryMissingException;
import bg.sofia.uni.fmi.mjt.cooking.response.Recipe;
import bg.sofia.uni.fmi.mjt.cooking.util.TestMockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecipesAPIClientTest {

    @Mock
    private HttpClient httpClientMock;

    @Mock
    private HttpResponse<String> httpResponseMock;

    @InjectMocks
    private RecipesAPIClient recipesAPIClient;

    @BeforeEach
    void setUp() throws IOException, InterruptedException {
        when(httpClientMock.send(any(), ArgumentMatchers.<HttpResponse.BodyHandler<String>>any()))
                .thenReturn(httpResponseMock);
    }

    @Test
    void testGetRecipes()
            throws RequestFailedException, ForbiddenErrorException,
            InvalidUriException, RequiredQueryMissingException,
            BadRequestException, InvalidAppCredentialsException
    {
        when(httpResponseMock.statusCode()).thenReturn(200);
        when(httpResponseMock.body()).thenReturn(TestMockData.MOCK_RECIPE_RESPONSE);


        List<Recipe> recipes = (List<Recipe>) recipesAPIClient.getRecipes(
                "blueberry",
                Set.of("egg free"),
                Set.of("Lunch")
        );

        assertEquals(1, recipes.size(), "getRecipes() should return correct data!");
        Recipe recipe = recipes.getFirst();
        assertEquals("Recipe[label=Healthy Blueberry Banana Greek Yogurt,"
                + " dietLabels=[BALANCED, LOW_SODIUM],"
                + " healthLabels=[KIDNEY_FRIENDLY, VEGETARIAN, PESCATARIAN, MEDITERRANEAN, DASH,"
                + " GLUTEN_FREE, WHEAT_FREE, EGG_FREE, PEANUT_FREE, TREE_NUT_FREE, SOY_FREE, FISH_FREE,"
                + " SHELLFISH_FREE, PORK_FREE, RED_MEAT_FREE, CRUSTACEAN_FREE, CELERY_FREE, MUSTARD_FREE,"
                + " SESAME_FREE, LUPINE_FREE, MOLLUSK_FREE, ALCOHOL_FREE, NO_OIL_ADDED, SULFITE_FREE,"
                + " KOSHER, IMMUNO_SUPPORTIVE], ingredientLines=[* 1 cup blueberry, * 1/2 large banana,"
                + " * 1/2 cup greek yogurt, * 2 tbsp greek yogurt], totalWeight=395.9999999993914,"
                + " cuisineType=[AMERICAN, MEDITERRANEAN], mealType=[LUNCH_DINNER], dishType=[DESSERTS]]",
                recipe.toString(),
                "getRecipes() should return correct data!");
    }

    @Test
    void testThrowsBadRequestException() {
        when(httpResponseMock.statusCode()).thenReturn(400);
        when(httpResponseMock.body()).thenReturn("{\"errors\":[{\"error\":" +
                "\"Not a valid health: \\u0027kidney-sddsdfriendly\\u0027\"," +
                "\"property\":\"search.arg0.healths[]." +
                "\\u003citerable element\\u003e\"}]}");
        assertThrows(BadRequestException.class,
                () -> recipesAPIClient.getRecipes("sdos",
                        Set.of("invalid"),
                        Set.of()),
                "should throw BadRequestException when" +
                        " the response code is 401");
    }

    @Test
    void testThrowsInvalidAppCredentialsException() {
        when(httpResponseMock.statusCode()).thenReturn(401);
        when(httpResponseMock.body()).thenReturn("{\"errors\":[{\"error\":" +
                "\"Not a valid app_id: \\u0027kidney-sddsdfriendly\\u0027\"," +
                "\"property\":\"search.arg0.healths[]." +
                "\\u003citerable element\\u003e\"}]}");
        assertThrows(InvalidAppCredentialsException.class,
                () -> recipesAPIClient.getRecipes("sdos",
                        Set.of("invalid"),
                        Set.of()),
                "should throw InvalidAppCredentialsException when" +
                        " the response code is 401");
    }

    @Test
    void testThrowsForbiddenErrorException() {
        when(httpResponseMock.statusCode()).thenReturn(403);
        when(httpResponseMock.body()).thenReturn("{\"errors\":[{\"error\":" +
                "\"Forbidden\"," +
                "\"property\":\"search.arg0.healths[]." +
                "\\u003citerable element\\u003e\"}]}");
        assertThrows(ForbiddenErrorException.class,
                () -> recipesAPIClient.getRecipes("sdos",
                        Set.of("invalid"),
                        Set.of()),
                "should throw ForbiddenErrorException when" +
                        " the response code is 403");
    }

}
