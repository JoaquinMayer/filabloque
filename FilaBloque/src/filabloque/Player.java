package filabloque;

/**
 *
 * @authors Agustin Tosar & Joaquin Mayer
 */
public class Player {
    private String name;
    private String alias;
    private int age;
    private int gamesWon;

    public Player(String name, String alias, int age) {
        this.name = name;
        this.alias = alias;
        this.age = age;
        this.gamesWon = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    @Override
    public String toString() {
        return alias;
    }
    
}
