package json;

public abstract class AbstractMeasurments {
    int airQualityIndex;
    int humidity;
    int measurementTime;
    int pm1;
    int pm10;
    int pm25;
    int pollutionLevel;
    int pressure;
    int temperature;
    int windDirection;
    int windSpeed;

    public int getAirQualityIndex() {
        return airQualityIndex;
    }

    public void setAirQualityIndex(int airQualityIndex) {
        this.airQualityIndex = airQualityIndex;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(int measurementTime) {
        this.measurementTime = measurementTime;
    }

    public int getPm1() {
        return pm1;
    }

    public void setPm1(int pm1) {
        this.pm1 = pm1;
    }

    public int getPm10() {
        return pm10;
    }

    public void setPm10(int pm10) {
        this.pm10 = pm10;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }

    public int getPollutionLevel() {
        return pollutionLevel;
    }

    public void setPollutionLevel(int pollutionLevel) {
        this.pollutionLevel = pollutionLevel;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AirQualityIndex = " +airQualityIndex + "\n");
        sb.append("Humidity = " + humidity + "\n");
        sb.append("pm1 = " + pm1 + "\n");
        sb.append("pm10 =" + pm10 + "\n");
        sb.append("pm25 = " + pm25 + "\n");
        sb.append("pollutionLevel = " + pollutionLevel + "\n");
        sb.append("pressure = " + pressure + "\n");
        sb.append("temperature = " + temperature + "\n");
        sb.append("windDirection = " + windDirection + "\n");
        sb.append("windSpeed = " + windSpeed);
        return sb.toString();
    }
}
