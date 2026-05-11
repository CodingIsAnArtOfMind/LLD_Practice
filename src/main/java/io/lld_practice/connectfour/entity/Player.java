package io.lld_practice.connectfour.entity;

public class Player {
    String playerName;
    GridColor playerColor;

    public Player(String playerName, GridColor playerColor) {
        this.playerName = playerName;
        this.playerColor = playerColor;
    }
    public String getPlayerName() {return playerName;}

    public GridColor getPlayerColor() {return playerColor;}
}
