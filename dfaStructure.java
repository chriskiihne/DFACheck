import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class dfaStructure {
	// all possible states of DFA
	ArrayList<String> states = new ArrayList<String>();
	// all possible input characters in DFA
	ArrayList<Character> inputAlphabet = new ArrayList<Character>();
	// all possible valid end states of DFA
	public ArrayList<String> endStates = new ArrayList<String>();
	// starting state of DFA
	public String startState;
	// current state of DFA arrived at via inputAlphabets path
	public String currentState;
	// temporary array to hold transition function before custom object parses it
	ArrayList<String> deltaString = new ArrayList<String>();
	// custom triplet class to hold each part of transition function in correct area
	public ArrayList<Triplet<String, Character, String>> delta = new ArrayList<Triplet<String, Character, String>>();

	public dfaStructure(String args) {
		try {
			Scanner dfaInput = new Scanner(new FileInputStream(args));
			String content = null;
			while (dfaInput.hasNextLine()) {
				content = dfaInput.nextLine();
				content = dfaInput.nextLine();
				while (!(content.contains("% Sigma"))) {
					states.add(content);
					content = dfaInput.nextLine();
				}
				content = dfaInput.nextLine();
				while (!(content.contains("% F"))) {
					inputAlphabet.add(content.charAt(0));
					content = dfaInput.nextLine();
				}
				content = dfaInput.nextLine();
				while (!(content.contains("% Q0"))) {
					endStates.add(content);
					content = dfaInput.nextLine();
				}
				content = dfaInput.nextLine();
				startState = content;
				content = dfaInput.nextLine();
				content = dfaInput.nextLine();
				while (dfaInput.hasNextLine()) {
					deltaString.add(content);
					content = dfaInput.nextLine();
				}
			}
			deltaString.add(content);
		} catch (FileNotFoundException e) {
			System.out.println("DFACheck: the file '" + args + "' could not be opened\n");
			System.exit(1);
		}
		convertArrayToTriplet();
		currentState = startState;
	}

//make it work with strings instead of charAt
	private void convertArrayToTriplet() {
		for (int i = 0; i < deltaString.size(); i++) {
			String currentString = deltaString.get(i);
			String[] arrCurrentString = currentString.split(" ");
			Triplet<String, Character, String> deltaToAdd = new Triplet<String, Character, String>(arrCurrentString[0],
					arrCurrentString[1].charAt(0), arrCurrentString[2]);
			delta.add(deltaToAdd);
		}
	}

	public void getInfo() {
		System.out.println(states.toString());
		System.out.println(inputAlphabet.toString());
		System.out.println(endStates.toString());
		System.out.println(startState);
		for (int i = 0; i < delta.size(); i++) {
			System.out.println(delta.get(i).toString());
		}
	}
}