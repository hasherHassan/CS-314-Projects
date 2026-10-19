/*
 * Student information for assignment: Replace <NAME> in the following with your
 * name. You are stating, on your honor you did not copy any other code on this
 * assignment and have not provided your code to anyone.
 *
 * On my honor, Hasher, this programming assignment is my own work
 * and I have not provided this code
 * to any other student.
 *
 * UTEID: hmh3233
 * email address: hmh3233@my.utexas.edu
 * Number of slip days I am using: 0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class NameSurfer {

    // Menu Option 7 sees which of the names were late bloomers, which means that it sees which names
    // always below the top 1000 names in the first half of the data but were top 1000 in the second half

    // CS314 Answer the questions from the assignment page and explain the interesting trend you found
    // backed up by data.

    // CS314 students, add test code for NameRecord class here:
    private static void testNameRecord() {
        NameRecord improving = new NameRecord("Bobby", 1900, 
                new ArrayList<Integer>(Arrays.asList( 
                        1000, 900, 800, 700, 600,
                        500, 400, 300, 200, 100)));
        NameRecord declining = new NameRecord("Timmy", 1900, 
                new ArrayList<Integer>(Arrays.asList( 
                        100, 200, 300, 400, 500,
                        600, 700, 800, 900, 0)));
        NameRecord single = new NameRecord("John", 1900, 
                new ArrayList<Integer>(Arrays.asList( 
                        0, 0, 0, 0, 0, 0, 78, 0, 0, 0)));
        NameRecord tied = new NameRecord("Ty", 1900, 
                new ArrayList<Integer>(Arrays.asList( 
                        200, 300, 700, 200)));
        NameRecord consecutiveZeroes = new NameRecord("Marley", 1900, 
                new ArrayList<Integer>(Arrays.asList( 
                        0, 0, 129, 429)));
        NameRecord modern = new NameRecord("Alfred", 1980, 
                new ArrayList<Integer>(Arrays.asList(500, 400)));
        NameRecord anotherYear = new NameRecord("Bobby", 2000, 
                new ArrayList<Integer>(Arrays.asList(329, 483)));
        
        // getName tests
        checkString("getName test 1", "Bobby", improving.getName());
        checkString("getName test 2", "Timmy", declining.getName());
        
        // getDecade test
        checkInt("getDecade test 1", 1900, improving.getDecade());
        checkInt("getDecade test 2", 1980, modern.getDecade());
        
        // getNumDecades test
        checkInt("getNumDecades test 1", 10, improving.getNumDecades());
        checkInt("getNumDecades test 2", 2, modern.getNumDecades());
        
        // getRank test
        checkInt("getRank first index test", 1000, improving.getRank(0));
        checkInt("getRank last index test", 100, improving.getRank(9));
        checkInt("getRank unranked test", 0, single.getRank(0));
        
        // getBestDecade test
        checkInt("getBestDecade normal test", 1990, improving.getBestDecade());
        checkInt("getBestDecade tie test", 1930, tied.getBestDecade());
        
        // getRankedDecades test
        checkInt("getRankedDecades all ranked test", 10, improving.getRankedDecades());
        checkInt("getRankedDecades partially ranked test", 9, declining.getRankedDecades());
        checkInt("getRankedDecades one ranked test", 1, single.getRankedDecades());
        
        // isRankedEveryDecade
        checkBoolean("isRankedEveryDecade true test", true, improving.isRankedEveryDecade());
        checkBoolean("isRankedEveryDecade false test", false, declining.isRankedEveryDecade());
        
        // isRankedOnlyOneDecade
        checkBoolean("isRankedOnlyOneDecade true test", true, single.isRankedOnlyOneDecade());
        checkBoolean("isRankedOnlyOneDecade false test", false, declining.isRankedOnlyOneDecade());
        
        // alwaysMorePopular tests
        checkBoolean("alwaysMorePopular true test", true, improving.alwaysMorePopular());
        checkBoolean("alwaysMorePopular false test", false, declining.alwaysMorePopular());
        checkBoolean("alwaysMorePopular consecutive zeros test", false, consecutiveZeroes.alwaysMorePopular());
        
        // alwaysLessPopular tests
        checkBoolean("alwaysLessPopular true test", true, declining.alwaysLessPopular());
        checkBoolean("alwaysLessPopular false test", false, improving.alwaysLessPopular());
        checkBoolean("alwaysLessPopular consecutive zeros test", false, consecutiveZeroes.alwaysLessPopular());
        
        String expectedImproving = "Bobby\n1900: 1000\n1910: 900\n1920: 800\n1930: 700\n1940: 600\n1950: 500\n"
                                + "1960: 400\n1970: 300\n1980: 200\n1990: 100\n";
        checkString("toString test 1", expectedImproving, improving.toString());
        
        String expectedModern = "Alfred\n1980: 500\n1990: 400\n";
        checkString("toString test 2", expectedModern, modern.toString());
        
        // compareTo tests
        checkBoolean("compareTo before test", true, improving.compareTo(declining) < 0);
        checkBoolean("compareTo after test", true, declining.compareTo(improving) > 0);
        checkInt("compareTo equal name test", 0, improving.compareTo(anotherYear));
    }

    private static void checkInt(String testName, int expected, int actual){
        if (expected == actual) {
            System.out.println("PASSED: " + testName);
        } else{
            System.out.println("FAILED: " + testName);
            System.out.println("EXPECTED: " + expected);
            System.out.println("ACTUAL: " + actual);
        }
    }

    private static void checkBoolean(String testName, boolean expected, boolean actual){
        if (expected == actual) {
            System.out.println("PASSED: " + testName);
        } else{
            System.out.println("FAILED: " + testName);
            System.out.println("EXPECTED: " + expected);
            System.out.println("ACTUAL: " + actual);
        }
    }

    private static void checkString(String testName, String expected, String actual){
        if (expected.equals(actual)) {
            System.out.println("PASSED: " + testName);
        } else{
            System.out.println("FAILED: " + testName);
            System.out.println("EXPECTED: " + expected);
            System.out.println("ACTUAL: " + actual);
        }
    }

    // One of the basic data files given on the assignment.
    // Alter this to try different data files.
    private static final String NAME_FILE = "names.txt";

    // Tests all public methods in the NameRecord Class
    // A few simple tests for the Names and NameRecord class.
    public static void simpleTest() {
        // raw data for Jake. Alter as necessary for your NameRecord
        testNameRecord();
        String jakeRawData = "Jake 262 312 323 479 484 630 751 453 225 117 97";
        int baseDecade = 1900;
        Scanner jakeScanner = new Scanner(jakeRawData);
        String name = jakeScanner.next();
        ArrayList<Integer> rankings = new ArrayList<>();
        while(jakeScanner.hasNextInt()) {
            rankings.add(jakeScanner.nextInt());
        }
        NameRecord jakeRecord = new NameRecord(name, baseDecade, rankings);
        String expected = "Jake\n1900: 262\n1910: 312\n1920: 323\n1930: 479\n1940: "
                        + "484\n1950: 630\n1960: 751\n1970: 453\n1980: 225\n1990: "
                        + "117\n2000: 97\n";
        String actual = jakeRecord.toString();
        System.out.println("expected string:\n" + expected);
        System.out.println("actual string:\n" + actual);
        if (expected.equals(actual)) {
            System.out.println("passed Jake toString test.");
        } else {
            System.out.println("FAILED Jake toString test.");
        }

        // Some getName Tests
        Names names = new Names(getFileScannerForNames(NAME_FILE));
        String[] testNames = {"Isabelle", "isabelle", "sel",
                "X1178", "ZZ", "via", "kelly"};
        boolean[] expectNull = {false, false, true, true, true, true, false};
        for (int i = 0; i < testNames.length; i++) {
            performGetNameTest(names, testNames[i], expectNull[i]);
        }
    }

    // Checks if given name is present in Names.
    private static void performGetNameTest(Names names, String name,
            boolean expectNull) {

        System.out.println("Performing test for this name: " + name);
        if (expectNull) {
            System.out.println("Expected return value is null");
        } else {
            System.out.println("Expected return value is not null");
        }
        NameRecord result = names.getName(name);
        if ((expectNull && result == null) || (!expectNull && result != null)) {
            System.out.println("PASSED TEST.");
        } else {
            System.out.println("Failed test");
        }
    }

    // main method. Driver for the whole program
    public static void main(String[] args) {
        // Delete the following line in the final version of your program.
        simpleTest();
        Scanner fileScanner = getFileScannerForNames(NAME_FILE);
        Names namesDatabase = new Names(fileScanner);
        fileScanner.close();
        runOptions(namesDatabase);
    }

    /* pre: namesDatabase != null
    Ask user for options to perform on the given Names object.
    Creates a Scanner connected to System.in.
     */
    private static void runOptions(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The Names object namesDatabase cannot be null");
        }
        Scanner keyboard = new Scanner(System.in);
        MenuChoices[] menuChoices = MenuChoices.values();
        MenuChoices menuChoice;
        do {
            showMenu();
            int userChoice = getChoice(keyboard) - 1;
            menuChoice = menuChoices[userChoice];
            if(menuChoice == MenuChoices.SEARCH) {
                search(namesDatabase, keyboard);
            } else if (menuChoice == MenuChoices.ONE_NAME) {
                oneName(namesDatabase, keyboard);
            } else if (menuChoice == MenuChoices.APPEAR_ONCE) {
                appearOnce(namesDatabase);
            } else if (menuChoice == MenuChoices.APPEAR_ALWAYS) {
                appearAlways(namesDatabase);
            } else if (menuChoice == MenuChoices.ALWAYS_MORE) {
                alwaysMore(namesDatabase);
            } else if (menuChoice == MenuChoices.ALWAYS_LESS) {
                alwaysLess(namesDatabase);
            } else if (menuChoice == MenuChoices.STUDENT_SEARCH) {
                lateBloomers(namesDatabase);
            }
        } while(menuChoice != MenuChoices.QUIT);
        keyboard.close();
    }

    /* Create a Scanner and return connected to a File with the given name.
    pre: fileName != null
    post: Return a Scanner connected to the file or null
    if the File does not exist in the current directory.
     */
    private static Scanner getFileScannerForNames(String fileName) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("\n***** ERROR IN READING FILE ***** ");
            System.out.println("Can't find this file "
                    + fileName + " in the current directory.");
            System.out.println("Error: " + e);
            String currentDir = System.getProperty("user.dir");
            System.out.println("Be sure " + fileName + " is in this directory: ");
            System.out.println(currentDir);
            System.out.println("\nReturning null from method.");
            sc = null;
        }
        return sc;
    }

    /* Display the names that have appeared in every decade.
    pre: n != null
    post: print out names that have appeared in ever decade
     */
    private static void appearAlways(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter namesDatabase cannot be null");
        }
        ArrayList<String> results = namesDatabase.rankedEveryDecade();
        for (String name : results) {
            System.out.println(name);
        }
    }

    /* Display the names that have appeared in only one decade.
    pre: n != null
    post: print out names that have appeared in only one decade
     */
    private static void appearOnce(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter"
                    + " namesDatabase cannot be null");
        }
        ArrayList<String> results = namesDatabase.rankedOnlyOneDecade();
        for (String name : results) {
            System.out.println(name);
        }
    }

    /* Display the names that have gotten more popular
     * in each successive decade.
     * pre: n != null
     * post: print out names that have gotten more popular in each decade
     */
    private static void alwaysMore(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter"
                    + " namesDatabase cannot be null");
        }
        ArrayList<String> results = namesDatabase.alwaysMorePopular();
        for (String name : results) {
            System.out.println(name);
        }
    }

    /* Display the names that have gotten less popular
     * in each successive decade.
     * pre: n != null
     * post: print out names that have gotten less popular in each decade
     */
    private static void alwaysLess(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter"
                    + " namesDatabase cannot be null");
        }
        ArrayList<String> results = namesDatabase.alwaysLessPopular();
        for (String name : results) {
            System.out.println(name);
        }
    }

    /* Display the data for one name or state that name has never been ranked.
     * pre: n != null, keyboard != null and is connected to System.in
     * post: print out the data for n or a message that n has never been in the
     * top 1000 for any decade
     */
    private static void oneName(Names namesDatabase, Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (namesDatabase == null || keyboard == null) {
            throw new IllegalArgumentException("The parameters cannot be null");
        }
        System.out.print("Enter a name: ");
        String name = keyboard.nextLine();
        NameRecord result = namesDatabase.getName(name);
        if (result == null) {
            System.out.println(name + "has never been in the top 1000.");
        } else {
            System.out.print(result);
        }
    }

    /* Display all names that contain a substring from the user
     * and the decade they were most popular.
     * pre: n != null, keyboard != null and is connected to System.in
     * post: display the data for each name.
     */
    private static void search(Names namesDatabase, Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (namesDatabase == null || keyboard == null) {
            throw new IllegalArgumentException("The parameters cannot be null");
        }
        System.out.print("Enter a partial name: ");
        String partialName = keyboard.nextLine();
        ArrayList<NameRecord> results = namesDatabase.getMatches(partialName);
        if (results.size() == 0) {
            System.out.println("There are no matches for " + partialName + ".");
        } else {
            for (NameRecord record : results) {
                System.out.println(record.getName() + ": " + record.getBestDecade());
            }
        }
    }

    private static void lateBloomers(Names namesDatabase) {
        if (namesDatabase == null) {
            throw new IllegalArgumentException("The parameter"
                    + " namesDatabase cannot be null");
        }
        ArrayList<String> results = namesDatabase.lateBloomers();
        for (String name : results) {
            System.out.println(name);
        }
    }

    /* Get choice from the user keyboard != null and is connected to System.in
     * return an int that is >= MenuChoices.SEARCH.ordinal()
     *  and <= MenuChoices.QUIT.ordinal().
     */
    private static int getChoice(Scanner keyboard) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (keyboard == null) {
            throw new IllegalArgumentException("The parameter keyboard cannot be null");
        }
        int choice = getInt(keyboard, "Enter choice: ");
        keyboard.nextLine();
        // Add one due to zero based indexing of enums, but 1 based indexing of menu.
        final int MAX_CHOICE = MenuChoices.QUIT.ordinal() + 1;
        while (choice < 1  || choice > MAX_CHOICE) {
            System.out.println();
            System.out.println(choice + " is not a valid choice");
            choice = getInt(keyboard, "Enter choice: ");
            keyboard.nextLine();
        }
        return choice;
    }

    /* Ensure an int is entered from the keyboard.
     * pre: s != null and is connected to System.in
     * post: return the int typed in by the user.
     */
    private static int getInt(Scanner s, String prompt) {
        // Note, no way to check if keyboard actually connected to System.in
        // so we simply assume it is.
        if (s == null) {
            throw new IllegalArgumentException("The parameter s cannot be null");
        }
        System.out.print(prompt);
        while (!s.hasNextInt()) {
            s.next();
            System.out.println("That was not an int.");
            System.out.print(prompt);
        }
        return s.nextInt();
    }

    // Show the user the menu.
    private static void showMenu() {
        System.out.println();
        System.out.println("Options:");
        System.out.println("Enter 1 to search for names.");
        System.out.println("Enter 2 to display data for one name.");
        System.out.println("Enter 3 to display all names that appear in only "
                + "one decade.");
        System.out.println("Enter 4 to display all names that appear in all "
                + "decades.");
        System.out.println("Enter 5 to display all names that are more popular "
                + "in every decade.");
        System.out.println("Enter 6 to display all names that are less popular "
                + "in every decade.");
        System.out.println("Enter 7 to <replace with description of "
                + "your method / search>.");
        System.out.println("Enter 8 to quit.");
        System.out.println();
    }

    /**
     * An enumerated type to hold the menu choices
     * for the NameSurfer program.
     */
    private static enum MenuChoices {
        SEARCH, ONE_NAME, APPEAR_ONCE, APPEAR_ALWAYS, ALWAYS_MORE,
        ALWAYS_LESS, STUDENT_SEARCH, QUIT;
    }
}
