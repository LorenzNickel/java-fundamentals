package algorithms.optimization.simplex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javafx.util.Pair;

/**
 * @author thepn
 */
public class Tableau {

  private double[][] rows;
  private final double[] cons;
  private final int rowsAmount;

  private double[] obj;
  private int[] basis;
  private double[] delta;

  public Tableau(double[] obj, double[][] rows, double[] cons) {
    this.obj = obj;
    this.delta = new double[rows.length];
    this.rows = rows;
    this.cons = cons;

    rowsAmount = obj.length;
  }

  /**
   * @return the calculated object value of the tableau
   */
  public double computeObjectValue() {
    int lastPos = delta.length - 1;
    double result = 0;

    for (int i = 0; i < basis.length; i++) {
      // calculate object function result
      // basis array holds the values for function input
      result += obj[basis[i]] * rows[i][lastPos];
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

    cons[row] /= e;

    for (int i = 0; i < rows.length; i++) {
      if (i == row) {
        continue;
      }

      double d = rows[i][col];

      for (int j = 0; j < rows[i].length; j++) {
        rows[i][j] = rows[i][j] - d * rows[row][j];
      }

      cons[i] = cons[i] - d * cons[row];
    }
  }

  /**
   * @return true if all b values are negative? idk
   */
  public boolean isFeasible() {
    for (int i = 0; i < cons.length; i++) {
      if (cons[i] >= 0) {
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

      delta -= obj[colIndex];
      deltaValues.add(delta);
    }

    this.delta = new double[deltaValues.size()];

    for (int i = 0; i < deltaValues.size(); i++) {
      this.delta[i] = deltaValues.get(i);
    }
  }

  /**
   * Locates the indices of the new basis and returns their values as array
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
        list.add(new Pair<>(oneIndex, obj[colIndex]));
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
        bottleNecks[i] = cons[i] / rows[i][colIndex];
      }
    }

    return minimize(bottleNecks);
  }

  public void solve() {
    List<Double> obj = new ArrayList<Double>();
    List<Integer> basis = new ArrayList<>();

    List<List<Double>> rows = new ArrayList<>();

    for (int i = 0; i < this.rows.length; i++) {
      rows.add(new ArrayList<>());
      for (int j = 0; j < this.rows[i].length; j++) {
        rows.get(i).add(this.rows[i][j]);
      }
    }

    // fill up obj values
    for (double v : this.obj) {
      obj.add(v);
    }

    // build tableau
    for (int i = 0; i < rowsAmount; i++) {
      obj.add(0D);

      List<Double> ident = new ArrayList<>();

      for (int j = 0; j < rows.size(); j++) {
        ident.add(0D);
      }

      ident.set(i, 1D);

      basis.add(rowsAmount + i);

      ident.add(cons[i]);

      for (int k = 0; k < rows.size(); k++) {
        rows.get(k).add(ident.get(k));
      }
    }

    System.out.println("basis");
    System.out.println(basis);

    this.rows = new double[rows.size()][rows.get(0).size()];

    for (int i = 0; i < this.rows.length; i++) {
      for (int j = 0; j < this.rows[i].length; j++) {
        this.rows[i][j] = rows.get(i).get(j);
      }
    }

    this.basis = new int[basis.size()];

    for (int i = 0; i < basis.size(); i++) {
      this.basis[i] = basis.get(i);
    }

    this.obj = new double[obj.size()];

    for (int i = 0; i < obj.size(); i++) {
      this.obj[i] = obj.get(i);
    }

    // obj = noch eine 0 am ende?
    Arrays.fill(delta, 0);

    int iteration = 0;

    while (true) {
      System.out.println("Iteration: #" + ++iteration);

      if(iteration >= 5){
        return;
      }

      if (!isFeasible()) {
        System.err.println("Nicht lösbar. Min ein b < 0 ist erforderlich!");
        this.display();
        return;
      }

      calculateDelta();

      int k = isOptimal();

      if (k == -1) {
        display();
        System.out.println("Optimum gefunden!");
        return;
      }

      Pair<Integer, Double> bottleneck = calculateBottleneck(k);

      if (bottleneck.getValue() < Long.MAX_VALUE) {
        display();

        System.out.println("x_" + (rowsAmount + bottleneck.getKey() + 1) + " verlässt die Basis.");
        System.out.println("x_" + (k + 1) + " geht in die Basis.");

        pivot(bottleneck.getKey(), k);
        this.basis[bottleneck.getKey()] = k;
      } else {
        System.err.println("Nicht lösbar!");
        this.display();
        return;
      }
    }
  }

  public void display(){
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

    for (int i = 0; i < obj.length; i++) {
      key.append(" x_").append(i + 1);
      builder.append(" ").append(obj[i]);
    }

    System.out.println(key.toString().substring(1));
    System.out.println(builder.toString().substring(1));
  }

  public void printDelta(){
    final StringBuilder builder = new StringBuilder("");

    for (int i = 0; i < delta.length; i++) {
      builder.append(" ").append(delta[i]);
    }

    System.out.println(builder.toString().substring(1));
  }

  public void printRows(){
    for (int i = 0; i < rows.length; i++) {
      final StringBuilder builder = new StringBuilder("");

      for(int j = 0; j < rows[i].length; j++){
        builder.append(" ").append(rows[i][j]);
      }

      builder.append(" | ").append(cons[i]);

      System.out.println(builder.toString().substring(1));
    }
  }

  public static void main(String[] args) {
    Tableau tableau = new Tableau(new double[]{6, -4, -2}, new double[][]{
        new double[]{3, 0, 3},
        new double[]{1, 1, -1},
        new double[]{3, 2, 1}
    }, new double[]{10, 12, 26});

    tableau.solve();
  }

  @Override
  public String toString() {
    return "Tableau{" +
        "rows=" + Arrays.toString(rows) +
        ", cons=" + Arrays.toString(cons) +
        ", delta=" + Arrays.toString(delta) +
        ", basis=" + Arrays.toString(basis) +
        ", obj=" + Arrays.toString(obj) +
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
