package me.bayanov.shapes;

public class Circle implements Shape {

    private double r;

    public Circle(double r) {
        this.r = r;
    }

    @Override
    public double getWidth() {
        return r * 2;
    }

    @Override
    public double getHeight() {
        return r * 2;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(r, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * r;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Circle {");
        sb.append(System.lineSeparator());
        sb.append("    r = ");
        sb.append(r);
        sb.append(System.lineSeparator());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Circle circle = (Circle) obj;
        return this.r == circle.r;
    }

    @Override
    public int hashCode() {
        final int prime = 5;

        int hash = 1;
        hash = prime * hash + Double.hashCode(r);

        return hash;
    }
}
