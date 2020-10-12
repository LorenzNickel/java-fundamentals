package algorithms.search;

import algorithms.search.astar.AStar;
import algorithms.search.astar.model.Vector2;

public class AStarTest {

  public static void test() {
    AStar astar = new AStar(30, new Vector2(0, 0), new Vector2(26, 20));
    astar.setRandomBlocks(200);
    try {
      astar.calcPath();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.printf("\nTotal path cost: %f", astar.getTotalCost());
  }
}
