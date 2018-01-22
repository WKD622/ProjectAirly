package json;

import json.currentmeasurments.CurrentMeasurements;
import json.history.History;
import java.util.Arrays;
import java.util.List;

public class JsonModel {
    private CurrentMeasurements currentMeasurements;
    private List<History> history;

    public CurrentMeasurements getCurrentMeasurements() {
        return currentMeasurements;
    }

    public void setCurrentMeasurements(CurrentMeasurements currentMeasurements) {
        this.currentMeasurements = currentMeasurements;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(256);
        stringBuilder.append("History: " + Arrays.toString(getHistory().toArray()) + "\n");
        stringBuilder.append("CurrentMeasurments:\n" + currentMeasurements.toString());
        return stringBuilder.toString();
    }

}
