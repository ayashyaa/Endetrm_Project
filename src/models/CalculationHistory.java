package models;

public class CalculationHistory {
    private String figure;
    private double area;
    private double perimeter;
    private double volume;

    public CalculationHistory(String shape, double area, double perimeter, double volume) {
        this.figure = shape;
        this.area = area;
        this.perimeter = perimeter;
        this.volume = volume;
    }

    public String getFigure() {
        return figure;
    }

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public double getVolume() {
        return volume;
    }
}
