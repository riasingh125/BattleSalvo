package cs3500.pa03.Model;

import cs3500.pa03.BoardSetup;
import cs3500.pa03.View.BoardSetupView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents the shots taken by the user
 */
public class TakeShot {
  public BoardSetupView bsv = new BoardSetupView();
  Scanner sc = new Scanner(System.in);
  ArrayList<Coord> history = new ArrayList<>();

  /**
   * @return the list of shots taken by the user. This won't be tested, the bottom method will
   */
  public List<Coord> takeShots(BoardSetup bs) {
    bsv.printTakeShots(bs.getTotalShips());
    ArrayList<Coord> shots = new ArrayList<>();
    int invalidShotsCount = 0;
    while (shots.size() < bs.getTotalShips()) {
      String line = sc.nextLine().trim();
      if (line.isEmpty()) {
        break;
      }
      String[] coordinates = line.split(" ");
      if (coordinates.length != 2) {
        bsv.printLine();
        bsv.printInvalidShots(bs.getTotalShips());
        invalidShotsCount++;
        if (invalidShotsCount >= bs.getTotalShips()) {
          break;
        }
        continue;
      }
      try {
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);
        if (x < 0 || x >= bs.getHeight() || y < 0 || y >= bs.getWidth()) {
          bsv.printLine();
          bsv.printInvalidShots(bs.getTotalShips());
          invalidShotsCount++;
          if (invalidShotsCount >= bs.getTotalShips()) {
            break;
          }
          continue;
        }
        Coord shot = new Coord(x, y);
        shots.add(shot);
      } catch (NumberFormatException e) {
        bsv.printLine();
        bsv.printInvalidShots(bs.getTotalShips());
        invalidShotsCount++;
        if (invalidShotsCount >= bs.getTotalShips()) {
          break;
        }
      }
    }
    return shots;
  }

  /**
   * @return the list of shots taken by the AI.
   */
  public List<Coord> aiTakeShots(BoardSetup bs) {
    Random r = new Random();
    ArrayList<Coord> shots = new ArrayList<>();
    for (int i = 0; i < bs.getTotalShips(); i++) {
      int x = r.nextInt(bs.getHeight());
      int y = r.nextInt(bs.getWidth());
      Coord shot = new Coord(x, y);
      shots.add(shot);
    }
    return shots;
  }

  /**
   * @return the list of shots taken by the Server AI.
   */
  public List<Coord> ServerTakeShots(int height, int width, int totalShips) {
    Random r = new Random();
    ArrayList<Coord> shots = new ArrayList<>();

    while (shots.size() < totalShips) {
      int x = r.nextInt(width);
      int y = r.nextInt(height);
      Coord shot = new Coord(x, y);

      boolean isDuplicate = false;
      for (Coord coord : shots) {
        if (coord.getX() == x && coord.getY() == y) {
          isDuplicate = true;
          break;
        }
      }

      for (Coord coord : this.history) {
        if (coord.getX() == x && coord.getY() == y) {
          isDuplicate = true;
          break;
        }
      }

      if (!isDuplicate) {
        shots.add(shot);
        this.history.add(shot);
      }
    }
    return shots;
  }


  /**
   * Takes shots based on a given string input. - Only for testing purposes for takeShots method
   *
   * @param bs    the board setup
   * @param input the string input containing the shots (e.g., "0 0\n5 5\n7 8\n")
   * @return the list of shots
   */
  public List<Coord> takeShotsFromString(BoardSetup bs, String input) {
    bsv.printTakeShots(bs.getTotalShips());
    ArrayList<Coord> shots = new ArrayList<>();
    int invalidShotsCount = 0;
    String[] lines = input.trim().split("\n");
    for (String line : lines) {
      String[] coordinates = line.trim().split(" ");
      if (coordinates.length != 2) {
        bsv.printLine();
        bsv.printInvalidShots(bs.getTotalShips());
        invalidShotsCount++;
        if (invalidShotsCount >= bs.getTotalShips()) {
          break;
        }
        continue;
      }
      try {
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);
        if (x < 0 || x >= bs.getHeight() || y < 0 || y >= bs.getWidth()) {
          bsv.printLine();
          bsv.printInvalidShots(bs.getTotalShips());
          invalidShotsCount++;
          if (invalidShotsCount >= bs.getTotalShips()) {
            break;
          }
          continue;
        }
        Coord shot = new Coord(x, y);
        shots.add(shot);
      } catch (NumberFormatException e) {
        bsv.printLine();
        bsv.printInvalidShots(bs.getTotalShips());
        invalidShotsCount++;
        if (invalidShotsCount >= bs.getTotalShips()) {
          break;
        }
      }
    }
    return shots;
  }
}

