package feature;

import weather.Weather;

import java.util.Arrays;
import java.util.List;

public class WeatherFeaturizer implements Featurizer<Weather> {

    private static final List<String> FEATURES = Arrays.asList("weatherCondition", "temperature");

    @Override
    public List<String> featureNames() {
        return FEATURES;
    }

    @Override
    public Object featureValue(Weather object, String featureName) {
        return switch (featureName) {
            case "weatherCondition" -> object.getWeatherCondition();
            case "temperature" -> object.getTemperature();
            default -> throw new IllegalArgumentException("Unknown feature: " + featureName);
        };
    }
}