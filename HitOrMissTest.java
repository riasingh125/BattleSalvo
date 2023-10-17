import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa03.Model.Coord;
import cs3500.pa03.Model.DirectionType;
import cs3500.pa03.Model.GameBoard;
import cs3500.pa03.Model.HitOrMiss;
import cs3500.pa03.Model.Ship;
import cs3500.pa03.Model.ShipType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HitOrMissTest {
  HitOrMiss h;
  GameBoard board;
  char[][] trackerBoard;

  /**
   * Set up the HitOrMiss object.
   */
  @BeforeEach
  public void setup() {
    h = new HitOrMiss();
    board = new GameBoard(8, 8);
    trackerBoard = new char[8][8];
  }

  @Test
  void humanHit() {
    List<Coord> points = Arrays.asList(
        new Coord(1, 1),
        new Coord(1, 2),
        new Coord(1, 3));
    List<Coord> successfulHits = h.humanHit(points, board, trackerBoard);
    assertEquals('M', board.getBoard(1,1));
    assertEquals('M', board.getBoard(1,2));
    assertEquals('M', board.getBoard(1,3));
    assertEquals('M', trackerBoard[1][1]);
    assertEquals('M', trackerBoard[1][2]);
    assertEquals('M', trackerBoard[1][3]);

    assertEquals(0, successfulHits.size());

  }

  @Test
  void aiHit() {
    List<Coord> points = new ArrayList<>();
    points.add(new Coord(2, 2));
    points.add(new Coord(3, 3));

    List<Coord> successfulHits = h.aiHit(points, board);

    assertEquals('M', board.getBoard(2, 2));
    assertEquals('M', board.getBoard(3, 3));

    assertEquals(0, successfulHits.size());
  }

  @Test
  void aiServerBoardHits() {
    List<Coord> shots = new ArrayList<>();
    shots.add(new Coord(2, 3));
    shots.add(new Coord(4, 5));

    List<Ship> ships = new ArrayList<>();
    Ship ship1 = new Ship(
        ShipType.BATTLESHIP, List.of(new Coord(2, 3), new Coord(2, 4), new Coord(2, 5), new Coord(2, 6)), DirectionType.HORIZONTAL, new Coord(2, 3));
    Ship ship2 = new Ship(ShipType.CARRIER, List.of(new Coord(4, 5), new Coord(5, 5), new Coord(6, 5)), DirectionType.VERTICAL, new Coord(4, 5));
    ships.add(ship1);
    ships.add(ship2);

    List<Coord> successfulHits = h.AiServerBoardHits(shots, ships);

    // Assert the size of the successfulHits list
    assertEquals(2, successfulHits.size());
  }
}