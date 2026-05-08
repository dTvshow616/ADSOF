package ApartadoCuatro;

public class Weather implements Comparable<Weather> {
    private final WeatherCondition weatherCondition;
    private final Temperature temperature;
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public Weather(WeatherCondition weatherCondition, Temperature temperature) {
        this.weatherCondition = weatherCondition;
        this.temperature = temperature;
    }

    @Override
    public int compareTo(Weather o) {
        return (o.getWeatherCondition().compareTo(this.getWeatherCondition()) +
                o.getTemperature().compareTo(this.getTemperature()));
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public WeatherCondition getWeatherCondition() {
        return weatherCondition;
    }
}