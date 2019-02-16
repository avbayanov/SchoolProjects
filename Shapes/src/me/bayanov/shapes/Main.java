package me.bayanov.shapes;

import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        Shape square1 = new Square(10);
        Shape square2 = new Square(20);

        Shape rectangle1 = new Rectangle(10, 10);
        Shape rectangle2 = new Rectangle(20, 20);

        Shape triangle1 = new Triangle(10, 20,20,20, 30, 30);
        Shape triangle2 = new Triangle(10, 10 , 10, 30, 30, 30);

        Shape circle1 = new Circle(10);
        Shape circle2 = new Circle(20);

        Shape[] array = {square1, square2, rectangle1, rectangle2, triangle1, triangle2, circle1, circle2};

//        Sort by area
        Arrays.sort(array, new Comparator<Shape>() {
            @Override
            public int compare(Shape o1, Shape o2) {
                return Double.compare(o2.getArea(), o1.getArea());
            }
        });

        System.out.printf("Max area: %f %nIt is %s%n%n",
                array[0].getArea(), array[0]);

//        Sort by perimeter
        Arrays.sort(array, new Comparator<Shape>() {
            @Override
            public int compare(Shape o1, Shape o2) {
                return Double.compare(o2.getPerimeter(), o1.getPerimeter());
            }
        });

        System.out.printf("Second perimeter: %f %nIt is %s",
                array[1].getPerimeter(), array[1]);

    }
}
