package com.newey.exercises;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * A simple unit test
 */
public class MatrixExerciseTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldTestMatrix() {
        MatrixExercise matrixExercise = new MatrixExercise(7, 7, 3);
        matrixExercise.generateMatrix();
        assertEquals(3, countZeroes(matrixExercise.getMatrix()));
        matrixExercise.zeroRowsAndColumns();
        assertTrue(countZeroes(matrixExercise.getMatrix()) > 3);
    }

    private int countZeroes(String[][] matrix) {
        int count = 0;
        for (String[] matrixRow : matrix) {
            for (String s : matrixRow) {
                if ("0".equals(s)) {
                    count++;
                }
            }
        }
        return count;
    }
}
