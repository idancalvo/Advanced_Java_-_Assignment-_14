
/**Main class to display: List,Person,Node and "max" method
*
*@author Idan Calvo
*@version 1.0
*/
import java.util.Scanner;

public class Main {

	static final int NUM_OF_STRING = 6;
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		example1();
		System.out.println("-------------------------------------");
		example2();
	}

	//example1 - "List of string" and "revers"
	public static void example1() {
		System.out.println("1.3 Displays a 'List' of 'string':\n");
		List<String> list1 = new List<String>();
		insert1(list1);
		System.out.println("\nThe list was created:");
		System.out.println(list1);

		System.out.println("\nNow we will present the reversed list:");
		reverseList(list1);
		System.out.println(list1);
	}

	//example2 - "List of Person" and "older Person"
	public static void example2() {
		System.out.println("1.4 Displays a 'List' of 'Person':");
		List<Person> list2 = new List<Person>();
		insert2(list2);
		System.out.println("\nThe list of Person:");
		System.out.println(list2);
		System.out.println("\nThe older Person is:\n" + max(list2));

	}
	
	//insert String to list1
	public static void insert1(List<String> list1) {
		System.out.println("You are asked to enter " + NUM_OF_STRING + " items to the list.");
		for (int i = 0; i < NUM_OF_STRING; i++) {
			System.out.print("Please insert string: ");
			list1.add(sc.nextLine());
		}
	}

	//revers list1
	public static <E> void reverseList(List<E> list1) {
		try {
			E temp = list1.remove();
			reverseList(list1);
			list1.add(temp);
		} catch (EmptyListException e) {
			return;
		}
	}

	//insert Person to list2
	private static void insert2(List<Person> list2) {
		Person p1 = new Person("joni", 123456, 1985);
		Person p2 = new Person("hila", 245879, 1996);
		Person p3 = new Person("yosi", 356891, 1957);
		Person p4 = new Person("momi", 945896, 1963);
		list2.add(p1);
		list2.add(p2);
		list2.add(p3);
		list2.add(p4);
	}

	// Generic method - Returns maximum value (must implement Comparable)
	public static <E extends Comparable<E>> E max(List<E> list1) {
		if (list1 != null) {
			Node<E> tempItem;
			Node<E> maxItem;
			maxItem = tempItem = list1.getHead();

			if (tempItem == null) {
				System.out.println("The list is empty");
				return null;
			} // if

			while (tempItem != null) {
				if ((tempItem.getData()).compareTo(maxItem.getData()) > 0) {
					maxItem = tempItem;
				}
				tempItem = tempItem.getNext();
			} // while

			return maxItem.getData();
		} // if

		else {
			return null;
		} // else
	}
}
