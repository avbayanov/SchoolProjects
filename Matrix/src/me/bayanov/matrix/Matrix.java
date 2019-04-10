package me.bayanov.matrix;

import me.bayanov.vector.Vector;

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
        if (components.length == 0) {
            throw new IllegalArgumentException("Rows count must be > 0");
        }

        rows = new Vector[components.length];

        int maxRowLength = components[0].length;
        for (double[] row : components) {
            if (row.length > maxRowLength) {
                maxRowLength = row.length;
            }
        }

        if (maxRowLength == 0) {
            throw new IllegalArgumentException("Columns count must be > 0");
        }

        for (int i = 0; i < components.length; i++) {
            rows[i] = new Vector(maxRowLength, components[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Vectors size must be > 0");
        }

        rows = new Vector[vectors.length];

        int maxRowLength = vectors[0].getSize();
        for (Vector row : vectors) {
            if (row.getSize() > maxRowLength) {
                maxRowLength = row.getSize();
            }
        }

        for (int i = 0; i < vectors.length; i++) {
            double[] row = new double[vectors[i].getSize()];
            for (int j = 0; j < vectors[i].getSize(); j++) {
                row[j] = vectors[i].getComponent(j);
            }

            rows[i] = new Vector(maxRowLength, row);
        }
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
        if (index < 0 || index >= getRowsCount()) {
            throw new IndexOutOfBoundsException("Index must be between 0 and (rows count - 1)");
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector row) {
        if (index < 0 || index >= getRowsCount()) {
            throw new IndexOutOfBoundsException("Index must be between 0 and (rows count - 1)");
        }

        if (row.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Row size must be equal");
        }

        rows[index] = new Vector(row);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Index must be between 0 and (columns count - 1)");
        }

        Vector column = new Vector(getRowsCount());
        for (int i = 0; i < getRowsCount(); i++) {
            column.setComponent(i, rows[i].getComponent(index));
        }

        return column;
    }

    public void transpose() {
        Vector[] transposedRows = new Vector[getColumnsCount()];

        for (int i = 0; i < getColumnsCount(); i++) {
            transposedRows[i] = getColumn(i);
        }

        rows = transposedRows;
    }

    public void multiplyOnScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyOnScalar(scalar);
        }
    }

    private Matrix getSubmatrixForMinor(int index) {
        Matrix submatrix = new Matrix(getRowsCount() - 1, getRowsCount() - 1);

        for (int iOld = 1, iNew = 0; iOld < getRowsCount(); ++iOld) {
            for (int jOld = 0, jNew = 0; jOld < getRowsCount(); ++jOld) {
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
        if (getRowsCount() != getColumnsCount()) {
            throw new ArithmeticException("Matrix must be square");
        }

        if (getRowsCount() == 1) {
            return rows[0].getComponent(0);
        }

        if (getRowsCount() == 2) {
            return rows[0].getComponent(0) * rows[1].getComponent(1)
                    - rows[0].getComponent(1) * rows[1].getComponent(0);
        }

        Matrix submatrix = getSubmatrixForMinor(0);
        double determinant = rows[0].getComponent(0) * submatrix.getDeterminant();
        for (int i = 1; i < getRowsCount(); i++) {
            submatrix = getSubmatrixForMinor(i);
            determinant += Math.pow(-1, i) * rows[0].getComponent(i)
                    * submatrix.getDeterminant();
        }

        return determinant;
    }

    public Matrix multiplyOnVectorColumn(Matrix vector) {
        if (vector == null) {
            throw new NullPointerException("Vector Matrix must be not null");
        }

        if (vector.getColumnsCount() != 1) {
            throw new IllegalArgumentException("Vector must be vector-column");
        }
        if (getColumnsCount() != vector.getRowsCount()) {
            throw new ArithmeticException("Columns count in original matrix must be equal to rows count vector matrix");
        }

        double[][] result = new double[getRowsCount()][1];
        for (int i = 0; i < getRowsCount(); i++) {
            for (int j = 0; j < vector.getRowsCount(); j++) {
                result[i][0] += rows[i].getComponent(j) * vector.rows[j].getComponent(0);
            }
        }

        return new Matrix(result);
    }

    public void add(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Matrix must be not null");
        }

        if (matrix.getRowsCount() != getRowsCount()) {
            throw new ArithmeticException("Rows count must be equal");
        }
        if (matrix.getColumnsCount() != getColumnsCount()) {
            throw new ArithmeticException("Columns count must be equal");
        }

        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].add(matrix.getRow(i));
        }
    }

    public void subtract(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Matrix must be not null");
        }

        if (matrix.getRowsCount() != getRowsCount()) {
            throw new ArithmeticException("Rows count must be equal");
        }
        if (matrix.getColumnsCount() != getColumnsCount()) {
            throw new ArithmeticException("Columns count must be equal");
        }

        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].subtract(matrix.getRow(i));
        }
    }

    public static Matrix add(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new NullPointerException("First Matrix must be not null");
        }
        if (matrix2 == null) {
            throw new NullPointerException("Second Matrix must be not null");
        }

        if (matrix1.getRowsCount() != matrix2.getRowsCount()) {
            throw new ArithmeticException("Rows count must be equal");
        }
        if (matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new ArithmeticException("Columns count must be equal");
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

        if (matrix1.getRowsCount() != matrix2.getRowsCount()) {
            throw new ArithmeticException("Rows count must be equal");
        }
        if (matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new ArithmeticException("Columns count must be equal");
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