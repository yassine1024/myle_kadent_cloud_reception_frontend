package cabinet;

import com.google.gson.*;

import java.lang.reflect.Type;

public class CabinetAdapter implements JsonDeserializer<Cabinet> {

    @Override
    public Cabinet deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String id = jsonObject.get("id").getAsString();
        String name = jsonObject.get("name").getAsString();
        String field = jsonObject.get("field").getAsString();
        String address = jsonObject.get("address").getAsString();
        String phoneNumber = jsonObject.get("phoneNumber").getAsString();
        // Extract other properties from JSON

        Cabinet cabinet = new Cabinet();
        cabinet.setId(id);
        cabinet.setName(name);
        cabinet.setField(field);
        cabinet.setAddress(address);
        cabinet.setPhoneNumber(phoneNumber);
        // Set other properties

        return cabinet;
    }
}