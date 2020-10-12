package algorithms.search.astar.visualization;

import algorithms.search.astar.model.Node;
import java.io.IOException;

/**
 * This class is for the visualization of the Grid
 *
 * @author serturx
 */
public class cmdLineVisualization {

  /**
   * This method clears the cmd Interface and prints the grid
   *
   * @param grid Grid in form of a 2D Node Array
   */
  public static void showGrid(Node[][] grid) {

    clrscrn();

    System.out.print("\n");

    // Column indeces
    for (int i = 0; i < grid.length; i++) {
      if (i == 0) {
        System.out.print("      00   ");
      } else {
        if (i > 9) {
          System.out.printf("%d   ", i);
        } else {
          System.out.printf("0%d   ", i);
        }
      }
    }

    System.out.print("\n");

    for (int i = 0; i < grid.length * 5 + 5; i++) {
      System.out.print("-");
    }

    System.out.print("\n");

    // Node prints
    for (int i = 0; i < grid.length; i++) {

      System.out.printf(" %s |", i < 10 ? "0" + i : i);

      for (int j = 0; j < grid[0].length; j++) {

        String status = grid[i][j].getStatus();
        if (status.length() > 1) {
          status = status.substring(0, 2);
        }

        System.out.printf(" %s %s", status, " ");
      }

      System.out.print("\n");
    }

    System.out.print("\n");

    for (int i = 0; i < grid.length * 5 + 5; i++) {
      System.out.print("-");
    }
  }

  /** Clears the screen (atleast on windows) */
  public static void clrscrn() {
    try {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } catch (InterruptedException | IOException e) {
      e.printStackTrace();
    }
  }
}
