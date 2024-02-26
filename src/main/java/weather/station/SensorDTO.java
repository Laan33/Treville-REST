package weather.station;

import java.util.HashMap;
import java.util.Map;

public class SensorDTO {
    private String id;
    private String countryName;
    private String cityName;
    private Map<String, Metric> metrics; // Metric -> (Timestamp, Value)


    SensorDTO() {
        this.metrics = new HashMap<>();
    }

    SensorDTO(String countryName, String cityName) {
        this.countryName = countryName;
        this.cityName = cityName;
        this.metrics = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Map<String, Metric> getMetrics() {
        return metrics;
    }

    public void setMetrics(Map<String, Metric> metrics) {
        this.metrics = metrics;
    }

    public void addMetric(String metricName) {
        metrics.put(metricName, new Metric());
    }

}

