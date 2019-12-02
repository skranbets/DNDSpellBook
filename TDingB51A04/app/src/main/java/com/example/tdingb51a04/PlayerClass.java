package com.example.tdingb51a04;

public class PlayerClass {
    private int playerClassID;
    private String playerClassName;

    public PlayerClass(int playerClassID, String playerClassName) {
        this.playerClassID = playerClassID;
        this.playerClassName = playerClassName;
    }

    public void setPlayerClassID(int playerClassID) {
        this.playerClassID = playerClassID;
    }

    public int getPlayerClassID() {
        return playerClassID;
    }

    public void setPlayerClassName(String playerClassName) {
        this.playerClassName = playerClassName;
    }

    public String getPlayerClassName() {
        return playerClassName;
    }
}
