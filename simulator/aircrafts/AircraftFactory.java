package simulator.aircrafts;

import simulator.aircrafts.Coordinates;

public class AircraftFactory {
    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height){
        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        if (type.equalsIgnoreCase("baloon")){
            return new Baloon(name, coordinates);
        } else if (type.equalsIgnoreCase("jetplane")){
            return new JetPlane(name, coordinates);
        } else if (type.equalsIgnoreCase("helicopter")){
            return new Helicopter(name, coordinates);
        } else {
            System.out.println("Not a valid aircraft type.");
            System.exit(1);
            return null;
        }
    }
}