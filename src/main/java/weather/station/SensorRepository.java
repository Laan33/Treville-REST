package weather.station;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "sensors", path = "sensors")
public interface SensorRepository extends MongoRepository<Sensor, String> {

//    List<Sensor> findByCityName(@Param("cityName") String cityName);
    List<Metric> querySensorMetrics()


//    void addSensorMetric(@Param());

//    List<String> querySensorMetric();


//    Sensor findById();


//    public Sensor findById(Long id);
//    public List<Sensor> findByCounty(String county);




}
