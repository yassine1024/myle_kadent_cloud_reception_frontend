package employee;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import employee.medecin.Medecin;

import java.io.IOException;


public class EmployeeDeserializer extends TypeAdapter<Employee> {
    @Override
    public void write(JsonWriter out, Employee value) throws IOException {
        // Implement serialization logic here
    }

    @Override
    public Employee read(JsonReader in) throws IOException {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(in).getAsJsonObject();
        //String type = jsonObject.get("type").getAsString();
        return new Gson().fromJson(jsonObject, Medecin.class);
        /*switch (type) {
            case "medecin":
                return new Gson().fromJson(jsonObject, Medecin.class);
        *//*case "cat":
        return new Gson().fromJson(jsonObject, Cat.class);*//*
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }*/
    }
}