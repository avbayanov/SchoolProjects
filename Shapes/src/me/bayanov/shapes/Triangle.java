package me.bayanov.shapes;

public class Triangle implements Shape {

    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;

        this.x2 = x2;
        this.y2 = y2;

        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        return Math.max(Math.max(x1, x2), x3) - Math.min(Math.min(x1, x2), x3);
    }

    @Override
    public double getHeight() {
        return Math.max(Math.max(y1, y2), y3) - Math.min(Math.min(y1, y2), y3);
    }

    @Override
    public double getArea() {
        return 0.5 * Math.abs((x1 - x3) * (y2 - y1) - (x1 - x2) * (y3 - y1));
    }

    @Override
    public double getPerimeter() {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) +
                Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2)) +
                Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Triangle {");
        sb.append(System.lineSeparator());
        sb.append("    x1 = ");
        sb.append(x1);
        sb.append(System.lineSeparator());
        sb.append("    y1 = ");
        sb.append(y1);
        sb.append(System.lineSeparator());
        sb.append("    x2 = ");
        sb.append(x2);
        sb.append(System.lineSeparator());
        sb.append("    y2 = ");
        sb.append(y2);
        sb.append(System.lineSeparator());
        sb.append("    x3 = ");
        sb.append(x3);
        sb.append(System.lineSeparator());
        sb.append("    y3 = ");
        sb.append(y3);
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

        Triangle triangle = (Triangle) obj;
        return this.x1 == triangle.x1 && this.y1 == triangle.y1
                && this.x2 == triangle.x2 && this.y2 == triangle.y2
                && this.x3 == triangle.x3 && this.y3 == triangle.y3;
    }

    @Override
    public int hashCode() {
        final int prime = 5;

        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);

        return hash;
    }
}
