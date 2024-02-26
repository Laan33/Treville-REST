package weather.station;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorServiceImpl sensorServiceImpl;

    public SensorController(SensorServiceImpl sensorServiceImpl) {
        this.sensorServiceImpl = sensorServiceImpl;
    }

    // REST endpoints for sensor operations

    @GetMapping
    Collection<Docum>

//
//    @PostMapping
//    public ResponseEntity<String> registerSensor(@RequestBody Sensor sensor) {
//        Sensor registeredSensor = sensorServiceImpl.registerSensor(sensor);
//        return ResponseEntity.ok(registeredSensor);
//    }


//
//    @PostMapping("/{sensorId}/metrics")
//    public ResponseEntity<String> addMetricToSensor(@PathVariable String sensorId, @RequestBody MetricRequest metricRequest) {
//        // Implement logic to add metric to sensor
//    }
//
//    @GetMapping("/{sensorId}")
//    public ResponseEntity<Sensor> getSensorById(@PathVariable String sensorId) {
//        // Implement logic to retrieve sensor by ID
//    }
//
//    @GetMapping("/{sensorId}/data")
//    public ResponseEntity<SensorData> querySensorData(@PathVariable String sensorId, @RequestParam(required = false) String metric, @RequestParam(required = false) String startDate, @RequestParam(required = false) String endDate) {
//        // Implement logic to query sensor data
//    }
}
