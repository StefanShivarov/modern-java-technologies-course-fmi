package bg.sofia.uni.fmi.mjt.cooking.json;

import bg.sofia.uni.fmi.mjt.cooking.enums.ValuedEnum;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class EnumJsonSerializer<T extends Enum<T> & ValuedEnum> implements JsonSerializer<T> {

    @Override
    public JsonElement serialize(
            T enumObj,
            Type type,
            JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(enumObj.getValue());
    }

}
