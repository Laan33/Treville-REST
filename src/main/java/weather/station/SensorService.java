package weather.station;

import java.util.Map;

public interface SensorService {
    Sensor registerSensor(Sensor sensor);
    void updateSensorMetrics(String sensorId, Map<String, Double> metrics);
//    Map<String, Double> getAverageMetrics(String sensorId, String[] metrics, LocalDate startDate, LocalDate endDate);
    void addMetricToSensor(String sensorId, String metricName);
}
