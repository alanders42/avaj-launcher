package simulator.aircrafts;

import simulator.aircrafts.Aircraft;
import simulator.aircrafts.Coordinates;
import simulator.WeatherTower;
import simulator.Simulator;

public class JetPlane extends Aircraft implements Flyable{
    private WeatherTower weatherTower;
    JetPlane (String name, Coordinates coordinates) {
        super(name, coordinates);
    }
    public void updateConditions(){
        String weather = weatherTower.getWeather(coordinates);
        String comment = "";
        if (weather.equals("RAIN")){
            this.coordinates = new Coordinates(
                coordinates.getLongitude(),
                coordinates.getLatitude() + 5,
                coordinates.getHeight()
            );
            comment = "Better watch out for lighting.";
        } else if (weather.equals("FOG")){
            this.coordinates = new Coordinates(
                coordinates.getLongitude(),
                coordinates.getLatitude() + 1,
                coordinates.getHeight()
            );
            comment = "Is that a mountain?";
        } else if (weather.equals("SUN")){
            this.coordinates = new Coordinates(
                coordinates.getLongitude(),
                coordinates.getLatitude() + 10,
                coordinates.getHeight() + 2
            );
            comment = "Great day for flying.";
        } else if (weather.equals("SNOW")){
            this.coordinates = new Coordinates(
                coordinates.getLongitude(),
                coordinates.getLatitude(),
                coordinates.getHeight() - 7
            );
            comment = "We gonna freeze";
        }
        Simulator.writer.println("JetPlane#" + this.name + "(" + this.id + "): " + comment);
        if (this.coordinates.getHeight() <= 0){
            this.weatherTower.unregister(this);
            Simulator.writer.println("JetPlane#" + this.name + "(" + this.id + ") landing.");
            Simulator.writer.println("Tower says: JetPlane#" + this.name + "(" + this.id + ") unregistered from weather tower.");
        }
    }
    public void registerTower(WeatherTower weatherTower){
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Simulator.writer.println("Tower says: JetPlane#" + this.name + "(" + this.id + ") registered to weather tower.");
    }
}