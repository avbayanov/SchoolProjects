package me.bayanov.shapes;

public class Rectangle implements Shape {

    private double a;
    private double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public double getWidth() {
        return a;
    }

    @Override
    public double getHeight() {
        return b;
    }

    @Override
    public double getArea() {
        return a * b;
    }

    @Override
    public double getPerimeter() {
        return (a + b) * 2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Rectangle {");
        sb.append(System.lineSeparator());
        sb.append("    a = ");
        sb.append(a);
        sb.append(System.lineSeparator());
        sb.append("    b = ");
        sb.append(b);
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

        Rectangle rectangle = (Rectangle) obj;
        return this.a == rectangle.a && this.b == rectangle.b;
    }

    @Override
    public int hashCode() {
        final int prime = 5;

        int hash = 1;
        hash = prime * hash + Double.hashCode(a);
        hash = prime * hash + Double.hashCode(b);

        return hash;
    }
}
