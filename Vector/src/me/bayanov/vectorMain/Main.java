package me.bayanov.vectorMain;

import me.bayanov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector = new Vector(1);
        vector.setComponent(0, 1);

        double[] doubles = {1, 2, 3, 4, 5};
        Vector vector1 = new Vector(doubles);

        Vector vector2 = new Vector(7, doubles);

        System.out.println(vector1 + " + " + vector2 + " = " + Vector.add(vector1, vector2));
        System.out.println(vector1 + " - " + vector2 + " = " + Vector.subtract(vector1, vector2));

        double multiplier = 5;
        System.out.print(vector1 + " * " + multiplier + " = ");
        vector1.scalarMultiple(5);
        System.out.println(vector1);

        Vector vector3 = new Vector(vector2);
        vector3.reverse();
        System.out.println("Reverted " + vector2 + " is " + vector3);

        System.out.println("Length of " + vector1 + " is " + vector1.getLength());

        System.out.println("Scalar product of " + vector1 + " and " + vector2 + " is "
                + Vector.scalarProduct(vector1, vector2));
    }
}
