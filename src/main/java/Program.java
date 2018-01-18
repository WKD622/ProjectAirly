import json.Connection;
import json.JsonAnalizer;

import java.io.IOException;

public class Program {
    public static void main (String args[]) {
        Connection connection = new Connection();
        JsonAnalizer jsonAnalizer = new JsonAnalizer();
        try {
            String json = connection.connect("https://airapi.airly.eu/v1/sensor/measurements?sensorId=1060");
            System.out.println(json);
            System.out.println("!!!!!!!!!!!!!!!!!");
            System.out.println(jsonAnalizer.analizeJson(json));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
