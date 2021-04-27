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
public class Piece {
    private int positionX;
    private char positionY;
    private char side;
    private String color;

    public Piece(int positionX, char positionY, char side, String color) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.side = side;
        this.color = color;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public char getPositionY() {
        return positionY;
    }

    public void setPositionY(char positionY) {
        this.positionY = positionY;
    }

    public char getSide() {
        return side;
    }

    public void setSide(char side) {
        this.side = side;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
}
