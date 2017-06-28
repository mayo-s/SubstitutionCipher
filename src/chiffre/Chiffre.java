package chiffre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
//import java.util.Arrays;
//import java.util.Collections;

public class Chiffre {

	private static Chiffre doStuff;
	private static int alphabetSize;
	private char[] engLetterFreq = { ' ', 'e', 't', 'a', 'o', 'i', 'n', 's', 'h', 'r', 'd', 'l', 'c', 'u', 'm', 'w',
			'f', 'g', 'y', 'p', 'b', 'v', 'k', 'j', 'x', 'q', 'z' };
	private static String chiffre;
	private static String text;
	private static char[] correctLetters; // [0] == Space, [1..] == a to z
	private static String dictPath;
	private static String[] dictionary;
	private static ArrayList<ArrayList<String>> repeatedCharWordArray;

	private char[][] charMatchingList = { /* A */ { 'a' },
			/* B */ { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' },
			/* C */ { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' },
			/* D */ { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' },
			/* E */ { 'e' },
			/* F */ { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' },
			/* G */ { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' },
			/* H */ { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' },
			/* I */ { 'i' },
			/* J */ { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' },
			/* K */ { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' },
			/* L */ { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' },
			/* M */ { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' },
			/* N */ { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' },
			/* O */ { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' },
			/* P */ { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' },
			/* Q */ { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' },
			/* R */ { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' },
			/* S */ { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' },
			/* T */ { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' },
			/* U */ { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' },
			/* V */ { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' },
			/* W */ { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' },
			/* X */ { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' },
			/* Y */ { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' },
			/* Z */ { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z' } };

	public Chiffre() {
		alphabetSize = 27;
		chiffre = "xureveoulrefknpavberweqwuegwceappergvleovlrazfbevdfaewmedfbwubjvbyenpfalusfeabdensavlvbyenavbecalexwsbeabdevecvppeyvtfeqwueaejwonpfrfeajjwubrewmergfelqlrfoeabdefknwubdergfeajruaperfajgvbylewmergfeysfarefknpwsfsewmergfersurgergfeoalrfsexuvpdfsewmeguoabegannvbfll";
		text = "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system and expound the actual teachings of the great explorer of the truth the master builder of human happiness";
		correctLetters = new char[alphabetSize];
		dictPath = "./src/wordlist.txt";
		repeatedCharWordArray = new ArrayList<ArrayList<String>>();
	}

	public static void main(String[] args) throws IOException {
		doStuff = new Chiffre();
		// String userInput = doStuff.userInput();
		int[][] charFrequency = doStuff.analyse(chiffre.toLowerCase());
		doStuff.decipher(doStuff.sortArray(charFrequency), chiffre.toLowerCase());
	}

	private String userInput() throws IOException {
		System.out.println("Type your stuff here: ");
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		String input = bfr.readLine();

		return input;
	}

	private int[][] analyse(String input) {

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

		System.out.println("Chiffre Input: " + chiffre);
		System.out.println("Analysis: ");
		for (int i = 0; i < alphabetSize; i++) {
			System.out.println((char) statistics[0][i] + " " + statistics[1][i]);
		}
		return statistics;
	}

	private int[][] sortArray(int[][] charFrequency) {

		int[][] sortedByValue = new int[2][alphabetSize];

		for (int i = 0; i < alphabetSize; i++) {
			int maxValue = 0;
			int theChar = 0;
			int posOfMaxValue = 0;
			for (int curr = 0; curr < alphabetSize; curr++) {
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
		char[] cipherText = chiffre.toCharArray();
		char[] finalText = new char[chiffre.length()];
		// char[] finalText2 = new char[chiffre.length()];

		System.out.println("Sorted: ");
		for (int i = 0; i < alphabetSize; i++) {
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
		System.out.println("Decipher eng Stat: ");
		String decFinalText = "";

		for (char i : finalText) {
			// System.out.print(i);
			decFinalText += i;
		}
		String[] decWordsArray = decFinalText.split(" ");

		System.out.println(decFinalText);
		System.out.println("\nShould be: ");
		System.out.println(text);

		dictionary(dictPath, ""); // initialize dictionary
		findOneLetterWord(decWordsArray);

		String finalTextTemp = "";
		boolean replaced = false;
		for (char ch : finalText) {
			for (int index = 0; index < correctLetters.length; index++) {
				if ((int) correctLetters[index] >= 97 && (int) correctLetters[index] <= 122) {
					if ((char) index + 96 == ch) {
						finalTextTemp += correctLetters[index];
						replaced = true;
						break;
					}
				}
			}
			if (!replaced) {
				finalTextTemp += ch;
			}
			replaced = false;
		}
		System.out.println("\nAfter one letter word correction:");
		System.out.println(finalTextTemp);

		String[] decWordsArray2 = finalTextTemp.split(" ");

		findWordPattern(decWordsArray2);

		// System.out.print("Correct letters: \n");
		// System.out.println(correctLetters);
		// System.out.print("Letter frequency: \n");
		// System.out.println(engLetterFreq);
		// System.out.println(repeatedCharWordArray);
		removeFalseMatches(repeatedCharWordArray);

		for (char[] charList : charMatchingList) {
			System.out.println("");
			for (char charMatch : charList) {
				System.out.print(charMatch + ", ");
			}
		}

	}

	public static void dictionary(String file, String word) {
		ReadFile rf = new ReadFile();

		// The text file location of your choice
		String filename = file;

		try {
			String[] lines = rf.readLines(filename);
			dictionary = rf.readLines(filename); // fix me
			for (String line : lines) {
				if (line.equals(word)) {
					System.out.println("Found word: " + line);
				}
			}
		} catch (IOException e) {
			// Print out the exception that occurred
			System.out.println("Unable to create " + filename + ": " + e.getMessage());
		}
	}

	private void findOneLetterWord(String[] decWordsArray) {
		// System.out.println("\n");
		int[] oneLetterWords = { 0, 0, 0, 0 };

		for (String word : decWordsArray) {

			if (word.length() == 1) {
				// System.out.println("word: " + word);
				// System.out.println("word.charAt(0): " + word.charAt(0));

				if (oneLetterWords[0] <= 0) {
					oneLetterWords[0] = word.charAt(0);
					oneLetterWords[1]++;
				} else if (oneLetterWords[0] == word.charAt(0)) {
					oneLetterWords[1]++;
				} else if (oneLetterWords[2] <= 0) {
					oneLetterWords[2] = word.charAt(0);
					oneLetterWords[3]++;
				} else if (oneLetterWords[2] == word.charAt(0)) {
					oneLetterWords[3]++;
				}
			}
		}
		/*
		 * if (oneLetterWords[0] == 'a') System.out.println("a!: " + (char)
		 * oneLetterWords[0] + " i: " + (char) oneLetterWords[2]); else if
		 * (oneLetterWords[2] == 'a') System.out.println("a!: " + (char)
		 * oneLetterWords[2] + " i: " + (char) oneLetterWords[0]); else if
		 * (oneLetterWords[0] == 'i') System.out.println("a: " + (char)
		 * oneLetterWords[2] + " i!: " + (char) oneLetterWords[0]); else if
		 * (oneLetterWords[2] == 'i') System.out.println("a: " + (char)
		 * oneLetterWords[0] + " i!: " + (char) oneLetterWords[2]); else if
		 * (oneLetterWords[1] >= oneLetterWords[3]) System.out.println("a: " +
		 * (char) oneLetterWords[0] + " i: " + (char) oneLetterWords[2]); else
		 * if (oneLetterWords[1] < oneLetterWords[3]) System.out.println("a: " +
		 * (char) oneLetterWords[2] + " i: " + (char) oneLetterWords[0]);
		 */
		if (oneLetterWords[0] == 'a') {
			correctLetters[1] = (char) oneLetterWords[0];
			correctLetters[9] = (char) oneLetterWords[2];
			correctLetters[(int) oneLetterWords[0] - 96] = 'a';
			correctLetters[(int) oneLetterWords[2] - 96] = 'i';
		} else if (oneLetterWords[2] == 'a') {
			correctLetters[1] = (char) oneLetterWords[2];
			correctLetters[9] = (char) oneLetterWords[0];
			correctLetters[(int) oneLetterWords[2] - 96] = 'a';
			correctLetters[(int) oneLetterWords[0] - 96] = 'i';

		} else if (oneLetterWords[0] == 'i') {
			correctLetters[1] = (char) oneLetterWords[2];
			correctLetters[9] = (char) oneLetterWords[0];
			correctLetters[(int) oneLetterWords[2] - 96] = 'a';
			correctLetters[(int) oneLetterWords[0] - 96] = 'i';
		} else if (oneLetterWords[2] == 'i') {
			correctLetters[1] = (char) oneLetterWords[0];
			correctLetters[9] = (char) oneLetterWords[2];
			correctLetters[(int) oneLetterWords[0] - 96] = 'a';
			correctLetters[(int) oneLetterWords[2] - 96] = 'i';
		} else if (oneLetterWords[1] >= oneLetterWords[3]) {
			correctLetters[1] = (char) oneLetterWords[0];
			correctLetters[9] = (char) oneLetterWords[2];
		} else if (oneLetterWords[1] < oneLetterWords[3]) {
			correctLetters[1] = (char) oneLetterWords[2];
			correctLetters[9] = (char) oneLetterWords[0];
		}
		// System.out.println("a: " + correctLetters[1] + " i: " +
		// correctLetters[9]);
		// System.out.println("a: " + correctLetters[1] + " i: " +
		// correctLetters[9]);
	}

	private void findWordPattern(String[] decWordsArray) {
		for (String word : decWordsArray) {
			if (word.length() > 1) {
				int counter = 0;
				int charCounter = 1;
				boolean hasRepChar = false;
				int[] repeatedChar = new int[word.length()];
				int[] charIndex = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						-1, -1, -1, -1, -1 };
				int[] charId = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						-1, -1, -1, -1 };
				for (char ch : word.toCharArray()) {
					if (charIndex[ch - 'a'] < 0) {
						charIndex[ch - 'a'] = counter;
					} else {
						if (charId[ch - 'a'] < 0) {
							charId[ch - 'a'] = charCounter++;
						}
						repeatedChar[counter] = charId[ch - 'a'];
						repeatedChar[charIndex[ch - 'a']] = charId[ch - 'a'];
						hasRepChar = true;
					}
					counter++;
				}

				if (hasRepChar) {
					ArrayList<String> matchingStrings = new ArrayList<String>();
					matchingStrings.add(word);
					for (String line : dictionary) {
						boolean isUnusable = false;
						for (char ch : line.toLowerCase().toCharArray()) {
							if (ch < 'a' || ch > 'z') {
								isUnusable = true;
							}
						}
						if (isUnusable) {
							continue;
						}
						if (line.length() == word.length()) {
							int[] repeatedChar2 = new int[word.length()];
							int[] charIndex2 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
									-1, -1, -1, -1, -1, -1, -1, -1 };
							counter = 0;
							charCounter = 1;
							hasRepChar = false;
							int[] charId2 = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
									-1, -1, -1, -1, -1, -1, -1, -1 };
							for (char ch : line.toLowerCase().toCharArray()) {
								if (charIndex2[ch - 'a'] < 0) {
									charIndex2[ch - 'a'] = counter;
								} else {
									if (charId2[ch - 'a'] < 0) {
										charId2[ch - 'a'] = charCounter++;
									}
									repeatedChar2[counter] = charId2[ch - 'a'];
									repeatedChar2[charIndex2[ch - 'a']] = charId2[ch - 'a'];
									hasRepChar = true;
								}
								counter++;
							}

							if (hasRepChar) {
								boolean hasMatch = true;
								for (int index = 0; index < word.length(); index++) {
									if (repeatedChar[index] != repeatedChar2[index]) {
										hasMatch = false;
										break;
									}
								}
								if (hasMatch) {
									matchingStrings.add(line.toLowerCase());
									// System.out.println(word + " " + line);
									for (int index = 0; index < line.length(); index++) {

									}
								}
							}
						}
					}
					repeatedCharWordArray.add(matchingStrings);
				}
				// dictionary(dicPath,word);
			}
		}
	}

	private void removeFalseMatches(ArrayList<ArrayList<String>> repeatedCharWordArray) {

		for (int m = 0; m < repeatedCharWordArray.size(); m++) {
			ArrayList<String> arrList = repeatedCharWordArray.get(m);
			// for (Iterator<ArrayList<String>> arrList =
			// repeatedCharWordArray.iterator(); arrList.hasNext();) {
			String origWord = arrList.get(0);
			// for ( String matchingWord : (Iterable<matchingWord>) () ->
			// arrList) {
			for (int n = 0; n < repeatedCharWordArray.get(m).size(); n++) {
				String word2compare = arrList.get(n);
				if (origWord == word2compare)
					continue;

				char[] origChars = origWord.toCharArray();
				char[] matchingChars = word2compare.toCharArray();

				for (int origIndex = 0; origIndex < origChars.length; origIndex++) {
					// System.out.print(((int)matchingChars[origIndex]-97) + "
					// gleich " + matchingChars[origIndex] + ", ");
					char[] possibleChars = charMatchingList[(int) matchingChars[origIndex] - 97];

						boolean hasMatch = false;

						for (int charIndex = 0; charIndex < possibleChars.length; charIndex++) {
							
							if(possibleChars.length == 1 && origIndex == charIndex && origChars[origIndex] == 'a' && origChars[origIndex] != matchingChars[charIndex]){
								System.out.println("posschar: " + possibleChars[0]);
								System.out.println(origWord + " " + word2compare + " " + origChars[origIndex] + " " + matchingChars[charIndex]);

								repeatedCharWordArray.get(m).remove(n);
								break;
							}
							
							else if (possibleChars[charIndex] == origChars[origIndex]) {
								hasMatch = true;

								break;
							}
						}

						if (!hasMatch) {
							// System.out.println(repeatedCharWordArray.get(getIndex));

							repeatedCharWordArray.get(m).remove(word2compare);
							// System.out.println(repeatedCharWordArray.get(getIndex));
							break;
						}
					}
			}

			if (repeatedCharWordArray.get(m).size() <= 9)
				System.out.println(repeatedCharWordArray.get(m));
		}
	}

	// Liste der matching Words aller anderen Wörter durchgehen. RegEx:
	// Teilmenge
	// in der entdeckten Buchstaben müssen Ergebnis liefern. ansonsten remove()
}