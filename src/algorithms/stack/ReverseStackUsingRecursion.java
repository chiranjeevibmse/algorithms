package algorithms.stack;

import java.util.Iterator;
import java.util.Stack;

public class ReverseStackUsingRecursion {
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(10);
		stack.push(20);
		stack.push(30);
		stack.push(40);
		stack.push(50);
		System.out.println("Stack :");
		Iterator<Integer> iterator = stack.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next() + ",");
		}
		System.out.println("\nReverse : ");
		ReverseStackUsingRecursion.reverse(stack);
	}

	public static void reverse(Stack<Integer> stack) {
		if (stack.empty()) {
			return;
		}
		int next = stack.pop();
		System.out.print(next + ",");
		reverse(stack);
	}
}
