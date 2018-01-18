package json;

public class Forecast {
    String fromDateTime;
    Measurments measurments;
    String tillDateTime;

    public String getFromDateTime() {
        return fromDateTime;
    }

    public void setFromDateTime(String fromDateTime) {
        this.fromDateTime = fromDateTime;
    }

    public Measurments getMeasurments() {
        return measurments;
    }

    public void setMeasurments(Measurments measurments) {
        this.measurments = measurments;
    }

    public String getTillDateTime() {
        return tillDateTime;
    }

    public void setTillDateTime(String tillDateTime) {
        this.tillDateTime = tillDateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fromDateTime = " + fromDateTime + "\n");
        sb.append(measurments.toString() + "\n");
        sb.append("tillDateTime = " + tillDateTime);
        return  sb.toString();
    }
}
