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

    private static double getLineLength(double xA, double yA, double xB, double yB) {
        return Math.sqrt(Math.pow(xB - xA, 2) + Math.pow(yB - yA, 2));
    }

    @Override
    public double getPerimeter() {
        return getLineLength(x1, y1, x2, y2) + getLineLength(x2, y2, x3, y3) + getLineLength(x3, y3, x1, y1);
    }

    @Override
    public String toString() {
        return "Triangle {" + System.lineSeparator() + "    x1 = " + x1 + System.lineSeparator()
                + "    y1 = " + y1 + System.lineSeparator() + "    x2 = " + x2 + System.lineSeparator()
                + "    y2 = " + y2 + System.lineSeparator() + "    x3 = " + x3 + System.lineSeparator()
                + "    y3 = " + y3 + System.lineSeparator() + "}";
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
