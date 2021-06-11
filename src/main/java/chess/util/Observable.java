package chess.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gr.45
 */
public class Observable {

    /**
     * List of all observers
     */
    private List<Observer> observers = new ArrayList<Observer>();

    /**
     * Add an observer
     * @param obs
     */
    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    /**
     * Delete an observer
     * @param obs
     */
    public void removeObserver(Observer obs) {
        observers.remove(obs);
    }

    /**
     * Clear the list of observers
     */
    public void clearObservers() {
        observers.clear();
    }

    /**
     * Notify the observer that something changed
     */
    public void notifyObservers() {
        for (Observer obs: observers) {
            obs.update();
        }
    }
}
