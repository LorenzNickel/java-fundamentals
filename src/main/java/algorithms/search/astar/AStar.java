package algorithms.search.astar;

import algorithms.search.astar.model.Node;
import algorithms.search.astar.model.Vector2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

/**
 * This class is an implementation of the A* Algorithm which is an extension of the Dijkstra Algorithm. A* finds the
 * short path between two nodes in a graph. In contrast to the Dijkstra Algorithm, it has an additonal cost to
 * calculate: the H Cost, a heuristic which is the distance between the current node and the destination node in this
 * example. For more information: https://en.wikipedia.org/wiki/A*_search_algorithm
 *
 * @author serturx
 */
public class AStar {

  /**
   * Default diagonal moving cost
   */
  private static final double DIAG_COST = 14d;
  /**
   * Default horizontal and vertical move cost
   */
  private static final double DEF_COST = 10d;
  /**
   * List with all found nodes, the one with the lowest f cost is at the top
   */
  private PriorityQueue<Node> openList;
  /**
   * All closed nodes (closed meaning all it's neighbours have been added)
   */
  private Set<Node> closedSet;
  /**
   * Quadratic Grid containing all nodes
   */
  private Node[][] grid;
  /**
   * Starting node
   */
  private Node from;
  /**
   * Destination node
   */
  private Node to;
  /**
   * List containing the best path after it has been calculated
   */
  private ArrayList<Node> path;
  /**
   * Total cost of the calculated path
   */
  private double totalCost;
  /**
   * Whether to keep track of the algorithms steps
   */
  private boolean trackSteps;
  /**
   * Contains all algorithm steps
   */
  private ArrayList<String> steps;

  /**
   * AStar Constructor
   *
   * @param size Size of the grid
   * @param from Starting node coordinates
   * @param to   Destination node coordinates
   */
  public AStar(int size, Vector2 from, Vector2 to) {

    // Checks whether the given coordinates are in the grid
    if (!from.allInRange(0, size) || !to.allInRange(0, size)) {
      throw new IllegalArgumentException("Start or Destination Node out of Bounds");
    }

    this.grid = new Node[size][size];

    // Fills the array with nodes
    for (int i = 0; i < this.grid.length; i++) {
      for (int j = 0; j < this.grid.length; j++) {
        this.grid[i][j] = new Node(new Vector2(j, i));
      }
    }

    // Sets the rest of the members up
    this.from = grid[from.getY()][from.getX()];
    this.to = grid[to.getY()][to.getX()];
    this.from.setFCost(0);
    this.from.setGCost(0);
    this.from.setHCost(0);
    this.path = new ArrayList<>();
    this.openList =
        new PriorityQueue<>(
            Comparator.comparingDouble(Node::getFCost));

    this.closedSet = new HashSet<>();
    this.trackSteps = false;
    this.totalCost = Double.POSITIVE_INFINITY;
  }

  /**
   * Additional Constructor if steps should be tracked
   *
   * @param size       Size of the grid
   * @param from       Starting node coordinates
   * @param to         Destionation node coordinates
   * @param trackSteps Whether to track steps
   */
  public AStar(int size, Vector2 from, Vector2 to, boolean trackSteps) {
    this(size, from, to);
    this.trackSteps = trackSteps;
    this.steps = new ArrayList<>();
  }

  /**
   * Calculates the shortest path between the two given nodes
   */
  public void calcPath() {
    boolean found = false;
    // add the starting node
    openList.add(from);

    // While there are nodes to discover
    while (!openList.isEmpty()) {
      if (trackSteps) {
        updateNodeCostStatus();
      }

      // takes the node with the lower f Cost
      Node current = openList.poll();
      closedSet.add(current);

      if (current == null) {
        throw new NullPointerException("Fatal Error: Null pointer on current node");
      }
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
      setFinalPathStatus();
    }
  }

  /**
   * Adds all neighbouring nodes to the open list
   *
   * @param current opened node
   */
  public void addNeighbours(Node current) {
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
   * @param current   opened node
   * @param neighbour neighbouring node to add
   * @param cost      cost of moving from the opened node to the neighouring node
   */
  public void addNeighbourNode(Node current, Node neighbour, double cost) {
    if (neighbour.isWalkable() && !closedSet.contains(neighbour)) {
      // if the neighbouring node hasn't been added yet add it and calculate its cost
      // otherwise if the found path to the node is shorter than the previous one,
      // update the cost and set its parent node to the just opened node
      if (!openList.contains(neighbour) || neighbour.checkShorterPath(current, cost)) {
        neighbour.setPrevious(current);
        neighbour.calcAllCosts(to, cost);
        if (!openList.contains(neighbour)) {
          openList.add(neighbour);
        } else {
          // Re-add the changed node, as PriorityQueue only checks the value when added to
          // the Queue
          closedSet.remove(neighbour);
          closedSet.add(neighbour);
        }
      }
    }
  }


  /**
   * Checks whether the neighbouring node is diagonal relative to the other node
   *
   * @param o1 Node
   * @param o2 other Node
   * @return Whether the node are diagonal to eachother
   */
  public boolean isDiagonal(Node o1, Node o2) {
    return Math.abs(o1.getX() - o2.getX()) == 1 && Math.abs(o1.getY() - o2.getY()) == 1;
  }

  /**
   * Sets the status of all nodes on the path to reflect that the path goes through them and adds it to the steps list
   */
  public void setFinalPathStatus() {
    path.forEach(node -> node.setStatus("~~"));

    grid[from.getY()][from.getX()].setStatus("ST");
    grid[to.getY()][to.getX()].setStatus("FI");

    if (trackSteps) {
      steps.add(this.toString());
    }

  }

  /**
   * Updates all status on found nodes, shows their cost if they haven't been closed yet and adds it to the steps list
   */
  private void updateNodeCostStatus() {
    closedSet.forEach(node -> node.setStatus("CL"));

    openList.forEach(node -> node.setStatus(String.valueOf(node.getFCost())));

    grid[to.getY()][to.getX()].setStatus("FI");
    grid[from.getY()][from.getX()].setStatus("ST");

    steps.add(this.toString());

  }

  /**
   * Sets an impassable node
   *
   * @param v coordinate of the block to set
   */
  public void setBlock(Vector2 v) {
    if ((v.getX() == from.getX() && v.getY() == from.getY()) || (v.getX() == to.getX() && v.getY() == to.getY())) {
      throw new IllegalArgumentException("Node to set as block is either the start or destination node");
    } else {
      if (v.getX() >= grid.length || v.getY() >= grid.length) {
        throw new IllegalArgumentException("Node to set as block is out of bounds");
      }
    }

    grid[v.getY()][v.getX()].setWalkable(false);
    grid[v.getY()][v.getX()].setStatus("||");
  }

  /**
   * Sets a given amount of nodes impassable randomly
   *
   * @param amount amount of blocks to add
   */
  public void setRandomBlocks(int amount) {
    Random r = new Random();

    if (amount >= Math.pow(grid.length, 2)) {
      throw new IllegalArgumentException("Exception: Amount >= Total Grid Nodes");
    }

    while (amount > 0) {

      int x = r.nextInt(grid.length - 1);
      int y = r.nextInt(grid.length - 1);
      if (!(grid[y][x].equals(from) || grid[y][x].equals(to)) && grid[y][x].isWalkable()) {
        grid[y][x].setWalkable(false);
        grid[y][x].setStatus("||");
        amount--;
      }
    }
  }

  /**
   * Sets blocks according to a 2D boolean array true meaning the underlying node should not be walkable e.g. if
   * input[2][3] is true the node at x: 3 and y: 2 will be set as unwalkable
   *
   * @param input input array
   */
  public void setBlocks(boolean[][] input) {
    if (input.length != grid.length || Arrays.stream(input).anyMatch(i -> i.length != grid.length)) {
      throw new IllegalArgumentException(String.format("Input array must be size %dx%d", grid.length, grid.length));
    }

    for (int i = 0; i < input.length; i++) {
      for (int j = 0; j < input.length; j++) {
        if (input[i][j]) {
          setBlock(new Vector2(j, i));
        }
      }
    }
  }

  /**
   * Returns a simple visualization of the Grid
   */
  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();

    // Column indeces
    for (int i = 0; i < grid.length; i++) {
      if (i == 0) {
        sb.append("      00 ");
      } else {
        if (i > 9) {
          sb.append(String.format("%d ", i));
        } else {
          sb.append(String.format("0%d ", i));
        }
      }
    }

    sb.append("\n");

    for (int i = 0; i < grid.length * 3 + 5; i++) {
      sb.append("-");
    }

    sb.append("\n");

    // Node prints
    for (int i = 0; i < grid.length; i++) {

      sb.append(String.format(" %s |", i < 10 ? "0" + i : i));

      for (int j = 0; j < grid[0].length; j++) {
        String status = grid[i][j].getStatus();
        sb.append(" ").append(status);
      }

      sb.append("\n");
    }

    for (int i = 0; i < grid.length * 3 + 5; i++) {
      sb.append("-");
    }

    return sb.toString();
  }

  public double getTotalCost() {
    return this.totalCost;
  }

  public Node[][] getGrid() {
    return this.grid;
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

  public ArrayList<String> getSteps() {
    return this.steps;
  }

  public void setNode(Node toSet) {
    grid[toSet.getY()][toSet.getX()] = toSet;
  }

  public Node getNode(Vector2 coordinates) {
    return grid[coordinates.getY()][coordinates.getX()];
  }

  public PriorityQueue<Node> getOpenList() {
    return openList;
  }

  public Node getNode(int x, int y) {
    return grid[y][x];
  }

  public Set<Node> getClosedSet() {
    return closedSet;
  }

}
