package trivia;

import java.io.Serializable;


//Shahadat Anadil, 2/20/2025, IT114-004, Phase 1, sha38@njit.edu

/**
 Basically the game state for Animated Movies Trivia
 Keeps track of messages and sender IDs
 
 @author [shahadat anadil]
 **/

public class AnimatedMoviesTriviaGameState implements Serializable {


   public String message; // Original message from a client.
   public int senderID; // The ID of the client who sent that message.

   /**
    * Updates the game with the latest message from a player
    * @param sender the ID of the player sending the message
    * @param message the message that was sent by the player
    */

   public void applyMessage(int sender, Object message) {
       this.senderID = sender;
       this.message = (String) message;
   }
}

