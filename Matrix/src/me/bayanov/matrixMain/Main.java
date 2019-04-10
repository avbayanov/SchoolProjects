package me.bayanov.matrixMain;

import me.bayanov.matrix.Matrix;
import me.bayanov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[][] square = {{57, 46, 18}, {0, 3, 2, 1}, {25, 16, 44, 37}, {52, 6, 75, 8}};
        Matrix matrix1 = new Matrix(square);

        Matrix matrix2 = new Matrix(matrix1);
        matrix2.transpose();
        System.out.println("Transposed " + matrix1 + " is " + matrix2);

        System.out.println("Determinant of " + matrix1 + " is " + matrix1.getDeterminant());

        Vector[] vectors = new Vector[]{
                new Vector(new double[]{1, 2, 3, 4}),
                new Vector(new double[]{4, 5, 6, 7}),
                new Vector(new double[]{8, 9, 1}),
                new Vector(new double[]{1, 2, 3, 4})};
        Matrix matrix3 = new Matrix(vectors);

        System.out.println(matrix3 + " + " + matrix1 + " = " + Matrix.add(matrix3, matrix1));
        System.out.println(matrix3 + " - " + matrix1 + " = " + Matrix.subtract(matrix3, matrix1));

        Matrix m1 = new Matrix(new double[][]{{-1, 1}, {2, 0}, {0, 3}});
        Matrix m2 = new Matrix(new double[][]{{3, 1, 2}, {0, -1, 4}});
        System.out.println(m1 + " * " + m2 + " = " + Matrix.multiplyMatrixOnMatrix(m1, m2));

        Matrix vectorColumn = new Matrix(new double[][]{{1}, {2}, {3}});

        System.out.println(m2 + " * " + vectorColumn + " = " + m2.multiplyOnVectorColumn(vectorColumn));
    }
}
