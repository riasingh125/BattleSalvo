import cs3500.pa03.BattleSalvoController;
import org.junit.jupiter.api.BeforeEach;

class BattleSalvoControllerTest {
 private BattleSalvoController bsc;

  private Readable read;
  private Appendable appenda;

  @BeforeEach
  void setUp() {
    bsc = new BattleSalvoController(appenda, read);
    //literally just run game

  }

//  @Test
//  void setupGame() {
////    assertEquals("", this.appenda.toString());
////    StringBuilder sb = new StringBuilder();
////    BoardSetup boardSetup = new BoardSetup(new StringReader("hi"), sb.append("hi"));
////    boolean testerBool = true;
////    bsc.setupGame(testerBool);
////    System.out.println(this.appenda);
//  }



//  @Test
//  void getBoardSetup() {
////    assertEquals(bsc.bs, bsc.getBoardSetup());
//  }
}