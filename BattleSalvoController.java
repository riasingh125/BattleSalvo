package cs3500.pa03;

import static cs3500.pa03.View.PrintBoard.printAiBoard;
import static cs3500.pa03.View.PrintBoard.printBoard;

import cs3500.pa03.Model.AiPlayer;
import cs3500.pa03.Model.Coord;
import cs3500.pa03.Model.GameBoard;
import cs3500.pa03.Model.HitOrMiss;
import cs3500.pa03.Model.HumanPlayer;
import cs3500.pa03.Model.Ship;
import cs3500.pa03.Model.ShipType;
import cs3500.pa03.Model.ShipUnits;
import cs3500.pa03.Model.Player;
import cs3500.pa03.Model.TrackerBoard;
import cs3500.pa03.View.BoardSetupView;
import cs3500.pa03.View.PrintBoard;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//pass in boolean, set boolean false

/**
 * runs BattleSalvo game
 */
public class BattleSalvoController {
  public static BoardSetup bs;
  private Readable read;
  private Appendable append;

  public BattleSalvoController() {
  }

  public BattleSalvoController(Appendable append, Readable read) {
    append = Objects.requireNonNull(append);
    read = Objects.requireNonNull(read);
  }

  /**
   * overall function that runs the entire game
   */
  public void setupGame() {
    final ShipUnits su = new ShipUnits();
    bs = new BoardSetup(new InputStreamReader(System.in), System.out);

    bs.setupBoard();
    int height = bs.getHeight();
    int width = bs.getWidth();
    int carrier = bs.getCarrier();
    int battleship = bs.getBattleship();
    int destroyer = bs.getDestroyer();
    int submarine = bs.getSubmarine();
    int totalShipUnits = su.getShipUnits(carrier, battleship, destroyer, submarine);
    startGame(height, width, carrier, battleship, destroyer, submarine, totalShipUnits, bs);
  }

  /**
   * @param height
   * @param width
   * @param carrier
   * @param battleship
   * @param destroyer
   * @param submarine
   * @param totalShipUnits
   * @param bs             //places ships and prints board
   */
  private void startGame(int height, int width, int carrier, int battleship, int destroyer,
                         int submarine, int totalShipUnits, BoardSetup bs) {
    this.bs = bs;
    Player p1 = new HumanPlayer(this);
    Player p2 = new AiPlayer(this);
    final PrintBoard pb = new PrintBoard();
    //final StartGame sg = new StartGame();
    final TrackerBoard tb = new TrackerBoard();

    Map<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.SUBMARINE, submarine);
    specifications.put(ShipType.DESTROYER, destroyer);
    specifications.put(ShipType.BATTLESHIP, battleship);
    specifications.put(ShipType.CARRIER, carrier);

    List<Ship> playerShips = p1.setup(height, width, specifications);
    List<Ship> aiShips = p2.setup(height, width, specifications);

    GameBoard humanBoard = new GameBoard(height, width);
    for (Ship ship : playerShips) {
      humanBoard.placeShip(ship.getShipType(), ship.getDirection());
    }

    GameBoard aiBoard = new GameBoard(height, width);
    for (Ship ship : aiShips) {
      aiBoard.placeShip(ship.getShipType(), ship.getDirection());
    }

    char[][] trackerBoard = tb.trackerBoard(height, width);

    // pb.printBoard(aiBoard, "Opponent's Board Data"); // uncomment to see opponent's board
    printAiBoard(trackerBoard, "Opponent's Board");
    printBoard(humanBoard, "Your Board");
    runGame(humanBoard, aiBoard, trackerBoard, totalShipUnits, this);
  }




  /**
   * @return BoardSetup field in class
   */
  public BoardSetup getBoardSetup() {
    return this.bs;
  }

  /**
   * @param humanBoard
   * @param aiBoard
   * @param trackerBoard
   * @param totalShipUnits
   * @param bsg            //runs the game post setup while game isn't over
   */
  private void runGame(GameBoard humanBoard, GameBoard aiBoard, char[][] trackerBoard,
                       int totalShipUnits, BattleSalvoController bsg) {
    HumanPlayer humanPlayer = new HumanPlayer(bsg);
    AiPlayer aiPlayer = new AiPlayer(bsg);
    HitOrMiss hm = new HitOrMiss();
    BoardSetupView bsv = new BoardSetupView();
    boolean isGameOver = false;
    while (!isGameOver) {
      List<Coord> humanShots = humanPlayer.takeShots();
      List<Coord> aiShots = aiPlayer.takeShots();

      List<Coord> humanShotsOnBoard = humanPlayer.reportDamage(hm.aiHit(aiShots, humanBoard));
      List<Coord> aiShotsOnBoard = aiPlayer.reportDamage(hm.humanHit(humanShots, aiBoard,
          trackerBoard));

      humanPlayer.successfulHits(humanShotsOnBoard);
      aiPlayer.successfulHits(aiShotsOnBoard);

      printAiBoard(trackerBoard, "Opponent's Board");
      printBoard(humanBoard, "Your Board");

      if (humanBoard.getShipsSunk() == totalShipUnits) {
        bsv.printLoser();
        isGameOver = true;
        break;
      }
      if (aiBoard.getShipsSunk() == totalShipUnits) {
        bsv.printUserWinner();
        isGameOver = true;
        break;
      }
      if (humanBoard.getShipsSunk() == totalShipUnits && aiBoard.getShipsSunk() == totalShipUnits) {
        bsv.printTie();
        isGameOver = true;
        break;
      }

    }
  }
}