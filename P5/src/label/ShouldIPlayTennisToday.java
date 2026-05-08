package label;

import weather.*;

public class ShouldIPlayTennisToday implements LabelProvider<Weather, Boolean> {

    @Override
    public Boolean provideLabel(Weather object) {
        if (object.getTemperature() == Temperature.EXTREMELY_COLD ||
            object.getTemperature() == Temperature.EXTREMELY_HOT) {
            return false;
        }

        if (object.getWeatherCondition() == WeatherCondition.RAINY ||
            object.getWeatherCondition() == WeatherCondition.SNOWING) {
            return false;
        }

        return true;

    }
}