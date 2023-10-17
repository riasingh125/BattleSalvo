package cs3500.pa03.Model;

/**
 * Represents the coordinates of a cell.
 */
public class Coord {
  private int x;
  private int y;

  public Coord(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * @return the x position
   */
  public int getX() {
    return x;
  }

  /**
   * @return the y position
   */
  public int getY() {
    return y;
  }
}
