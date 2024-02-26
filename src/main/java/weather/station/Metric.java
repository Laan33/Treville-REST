package weather.station;

import java.util.Map;

class Metric {
    private Map<String, Double> values; // Timestamp -> Value

    Metric() {
    }

    public Metric(Map<String, Double> values) {
        this.values = values;
    }

    public Map<String, Double> getValues() {
        return values;
    }

    public void setValues(Map<String, Double> values) {
        this.values = values;
    }

    public void addValue(String timeStamp, Double value) {
        values.put(timeStamp, value);
    }
}