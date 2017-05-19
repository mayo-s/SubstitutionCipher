package chiffre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Chiffre {

	private static Chiffre doStuff;
	
	public Chiffre(){
	}

	public static void main (String[] args) throws IOException{
		doStuff = new Chiffre();
    	String userInput = doStuff.userInput();  
    	doStuff.analyse(userInput.toLowerCase());
    }
	
	private String userInput() throws IOException {
		System.out.println("Type your stuff here: ");
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        String input = bfr.readLine();
        
        return input;
	}
	
	private void analyse(String input){
		
		char[] charArray = input.toCharArray();
		int[] statistics = new int[27];
		
		for(char aChar : charArray){
			int charArrayPos = 0;
			//if(aChar > 65 && aChar <= 90) charArrayPos = aChar - 65;
			if(aChar > 97 && aChar <= 122) charArrayPos = aChar - 97;
			if(aChar == 32) charArrayPos = 26;
			
			statistics[charArrayPos]++; 
		}
	}	
}
