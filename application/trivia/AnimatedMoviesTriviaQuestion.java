package trivia;

// shahadat anadil 3/26/2025 it114-004 phase3 sha38@njit.edu

/**
 * Shows a trivia question related to animated movies
 * includes a question and its corresponding  answer
 * 
 * @author Shahadat Anadil
 * @param question the trivia questions
 * @param answer the answers to that trivia question
 */

public record AnimatedMoviesTriviaQuestion(String question, String answer) {
    /**
     * this checks if the answer that the user provided matches the correct answer(which is case sensitive)
     * 
     * @param userAnswer the answer the user gives 
     * @return true if the user's answer is correct, false if it's not correct
     */

    public boolean isCorrectAnswer(String userAnswer) {
        return this.answer.equalsIgnoreCase(userAnswer);
    }
 }