package cs3500.pa03.Model;

/**
 * Represents the result of a game
 */
public enum GameResult {
  WIN ("win"),
  LOSE ("lose"),
  TIE ("tie");
  String result;

  GameResult(String result) {
    this.result = result;
  }

}
