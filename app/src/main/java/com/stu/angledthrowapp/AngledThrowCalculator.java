package com.stu.angledthrowapp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class AngledThrowCalculator {

    public static ArrayList<Double> xCoords;
    public static ArrayList<Double> yCoords;
    public static ArrayList<Double> timePoints;
    public static Double g = 9.81, timeStep = 0.1, velocity, angle;
    public static boolean IsCalculated = false;
    public static ResponseAngledThrow responseAngledThrow = new ResponseAngledThrow();
    public static int lastChange = 0;

    private static void Init() {
        if (xCoords == null || yCoords == null || timePoints == null) {
            xCoords = new ArrayList<>();
            yCoords = new ArrayList<>();
            timePoints = new ArrayList<>();
        }
        if (velocity == null || angle == null) {
            throw new IllegalArgumentException("Cannot calculate with null inputs");
        }

    }

    private static void ClearLists() {
        xCoords.clear();
        yCoords.clear();
        timePoints.clear();
        lastChange = 1;
    }

    public static void CalculateLocally() {

        double x = 0, y = 0, time = 0;
        double timeStop = (2 * velocity * Math.sin(Math.toRadians(angle))) / g;
        Init();
        ClearLists();

        for (; time < timeStop; time += timeStep) {
            x = velocity * time * Math.cos(Math.toRadians(angle));
            y = velocity * time * Math.sin(Math.toRadians(angle)) - ((g * Math.pow(time, 2)) / 2);

            xCoords.add(round(x, 4));
            yCoords.add(round(y, 4));
            timePoints.add(round(time, 4));
        }

        //add last point and time
        x = velocity * timeStop * Math.cos(Math.toRadians(angle));
        xCoords.add(round(x, 4));
        yCoords.add(0.0);
        timePoints.add(round(timeStop, 4));
        IsCalculated = true;
    }


    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
