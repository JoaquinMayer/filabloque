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
        this.players = null;
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
        }   
    }
    
    private void addPlayer(Player player) {
        this.players.add(player);
    }
    
    private boolean isValidAlias(String alias) {
        if (alias == null) {
            return false;
        }
        
        for (Player player : this.players) {
            if (player.getAlias().equals(alias)) {
                return false;
            }
        }
        
        return true;
    }
    
    public void startSystem() {
        int menuOption = 0;
        
        System.out.println("Bienvenido a Fila y Bloque");
        System.out.println("__________________________");
        System.out.println();
        
        this.menu();
    }
    
    private void menu () {
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
                Scanner scan = new Scanner(System.in);
                option = scan.nextInt();
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
            
            System.out.println("Ingrese el nombre del jugador:");
            String userName = scan.nextLine();

            System.out.println("Ingrese un alias:");
            while (userAlias == null) {
                userAlias = scan.nextLine();
                
                userAlias = this.isValidAlias(userAlias) ? userAlias : null;
                
                if (userAlias == null) {
                    System.out.println("El alias ya existe, por favor ingrese otro alias:");
                }
            }

            
            System.out.println("Ingrese la edad:");
            int userAge = scan.nextInt();
            
            Player newPlayer = new Player(userName, userAlias, userAge);
            this.addPlayer(newPlayer);
        }
        catch(Exception ex)
        {
           System.out.println("Algun campo no es valido!");
        }
    }
    
    private void newGame() {
        int player = 0;
        ArrayList<Player> players = null;

        System.out.println("Nuevo juego");
        System.out.println("__________________________");
        
        for (int i = 1; i <= 2; i++) {
            System.out.println("Seleccione el jugador " + i + ":");
            System.out.println();
            
            while (player == 0) {
                this.showPlayers(this.players);
                
                try {
                    Scanner scan = new Scanner(System.in);
                    player = scan.nextInt();
                    players.add(this.players.get(i-1));
                }
                catch(Exception ex)
                {
                   System.out.println("Por favor ingrese una opcion valida");
                   player = 0;
                }
            }
        }
        
//        Game game = new Game(players.get(0), players.get(1));
//        game.startGame();
    }
    
    private void showRanking(String order) {
        System.out.println("show ranking");
    }
    
    private void exit() {
        System.out.println("exit");
    }
}
