/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filabloque;

import java.util.Scanner;

/**
 *
 * @author joaquin
 */
public class Game {

    private Player player1;
    private Player player2;
    private Board board;
    private int playerTurn;
    private boolean[][] completedDesigns;
    private boolean finishedGame;

    private String ANSI_RESET = "\u001B[0m";
    private String ANSI_RED = "\u001B[31m";
    private String ANSI_BLUE = "\u001B[34m";

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = null;
        this.playerTurn = 1;
        this.completedDesigns = new boolean[][]{{false, false}, {false, false}};
        this.finishedGame = false;
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

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playTurn) {
        this.playerTurn = playTurn;
    }

    public boolean[][] getCompletedDesigns() {
        return completedDesigns;
    }

    public void setCompletedDesigns(boolean[][] completedDesigns) {
        this.completedDesigns = completedDesigns;
    }

    public boolean isFinishedGame() {
        return finishedGame;
    }

    public void setFinishedGame(boolean finishedGame) {
        this.finishedGame = finishedGame;
    }

    public void startGame() {
        System.out.println("Juego iniciado");
        System.out.println("__________________________");
        System.out.println();

        int configuration[] = this.gameConfiguration();
        this.setBoard(new Board(configuration[0], configuration[1]));

        this.board.showBoard();

        while (!this.isFinishedGame()) {
            this.playTurn();
            this.checkTurn();
        }
    }

    private int[] gameConfiguration() {
        Scanner scan = new Scanner(System.in);
        int configuration[] = new int[]{1, 1};
        int boardConfigOption = 0;
        int sizeOption = 0;

        while (boardConfigOption == 0) {
            try {
                System.out.println("Configuracion del tablero");
                System.out.println("__________________________");
                System.out.println("(Elija el modo en que desea configurar el tablero)");
                System.out.println();
                System.out.println("1. Aleatorio");
                System.out.println("2. Preconfigurado");
                System.out.println("3. Salir");
                System.out.println("__________________________");
                System.out.println();
                System.out.println("Ingrese una opcion: ");
                boardConfigOption = scan.nextInt();

                if (boardConfigOption > 0 && boardConfigOption <= 3) {
                    configuration[0] = boardConfigOption;
                } else {
                    System.out.println("Por favor ingrese una opcion valida.");
                    boardConfigOption = 0;
                }
            } catch (Exception ex) {
                System.out.println("Por favor ingrese una opcion valida.");
                boardConfigOption = 0;
                scan.nextLine();
            }
        }

        while (sizeOption == 0) {
            try {
                System.out.println("Tamano del tablero");
                System.out.println("__________________________");
                System.out.println("(Elija el tamano en que desea ver el tablero)");
                System.out.println();
                System.out.println("1. Normal");
                System.out.println("2. Grande");
                System.out.println("3. Salir");
                System.out.println("__________________________");
                System.out.println();
                System.out.println("Ingrese una opcion: ");
                sizeOption = scan.nextInt();

                if (sizeOption > 0 && sizeOption <= 3) {
                    configuration[1] = sizeOption;
                } else {
                    System.out.println("Por favor ingrese una opcion valida.");
                    sizeOption = 0;
                }
            } catch (Exception ex) {
                System.out.println("Por favor ingrese una opcion valida.");
                sizeOption = 0;
                scan.nextLine();
            }
        }

        return configuration;
    }

    public void playTurn() {
        String actualPlayerName;

        if (this.playerTurn == 1) {
            actualPlayerName = this.player1.getName();
            System.out.print("Es el turno de " + ANSI_RED + actualPlayerName + ANSI_RESET);
        } else {
            actualPlayerName = this.player2.getName();
            System.out.print("Es el turno de " + ANSI_BLUE + actualPlayerName + ANSI_RESET);
        }

        System.out.println();

        for (int i = 1; i <= 2; i++) {
            boolean madeMove = false;
            while (!madeMove) {
                String play = this.readPlay(i);
                madeMove = board.makeMove(play, i, this.getPlayerTurn());

                if (!madeMove) {
                    System.out.println("Jugada invalida, por favor ingrese una jugada valida.");
                }
            }

            this.board.showBoard();
        }

        if (this.playerTurn == 1) {
            this.setPlayerTurn(2);
        } else {
            this.setPlayerTurn(1);
        }
    }

    public String readPlay(int turn) {
        Scanner scan = new Scanner(System.in);
        boolean validPlay = false;
        String play = "";

        while (!validPlay) {
            try {
                System.out.print("Ingrese la siguiente jugada (turno " + turn + "): ");
                play = scan.nextLine();

                if (this.isValidFormatPlay(play)) {
                    validPlay = true;
                } else {
                    System.out.println("Por favor ingrese una jugada valida.");
                }
            } catch (Exception ex) {
                System.out.println("Por favor ingrese una jugada valida.");
            }
        }
        return play;
    }

    private boolean isValidFormatPlay(String play) {
        return true;
    }

    public void checkTurn() {
        this.checkCompletedDesigns();
        this.setFinishedGame(this.checkWinner());
    }

    private void checkCompletedDesigns() {
        try {
            boolean updatedCompletedDesigns[][] = this.getCompletedDesigns();

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (this.board.getBoard()[i][j] == 1 || this.board.getBoard()[i][j] == 3) {
                        int pieceToCheck = this.board.getBoard()[i][j];
                        boolean horizontalDesign = true;
                        for (int k = 0; k < 4 && horizontalDesign; k++) {
                            if (this.board.getBoard()[i][k] != pieceToCheck) {
                                horizontalDesign = false;
                            }
                        }

                        boolean verticalDesign = true;
                        for (int k = 0; k < 4 && verticalDesign; k++) {
                            if (this.board.getBoard()[k][j] != pieceToCheck) {
                                verticalDesign = false;
                            }
                        }

                        if (horizontalDesign || verticalDesign) {
                            if (pieceToCheck == 1) {
                                updatedCompletedDesigns[0][0] = true;
                                System.out.println(this.getPlayer1().getName() + " completo la fila");
                            } else {
                                updatedCompletedDesigns[1][0] = true;
                                System.out.println(this.getPlayer2().getName() + " completo la fila");
                            }
                        }

                        this.setCompletedDesigns(updatedCompletedDesigns);
                    } else if (this.board.getBoard()[i][j] == 2 || this.board.getBoard()[i][j] == 4) {
                        int pieceToCheck = this.board.getBoard()[i][j];
                        boolean blockDesign = false;
                        if (i + 1 < 4 && this.board.getBoard()[i + 1][j] == pieceToCheck && j + 1 < 4 && this.board.getBoard()[i + 1][j + 1] == pieceToCheck && this.board.getBoard()[i][j + 1] == pieceToCheck) {
                            blockDesign = true;
                        }

                        if (blockDesign) {

                            if (pieceToCheck == 2) {
                                updatedCompletedDesigns[0][1] = true;
                                System.out.println(this.getPlayer1().getName() + " completo el bloque");
                            } else {
                                updatedCompletedDesigns[1][1] = true;
                                System.out.println(this.getPlayer2().getName() + " completo el bloque");
                            }

                            this.setCompletedDesigns(updatedCompletedDesigns);
                        }
                    }

                }
            }
        } catch (Exception e) {
            System.out.println("Error en chequeo");
        }

    }

    private boolean checkWinner() {
        boolean gameFinished = true;

        if (this.getCompletedDesigns()[0][0]
                && this.getCompletedDesigns()[0][1]
                && this.getCompletedDesigns()[1][0]
                && this.getCompletedDesigns()[1][1]) {
            System.out.println("Empate");
        } else if (this.getCompletedDesigns()[0][0] && this.getCompletedDesigns()[1][0]) {
            this.player1.setGamesWon(this.getPlayer1().getGamesWon() + 1);
            System.out.println("Gano " + this.getPlayer1().getName());
        } else if (this.getCompletedDesigns()[0][1] && this.getCompletedDesigns()[1][1]) {
            this.player2.setGamesWon(this.getPlayer2().getGamesWon() + 1);
            System.out.println("Gano " + this.getPlayer2().getName());
        } else {
            gameFinished = false;
        }

        return gameFinished;
    }
}
