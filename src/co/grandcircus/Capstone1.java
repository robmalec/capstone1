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
	
	public static char getCheckRepeatChar() throws Exception {
		char r = scn.nextLine().charAt(0);
		if ((r != 'y') && (r != 'n')) {
			throw new Exception();
		}
		return r;
	}
	
	public static void getInput(String[] stringArray) {
		stringArray = scn.nextLine().split(" ");
	}
	
	public static String toPigLatin(String input) {
		if (!hasNonLetter(input)) {
			char temp = input.charAt(0);
			String suffix = isVowel(temp) ? "yay" : "ay";
			input = input.substring(1);
			input+= temp + handleCase(input, suffix);
		}
		return input;
	}
	
	public static String handleCase(String input, String suffix) {
		int numLowerCase = 0, numUpperCase = 0;
		for (char c : input.toCharArray()) {
			if (Character.isLowerCase(c)) {
				numLowerCase++;
			}
			else {
				numUpperCase++;
			}
		}
		if (numUpperCase > numLowerCase) {
			return suffix.toUpperCase();
		}
		else {
			return suffix.toLowerCase();
		}
	}
	
	public static StringBuilder toPigLatin(StringBuilder input) {
		input.append(input.charAt(0));
		input.deleteCharAt(0);
		input.append("ay");
		return input;
	}
	
	public static Boolean hasNonLetter(String input) {
		return (hasNumber(input) || hasSpecChar(input));
	}
	
	public static Boolean hasNumber(String input) {
		return (checkASCIIRange(input, 48,57));
	}
	public static Boolean hasSpecChar(String input) {
		return (checkASCIIRange(input, 33, 38)
				|| checkASCIIRange(input, 40, 47)
				|| checkASCIIRange(input, 58, 64)
				|| checkASCIIRange(input, 91, 96)	
				|| checkASCIIRange(input, 123, 126));
	}
	
	public static Boolean checkASCIIRange(String input, int start, int end) {
		for (int i = start; i <= end; i++) {
			if (input.indexOf((char) i) != -1){
				return true;
			}
		}
		return false;
	}
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
