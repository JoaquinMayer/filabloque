package filabloque;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @authors Agustin Tosar & Joaquin Mayer
 */
public class FilaBloque {

    private ArrayList<Player> players;
    private Game game;
    private boolean exit = false;

    public FilaBloque() {
        this.players = new ArrayList<>();
        this.game = null;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public void startSystem() {
        System.out.println("Bienvenido a Fila y Bloque");
        System.out.println("__________________________");

        while (!this.isExit()) {
            this.menu();
        }

        this.exit();
    }

    private void showPlayers(int order, Player filterPlayer) {
        System.out.println("");
        System.out.println("Jugadores");
        System.out.println("__________________________");
        int i = 1;

        if (order == 1) {
            Collections.sort(this.players, (Player p1, Player p2) -> {
                return Integer.valueOf(p2.getGamesWon()).compareTo(p1.getGamesWon());
            });
        } else {
            Collections.sort(this.players, (Player p1, Player p2) -> {
                return p1.getName().compareToIgnoreCase(p2.getName());
            });
        }

        for (Player player : this.players) {
            if (!player.equals(filterPlayer)) {
                System.out.println(i + ". " + player.getName() + " " + player.getGamesWon());
                i++;
            } else {
                i++;
            }

        }

        System.out.println("__________________________");

    }

    private void addPlayer(Player player) {
        this.players.add(player);
    }

    private boolean isValidAlias(String alias) {
        boolean isValid = true;

        if (!(this.players == null) && alias != null) {
            for (Player player : this.players) {
                if (player.getAlias().equals(alias)) {
                    isValid = false;
                }
            }
        } else {
            isValid = false;
        }

        return isValid;
    }

    private void menu() {
        Scanner scan = new Scanner(System.in);
        int option = 0;

        while (option == 0) {
            System.out.println();
            System.out.println("???????????????????????????????????????????????????????????????????????????????????????");
            System.out.println("???   Menu                    ???");
            System.out.println("??? _________________________ ???");
            System.out.println("??? 1. Crear nuevo jugador    ???");
            System.out.println("??? 2. Nuevo juego            ???");
            System.out.println("??? 3. Mostrar el ranking     ???");
            System.out.println("??? 4. Salir                  ???");
            System.out.println("??? _________________________ ???");
            System.out.println("???????????????????????????????????????????????????????????????????????????????????????");

            try {
                System.out.println();
                System.out.print("Ingrese una opci??n: ");
                option = scan.nextInt();
                scan.nextLine();
            } catch (Exception ex) {
                System.out.println("Por favor ingrese una opci??n v??lida:");
                option = 0;
                scan.nextLine();
            }
        }

        System.out.println();

        switch (option) {
            case 1 ->
                this.newPlayer();
            case 2 ->
                this.newGame();
            case 3 ->
                this.showRanking();
            case 4 ->
                this.setExit(true);
            default ->
                System.out.println("Opci??n inv??lida!");
        }

    }

    private void newPlayer() {
        Scanner scan = new Scanner(System.in);
        String userAlias = null;

        try {
            System.out.println("Nuevo jugador");
            System.out.println("__________________________");
            System.out.println();

            System.out.print("Ingrese el nombre del jugador: ");
            String userName = scan.nextLine();

            while (userName.equals("")) {
                System.out.print("El nombre no puede estar vac??o, por favor ingrese un nombre valido: ");
                userName = scan.nextLine();
            }

            System.out.print("Ingrese un alias: ");
            while (userAlias == null || userAlias.equals("")) {
                userAlias = scan.nextLine();
                userAlias = this.isValidAlias(userAlias) ? userAlias : null;

                if (userAlias == null) {
                    System.out.println();
                    System.out.print("El alias ya existe, por favor ingrese otro alias:");
                } else if (userAlias.equals("")) {
                    System.out.print("El alias no puede estar vac??o, por favor ingrese un alias valido: ");
                }
            }

            System.out.print("Ingrese la edad: ");
            int userAge = 0;
            while (userAge == 0) {
                try {
                    userAge = scan.nextInt();
                } catch (Exception e) {
                    System.out.println("Edad no v??lida. Por favor, ingrese una edad v??lida");
                    userAge = 0;
                    scan.nextLine();
                }

            }

            Player newPlayer = new Player(userName, userAlias, userAge);
            this.addPlayer(newPlayer);
            System.out.println();
            System.out.println("__________________________");
            System.out.println();
            System.out.println("El usuario " + newPlayer.getName() + " se ha creado correctamente.");
            System.out.println();
            System.out.println("__________________________");

        } catch (Exception ex) {
            System.out.println("Algun campo no es valido.");
        }
    }

    private void newGame() {
        Scanner scan = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<>();

        System.out.println("Nuevo juego");
        System.out.println("__________________________");

        if (this.players.size() > 1) {
            for (int i = 1; i <= 2; i++) {
                int player = 0;
                while (player == 0) {
                    if (players.size() > 0) {
                        this.showPlayers(2, players.get(0));
                    } else {
                        this.showPlayers(2, null);
                    }

                    try {
                        System.out.println();
                        System.out.print("Seleccione el jugador " + i + ": ");
                        player = scan.nextInt();
                        System.out.println();

                        if (player > 0 && !(player - 1 > this.players.size())) {
                            if (players.size() > 0 && players.get(0).equals(this.players.get(player - 1))) {
                                System.out.println("Por favor ingrese una opci??n v??lida.");
                                player = 0;
                            } else {
                                players.add(this.players.get(player - 1));
                            }
                        } else {
                            System.out.println("Por favor ingrese una opci??n v??lida.");
                            player = 0;
                        }
                    } catch (Exception ex) {
                        System.out.println("Por favor ingrese una opci??n v??lida.");
                        scan.next();
                        player = 0;
                    }
                }
            }

            Game game = new Game(players.get(0), players.get(1));
            game.startGame();
        } else {
            System.out.println();
            System.out.println("No hay suficientes jugadores");
        }

        System.out.println("__________________________");

    }

    private void showRanking() {
        if (this.players.size() > 0) {
            Scanner scan = new Scanner(System.in);
            int option = 0;

            while (option == 0) {
                System.out.println("Seleccione el orden del ranking");
                System.out.println("__________________________");
                System.out.println("1. Juegos Ganados");
                System.out.println("2. Alfab??ticamente");
                System.out.println("3. Salir");
                System.out.println("__________________________");
                System.out.println();

                try {
                    System.out.print("Ingrese una opci??n: ");
                    option = scan.nextInt();
                    scan.nextLine();

                    if (option < 0 && option > 3) {
                        System.out.print("Ingrese una opci??n v??lida");
                        option = 0;
                    }
                } catch (Exception e) {
                    System.out.println("Por favor ingrese una opci??n v??lida");
                    option = 0;
                    scan.nextLine();
                }

            }

            if (option != 3) {
                this.showPlayers(option, null);
            }
        } else {
            System.out.println("Aun no hay jugadores");
        }

    }

    private void exit() {
        System.out.println("Exit");
    }
}
