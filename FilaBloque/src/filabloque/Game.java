/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filabloque;

/**
 *
 * @author joaquin
 */
public class Game {
    private Player player1;
    private Player player2;
    private Board board;
    private String turn;
    private int playTurn;
    private boolean[][] completedDesigns;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = null;
        this.turn = "";
        this.playTurn = 1;
        this.completedDesigns = null;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public int getPlayTurn() {
        return playTurn;
    }

    public void setPlayTurn(int playTurn) {
        this.playTurn = playTurn;
    }

    public boolean[][] getCompletedDesigns() {
        return completedDesigns;
    }

    public void setCompletedDesigns(boolean[][] completedDesigns) {
        this.completedDesigns = completedDesigns;
    }
    
    public void startGame() {
        System.out.println("Juego iniciado");
    }
    
    public void buildBoard(String config){
        System.out.println("Se agregan las piezas al board");
    }
    
    public void showBoard(){
        System.out.println("Se muestra el board");
    }
    
    public void playTurn(){
        System.out.println("Se juega el turno");
    }
    
    public String readPlay(){
        return "M B1 A2";
    }
    
    public void makePlay(String play){
        System.out.println("Se realiza la juagada");
    }
    
    private boolean isValidPlay(String play) {
          return false;
    }
    
    public void checkTurn() {
        System.out.println("Se verifica la jugada y devuelve si gano");
    }
}
