import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.Model.GameBoard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameBoardTest {
  GameBoard gb;

  @BeforeEach
  void setup() {
    gb = new GameBoard(6, 6);
  }

  @Test
  void createBoard() {
  }

  @Test
  void getHeight() {
  }

  @Test
  void getWidth() {
  }

  @Test
  void getBoard() {
  }

  @Test
  void placeShip() {
  }

  @Test
  void openSpot() {
  }

  @Test
  void setBoard() {
  }

  @Test
  void sinkShip() {
    gb.sinkShip();
    assertEquals(1, gb.getShipsSunk());
  }

  @Test
  void getShipsSunk() {
  }
}