import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa03.Model.Coord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoordTest {
  Coord c;

  /**
   * Set up the Coord object.
   */
  @BeforeEach
  public void setup() {
    c = new Coord(1, 1);
  }

  @Test
  void getRow() {
    c.getX();
    assertEquals(1, c.getX());
  }

  @Test
  void getCol() {
    c.getY();
    assertEquals(1, c.getY());
  }
}