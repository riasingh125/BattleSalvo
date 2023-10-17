package cs3500.pa03.Model;

/**
 * Represents the type of ship and its attributes.
 */
public enum ShipType {
  CARRIER(6, 'C'),
  BATTLESHIP(5, 'B'),
  SUBMARINE(3, 'S'),
  DESTROYER(4, 'D');

  private final int length;
  private final char symbol;

  ShipType(int length, char c) {
    this.length = length;
    this.symbol = c;
  }

  /**
   * @return the length of a ship
   */
  public int getLength() {
    return length;
  }

  /**
   * @return parses the value of a length into a string
   */
  @Override
  public String toString() {
    return String.valueOf(length);
  }

  /**
   * @return the symbol corresponding to the type of ship
   */
  public char getSymbol() {
    return symbol;
  }
}
