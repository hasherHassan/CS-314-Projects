import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* CS 314 STUDENTS: FILL IN THIS HEADER AND THEN COPY AND PASTE IT TO YOUR
 * LetterInventory.java AND AnagramSolver.java CLASSES.
 *
 * Student information for assignment:
 *
 *  On my honor, Hasher, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: hmh3233
 *  email address: hmh3233@my.utexas.edu
 *  TA name: Jason Oh
 *  Number of slip days I am using: 0
 */

public class AnagramSolver {
    //instance variables
    private Map<String, LetterInventory> wordInventories;
    // Associates each dictionary word with its letter inventory

    /*
     * pre: dictionary != null, dictionary does not contain a null element or an empty string,
     *      all elements of dictionary contain only lower case, English letters a - z.
     * post: Creates a solver that forms anagrams using words from dictionary
     * @param dictionary Contains the words to form anagrams from.
     * @throws IllegalArgumentException if precons not met
     */
    public AnagramSolver(Set<String> dictionary) {
        if (dictionary == null) {
            throw new IllegalArgumentException("Dictionary cannot be null");
        }
        for (String word : dictionary) {
            if (word == null || word.isEmpty()) {
                throw new IllegalArgumentException("Dictionary words cannot be null or empty");
            }
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (ch < 'a' || ch > 'z') {
                    throw new IllegalArgumentException("Dictionary must contain only valid words");
                }
            }
        }
        wordInventories = new HashMap<>();
        for (String word : dictionary) {
            wordInventories.put(word, new LetterInventory(word));
        }
    }

    /*
     * pre: maxWords >= 0, s != null, s contains at least one
     * English letter.
     * post: return a list of anagrams that can be formed from s with
     * no more than maxWords, unless maxWords is 0 in which case
     * there is no limit on the number of words in the anagram. Results
     * are ordered y word count, then alphabetically. Words withn each
     * anagram are alphabetical. Case and nonletter characters are
     * ignored.
     * @throws IllegalArgumentException if precons not met
     */
    public List<List<String>> getAnagrams(String s, int maxWords) {
        checkPrecons(s, maxWords);
        ArrayList<List<String>> result = new ArrayList<List<String>>();
        LetterInventory phraseInventory = new LetterInventory(s);
        ArrayList<String> usableWords = new ArrayList<>();
        for (String key : wordInventories.keySet()) {
            if (phraseInventory.subtract(wordInventories.get(key)) != null) {
                usableWords.add(key);
            }
        }
        Collections.sort(usableWords);
        ArrayList<String> currentAnagram = new ArrayList<>();
        getAnagramsHelper(phraseInventory, usableWords, 0, currentAnagram, maxWords, result);
        Collections.sort(result, new AnagramComparator());
        return result;
    }

    /**
     * pre: inventory, words, currentAnagram, and result != null, words is sorted
     * alphabetically and contains only nonempty words which are in wordInvetories,
     * 0 <= index <= words.size(), maxWords >= 0, currentAnagram is sorted and inventory
     * contains the target's remaining letters after using the words in currentAnagram
     * post: Adds copies of all valid completions of currentAnagram to result, using
     * words at index or later. Leaves inventory, words, and currentAnagram unchanged
     */
    private void getAnagramsHelper(LetterInventory inventory, ArrayList<String> words, int index,
                ArrayList<String> currentAnagram, int maxWords, List<List<String>> result) {
        if (inventory.isEmpty()) {
            result.add(new ArrayList<>(currentAnagram));
        } else if (maxWords == 0 || currentAnagram.size() < maxWords) {
            for (int i = index; i < words.size(); i++) {
                String word = words.get(i);
                LetterInventory remaining = inventory.subtract(wordInventories.get(word));
                if (remaining != null) {
                    currentAnagram.add(word);
                    getAnagramsHelper(remaining, words, i, currentAnagram, maxWords, result);
                    currentAnagram.remove(currentAnagram.size() - 1);
                }
            }
        }
    }

    /**
     * pre: none
     * post: Throws IllegalArgumentException if maxWords < 0, s == null, or
     * s does not contain at least one english letter, otherwise, completes
     * normally if arguments are valid
     * @throws IllegalArgumentException if precons not met
     */
    private void checkPrecons(String s, int maxWords) {
        if (maxWords < 0) {
            throw new IllegalArgumentException("Max words has to be >= 0");
        } else if (s == null) {
            throw new IllegalArgumentException("S has to be non null");
        }
        boolean hasEnglishLetter = false;
        s = s.toLowerCase();
        for (int i = 0; i < s.length() && !hasEnglishLetter; i++) {
            char ch = s.charAt(i);
            hasEnglishLetter = 'a' <= ch && ch <= 'z';
        }
        if (!hasEnglishLetter) {
            throw new IllegalArgumentException("Phrase must contains at least one " +
                        "English letter");
        }
    }

    private static class AnagramComparator implements Comparator<List<String>> {
        /**
         * pre: a1 != null, a2 != null, a1 and a2 do not contain null strings
         * post: returns a negative value if a1 comes before a2, a positive value
         * if a1 comes after a2, or zero if they contain the same order. Compares
         * by number of words first, then alphabetically by the first different word.
         * a1 and a2 are not modified.
         */
        public int compare(List<String> a1, List<String> a2) {
            if (a1.size() != a2.size()) {
                return a1.size() - a2.size();
            }
            for (int i = 0; i < a1.size(); i++) {
                int comparison = a1.get(i).compareTo(a2.get(i));
                if (comparison != 0) {
                    return comparison;
                }
            }
            return 0;
        }
    }
}
