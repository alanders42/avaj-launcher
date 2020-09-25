package simulator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import simulator.aircrafts.Flyable;
import simulator.aircrafts.AircraftFactory;
import simulator.WeatherTower;

public class Simulator{
    private static WeatherTower weatherTower;
    private static List<Flyable> aircrafts = new ArrayList<Flyable>();
    public static PrintWriter writer;
    public static void main(String[] arg){
        try {
          BufferedReader file = new BufferedReader(new FileReader(arg[0]));
          writer = new PrintWriter(new File("simulation.txt"));
          String buffer = file.readLine();
          if (buffer != null){
            int n = Integer.parseInt(buffer);
            if (n > 0){
              weatherTower = new WeatherTower();
              for (buffer = file.readLine(); buffer != null; buffer = file.readLine()){
                  String[] split = buffer.split(" ");
                  if (split.length != 5 || split[0].isEmpty() || split[1].isEmpty()){
                    System.out.println("Incorrect values entered.");
                    System.exit(1);
                  }
                  AircraftFactory aircraftFactory = new AircraftFactory();
                  Flyable aircraft = aircraftFactory.newAircraft(split[0], split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4]));
                  if (aircraft != null){
                    aircrafts.add(aircraft);
                    aircraft.registerTower(weatherTower);
                  }
                }
                if (aircrafts.isEmpty()){
                  System.out.println("No aircrafts registered.");
                  System.exit(1);
                }
                  for (int i = 0; i < n; i++){
                    weatherTower.changeWeather();
                  }
            } else {
              System.out.println("Number of simulations must be at least 1.");
            }
          } else {
            System.out.println("Scenario file can't be empty.");
          }
          writer.close();
          file.close();
        } catch (FileNotFoundException e) {
			System.out.println("File not found: " + arg[0]);
		} catch (IOException e) {
			System.out.println("There was an error while reading the file: " + arg[0]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Incorrect number of values entered.");
		} catch (NumberFormatException e){
			System.out.println("Number of simulations or coordinates not a valid number.");
    }catch (Exception e) {
			System.out.println(e);
        }
    }
}