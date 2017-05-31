package chiffre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Chiffre {

	private static Chiffre doStuff;
	private char[] engLetterFreq = { ' ', 'e', 't', 'a', 'o', 'i', 'n', 's', 'h', 'r', 'd', 'l', 'c', 'u', 'm', 'w',
			'f', 'g', 'y', 'p', 'b', 'v', 'k', 'j', 'x', 'q', 'z' };
	private static String chiffre = "xureveoulrefknpavberweqwuegwceappergvleovlrazfbevdfaewmedfbwubjvbyenpfalusfeabdensavlvbyenavbecalexwsbeabdevecvppeyvtfeqwueaejwonpfrfeajjwubrewmergfelqlrfoeabdefknwubdergfeajruaperfajgvbylewmergfeysfarefknpwsfsewmergfersurgergfeoalrfsexuvpdfsewmeguoabegannvbfll";
	private static String text = "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system and expound the actual teachings of the great explorer of the truth the master builder of human happiness";
	private static String word = "Aaron";

	public Chiffre() {
	}

	public static void main(String[] args) throws IOException {
		doStuff = new Chiffre();
		// String userInput = doStuff.userInput();
		int[][] charFrequency = doStuff.analyse(chiffre.toLowerCase());
		doStuff.decipher(doStuff.sortArray(charFrequency), chiffre.toLowerCase());

		dictionary("./wordlist.txt");
	}

	private String userInput() throws IOException {
		System.out.println("Type your stuff here: ");
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		String input = bfr.readLine();

		return input;
	}

	private int[][] analyse(String input) {

		int alphabetSize = 27;

		char[] charArray = input.toCharArray();
		int[][] statistics = new int[2][alphabetSize];
		for (int i = 0; i < alphabetSize; i++) {
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
			System.out.println((char) statistics[0][i] + " " + statistics[1][i]);
		}

		return statistics;
	}

	private int[][] sortArray(int[][] charFrequency) {

		int length = 27;

		int[][] sortedByValue = new int[2][length];

		for (int i = 0; i < length; i++) {
			int maxValue = 0;
			int theChar = 0;
			int posOfMaxValue = 0;
			for (int curr = 0; curr < length; curr++) {
				if (charFrequency[1][curr] > maxValue) {
					theChar = charFrequency[0][curr];
					maxValue = charFrequency[1][curr];
					posOfMaxValue = curr;

				}
			}
			sortedByValue[0][i] = theChar;
			sortedByValue[1][i] = maxValue;
			charFrequency[1][posOfMaxValue] = 0;
		}

		return sortedByValue;
	}

	private void decipher(int[][] charFrequency, String chiffre) {

		System.out.println("");
		int length = 27;
		char[] cipherText = chiffre.toCharArray();
		char[] finalText = new char[chiffre.length()];

		System.out.println("Sorted: ");
		for (int i = 0; i < length; i++) {
			System.out.println((char) charFrequency[0][i] + " " + charFrequency[1][i]);
		}

		for (int n = 0; n < engLetterFreq.length; n++) {
			char currStatisticsChar = engLetterFreq[n];
			char currAnalysisChar = (char) charFrequency[0][n];
			for (int i = 0; i < cipherText.length; i++) {
				if (cipherText[i] == currAnalysisChar) {
					finalText[i] = currStatisticsChar;
				}
			}
		}

		System.out.println("");
		System.out.println("Chiffre: ");
		System.out.println(chiffre + "\n");
		System.out.println("Decipher: ");
		String decFinalText = "";

		for (char i : finalText) {

			// System.out.print(i);
			decFinalText += i;
		}
		String[] decWordsArray = decFinalText.split("");
		System.out.print(decFinalText);

		System.out.println("\n");
		System.out.println("Should be: ");
		System.out.println(text);
	}

	public static void dictionary(String file) {
		ReadFile rf = new ReadFile();

		// The text file location of your choice
		String filename = file;

		try {
			String[] lines = rf.readLines(filename);

			for (String line : lines) {
				if (line.equals(word)) {
					System.out.println(line);
				}
			}
		} catch (IOException e) {
			// Print out the exception that occurred
			System.out.println("Unable to create " + filename + ": " + e.getMessage());
		}
	}
}