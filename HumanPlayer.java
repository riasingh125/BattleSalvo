package cs3500.pa03.Model;

import cs3500.pa03.BattleSalvoController;
import cs3500.pa03.View.BoardSetupView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Represents the human player.
 */
public class HumanPlayer implements Player {
  BattleSalvoController gm;
  Random rand = new Random();


  /**
   * @return the name of the human player
   */
  @Override
  public String name() {
    return "User Player";
  }

  public HumanPlayer(BattleSalvoController gm) {
    this.gm = gm;
  }

  /**
   * @param height         the height of the board, range: [6, 15] inclusive
   * @param width          the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return a list of ship and placements of the ship
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    ArrayList<Ship> ships = new ArrayList<>();
    GameBoard gm = new GameBoard(height, width);
    for (ShipType shipType : specifications.keySet()) {
      for (int i = 0; i < specifications.get(shipType); i++) {
        DirectionType direction =
            rand.nextBoolean() ? DirectionType.HORIZONTAL : DirectionType.VERTICAL;
        List<Coord> shipCoords = gm.placeShip(shipType, direction);
        Coord startCoord = gm.getFirstCoord();
        Ship ship = new Ship(shipType, shipCoords, direction, startCoord);
        ships.add(ship);

      }
    }
    return ships;
  }

  /**
   * @return list of shots the human player sends
   */
  @Override
  public List<Coord> takeShots() {
    TakeShot ts = new TakeShot();
    return ts.takeShots(gm.getBoardSetup());
  }

  /**
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return list of ship hits
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    BoardSetupView bsv = new BoardSetupView();
    for (Coord c : opponentShotsOnBoard) {
      int x = c.getX();
      int y = c.getY();
      bsv.printAiShots(x, y);
    }
    return opponentShotsOnBoard;
  }

  /**
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   *                                  //  represents opponents ship sunk
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    BoardSetupView bsv = new BoardSetupView();
    for (Coord c : shotsThatHitOpponentShips) {
      int x = c.getX();
      int y = c.getY();
      bsv.printHumanShots(x, y);
    }
  }

  /**
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {
    return;
  }
}
