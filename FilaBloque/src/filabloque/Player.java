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
public class Player {
    private String name;
    private String alias;
    private int age;
    private String color;

    public Player(String name, String alias, int age) {
        this.name = name;
        this.alias = alias;
        this.age = age;
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
}
