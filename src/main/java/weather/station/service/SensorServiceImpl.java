package weather.station.service;

import org.springframework.stereotype.Service;
import weather.station.dao.SensorRepository;
import weather.station.contracts.Metric;
import weather.station.contracts.Sensor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;


    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    @Override
    public Optional<Sensor> findBySensorId(String sensorId) {
        return sensorRepository.findById(sensorId);
    }

//    @Override
//    public List<Sensor> findByCountryNameAndCityName(String cityName, String countryName) {
//        return sensorRepository.findByCityNameAndCountryName(countryName, cityName);
//    }

    @Override
    public Sensor registerSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    @Override
    public void addMetricToSensor(String sensorId, String metricName) {
        sensorRepository.findById(sensorId).ifPresent(sensor -> {
            sensor.addMetric(metricName);
            sensorRepository.save(sensor);
        });
    }

    @Override
    public void addValuesToSensorMetric(String sensorId, String metricName, LocalDate date, Double value) {
        sensorRepository.findById(sensorId).ifPresent(sensor -> {
            sensor.addValueToMetric(metricName, date, value);
            sensorRepository.save(sensor);
        });
    }

    @Override
    public void deleteSensorById(String sensorId) {
        sensorRepository.deleteById(sensorId);
    }

    @Override
    public String getMetricValue(String sensorId, String metric, LocalDate startDate, LocalDate endDate) {
        Optional<Sensor> sensorOptional = sensorRepository.findById(sensorId);
        if (sensorOptional.isPresent()) {
            Sensor sensor = sensorOptional.get();
            Metric sensorMetric = sensor.getMetric(metric);
            if (sensorMetric != null) {
                return String.format(
                        "City: %s, SensorID: %s, Metric: %s, Average value: %10.2f",
                        sensor.getCityName(),
                        sensorId,
                        metric,
                        sensorMetric.getAverage());
            }

        }
        return "Metric does not exist";
    }

    @Override
    public List<String> getMetricValues(List<String> sensorIds, List<String> metrics, LocalDate startDate, LocalDate endDate) {
        List<String> reportList = new ArrayList<>();

        for (String sensorId : sensorIds) {
            for (String metric : metrics) {
                Optional<Sensor> sensorOptional = sensorRepository.findById(sensorId);
                if (sensorOptional.isPresent()) {
                    Sensor sensor = sensorOptional.get();
                    Metric sensorMetric = sensor.getMetric(metric, startDate, endDate);

                    if (sensorMetric != null) {
                        reportList.add(String.format(
                                "City: %s, SensorID: %s, Metric: %s, Average value: %10.2f",
                                sensor.getCityName(),
                                sensorId,
                                metric,
                                sensorMetric.getAverage()));
                    }
                }
            }
        }
        return reportList;
    }
}

