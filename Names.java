/*  Student information for assignment:
*
*  On my honor, <NAME>, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID: hmh3233
*  email address: hmh3233@my.utexas.edu
*  Number of slip days I am using: 0
*/

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

/**
* A collection of NameRecords.
* Stores NameRecord objects and provides methods to select
* NameRecords based on various criteria.
*/
public class Names {

    private ArrayList<NameRecord> names;
    /**
     * Construct a new Names object based on the data source the Scanner
     * sc is connected to. Assume the first two lines in the
     * data source are the base year and number of decades to use.
     * Any lines without the correct number of decades are discarded
     * and are not part of the resulting Names object.
     * Any names with ranks of all 0 are discarded and not
     * part of the resulting Names object.
     * @param sc Is connected to a data file with baby names
     * and positioned at the start of the data source.
     */
    public Names(Scanner sc) {
        names = new ArrayList<NameRecord>();
        int baseYear = sc.nextInt();
        int decades = sc.nextInt();
        sc.nextLine();
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            String[] parsedData = line.split("\\s+");
            if (parsedData.length == decades + 1){
                String name = parsedData[0];
                ArrayList<Integer> rankings = new ArrayList<Integer>();
                boolean allZeroes = true;
                for (int i = 1; i < parsedData.length; i++){
                    int rank = Integer.parseInt(parsedData[i]);
                    rankings.add(rank);
                    if (rank != 0){
                        allZeroes = false;
                    }
                }
                if (!allZeroes){
                    names.add(new NameRecord(name, baseYear, rankings));
                }
            }
        }
        Collections.sort(names);
    }

   /**
    * Returns an ArrayList of NameRecord objects that contain a
    * given substring, ignoring case.  The names must be in sorted order based
    * on the names of the NameRecords.
    * @param partialName != null, partialName.length() > 0
    * @return an ArrayList of NameRecords whose names contains
    * partialName. If there are no NameRecords that meet this
    * criteria returns an empty list.
    */
    public ArrayList<NameRecord> getMatches(String partialName) {
        ArrayList<NameRecord> matchingNames = new ArrayList<NameRecord>();
        String target = partialName.toLowerCase();
        for (int i = 0; i < names.size(); i++){
            NameRecord currentRecord = names.get(i);
            String currentName = currentRecord.getName().toLowerCase();
            if (currentName.contains(target)){
                matchingNames.add(currentRecord);
            }
        }
        return matchingNames;
    }

   /**
    * Returns an ArrayList of Strings of names that have been ranked in the
    * top 1000 or better for every decade. The Strings  must be in sorted
    * order based on the name of the NameRecords.
    * @return A list of the names that have been ranked in the top
    * 1000 or better in every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list.
    */
    public ArrayList<String> rankedEveryDecade() {
        ArrayList<String> topNames = new ArrayList<String>();
        for (int i = 0; i < names.size(); i++){
            if (names.get(i).isRankedEveryDecade()){
                topNames.add(names.get(i).getName());
            }
        }
        return topNames;
    }

   /**
    * Returns an ArrayList of Strings of names that have been ranked in the
    * top 1000 or better in exactly one decade. The Strings must be in sorted
    * order based on the name of the NameRecords.
    * @return A list of the names that have been ranked in the top
    * 1000 or better in exactly one decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list.
    */
    public ArrayList<String> rankedOnlyOneDecade() {
        ArrayList<String> topNames = new ArrayList<String>();
        for (int i = 0; i < names.size(); i++){
            if (names.get(i).isRankedOnlyOneDecade()){
                topNames.add(names.get(i).getName());
            }
        }
        return topNames;
    }

   /**
    * Returns an ArrayList of Strings of names that have been getting more
    * popular every decade. The Strings  must be in sorted
    * order based on the name of the NameRecords.
    * @return A list of the names that have been getting more popular in
    * every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list.
    */
    public ArrayList<String> alwaysMorePopular() {
        ArrayList<String> topNames = new ArrayList<String>();
        for (int i = 0; i < names.size(); i++){
            if (names.get(i).alwaysMorePopular()){
                topNames.add(names.get(i).getName());
            }
        }
        return topNames;
    }

   /**
    * Returns an ArrayList of Strings of names that have been getting less
    * popular every decade. The Strings  must be in sorted order based
    * on the name of the NameRecords.
    * @return A list of the names that have been getting less popular in
    * every decade. The list is in sorted ascending
    * order. If there are no NameRecords that meet this
    * criteria returns an empty list.
    */
    public ArrayList<String> alwaysLessPopular() {
        ArrayList<String> topNames = new ArrayList<String>();
        for (int i = 0; i < names.size(); i++){
            if (names.get(i).alwaysLessPopular()){
                topNames.add(names.get(i).getName());
            }
        }
        return topNames;
    }

    /**
     * Returns an ArrayList of Strings of names that are late 
     * bloomers, which are names that were unranked in every 
     * decade during the first half of the data but was ranked 
     * in every decade during the second half. If there is an 
     * odd number of decades, the middle decade is included in the
     * second half. The returned names are in sorted ascending order.
     * @return a sorted list of names that meet the late bloomer reqs,
     * and returns an empty list if no names qualify
     */
    public ArrayList<String> lateBloomers(){
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < names.size(); i++){
            NameRecord current = names.get(i);
            int middle = current.getNumDecades() / 2;
            boolean qualifies = true;
            for (int j = 0; j < middle; j++) {
                if (current.getRank(j) != 0) {
                    qualifies = false;
                }
            }
            for (int j = middle; j < current.getNumDecades(); j++) {
                if (current.getRank(j) == 0) {
                    qualifies = false;
                }
            }
            if (qualifies) {
                result.add(current.getName());
            }
        }
        return result;
    }

   /**
    * Return the NameRecord in this Names object that matches the given String ignoring case.
    * <br>
    * <tt>pre: name != null</tt>
    * @param name The name to search for.
    * @return The name record with the given name or null if no NameRecord in this Names
    * object contains the given name.
    */
    public NameRecord getName(String name) {
        if (name == null) {
           throw new IllegalArgumentException("The parameter name cannot be null");
	    }
        for (int i = 0; i < names.size(); i++){
            if (names.get(i).getName().equalsIgnoreCase(name)){
                return names.get(i);
            }
        }
        return null;
    }
}
