package algorithms.stack;

import java.util.Scanner;

public class TwoStacks {
	private int[] array;
	private int top1;
	private int top2;
	private int size;

	public TwoStacks(int size) {
		this.size = size;
		array = new int[this.size];
		top1 = -1;
		top2 = this.size - 1;
	}
	public void push1(int value) {
		if (top1 < size - 1 && top1 < top2) {
			array[++top1] = value;
		} else {
			System.out.println("Stack 1 is Full");
		}
	}

	public void push2(int value) {
		if (top2 <= size - 1 && top2 > top1) {
			array[top2--] = value;
		} else {
			System.out.println("Stack 2 is Full");
		}
	}

	public int pop1() {
		if (top1 > -1 && top1 <= top2) {
			int value = array[top1];
			array[top1] = 0;
			top1 -= 1;
			return value;
		} else {
			System.out.println("Stack 1 is empty");
			return -1;
		}
	}

	public int pop2() {
		if (top2 < size - 1 && top2 >= top1) {
			top2 += 1;
			int value = array[top2];
			array[top2] = 0;
			return value;
		} else {
			System.out.println("Stack 2 is Empty");
			return -1;
		}
	}
	public static void main(String[] args) {
		System.out.println("Enter size of Array : ");
		Scanner keyboard = new Scanner(System.in);
		try {
			int size = keyboard.nextInt();
			TwoStacks stacks = new TwoStacks(size);
			for (;;) {
				stacks.print();
				System.out.println("Enter 1 to Push");
				System.out.println("Enter 2 to Pop");
				System.out.println("Enter 3 to Exit");
				int option = keyboard.nextInt();
				switch (option) {
				case 1:
					System.out.println("Enter 1 to Push to First stack");
					System.out.println("Enter 2 to Push to Second stack");
					int stack = keyboard.nextInt();
					if (stack >= 1 && stack <= 2) {
						System.out.println("Enter the Value :");
						int value = keyboard.nextInt();
						if (stack == 1) {
							stacks.push1(value);
						} else if (stack == 2) {
							stacks.push2(value);
						}
					}
					break;
				case 2:
					System.out.println("Enter 1 to Pop to First stack");
					System.out.println("Enter 2 to Pop to Second stack");
					stack = keyboard.nextInt();
					if (stack >= 1 && stack <= 2) {
						int value = -10000;
						if (stack == 1) {
							value = stacks.pop1();
						} else if (stack == 2) {
							value = stacks.pop2();
						}
						System.out.println("Poped Value : " + value);
					}
					break;
				default:
					System.exit(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			keyboard.close();
		}
	}

	private void print() {
		System.out.println("**************************");
		for (int i = 0; i < array.length; i++) {
			System.out.print(" " + array[i]);
		}
		System.out.println("\n**************************");
	}
}
