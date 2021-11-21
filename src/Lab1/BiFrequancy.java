package Lab1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BiFrequancy {
	
	public void decoder(String EncryptedText, String SourceText) throws IOException {
		
		ArrayList<Integer> MidS = new ArrayList<>();
		ArrayList<Integer> MidD = new ArrayList<>();
		ArrayList<String> MidKS = new ArrayList<>();
		ArrayList<String> MidKD = new ArrayList<>();
		HashMap<String, Integer> BiFreqS = new HashMap<>();
		HashMap<String, Integer> BiFreqD = new HashMap<>();
		HashMap<Integer, String> BiFreqSRev = new HashMap<>();
		HashMap<Integer, String> BiFreqDRev = new HashMap<>();
		String alpha = "אבגדהו¸זחטיךכלםמןנסעףפץצקרשתûü‎‏ÿ";
		SourceText = SourceText.toLowerCase();
		
		StringBuilder DecryptedText = new StringBuilder(EncryptedText);
		
		for(int i = 0; i < SourceText.length()-1; i ++) {
			
			String bigram = SourceText.substring(i, i+2).toLowerCase();
			
			boolean flag1 = false;
			boolean flag2 = false;
			for (int j = 0; j < alpha.length(); j ++) {
				if (bigram.charAt(0) == alpha.charAt(j)) flag1 = true;
				if (bigram.charAt(1) == alpha.charAt(j)) flag2 = true;
			}
			
			if(flag1 && flag2) {
				if(BiFreqS.get(bigram) != null) BiFreqS.put(bigram, BiFreqS.get(bigram) + 1);
				else BiFreqS.put(bigram, 1);
			}
			
		}
		
		for(int i = 0; i < DecryptedText.length()-1; i ++) {
			
			String bigram = DecryptedText.substring(i, i+2).toLowerCase();
			
			boolean flag1 = false;
			boolean flag2 = false;
			for (int j = 0; j < alpha.length(); j ++) {
				
				if (bigram.charAt(0) == alpha.charAt(j)) flag1 = true;
				if (bigram.charAt(1) == alpha.charAt(j)) flag2 = true;
			}
			
			if(flag1 && flag2) {
				if(BiFreqD.get(bigram) != null) BiFreqD.put(bigram, BiFreqD.get(bigram) + 1);
				else BiFreqD.put(bigram, 1);
			}
			
		}
		
		for(String s : BiFreqS.keySet())
			BiFreqSRev.put(BiFreqS.get(s), s);
		System.out.println(BiFreqSRev);
		for(int i = 0; i < DecryptedText.length()-1; i ++) {
			int value = -1;
			String bi = DecryptedText.substring(i, i + 2);
			if(BiFreqD.get(bi) != null) {
			value = BiFreqD.get(bi);
			}
			if(BiFreqSRev.get(value) != null) {DecryptedText.replace(i, i + 2, BiFreqSRev.get(value)); }
			
		}
		
		FileWriter writeFile = new FileWriter("C:\\Users\\User\\eclipse-workspace\\Lab1_zashchita\\src\\Lab1\\DecryptedTextBiFreq.txt");
		writeFile.write(DecryptedText.toString());
		writeFile.close();
		
		System.out.println(BiFreqS + "\n" + BiFreqD);
		
		
	}
	
}
