package algorithms.search.astar.model;

/** Represents a 2D Coordinate */
public class Vector2 {
  /** X Coordinate (or Column) */
  private int x;
  /** Y Coordinate (or Row) */
  private int y;

  public Vector2(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Checks whether x and y are in the given range
   *
   * @param lowerBound lower bound inclusive
   * @param upperBound upper bound exclusive
   * @return whether they're in range
   */
  public boolean allInRange(int lowerBound, int upperBound) {
    if (lowerBound <= x && x < upperBound && lowerBound <= y && y < upperBound) {
      return true;
    }
    return false;
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
