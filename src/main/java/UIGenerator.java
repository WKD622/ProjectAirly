package json;

import json.history.History;
import java.util.List;

public class UIGenerator {
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_YELLOW = "\u001B[33m";
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_BLUE = "\u001B[34m";
    private final int maxSize = 50;
    private final String historyHeader =
            "║  _    _ _____  _____ _______ ____  _______     __      _      _                    ║\n" +
            "║ | |  | |_   _|/ ____|__   __/ __ \\|  __ \\ \\   / //\\   (_)    | |                   ║\n" +
            "║ | |__| | | | | (___    | | | |  | | |__) \\ \\_/ //  \\   _ _ __| |_   _              ║\n" +
            "║ |  __  | | |  \\___ \\   | | | |  | |  _  / \\   // /\\ \\ | | '__| | | | |             ║\n" +
            "║ | |  | |_| |_ ____) |  | | | |__| | | \\ \\  | |/ ____ \\| | |  | | |_| |             ║\n" +
            "║ |_|__|_|_____|_____/___|_|__\\____/|_|__\\_\\_|_/_/__  \\_\\_|_|__|_|\\__, |             ║\n" +
            "║  / ____/ __ \\| \\ | |  __ \\_   _|__   __|_   _/ __ \\| \\ | |/ ____|__/ |             ║\n" +
            "║ | |   | |  | |  \\| | |  | || |    | |    | || |  | |  \\| | (___ |___/              ║\n" +
            "║ | |   | |  | | . ` | |  | || |    | |    | || |  | | . ` |\\___ \\                   ║\n" +
            "║ | |___| |__| | |\\  | |__| || |_   | |   _| || |__| | |\\  |____) |                  ║\n" +
            "║  \\_____\\____/|_| \\_|_____/_____|  |_|  |_____\\____/|_| \\_|_____/                   ║\n";


    private final String presentHeader =
            "║   _____ _    _ _____  _____  ______ _   _ _______       _      _                   ║\n" +
            "║  / ____| |  | |  __ \\|  __ \\|  ____| \\ | |__   __|/\\   (_)    | |                  ║\n" +
            "║ | |    | |  | | |__) | |__) | |__  |  \\| |  | |  /  \\   _ _ __| |_   _             ║\n" +
            "║ | |    | |  | |  _  /|  _  /|  __| | . ` |  | | / /\\ \\ | | __ | | | | |            ║\n" +
            "║ | |____| |__| | | \\ \\| | \\ \\| |____| |\\  |  | |/ ____ \\| | |  | | |_| |            ║\n" +
            "║  \\_____|\\____/|_| _\\_\\_|_ \\_\\______|_|_\\_|__|_/_/_  _\\_\\_|_|__|_|\\__, |            ║\n" +
            "║  / ____/ __ \\| \\ | |  __ \\_   _|__   __|_   _/ __ \\| \\ | |/ ____| __/ |            ║\n" +
            "║ | |   | |  | |  \\| | |  | || |    | |    | || |  | |  \\| | (___  |___/             ║\n" +
            "║ | |   | |  | | . ` | |  | || |    | |    | || |  | | . ` |\\___ \\                   ║\n" +
            "║ | |___| |__| | |\\  | |__| || |_   | |   _| || |__| | |\\  |____) |                  ║\n" +
            "║  \\_____\\____/|_| \\_|_____/_____|  |_|  |_____\\____/|_| \\_|_____/                   ║\n";

    private final String upperBorder = "╔════════════════════════════════════════════════════════════════════════════════════╗";
    private String downBorder = "╚════════════════════════════════════════════════════════════════════════════════════╝";
    private final String leftAndRightBorders = "║                                                                                    ║";
    private final String endMessage = "\n║ PM10 AND PM25 SCALE:                                                               ║\n║" +
                                      ANSI_GREEN + " [ 0 - 40µg ] GREEN IS PERFECT!" + ANSI_RESET + "                                                     ║\n║" +
                                      ANSI_YELLOW + " [ 40µg - 100µg ] YELLOW MEANS IT IS GETTING WORSE, BUT NOT THAT BAD...             " + ANSI_RESET +"║\n"
                                    + ANSI_RESET + "║" +
                                      ANSI_RED + " [ 100µg and more ] RED MEANS YOU SHOULD NOT LEAVE YOUR HOME." + ANSI_RESET + "                       ║\n";


    public String showHistory(JsonModel jsonModel, String pm10Header, String pm25Header) {

        StringBuilder pm10Graph = new StringBuilder(512);
        StringBuilder pm25Graph = new StringBuilder(512);
        List<History> histories = null;
        try {
             histories = jsonModel.getHistory();
        }
        catch (NullPointerException noInformations){
            System.out.println("Wrong data");
        }
        pm10Graph.append(upperBorder + "\n");
        pm10Graph.append(historyHeader);
        pm10Graph.append(leftAndRightBorders + "\n");
        pm10Graph.append("║ [    PM 10    ] [ " + ANSI_BLUE + pm10Header + ANSI_RESET);

        for (int i = 0; i < maxSize - pm10Header.length(); i++)
            pm10Graph.append(" ");

        pm10Graph.append(" ]             ║\n");
        pm25Graph.append("║ [    PM 25    ] [ " + ANSI_BLUE + pm25Header + ANSI_RESET);

        for (int i = 0; i < maxSize - pm25Header.length(); i++)
            pm25Graph.append(" ");

        pm25Graph.append(" ]             ║\n");

        int i = 1;
        for (History hist : histories) {
            if (i % 2 == 1) {
                pm10Graph.append(generatePmBar(hist.getMeasurments().getPm10(), hourParser(hist.getFromDateTime())) + "\n");
                pm25Graph.append(generatePmBar(hist.getMeasurments().getPm25(), hourParser(hist.getFromDateTime())) + "\n");
            }
            i++;
        }
        pm10Graph.append(leftAndRightBorders);
        pm25Graph.append(leftAndRightBorders);
        pm25Graph.append(endMessage);
        pm25Graph.append(downBorder);
        return pm10Graph.toString() + "\n" + pm25Graph.toString();
    }

    public String showPresent(JsonModel jsonModel){
        int caqi=0, pm10=0, pm25=0, tmp=0, preassure=0, windSpeed=0,windDirection=0;
        try {
            caqi = jsonModel.getCurrentMeasurements().getAirQualityIndex();
            pm10 = jsonModel.getCurrentMeasurements().getPm10();
            pm25 = jsonModel.getCurrentMeasurements().getPm25();
            tmp = jsonModel.getCurrentMeasurements().getTemperature();
            preassure = jsonModel.getCurrentMeasurements().getPressure();
            windSpeed = jsonModel.getCurrentMeasurements().getWindSpeed();
            windDirection = jsonModel.getCurrentMeasurements().getWindSpeed();
        }
        catch (NullPointerException noInformation){
            System.out.println("Wrong data");
        }

        StringBuilder present = new StringBuilder(512);
        present.append(upperBorder);
        present.append("\n");
        present.append(presentHeader);
        present.append(leftAndRightBorders + "\n");
        present.append(leftAndRightBorders + "\n");
        present.append("║ " + generateOneColourBar(jsonModel.getCurrentMeasurements().getHumidity(),100, " HUMIDITY  ", ANSI_BLUE));
        present.append("         ║\n");
        present.append("║ " + generateOneColourBar(jsonModel.getCurrentMeasurements().getPollutionLevel(),6," POLLUTION ", ANSI_RED));
        present.append("         ║\n");
        present.append(generatePmBar(pm10, "PM10 ") +"\n");
        present.append(generatePmBar(pm25, "PM25 ") + "\n");
        present.append("║ [    CAQI     ] [ " + caqi +" ]");
        present.append("                                                            " + createEndOfBorder(caqi));
        present.append("║ [ TEMPERATURE ] [ " + tmp + " °C ]");
        present.append("                                                         " + createEndOfBorder(tmp));
        present.append("║ [  PREASSURE  ] [ " + preassure/100 + " hPa ]");
        present.append("                                                       " + createEndOfBorder(preassure/10));
        present.append("║ [ WIND SPEED  ] [ " + windSpeed + " m/s ]");
        present.append("                                                        " + createEndOfBorder(windSpeed));
        present.append("║ [  WIND DIR   ] [ " + windDirection + " ° ]");
        present.append("                                                          " + createEndOfBorder(windDirection));
        present.append(leftAndRightBorders + "\n");
        present.append(leftAndRightBorders);
        present.append(endMessage);
        present.append(leftAndRightBorders + "\n");
        present.append(downBorder);
        return present.toString();
    }

    private String generatePmBar(int pm, String barTitle) {
        int percents = pm;
        pm = pm / 10;
        StringBuilder bar = new StringBuilder(128);

        if (pm > maxSize) pm = maxSize;
            bar.append("║ [    " + barTitle + "    ] [ ");

        for (int i = 0; i <= pm; i++) {
            if (i <= 4) bar.append(ANSI_GREEN + "|" + ANSI_RESET);
                else if (i <= 10) bar.append(ANSI_YELLOW + "|" + ANSI_RESET);
                    else bar.append(ANSI_RED + "|" + ANSI_RESET);
        }

        for (int i = 0; i < maxSize - pm-1; i++)
            bar.append(" ");

        if (percents < 10) bar.append(" ] " + percents + "%  of norm  ║");
            else if (percents < 100) bar.append(" ] " + percents + "% of norm ║");
                else bar.append(" ] " + ANSI_RED + "WARNING!" + ANSI_RESET + "    ║");

        return bar.toString();
    }

    private String generateOneColourBar(int value, int maxValue, String barTitle, String barColour){
        StringBuilder bar = new StringBuilder(128);
        bar.append("[ " + barTitle + " ] [ " + barColour);
        int percent = (int)(( (double)value / (double)maxValue) * 100);
        for (int i=0; i< percent/2; i++)
            bar.append("|");
        for (int i=0; i<maxSize-percent/2; i++)
            bar.append(" ");
        if (percent == 100) percent = percent - 1;
        bar.append(ANSI_RESET + " ] " + percent + "%");

        return bar.toString();
    }

    private String hourParser(String Hour) {
        return Hour.substring(11, 16);
    }

    private String createEndOfBorder(int value){
        if ((value > 9 && value < 100) || (value > -9 && value < 0)) return " ║\n";
        else if ( value < -9 ) return  "║\n";
            else if ( value >=0 && value < 10 ) return "  ║\n";
                else return "║\n";
    }
}
