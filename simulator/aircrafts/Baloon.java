package simulator.aircrafts;

import simulator.aircrafts.Aircraft;
import simulator.aircrafts.Coordinates;
import simulator.Simulator;
import simulator.WeatherTower;

public class Baloon extends Aircraft implements Flyable{
    private WeatherTower weatherTower;
    Baloon (String name, Coordinates coordinates) {
        super(name, coordinates);
    }
    public void updateConditions(){
        String weather = weatherTower.getWeather(coordinates);
        String comment = "";
        if (weather.equals("RAIN")){
            this.coordinates = new Coordinates(
                coordinates.getLongitude(),
                coordinates.getLatitude(),
                coordinates.getHeight() - 5
            );
            comment = "It' raining, its pouring.";
        } else if (weather.equals("FOG")){
            this.coordinates = new Coordinates(
                coordinates.getLongitude(),
                coordinates.getLatitude(),
                coordinates.getHeight() - 3
            );
            comment = "So gloomy.";
        } else if (weather.equals("SUN")){
            this.coordinates = new Coordinates(
                coordinates.getLongitude() + 2,
                coordinates.getLatitude(),
                coordinates.getHeight() + 4
            );
            comment = "Perfect weather.";
        } else if (weather.equals("SNOW")){
            this.coordinates = new Coordinates(
                coordinates.getLongitude(),
                coordinates.getLatitude(),
                coordinates.getHeight() - 15
            );
            comment = "It's snowing. We're  gonna crash.";
        }
        Simulator.writer.println("Baloon#" + this.name + "(" + this.id + "): " + comment);
        if (this.coordinates.getHeight() <= 0){
            this.weatherTower.unregister(this);
            Simulator.writer.println("Baloon#" + this.name + "(" + this.id + ") landing.");
            Simulator.writer.println("Tower says: Baloon#" + this.name + "(" + this.id + ") unregistered from weather tower.");
        }
    }
    public void registerTower(WeatherTower weatherTower){
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Simulator.writer.println("Tower says: Baloon#" + this.name + "(" + this.id + ") registered to weather tower.");
    }
}