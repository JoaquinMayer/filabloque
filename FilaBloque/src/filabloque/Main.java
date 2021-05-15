/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filabloque;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author joaquin
 */
public class Main {
    private ArrayList<Player> players;
    private Game game;

    public Main() {
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
    
    private void showPlayers(ArrayList<Player> players) {
        int i = 1;
        for (Player player : players) {
            System.out.println(i + ". " + player.getName());
            i++;
        }   
    }
    
    private void addPlayer(Player player) {
        this.players.add(player);
    }
    
    private boolean isValidAlias(String alias) {
        if (alias == null) {
            return false;
        } 
        
        if (!(this.players == null)) {        
            for (Player player : this.players) {
                if (player.getAlias().equals(alias)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public void startSystem() {        
        System.out.println("Bienvenido a Fila y Bloque");
        System.out.println("__________________________");
        System.out.println();
        
        this.menu();
    }
    
    private void menu () {
        Scanner scan = new Scanner(System.in);
        int option = 0;
        
        while (option == 0) {
            System.out.println("Menu");
            System.out.println("__________________________");
            System.out.println("1. Crear nuevo jugador");
            System.out.println("2. Nuevo juego");
            System.out.println("3. Mostrar el ranking");
            System.out.println("4. Salir");
            System.out.println("__________________________");

            try {
                System.out.println();
                System.out.print("Ingrese una opcion: ");
                option = scan.nextInt();
                System.out.println();
            }
                 catch(Exception ex)
            {
               System.out.println("Por favor ingrese una opcion valida");
            }
        }
        
        switch(option) {
        case 1:
          this.newPlayer();
          break;
        case 2:
          this.newGame();
          break;
        case 3:
          this.showRanking("ASC");
          break;
        case 4:
          this.exit();
          break;
        default:
          System.out.println("Opcion no valida!");
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

            System.out.print("Ingrese un alias: ");
            while (userAlias == null) {
                userAlias = scan.nextLine();
                userAlias = this.isValidAlias(userAlias) ? userAlias : null;
                
                if (userAlias == null) {
                    System.out.println();
                    System.out.print("El alias ya existe, por favor ingrese otro alias:");
                }
            }

            System.out.print("Ingrese la edad: ");
            int userAge = scan.nextInt();
                        
            Player newPlayer = new Player(userName, userAlias, userAge);
            this.addPlayer(newPlayer);
            System.out.println("El usuario " + newPlayer.getName() + " se ha creado correctamente.");
            System.out.println();
            this.menu();
        }
        catch(Exception ex)
        {
           System.out.println("Algun campo no es valido.");
        }
    }
    
    private void newGame() {
        Scanner scan = new Scanner(System.in);
        int player = 0;
        ArrayList<Player> players = new ArrayList<>();

        System.out.println("Nuevo juego");
        System.out.println("__________________________");
        
        for (int i = 1; i <= 2; i++) {
            while (player == 0) {
                this.showPlayers(this.players);
                
                try {
                    System.out.println();
                    System.out.print("Seleccione el jugador " + i + ":");                    
                    player = scan.nextInt();
                    System.out.println();

                    if (player > 0) {
                        players.add(this.players.get(player - 1));
                    } else {
                        System.out.println("Por favor ingrese una opcion valida.");
                    }
                }
                catch(Exception ex)
                {
                   System.out.println("Por favor ingrese una opcion valida.");
                   player = 0;
                }
            }
        }
        
        Game game = new Game(players.get(0), players.get(1));
        game.startGame();
        this.menu();
    }
    
    private void showRanking(String order) {
        System.out.println("show ranking");
    }
    
    private void exit() {
        System.out.println("exit");
    }
}
