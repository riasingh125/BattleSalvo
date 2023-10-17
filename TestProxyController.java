package cs3500.pa04;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa03.BattleSalvoController;
import cs3500.pa04.JSON.EndgameJson;
import cs3500.pa04.JSON.JsonUtils;
import cs3500.pa04.JSON.MessageJson;
import cs3500.pa04.JSON.JoinJson;
import cs3500.pa04.JSON.ReportDamageJson;
import cs3500.pa04.JSON.SetupJson;
import cs3500.pa04.JSON.TakeShotsJson;
import cs3500.pa03.Model.AiPlayer;
import cs3500.pa03.Model.Coord;
import cs3500.pa03.Model.DirectionType;
import cs3500.pa03.Model.GameResult;
import cs3500.pa03.Model.GameType;
import cs3500.pa03.Model.Player;
import cs3500.pa04.JSON.SucessfulHitsJson;
import cs3500.pa03.Model.Ship;
import cs3500.pa03.Model.ShipType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class TestProxyController {
  private ProxyController pc;
  private Player ai;
  private BattleSalvoController bsc;

  private ByteArrayOutputStream tester;

  @BeforeEach
  public void setup() {
    bsc = new BattleSalvoController();
    ai = new AiPlayer(bsc);
    this.tester = new ByteArrayOutputStream(2048);
    assertEquals("", logToString());
  }

  @Test
  public void testHandleJoin() {
    JoinJson joinJson = new JoinJson("riasingh125", GameType.SINGLE.toString());
    JsonNode sampleMessage = createSampleMessage("join", joinJson);
    Mocket mockSocket = new Mocket(this.tester, List.of(sampleMessage.toString()));

    try {
      this.pc = new ProxyController(mockSocket, ai);
    } catch (IOException e) {
      fail("proxy controller couldn't be created");
    }

    this.pc.run();

    String expectedOutcome =
        "{\"method-name\":\"join\",\"arguments\":{\"name\":\"A-goofyGoober\",\"game-type\":\"SINGLE\"}}";
    assertEquals(expectedOutcome.stripTrailing(), logToString().stripTrailing());

  }

  @Test
  public void testHandleTakeShots() {
    Coord coord1 = new Coord(1, 2);
    Coord coord2 = new Coord(2, 1);
    TakeShotsJson shotsJson = new TakeShotsJson(List.of(coord1, coord2));
    JsonNode sampleMessage = createSampleMessage("take-shots", shotsJson);
    Mocket mockSocket = new Mocket(this.tester, List.of());
    try {
      this.pc = new ProxyController(mockSocket, ai);
    } catch (IOException e) {
      fail("proxy controller couldn't be created");
    }
    pc.run();
    String expectedOutcome =
        "{\"method-name\":\"take-shots\",\"arguments\":{\"coordinates\":[{\"x\":1,\"y\":2},{\"x\":2,\"y\":1}]}}";

    assertEquals(expectedOutcome.stripLeading().stripLeading(), sampleMessage.toString());
  }


  @Test
  public void handleEndGameTestTie() {
    EndgameJson endJson = new EndgameJson(GameResult.TIE, "test");
    JsonNode sampleMessage = createSampleMessage("end-game", endJson);
    Mocket mockSocket = new Mocket(this.tester, List.of(sampleMessage.toString()));

    try {
      this.pc = new ProxyController(mockSocket, ai);
    } catch (IOException e) {
      fail("couldn't make proxycontroller");
    }
    this.pc.run();
    String expected = "";
    assertEquals(expected, logToString());
  }

  @Test
  public void handleEndGameTestWin() {
    EndgameJson endJson = new EndgameJson(GameResult.WIN, "test");
    JsonNode sampleMessage = createSampleMessage("end-game", endJson);
    Mocket mockSocket = new Mocket(this.tester, List.of(sampleMessage.toString()));

    try {
      this.pc = new ProxyController(mockSocket, ai);
    } catch (IOException e) {
      fail("couldn't make proxycontroller");
    }
    this.pc.run();
    String expected = "";
    assertEquals(expected, logToString());
  }

  @Test
  public void handleEndGameTestLose() {
    EndgameJson endJson = new EndgameJson(GameResult.LOSE, "test");
    JsonNode sampleMessage = createSampleMessage("end-game", endJson);
    Mocket mockSocket = new Mocket(this.tester, List.of(sampleMessage.toString()));

    try {
      this.pc = new ProxyController(mockSocket, ai);
    } catch (IOException e) {
      fail("couldn't make proxycontroller");
    }
    this.pc.run();
    String expected = "";
    assertEquals(expected, logToString());
  }


  @Test
  public void testReportDamage() {
    Coord coord1 = new Coord(1, 2);
    Coord coord2 = new Coord(2, 1);
    ReportDamageJson report = new ReportDamageJson(List.of(coord2, coord1));
    JsonNode sampleMessage = createSampleMessage("report-damage", report);
    Mocket mocket = new Mocket(tester, List.of(sampleMessage.toString()));
    try {
      this.pc = new ProxyController(mocket, ai);
    } catch (IOException e) {
      fail("couldn't make proxycontroller");
    }
    Coord coord3 = new Coord(1, 2);
    Coord coord4 = new Coord(2, 1);
    Coord coord5 = new Coord(1, 3);
    Coord coord6 = new Coord(3, 1);
    Ship ship1 =
        new Ship(ShipType.BATTLESHIP, List.of(coord3, coord4), DirectionType.HORIZONTAL, coord3);
    Ship ship2 =
        new Ship(ShipType.SUBMARINE, List.of(coord5, coord6), DirectionType.VERTICAL, coord5);
    pc.fleet = List.of(ship1, ship2);
    this.pc.run();
    String expected =
        "{\"method-name\":\"report-damage\",\"arguments\":{\"coordinates\":[{\"x\":1,\"y\":2}]}}";
    assertEquals(expected.stripTrailing(), logToString().stripTrailing());
  }

  @Test
  public void handleSuccessfulHits() {
    Coord coord1 = new Coord(1, 2);
    Coord coord2 = new Coord(2, 1);
    SucessfulHitsJson sucess = new SucessfulHitsJson("");
    JsonNode sampleMessage = createSampleMessage("successful-hits", sucess);
    Mocket mocket = new Mocket(tester, List.of(coord1.toString(), coord2.toString()));
    try {
      this.pc = new ProxyController(mocket, ai);
    } catch (IOException e) {
      fail("couldn't make proxycontroller");
    }
    this.pc.run();
    String expected = "{\"method-name\":\"successful-hits\",\"arguments\":{\"arguments: \":\"\"}}";
    assertEquals(expected.stripLeading().stripTrailing(), sampleMessage.toString());
  }

  @Test
  public void handleSetUp() {
    BattleSalvoController bsc2 = new BattleSalvoController();
    AiPlayer aip2 = new AiPlayer(bsc2);
    Map<ShipType, Integer> sampleFleetSpec = new LinkedHashMap<>();
    sampleFleetSpec.put(ShipType.SUBMARINE, 1);
    sampleFleetSpec.put(ShipType.CARRIER, 1);
    sampleFleetSpec.put(ShipType.BATTLESHIP, 1);
    sampleFleetSpec.put(ShipType.DESTROYER, 1);

    SetupJson setupJson = new SetupJson(10, 10, sampleFleetSpec);
    JsonNode sampleMessage = createSampleMessage("setup", setupJson);
    Mocket mocket = new Mocket(tester, List.of(sampleMessage.toString()));
    try {
      this.pc = new ProxyController(mocket, aip2);
    } catch (IOException e) {
      fail("couldn't make proxycontroller");
    }
    this.pc.run();
    String expected =
        "{\"method-name\":\"setup\",\"arguments\":{\"width\":10,\"height\":10,\"fleet-spec\":{\"SUBMARINE\":1,\"CARRIER\":1,\"BATTLESHIP\":1,\"DESTROYER\":1}}}";

    assertEquals(expected.stripLeading().stripTrailing(), sampleMessage.toString().stripTrailing());
  }

  /**
   * Converts the ByteArrayOutputStream log to a string in UTF_8 format
   *
   * @return String representing the current log buffer
   */
  private String logToString() {
    return tester.toString();
  }

  /**
   * Create a MessageJson for some name and arguments.
   *
   * @param messageName   name of the type of message; "hint" or "win"
   * @param messageObject object to embed in a message json
   * @return a MessageJson for the object
   */
  private JsonNode createSampleMessage(String messageName, Record messageObject) {
    MessageJson messageJson =
        new MessageJson(messageName, JsonUtils.serializeRecord(messageObject));
    return JsonUtils.serializeRecord(messageJson);
  }
}