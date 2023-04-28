package com.newey.exercises;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
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
    }

    private void initializeMatrix() {
        matrix = new String[rows][columns];
        // Populate the matrix with 'X'.
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                matrix[i][j] = "X";
            }
        }
    }

    public void printMatrix() {
        System.out.println("****** Generated Matrix ******");
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
            matrixExercise.printMatrix();
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
}
