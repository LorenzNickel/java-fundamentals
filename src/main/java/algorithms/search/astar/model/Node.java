package algorithms.search.astar.model;

/**
 * This class represents a node in graph specific for this implementation of the A* Algorithm
 */
public class Node {

  /**
   * Coordinate (or position) of the node in the grid
   */
  Vector2 pos;
  /**
   * String shown in the CLI (exactly 2 chars long)
   */
  private String status;
  /**
   * G Cost and H Cost combined
   */
  private double fCost;
  /**
   * Heuristic: Distance to the end node
   */
  private double hCost;
  /**
   * Distance to the start node
   */
  private double gCost;
  /**
   * Whether the node is traversable
   */
  private boolean walkable;
  /**
   * Parent (or previous) node
   */
  private Node previous;

  /**
   * Node constructor
   *
   * @param pos Coordinate of the node in the grid
   */
  public Node(Vector2 pos) {
    this.pos = pos;
    this.status = "  ";
    this.fCost = Double.POSITIVE_INFINITY;
    this.hCost = Double.POSITIVE_INFINITY;
    this.gCost = Double.POSITIVE_INFINITY;
    this.walkable = true;
    this.previous = null;
  }

  /**
   * Checks whether a shorter path is found
   *
   * @param openedNode Node which called this method
   * @param cost       Cost to move from the calling node to this
   * @return whether the path is shorter
   */
  public boolean checkShorterPath(Node openedNode, double cost) {
    return openedNode.getGCost() + cost < gCost;
  }

  /**
   * Calculates all Costs
   *
   * @param to   Destination node
   * @param cost Cost to move to this node from the calling node
   */
  public void calcAllCosts(Node to, double cost) {
    calcHCost(to);
    calcGCost(cost);
    fCost = gCost + hCost;
  }

  /**
   * Calculates the H cost
   *
   * @param to Destination node
   */
  public void calcHCost(Node to) {
    hCost = Math.sqrt(Math.pow(pos.getX() - to.getX(), 2) + Math.pow(pos.getY() - to.getY(), 2)) * 10;
  }

  /**
   * Calculates the G cost
   *
   * @param cost Cost to move to this node from the calling node
   */
  public void calcGCost(double cost) {
    gCost = previous.getGCost() + cost;
  }

  @Override
  public boolean equals(Object o) {
    Node oNode = (Node) o;
    return (this.pos.getX() == oNode.getX() && this.pos.getY() == oNode.getY());
  }

  public Node getPrevious() {
    return this.previous;
  }

  public void setPrevious(Node previous) {
    this.previous = previous;
  }

  public boolean isWalkable() {
    return this.walkable;
  }

  public void setWalkable(boolean walkable) {
    this.walkable = walkable;
  }

  public int getX() {
    return this.pos.getX();
  }

  public void setX(int x) {
    this.pos.setX(x);
  }

  public int getY() {
    return this.pos.getY();
  }

  public void setY(int y) {
    this.pos.setY(y);
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public double getFCost() {
    return this.fCost;
  }

  public void setFCost(double fCost) {
    this.fCost = fCost;
  }

  public double getHCost() {
    return this.hCost;
  }

  public void setHCost(double hCost) {
    this.hCost = hCost;
  }

  public double getGCost() {
    return this.gCost;
  }

  public void setGCost(double gCost) {
    this.gCost = gCost;
  }
}
