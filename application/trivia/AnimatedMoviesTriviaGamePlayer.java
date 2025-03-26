package trivia;

import java.io.IOException;
import java.util.Scanner;
import netgame.common.Client;

// shahadat anadil 3/26/2025 it114-004 phase3 sha38@njit.edu


/**
 * represents player in the animated movies trivia game
 * handles the client-side communication with the game server
 * 
 * player connects to a server, sends message, and can disconnect from the game.
 * @author Shahadat Anadil
 * @verison 3/06/2025
 *
 */
public class AnimatedMoviesTriviaGamePlayer {

    private static final int PORT = 37829; // Port number for the server.

    private static volatile boolean connected = false; // Tracks connection status.
    private static MoviesTriviaGameClient moviesTriviaGameClient;

    /**
     * entry point for the player client
     * prompts user for server hostname, connects to server, and message sending.
     * @param args optional command-lie arguments (hostname of the server)
     */
    public static void main(String[] args) {
        String host = "";
        Scanner scanner = new Scanner(System.in);
        if (args.length == 0) {
            System.out.print("[sha38] Enter the host name of the computer hosting the trivia game: ");
            host = scanner.nextLine().trim();
        } else {
            host = args[0];
        }
        //host not empty check
        if (host.isEmpty()) {
            System.out.println("[sha38] Host name cannot be empty. Exiting.");
            scanner.close();
            return;
        }

        // Try to establish a connection to the server.
        try {
            System.out.println("[sha38] Connecting to " + host + "...");
            moviesTriviaGameClient = new MoviesTriviaGameClient(host);
            connected = true;
            System.out.println("[sha38] Connected to the server. Type your messages below. Type 'quit' to exit.");
        } catch (IOException e) {
            System.out.println("[sha38] Failed to connect to the server: " + e.getMessage());
            scanner.close();
            return;
        }

        // Main loop for sending messages.
        while (connected) {
            String message = scanner.nextLine().trim();

            if (message.equalsIgnoreCase("quit")) {
                doQuit();
                break;
            }

            if (!message.isEmpty()) {
                moviesTriviaGameClient.send(message);
            }
        }

        scanner.close();
    }

    /**
     * Handles clean disconnection from the server.
     */
    private static void doQuit() {
        if (connected) {
            moviesTriviaGameClient.disconnect();
            try {
                Thread.sleep(1000); // Time for DisconnectMessage to actually be sent.
            } catch (InterruptedException e) {
            }
            connected = false;
            System.out.println("[sha38] Disconnected from the server. Goodbye!");
        }
    }

    /**
     * Inner class representing the trivia game client.
     */
    private static class MoviesTriviaGameClient extends Client {

        /**
         * Constructor to create a client connection to the specified host.
         *
         * @param host The server's host name or IP address.
         * @throws IOException If the connection cannot be established.
         */
        MoviesTriviaGameClient(String host) throws IOException {
            super(host, PORT);
        }

        /**
         * Called when a message is received from the server.
         *
         * @param message The received message.
         */
        @Override
        protected void messageReceived(Object message) {
            System.out.println(message.toString()); //modified 2/27
            if (message instanceof AnimatedMoviesTriviaGameState) {
                AnimatedMoviesTriviaGameState state = (AnimatedMoviesTriviaGameState) message;
                if (state.senderID != 0) {
                    System.out.println("[sha38] Player  " + state.senderID + " " + state.message);
                }
            }
        }

        /**
         * Called when the connection is closed due to an error.
         *
         * @param message Error message describing the reason for disconnection.
         */
        @Override
        protected void connectionClosedByError(String message) {
            System.out.println("[sha38] Connection closed due to error: " + message);
            connected = false;
        }

        /**
         * Called when a new player connects to the server.
         *
         * @param newPlayerID The ID of the newly connected player.
         */
        @Override
        protected void playerConnected(int newPlayerID) {
            System.out.println("[sha38] Player " + newPlayerID + " joined the game.");
        }

        /**
         * Called when a player disconnects from the server.
         *
         * @param departingPlayerID The ID of the player who disconnected.
         */
        @Override
        protected void playerDisconnected(int departingPlayerID) {
            System.out.println("[sha38] Player " + departingPlayerID + " left the game.");
        }
    }
}