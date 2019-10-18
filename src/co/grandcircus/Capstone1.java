package co.grandcircus

import java.util.Scanner;
import java.util.Vector;

public class Capstone1 {
	public static Scanner scn = new Scanner(System.in);
	public static void main(String[] args) {
		StringBuilder s = new StringBuilder();
		Vector<StringBuilder> strings = new Vector<StringBuilder>();
		char repeatChar = ' ';
		do {
			System.out.println("Type in something to be translated to Pig Latin:");
			getInput(strings);
			
			
			
			
		} while (repeatChar != 'n');
	}
	public static void getInput(Vector<StringBuilder> inputArr) {
		StringBuilder temp = new StringBuilder();
		StringBuilder nextLine;
		Boolean done = false;
		while (!done) {
			nextLine = new StringBuilder(scn.next());
			inputArr.add(nextLine);
			if (inputArr.lastElement().toString().endsWith("\n")) {
				done = true;
			}
		}
		
		
		
	}
	public static StringBuilder toPigLatin(StringBuilder input) {
		input.append(input.charAt(0));
		input.deleteCharAt(0);
		input.append("ay");
		return input;
	}
}
