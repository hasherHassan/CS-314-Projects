/*  Student information for assignment:
 *
 *  On my honor, Hasher, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  Name: Hasher
 *  email address: hmh3233@my.utexas.edu
 *  Number of slip days used on this assignment: 0
 */

// add imports as necessary

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Manages the details of EvilHangman. This class keeps
 * tracks of the possible words from a dictionary during
 * rounds of hangman, based on guesses so far.
 *
 */
public class HangmanManager {

    // instance variables / fields
    private Set<String> originalWords;
    private Set<String> activeSet;
    private Set<String> guessedLetter;
    private String pattern;
    private int guessesLeft;
    private int guessNumber;
    private HangmanDifficulty difficulty;
    private boolean debugOn;

    /**
     * Create a new HangmanManager from the provided set of words and phrases.
     * pre: words != null, words.size() > 0
     * @param words A set with the words for this instance of Hangman.
     * @param debugOn true if we should print out debugging to System.out.
     */
    public HangmanManager(Set<String> words, boolean debugOn) {
        originalWords = new HashSet<String>(words);
        this.debugOn = debugOn;
    }

    /**
     * Create a new HangmanManager from the provided set of words and phrases.
     * Debugging is off.
     * pre: words != null, words.size() > 0
     * @param words A set with the words for this instance of Hangman.
     */
    public HangmanManager(Set<String> words) {
        this(words, false);
    }


    /**
     * Get the number of words in this HangmanManager of the given length.
     * pre: none
     * @param length The given length to check.
     * @return the number of words in the original Dictionary
     * with the given length
     */
    public int numWords(int length) {
        int count = 0;
        for (String word: originalWords){
            if (word.length() == length){
                count++;
            }
        }
        return count;
    }


    /**
     * Get for a new round of Hangman. Think of a round as a
     * complete game of Hangman.
     * @param wordLen the length of the word to pick this time.
     * numWords(wordLen) > 0
     * @param numGuesses the number of wrong guesses before the
     * player loses the round. numGuesses >= 1
     * @param diff The difficulty for this round.
     */
    public void prepForRound(int wordLen, int numGuesses, HangmanDifficulty diff) {
        guessesLeft = numGuesses;
        difficulty = diff;
        guessNumber = 0;
        guessedLetters = new TreeSet<Character>();
        activeWords = new HashSet<String>;
        for (String word: originalWords){
            if (word.length == wordLen){
                activeWords.add(word);
            }
        }
        StringBuilder startingPattern = new StringBuilder();
        for (int i = 0; i < wordLen; i++){
            startingPattern.append("-");
        }
        pattern = startPattern.toString();
    }


    /**
     * The number of words still possible (live) based on the guesses so far.
     *  Guesses will eliminate possible words.
     * @return the number of words that are still possibilities based on the
     * original dictionary and the guesses so far.
     */
    public int numWordsCurrent() {
        return activeSet.size();
    }


    /**
     * Get the number of wrong guesses the user has left in
     * this round (game) of Hangman.
     * @return the number of wrong guesses the user has left
     * in this round (game) of Hangman.
     */
    public int getGuessesLeft() {
        return guessesLeft;
    }


    /**
     * Return a String that contains the letters the user has guessed
     * so far during this round.
     * The characters in the String are in alphabetical order.
     * The String is in the form [let1, let2, let3, ... letN].
     * For example [a, c, e, s, t, z]
     * @return a String that contains the letters the user
     * has guessed so far during this round.
     */
    public String getGuessesMade() {
        return guessedLetter.toString();
    }


    /**
     * Check the status of a character.
     * @param guess The character to check. 
     * pre: Character.isLowerCase(guess) 
     * (In other words Character.isLowerCase(guess) is true)
     * @return true if guess has been used or guessed this round of Hangman,
     * false otherwise.
     */
    public boolean alreadyGuessed(char guess) {
        if (!Character.isLowerCase(guess)) {
            throw new IllegalArgumentException("The parameter guess must be a "
                    + "lower case letter. " + guess);
        }
        return guessedLetter.contains(guess);
    }



    /**
     * Get the current pattern. The pattern contains '-''s for
     * unrevealed (or guessed) characters and the actual character 
     * for "correctly guessed" characters.
     * @return the current pattern.
     */
    public String getPattern() {
        return pattern;
    }


    /**
     * Update the game status (pattern, wrong guesses, word list),
     * based on the give guess.
     * @param guess pre: !alreadyGuessed(ch), the current guessed character
     *  and Character.isLowerCase(guess) 
     * (In other words Character.isLowerCase(guess) is true)
     * @return return a tree map with the resulting patterns and the number of
     * words in each of the new patterns.
     * The return value is for testing and debugging purposes.
     */
    public TreeMap<String, Integer> makeGuess(char guess) {
        if (!Character.isLowerCase(guess)) {
            throw new IllegalArgumentException("The parameter guess must be a "
                    + "lower case letter. " + guess);
        } else if(alreadyGuessed(guess)) {
            throw new IllegalArgumentException("The parameter guess must not "
                    + "have already been guessed. Guesses so far: " + getGuessesMade());
        } else if (!pattern.contains("-")){
            throw new IllegalArgumentException("The parameter guess must have atleast one - ");
        }
        Map<String, ArrayList<String>> families = new TreeMap<>();
        for (String word : activeWords) {
            String possiblePattern = "";
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess) { 
                    possiblePattern += guess;
                } else {
                    possiblePattern += currentPattern.charAt(i);
                }
            }
            if (!families.containsKey(possiblePattern)){
                families.put(possiblePattern, new ArrayList<String>());
            }
            families.get(possiblePattern).add(word);
        }
        int count = 0;
        String bestKey = null;
        for (String key : families.keySet()) {
            int size = families.get(key).size();
            if (bestKey == null || size > count){
                count = size;
                bestKey = key;
            }
            else if (size == count){
                int letterCount = countLetter(key, guess);
                int bestCount = countLetter(bestKey, guess);
                if (letterCount < bestCount 
                        || (letterCount == bestCount && key.compareTo(bestKey) < 0)){
                    bestKey = key;
                }
            }
        }
        if (countLetter(bestKey, guess) == 0){
            guessesLeft--;
        }
        pattern = bestKey;
        activeWords = new HashSet<String>(families.get(bestKey));
        guessedLetter.add(guess);
        guessNumber++;
        TreeMap<String, Integer> result = new TreeMap<>();
        for (String key : families.keySet()) {
            results.put(key, families.get(key).size());
        }
        return result;
    } 


    /**
     * Return the secret word this HangmanManager finally ended up
     * picking for this round.
     * If there are multiple possible words left one is selected at random.
     * <br> pre: numWordsCurrent() > 0
     * @return return the secret word the manager picked.
     */
    public String getSecretWord() {
        return "DEFAULT";
    }
}
