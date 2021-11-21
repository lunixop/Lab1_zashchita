package Lab1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Frequancy {
	
	public void Decoder(String EncryptedText, String SourceText) throws IOException {
		
		HashMap<Integer, Character> MapSource = new HashMap<>();
		HashMap<Integer, Character> MapDe = new HashMap<>();
		ArrayList<Integer> FreqS = new ArrayList<>();
		ArrayList<Integer> FreqD = new ArrayList<>();
		String alpha = "אבגדהו¸זחטיךכלםמןנסעףפץצקרשתûü‎‏ÿ";
		
		for(int i = 0; i < alpha.length(); i ++) {
			int freq = 0;
			for (int j = 0; j < SourceText.length(); j ++) {

				if(alpha.charAt(i) == SourceText.toLowerCase().charAt(j))  freq ++; 
				
			}
			
			while (MapSource.get(freq) != null) freq--;
			FreqS.add(freq);
			MapSource.put(freq, alpha.charAt(i));
			
			freq = 0;
			for (int j = 0; j < EncryptedText.length(); j ++) {
				
				if(alpha.charAt(i) == EncryptedText.charAt(j))  freq ++; 
				
			}
			
			while (MapDe.get(freq) != null) freq--;
			MapDe.put(freq, alpha.charAt(i));
			FreqD.add(freq);
			
		}
		
		Collections.sort(FreqS);
		Collections.reverse(FreqS);
		Collections.sort(FreqD);
		Collections.reverse(FreqD);
		StringBuilder FreqAlpha = new StringBuilder();
		StringBuilder DeFreqAlpha = new StringBuilder();
		
		for(int i : FreqS) FreqAlpha.append(MapSource.get(i));
		for(int i : FreqD) DeFreqAlpha.append(MapDe.get(i));
		System.out.println(FreqAlpha + "\n" + DeFreqAlpha);
		
		StringBuilder DecryptedText = new StringBuilder(EncryptedText);
		for(int i = 0; i < DecryptedText.length(); i ++) {
			
			int j = ReturnIndex(DecryptedText.charAt(i), DeFreqAlpha.toString());
			if (j != - 1)
				DecryptedText.replace(i, i + 1, Character.toString(FreqAlpha.charAt(j)) );  
			
		}
		
		FileWriter writeFile = new FileWriter("C:\\Users\\User\\eclipse-workspace\\Lab1_zashchita\\src\\Lab1\\DecryptedTextFreq.txt");
		writeFile.write(DecryptedText.toString());
		writeFile.close();
		
		
		
	}
	
	private int ReturnIndex( char letter, String DeFreqAlpha ) {
		
		for (int j = 0; j < DeFreqAlpha.length(); j ++) if ( DeFreqAlpha.charAt(j) == letter) return j;
			
		return -1;
		
	}
	
}
