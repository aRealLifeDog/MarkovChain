import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MarkovChain {
	
	private List<String> keyArrayList = new ArrayList<>();
	private ArrayList<String> fullArrayList = new ArrayList<String>();
	private Scanner input;
	private HashMap<String, ArrayList<String>> wordMap = new HashMap<String, ArrayList<String>>();
	
	public MarkovChain (String filename) {
		try {
			File file = new File(filename);
			input = new Scanner(file);
			String[] wordSplit;
				while (input.hasNextLine()) {
					wordSplit = input.nextLine().split(" ");
					for (int i = 0; i < wordSplit.length; i++) {	
							fullArrayList.add(wordSplit[i]);
					}
					for (int i = 0; i < fullArrayList.size(); i++) {
						String currentWord = fullArrayList.get(i);
//						System.out.println("Adding: " + currentWord);
						keyArrayList.add(currentWord);
					}
					keyArrayList = keyArrayList.stream().distinct().collect(Collectors.toList());
//					for (int i = 0; i < keyArrayList.size(); i++) {
//						System.out.println(keyArrayList.get(i));
//					}
				}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		wordMap = wordMapper();
		constructSentence();
	}

	public HashMap<String, ArrayList<String>> wordMapper() {
		HashMap<String, ArrayList<String>> wordFollowMap = new HashMap<String, ArrayList<String>>();		
		
		for (int i = 0; i < keyArrayList.size(); i++) {
			System.out.println(i + "\nFOR: " + keyArrayList.get(i));
			System.out.print("# of Locations: " + findLocations(keyArrayList.get(i), fullArrayList).size() + "\n");
			ArrayList<String> wordAfterList= new ArrayList<String>();
			
			for (int j = 0; j < findLocations(keyArrayList.get(i), fullArrayList).size(); j++) {
				int k = findLocations(keyArrayList.get(i), fullArrayList).get(j);
				
				if (k+1 == fullArrayList.size()) {
					break;
				}
				else if (k < fullArrayList.size()) {
						wordAfterList.add(fullArrayList.get(k+1));
						System.out.println("WORD AFTER: " + fullArrayList.get(k+1));
				}
					
			}
//			System.out.println();
			
			wordFollowMap.put(keyArrayList.get(i), wordAfterList);
		
		}
		
		return wordFollowMap;
	}
	
	private ArrayList<Integer> findLocations(String keyWord, ArrayList<String> fullList) {
		ArrayList<Integer> locArrayList = new ArrayList<Integer>();
		for (int i = 0; i < fullList.size(); i++) {
			if (fullList.get(i).equalsIgnoreCase(keyWord)) {
				locArrayList.add(i);
			}	
		}
		return locArrayList;
	}
	
	
	public void constructSentence() {
		StringBuilder s = new StringBuilder();
		Random rand = new Random();
		int seed;
		do {
			seed = rand.nextInt(keyArrayList.size());
		} while (!Character.isUpperCase(keyArrayList.get(seed).charAt(0)));
		int wordCount = 0;
		System.out.println("Seed: " + seed);
		int r1 = seed, r2 = 0;
		int stopSize = 50;
		while (wordCount <= stopSize) {
//			System.out.println("Seed Word: " + keyArrayList.get(r1));
//			System.out.println("Word After: " + wordMap.get(keyArrayList.get(r1)).get(r2));
			
			s.append(keyArrayList.get(r1));
			if (wordCount < stopSize) {
				s.append(" ");
			}
			r1 = findIndex(r1, r2);
			do {
				r2 = rand.nextInt(wordMap.get(keyArrayList.get(r1)).size());
			} while (Character.isUpperCase(keyArrayList.get(r2).charAt(0)) && wordMap.get(keyArrayList.get(r1)).size() > 1);
			if (keyArrayList.get(r2).charAt(0) == '>' ) {
				s.append("\n");
			}
//			System.out.println();
			wordCount++;
			if (wordCount % 20 == 0) {
				s.append("\n");
			}
		}
		s.append(".");
		String string = s.toString();
		string = string.toLowerCase();
		System.out.println(string);
	}
	
	public int findIndex(int num1, int num2) {
		String f = wordMap.get(keyArrayList.get(num1)).get(num2);
		int index = 0;
		for (int i = 0; i < keyArrayList.size(); i++) {
			if (keyArrayList.get(i).equalsIgnoreCase(f)) {
				index = i;
			}
		}
		return index;
	}
		
}
