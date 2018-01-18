package json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonAnalizer {
    public String analizeJson(String jsonLine){
        JsonElement jelement = new JsonParser().parse(jsonLine);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("currentMeasurements");
        JsonArray jarray = jobject.getAsJsonObject("currentMeasurements").getAsJsonArray();
        return "lala";
    }
}
