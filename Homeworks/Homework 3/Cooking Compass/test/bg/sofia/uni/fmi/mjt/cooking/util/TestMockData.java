package bg.sofia.uni.fmi.mjt.cooking.util;

public class TestMockData {

    public static final String MOCK_RECIPE_RESPONSE = """
            {
              "from": 1,
              "to": 1,
              "count": 1,
              "_links": {},
              "hits": [
                {
                  "recipe": {
                    "label": "Healthy Blueberry Banana Greek Yogurt",
                    "dietLabels": [
                      "Balanced",
                      "Low-Sodium"
                    ],
                    "healthLabels": [
                      "Kidney-Friendly",
                      "Vegetarian",
                      "Pescatarian",
                      "Mediterranean",
                      "DASH",
                      "Gluten-Free",
                      "Wheat-Free",
                      "Egg-Free",
                      "Peanut-Free",
                      "Tree-Nut-Free",
                      "Soy-Free",
                      "Fish-Free",
                      "Shellfish-Free",
                      "Pork-Free",
                      "Red-Meat-Free",
                      "Crustacean-Free",
                      "Celery-Free",
                      "Mustard-Free",
                      "Sesame-Free",
                      "Lupine-Free",
                      "Mollusk-Free",
                      "Alcohol-Free",
                      "No oil added",
                      "Sulfite-Free",
                      "Kosher",
                      "Immuno-Supportive"
                    ],
                    "ingredientLines": [
                      "* 1 cup blueberry",
                      "* 1/2 large banana",
                      "* 1/2 cup greek yogurt",
                      "* 2 tbsp greek yogurt"
                    ],
                    "totalWeight": 395.9999999993914,
                    "cuisineType": [
                      "american",
                      "mediterranean"
                    ],
                    "mealType": [
                      "lunch/dinner"
                    ],
                    "dishType": [
                      "desserts"
                    ]
                  },
                  "_links": {
                    "self": {
                      "title": "Self",
                      "href": "https://api.edamam.com/api/recipes/v2/779aacd1298b875ce8d3c0cf678772b4?type=public&app_id=4986381d&app_key=e3ff561e715f8cd121a46b1c87007c5b"
                    }
                  }
                }
              ]
            }""";
}
