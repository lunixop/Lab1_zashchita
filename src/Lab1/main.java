package Lab1;

import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		
		GenerateCode CezarCode = new GenerateCode();
		String EncryptedText = CezarCode.CreateEncryptedText();
		String SourceText = CezarCode.ReturnSource();
		Frequancy decoder = new Frequancy();
		decoder.Decoder(EncryptedText, SourceText);
		BiFrequancy Bidecoder = new BiFrequancy();
		Bidecoder.decoder(EncryptedText, SourceText);
		
	}
	
}
