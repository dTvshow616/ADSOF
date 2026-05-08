package ApartadoCuatro;

public class Weather {
    private final WeatherCondition weatherCondition;
    private final Temperature temperature;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public Weather(WeatherCondition weatherCondition, Temperature temperature) {
        this.weatherCondition = weatherCondition;
        this.temperature = temperature;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public WeatherCondition getWeatherCondition() {
        return weatherCondition;
    }
}