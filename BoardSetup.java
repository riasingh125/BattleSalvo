package cs3500.pa03;

import cs3500.pa03.Model.Validation;
import cs3500.pa03.View.BoardSetupView;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;

/**
 * Represents setting up the Board and all setup requirements
 */
public class BoardSetup {
  private int height;
  private int width;
  private int carrier;
  private int battleship;
  private int destroyer;
  private int submarine;

  private int totalShips;

  private final Readable rd;
  private final Appendable ap;

  public BoardSetup(int width, int height, int totalShips) {
    this.rd = new InputStreamReader(System.in);
    this.ap = System.out;
    this.width = width;
    this.height = height;
    this.totalShips = totalShips;
  }

  public BoardSetup(Readable rd, Appendable ap) {
    this.rd = Objects.requireNonNull(rd);
    this.ap = Objects.requireNonNull(ap);
  }

  /**
   * Sets up the board with ships and fleets
   */
  public void setupBoard() {
    BoardSetupView bsv = new BoardSetupView();
    final Scanner s = new Scanner(rd);
    final Validation valid = new Validation();

    bsv.printWelcomeMessage();
    bsv.printDimensionsMessage();
    bsv.printLine();

    height = valid.validateDimensions(s);
    width = valid.validateDimensions(s);

    int maxLength = Math.min(height, width);

    bsv.printLine();
    bsv.printFleetMessage(maxLength);
    bsv.printLine();

    carrier = valid.validateNumber(s);
    battleship = valid.validateNumber(s);
    destroyer = valid.validateNumber(s);
    submarine = valid.validateNumber(s);

    valid.validateFleet(maxLength, carrier, battleship, destroyer, submarine);

    int ships = carrier + battleship + destroyer + submarine;
    setTotalShips(ships);

  }

  /**
   * @return int -> height field
   */
  public int getHeight() {
    return height;
  }

  /**
   * @return int -> width field
   */
  public int getWidth() {
    return width;
  }

  /**
   * @return int -> carrier field
   */
  public int getCarrier() {
    return carrier;
  }

  /**
   * @return int -> battleship field
   */
  public int getBattleship() {
    return battleship;
  }

  /**
   * @return int -> destroyer field
   */
  public int getDestroyer() {
    return destroyer;
  }

  /**
   * @return int -> submarine field
   */
  public int getSubmarine() {
    return submarine;
  }

  /**
   * @param allShips //sets a new value for the totalShips field
   */
  public void setTotalShips(int allShips) {
    totalShips = allShips;
  }

  /**
   * @return int -> totalShips field
   */
  public int getTotalShips() {
    return totalShips;
  }
}
