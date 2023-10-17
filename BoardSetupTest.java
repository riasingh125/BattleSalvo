import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa03.BoardSetup;
import java.io.InputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardSetupTest {

  BoardSetup bs;

  Readable read;
  Appendable append;

  @BeforeEach
  void setUp() {

  }


  @Test
  void setupBoard() {
//    ByteArrayInputStream input = new ByteArrayInputStream(" 7 \n 9 \n".getBytes());
//    System.setIn(input);
//    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(2048);
//    System.setOut(new PrintStream(outputStream));
//
//    Scanner scanner = new Scanner(System.in);
//    String str = scanner.next();
//    StringReader read = new StringReader(str);
//    StringBuilder append = new StringBuilder(str);
//
//    BoardSetup bs = new BoardSetup(read, append);
//    bs.setupBoard();
//    assertEquals(7, bs.getHeight());
//    assertEquals(6, bs.getWidth());
//    ByteArrayInputStream input = new ByteArrayInputStream("7\n6\n8\n".getBytes());
//    System.setIn(input);
//    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(2048);
//    System.setOut(new PrintStream(outputStream));
//    read = new StringReader(input.toString());
//    append = new StringBuilder(read.toString());
//    BoardSetup bs = new BoardSetup(read, append);
//    bs.setupBoard();
//    assertEquals(7, bs.getHeight());
//    assertEquals(6, bs.getWidth());

    InputStream restoreBackup = System.in;

  }

  @Test
  void getHeight() {
    bs = new BoardSetup(6,6, 4);
    assertEquals(6, bs.getHeight());
  }

  @Test
  void getWidth() {
    bs = new BoardSetup(6,6, 4);
    assertEquals(6, bs.getWidth());
  }

  @Test
  void getCarrier() {
    bs = new BoardSetup(6,6, 4);
    assertEquals(4, bs.getTotalShips());

  }

  @Test
  void getBattleship() {
  }

  @Test
  void getDestroyer() {
  }

  @Test
  void getSubmarine() {
  }

  @Test
  void setTotalShips() {
  }

  @Test
  void getTotalShips() {
  }
}