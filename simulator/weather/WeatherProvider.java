package simulator.weather;

import java.util.Random;

import simulator.aircrafts.Coordinates;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static Random rand = new Random();
    private static String[] weather = {
        "RAIN",
        "FOG",
        "SUN",
        "SNOW"
    };

    private WeatherProvider(){

    }

    public static WeatherProvider getProvider(){
        return(WeatherProvider.weatherProvider);
    }

    public String getCurrentWeather(Coordinates coordinates){
        return (WeatherProvider.weather[rand.nextInt(4)]);
    }
}