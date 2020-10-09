package algorithms.optimization.simplex;

import algorithms.optimization.simplex.impl.TableauImpl;
import java.util.ArrayList;
import java.util.List;

/**
 * @author thepn
 *
 * Builder for a Simplex-Tableau.
 *
 * @see TableauImpl
 * @see Tableau
 */
public class TableauBuilder {

  private final List<Double> obj = new ArrayList<>();
  private final List<Double> cons = new ArrayList<>();
  private final List<List<Double>> rows = new ArrayList<>();
  private final List<Integer> basis = new ArrayList<>();

  /**
   * Adds object function coefficient (array).
   * Could be 4 * x1 + 12 * x2 + 3 * x3 = [...]
   * Array would be [4, 12, 3]
   * @param obj array of coefficients
   */
  public TableauBuilder addObjectFunctionCoefficients(double... obj){
    assert obj.length != 0;

    for (double v : obj) {
      this.obj.add(v);
    }

    return this;
  }

  /**
   * adds a new constraint in format of
   * c0 * x1 + c1 * x2 + c2 * x3 = result
   * @param result - constraint result
   * @param coefficients - constraint coefficients
   */
  public TableauBuilder addConstraint(double result, double... coefficients){
    assert coefficients.length != 0;

    final List<Double> row = new ArrayList<>();

    for (double coefficient : coefficients) {
      row.add(coefficient);
    }

    cons.add(result);
    rows.add(row);

    return this;
  }

  /**
   * Builds up the tableau using the given values in our arrays. Also the missing basis columns will
   * be added to create a fitting start solution.
   */
  private void buildTableau() {
    final int rowsAmount = rows.size();
    // build tableau
    for (int i = 0; i < rowsAmount; i++) {
      obj.add(0D);

      final List<Double> ident = new ArrayList<>();

      for (int j = 0; j < rows.size(); j++) {
        ident.add(0D);
      }

      ident.set(i, 1D);
      basis.add(rowsAmount + i);
      ident.add(cons.get(i));

      for (int k = 0; k < rows.size(); k++) {
        rows.get(k).add(ident.get(k));
      }
    }
  }

  /**
   * Converts row list to row array
   * @param rowList
   * @return rows as double array
   */
  private double[][] toRowArray(List<List<Double>> rowList){
    final double[][] rows = new double[rowList.size()][rowList.get(0).size()];

    for (int i = 0; i < rowList.size(); i++) {
      final List<Double> list = rowList.get(i);
      final double[] row = new double[list.size()];

      for (int j = 0; j < list.size(); j++) {
        row[j] = list.get(j);
      }

      rows[i] = row;
    }

    return rows;
  }

  private double[] toArray(List<Double> cons){
    final double[] consArray = new double[cons.size()];

    for (int i = 0; i < cons.size(); i++) {
      consArray[i] = cons.get(i);
    }

    return consArray;
  }

  private int[] toBasisArray(List<Integer> basis){
    final int[] basisArray = new int[basis.size()];

    for (int i = 0; i < basis.size(); i++) {
      basisArray[i] = basis.get(i);
    }

    return basisArray;
  }

  /**
   * Initializes a Simplex-Tableau
   * @return tableau
   */
  public Tableau build(){
    this.assertPreconditions();
    this.buildTableau();

    final double[][] rows = toRowArray(this.rows);
    double[] cons = toArray(this.cons);
    int[] basis = toBasisArray(this.basis);
    double[] obj = toArray(this.obj);

    return new TableauImpl(obj, rows, cons, basis);
  }

  private void assertPreconditions(){
    assert rows.size() > 0;
    assert obj.size() > 0;

    final int objSize = obj.size();

    for (List<Double> row : rows) {
      assert row.size() > 0;

      assert row.size() == objSize;
    }

    assert cons.size() == rows.size();
  }
}
