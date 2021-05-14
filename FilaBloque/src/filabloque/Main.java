/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filabloque;

import java.util.ArrayList;

/**
 *
 * @author joaquin
 */
public class Main {
    private ArrayList<Player> players;
    private Game game;

    public Main(ArrayList<Player> players, Game game) {
        this.players = players;
        this.game = game;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Game getGame() {
        return game;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    
    public void startSystem() {
        System.out.println("start System");
    }
    
    public void newPlayer() {
        System.out.println("new Player");
    }
    
    public void newGame() {
        System.out.println("new Game");
    }
    
    public void showRanking(String order) {
        System.out.println("show ranking");
    }
    
    public boolean isValidAlias(String alias) {
        return true;
    }
}
