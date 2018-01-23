import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import connection.Connection;
import json.JsonModel;
import json.UIGenerator;

import java.io.IOException;

public class Program {
    public static void main (String args[]) {
        Connection connection = new Connection();
        String json = "";
        String url = "https://airapi.airly.eu/v1/sensor/measurements?sensorId=";
        String sensorID = "1060";
        String token = "97ff2a53cb3e46c4a47f703a888bf8c0";

        try {
            json = connection.connect(url + sensorID, token);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonModel jsonModel = gson.fromJson(json, JsonModel.class);

        UIGenerator uiGenerator = new UIGenerator();
        System.out.println(uiGenerator.showPresent(jsonModel));
        System.out.println("\n-------------------------------------------------------\n");
        System.out.println(uiGenerator.showHistory(jsonModel, "SENSOR MESSAGE", "SENSOR MESSAGE"));

    }
}
