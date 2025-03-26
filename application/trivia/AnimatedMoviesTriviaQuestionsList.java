package trivia;

// shahadat anadil 3/26/2025 it114-004 phase3 sha38@njit.edu


import java.util.ArrayList;
/**
 * This class contains a list of movie trivia questions
 * Provides the methods to get a question and check the size of the list
 * 
 * @author Shahadat Anadil
 */


public class AnimatedMoviesTriviaQuestionsList {
   private ArrayList<AnimatedMoviesTriviaQuestion> questions;
   public AnimatedMoviesTriviaQuestionsList() {
        /**
         * Constructors to initialize the list of movie trivia questions
         */
       questions = new ArrayList<>();
       questions.add(new AnimatedMoviesTriviaQuestion("In Coco, what is the name of the musical icon Ernesto de la Cruz stole songs from?", "Hector"));
       questions.add(new AnimatedMoviesTriviaQuestion("In The Lego Movie, what is the name of the prophecy that Emmet is believed to fulfill?", "Prophecy"));
       questions.add(new AnimatedMoviesTriviaQuestion("In Wall-E, what is the name of the spaceship where humans live", "Axiom"));
       questions.add(new AnimatedMoviesTriviaQuestion("In Up, what is the name of the rare bird that Kevin is?", "Snipe"));
       questions.add(new AnimatedMoviesTriviaQuestion("In Ratatouille, what is the name of the food critic who reviews Gusteau's resturant?", "Ego"));
       questions.add(new AnimatedMoviesTriviaQuestion("In Aladdin, what is the name of Jasmine's pet tiger", "Rajah"));
       // Add more questions here in random order.
   }

   /**
    * retrieves a trivia questions, this is based on the provided index

    * @param currentQuestionIndex the index of the question it's going to get
    * @return it'll return the trivia question at the given index
    */
   public AnimatedMoviesTriviaQuestion get(int currentQuestionIndex) {
       return questions.get(currentQuestionIndex);
   }
   /**
    * returns the # of questions
    * @return the # of questions in this list
    */
   public int size() {
       return questions.size();
   }
}
