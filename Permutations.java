import java.util.*;
import java.io.*;
import java.util.*;


public class Permutations {
	
	public static final String delimiter = ",";
    public static ArrayList<ArrayList<String>> listOfWords=new ArrayList<ArrayList<String>>();
	
    public static void readFile(String csvFile) {
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = "";
            String[] tempArr;
            int i=0;
            while((line = br.readLine()) != null) {
                ArrayList<String> char_list=new ArrayList<String>();
                tempArr = line.split(delimiter);
                for(String tempStr : tempArr) {
                    String formattedStr = tempStr.replaceAll("^\'`\"|\'`\"$", "");
                    char_list.add(formattedStr);
                }
                listOfWords.add(char_list);
            }
            br.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        ArrayList<String> permutations = new ArrayList<String>();
        // Start by passing in empty string as initial value
        permutations.addAll(permutate("", listOfWords));

        // Print output
        for (int i = 0; i < permutations.size(); i++) {
            System.out.print(permutations.get(i)+", ");
        }
    }
	
    static ArrayList<String> permutate(String currentValue, ArrayList<ArrayList<String>> wordList) {
        ArrayList<String> permutations = new ArrayList<String>();

        if (wordList.size() == 1) {
            // we are down to last list, so just concatenate and return
            for (int i = 0; i< wordList.get(0).size(); i++) {
                permutations.add(currentValue + wordList.get(0).get(i));
            }
            return permutations;
        }
        ArrayList<ArrayList<String>> copy = new ArrayList(wordList);
        // Loop over top most array list and send remaining recursively
        ArrayList<String> current = copy.remove(0);
        for (int i = 0; i< current.size(); i++) {
            // concatenate each character in current list with current value and pass that as currentValue to next recursive call
            permutations.addAll(permutate(currentValue + current.get(i), copy));
        }
        return permutations;
    }
	
	  public static void main(String[] args) {
        // csv file to read
        String csvFile = args[0];
        readFile(csvFile);
    }
}
