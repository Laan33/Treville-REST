package weather.station.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import weather.station.contracts.Metric;
import weather.station.contracts.Sensor;

import java.util.Date;
import java.util.List;

public interface SensorRepository extends MongoRepository<Sensor, String> {

//    List<Sensor> findByCityName(@Param("cityName") String cityName);
@Query("{'_id': ?0, 'metrics.name': { $in: ?1 }, 'metrics.date': { $gte: ?2, $lte: ?3 }}")
List<Metric> getMetrics(String sensorId, List<String> metrics, Date startDate, Date endDate);
//    List<Sensor> findByCityNameAndCountryName(String countryName, String cityName);



//    void addSensorMetric(@Param());

//    List<String> querySensorMetric();


//    Sensor findById();


//    public Sensor findById(Long id);
//    public List<Sensor> findByCounty(String county);




}
