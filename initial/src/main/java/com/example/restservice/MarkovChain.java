import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Set;
import java.util.ArrayList;

public class MarkovChain {
    private String inputStr;
    private int ngrams;
    private Map<String, HashMap<String, Integer>> freqTable = new HashMap<>();

    MarkovChain(String text, int n) {
        this.inputStr = text;
        this.ngrams = n;

        makeFreqTable();
    }

    /*
    // Make an n-gram frequency table that maps n-char string to nextCharFreq table.
    */
    void makeFreqTable() {
        for(int i = 0; i <= inputStr.length() - ngrams; i++){
            // Get an n-char string from current index
            StringBuilder curS = new StringBuilder();
            for (int k = i; k < i + ngrams; k++){
                curS.append(inputStr.charAt(k));
            }
            String curStr = curS.toString();

            HashMap<String, Integer> nextCharFreq = new HashMap<>();

            if (freqTable.containsKey(curStr)) {
                nextCharFreq = freqTable.get(curStr);
            }

            // Make a table (nextCharFreq) that maps the next char to the occurrence time of the char
            if (i < inputStr.length() - ngrams) {
                StringBuilder nextS = new StringBuilder();
                nextS.append(inputStr.charAt(i + ngrams));
                String nextStr = nextS.toString();
                if (!nextCharFreq.containsKey(nextStr)){
                    nextCharFreq.put(nextStr, 1);
                } else {
                    int value = nextCharFreq.get(nextStr);
                    value++;
                    nextCharFreq.put(nextStr, value);
                }
            }
            freqTable.put(curStr, nextCharFreq);
        }
    }

    /*
    // Returns a string that has highest possibility.
    */
    public String generateNextChar(String str) {
        TreeMap<Integer, ArrayList<String>> nextStrPool = makeNextStringPool(str);

        Character space = ' ';

        // The last key in TreeMap has the highest occurrence times
        Integer lastKey = nextStrPool.lastKey();
        ArrayList<String> strPool1 = nextStrPool.get(lastKey);
        char firstC = strPool1.get(0).charAt(0);

        if (space.equals(firstC))  {
            if (strPool1.size() > 1) {
                return strPool1.get(1);
            } else {
                // Get one key lower than the most frequent one if the next string with the highest possibility is a space
                Integer lowerKey = nextStrPool.lowerKey(lastKey);
                ArrayList<String> strPool2 = nextStrPool.get(lowerKey);
                return strPool2.get(0);
            }
        }

        return strPool1.get(0);
    }

    /*
    // Convert nextCharFreq to a table that maps occurrence time to an array of strings
    */
    TreeMap<Integer, ArrayList<String>> makeNextStringPool(String str) {
        TreeMap<Integer, ArrayList<String>> nextStrPool = new TreeMap<>();

        if (freqTable.containsKey(str)){
            HashMap<String, Integer> nextCharFreq = freqTable.get(str);

            Set<Map.Entry<String, Integer>> charFreqEntrySet = nextCharFreq.entrySet();

            for (Map.Entry<String, Integer> entry: charFreqEntrySet) {
                int occurTimes = entry.getValue();

                ArrayList<String> strPool = new ArrayList<>();
                if (!nextStrPool.containsKey(occurTimes)) {
                    strPool.add(entry.getKey());
                } else {
                    strPool = nextStrPool.get(occurTimes);
                    strPool.add(entry.getKey());
                }
                nextStrPool.put(occurTimes, strPool);
            }
        } else {
            System.out.println("Invalid input.");
        }

        return nextStrPool;
    }
}