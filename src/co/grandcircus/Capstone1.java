package co.grandcircus;

import java.util.Scanner;
import java.util.Vector;

public class Capstone1 {
	public static Scanner scn = new Scanner(System.in);
	public static void main(String[] args) {
		StringBuilder s = new StringBuilder();
		String[] stringArray = null;
		char repeatChar = ' ';
		do {
			
			try {
				System.out.println("Type in something to be translated to Pig Latin:");
				
				//Splitting up input by word
				stringArray = scn.nextLine().split(" ");
				
				//Translating each word
				for (int i = 0; i < stringArray.length; i++) {
					stringArray[i] = toPigLatin(stringArray[i]);
					System.out.print(stringArray[i] + " ");
				}
				System.out.println("");
				
				System.out.println("Would you like to translate another sentence? (y/n)");
				repeatChar = getCheckRepeatChar();
				
			}
			catch (Exception e) {
				System.out.println("Fool.  You have failed.  Try again.");
				repeatChar = 'y';
			}
			
		} while (repeatChar ==  'y');
	}
	
	/*
	 * Gets the user's response when asked if they'd like to continue
	 * */
	public static char getCheckRepeatChar() throws Exception {
		char r = scn.nextLine().charAt(0);
		if ((r != 'y') && (r != 'n')) {
			throw new Exception();
		}
		return r;
	}
	
	/*
	 * Returns a string that's a pit-latin-formatted 
	 * */
	public static String toPigLatin(String input) {
		if (!hasNonLetter(input)) {
			char temp = input.charAt(0);
			String suffix = isVowel(temp) ? "yay" : "ay";
			input = input.substring(1);
			input+= temp + handleCase(input, suffix);
		}
		return input;
	}
	
	/*
	 * Detects if the input is of mostly upper or mostly lower case, or if the user is randomly alternating between upper and lower, 
	 * and manipulates the 'ay' suffix appropriately.
	 * 
	 * Non-random case alternation:
	 * Mostly uppercase inputs will get their suffix entirely in uppercase.
	 * Mostly lowercase inputs have them in entirely lowercase
	 * 
	 * Random case alternation:
	 * the cases of letters in the suffix are randomized
	 * */
	public static String handleCase(String input, String suffix) {
		//Counting number of upper vs. lowercase letters, if the capitalization isn't alternating
		int numLowerCase = 0, numUpperCase = 0;
		
		//Counting how many of the last characters we've read in are of the same case as the current one
		int lowerCaseChain = 0, upperCaseChain = 0;
		
		Boolean caseChainOver3 = false;
		
		for (char c : input.toCharArray()) {
			if (Character.isLowerCase(c)) {
				numLowerCase++;
				upperCaseChain = 0;
				lowerCaseChain++;
				if (lowerCaseChain >= 3) {
					caseChainOver3 = true;
				}
			}
			else {
				numUpperCase++;
				lowerCaseChain = 0;
				upperCaseChain++;
				if (upperCaseChain >= 3) {
					caseChainOver3 = true;
				}
			}
		}
		
		//Randomizing the capital letters on the suffix if there isn't a chain of more than 3 letters of the same case 
		if (caseChainOver3) {
			if (numUpperCase > numLowerCase) {
				return suffix.toUpperCase();
			}
			else {
				return suffix.toLowerCase();
			}
		}
		else {
			
			//Detecting if the person is typing in alternating capital letters as if they're being sarcastic on Reddit,
			//and randomizing the capitalization of the pig latin ending if so
			suffix = suffix.toLowerCase();
			double random = 0.0;
			for (int i = 0; i < suffix.length(); i++)
			{
				random = Math.random();
				if (random > 0.5) {
					try {
						suffix = replaceChar(suffix, i, Character.toUpperCase(suffix.charAt(i)));
					}
					catch (Exception e)
					{
						System.out.println("Looks like there's a problem with replaceChar");
					}
				}
				
			}
			return suffix;
		}
	}
	/*
	 * Returns a string where the character at the given replaceIndex is replaced with the given character newChar
	 * */
	public static String replaceChar(String input, int replaceIndex, char newChar) {
		if (replaceIndex == 0) {
			input = input.substring(1);
			return (newChar + input);
		}
		else if (replaceIndex == (input.length() - 1)) {
			input = input.substring(0,(input.length() - 1));
			return (input + newChar);
		}
		else
		{
			String first = input.substring(0,(replaceIndex - 1));
			String last = input.substring(replaceIndex + 1);
			return (first + newChar + last);
		}
		
	}
	
	/*
	 * Returns true if input has any number or special character
	 * */
	public static Boolean hasNonLetter(String input) {
		return (hasNumber(input) || hasSpecChar(input));
	}
	
	/*
	 * Returns true if input has any number in it
	 * */
	public static Boolean hasNumber(String input) {
		return (checkASCIIRange(input, 48,57));
	}
	
	/*
	 * Returns true if input has any special, non-alphanumeric characters in it
	 * */
	public static Boolean hasSpecChar(String input) {
		return (checkASCIIRange(input, 33, 38)
				|| checkASCIIRange(input, 40, 47)
				|| checkASCIIRange(input, 58, 64)
				|| checkASCIIRange(input, 91, 96)	
				|| checkASCIIRange(input, 123, 126));
	}
	
	/*
	 * Returns true if input contains any characters with ASCII values that fall within the given range
	 * */
	public static Boolean checkASCIIRange(String input, int start, int end) {
		for (int i = start; i <= end; i++) {
			if (input.indexOf((char) i) != -1){
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Returns true if input contains any vowels
	 * */
	public static Boolean isVowel(char c) {
		c = Character.toLowerCase(c);
		switch (c) {
		case 'a':
		case 'e':
		case 'i':
		case 'o':
		case 'u':
			return true;
		}
		return false;
	}
}
