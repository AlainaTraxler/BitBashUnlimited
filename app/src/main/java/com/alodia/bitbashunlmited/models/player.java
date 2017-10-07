package com.alodia.bitbashunlmited.models;

import java.util.HashMap;

/**
 * Created by Alaina Traxler on 10/7/2017.
 */

public class player {
    public String handle;
    public String email;
    public HashMap<String, String> bashes;
    public int bashWins;
    public int gameWins;
    public String userId;

    public player(String handle, String email) {
        this.handle = handle;
        this.email = email;
        bashWins = 0;
        gameWins = 0;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<String, String> getBashes() {
        return bashes;
    }

    public void setBashes(HashMap<String, String> bashes) {
        this.bashes = bashes;
    }

    public int getBashWins() {
        return bashWins;
    }

    public void setBashWins(int bashWins) {
        this.bashWins = bashWins;
    }

    public int getGameWins() {
        return gameWins;
    }

    public void setGameWins(int gameWins) {
        this.gameWins = gameWins;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
