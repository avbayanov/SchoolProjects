package me.bayanov.shapesMain;

import me.bayanov.shapes.Shape;

import java.util.Comparator;

public class ShapeAreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        return Double.compare(o2.getArea(), o1.getArea());
    }
}
