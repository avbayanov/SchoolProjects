package me.bayanov.matrix;

import me.bayanov.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Rows count must be > 0");
        }
        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Columns count must be > 0");
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Matrix must be not null");
        }

        rows = new Vector[matrix.getRowsCount()];

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            rows[i] = new Vector(matrix.getRow(i));
        }
    }

    public Matrix(double[][] components) {
        this(components.length, components[0].length);

        for (int i = 0; i < components.length; i++) {
            if (components[i].length > components[0].length) {
                throw new IllegalArgumentException("Row size must be between 0 and first row size");
            }

            if (components[i].length != 0) {
                setRow(i, new Vector(components[i]));
            }
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Vectors size must be > 0");
        }

        rows = Arrays.copyOf(vectors, vectors.length);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{ ");
        for (Vector row : rows) {
            sb.append(row);
            sb.append(", ");
        }
        sb.deleteCharAt(sb.length() - 2);
        sb.append("}");

        return sb.toString();
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Index must be between 0 and (rows count - 1)");
        }
        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector row) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Index must be between 0 and (rows count - 1)");
        }
        if (row.getSize() > rows[0].getSize()) {
            throw new IllegalArgumentException("Row size must be between 1 and (columns count - 1)");
        }

        if (row.getSize() < rows[0].getSize()) {
            rows[index] = new Vector(rows[0].getSize());

            for (int i = 0; i < row.getSize(); i++) {
                rows[index].setComponent(i, row.getComponent(i));
            }
        } else {
            rows[index] = new Vector(row);
        }
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= rows[0].getSize()) {
            throw new IndexOutOfBoundsException("Index must be between 0 and (columns count - 1)");
        }

        Vector column = new Vector(rows.length);
        for (int i = 0; i < rows.length; i++) {
            column.setComponent(i, rows[i].getComponent(index));
        }

        return column;
    }

    public void transpose() {
        Vector[] transposedRows = new Vector[rows[0].getSize()];

        for (int i = 0; i < rows[0].getSize(); i++) {
            transposedRows[i] = new Vector(rows.length);
            for (int j = 0; j < rows.length; j++) {
                transposedRows[i].setComponent(j, rows[j].getComponent(i));
            }
        }

        rows = transposedRows;
    }

    public void multiplyOnScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyOnScalar(scalar);
        }
    }

    private Matrix getSubmatrixForMinor(int index) {
        Matrix submatrix = new Matrix(rows.length - 1, rows.length - 1);

        for (int iOld = 1, iNew = 0; iOld < rows.length; ++iOld) {
            for (int jOld = 0, jNew = 0; jOld < rows.length; ++jOld) {
                if (jOld != index) {
                    submatrix.rows[iNew].setComponent(jNew, rows[iOld].getComponent(jOld));
                    ++jNew;
                }
            }
            ++iNew;
        }

        return submatrix;
    }

    public double getDeterminant() {
        if (rows.length != rows[0].getSize()) {
            throw new ArithmeticException("Matrix must be square");
        }

        if (rows.length == 1) {
            return rows[0].getComponent(0);
        }

        if (rows.length == 2) {
            return rows[0].getComponent(0) * rows[1].getComponent(1)
                    - rows[0].getComponent(1) * rows[1].getComponent(0);
        }

        Matrix submatrix = getSubmatrixForMinor(0);
        double determinant = rows[0].getComponent(0) * submatrix.getDeterminant();
        for (int i = 1; i < rows.length; i++) {
            submatrix = getSubmatrixForMinor(i);
            determinant += Math.pow(-1, i) * rows[0].getComponent(i)
                    * submatrix.getDeterminant();
        }

        return determinant;
    }

    public void multiplyOnVectorMatrix(Matrix vector) {
        if (vector == null) {
            throw new NullPointerException("Vector Matrix must be not null");
        }

        if (vector.getRowsCount() == 1) {
            if (rows.length != vector.getColumnsCount()) {
                throw new ArithmeticException("Rows count in original matrix must be equal to columns count vector matrix");
            }

            Vector[] result = new Vector[rows.length];
            for (int i = 0; i < rows.length; i++) {
                result[i] = new Vector(rows.length);

                for (int j = 0; j < rows.length; j++) {
                    result[i].setComponent(j, rows[i].getComponent(0) * vector.rows[0].getComponent(j));
                }
            }

            rows = result;
        } else if (vector.getColumnsCount() == 1) {
            if (rows[0].getSize() != vector.getRowsCount()) {
                throw new ArithmeticException("Columns count in original matrix must be equal to rows count vector matrix");
            }

            Vector[] result = new Vector[rows.length];
            for (int i = 0; i < rows.length; i++) {
                result[i] = new Vector(1);

                for (int j = 0; j < vector.rows.length; j++) {
                    result[i].setComponent(0,
                            result[i].getComponent(0) + rows[i].getComponent(j) * vector.rows[j].getComponent(0));
                }
            }

            rows = result;
        } else {
            throw new ArithmeticException("Vector Matrix must be vector (row count == 1 OR column count == 1)");
        }
    }

    public void add(Matrix matrix) {
        if (matrix.getRowsCount() != rows.length) {
            throw new ArithmeticException("Rows count must be equal");
        }
        if (matrix.getColumnsCount() != rows[0].getSize()) {
            throw new ArithmeticException("Columns count must be equal");
        }

        for (int i = 0; i < rows.length; i++) {
            Vector currentRowInMatrix = matrix.getRow(i);

            for (int j = 0; j < rows[0].getSize(); j++) {
                rows[i].setComponent(j, rows[i].getComponent(j) + currentRowInMatrix.getComponent(j));
            }
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix.getRowsCount() != rows.length) {
            throw new ArithmeticException("Rows count must be equal");
        }
        if (matrix.getColumnsCount() != rows[0].getSize()) {
            throw new ArithmeticException("Columns count must be equal");
        }

        for (int i = 0; i < rows.length; i++) {
            Vector currentRowInMatrix = matrix.getRow(i);

            for (int j = 0; j < rows[0].getSize(); j++) {
                rows[i].setComponent(j, rows[i].getComponent(j) - currentRowInMatrix.getComponent(j));
            }
        }
    }

    public static Matrix add(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new NullPointerException("First Matrix must be not null");
        }
        if (matrix2 == null) {
            throw new NullPointerException("Second Matrix must be not null");
        }

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);

        return result;
    }

    public static Matrix subtract(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new NullPointerException("First Matrix must be not null");
        }
        if (matrix2 == null) {
            throw new NullPointerException("Second Matrix must be not null");
        }

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);

        return result;
    }

    public static Matrix multiplyMatrixOnMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new NullPointerException("First Matrix must be not null");
        }
        if (matrix2 == null) {
            throw new NullPointerException("Second Matrix must be not null");
        }

        if (matrix1.getRowsCount() != matrix2.getColumnsCount()) {
            throw new ArithmeticException("Rows count of first matrix must be equal to columns count of second matrix");
        }

        double[][] result = new double[Math.max(matrix1.getRowsCount(), matrix2.getRowsCount())]
                [Math.max(matrix1.getColumnsCount(), matrix2.getColumnsCount())];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                for (int k = 0; k < matrix1.getColumnsCount(); k++) {
                    result[i][j] += matrix1.rows[i].getComponent(k) * matrix2.rows[k].getComponent(j);
                }
            }
        }

        return new Matrix(result);
    }
}