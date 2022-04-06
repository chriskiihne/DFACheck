public class Triplet<A, B, C> {

	private final A currentState;
	private final B currentInput;
	private final C endState;

	public Triplet(final A first, final B second, final C third) {
		currentState = first;
		currentInput = second;
		endState = third;
	}

	public A getFirst() {
		return currentState;
	}

	public B getSecond() {
		return currentInput;
	}

	public C getThird() {
		return endState;
	}

	@Override
	public String toString() {
		return currentState + ", " + currentInput + ", " + endState;
	}

}
