package cs3500.pa04;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.util.List;

/**
 * Mock a Socket to simulate behaviors of ProxyControllers being connected to a server.
 */
public class Mocket extends Socket {

  private final InputStream testInputs;

  private final ByteArrayOutputStream testlog;

  /**
   * @param testlog what the server has received from the client
   * @param toSend  what the server will send to the client
   */
  public Mocket(ByteArrayOutputStream testlog, List<String> toSend) {
    this.testlog = testlog;
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    for (String message : toSend) {
      pw.println(message);
    }
    this.testInputs = new ByteArrayInputStream(sw.toString().getBytes());
  }

  /**
   * @return InputStream -> representing the test inputs for testing
   */
  public InputStream getInputStream() {
    return this.testInputs;
  }

  /**
   * @return OutputStream -> representing the test ouputs
   */
  public OutputStream getOutputStream() {
    return this.testlog;
  }
}
