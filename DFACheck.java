import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DFACheck {
	public static void main(String[] args) {
		// maybe change to not equaling 2
		if (args.length < 2) {
			System.out.println("DFACheck: invalid usage - the program must be given two files as input");
			System.exit(1);
		}
		dfaStructure dfaStruct = new dfaStructure(args[0]);
		boolean done = false;
		try {
			Scanner fileInput = new Scanner(new FileInputStream(args[1]));
			String content = null;
			while (fileInput.hasNextLine()) {
				dfaStruct.currentState = dfaStruct.startState;
				done = false;
				content = fileInput.nextLine();
				for (int i = 0; i < content.length(); i++) {
					char c = content.charAt(i);
					for (int j = 0; j < dfaStruct.delta.size(); j++) {
						if (dfaStruct.delta.get(j).getFirst().equals(dfaStruct.currentState)
								&& dfaStruct.delta.get(j).getSecond().equals(c)) {
							dfaStruct.currentState = dfaStruct.delta.get(j).getThird();
							break;
						}
					}
				}
				for (int i = 0; i < dfaStruct.endStates.size(); i++) {
					if (dfaStruct.currentState.equals(dfaStruct.endStates.get(i))) {
						System.out.println(content + " accepted");
						done = true;
						break;
					}
				}
				if (done == false) {
					System.out.println(content + " rejected");
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("DFACheck: the file '" + args[0] + "' could not be opened");
		}
	}
}