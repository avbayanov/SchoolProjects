package me.bayanov.shapes;

public class Square implements Shape {

    private double a;

    public Square(double a) {
        this.a = a;
    }

    @Override
    public double getWidth() {
        return a;
    }

    @Override
    public double getHeight() {
        return a;
    }

    @Override
    public double getArea() {
        return Math.pow(a, 2);
    }

    @Override
    public double getPerimeter() {
        return a * 4;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Square {");
        sb.append(System.lineSeparator());
        sb.append("    a = ");
        sb.append(a);
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

        Square square = (Square) obj;
        return this.a == square.a;
    }

    @Override
    public int hashCode() {
        final int prime = 5;

        int hash = 1;
        hash = prime * hash + Double.hashCode(a);

        return hash;
    }
}
