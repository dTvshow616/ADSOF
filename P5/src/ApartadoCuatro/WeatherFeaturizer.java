package ApartadoCuatro;

import ApartadoUno.Featurizer;

import java.util.Arrays;
import java.util.List;

public class WeatherFeaturizer implements Featurizer<Weather> {

    private static final List<String> FEATURES = Arrays.asList("weatherCondition", "temperature");

    @Override
    public List<String> featureNames() {
        return FEATURES;
    }

    @Override
    public Object importantFeatureValues(Weather object) {
        return "(weatherCondition: " + object.getWeatherCondition() + "temperature: " + object.getTemperature() + ")";
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