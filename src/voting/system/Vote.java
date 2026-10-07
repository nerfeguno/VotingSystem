/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package voting.system;

/**
 *
 * @author nerfe
 */
public class Vote {
    private int voterId;
    private int candidateId;
    private String position;

    public Vote(int voterId, int candidateId, String position) {
        this.voterId = voterId;
        this.candidateId = candidateId;
        this.position = position;
    }

    public int getVoterId() {
        return voterId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public String getPosition() {
        return position;
    }
}
