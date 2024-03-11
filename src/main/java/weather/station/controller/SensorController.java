package weather.station.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import weather.station.dao.SensorDTO;
import weather.station.service.SensorService;
import weather.station.contracts.Sensor;
import weather.station.utils.ObjectMapperUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping(value = "/")
    public List<SensorDTO> getAllSensors() {
        return ObjectMapperUtils.mapAll(sensorService.findAll(), SensorDTO.class);
    }

    @GetMapping(value = "/{sensorId}")
    public SensorDTO getSensorById(@PathVariable("sensorId") String sensorId) {
        return ObjectMapperUtils.map(sensorService.findBySensorId(sensorId), SensorDTO.class);
    }

//    @GetMapping(value = "/byCountryAndCity/{countryName}/{cityName}")
//    public SensorDTO getSensorByCountryAndCity(
//            @PathVariable("countryName") String countryName,
//            @PathVariable("cityName") String cityName) {
//        return ObjectMapperUtils.map(sensorService.findByCountryNameAndCityName(countryName, cityName), SensorDTO.class);
//    }

    @PostMapping(value = "/{sensorId}")
    public ResponseEntity<String> registerSensor(@RequestBody SensorDTO sensorDTO) {
        Sensor sensor = ObjectMapperUtils.map(sensorDTO, Sensor.class);

        // Perform registration logic in the service layer
        sensorService.registerSensor(sensor);

        // You can customize the response as needed
        return new ResponseEntity<>("Sensor registered successfully" + sensor.toString(), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{sensorId}")
    public ResponseEntity<?> deleteSensorById(@PathVariable("sensorId") String sensorId) {
        sensorService.deleteSensorById(sensorId);
        return new ResponseEntity<>("Sensor deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/{sensorId}/metric/{metricName}")
    public ResponseEntity<String> addMetricToSensor(
            @PathVariable("sensorId") String sensorId,
            @PathVariable("metricName") String metricName) {
        sensorService.addMetricToSensor(sensorId, metricName);
        return new ResponseEntity<>("Metric added to sensor successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/{sensorId}/metric/{metricName}/values")
    public ResponseEntity<String> addValuesToSensorMetric(
            @PathVariable("sensorId") String sensorId,
            @PathVariable("metricName") String metricName,
            @RequestBody Map<String, Object> requestBody) {

        LocalDate date = LocalDate.parse((CharSequence) requestBody.get("date"));
        Double value = (Double) requestBody.get("value");

        sensorService.addValuesToSensorMetric(sensorId, metricName, date, value);
        return new ResponseEntity<>("Values added to sensor metric successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/{sensorId}/metric/{metricName}/values")
    public String getSingularMetricValue(@PathVariable("sensorId") String sensorId,
                                             @PathVariable("metricName") String metricName,
                                             @RequestBody Map<String, Object> requestBody) {

        LocalDate startDate = LocalDate.parse((CharSequence) requestBody.get("startDate"));
        LocalDate endDate = LocalDate.parse((CharSequence) requestBody.get("startDate"));
        return  sensorService.getMetricValue(sensorId, metricName, startDate, endDate);
    }

    @GetMapping(value = "/metric/values")
    public List<String> getSensorsMetricValues(@RequestBody Map<String, Object> requestBody) {
        List<String> sensorIds = (List<String>) requestBody.get("sensorIds");
        List<String> metrics = (List<String>) requestBody.get("metrics");
        LocalDate startDate = LocalDate.parse((CharSequence) requestBody.get("startDate"));
        LocalDate endDate = LocalDate.parse((CharSequence) requestBody.get("endDate"));

        return sensorService.getMetricValues(sensorIds, metrics, startDate, endDate);
    }

}
