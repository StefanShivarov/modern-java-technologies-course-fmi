package bg.sofia.uni.fmi.mjt.cooking.response;

import java.util.List;

public record RecipesResponse(int from, int to, int count, LinksData _links, List<Hit> hits) {
}
