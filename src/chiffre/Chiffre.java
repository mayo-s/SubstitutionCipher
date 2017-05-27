package chiffre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Chiffre {

	private static Chiffre doStuff;
	private char[] engLetterFreq = { 'e', 't', 'a', 'o', 'i', 'n', 's', 'h', 'r', 'd', 'l', 'c', 'u', 'm', 'w', 'f',
			'g', 'y', 'p', 'b', 'v', 'k', 'j', 'x', 'q', 'z' };
	private static String chiffre = "xureveoulrefknpavberweqwuegwceappergvleovlrazfbevdfaewmedfbwubjvbyenpfalusfeabdensavlvbyenavbecalexwsbeabdevecvppeyvtfeqwueaejwonpfrfeajjwubrewmergfelqlrfoeabdefknwubdergfeajruaperfajgvbylewmergfeysfarefknpwsfsewmergfersurgergfeoalrfsexuvpdfsewmeguoabegannvbfll";
	private static String text = "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system and expound the actual teachings of the great explorer of the truth the master builder of human happiness";

	public Chiffre() {
	}

	public static void main(String[] args) throws IOException {
		doStuff = new Chiffre();
		// String userInput = doStuff.userInput();
		int[][] charFrequency = doStuff.analyse(chiffre.toLowerCase());
		// doStuff.decipher(charFrequency, chiffre);
		System.out.println("\n");
		// int[][] charFrequency2 = doStuff.analyse(text.toLowerCase());
		// doStuff.decipher(charFrequency2, text);

	}

	private String userInput() throws IOException {
		System.out.println("Type your stuff here: ");
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		String input = bfr.readLine();

		return input;
	}

	private int[][] analyse(String input) {

		int alphabetSize = 26;

		char[] charArray = input.toCharArray();
		int[][] statistics = new int[2][alphabetSize];
		for(int i = 0; i < alphabetSize; i++ ){
			statistics[0][i] = i + 97;
		}

		for (char aChar : charArray) {
			int charArrayPos = 0;
			if (aChar >= 97 && aChar <= 122)
				charArrayPos = aChar - 97; // a - z

			statistics[1][charArrayPos]++;
		}

		System.out.println("Chiffre Inpur: " + chiffre);
		System.out.println("Analysis: ");
		for (int i = 0; i < alphabetSize; i++) {
			System.out.println((char)statistics[0][i] + " " + statistics[1][i]);
		}
		System.out.println("Sorting: ");
		return statistics;
	}

	private void decipher(int[][] charFrequency, String chiffre) {
		System.out.println("");
		char[] cipherText = chiffre.toCharArray();
		char[] finalText = new char[chiffre.length()];
		
		for(char currChar : cipherText){
			
		}

		for (int[] i : charFrequency) {
			for (int n = chiffre.length(); n >= 0; n--) {

				System.out.print("");
			}
		}
	}
}
