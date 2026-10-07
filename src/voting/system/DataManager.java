/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package voting.system;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nerfe
 */
public class DataManager {
    private static final String DATA_FOLDER = "data";
    private static final String USERS_FILE = DATA_FOLDER + "/users.txt";
    private static final String CANDIDATES_FILE = DATA_FOLDER + "/candidates.txt";
    private static final String VOTES_FILE = DATA_FOLDER + "/votes.txt";

    public static void initialize() {

        File folder = new File(DATA_FOLDER);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        try {
            File users = new File(USERS_FILE);
            File candidates = new File(CANDIDATES_FILE);
            File votes = new File(VOTES_FILE);

            if (!users.exists()) {
                users.createNewFile();
            }

            if (!candidates.exists()) {
                candidates.createNewFile();
            }

            if (!votes.exists()) {
                votes.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static User loginUser(String username, String password) {

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(USERS_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length >= 6) {

                    String storedUsername = data[2];
                    String storedPassword = data[3];

                    if (storedUsername.equals(username)
                            && storedPassword.equals(password)) {

                        return new User(
                                Integer.parseInt(data[0]),
                                data[1],
                                data[2],
                                data[3],
                                Boolean.parseBoolean(data[4]),
                                data[5]
                        );
                    }
                }
            }

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean usernameExists(String username) {

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(USERS_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length >= 3
                        && data[2].equals(username)) {

                    return true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean registerUser(
            String fullName,
            String username,
            String password) {

        if (usernameExists(username)) {
            return false;
        }

        int nextId = getNextUserId();

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(USERS_FILE, true))) {

            writer.write(
                    nextId + "|" +
                    fullName + "|" +
                    username + "|" +
                    password + "|false|VOTER"
            );

            writer.newLine();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static int getNextUserId() {

        int maxId = 0;

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(USERS_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length > 0) {

                    int id = Integer.parseInt(data[0]);

                    if (id > maxId) {
                        maxId = id;
                    }
                }
            }

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return maxId + 1;
    }


    public static List<Candidate> getCandidates() {

        List<Candidate> candidates = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(CANDIDATES_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length >= 4) {

                    candidates.add(
                            new Candidate(
                                    Integer.parseInt(data[0]),
                                    data[1],
                                    data[2],
                                    Integer.parseInt(data[3])
                            )
                    );
                }
            }

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return candidates;
    }

    public static boolean addCandidate(
            String name,
            String position) {

        int id = getNextCandidateId();

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(CANDIDATES_FILE, true))) {

            writer.write(
                    id + "|" +
                    name + "|" +
                    position + "|0"
            );

            writer.newLine();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static int getNextCandidateId() {

        int maxId = 0;

        for (Candidate candidate : getCandidates()) {

            if (candidate.getId() > maxId) {
                maxId = candidate.getId();
            }
        }

        return maxId + 1;
    }

    public static boolean deleteCandidate(int candidateId) {

        List<Candidate> candidates = getCandidates();

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(CANDIDATES_FILE))) {

            for (Candidate candidate : candidates) {

                if (candidate.getId() != candidateId) {

                    writer.write(
                            candidate.getId() + "|" +
                            candidate.getName() + "|" +
                            candidate.getPosition() + "|" +
                            candidate.getVotes()
                    );

                    writer.newLine();
                }
            }

            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    public static boolean recordVote(
            int voterId,
            int candidateId) {

        List<Candidate> candidates = getCandidates();

        Candidate selected = null;

        for (Candidate candidate : candidates) {

            if (candidate.getId() == candidateId) {
                selected = candidate;
                break;
            }
        }

        if (selected == null) {
            return false;
        }

        selected.addVote();

        saveCandidates(candidates);

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(VOTES_FILE, true))) {

            writer.write(
                    voterId + "|" +
                    candidateId + "|" +
                    selected.getPosition()
            );

            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        updateUserVotedStatus(voterId);

        return true;
    }

    private static void saveCandidates(
        List<Candidate> candidates) {

        try (BufferedWriter writer =
                new BufferedWriter(
                    new FileWriter(CANDIDATES_FILE))) {

            for (Candidate candidate : candidates) {

                writer.write(
                    candidate.getId() + "|" +
                    candidate.getName() + "|" +
                    candidate.getPosition() + "|" +
                    candidate.getVotes()
                );

                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean updateCandidate(
        int candidateId,
        String name,
        String position) {

        List<Candidate> candidates = getCandidates();

        boolean found = false;

        for (Candidate candidate : candidates) {

            if (candidate.getId() == candidateId) {

                candidate.setName(name);
                candidate.setPosition(position);

                found = true;
                break;
            }
        }

        if (!found) {
            return false;
        }

        saveCandidates(candidates);

        return true;
    }
    

    private static void updateUserVotedStatus(int userId) {

        List<String> lines = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(USERS_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length >= 6
                        && Integer.parseInt(data[0]) == userId) {

                    data[4] = "true";

                    line = String.join("|", data);
                }

                lines.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(USERS_FILE))) {

            for (String line : lines) {

                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
