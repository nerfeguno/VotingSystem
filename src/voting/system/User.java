/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package voting.system;

/**
 *
 * @author nerfe
 */
public class User {
    private int id;
    private String fullName;
    private String username;
    private String password;
    private boolean hasVoted;
    private String role;

    public User(int id, String fullName, String username,
            String password, boolean hasVoted, String role) {

        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.hasVoted = hasVoted;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean hasVoted() {
        return hasVoted;
    }

    public String getRole() {
        return role;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }
}
