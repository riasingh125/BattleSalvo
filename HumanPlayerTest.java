import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;


import cs3500.pa03.BattleSalvoController;
import cs3500.pa03.Model.Coord;
import cs3500.pa03.Model.GameBoard;
import cs3500.pa03.Model.GameResult;
import cs3500.pa03.Model.HumanPlayer;
import cs3500.pa03.Model.Ship;
import cs3500.pa03.Model.ShipType;
import cs3500.pa03.View.BoardSetupView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HumanPlayerTest {
  BattleSalvoController game;
  BoardSetupView view;
  HumanPlayer humanPlayer;

  @BeforeEach
  void setUp() {
    game = mock(BattleSalvoController.class);
    view = mock(BoardSetupView.class);
    humanPlayer = new HumanPlayer(game);
  }
  @Test
  public void nameTest(){
    assertEquals("User Player", humanPlayer.name());
  }

  @Test
  void testSetup() {
    int height = 10;
    int width = 10;
    Map<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.BATTLESHIP, 2);
    specifications.put(ShipType.CARRIER, 3);

    GameBoard gameBoard = new GameBoard(height, width);
    List<Ship> ships = humanPlayer.setup(height,width,specifications);

    // Assert that the returned list of ships has the expected size
    assertEquals(5, ships.size());

//    int height = 10;
//    int width = 10;
//    List<Coord> coords = new ArrayList<>();
//    coords.add(new Coord(0, 0));
//    coords.add(new Coord(1, 1));
//    coords.add(new Coord(2, 2));
//    Map<ShipType, Integer> specifications = new HashMap<>();
//    specifications.put(ShipType.CARRIER, 1);
//    specifications.put(ShipType.BATTLESHIP, 2);
//
//    GameBoard gameBoard = mock(GameBoard.class);
//    when(gameBoard.placeShip().thenReturn(coords);
//
//    List<Ship> expectedShips = new ArrayList<>();
//    expectedShips.add(new Ship(ShipType.CARRIER, coords));
//    expectedShips.add(new Ship(ShipType.BATTLESHIP, coords));
//    expectedShips.add(new Ship(ShipType.BATTLESHIP, coords));
//
//    List<Ship> actualShips = humanPlayer.setup(height, width, specifications);
//
//
//    assertEquals(expectedShips.size(), actualShips.size());
//    assertFalse(actualShips.containsAll(expectedShips));
  }

  @Test
  void testTakeShots() {
    List<Coord> opponentShots = new ArrayList<>();
    opponentShots.add(new Coord(0, 0));
    opponentShots.add(new Coord(1, 1));

    doNothing().when(view).printAiShots(0, 0);
    doNothing().when(view).printAiShots(1, 1);

    List<Coord> expectedShots = new ArrayList<>();
    expectedShots.add(new Coord(0, 0));
    expectedShots.add(new Coord(1, 1));

  }

  @Test
  void testReportDamage() {
    List<Coord> opponentShots = new ArrayList<>();
    opponentShots.add(new Coord(0, 0));
    opponentShots.add(new Coord(1, 1));

    doNothing().when(view).printAiShots(0, 0);
    doNothing().when(view).printAiShots(1, 1);

    List<Coord> actualShots = humanPlayer.reportDamage(opponentShots);

    assertEquals(opponentShots.size(), actualShots.size());
    assertTrue(actualShots.containsAll(opponentShots));
  }

  @Test
  void testSuccessfulHits() {
    List<Coord> successfulHits = new ArrayList<>();
    successfulHits.add(new Coord(0, 0));
    successfulHits.add(new Coord(1, 1));

    doNothing().when(view).printHumanShots(0, 0);
    doNothing().when(view).printHumanShots(1, 1);

    humanPlayer.successfulHits(successfulHits);
  }

  @Test
  void testEndGame() {
    humanPlayer.endGame(GameResult.WIN, "Congratulations!");

  }
}