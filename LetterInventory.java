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

public class LetterInventory {
    //instance variables
    private int[] inventory;
    // Counts of English letters

    private int size;

    private static final int ALPHABET_COUNT = 26;

    /**
     * pre: data != null
     * post: constructor that keeps track of the frequency of each letter,
     * uppercase or lowercase English letters are counted together and other
     * characters are ignored
     * @throws IllegalArgumentException if precons not met
     */
    public LetterInventory(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        inventory = new int[ALPHABET_COUNT];
        data = data.toLowerCase();
        for (int i = 0; i < data.length(); i++) {
            char letter = data.charAt(i);
            if ('a' <= letter && letter <= 'z') {
                inventory[letter - 'a']++;
                size++;
            }
        }
    }

    /**
     * pre: letter is an English character, 'a' - 'z', 'A' - 'Z'
     * post: returns the frequency of the letter in LetterInventory
     * @throws IllegalArgumentException if precons not met
     */
    public int get(char letter) {
        letter = Character.toLowerCase(letter);
        if ('a' > letter || letter > 'z') {
            throw new IllegalArgumentException("Letter has to be an English letter");
        }
        return inventory[letter - 'a'];
    }

    /**
     * pre: none
     * post: returns total number of letters in LetterInventory
     */
    public int size() {
        return size;
    }

    /**
     * pre: none
     * post: returns if LetterInventory is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * pre: none
     * post: returns String representation of LetterInventory,
     * returns lowercase letters in alphabetical order, repeated
     * according to their counts, or "" when empty
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < inventory.length; i++) {
            for (int j = 0; j < inventory[i]; j++) {
                sb.append((char)(i + 'a'));
            }
        }
        return sb.toString();
    }

    /**
     * pre: other != null
     * post: neither the calling object or other are altered as a
     * result of this method call, returns a new LetterInventory
     * created by adding the frequencies from the calling LetterInventory
     * object to the frequencies of the letters in other
     * @throws IllegalArgumentException if precons not met
     */
    public LetterInventory add(LetterInventory other) {
        if (other == null) {
            throw new IllegalArgumentException("Other cannot be null");
        }
        LetterInventory result = new LetterInventory("");
        for (int i = 0; i < inventory.length; i++) {
            result.inventory[i] = inventory[i] + other.inventory[i];
            result.size += result.inventory[i];
        }
        return result;
    }

    /**
     * pre: other != null
     * post: returns a new inventory containing this inventory's
     * counts minus other's counts, or null if any count would be
     * negative. Neither inventory is modified.
     * @throws IllegalArgumentException if precons not met
     */
    public LetterInventory subtract(LetterInventory other) {
        if (other == null) {
            throw new IllegalArgumentException("Other cannot be null");
        }
        LetterInventory result = new LetterInventory("");
        for (int i = 0; i < inventory.length; i++) {
            result.inventory[i] = inventory[i] - other.inventory[i];
            result.size += result.inventory[i];
            if (result.inventory[i] < 0) {
                return null;
            }
        }
        return result;
    }

    /**
     * pre: none
     * post: Returns true if other is a LetterInventory with
     * identical letter counts to this LetterInventory; returns
     * false otherwise, also for null
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof LetterInventory)) {
            return false;
        }
        LetterInventory otherInventory = (LetterInventory) other;
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != otherInventory.inventory[i]) {
                return false;
            }
        }
        return true;
    }
}