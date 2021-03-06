package me.bayanov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Vector size must be > 0");
        }
        components = new double[length];
    }

    public Vector(Vector originalVector) {
        components = Arrays.copyOf(originalVector.components, originalVector.components.length);
    }

    public Vector(double[] components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("Vector size must be > 0");
        }

        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int length, double[] components) {
        if (length <= 0) {
            throw new IllegalArgumentException("Vector size must be > 0");
        }
        this.components = Arrays.copyOf(components, length);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{ ");
        for (double component : components) {
            sb.append(component);
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length() - 2);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Vector vector = (Vector) obj;
        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }

    public int getSize() {
        return components.length;
    }

    public void add(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Vector must be not null");
        }

        if (vector.components.length > components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Vector must be not null");
        }

        if (vector.components.length > components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyOnScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void reverse() {
        multiplyOnScalar(-1);
    }

    public double getLength() {
        double sumOfSquaredComponents = 0;

        for (double component : components) {
            sumOfSquaredComponents += Math.pow(component, 2);
        }

        return Math.sqrt(sumOfSquaredComponents);
    }

    public double getComponent(int index) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Index must be between 0 and (vector size - 1)");
        }
        return components[index];
    }

    public void setComponent(int index, double component) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Index must be between 0 and (vector size - 1)");
        }
        components[index] = component;
    }

    public static Vector add(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new NullPointerException("First Vector must be not null");
        }
        if (vector2 == null) {
            throw new NullPointerException("Second Vector must be not null");
        }

        Vector result = new Vector(vector1);
        result.add(vector2);

        return result;
    }

    public static Vector subtract(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new NullPointerException("First Vector must be not null");
        }
        if (vector2 == null) {
            throw new NullPointerException("Second Vector must be not null");
        }

        Vector result = new Vector(vector1);
        result.subtract(vector2);

        return result;
    }

    public static double scalarProduct(Vector vector1, Vector vector2) {
        if (vector1 == null) {
            throw new NullPointerException("First Vector must be not null");
        }
        if (vector2 == null) {
            throw new NullPointerException("Second Vector must be not null");
        }

        int minSize = Math.min(vector1.components.length, vector2.components.length);
        double sumOfMultipliedPairs = 0;
        for (int i = 0; i < minSize; i++) {
            sumOfMultipliedPairs += vector1.components[i] * vector2.components[i];
        }

        return sumOfMultipliedPairs;
    }
}
