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
				stringArray = scn.nextLine().toLowerCase().split(" ");
				
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
		char temp = input.charAt(0);
		String suffix = isVowel(temp) ? "yay" : "ay";
		input = input.substring(1);
		input+= temp + suffix;
		return input;
	}
	public static StringBuilder toPigLatin(StringBuilder input) {
		input.append(input.charAt(0));
		input.deleteCharAt(0);
		input.append("ay");
		return input;
	}
	public static Boolean isVowel(char c) {
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
