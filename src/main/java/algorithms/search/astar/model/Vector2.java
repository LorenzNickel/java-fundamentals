package algorithms.search.astar.model;

/**
 * Represents a 2D Coordinate in respect to a grid (only positive integers are allowed)
 */
public class Vector2 {

  /**
   * X Coordinate (or Column)
   */
  private int x;
  /**
   * Y Coordinate (or Row)
   */
  private int y;

  public Vector2(int x, int y) {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("Coordinate must be positive");
    }

    this.x = x;
    this.y = y;
  }

  /**
   * Checks whether x and y are in the given range
   *
   * @param lowerBound lower bound inclusive
   * @param upperBound upper bound exclusive
   * @return whether x and y are in range
   */

  public boolean allInRange(int lowerBound, int upperBound) {
    if (lowerBound >= upperBound || lowerBound < 0) {
      throw new IllegalArgumentException("Bounds cannot be equal, upperBound must be bigger than lowerBound and "
          + "bounds must be positive");
    }

    return lowerBound <= x && x < upperBound && lowerBound <= y && y < upperBound;
  }

  @Override
  public String toString() {
    return String.format("x: %d, y: %d", this.x, this.y);
  }

  @Override
  public boolean equals(Object obj) {
    Node oNode = (Node) obj;
    return this.x == oNode.getX() && this.y == oNode.getY();
  }

  public int getX() {
    return this.x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return this.y;
  }

  public void setY(int y) {
    this.y = y;
  }
}
