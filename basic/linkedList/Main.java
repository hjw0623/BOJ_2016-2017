package basic.linkedList;

public class Main {

	public static void main(String[] args) {

		SingleLinkedList numbers = new SingleLinkedList();
		numbers.addLast(10);
		numbers.addLast(15);
		numbers.addLast(20);
		numbers.addLast(30);
		System.out.println(numbers.remove(1));

		System.out.println(numbers);
	}

}
