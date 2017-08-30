package algorithms.stack;

import java.util.Scanner;

public class ReverseStringUsingStack {
	private char[] stack;
	private int top = -1;

	public ReverseStringUsingStack(int size) {
		stack = new char[size];
	}

	public void push(char ch) {
		if (top < stack.length - 1) {
			stack[++top] = ch;
		} else {
			System.out.println("Stack is Full");
		}
	}

	public char pop() {
		if (top > -1) {
			char vale = stack[top];
			top--;
			return vale;
		} else {
			System.out.println("Stack underflow");
			return 'Ω';
		}
	}
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		try {
			System.out.println("Enter the String : ");
			String enteredString = keyboard.nextLine();
			if (null != enteredString && !enteredString.isEmpty()) {
				ReverseStringUsingStack stack = new ReverseStringUsingStack(enteredString.length());
				char[] charArray = enteredString.toCharArray();
				for (char c : charArray) {
					stack.push(c);
				}
				char[] reverseChar = new char[enteredString.length()];
				char popedChar = stack.pop();
				int count = -1;
				while (popedChar != 'Ω') {
					reverseChar[++count] = popedChar;
					popedChar = stack.pop();
				}
				String reveString = new String(reverseChar);
				System.out.println("Reverse :" + reveString);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			keyboard.close();
		}
	}

}
