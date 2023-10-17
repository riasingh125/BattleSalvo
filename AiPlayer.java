package cs3500.pa03.Model;

import cs3500.pa03.BattleSalvoController;
import cs3500.pa03.View.BoardSetupView;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class AiPlayer implements Player {
  BattleSalvoController bsg;
  Random rand = new Random();

  int height;
  int width;
  int numShips;


  @Override
  public String name() {
    return "A-goofyGoober";
  }

  public AiPlayer(BattleSalvoController bsg) {
    this.bsg = bsg;
  }

  /**
   * @param height         the height of the board, range: [6, 15] inclusive
   * @param width          the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return -> a list of total ships on the board
   * //sets the ships up and places the ships on the board
   */
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    ArrayList<Ship> ships = new ArrayList<>();
    Map<ShipType, Integer> map = new LinkedHashMap<>(specifications);
    for (Map.Entry<ShipType, Integer> shipType : map.entrySet()) {
      for (int i = 0; i < specifications.get(shipType.getKey()); i++) {
        GameBoard gm = new GameBoard(height, width);
        DirectionType direction = rand.nextBoolean() ? DirectionType.VERTICAL
            : DirectionType.HORIZONTAL;
        List<Coord> shipCoords = gm.placeShip(shipType.getKey(), direction);
        Coord startCoord = gm.getFirstCoord();
        Ship ship = new Ship(shipType.getKey(), shipCoords, direction, startCoord);
        ships.add(ship);

      }
    }
    this.width = width;
    this.height = height;
    this.numShips = ships.size();
    return ships;
  }

  /**
   * @return a list of shots the ai player shoots
   */
  @Override
  public List<Coord> takeShots() {
    TakeShot ts = new TakeShot();
    return ts.ServerTakeShots(this.height, this.width, this.numShips);
  }

  /**
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return list of which shots hit a ship on ai player and updates board
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    return opponentShotsOnBoard;
  }

  /**
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   *                                  // updates the opponent board shown to ai with the correct shots
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    BoardSetupView bsv = new BoardSetupView();
    for (Coord c : shotsThatHitOpponentShips) {
      int x = c.getX();
      int y = c.getY();
      bsv.printAiShots(x, y);
    }

  }

  /**
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   *               // explains why the game ended
   */
  @Override
  public void endGame(GameResult result, String reason) {
    System.out.println("Result, " + result + ", Reason: " + reason);
  }
}