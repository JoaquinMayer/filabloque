/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filabloque;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author agustin
 */
public class Board {

    private int[][] board;
    private int designBoard;
    private int config;

    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_RED = "\u001B[31m";
    private final String ANSI_BLUE = "\u001B[34m";

    public Board(int config, int designBoard) {
        this.designBoard = designBoard;
        this.config = config;
        this.buildBoard();
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }
    
    private void setBoardPosition(int x, int y, int value) {
        this.board[x][y] = value;
    }

    public int getDesignBoard() {
        return designBoard;
    }

    public void setDesignBoard(int designBoard) {
        this.designBoard = designBoard;
    }

    public int getConfig() {
        return config;
    }

    public void setConfig(int config) {
        this.config = config;
    }

    // validate if the actual & next positions are inside the board & the movement is valid
    private boolean isValidMovement(String from, String to, int rowFrom, int columnFrom, int rowTo, int columnTo) {
        return (!from.equals(to) && rowFrom >= 0 && rowFrom < 4 && columnFrom >= 0 && columnFrom < 4 && rowTo >= 0 && rowTo < 4 && columnTo >= 0 && columnTo < 4)
                && (rowTo == rowFrom || rowTo == rowFrom - 1 || rowTo == rowFrom + 1) && (columnTo == columnFrom || columnTo == columnFrom - 1 || columnTo == columnFrom + 1);
    }

    public boolean isPlayerPiece(int x, int y, int player) {
        if (player == 1) {
            return this.getBoard()[x][y] == 1 || this.getBoard()[x][y] == 2;
        } else {
            return this.getBoard()[x][y] == 3 || this.getBoard()[x][y] == 4;
        }
    }

    private void buildBoard() {
        int[][] actualBoard = {{3, 0, 2, 3}, {0, 0, 0, 0}, {0, 4, 3, 0}, {1, 2, 1, 0}};

        // custom board configuration
        if (this.getConfig() == 1) {
            int row = 0;
            int column = 0;
            List<Integer> list = new ArrayList<>(Collections.nCopies(8, 0));

            for (int i = 0; i < 4; i++) {
                list.add((int) Math.floor(Math.random() * (2) + 1));
            }

            for (int i = 0; i < 4; i++) {
                list.add((int) Math.floor(Math.random() * (2) + 3));
            }

            Collections.shuffle(list);

            for (int number : list) {
                actualBoard[row][column] = number;

                if (row == 4) {
                    row = 0;
                    column = 0;
                }
                if (column == 3) {
                    column = 0;
                    row++;
                } else {
                    column++;
                }
            }
        }

        this.setBoard(actualBoard);
    }

    public void showBoard() {
        String[] letters = {"A", "B", "C", "D"};

        // default board size
        if (this.getDesignBoard() == 1) {
            this.showSmallBoard(letters);
        } else {
            this.showBigBoard(letters);
        }
    }

    private void showSmallBoard(String[] letters) {
        System.out.println("   1 2 3 4  ");
        System.out.println("  +-+-+-+-+");

        for (int i = 0; i < 4; i++) {
            System.out.print(letters[i] + " ");

            for (int j = 0; j < this.board.length; j++) {
                System.out.print("|");

                switch (this.board[i][j]) {
                    case 1:
                        System.out.print(ANSI_RED + "F" + ANSI_RESET);
                        break;
                    case 2:
                        System.out.print(ANSI_RED + "D" + ANSI_RESET);
                        break;
                    case 3:
                        System.out.print(ANSI_BLUE + "F" + ANSI_RESET);
                        break;
                    case 4:
                        System.out.print(ANSI_BLUE + "D" + ANSI_RESET);
                        break;
                    default:
                        System.out.print(" ");
                }
            }

            System.out.print("|");
            System.out.println("");
            System.out.println("  +-+-+-+-+");
        }
    }

    private void showBigBoard(String[] letters) {
        System.out.println("     1     2     3     4  ");
        System.out.println("  +-----+-----+-----+-----+");

        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 5; k++) {
                if (k == 2) {
                    System.out.print(letters[i] + " ");
                } else {
                    System.out.print("  ");
                }

                for (int j = 0; j < this.board.length; j++) {
                    System.out.print("|");
                    switch (this.board[i][j]) {
                        case 1 -> {
                            switch (k) {
                                case 0 ->
                                    System.out.print(ANSI_RED + "FFFFF" + ANSI_RESET);
                                case 1 ->
                                    System.out.print(ANSI_RED + "F    " + ANSI_RESET);
                                case 2 ->
                                    System.out.print(ANSI_RED + "FFF  " + ANSI_RESET);
                                case 3 ->
                                    System.out.print(ANSI_RED + "F    " + ANSI_RESET);
                                case 4 ->
                                    System.out.print(ANSI_RED + "F    " + ANSI_RESET);
                            }
                        }

                        case 2 -> {
                            switch (k) {
                                case 0 ->
                                    System.out.print(ANSI_RED + "DDDD " + ANSI_RESET);
                                case 1 ->
                                    System.out.print(ANSI_RED + "D   D" + ANSI_RESET);
                                case 2 ->
                                    System.out.print(ANSI_RED + "D   D" + ANSI_RESET);
                                case 3 ->
                                    System.out.print(ANSI_RED + "D   D" + ANSI_RESET);
                                case 4 ->
                                    System.out.print(ANSI_RED + "DDDD " + ANSI_RESET);
                            }
                        }

                        case 3 -> {
                            switch (k) {
                                case 0 ->
                                    System.out.print(ANSI_BLUE + "FFFFF" + ANSI_RESET);
                                case 1 ->
                                    System.out.print(ANSI_BLUE + "F    " + ANSI_RESET);
                                case 2 ->
                                    System.out.print(ANSI_BLUE + "FFF  " + ANSI_RESET);
                                case 3 ->
                                    System.out.print(ANSI_BLUE + "F    " + ANSI_RESET);
                                case 4 ->
                                    System.out.print(ANSI_BLUE + "F    " + ANSI_RESET);
                            }
                        }

                        case 4 -> {
                            switch (k) {
                                case 0 ->
                                    System.out.print(ANSI_BLUE + "DDDD " + ANSI_RESET);
                                case 1 ->
                                    System.out.print(ANSI_BLUE + "D   D" + ANSI_RESET);
                                case 2 ->
                                    System.out.print(ANSI_BLUE + "D   D" + ANSI_RESET);
                                case 3 ->
                                    System.out.print(ANSI_BLUE + "D   D" + ANSI_RESET);
                                case 4 ->
                                    System.out.print(ANSI_BLUE + "DDDD " + ANSI_RESET);
                            }
                        }

                        default -> {
                            switch (k) {
                                case 0 ->
                                    System.out.print(ANSI_RED + "     " + ANSI_RESET);
                                case 1 ->
                                    System.out.print(ANSI_RED + "     " + ANSI_RESET);
                                case 2 ->
                                    System.out.print(ANSI_RED + "     " + ANSI_RESET);
                                case 3 ->
                                    System.out.print(ANSI_RED + "     " + ANSI_RESET);
                                case 4 ->
                                    System.out.print(ANSI_RED + "     " + ANSI_RESET);
                            }
                        }

                    }
                }
                System.out.println("|");
            }
            System.out.println("  +-----+-----+-----+-----+");
        }
    }

    public Boolean makeMove(String play, int turn, int player) {
        Boolean isValid = false;
        String action = play.split(" ")[0];

        if (action.equals("M")) {
            isValid = this.movePiece(play, turn, player);
        } else {
            isValid = this.invertPiece(play, turn, player);
        }

        return isValid;
    }

    private boolean movePiece(String play, int turn, int player) {
        Boolean isValid = false;
        String from = play.split(" ")[1];
        String letterFrom = from.split("")[0];
        int rowFrom = positionLetter(letterFrom);
        int columnFrom = Integer.parseInt(from.split("")[1]) - 1;

        String to = play.split(" ")[2];
        String letterTo = to.split("")[0];
        int rowTo = positionLetter(letterTo);
        int columnTo = Integer.parseInt(to.split("")[1]) - 1;

        if (this.isValidMovement(from, to, rowFrom, columnFrom, rowTo, columnTo)) {
            if (turn == 1) {
                if (player == 1) {
                    if (this.isPlayerPiece(rowFrom, columnFrom, 1)) {
                        int tmp = this.getBoard()[rowFrom][columnFrom];
                        this.setBoardPosition(rowFrom, columnFrom, this.getBoard()[rowTo][columnTo]);
                        this.setBoardPosition(rowTo, columnTo, tmp);
                        isValid = true;
                    }
                } else {
                    if (this.isPlayerPiece(rowFrom, columnFrom, 2)) {
                        int tmp = this.getBoard()[rowFrom][columnFrom];
                        this.setBoardPosition(rowFrom, columnFrom, this.getBoard()[rowTo][columnTo]);
                        this.setBoardPosition(rowTo, columnTo, tmp);
                        isValid = true;
                    }
                }
            } else {
                int tmp = this.getBoard()[rowFrom][columnFrom];
                this.setBoardPosition(rowFrom, columnFrom, this.getBoard()[rowTo][columnTo]);
                this.setBoardPosition(rowTo, columnTo, tmp);
                isValid = true;
            }
        }

        return isValid;
    }

    private boolean invertPiece(String play, int turn, int player) {
        Boolean isValid = false;
        String invert = play.split(" ")[1];
        String letter = invert.split("")[0];
        int row = positionLetter(letter);
        int column = Integer.parseInt(invert.split("")[1]) - 1;

        if (turn == 1) {
            if (player == 1) {
                if (this.isPlayerPiece(row, column, 1)) {
                    if (this.getBoard()[row][column] == 1) {
                        this.setBoardPosition(row, column, 2);
                    } else {
                        this.setBoardPosition(row, column, 1);
                    }
                    isValid = true;
                }
            } else {
                if (this.isPlayerPiece(row, column, 2)) {
                    if (this.getBoard()[row][column] == 3) {
                        this.setBoardPosition(row, column, 4);
                    } else {
                        this.setBoardPosition(row, column, 3);
                    }
                    isValid = true;
                }
            }
        } else {
            if (this.getBoard()[row][column] != 0) {
                if (this.isPlayerPiece(row, column, 1)) {
                    if (this.getBoard()[row][column] == 1) {
                        this.setBoardPosition(row, column, 2);
                    } else {
                        this.setBoardPosition(row, column, 1);
                    }
                } else if (this.isPlayerPiece(row, column, 2)) {
                    if (this.getBoard()[row][column] == 3) {
                        this.setBoardPosition(row, column, 4);
                    } else {
                        this.setBoardPosition(row, column, 3);
                    }
                }
                isValid = true;
            }
        }

        return isValid;
    }

    private int positionLetter(String letter) {
        int row = 0;

        switch (letter) {
            case "A" ->
                row = 0;
            case "B" ->
                row = 1;
            case "C" ->
                row = 2;
            case "D" ->
                row = 3;
        }
        return row;
    }
}
