import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa03.Model.Validation;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidationTest {
  Validation v;

  /**
   * Set up the Validation object.
   */
  @BeforeEach
  public void setup() {
    v = new Validation();
  }

  @Test
  void validateDimensions() {
    String input1 = "9";
    ByteArrayInputStream inputStream = new ByteArrayInputStream(input1.getBytes());
    System.setIn(inputStream);
    Scanner scanner = new Scanner(System.in);

    int result = v.validateDimensions(scanner);
    assertTrue(result >= 6 && result <=15);
  }

//  @Test
//  public void testInvalidDimension(){
//    String input = "50";
//    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
//    System.setIn(inputStream);
//    Scanner scanner = new Scanner(System.in);
//    Validation validator = new Validation();
//    assertEquals("Uh Oh! You've entered invalid dimensions. "
//        + "Please remember that the height and width\nof the game must be in the range (6, 15), "
//        + "inclusive. Try again! ", validator.validateDimensions(scanner));
//
//  }

  @Test
  void validateNumberTest() {
    String input = "11";
    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream);
    Scanner scanner = new Scanner(System.in);
    Validation validate = new Validation();
    int number = validate.validateNumber(scanner);

    assertEquals(11, number);

  }

  @Test
  void validateFleet() {
    String input = "1\n1\n1\n1\n";
    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream);
    Scanner scanner = new Scanner(System.in);

    int initialCarrier = 1;
    int initialBattleship = 1;
    int initialDestroyer = 1;
    int initialSubmarine = 1;

    v.validateFleet(8, initialCarrier, initialBattleship, initialDestroyer, initialSubmarine);

    // Assert that the fleet values have been updated correctly
    assertEquals(1, initialCarrier);
    assertEquals(1, initialBattleship);
    assertEquals(1, initialDestroyer);
    assertEquals(1, initialSubmarine);

  }
}