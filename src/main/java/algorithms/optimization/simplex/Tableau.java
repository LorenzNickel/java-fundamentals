package algorithms.optimization.simplex;

import javafx.util.Pair;

public interface Tableau {

  /**
   * @return the calculated object value of the tableau
   */
  double computeObjectValue();

  /**
   * @return index of the maximum delta value or -1 if all delta values are not positive
   */
  int isOptimal();

  /**
   * pivotizes the tableau at row, col index
   *
   * @param row
   * @param col
   */
  void pivot(int row, int col);

  /**
   * @return true if at least one b value is positive
   */
  boolean isFeasible();

  /**
   * finds new basis and calculates new delta values
   */
  void calculateDelta();

  /**
   * Locates the indices of the new basis and returns their values as array
   *
   * @return new base as double array
   */
  double[] findBasis();

  /**
   * @param colIndex
   * @return bottlenecks as pair-list: [Index, Bottleneck Value]
   */
  Pair<Integer, Double> calculateBottleneck(int colIndex);

  /**
   * solves the simplex tableau
   */
  double solve();

  void display();

  void printObj();

  void printDelta();

  void printRows();

}
