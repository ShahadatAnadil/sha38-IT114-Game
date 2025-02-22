package trivia;

import java.io.IOException;
import netgame.common.Hub;

//Shahadat Anadil, 2/20/2025, IT114-004, Phase 1, sha38@njit.edu

/**
 * The server for the animated movies trivia game, essentially handles the connections and communication between players.
 * @author [shahadat anadil]
 */


public class AnimatedMoviesTriviaGameServer extends Hub {


   private final static int PORT = 37829;


   private AnimatedMoviesTriviaGameState state;

   /**
    * Initializing the server and setting up the game state
    * @throws IOException only if there is an issue starting the server
    */


   public AnimatedMoviesTriviaGameServer() throws IOException {
       super(PORT);
       setAutoreset(true);
       state = new AnimatedMoviesTriviaGameState();
   }


   /**
    * Handles messages received from clients by applying them to the game state
    * and broadcasting the updated state to all connected players.
    *
    *@param playerID of the player sending the message
    *@param message the content of the message
    */
   @Override

   protected void messageReceived(int playerID, Object message) {
    System.out.printf("[sha38] Player %d %s\n", playerID, message);
    state.applyMessage(playerID, message);
    sendToAll(state);
}


/**
 * Called when a player connects. Updates the game state and notifies all
 * players.
 * 
 * @param playerID the ID of the new player connecting
 */
@Override
protected void playerConnected(int playerID) {
    System.out.printf("[sha38] player %d connected.\n", playerID);
    AnimatedMoviesTriviaGameState newState= new AnimatedMoviesTriviaGameState();
    sendToAll(newState);
}


/**
 * Called when a player disconnects. Updates the game state and notifies
 * remaining players.
 * 
 * @param playerID the id of the player that's discounnecting
 *     */

@Override
protected void playerDisconnected(int playerID) {
    System.out.printf("[sha38] Player %d disconnected.\n", playerID);
    sendToAll(state);
}

/**
 * the main method to start the server
 * @param args command-line args
 */


public static void main(String[] args) {
    try {
        new AnimatedMoviesTriviaGameServer();
    } catch (IOException e) {
        System.out.println("[sha38] Error starting server: " + e.getMessage());
    }
}
}

