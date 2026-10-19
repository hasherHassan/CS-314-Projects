import java.util.ArrayList;

public class NameRecord implements Comparable<NameRecord>{
    private String name;
    private int baseDecade;
    private ArrayList<Integer> rankings;

    public NameRecord(String name, int decade, ArrayList<Integer> rankings){
        this.name = name;
        this.baseDecade = decade;
        this.rankings = new ArrayList<Integer>(rankings);
    }

    public String getName(){
        return name;
    }

    public int getDecade(){
        return baseDecade;
    }

    public int getNumDecades(){
        return rankings.size();
    }

    public int getRank(int decade){
        return rankings.get(decade);
    }

    public int getBestDecade(){
        int bestRank = Integer.MAX_VALUE;
        int bestDecade = baseDecade;
        for (int i = 0; i < rankings.size(); i++){
            int currentRank = rankings.get(i);
            if (currentRank != 0 && currentRank <= bestRank){
                bestRank = currentRank;
                bestDecade = baseDecade + i * 10;
            }
        }
        return bestDecade;
    }

    public int getRankedDecades(){
        int count = 0;
        for (int rank : rankings) {
            if (rank != 0) {
                count++;
            }
        }
        return count;
    }

    public boolean isRankedEveryDecade(){
        return getRankedDecades() == rankings.size();
    }

    public boolean isRankedOnlyOneDecade(){
        return getRankedDecades() == 1;
    }

    public boolean alwaysMorePopular(){
        for (int i = 1; i < rankings.size(); i++){
            int currentRank = convertUnranked(rankings.get(i));
            int prevRank = convertUnranked(rankings.get(i-1));
            if (currentRank >= prevRank){
                return false;
            }
        }
        return true;
    }

    public boolean alwaysLessPopular(){
        for (int i = 1; i < rankings.size(); i++){
            int currentRank = convertUnranked(rankings.get(i));
            int prevRank = convertUnranked(rankings.get(i-1));
            if (currentRank <= prevRank){
                return false;
            }
        }
        return true;
    }

    private int convertUnranked(int rank){
        if (rank == 0) {
            return 1001;
        }
        return rank;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append(name).append("\n");
        for (int i = 0; i < rankings.size(); i++){
            int decade = baseDecade + i * 10;
            result.append(decade).append(": ").append(rankings.get(i)).append("\n");
        }
        return result.toString();
    }

    @Override
    public int compareTo(NameRecord other){
        return name.compareTo(other.name);
    }
}
