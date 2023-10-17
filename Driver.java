package cs3500.pa04;

import cs3500.pa03.BattleSalvoController;
import cs3500.pa03.Model.AiPlayer;
import cs3500.pa03.Model.Player;
import java.io.IOException;
import java.net.Socket;

/**
 * // Runs both versions of BattleSalvo
 */
public class Driver {

  public static void main(String[] args) {
    BattleSalvoController bsc = new BattleSalvoController();
    if (args.length == 0) {
      bsc.setupGame();
    } else if (args.length >= 2) {
      try {
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        runClient(host, port);
      } catch (NumberFormatException | IOException e) {
        throw new IllegalArgumentException("Invalid port number");
      }
    } else {
      throw new IllegalArgumentException("Invalid number of arguments");
    }
  }

  /**
   * @param host
   * @param port
   * @throws IOException //runs the game connected to server
   */
  private static void runClient(String host, int port) throws IOException {
    Socket server = new Socket(host, port);
    BattleSalvoController bs = new BattleSalvoController();
    Player player = new AiPlayer(bs);
    ProxyController proxy = new ProxyController(server, player);
    proxy.run();
  }
}
