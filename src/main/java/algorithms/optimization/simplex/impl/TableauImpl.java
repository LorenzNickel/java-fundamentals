package algorithms.optimization.simplex.impl;

import algorithms.optimization.simplex.Tableau;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javafx.util.Pair;

/**
 * Basic implementation of the simplex algorithm. For more information have a look at
 * <a href="https://en.wikipedia.org/wiki/Simplex_algorithm">Wikipedia</a>
 * <p>
 * This algorithm solves linear optimization problems.
 * </p>
 * <p>
 * When you are not able to find a fitting start solution you need to use the 2-phase method which
 * is not implemented yet.
 * </p>
 * @author thepn
 */
public class TableauImpl implements Tableau {

  private final double[] constraintResults; // b - vector
  private final int rowsAmount;

  private double[] objectFunctionCoefficients;
  private double[][] rows; // constraint coefficients
  private int[] basis; // indices of the columns which are currently the base columns
  private double[] delta; // delta values

  public TableauImpl(double[] objectFunctionCoefficients, double[][] rows,
      double[] constraintResults, int[] basis) {
    this.objectFunctionCoefficients = objectFunctionCoefficients;
    this.delta = new double[rows.length];
    this.rows = rows;
    this.constraintResults = constraintResults;
    this.basis = basis;

    rowsAmount = objectFunctionCoefficients.length;
  }

  /**
   * @return the calculated object value of the tableau
   */
  public double computeObjectValue() {
    double result = 0;

    for (int i = 0; i < basis.length; i++) {
      // calculate object function result
      // basis array holds the values for function input
      result += objectFunctionCoefficients[basis[i]] * constraintResults[i];
    }

    return result;
  }

  /**
   * @return index of the maximum delta value or -1 if all delta values are not positive
   */
  public int isOptimal() {
    double max = 0L;
    int maxIndex = -1;

    for (int i = 0; i < delta.length; i++) {
      if (delta[i] > max) {
        max = delta[i];
        maxIndex = i;
      }
    }

    return maxIndex;
  }

  /**
   * pivotizes the tableau at row, col index
   *
   * @param row
   * @param col
   */
  public void pivot(int row, int col) {
    double e = rows[row][col];
    System.out.println("Pivotize at (" + row + ", " + col + ") = " + e);

    for (int i = 0; i < rows[row].length; i++) {
      rows[row][i] /= e;
    }

    constraintResults[row] /= e;

    for (int i = 0; i < rows.length; i++) {
      if (i == row) {
        continue;
      }

      double d = rows[i][col];

      for (int j = 0; j < rows[i].length; j++) {
        rows[i][j] = rows[i][j] - d * rows[row][j];
      }

      constraintResults[i] = constraintResults[i] - d * constraintResults[row];
    }
  }

  /**
   * @return true if at least one b value is positive
   */
  public boolean isFeasible() {
    for (int i = 0; i < constraintResults.length; i++) {
      if (constraintResults[i] >= 0) {
        return true;
      }
    }

    return false;
  }

  /**
   * finds new basis and calculates new delta values
   */
  public void calculateDelta() {
    double[] basis = findBasis();
    List<Double> deltaValues = new ArrayList<>();

    for (int colIndex = 0; colIndex < rows[0].length; colIndex++) {
      double delta = 0;

      for (int rowIndex = 0; rowIndex < rows.length; rowIndex++) {
        delta += basis[rowIndex] *
            rows[rowIndex][colIndex];
      }

      delta -= objectFunctionCoefficients[colIndex];
      deltaValues.add(delta);
    }

    this.delta = new double[deltaValues.size()];

    for (int i = 0; i < deltaValues.size(); i++) {
      this.delta[i] = deltaValues.get(i);
    }
  }

  /**
   * Locates the indices of the new basis and returns their values as array
   *
   * @return new base as double array
   */
  public double[] findBasis() {
    List<Pair<Integer, Double>> list = new ArrayList<>();

    for (int colIndex = 0; colIndex < rows[0].length; colIndex++) {
      int oneIndex = -1;
      int zeroes = 0;

      for (int rowIndex = 0; rowIndex < rows.length; rowIndex++) {
        if (rows[rowIndex][colIndex] == 0) {
          zeroes++;
        }

        if (rows[rowIndex][colIndex] == 1) {
          oneIndex = rowIndex;
        }
      }

      if (oneIndex != -1 && zeroes == rows.length - 1) {
        list.add(new Pair<>(oneIndex, objectFunctionCoefficients[colIndex]));
      }
    }

    list.sort(Comparator.comparingInt(Pair::getKey));
    double[] base = new double[list.size()];

    for (int i = 0; i < list.size(); i++) {
      base[i] = list.get(i).getValue();
    }

    return base;
  }

  /**
   * @param colIndex
   * @return bottlenecks as pair-list: [Index, Bottleneck Value]
   */
  public Pair<Integer, Double> calculateBottleneck(int colIndex) {
    double[] bottleNecks = new double[rows.length];
    Arrays.fill(bottleNecks, Long.MAX_VALUE);

    for (int i = 0; i < rows.length; i++) {
      if (rows[i][colIndex] > 0) {
        bottleNecks[i] = constraintResults[i] / rows[i][colIndex];
      }
    }

    return minimize(bottleNecks);
  }

  public double solve() {
    // fill delta values with 0
    Arrays.fill(delta, 0);
    int iteration = 0;

    while (true) {
      System.out.println("Iteration: #" + ++iteration);
      display();

      if (!isFeasible()) {
        System.err.println("Not solvable. At least one b < 0 is needed to solve!");
        this.display();
        throw new IllegalArgumentException("At least one b value (constraint result) must be > 0!");
      }

      calculateDelta();

      int k = isOptimal();

      if (k == -1) {
        display();
        System.out.println("Optimum found!");
        return computeObjectValue();
      }

      final Pair<Integer, Double> bottleneck = calculateBottleneck(k);

      if (bottleneck.getValue() < Long.MAX_VALUE) {
        display();

        System.out.println("x_" + (rowsAmount + bottleneck.getKey() + 1) + " leaves the basis.");
        System.out.println("x_" + (k + 1) + " gets into basis");

        pivot(bottleneck.getKey(), k);
        this.basis[bottleneck.getKey()] = k;
      } else {
        System.err.println("not solvable: No bottlenecks were found!");
        this.display();
        throw new IllegalStateException("Could not solve problem: No bottlenecks were found!");
      }
    }
  }

  /**
   * Builds up the tableau using the given values in our arrays. Also the missing basis columns will
   * be added to create a fitting start solution.
   *
   * @param obj
   * @param basis
   * @param rows
   */
  void buildTableau(List<Double> obj, List<Integer> basis, List<List<Double>> rows) {
    for (int i = 0; i < this.rows.length; i++) {
      rows.add(new ArrayList<>());
      for (int j = 0; j < this.rows[i].length; j++) {
        rows.get(i).add(this.rows[i][j]);
      }
    }

    // fill up obj values
    for (double v : this.objectFunctionCoefficients) {
      obj.add(v);
    }

    // build tableau
    for (int i = 0; i < rowsAmount; i++) {
      obj.add(0D);

      final List<Double> ident = new ArrayList<>();

      for (int j = 0; j < rows.size(); j++) {
        ident.add(0D);
      }

      ident.set(i, 1D);
      basis.add(rowsAmount + i);
      ident.add(constraintResults[i]);

      for (int k = 0; k < rows.size(); k++) {
        rows.get(k).add(ident.get(k));
      }
    }
  }

  /**
   * copies list values into array fields
   *
   * @param obj
   * @param basis
   * @param rows
   */
  void initializeTableau(List<Double> obj, List<Integer> basis, List<List<Double>> rows) {
    // copy rows into array
    this.rows = new double[rows.size()][rows.get(0).size()];

    for (int i = 0; i < this.rows.length; i++) {
      for (int j = 0; j < this.rows[i].length; j++) {
        this.rows[i][j] = rows.get(i).get(j);
      }
    }

    // copy basis into array
    this.basis = new int[basis.size()];

    for (int i = 0; i < basis.size(); i++) {
      this.basis[i] = basis.get(i);
    }

    // copy object coefficients into array
    this.objectFunctionCoefficients = new double[obj.size()];

    for (int i = 0; i < obj.size(); i++) {
      this.objectFunctionCoefficients[i] = obj.get(i);
    }
  }

  public void display() {
    System.out.println("-------------------------------------");
    printObj();
    System.out.println("-------------------------------------");
    printRows();
    System.out.println("-------------------------------------");
    printDelta();
    System.out.println("Object value " + computeObjectValue());
  }

  public void printObj() {
    final StringBuilder key = new StringBuilder("");
    final StringBuilder builder = new StringBuilder("");

    for (int i = 0; i < objectFunctionCoefficients.length; i++) {
      key.append(" x_").append(i + 1);
      builder.append(" ").append(objectFunctionCoefficients[i]);
    }

    System.out.println(key.toString().substring(1));
    System.out.println(builder.toString().substring(1));
  }

  public void printDelta() {
    final StringBuilder builder = new StringBuilder("");

    for (int i = 0; i < delta.length; i++) {
      builder.append(" ").append(delta[i]);
    }

    System.out.println(builder.toString().substring(1));
  }

  public void printRows() {
    for (int i = 0; i < rows.length; i++) {
      final StringBuilder builder = new StringBuilder("");

      for (int j = 0; j < rows[i].length; j++) {
        builder.append(" ").append(rows[i][j]);
      }

      builder.append(" | ").append(constraintResults[i]);

      System.out.println(builder.toString().substring(1));
    }
  }

  @Override
  public String toString() {
    return "Tableau{" +
        "rows=" + Arrays.toString(rows) +
        ", cons=" + Arrays.toString(constraintResults) +
        ", delta=" + Arrays.toString(delta) +
        ", basis=" + Arrays.toString(basis) +
        ", obj=" + Arrays.toString(objectFunctionCoefficients) +
        '}';
  }

  /**
   * @param arr
   * @return pair of min value of the array and its index as the pair's key
   */
  private Pair<Integer, Double> minimize(double[] arr) {
    int index = 0;
    double value = Long.MAX_VALUE;

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] < value) {
        value = arr[i];
        index = i;
      }
    }

    return new Pair<>(index, value);
  }
}
