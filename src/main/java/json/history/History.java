package json.history;

public class History {
    private String fromDateTime;
    private Measurments measurements;
    private String tillDateTime;

    public String getFromDateTime() {
        return fromDateTime;
    }

    public void setFromDateTime(String fromDateTime) {
        this.fromDateTime = fromDateTime;
    }

    public Measurments getMeasurments() {
        return measurements;
    }

    public void setMeasurments(Measurments measurments) {
        this.measurements = measurements;
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
        sb.append(measurements.toString() + "\n");
        sb.append("tillDateTime = " + tillDateTime);
        return  sb.toString();
    }
}
