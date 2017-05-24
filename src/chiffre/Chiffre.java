package chiffre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Chiffre {

	private static Chiffre doStuff;
	private char[] engLetterFreq = {'e', 't', 'a', 'o', 'i', 'n', 's', 'h', 'r', 'd', 'l', 'c', 'u', 'm', 'w', 'f', 'g', 'y', 'p', 'b', 'v', 'k', 'j', 'x', 'q', 'z'};
	private static String chiffre = "xureveoulrefknpavberweqwuegwceappergvleovlrazfbevdfaewmedfbwubjvbyenpfalusfeabdensavlvbyenavbecalexwsbeabdevecvppeyvtfeqwueaejwonpfrfeajjwubrewmergfelqlrfoeabdefknwubdergfeajruaperfajgvbylewmergfeysfarefknpwsfsewmergfersurgergfeoalrfsexuvpdfsewmeguoabegannvbfll";
	private static String text = "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system and expound the actual teachings of the great explorer of the truth the master builder of human happiness";
	public Chiffre(){
	}

	public static void main (String[] args) throws IOException{
		doStuff = new Chiffre();
    	//String userInput = doStuff.userInput();  
    	int[] charFrequency = doStuff.analyse(chiffre.toLowerCase());
    	doStuff.decipher(charFrequency, chiffre);
    	System.out.println("\n");
    	int[] charFrequency2 = doStuff.analyse(text.toLowerCase());
    	doStuff.decipher(charFrequency2, text);
    	
    }
	
	private String userInput() throws IOException {
		System.out.println("Type your stuff here: ");
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        String input = bfr.readLine();
        
        return input;
	}
	
	private int[] analyse(String input){
		
		char[] charArray = input.toCharArray();
		int[] statistics = new int[27];
		
		for(char aChar : charArray){
			int charArrayPos = 0;
			//if(aChar > 65 && aChar <= 90) charArrayPos = aChar - 65;
			if(aChar > 97 && aChar <= 122) charArrayPos = aChar - 97;
			if(aChar == 32) charArrayPos = 26;
			
			statistics[charArrayPos]++;	
		}
		
		for(int i : statistics){
			System.out.print(i + " ");
		}
		
		return statistics;		
	}
	
	private void decipher(int[] charFrequencies, String chiffre){
		System.out.println("");
		Arrays.sort(charFrequencies);
//		for(int i : charFrequencies){
//			System.out.print(i + " ");
//		}
		
		for (int i : charFrequencies){
			for(int n = chiffre.length(); n >= 0; n--){
				
				System.out.print("");
			}
		}
	}
}
