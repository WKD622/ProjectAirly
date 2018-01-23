import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import connection.Connection;
import json.JsonModel;
import json.UIGenerator;

import java.io.IOException;

public class Program {
    public static void main (String[] args) {
    Double x=0.0,y=0.0;
    String url = "";
    String urlID = "https://airapi.airly.eu/v1/sensor/measurements?sensorId=";
    String urlXY = "https://airapi.airly.eu/v1/mapPoint/measurements?";
    String sensorID = "";
    String json = "";
    String token = "";
    boolean llh = false, llc = false, idc = false, idh = false;


        switch (args[0]){
            case "-llh" : {
                x=Double.parseDouble(args[1]);
                y=Double.parseDouble(args[2]);
                token = args[4];
                llh = true;
                break;
            }
            case  "-llc" : {
                x=Double.parseDouble(args[1]);
                y=Double.parseDouble(args[2]);
                token = args[4];
                llc = true;
                break;
            }
            case "-idc" : {
                sensorID = args[1];
                token = args[3];
                idc = true;
                break;
            }
            case "-idh" : {
                sensorID = args[1];
                token = args[3];
                idh = true;
                break;
            }
            default : System.out.println("Wrong argumensts");
        }

        if (llh || llc){
            url = urlXY + "latitude=" + x + "&longitude=" + y;
        }
        else if (idc || idh){
            url = urlID + sensorID;
        }

        Connection connection = new Connection();
        try {
            json = connection.connect(url, token);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonModel jsonModel = gson.fromJson(json, JsonModel.class);

        UIGenerator uiGenerator = new UIGenerator();

        if (idc || llc) System.out.println(uiGenerator.showPresent(jsonModel));
        else if (idh) System.out.println(uiGenerator.showHistory(jsonModel, "SENSOR ID: " + sensorID, "SENSOR ID: " + sensorID));
        else if (llh) System.out.println(uiGenerator.showHistory(jsonModel, "X = " + x + "  Y = " + y, "X = " + x + "  Y = " + y));

    }
}
