package algorithms.search.astar;

import algorithms.search.astar.model.Node;
import algorithms.search.astar.model.Vector2;
import algorithms.search.astar.visualization.cmdLineVisualization;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

/**
 * This clas is an implementation of the A* Algorithm which is an extension of the Dijkstra
 * Algorithm. A* finds the short path between two nodes in a graph. In contrast to the Dijkstra
 * Algorithm, it has an additonal cost to calculate: the H Cost, a heuristic which is the distance
 * between the current node and the destination node in this example. For more information:
 * https://en.wikipedia.org/wiki/A*_search_algorithm
 *
 * @author serturx
 */
public class AStar {

  /** Default diagonal moving cost */
  private static final double DIAG_COST = 14d;
  /** Default horizontal and vertical move cost */
  private static final double DEF_COST = 10d;
  /** List with all found nodes, the one with the lowest f cost is at the top */
  private PriorityQueue<Node> openList;
  /** All closed nodes (closed meaning all it's neighbours have been added) */
  private Set<Node> closedSet;
  /** Quadratic Grid containing all nodes */
  private Node[][] grid;
  /** Starting node */
  private Node from;
  /** Destination node */
  private Node to;
  /** List containing the best path after it has been calculated */
  private ArrayList<Node> path;
  /** Total cost of the calculated path */
  private double totalCost;
  /** Whether to print the algorithm steps */
  private boolean showUpdates;
  /** Time to wait between opening new nodes */
  private int delay;

  /**
   * AStar Constructor
   *
   * @param size Size of the grid
   * @param from Starting node coordinates
   * @param to Destination node coordinates
   */
  public AStar(int size, Vector2 from, Vector2 to) {

    // Checks whether the given coordinates are in the grid
    if (!(from.allInRange(0, size) || !to.allInRange(0, size))) {
      throw new IllegalArgumentException("Start or Destination Node out of Bounds");
    }

    this.grid = new Node[size][size];

    // Fills the array with nods
    for (int i = 0; i < this.grid.length; i++) {
      for (int j = 0; j < this.grid.length; j++) {
        this.grid[i][j] = new Node(j, i);
      }
    }

    // Sets the rest of the members up
    this.from = grid[from.getY()][from.getX()];
    this.to = grid[to.getY()][to.getX()];
    this.from.setFCost(0);
    this.from.setGCost(0);
    this.from.setHCost(0);
    this.path = new ArrayList<Node>();
    this.openList =
        new PriorityQueue<>(
          new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
              return Double.compare(o1.getFCost(), o2.getFCost());
            }
          });

    this.closedSet = new HashSet<>();
    this.showUpdates = false;
  }

  /**
   * Additional Constructor is updates between algorithm steps are desired
   *
   * @param size Size of the grid
   * @param from Starting node coordinates
   * @param to Destionation node coordinates
   * @param showUpdates Whether to show steps
   * @param delay Delay between steps
   */
  public AStar(int size, Vector2 from, Vector2 to, boolean showUpdates, int delay) {
    this(size, from, to);
    this.showUpdates = showUpdates;
    this.delay = delay;
  }

  /**
   * Calculates the shortest path between the two given nodes
   *
   * @throws InterruptedException
   */
  public void calcPath() throws InterruptedException {
    boolean found = false;
    // add the starting node
    openList.add(from);

    // While there are nodes to discover
    while (!openList.isEmpty()) {
      if (showUpdates) {
        updateNodeCostShow();
        Thread.sleep(delay);
      }

      // takes the node with the lower f Cost
      Node current = openList.poll();
      closedSet.add(current);

      // if the taken node is the destination node, a path has been found
      if (current.equals(getTo())) {
        found = true;
        totalCost = current.getFCost();
        backTracePath(current);
        break;
      } else {
        // otherwise just add all neighbouring nodes to the open list
        addNeighbours(current);
      }
    }

    if (found) {
      setFinalPathStatusShow();
    } else {
      totalCost = Double.POSITIVE_INFINITY;
    }
  }

  /**
   * Adds all neighbouring nodes to the open list
   *
   * @param current opened node
   */
  private void addNeighbours(Node current) {
    int currentX = current.getX();
    int currentY = current.getY();

    // go through all neighbouring nodes
    for (int i = currentY - 1; i <= currentY + 1; i++) {
      if (i < 0 || i >= grid.length) {
        continue;
      }

      for (int j = currentX - 1; j <= currentX + 1; j++) {
        if (j < 0 || j >= grid.length) {
          continue;
        }

        if (!(currentX == j && currentY == i)) {
          addNeighbourNode(
              current, grid[i][j], isDiagonal(current, grid[i][j]) ? DIAG_COST : DEF_COST);
        }
      }
    }
  }

  /**
   * Traces the path back
   *
   * @param current opened node
   */
  private void backTracePath(Node current) {
    if (current != null) {
      path.add(current);
      backTracePath(current.getPrevious());
    }
  }

  /**
   * Adds a neighbouring node or updates its cost if its already in the list
   *
   * @param current opened node
   * @param neighbour neighbouring node to add
   * @param cost cost of moving from the opened node to the neighouring node
   */
  private void addNeighbourNode(Node current, Node neighbour, double cost) {
    if (neighbour.isWalkable() && !closedSet.contains(neighbour)) {
      // if the neighbouring node hasn't been added yet add it and calculate its cost
      // otherwise if the found path to the node is shorter than the previous one,
      // update the
      // cost and set its parent node to the just opened node
      if (!openList.contains(neighbour) || neighbour.checkShorterPath(current, cost)) {
        neighbour.setPrevious(current);
        neighbour.calcAllCosts(to, cost);
        if (!openList.contains(neighbour)) {
          openList.add(neighbour);
        } else {
          // Re-add the changed node, as PriorityQueue only checks the value when added to
          // the Queue
          Node n = neighbour;
          closedSet.remove(n);
          closedSet.add(n);
        }
      }
    }
  }

  /**
   * Checks whether the neighbouring node is diagonal relative to the opened node
   *
   * @param opened Opened Node
   * @param neighbour Neighbouring node to check
   * @return Whether the node is diagonal
   */
  private boolean isDiagonal(Node opened, Node neighbour) {
    int midX = opened.getX();
    int midY = opened.getY();
    int neiX = neighbour.getX();
    int neiY = neighbour.getY();

    if (neiX == midX - 1 && neiY == midY + 1) {
      return true;
    }
    if (neiX == midX + 1 && neiY == midY + 1) {
      return true;
    }
    if (neiX == midX - 1 && neiY == midY - 1) {
      return true;
    }
    if (neiX == midX + 1 && neiY == midY - 1) {
      return true;
    }

    return false;
  }

  /**
   * Sets the status of all nodes on the path to reflect that the path goes through them And prints
   * the grid
   */
  private void setFinalPathStatusShow() {
    path.forEach(
        node -> {
          node.setStatus("~~");
        });

    grid[from.getY()][from.getX()].setStatus("ST");
    grid[to.getY()][to.getX()].setStatus("FI");

    cmdLineVisualization.showGrid(grid);
  }

  /**
   * Updates all status on found nodes, shows their cost if they haven't been closed yet, otherwise
   * shows CL And prints the grid
   */
  private void updateNodeCostShow() {
    closedSet.forEach(
        node -> {
          node.setStatus(String.valueOf("CL"));
        });

    openList.forEach(
        node -> {
          node.setStatus(String.valueOf(node.getFCost()));
        });

    grid[to.getY()][to.getX()].setStatus("FI");

    cmdLineVisualization.showGrid(grid);
  }

  /**
   * Sets impassable nodes
   *
   * @param blocks Nodes to block
   */
  public void setBlocks(ArrayList<Vector2> blocks) {
    blocks.forEach(
        v -> {
          grid[v.getY()][v.getX()].setWalkable(false);
          grid[v.getY()][v.getX()].setStatus("||");
        });
  }

  /**
   * Sets a given amount of nodes impassable randomly
   *
   * @param amount
   */
  public void setRandomBlocks(int amount) {
    Random r = new Random();

    if (amount >= Math.pow(grid.length, 2)) {
      throw new IllegalArgumentException("Exception: Amount >= Total Grid Nodes");
    }

    while (amount > 0) {

      int X = r.nextInt(grid.length - 1);
      int Y = r.nextInt(grid.length - 1);
      if (!(grid[Y][X].equals(from) || grid[Y][X].equals(to)) && grid[Y][X].isWalkable()) {
        grid[Y][X].setWalkable(false);
        grid[Y][X].setStatus("||");
        amount--;
      }
    }
  }

  public double getTotalCost() {
    return this.totalCost;
  }

  public void setTotalCost(double totalCost) {
    this.totalCost = totalCost;
  }

  public Node[][] getGrid() {
    return this.grid;
  }

  public void setGrid(Node[][] grid) {
    this.grid = grid;
  }

  public Node getFrom() {
    return this.from;
  }

  public void setFrom(Node from) {
    this.from = from;
  }

  public Node getTo() {
    return this.to;
  }

  public void setTo(Node to) {
    this.to = to;
  }
}
