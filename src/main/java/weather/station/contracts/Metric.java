    package weather.station.contracts;

    import java.time.LocalDate;
    import java.util.HashMap;
    import java.util.Map;

    public class Metric {
        private Map<LocalDate, Double> values; // Timestamp -> Value

        public Metric() {
            values = new HashMap<>();
        }

        public Metric(Map<LocalDate, Double> values) {
            this.values = values;
        }

        public Map<LocalDate, Double> getValues() {
            return values;
        }

        public void setValues(Map<LocalDate, Double> values) {
            this.values = values;
        }

        public void addValue(LocalDate date, Double value) {
            values.put(date, value);
        }

        public double getAverage() {
            if (values.isEmpty()) {
                return 0.0; // Return 0 if the map is empty to avoid division by zero
            }

            double sum = 0.0;
            for (Double value : values.values()) {
                sum += value;
            }
            return sum;
        }
    }