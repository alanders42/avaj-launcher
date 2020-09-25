package simulator.aircrafts;

import simulator.aircrafts.Aircraft;
import simulator.aircrafts.Coordinates;
import simulator.WeatherTower;
import simulator.Simulator;

public class Helicopter extends Aircraft implements Flyable{
    private WeatherTower weatherTower;
    Helicopter (String name, Coordinates coordinates) {
        super(name, coordinates);
    }
    public void updateConditions(){
        String weather = weatherTower.getWeather(coordinates);
        String comment = "";
        if (weather.equals("RAIN")){
            this.coordinates = new Coordinates(
                coordinates.getLongitude() + 5,
                coordinates.getLatitude(),
                coordinates.getHeight()
            );
            comment = "The rain is making it hard to fly";
        } else if (weather.equals("FOG")){
            this.coordinates = new Coordinates(
                coordinates.getLongitude() + 1,
                coordinates.getLatitude(),
                coordinates.getHeight()
            );
            comment = "Can't see where we're going.";
        } else if (weather.equals("SUN")){
            this.coordinates = new Coordinates(
                coordinates.getLongitude() + 10,
                coordinates.getLatitude(),
                coordinates.getHeight() + 2
            );
            comment = "This is hot.";
        } else if (weather.equals("SNOW")){
            this.coordinates = new Coordinates(
                coordinates.getLongitude(),
                coordinates.getLatitude(),
                coordinates.getHeight() - 12
            );
            comment = "My rotor is going to freeze!";
        }
        Simulator.writer.println("Helicopter#" + this.name + "(" + this.id + "): " + comment);
        if (this.coordinates.getHeight() <= 0){
            this.weatherTower.unregister(this);
            Simulator.writer.println("Helicopter#" + this.name + "(" + this.id + ") landing.");
            Simulator.writer.println("Tower says: Helicopter#" + this.name + "(" + this.id + ") unregistered from weather tower.");
        }
    }
    public void registerTower(WeatherTower weatherTower){
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Simulator.writer.println("Tower says: Helicopter#" + this.name + "(" + this.id + ") registered to weather tower.");
    }
}