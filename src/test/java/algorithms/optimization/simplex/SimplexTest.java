package algorithms.optimization.simplex;

import junit.framework.TestCase;

/**
 * @author thepn
 */
public class SimplexTest extends TestCase {

  public void testSolution(){
    Tableau tableau = new TableauBuilder().addObjectFunctionCoefficients(6, -4, -2)
        .addConstraint(10, 3, 0, 3)
        .addConstraint(12, 1, 1, -1)
        .addConstraint(26, 3, 2, 1)
        .build();

    assertTrue(tableau.isFeasible());

    double result = tableau.solve();

    assertEquals(-52.0, result);
    assertEquals(-1, tableau.isOptimal());
  }

  public void testIllegalStartSolution(){
    Tableau tableau = new TableauBuilder().addObjectFunctionCoefficients(6, -4, -2)
        .addConstraint(-10, 3, 0, 3)
        .addConstraint(-12, 1, 1, -1)
        .addConstraint(-26, 3, 2, 1)
        .build();

    try {
      tableau.solve();
      fail();
    } catch (IllegalArgumentException e) {
    }
  }

}
