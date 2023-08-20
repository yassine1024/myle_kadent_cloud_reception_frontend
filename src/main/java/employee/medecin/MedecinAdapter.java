package employee.medecin;

import com.google.gson.*;

import java.lang.reflect.Type;

public class MedecinAdapter implements JsonSerializer<Medecin>, JsonDeserializer<Medecin> {

    @Override
    public JsonElement serialize(Medecin src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        // Serialize properties from Medecin and Employee classes
        // For example: jsonObject.addProperty("id", src.getId());

        return jsonObject;
    }

    @Override
    public Medecin deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        // Deserialize properties into Medecin and Employee objects
        // For example: String id = jsonObject.get("id").getAsString();
        Medecin medecin = new Medecin();
        // Set properties on medecin object
        return medecin;
    }

}
