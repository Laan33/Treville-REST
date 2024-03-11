package weather.station.contracts;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import weather.station.contracts.Metric;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "sensors")
public class Sensor {

    @Id
    private String id;

    private String countryName;
    private String cityName;
    private Map<String, Metric> metrics; // Metric -> (Timestamp, Value)


    Sensor() {
        this.metrics = new HashMap<>();
    }

    Sensor(String countryName, String cityName) {
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

    public Metric getMetric(String metricName) {
        return metrics.get(metricName);
    }

    public Metric getMetric(String metricName, LocalDate startDate, LocalDate endDate) {
        Metric metric = metrics.get(metricName);
        if (metric == null) {
            return null; // or handle the case where the metric is not found
        }

        Map<LocalDate, Double> filteredValues = new HashMap<>();
        for (Map.Entry<LocalDate, Double> entry : metric.getValues().entrySet()) {
            LocalDate date = entry.getKey();
            if (!date.isBefore(startDate) && !date.isAfter(endDate)) {
                filteredValues.put(date, entry.getValue());
            }
        }

        return new Metric(filteredValues);
    }

    public void addValueToMetric(String metricName, LocalDate date, Double value) {
        metrics.get(metricName).addValue(date, value);
    }

}
