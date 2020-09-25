package simulator;

import java.util.ArrayList;
import java.util.List;

import simulator.aircrafts.Flyable;

public class Tower {
    private List<Flyable> observers = new ArrayList<Flyable>();
    private List<Flyable> landing = new ArrayList<Flyable>();

    public void register(Flyable flyable) {
        observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
            landing.add(flyable);
    }

    protected void conditionChanged() {
        for (Flyable observer : observers) {
            observer.updateConditions();
        }
        observers.removeAll(landing);
    }
}