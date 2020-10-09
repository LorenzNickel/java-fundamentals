package datastructures.math;

import java.util.Arrays;
import junit.framework.TestCase;

/**
 * @author Lorenz Nickel
 */
public class MatrixTest extends TestCase {

  private static final int[][] base = new int[][]{{1, 2, 3}, {1, 2, 3}, {1, 2, 3}, {1, 2, 3}};

  public void testDimensions() {
    final Matrix matrix = new Matrix(base);
    assertEquals(matrix.getMatrixDimensions(), new Tuple<>(3, 4));
    matrix.transpose();
    assertEquals(matrix.getMatrixDimensions(), new Tuple<>(4, 3));
  }

  public void testTransposeInversion() {
    final Matrix matrix = new Matrix(base);
    matrix.transpose();
    matrix.transpose();
    assertEquals(matrix, new Matrix(base));
  }

  public void testAdd() {
    final Matrix matrix = new Matrix(base);
    matrix.add(matrix);
    assertEquals(matrix, new Matrix(new int[][]{{2, 4, 6}, {2, 4, 6}, {2, 4, 6}, {2, 4, 6}}));
  }

  public void testMultiply() {
    final Matrix matrixA = new Matrix(base);
    final Matrix matrixB = new Matrix(base);
    matrixA.transpose();
    matrixA.multiply(matrixB);
    assertEquals(matrixA, new Matrix(new int[][]{{4, 8, 12}, {8, 16, 24}, {12, 24, 36}}));

    final Matrix matrixC = new Matrix(base);
    matrixB.multiply(2);
    matrixC.add(matrixC);
    assertEquals(matrixB, matrixC);
    matrixB.multiply(0);
    assertEquals(matrixB, new Matrix(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
  }

  public void testEmptyMatrix() {
    int[][] emptyBase = new int[0][];
    Matrix matrixA = new Matrix(emptyBase);
    Matrix matrixB = new Matrix(emptyBase);
    assertEquals(matrixA, matrixB);
    matrixA.transpose();
    assertEquals(matrixA, matrixB);
    matrixA.add(matrixB);
    assertEquals(matrixA, matrixB);
    matrixA.multiply(matrixB);
    assertEquals(matrixA, matrixB);
    assertEquals(matrixA, matrixA.clone());
    matrixA.multiply(10);
    assertEquals(matrixA, matrixB);
    matrixA.multiply(0);
    assertEquals(matrixA, matrixB);
  }

  public void testClone() {
    final Matrix matrixA = new Matrix(base);
    final Matrix matrixB = matrixA.clone();
    assertEquals(matrixA, matrixB);
    matrixA.add(matrixA);
    assertNotSame(matrixA, matrixB);
    matrixB.add(matrixB);
    assertEquals(matrixA, matrixB);
  }

  public void testArgumentExceptions() {
    final int[][] invalidMatrixArray = new int[][]{{1, 2}, {1}};
    final int[][] validMatrixArray = new int[][]{{1, 2}, {1, 2}, {1, 2}};
    try {
      new Matrix(null);
      fail();
    } catch (IllegalArgumentException ignored) {
    }
    try {
      new Matrix(invalidMatrixArray);
      fail();
    } catch (IllegalArgumentException ignored) {
    }
    final Matrix validMatrix = new Matrix(validMatrixArray);
    assertTrue(Arrays.deepEquals(validMatrix.getAsArray(), validMatrixArray));
    try {
      Matrix.isValidMatrix(null);
      fail();
    } catch (IllegalArgumentException ignored) {
    }
    try {
      validMatrix.getValueAtCoordinates(null);
      fail();
    } catch (IllegalArgumentException ignored) {
    }
    try {
      validMatrix.getValueAtCoordinates(validMatrix.getMatrixDimensions());
      fail();
    } catch (IllegalArgumentException ignored) {
    }
    assertEquals(validMatrix.getValueAtCoordinates(new Tuple<>(1, 1)), 2);
    try {
      validMatrix.add(null);
      fail();
    } catch (IllegalArgumentException ignored) {
    }
    final Matrix transposedMatrix = validMatrix.clone();
    transposedMatrix.transpose();
    try {
      validMatrix.add(transposedMatrix);
      fail();
    } catch (IllegalArgumentException ignored) {
    }
    try {
      validMatrix.add(null);
      fail();
    } catch (IllegalArgumentException ignored) {
    }
    try {
      validMatrix.multiply(validMatrix);
      fail();
    } catch (IllegalArgumentException ignored) {
    }
    try {
      validMatrix.multiply(null);
      fail();
    } catch (IllegalArgumentException ignored) {
    }
  }

  public void testToString() {
    final Matrix matrix = new Matrix(new int[][]{{1, 3}, {3, 4}});
    assertEquals("Matrix{array=[[1, 3], [3, 4]]}", matrix.toString());
  }

  public void testEqualsAndHashCode() {
    final int[][] validMatrixArray = new int[][]{{1, 2}, {1, 2}, {1, 2}};
    final Matrix matrixA = new Matrix(validMatrixArray);
    final Matrix matrixB = matrixA.clone();
    assertEquals(matrixA, matrixB);
    assertEquals(matrixA, matrixA);
    assertNotSame(null, matrixA);
    assertEquals(matrixA.hashCode(), matrixB.hashCode());
  }

}
