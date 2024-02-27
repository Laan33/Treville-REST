package weather.station;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SensorService {
    List<Sensor> findAll();
    Optional<Sensor> findBySensorId(String sensorId);
//    List<Sensor> findByCountryNameAndCityName(String cityName, String countryName);
    Sensor registerSensor(Sensor sensor);

    void addMetricToSensor(String sensorId, String metricName);
    void addValuesToSensorMetric(String sensorId, String metricName, LocalDate date, Double value);
    void deleteSensorById(String sensorId);

    //    @Override
//    public String[] getMetricValue(String[] sensorIds, String[] metrics, LocalDate startDate, LocalDate endDate) {
//        String strBuilder = "";
//        for (String sensorId : sensorIds) {
//            for (String metric : metrics) {
////                strBuilder.concat(sensorRepository.findById(sensorId).ifPresent(sensor -> sensor.getMetric(metric).getAverage()));
//                sensorRepository.findById(sensorId).ifPresent(sensor -> strBuilder.concat(sensor.getMetric(metric).getAverage()));
//
//            }
//        }
//
//        return new String[0];
//    }
    String getMetricValue(String sensorId, String metric, LocalDate startDate, LocalDate endDate);

    public List<String> getMetricValues(List<String> sensorIds, List<String> metrics, LocalDate startDate, LocalDate endDate);

}

//    void updateSensorMetrics(String sensorId, Map<String, Double> metrics);
//    Map<String, Double> getAverageMetrics(String sensorId, String[] metrics, LocalDate startDate, LocalDate endDate);