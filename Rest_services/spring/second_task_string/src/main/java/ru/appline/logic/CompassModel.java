package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CompassModel implements Serializable {
    private static final CompassModel instance = new CompassModel();
    private final Map<String, Range> model;

    public CompassModel() {
        model = new HashMap<String, Range>();
    }

    public static CompassModel getInstance() {
        return instance;
    }

    public void setDirection(String direction, Range range) {
        model.put(direction, range);
    }

    public String getDirection(int degree) {
        for (Map.Entry<String, Range> entry : model.entrySet()) {
            if (entry.getValue().isInRange(degree)) {
                return entry.getKey();
            }
        }
        return "Unknown direction";
    }

    public Map<String, Range> getDirections() {
        return model;
    }
}
