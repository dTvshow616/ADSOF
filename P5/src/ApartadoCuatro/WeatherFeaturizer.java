package ApartadoCuatro;

import ApartadoUno.Featurizer;

import java.util.List;

public class WeatherFeaturizer implements Featurizer<Weather> {
    @Override
    public List<String> featureNames() {
        return List.of(); // DUE
    }

    @Override
    public Object importantFeatureValues(Weather object) {
        return null; // DUE
    }

    @Override
    public Object featureValue(Weather object, String featureName) {
        return null; // DUE
    }
}