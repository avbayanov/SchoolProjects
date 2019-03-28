package me.bayanov.shapes;

public class Rectangle implements Shape {
    private double sideA;
    private double sideB;

    public Rectangle(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
    }

    @Override
    public double getWidth() {
        return sideA;
    }

    @Override
    public double getHeight() {
        return sideB;
    }

    @Override
    public double getArea() {
        return sideA * sideB;
    }

    @Override
    public double getPerimeter() {
        return (sideA + sideB) * 2;
    }

    @Override
    public String toString() {
        return "Rectangle {" + System.lineSeparator() + "    sideA = " + sideA + System.lineSeparator()
                + "    sideB = " + sideB + System.lineSeparator() + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Rectangle rectangle = (Rectangle) obj;
        return this.sideA == rectangle.sideA && this.sideB == rectangle.sideB;
    }

    @Override
    public int hashCode() {
        final int prime = 5;

        int hash = 1;
        hash = prime * hash + Double.hashCode(sideA);
        hash = prime * hash + Double.hashCode(sideB);

        return hash;
    }
}
