package cs3500.pa04;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cs3500.pa04.JSON.FleetJson;
import cs3500.pa04.JSON.JoinJson;
import cs3500.pa04.JSON.JsonUtils;
import cs3500.pa04.JSON.MessageJson;
import cs3500.pa04.JSON.SetupJson;
import cs3500.pa04.JSON.ShipJson;
import cs3500.pa04.JSON.VolleyJson;
import cs3500.pa03.Model.Coord;
import cs3500.pa03.Model.GameBoard;
import cs3500.pa03.Model.GameResult;
import cs3500.pa03.Model.HitOrMiss;
import cs3500.pa03.Model.Ship;
import cs3500.pa03.Model.ShipType;
import cs3500.pa03.Model.Player;
import cs3500.pa03.View.PrintBoard;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents communication between the server and client
 */
public class ProxyController {

  private final Socket server;
  private final InputStream in;
  private final PrintStream out;
  private final Player player;
  private final ObjectMapper mapper = new ObjectMapper();
  private static final JsonNode VOID_RESPONSE =
      new ObjectMapper().getNodeFactory().textNode("void");

  HitOrMiss hm = new HitOrMiss();
  int width;
  int height;
  List<Ship> fleet;
  PrintBoard pb = new PrintBoard();

  public ProxyController(Socket server, Player player) throws IOException {
    this.server = server;
    this.in = server.getInputStream();
    this.out = new PrintStream(server.getOutputStream());
    this.player = player;
    this.fleet = fleet;
  }

  /**
   * overall methods that runs everything
   */
  public void run() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.in);

      while (!this.server.isClosed()) {
        MessageJson message = parser.readValueAs(MessageJson.class);
        delegateMessage(message);
      }
    } catch (IOException e) {
    }
  }

  /**
   * @param methodName
   * @throws JsonProcessingException //delegates to other methods based on the method name given
   */
  private void delegateMessage(MessageJson methodName)
      throws JsonProcessingException {
    String name = methodName.method();
    JsonNode args = methodName.args();

    if (name.equals("join")) {
      handleJoin();
    } else if (name.equals("setup")) {
      handleSetup(mapper.convertValue(args, SetupJson.class));
    } else if (name.equals("take-shots")) {
      handleTakingShots();
    } else if (name.equals("report-damage")) {
      handleReportingDamage(args);
    } else if (name.equals("successful-hits")) {
      handleSuccessfulHits(args);
    } else if (name.equals("end-game")) {
      handleEndingGame(args);
    } else {
      throw new IllegalArgumentException("name does not exist");
    }
  }

  /**
   * @throws JsonProcessingException //represents the Join method communication
   */
  private void handleJoin() throws JsonProcessingException {
    JoinJson joinGame = new JoinJson(player.name(), "SINGLE");
    JsonNode joinServer = JsonUtils.serializeRecord(joinGame);
    MessageJson join = new MessageJson("join", joinServer);
    JsonNode joins = JsonUtils.serializeRecord(join);
    System.out.println(mapper.writeValueAsString(joins)); // delete all the exceptions
    this.out.println(joins);
  }

  /**
   * @param args -> arguments server asks for
   * @throws JsonProcessingException //represents SetUp method communcation
   */
  private void handleSetup(SetupJson args) throws JsonProcessingException {
    this.width = args.width();
    this.height = args.height();
    System.out.println(width);
    System.out.println(height);
    Map<ShipType, Integer> fleetSpec = args.fleetSpec();
    System.out.println(fleetSpec.toString());

    this.fleet = player.setup(this.height, this.width, fleetSpec);
    GameBoard aiBoard = new GameBoard(this.height, this.width);
    for (Ship ship : this.fleet) {
      aiBoard.placeShip(ship.getShipType(), ship.getDirection());
    }

    pb.printBoard(aiBoard, "Board:");

    List<Ship> aiShipList = aiBoard.getShipsPlace();
    List<ShipJson> fleetJson = new ArrayList<>();
    for (Ship ship : aiBoard.getShipsPlace()) {
      fleetJson.add(new ShipJson(ship.getFirstCoord(), ship.getShipLength(), ship.getDirection()));
    }
    this.fleet = aiShipList;
    FleetJson fleetJsonObj = new FleetJson(fleetJson);
    JsonNode fleetServer = JsonUtils.serializeRecord(fleetJsonObj);
    MessageJson setup = new MessageJson("setup", fleetServer);
    JsonNode setups = JsonUtils.serializeRecord(setup);
    System.out.println(mapper.writeValueAsString(setups));
    String finalSetup = mapper.writeValueAsString(setups);
    this.out.println(finalSetup);
  }

  /**
   * @throws JsonProcessingException //represents TakeShots method communication
   */
  private void handleTakingShots() throws JsonProcessingException {
    VolleyJson shots = new VolleyJson(player.takeShots());
    JsonNode shotsServer = JsonUtils.serializeRecord(shots);
    MessageJson shotsMessage = new MessageJson("take-shots", shotsServer);
    JsonNode shotsMessages = JsonUtils.serializeRecord(shotsMessage);
   // System.out.println(mapper.writeValueAsString(shotsMessages));
    String finalShots = mapper.writeValueAsString(shotsMessages);
    this.out.println(finalShots);
  }

  /**
   * @param args arguments server asks for
   * @throws JsonProcessingException // represents report damage method communication
   */
  private void handleReportingDamage(JsonNode args) throws JsonProcessingException {
    System.out.println("SLAYYYY");
    JsonNode coords = args.get("coordinates");
    List<Coord> targetCoords = new ArrayList<>();
    if (coords.isArray()) {
      for (JsonNode coord : coords) {
        int x = coord.get("x").asInt();
        int y = coord.get("y").asInt();
        Coord targetCoord = new Coord(x, y);
        targetCoords.add(targetCoord);
      }
    }
    List<Coord> shotsTaken = player.reportDamage(hm.AiServerBoardHits(targetCoords, this.fleet));
    ObjectNode damage = mapper.createObjectNode();
    ArrayNode damageArray = damage.putArray("coordinates");
    for (Coord coord : shotsTaken) {
      damageArray.add(JsonUtils.serializeRecord(coord));
    }
    MessageJson damageMessage = new MessageJson("report-damage", damage);
    String finalDamage = mapper.writeValueAsString(damageMessage);
    System.out.println(finalDamage.toCharArray());
    this.out.println(finalDamage);
  }

  /**
   * @param args arguments server asks for
   * @throws JsonProcessingException // represents successfulHits method communication
   */
  private void handleSuccessfulHits(JsonNode args) throws JsonProcessingException {
    JsonNode sHits = args.get("coordinates");
    List<Coord> successfulHits = new ArrayList<>();
    if (sHits.isArray()) {
      for (JsonNode coord : sHits) {
        int x = coord.get("x").asInt();
        int y = coord.get("y").asInt();
        Coord targetCoord = new Coord(x, y);
        successfulHits.add(targetCoord);
      }
    }
    System.out.println("HERE");
    player.successfulHits(successfulHits);
    MessageJson successfulHitsMessage = new MessageJson("successful-hits", VOID_RESPONSE);
    String finalSuccessfulHits = mapper.writeValueAsString(successfulHitsMessage);
    System.out.println("RIGHT" + finalSuccessfulHits);
    this.out.println(finalSuccessfulHits);

  }


  /**
   * @param args arguments server asks for
   * @throws JsonProcessingException // represents endgame method communication
   */
  private void handleEndingGame(JsonNode args) {
    String JsonResult = args.get("result").asText();
    String reason = args.get("reason").asText();
    GameResult result = null;
    if (JsonResult.equals("WON")) {
      result = GameResult.WIN;
    } else if (JsonResult.equals("LOST")) {
      result = GameResult.LOSE;
    } else if (JsonResult.equals("TIE")) {
      result = GameResult.TIE;
    }
    player.endGame(result, reason);
  }


}
