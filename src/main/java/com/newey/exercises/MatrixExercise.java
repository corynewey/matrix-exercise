package com.newey.exercises;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class MatrixExercise {

    private static final String DIMENSION_PARM = "-dimensions";
    private static final String ZERO_COUNT_PARM = "-numZeroes";

    private static final int DEFAULT_NUMBER_OF_ZEROES = 2;

    private final int rows;
    private final int columns;
    private final int numZeroes;
    private String[][]  matrix;

    public void generateMatrix() {
        initializeMatrix();
        int requestedZeroes = numZeroes > 0 ? numZeroes : DEFAULT_NUMBER_OF_ZEROES;
        // Generate random indexes where the zeroes will be put.
        Set<Point> zeroPoints = generateRandomPoints(requestedZeroes, columns, rows);
        for (Point zeroPoint : zeroPoints) {
            matrix[zeroPoint.y][zeroPoint.x] = "0";
        }
    }

    public void zeroRowsAndColumns() {
        // Walk the matrix and zero out the row and column of any zero that is seen.
        for (int y=0; y<rows; y++) {
            for (int x=0; x<columns; x++) {
                if ("0".equals(matrix[y][x])) {
                    zeroOutRowColumn(x, y);
                }
            }
        }
    }

    public String[][] getMatrix() {
        return matrix;
    }

    /**
     * Use a set to hold the random points so that we guarantee that we have the number of desired unique points.
     *
     * @param numPoints desired number of points
     * @param maxX      maximum x coordinate value
     * @param maxY      maximum y coordinate value
     * @return  Set of random points objects
     */
    private Set<Point> generateRandomPoints(int numPoints, int maxX, int maxY) {
        Set<Point> points = new HashSet<>();
        while (points.size() < numPoints) {
            points.add(Point.builder().x(getRandomIndex(0, maxX)).y(getRandomIndex(0, maxY)).build());
        }
        return points;
    }

    private int getRandomIndex(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private void initializeMatrix() {
        matrix = new String[rows][columns];
        // Populate the matrix with 'X'.
        for (int i=0; i<rows; i++) {
            Arrays.fill(matrix[i], "X");
        }
    }

    private void zeroOutRowColumn(int x, int y) {
        // ToDo: validate x and y; assure they are valid coordinates.
        // Zero-out column.
        for (String[] row : matrix) {
            row[x] = "0";
        }
        // Zero-out row.
        Arrays.fill(matrix[y], "0");
    }

    public void printMatrix(String header) {
        System.out.println(header);
        for (String[] rows : matrix) {
            System.out.println(String.join(", ", rows));
        }
    }

    public static void main(String[] args) {
        int columns = -1;
        int rows = -1;
        int numZeroes = -1;

        if (args.length > 0) {
            try {
                for (int idx = 0; idx < args.length; idx++) {
                    String arg = args[idx];
                    // We should only ever read either -dimensions or -numZeroes here, else it is a argument error and the user will see the usage message below.
                    if (DIMENSION_PARM.equals(arg)) {
                        rows = Integer.parseInt(args[++idx]);
                        columns = Integer.parseInt(args[++idx]);
                        continue;
                    } else if (ZERO_COUNT_PARM.equals(arg)) {
                        numZeroes = Integer.parseInt(args[++idx]);
                        continue;
                    }
                    break;
                }
            } catch (Exception e) {
                columns = rows = numZeroes = -1;
                // This will catch any exception, like parsing exceptions or array index out-of-bounds exceptions, etc.
                // Don't care what the exception is, just show the user the usage message below.
            }
        }
        if (columns > 0 && rows > 0) {
            MatrixExercise matrixExercise = new MatrixExercise(rows, columns, numZeroes);
            matrixExercise.generateMatrix();
            matrixExercise.printMatrix("****** Initially-Generated Matrix ******");
            matrixExercise.zeroRowsAndColumns();
            matrixExercise.printMatrix("****** Final Matrix ******");
            return;
        }
        printUsage();
    }

    private static void printUsage() {
        System.out.println("""
                                   Usage: java -jar target/matrix-exercise-1.0-SNAPSHOT.jar -dimensions [rows] [cols] -numZeroes [cnt]\s
                                    -- where rows (required) is number of matrix rows
                                    -- where cols (required) is number of matrix columns
                                    -- where cnt (optional) is number of zeroes to seed the matrix with (defaults to two)""");
    }

    @RequiredArgsConstructor
    @Builder
    @Getter
    private static class Point {
        private final int x;
        private final int y;

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point point) {
                return x == point.getX() && y == point.getY();
            }
            return false;
        }
    }
}
