package filabloque;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/**
 *
 * @authors Agustin Tosar & Joaquin Mayer
 *
 */
public class Game {

    private Player player1;
    private Player player2;
    private Board board;
    private int playerTurn;
    private boolean[] completedDesigns;
    private boolean finishedGame;
    private boolean exitGame = false;
    private String RESET_COLOR = "\u001B[0m";
    private String RED_COLOR = "\u001B[31m";
    private String BLUE_COLOR = "\u001B[34m";

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = null;
        this.playerTurn = 1;
        this.completedDesigns = new boolean[]{false, false, false, false};
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

    public boolean[] getCompletedDesigns() {
        return completedDesigns;
    }

    public void setCompletedDesigns(boolean[] completedDesigns) {
        this.completedDesigns = completedDesigns;
    }

    public boolean isFinishedGame() {
        return finishedGame;
    }

    public void setFinishedGame(boolean finishedGame) {
        this.finishedGame = finishedGame;
    }

    public boolean isExitGame() {
        return exitGame;
    }

    public void setExitGame(boolean exitGame) {
        this.exitGame = exitGame;
    }

    public void startGame() {
        this.playSound("./src/sounds/crowdclaps.wav"); // TODO: borrar antes de hacer la entrega

        System.out.println("Juego iniciado");
        System.out.println("__________________________");
        System.out.println();

        int configuration[] = this.gameConfiguration();
        if (configuration[0] != 3 || configuration[1] != 3) {
            this.setBoard(new Board(configuration[0], configuration[1]));

            this.board.showBoard();

            while (!this.isFinishedGame() && !this.isExitGame()) {
                this.playTurn();
                if (!this.isExitGame()) {
                    this.checkTurn();
                }
            }
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
                System.out.print("Ingrese una opcion: ");
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

        if (boardConfigOption == 3) {
            sizeOption = 3;
            configuration[1] = 3;
        }

        while (sizeOption == 0) {
            try {
                System.out.println("Tamaño del tablero");
                System.out.println("__________________________");
                System.out.println("(Elija el tamaño en que desea ver el tablero)");
                System.out.println();
                System.out.println("1. Normal");
                System.out.println("2. Grande");
                System.out.println("3. Salir");
                System.out.println("__________________________");
                System.out.println();
                System.out.print("Ingrese una opcion: ");
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

        if (sizeOption == 3) {
            configuration[0] = 3;
        }

        return configuration;
    }

    public void playTurn() {
        String actualPlayerName;

        if (this.playerTurn == 1) {
            actualPlayerName = this.player1.getName();
            System.out.print("Es el turno de " + RED_COLOR + actualPlayerName + RESET_COLOR);
        } else {
            actualPlayerName = this.player2.getName();
            System.out.print("Es el turno de " + BLUE_COLOR + actualPlayerName + RESET_COLOR);
        }

        System.out.println();

        for (int i = 1; i <= 2 && !this.isExitGame(); i++) {
            boolean madeMove = false;
            while (!madeMove && !this.isExitGame()) {
                String play = this.readPlay(i);

                if (!play.equalsIgnoreCase("X")) {
                    madeMove = board.makeMove(play, i, this.getPlayerTurn());

                    if (!madeMove) {
                        System.out.println("Jugada invalida, por favor ingrese una jugada valida.");
                    }
                } else {
                    this.checkExitGame();
                }
            }

            if (!this.isExitGame()) {
                this.board.showBoard();
            }

        }

        if (!this.isExitGame()) {
            if (this.playerTurn == 1) {
                this.setPlayerTurn(2);
            } else {
                this.setPlayerTurn(1);
            }
        }

    }

    public String readPlay(int turn) {
        Scanner scan = new Scanner(System.in);
        boolean validPlay = false;
        String play = "";

        while (!validPlay && !play.equalsIgnoreCase("X")) {
            try {
                System.out.print("Ingrese la siguiente jugada (turno " + turn + "): ");
                play = scan.nextLine().toUpperCase();

                if (!play.equalsIgnoreCase("X")) {
                    if (this.isValidFormatPlay(play)) {
                        validPlay = true;
                    } else {
                        System.out.println("Por favor ingrese una jugada valida.");
                    }
                }

            } catch (Exception ex) {
                System.out.println("Por favor ingrese una jugada valida.");
            }
        }
        return play;
    }

    private boolean isValidFormatPlay(String play) {
        boolean isValidFormatPlay = false;
        Pattern positions = Pattern.compile("A1|A2|A3|A4|B1|B2|B3|B4|C1|C2|C3|C4|D1|D2|D3|D4|");
        String[] playParams = play.split(" ");
        String action = playParams[0];
        String firstPosition = playParams[1];

        try {
            if (action.equals("I")) {
                isValidFormatPlay = positions.matcher(firstPosition).matches();
            } else if (action.equals("M")) {
                String secondPosition = playParams[2];

                isValidFormatPlay = positions.matcher(firstPosition).matches()
                        && positions.matcher(secondPosition).matches();
            }
        } catch (Exception e) {
            return false;
        }

        return isValidFormatPlay;
    }

    public void checkTurn() {
        this.checkCompletedDesigns();
        this.setFinishedGame(this.checkWinner());
    }

    private void checkCompletedDesigns() {
        System.out.println();
        try {
            boolean[] updatedCompletedDesigns = this.getCompletedDesigns().clone();

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
                                updatedCompletedDesigns[0] = true;
                            } else {
                                updatedCompletedDesigns[2] = true;
                            }
                        }

                    } else if (this.board.getBoard()[i][j] == 2 || this.board.getBoard()[i][j] == 4) {
                        int pieceToCheck = this.board.getBoard()[i][j];
                        boolean blockDesign = false;
                        if (i + 1 < 4 && this.board.getBoard()[i + 1][j] == pieceToCheck && j + 1 < 4
                                && this.board.getBoard()[i + 1][j + 1] == pieceToCheck
                                && this.board.getBoard()[i][j + 1] == pieceToCheck) {
                            blockDesign = true;
                        }

                        if (blockDesign) {

                            if (pieceToCheck == 2) {
                                updatedCompletedDesigns[1] = true;
                            } else {
                                updatedCompletedDesigns[3] = true;
                            }

                        }
                    }

                }
            }

            if (updatedCompletedDesigns[0] && !this.getCompletedDesigns()[0]) {
                System.out.println("El jugador " + RED_COLOR + this.getPlayer1().getAlias() + RESET_COLOR + " completo el diseño de fila");
            }
            if (updatedCompletedDesigns[1] && !this.getCompletedDesigns()[1]) {
                System.out.println("El jugador " + RED_COLOR + this.getPlayer1().getAlias() + RESET_COLOR + " completo el diseño de bloque");
            }
            if (updatedCompletedDesigns[2] && !this.getCompletedDesigns()[2]) {
                System.out.println("El jugador " + BLUE_COLOR + this.getPlayer2().getAlias() + RESET_COLOR + " completo el diseño de fila");
            }
            if (updatedCompletedDesigns[3] && !this.getCompletedDesigns()[3]) {
                System.out.println("El jugador " + BLUE_COLOR + this.getPlayer2().getAlias() + RESET_COLOR + " completo el diseño de bloque");
            }

            this.setCompletedDesigns(updatedCompletedDesigns);

        } catch (Exception e) {
            System.out.println("Error en chequeo");
        }

    }

    private boolean checkWinner() {
        boolean gameFinished = true;
        System.out.println();
        if (this.getCompletedDesigns()[0] && this.getCompletedDesigns()[1] && this.getCompletedDesigns()[2]
                && this.getCompletedDesigns()[3]) {
            System.out.println("Es un empate");
        } else if (this.getCompletedDesigns()[0] && this.getCompletedDesigns()[1]) {
            this.player1.setGamesWon(this.getPlayer1().getGamesWon() + 1);
            System.out.println("Ha ganado el jugador " + RED_COLOR + this.getPlayer1().getAlias() + RESET_COLOR);
        } else if (this.getCompletedDesigns()[2] && this.getCompletedDesigns()[3]) {
            this.player2.setGamesWon(this.getPlayer2().getGamesWon() + 1);
            System.out.println("Ha ganado el jugador " + BLUE_COLOR + this.getPlayer2().getAlias() + RESET_COLOR);
        } else {
            gameFinished = false;
        }

        if (gameFinished) {
            this.playSound("./src/sounds/crowdclaps.wav");
        }

        return gameFinished;
    }

    private void checkExitGame() {
        Scanner scan = new Scanner(System.in);
        int option = 0;

        while (option == 0) {
            System.out.println("¿Esta seguro que quiere salir y perder el juego?");
            System.out.println("__________________________");
            System.out.println("1. SI");
            System.out.println("2. NO");
            System.out.println("__________________________");
            System.out.println();

            try {
                System.out.print("Ingrese una opcion: ");
                option = scan.nextInt();
                scan.nextLine();

                if (option < 0 && option > 2) {
                    System.out.print("Ingrese una opcion valida");
                    option = 0;
                }
            } catch (Exception e) {
                System.out.println("Por favor ingrese una opcion valida");
                option = 0;
                scan.nextLine();
            }

        }

        if (option == 1) {
            this.setExitGame(true);
            if (this.playerTurn == 1) {
                this.getPlayer2().setGamesWon(this.getPlayer2().getGamesWon() + 1);
            } else {
                this.getPlayer1().setGamesWon(this.getPlayer1().getGamesWon() + 1);
            }
            this.setFinishedGame(true);
        }

    }

    public void playSound(String path) {
        try {
            File file = new File(path);
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            stream = AudioSystem.getAudioInputStream(file);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
        } catch (Exception e) {
            System.out.println("No se pudo reproducir el sonido correctamente");
        }
    }
}
