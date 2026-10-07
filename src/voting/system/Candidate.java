/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package voting.system;

/**
 *
 * @author nerfe
 */
public class Candidate {
    private int id;
    private String name;
    private String position;
    private int votes;

    public Candidate(int id, String name, String position, int votes) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.votes = votes;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getVotes() {
        return votes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public void addVote() {
        votes++;
    }
}
