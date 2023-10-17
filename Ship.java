package cs3500.pa03.Model;

import java.util.List;

/**
 * Represents a ship.
 */
public class Ship {
  private ShipType type;
  private List<Coord> points;
  private DirectionType direction;
  private int shipLength;
  private Coord firstCoord;

  public Ship(ShipType type, List<Coord> points, DirectionType direction, Coord firstCoord) {
    this.type = type;
    this.points = points;
    this.direction = direction;
    this.shipLength = type.getLength();
    this.firstCoord = firstCoord;
  }

  /**
   * @return the type of a ship
   */
  public ShipType getShipType() {
    return type;
  }

  /**
   * @return the coordinates of each ship
   */
  public List<Coord> getPoints() {

    return points;
  }

  /**
   * @return the coordinate of the first ship
   */
  public Coord getFirstCoord() {
    return firstCoord;
  }

  /**
   * @return the direction of a ship
   */
  public DirectionType getDirection() {
    return direction;
  }

  /**
   * @return the length of a ship
   */
  public int getShipLength() {
    return shipLength;
  }

}
