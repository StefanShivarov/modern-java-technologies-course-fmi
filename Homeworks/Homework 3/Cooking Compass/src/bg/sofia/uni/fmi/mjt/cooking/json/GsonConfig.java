package bg.sofia.uni.fmi.mjt.cooking.json;

import bg.sofia.uni.fmi.mjt.cooking.enums.CuisineType;
import bg.sofia.uni.fmi.mjt.cooking.enums.Diet;
import bg.sofia.uni.fmi.mjt.cooking.enums.DishType;
import bg.sofia.uni.fmi.mjt.cooking.enums.Health;
import bg.sofia.uni.fmi.mjt.cooking.enums.MealType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonConfig {

    private static final Gson gsonInstance = configureGson();

    private GsonConfig() {
    }

    public static Gson getGson() {
        return gsonInstance;
    }

    private static Gson configureGson() {
        return new GsonBuilder()
                .registerTypeAdapter(MealType.class,
                        new EnumJsonDeserializer<>(MealType.class))
                .registerTypeAdapter(Diet.class,
                        new EnumJsonDeserializer<>(Diet.class))
                .registerTypeAdapter(DishType.class,
                        new EnumJsonDeserializer<>(DishType.class))
                .registerTypeAdapter(Health.class,
                        new EnumJsonDeserializer<>(Health.class))
                .registerTypeAdapter(CuisineType.class,
                        new EnumJsonDeserializer<>(CuisineType.class))
                .registerTypeAdapter(MealType.class, new EnumJsonSerializer<>())
                .registerTypeAdapter(Diet.class, new EnumJsonSerializer<>())
                .registerTypeAdapter(DishType.class, new EnumJsonSerializer<>())
                .registerTypeAdapter(Health.class, new EnumJsonSerializer<>())
                .registerTypeAdapter(CuisineType.class, new EnumJsonSerializer<>())
                .setPrettyPrinting()
                .create();
    }

}
