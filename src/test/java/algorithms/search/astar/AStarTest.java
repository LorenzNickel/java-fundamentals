package algorithms.search.astar;

import algorithms.search.astar.model.Node;
import algorithms.search.astar.model.Vector2;
import java.util.Arrays;
import junit.framework.TestCase;

public class AStarTest extends TestCase {

  AStar astar1 = new AStar(20, new Vector2(2, 3), new Vector2(7, 9));
  AStar astar2 = new AStar(5, new Vector2(0, 0), new Vector2(4, 4));

  public void testSetBlock() {
    astar1.setBlock(new Vector2(1, 5));
    astar1.setBlock(new Vector2(0, 0));
    try {
      astar1.setBlock(new Vector2(22, 7));
      fail();
    } catch (IllegalArgumentException e) {
    }

    try {
      astar1.setBlock(new Vector2(2, 3));
      fail();
    } catch (IllegalArgumentException e) {
    }

    assertFalse(astar1.getNode(new Vector2(1, 5)).isWalkable());
    assertFalse(astar1.getNode(new Vector2(0, 0)).isWalkable());
    assertTrue(astar1.getNode(new Vector2(2, 4)).isWalkable());
    assertTrue(astar1.getNode(new Vector2(7, 7)).isWalkable());


  }

  public void testSetBlocks() {
    boolean[][] blocks1 = new boolean[13][4];

    try {
      astar1.setBlocks(blocks1);
      fail();
    } catch (IllegalArgumentException e) {
    }
  }

  public void testIsDiagonal() {
    assertFalse(astar1.isDiagonal(astar1.getNode(new Vector2(1, 1)), astar1.getNode(new Vector2(0, 1))));
    assertTrue(astar1.isDiagonal(astar1.getNode(new Vector2(1, 1)), astar1.getNode(new Vector2(0, 0))));
    assertFalse(astar1.isDiagonal(astar1.getNode(new Vector2(1, 1)), astar1.getNode(new Vector2(0, 1))));
    assertFalse(astar1.isDiagonal(astar1.getNode(new Vector2(1, 1)), astar1.getNode(new Vector2(3, 8))));
    assertTrue(astar1.isDiagonal(astar1.getNode(new Vector2(1, 1)), astar1.getNode(new Vector2(2, 2))));
    assertTrue(astar1.isDiagonal(astar1.getNode(new Vector2(1, 1)), astar1.getNode(new Vector2(2, 0))));
    assertFalse(astar1.isDiagonal(astar1.getNode(new Vector2(1, 1)), astar1.getNode(new Vector2(1, 2))));
    assertFalse(astar1.isDiagonal(astar1.getNode(new Vector2(1, 1)), astar1.getNode(new Vector2(5, 5))));
  }

  public void testAddNeighbourNode() {
    Node n1 = new Node(new Vector2(12, 13));
    Node n2 = new Node(new Vector2(17, 10));
    n1.setFCost(56);
    n1.setGCost(45);
    n2.setGCost(0);
    n2.setFCost(0);
    astar1.setNode(n1);
    astar1.setNode(n2);
    astar1.addNeighbourNode(astar1.getNode(12, 13), astar1.getNode(12, 14), 10d);
    astar1.addNeighbourNode(astar1.getNode(12, 13), astar1.getNode(13, 14), 14d);
    astar1.addNeighbourNode(astar1.getNode(12, 13), astar1.getNode(11, 13), 10d);
    astar1.addNeighbourNode(astar1.getNode(17, 10), astar1.getNode(18, 10), 10d);
    astar1.addNeighbourNode(astar1.getNode(17, 10), astar1.getNode(18, 9), 14d);
    astar1.addNeighbourNode(astar1.getNode(17, 10), astar1.getNode(17, 9), 10d);
    assertTrue(astar1.getOpenList().contains(astar1.getNode(12, 14)));
    assertTrue(astar1.getOpenList().contains(astar1.getNode(13, 14)));
    assertTrue(astar1.getOpenList().contains(astar1.getNode(11, 13)));
    assertTrue(astar1.getOpenList().contains(astar1.getNode(18, 10)));
    assertTrue(astar1.getOpenList().contains(astar1.getNode(18, 9)));
    assertTrue(astar1.getOpenList().contains(astar1.getNode(17, 9)));
    assertFalse(astar1.getOpenList().contains(astar1.getNode(17, 8)));
    assertFalse(astar1.getOpenList().contains(astar1.getNode(11, 12)));
    assertEquals(110d, astar1.getNode(17, 9).getFCost());
    assertEquals(124d, astar1.getNode(18, 9).getFCost());
    assertTrue(Math.abs(astar1.getNode(12, 14).getFCost() - 125.71) < 0.01);

  }

  public void testAddNeighbours() {
    astar2.getNode(2, 2).setFCost(83);
    astar2.getNode(2, 2).setGCost(83);
    astar2.addNeighbours(astar2.getNode(2, 2));
    astar2.getClosedSet().add(astar2.getNode(2, 2));
    assertTrue(Math.abs(astar2.getNode(1, 1).getFCost() - 139.42) < 0.01);
    assertTrue(Math.abs(astar2.getNode(2, 1).getFCost() - 129.05) < 0.01);
    assertTrue(Math.abs(astar2.getNode(3, 1).getFCost() - 128.62) < 0.01);
    assertTrue(Math.abs(astar2.getNode(1, 2).getFCost() - 129.05) < 0.01);
    assertTrue(Math.abs(astar2.getNode(3, 2).getFCost() - 115.36) < 0.01);
    assertTrue(Math.abs(astar2.getNode(1, 3).getFCost() - 128.62) < 0.01);
    assertTrue(Math.abs(astar2.getNode(2, 3).getFCost() - 115.36) < 0.01);
    assertTrue(Math.abs(astar2.getNode(3, 3).getFCost() - 111.14) < 0.01);
    assertEquals(8, astar2.getOpenList().size());
    assertEquals(1, astar2.getClosedSet().size());
  }

  public void testCalcPath() {
    try {
      AStar astar = new AStar(5, new Vector2(1, 4), new Vector2(3, 5));
      fail();
    } catch (IllegalArgumentException e) {
    }

    AStar astar3 = new AStar(5, new Vector2(0, 2), new Vector2(3, 4));
    AStar astar4 = new AStar(5, new Vector2(4, 3), new Vector2(0, 0));
    AStar astar5 = new AStar(5, new Vector2(2, 1), new Vector2(4, 4));
    AStar astar6 = new AStar(5, new Vector2(0, 0), new Vector2(4, 4));
    astar3.setFinalPathStatus();
    boolean[][] blocks1 = {
        {true, false, false, false, false},
        {false, true, true, true, false},
        {false, false, true, false, false},
        {true, false, true, false, false},
        {false, false, true, false, false}
    };

    boolean[][] blocks2 = {
        {false, false, false, false, false},
        {false, true, true, false, false},
        {false, false, true, true, false},
        {false, true, true, false, false},
        {false, false, true, false, false}
    };

    boolean[][] blocks3 = {
        {false, false, false, false, false},
        {false, false, false, false, false},
        {false, false, false, false, false},
        {false, false, false, true, true},
        {false, false, false, true, false}
    };

    astar3.setBlocks(blocks1);
    astar4.setBlocks(blocks2);
    astar5.setBlocks(blocks3);

    try {
      astar6.setBlock(new Vector2(5, 0));
      fail();
    } catch (IllegalArgumentException e) {
    }

    try {
      astar6.setBlock(new Vector2(0, 5));
      fail();
    } catch (IllegalArgumentException e) {
    }

    try {
      astar6.setBlock(new Vector2(0, 0));
      fail();
    } catch (IllegalArgumentException e) {
    }

    astar3.calcPath();
    astar4.calcPath();
    astar5.calcPath();

    assertEquals(92d, astar3.getTotalCost());
    assertEquals(58d, astar4.getTotalCost());
    assertEquals(Double.POSITIVE_INFINITY, astar5.getTotalCost());

  }

  public void testToString() {

    AStar astar7 = new AStar(10, new Vector2(8, 3), new Vector2(6, 7));
    AStar astar8 = new AStar(20, new Vector2(0, 19), new Vector2(19, 0));

    boolean[][] blocks4 = {
        {true, false, true, false, false, false, false, true, false, false},
        {false, true, false, false, false, false, false, true, false, false},
        {false, false, false, false, false, true, false, false, false, false},
        {false, false, true, false, false, false, false, true, false, true},
        {false, false, false, false, true, false, false, false, false, false},
        {false, false, false, true, true, false, false, false, true, false},
        {false, false, true, false, false, false, false, false, false, false},
        {false, false, false, false, false, false, false, false, false, false},
        {true, false, false, false, false, false, true, false, false, false},
        {false, false, false, false, false, false, false, false, false, false}
    };

    astar7.setBlocks(blocks4);

    assertEquals(
        "      00 01 02 03 04 05 06 07 08 09 \n"
            + "-----------------------------------\n"
            + " 00 | ||    ||             ||      \n"
            + " 01 |    ||                ||      \n"
            + " 02 |                ||            \n"
            + " 03 |       ||             ||    ||\n"
            + " 04 |             ||               \n"
            + " 05 |          || ||          ||   \n"
            + " 06 |       ||                     \n"
            + " 07 |                              \n"
            + " 08 | ||                ||         \n"
            + " 09 |                              \n"
            + "-----------------------------------", astar7.toString());

    astar8.calcPath();
    astar8.setFinalPathStatus();
    System.out.println(astar8.toString());

    assertEquals(
        "      00 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16 17 18 19 \n"
            + "-----------------------------------------------------------------\n"
            + " 00 |                                                          FI\n"
            + " 01 |                                                       ~~   \n"
            + " 02 |                                                    ~~      \n"
            + " 03 |                                                 ~~         \n"
            + " 04 |                                              ~~            \n"
            + " 05 |                                           ~~               \n"
            + " 06 |                                        ~~                  \n"
            + " 07 |                                     ~~                     \n"
            + " 08 |                                  ~~                        \n"
            + " 09 |                               ~~                           \n"
            + " 10 |                            ~~                              \n"
            + " 11 |                         ~~                                 \n"
            + " 12 |                      ~~                                    \n"
            + " 13 |                   ~~                                       \n"
            + " 14 |                ~~                                          \n"
            + " 15 |             ~~                                             \n"
            + " 16 |          ~~                                                \n"
            + " 17 |       ~~                                                   \n"
            + " 18 |    ~~                                                      \n"
            + " 19 | ST                                                         \n"
            + "-----------------------------------------------------------------", astar8.toString()
    );

    //assertEquals();
  }

  public void testSetRandomBlocks() {
    AStar astar9 = new AStar(20, new Vector2(0, 0), new Vector2(10, 9), true);
    try {
      astar9.setRandomBlocks(500);
      fail();
    } catch (IllegalArgumentException e) {
    }

    astar9.setRandomBlocks(157);
    assertEquals(157, Arrays.stream(astar9.getGrid()).flatMap(Arrays::stream).filter(n -> !n.isWalkable()).count());


  }

}
