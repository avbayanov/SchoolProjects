package me.bayanov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int length) {
        components = new double[length];
    }

    public Vector(Vector originalVector) {
        components = Arrays.copyOf(originalVector.components, originalVector.components.length);
    }

    public Vector(double[] components) {
        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int length, double[] components) throws IllegalArgumentException {
        if (length <= 0) {
            throw new IllegalArgumentException("Vector length must be > 0");
        }
        this.components = Arrays.copyOf(components, length);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{ ");
        for (double component: components) {
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

    public void add(Vector vector) throws NullPointerException {
        if (vector == null) {
            throw new  NullPointerException("Vector must be not null");
        }

        Vector result = add(this, vector);
        components = result.components;
    }

    public void subtract(Vector vector) throws NullPointerException {
        if (vector == null) {
            throw new  NullPointerException("Vector must be not null");
        }

        Vector result = subtract(this, vector);
        components = result.components;
    }

    public void scalarMultiple(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void reverse() {
        for (int i = 0; i < components.length; i++) {
            components[i] = -components[i];
        }
    }

    public double getLength() {
        double sumOfSquaredComponents = 0;

        for (double component: components) {
            sumOfSquaredComponents += Math.pow(component, 2);
        }

        return Math.sqrt(sumOfSquaredComponents);
    }

    public double getComponent(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Index must be between 0 and (vector length - 1)");
        }
        return components[index];
    }

    public void setComponent(int index, double component) throws IndexOutOfBoundsException {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Index must be between 0 and (vector length - 1)");
        }
        components[index] = component;
    }

    public static Vector add(Vector vector1, Vector vector2) throws NullPointerException {
        if (vector1 == null || vector2 == null) {
            throw new  NullPointerException("Each vector must be not null");
        }

        double[] result;

        if (vector1.components.length > vector2.components.length) {
            result = new double[vector1.components.length];
        } else {
            result = new double[vector2.components.length];
        }

        for (int i = 0; i < result.length; i++) {
            if (i >= vector1.components.length) {
                result[i] = vector2.components[i];
            } else if (i >= vector2.components.length) {
                result[i] = vector1.components[i];
            } else {
                result[i] = vector1.components[i] + vector2.components[i];
            }

        }

        return new Vector(result);
    }

    public static Vector subtract(Vector vector1, Vector vector2) throws NullPointerException {
        if (vector1 == null || vector2 == null) {
            throw new  NullPointerException("Each vector must be not null");
        }

        double[] result;

        if (vector1.components.length > vector2.components.length) {
            result = new double[vector1.components.length];
        } else {
            result = new double[vector2.components.length];
        }

        for (int i = 0; i < result.length; i++) {
            if (i >= vector1.components.length) {
                result[i] = -vector2.components[i];
            } else if (i >= vector2.components.length) {
                result[i] = vector1.components[i];
            } else {
                result[i] = vector1.components[i] - vector2.components[i];
            }

        }

        return new Vector(result);
    }

    public static double scalarProduct(Vector vector1, Vector vector2) throws NullPointerException {
        if (vector1 == null || vector2 == null) {
            throw new  NullPointerException("Each vector must be not null");
        }

        int maxSize;
        if (vector1.components.length > vector2.components.length) {
            maxSize = vector1.components.length;
        } else {
            maxSize = vector2.components.length;
        }

        double sumOfMultipliedPairs = 0;
        for (int i = 0; i < maxSize; i++) {
            if (i >= vector1.components.length || i >= vector2.components.length) {
                break;
            } else {
                sumOfMultipliedPairs += vector1.components[i] * vector2.components[i];
            }
        }

        return sumOfMultipliedPairs;
    }
}
