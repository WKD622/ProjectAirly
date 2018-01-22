package json;

import json.history.History;

import java.util.List;

public class UIGenerator {
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_GREEN = "\u001B[32m";
    private final String ANSI_YELLOW = "\u001B[33m";
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_BLUE = "\u001B[34m";
    private final String ANSI_RED_BACKGROUND = "\u001B[41m";
    private final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    private final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    private final int maxSize = 50;
    private final String historyHeader =
            "\n           HH   HH IIIII  SSSSS  TTTTTTT  OOOOO  RRRRRR  YY   YY \n" +
            "           HH   HH  III  SS        TTT   OO   OO RR   RR YY   YY \n" +
            "           HHHHHHH  III   SSSSS    TTT   OO   OO RRRRRR   YYYYY  \n" +
            "           HH   HH  III       SS   TTT   OO   OO RR  RR    YYY   \n" +
            "           HH   HH IIIII  SSSSS    TTT    OOOOO  RR   RR   YYY   \n";
    private final String presentHeader =
            "\n           PPPPPP  RRRRRR  EEEEEEE  SSSSS  EEEEEEE NN   NN TTTTTTT \n" +
                    "           PP   PP RR   RR EE      SS      EE      NNN  NN   TTT   \n" +
                    "           PPPPPP  RRRRRR  EEEEE    SSSSS  EEEEE   NN N NN   TTT   \n" +
                    "           PP      RR  RR  EE           SS EE      NN  NNN   TTT   \n" +
                    "           PP      RR   RR EEEEEEE  SSSSS  EEEEEEE NN   NN   TTT   \n";
    private final String endMessage = ANSI_GREEN + "\n[ 0 - 40 ] GREEN IS PERFECT! \n" +
                                      ANSI_YELLOW + "[ 40 - 100 ] YELLOW MEANS IT IS GETTING WORSE, BUT NOT THAT BAD...\n" +
                                      ANSI_RED + "[ 100 and more ] RED MEANS YOU SHOULD NOT LEAVE YOUR HOME.\n" + ANSI_RESET;


    public String showHistory(JsonModel jsonModel, String pm10Header, String pm25Header) {

        System.out.println(historyHeader);
        StringBuilder pm10Graph = new StringBuilder(512);
        StringBuilder pm25Graph = new StringBuilder(512);
        List<History> histories = jsonModel.getHistory();

        pm10Graph.append("[ PM 10 ] [ " + ANSI_BLUE + pm10Header + ANSI_RESET);
        for (int i = 0; i < maxSize - pm10Header.length(); i++)
            pm10Graph.append(" ");
        pm10Graph.append(" ]\n");

        pm25Graph.append("[ PM 25 ] [ " + ANSI_BLUE + pm25Header + ANSI_RESET);
        for (int i = 0; i < maxSize - pm25Header.length(); i++)
            pm25Graph.append(" ");
        pm25Graph.append(" ]\n");

        int i = 1;
        for (History hist : histories) {
            if (i % 2 == 1) {
                pm10Graph.append(generatePmBar(hist.getMeasurments().getPm10(), hourParser(hist.getFromDateTime())) + "\n");
                pm25Graph.append(generatePmBar(hist.getMeasurments().getPm25(), hourParser(hist.getFromDateTime())) + "\n");
            }
            i++;
        }

        pm25Graph.append(endMessage);
        return pm10Graph.toString() + "\n" + pm25Graph.toString();
    }

    public String showPresent(JsonModel jsonModel){
        StringBuilder present = new StringBuilder(512);
        present.append(presentHeader);
        present.append("\n");
        present.append(generateHumidityBar(jsonModel.getCurrentMeasurements().getHumidity(), "HUMIDITY   "));
        present.append("\n");
        present.append(generatePmBar(jsonModel.getCurrentMeasurements().getPm10(), "PM10       "));
        present.append("\n");
        present.append(generatePmBar(jsonModel.getCurrentMeasurements().getPm25(), "PM25       "));
        present.append("\n");
        present.append("[ TEMPERATURE ] [ " + jsonModel.getCurrentMeasurements().getTemperature() + " ]");
        present.append("\n");
        present.append("[ PREASSURE   ] [ " + jsonModel.getCurrentMeasurements().getPressure() + " Pa ]");
        present.append("\n");
        present.append("[ WIND SPEED  ] [ " + jsonModel.getCurrentMeasurements().getWindSpeed() + " ]");
        present.append("\n");
        present.append("[ WIND DIR    ] [ " + jsonModel.getCurrentMeasurements().getWindSpeed() + " ]");
        present.append("\n");
        present.append("\n");
        present.append(endMessage);
        return present.toString();
    }

    private String generatePmBar(int pm, String barTitle) {
        int percents = pm;
        pm = pm / 10;
        StringBuilder bar = new StringBuilder(128);

        if (pm > maxSize) pm = maxSize;
            bar.append("[ " + barTitle + " ] [ ");

        for (int i = 0; i < pm; i++) {
            if (i < 4) bar.append(ANSI_GREEN + "|" + ANSI_RESET);
                else if (i < 10) bar.append(ANSI_YELLOW + "|" + ANSI_RESET);
                    else bar.append(ANSI_RED + "|" + ANSI_RESET);
        }

        for (int i = 0; i < maxSize - pm; i++)
            bar.append(" ");

        if (percents < 10) bar.append(" ] " + percents + "%  of norm");
            else if (percents < 100) bar.append(" ] " + percents + "% of norm");
                else bar.append(" ] " + ANSI_RED + "WARNING!" + ANSI_RESET);

        return bar.toString();
    }

    private String generateHumidityBar(int humidity, String barTitle){
        StringBuilder bar = new StringBuilder(128);
        bar.append("[ " + barTitle + " ] [ " + ANSI_BLUE);
        for (int i=0; i< humidity/2; i++)
            bar.append("|");
        for (int i=0; i<maxSize-humidity/2; i++)
            bar.append(" ");
        bar.append(ANSI_RESET + " ] " + humidity + "%");

        return bar.toString();
    }

    private String hourParser(String Hour) {
        return Hour.substring(11, 16);
    }
}
