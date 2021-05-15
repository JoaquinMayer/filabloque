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

    private int[][] board;
    private String designBoard;
    private String config;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public Board(String designBoard, String config) {
        this.designBoard = designBoard;
        this.config = config;
        this.buildBoard(config);
    }

    public int[][] getBoard() {
        return board;
    }

    public String getDesignBoard() {
        return designBoard;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void setDesignBoard(String designBoard) {
        this.designBoard = designBoard;
    }

    private void buildBoard(String config) {
        if (config.equals("azar")) {
            System.out.println("Tablero al azar");
        } else {
            int[][] precargadoBoard = {{3, 0, 2, 3}, {0, 0, 0, 0}, {0, 4, 3, 0}, {1, 2, 1, 0}};
            this.setBoard(precargadoBoard);
        }
    }

    public void showBoard() {
        String[] letters = {"A", "B", "C", "D"};
        if (designBoard.equals("pequeño")) {
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

}
