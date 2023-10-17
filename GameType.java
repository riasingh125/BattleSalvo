package cs3500.pa03.Model;


/**
 * represents the type of game played
 */
public enum GameType {

  SINGLE("Single"),
  MULTI("Multi");
  String type;

  GameType(String type){
    this.type = type;
  }
}