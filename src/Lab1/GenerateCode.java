package Lab1;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GenerateCode {
	String Text;
	public String CreateEncryptedText() throws IOException {
		
		System.out.println("Enter the key to crypt the read text ");
		
		Scanner scanner = new Scanner(System.in); //Вводим ключ для шифрации
		String Key = scanner.nextLine();
		
    	File file = new File("C:\\Users\\User\\eclipse-workspace\\Lab1_zashchita\\src\\Lab1\\Text.txt");
		FileReader readFile = new FileReader(file); //Считываем строку в файле
		BufferedReader buffer = new BufferedReader(readFile);
		String textLine = buffer.readLine();
		
		StringBuilder SourceText = new StringBuilder(textLine);
		while (textLine != null) { SourceText.append(textLine + "\n"); textLine = buffer.readLine(); } //Добавляем \n, чтобы не было мессива в одну строчку.
		
		Text = SourceText.toString();
		readFile.close();
		
		System.out.println("Enter the key position that starts with 0");
		int KeyPosition = scanner.nextInt();
		scanner.close();
		
		String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
		String NewAlpha = ReturnNewAlpha(alphabet, Key, KeyPosition);
		String Mid = SourceText.toString().toLowerCase();
		System.out.println(alphabet + "\n" + NewAlpha);
		
		for (int i = 0; i < SourceText.length(); i ++) {
			int j = ReturnIndex(Mid.charAt(i), alphabet.toString());
			if (j != -1) { SourceText.replace(i, i + 1, Character.toString( NewAlpha.charAt(j) )); }
		}
			
		FileWriter writeFile = new FileWriter("C:\\Users\\User\\eclipse-workspace\\Lab1_zashchita\\src\\Lab1\\EncryptedText.txt");
		writeFile.write(SourceText.toString());
		writeFile.close();
		buffer.close();
		return SourceText.toString();
	}
	
	private String ReturnNewAlpha (String alphabet, String Key, int KeyPosition) {
		
		StringBuilder NewAlpha = new StringBuilder();
		StringBuilder AlpWithNoKey = new StringBuilder(alphabet);
		StringBuilder KeyBuilder = new StringBuilder(Key);
		KeyBuilder.append(" ");
		
		for (int i = 0; i < KeyBuilder.length()-1; i ++ ) {

			for (int k = i + 1; k < KeyBuilder.length(); k ++ )  { 
				
				if(KeyBuilder.charAt(i) == KeyBuilder.charAt(k)) 
					{ KeyBuilder.delete(k, k + 1); k--;}

				}
		
		}
			
		for(int i = 0; i <= 32; i++) NewAlpha.append(" ");
		NewAlpha.replace(KeyPosition, KeyBuilder.length() + KeyPosition, KeyBuilder.toString());
		int middle = KeyBuilder.length();
		int m = KeyPosition + KeyBuilder.length()-1;
		for(int i = 0; i < middle; i ++) {
			
			for(int k = 0; k < AlpWithNoKey.length(); k ++) {

				if (KeyBuilder.charAt(0) == AlpWithNoKey.charAt(k)) { AlpWithNoKey.delete(k, k + 1); KeyBuilder.delete(0, 1); }
				
			}
			
		}
		int j = 0;
		while (m != KeyPosition) {
			if (m > 32) m = 0;
			NewAlpha.replace(m, m+1, Character.toString( AlpWithNoKey.charAt(j) ));
			j ++;
			m ++;
			
		}
		
		return NewAlpha.toString();
	}
	
	public String ReturnSource() {
		
		return Text;
		
	}
	
	private int ReturnIndex(char letter, String Alpha) {
		
		for(int j = 0; j < Alpha.length(); j ++) { if (letter == Alpha.charAt(j)) return j; }
		
		return -1;
		
	}
	
}
