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
    private String referencesPieces[] = {"", "Frente Rojo", "Dorso Rojo", "Frente Azul", "Dorso Azul"};

    private String ANSI_RESET = "\u001B[0m";
    private String ANSI_RED = "\u001B[31m";
    private String ANSI_BLUE = "\u001B[34m";

    public Board(int designBoard, int config) {
        this.designBoard = designBoard;
        this.config = config;
        this.buildBoard(config);
    }

    public int[][] getBoard() {
        return board;
    }

    public int getDesignBoard() {
        return designBoard;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void setDesignBoard(int designBoard) {
        this.designBoard = designBoard;
    }

    private Boolean makeMove(String play, int turn, int player) {
        Boolean isValid = false;
        String action = play.split(" ")[0];
        if (action.equals("M")) {
            String from = play.split(" ")[1];
            String letterFrom = from.split("")[0];
            int rowFrom = positionLetter(letterFrom);
            int columnFrom = Integer.parseInt(from.split("")[1]) - 1;
            String to = play.split(" ")[2];
            String letterTo = to.split("")[0];
            int rowTo = positionLetter(letterTo);
            int columnTo = Integer.parseInt(to.split("")[1]) - 1;

            if (!from.equals(to) && rowFrom >= 0 && rowFrom < 4 && columnFrom >= 0 && columnFrom < 4 && rowTo >= 0 && rowTo < 4 && columnTo >= 0 && columnTo < 4) {
                if ((rowTo == rowFrom || rowTo == rowFrom - 1 || rowTo == rowFrom + 1) && (columnTo == columnFrom || columnTo == columnFrom - 1 || columnTo == columnFrom + 1)) {
                    if (turn == 1) {
                        if (player == 1) {
                            if (this.board[rowFrom][columnFrom] == 1 || this.board[rowFrom][columnFrom] == 2) {
                                int tmp = this.board[rowFrom][columnFrom];
                                this.board[rowFrom][columnFrom] = this.board[rowTo][columnTo];
                                this.board[rowTo][columnTo] = tmp;
                                isValid = true;
                            }
                        } else {
                            if (this.board[rowFrom][columnFrom] == 3 || this.board[rowFrom][columnFrom] == 4) {
                                int tmp = this.board[rowFrom][columnFrom];
                                this.board[rowFrom][columnFrom] = this.board[rowTo][columnTo];
                                this.board[rowTo][columnTo] = tmp;
                                isValid = true;
                            }
                        }
                    } else {
                        int tmp = this.board[rowFrom][columnFrom];
                        this.board[rowFrom][columnFrom] = this.board[rowTo][columnTo];
                        this.board[rowTo][columnTo] = tmp;
                        isValid = true;
                    }
                }
            }
        } else {
            String invert = play.split(" ")[1];
            String letter = invert.split("")[0];
            int row = positionLetter(letter);
            int column = Integer.parseInt(invert.split("")[1]) - 1;

            if (turn == 1) {
                if (player == 1) {
                    if (this.board[row][column] == 1 || this.board[row][column] == 2) {
                        if (this.board[row][column] == 1) {
                            this.board[row][column] = 2;
                        } else {
                            this.board[row][column] = 1;
                        }
                        isValid = true;
                    }
                } else {
                    if (this.board[row][column] == 3 || this.board[row][column] == 4) {
                        if (this.board[row][column] == 3) {
                            this.board[row][column] = 4;
                        } else {
                            this.board[row][column] = 3;
                        }
                        isValid = true;
                    }
                }
            } else {
                if (this.board[row][column] != 0) {
                    if (this.board[row][column] == 1 || this.board[row][column] == 2) {
                        if (this.board[row][column] == 1) {
                            this.board[row][column] = 2;
                        } else {
                            this.board[row][column] = 1;
                        }
                    } else if (this.board[row][column] == 3 || this.board[row][column] == 4) {
                        if (this.board[row][column] == 3) {
                            this.board[row][column] = 4;
                        } else {
                            this.board[row][column] = 3;
                        }
                    }
                    isValid = true;
                }
            }
        }

        return isValid;
    }

    private void buildBoard(int config) {
        if (config == 1) {
            List<Integer> list = new ArrayList<Integer>(Collections.nCopies(8, 0));

            for (int i = 0; i < 4; i++) {
                list.add((int) Math.floor(Math.random() * (2 - 1 + 1) + 1));
            }
            for (int i = 0; i < 4; i++) {
                list.add((int) Math.floor(Math.random() * (4 - 3 + 1) + 3));
            }
            Collections.shuffle(list);

            int row = 0;
            int column = 0;
            int[][] azar = new int[4][4];
            for (int number : list) {
                azar[row][column] = number;
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
            this.setBoard(azar);
        } else {
            int[][] precargadoBoard = {{3, 0, 2, 3}, {0, 0, 0, 0}, {0, 4, 3, 0}, {1, 2, 1, 0}};
            this.setBoard(precargadoBoard);
        }
    }

    public void showBoard() {
        String[] letters = {"A", "B", "C", "D"};
        if (designBoard == 1) {
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
        } else {
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
                            case 1:
                                switch (k) {
                                    case 0:
                                        System.out.print(ANSI_RED + "FFFFF" + ANSI_RESET);
                                        break;
                                    case 1:
                                        System.out.print(ANSI_RED + "F    " + ANSI_RESET);
                                        break;
                                    case 2:
                                        System.out.print(ANSI_RED + "FFF  " + ANSI_RESET);
                                        break;
                                    case 3:
                                        System.out.print(ANSI_RED + "F    " + ANSI_RESET);
                                        break;
                                    case 4:
                                        System.out.print(ANSI_RED + "F    " + ANSI_RESET);
                                        break;
                                }
                                break;
                            case 2:
                                switch (k) {
                                    case 0:
                                        System.out.print(ANSI_RED + "DDDD " + ANSI_RESET);
                                        break;
                                    case 1:
                                        System.out.print(ANSI_RED + "D   D" + ANSI_RESET);
                                        break;
                                    case 2:
                                        System.out.print(ANSI_RED + "D   D" + ANSI_RESET);
                                        break;
                                    case 3:
                                        System.out.print(ANSI_RED + "D   D" + ANSI_RESET);
                                        break;
                                    case 4:
                                        System.out.print(ANSI_RED + "DDDD " + ANSI_RESET);
                                        break;
                                }
                                break;
                            case 3:
                                switch (k) {
                                    case 0:
                                        System.out.print(ANSI_BLUE + "FFFFF" + ANSI_RESET);
                                        break;
                                    case 1:
                                        System.out.print(ANSI_BLUE + "F    " + ANSI_RESET);
                                        break;
                                    case 2:
                                        System.out.print(ANSI_BLUE + "FFF  " + ANSI_RESET);
                                        break;
                                    case 3:
                                        System.out.print(ANSI_BLUE + "F    " + ANSI_RESET);
                                        break;
                                    case 4:
                                        System.out.print(ANSI_BLUE + "F    " + ANSI_RESET);
                                        break;
                                }
                                break;
                            case 4:
                                switch (k) {
                                    case 0:
                                        System.out.print(ANSI_BLUE + "DDDD " + ANSI_RESET);
                                        break;
                                    case 1:
                                        System.out.print(ANSI_BLUE + "D   D" + ANSI_RESET);
                                        break;
                                    case 2:
                                        System.out.print(ANSI_BLUE + "D   D" + ANSI_RESET);
                                        break;
                                    case 3:
                                        System.out.print(ANSI_BLUE + "D   D" + ANSI_RESET);
                                        break;
                                    case 4:
                                        System.out.print(ANSI_BLUE + "DDDD " + ANSI_RESET);
                                        break;
                                }
                                break;
                            default:
                                switch (k) {
                                    case 0:
                                        System.out.print(ANSI_RED + "     " + ANSI_RESET);
                                        break;
                                    case 1:
                                        System.out.print(ANSI_RED + "     " + ANSI_RESET);
                                        break;
                                    case 2:
                                        System.out.print(ANSI_RED + "     " + ANSI_RESET);
                                        break;
                                    case 3:
                                        System.out.print(ANSI_RED + "     " + ANSI_RESET);
                                        break;
                                    case 4:
                                        System.out.print(ANSI_RED + "     " + ANSI_RESET);
                                        break;
                                }
                                break;
                        }

                    }
                    System.out.println("|");
                }
                System.out.println("  +-----+-----+-----+-----+");
            }
        }
    }

    private int positionLetter(String letter) {
        int row = 0;
        switch (letter) {
            case "A":
                row = 0;
                break;
            case "B":
                row = 1;
                break;
            case "C":
                row = 2;
                break;
            case "D":
                row = 3;
                break;
        }
        return row;
    }
}
