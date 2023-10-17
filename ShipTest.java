import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa03.Model.Coord;
import cs3500.pa03.Model.DirectionType;
import cs3500.pa03.Model.Ship;
import cs3500.pa03.Model.ShipType;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {
  Ship s;
  Ship s2;
  Ship s3;
  Ship s4;

  @BeforeEach
  public void setup() {
    Coord first = new Coord(2,1);
    Coord second = new Coord(1,2);
    List<Coord> points = List.of(first, second);
    s = new Ship(ShipType.CARRIER, points, DirectionType.HORIZONTAL, first);
  }

  @Test
  void getShipType() {
   assertEquals(ShipType.CARRIER, s.getShipType());

  }

  @Test
  void getPoints(){
    Coord first = new Coord(2,1);
    Coord second = new Coord(1,2);
    List<Coord> expected = List.of(first, second);
    assertEquals(expected.size(), s.getPoints().size());
  }

  @Test
  void testShipTypeLength() {
    assertEquals(6, ShipType.CARRIER.getLength());
    assertEquals(5, ShipType.BATTLESHIP.getLength());
    assertEquals(3, ShipType.SUBMARINE.getLength());
    assertEquals(4, ShipType.DESTROYER.getLength());
  }

  @Test
  void testShipTypeSymbol() {
    assertEquals('C', ShipType.CARRIER.getSymbol());
    assertEquals('B', ShipType.BATTLESHIP.getSymbol());
    assertEquals('S', ShipType.SUBMARINE.getSymbol());
    assertEquals('D', ShipType.DESTROYER.getSymbol());
  }

  @Test
  void getFirstCoord() {
    assertEquals(2, s.getFirstCoord().getX());
    assertEquals(1, s.getFirstCoord().getY());
  }

  @Test
  void getDirection() {
    assertEquals(DirectionType.HORIZONTAL, s.getDirection());
  }

  @Test
  void getShipLength() {
  }
}