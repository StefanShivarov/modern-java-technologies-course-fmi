package bg.sofia.uni.fmi.mjt.cooking.json;

import bg.sofia.uni.fmi.mjt.cooking.enums.ValuedEnum;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Arrays;

public class EnumJsonDeserializer<T extends Enum<T> & ValuedEnum> implements JsonDeserializer<T> {

    private final Class<T> enumClass;

    public EnumJsonDeserializer(Class<T> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        String strValue = jsonElement.getAsString();

        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> e.getValue().equalsIgnoreCase(strValue))
                .findFirst()
                .orElseThrow(() -> new JsonParseException("No such enum value!"));
    }

}
