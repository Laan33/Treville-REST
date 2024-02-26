package weather.station;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;


    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Sensor registerSensor(Sensor sensor) {
        return sensor;
    }

    public void updateSensorMetrics(String sensorId, Map<String, Double> metrics) {

    }

//    public void addMetricToSensor(String sensorId, String metricName, String timestamp, Double value) {
//        Optional<Sensor> optionalSensor = sensorRepository.findById(sensorId);
//        if (optionalSensor.isPresent()) {
//            Sensor sensor = optionalSensor.get();
//            Map<String, Map<String, Double>> metrics = sensor.getMetrics();
//            // Update metrics map with new metric data
//            // Assuming metrics is already initialized
//            metrics.computeIfAbsent(metricName, k -> new HashMap<>()).put(timestamp, value);
//            sensor.setMetrics(metrics);
//            sensorRepository.save(sensor);
//        } else {
//            // Handle case when sensor is not found
//            // You can throw an exception or handle it based on your requirements
//        }
//    }

    public void addMetricToSensor(String sensorId, String metricName) {
        Optional<Sensor> optionalSensor = sensorRepository.findById(sensorId);
        if (optionalSensor.isPresent()) {
            Sensor sensor = optionalSensor.get();

            Map<String, Metric> metricMap = sensor.getMetrics();

            metricMap.put(metricName, new Metric());
            sensorRepository.save(sensor);

        } else {
            // Handle case when sensor is not found
        }
    }


}

