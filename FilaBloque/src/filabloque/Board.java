/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filabloque;

/**
 *
 * @author agustin
 */
public class Board {

    private Piece[][] board;
    private String designBoard;

    public Board(Piece[][] board, String designBoard) {
        this.board = board;
        this.designBoard = designBoard;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public String getDesignBoard() {
        return designBoard;
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }

    public void setDesignBoard(String designBoard) {
        this.designBoard = designBoard;
    }
    
    public void BuildBoard(String config) {
        System.out.println("Build Board");
    }
        
    public void showBoard() {
        System.out.println("Show Board");
    }

}
