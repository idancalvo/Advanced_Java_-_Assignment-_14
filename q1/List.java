/**Generic List
*
*@author Idan Calvo
*@version 1.0
*/
public class List<T> {

//#Fields	
	private Node<T> head;
	private Node<T> tail;

//#Constructor
	public List() {
		this.head = null;
		this.tail = null;
	}

//#Methods
	
	//add node to the end of the list
	public void add(T data) {
		Node<T> tempNode = new Node<T>(data);
		if (this.head == null) {
			this.head = tempNode;
		} else {
			(this.tail).setNext(tempNode);
		}
		this.tail = tempNode;
	}

	//remove node from the top of the list
	public T remove () throws EmptyListException{
		if(this.head == null) {
			throw new EmptyListException("The list is empty");		
		}
		else {
			T tempData  = (this.head).getData();
			if (this.head == this.tail) {
				this.tail = null;
			}
			this.head = (this.head).getNext();
			return tempData;
		}
	}

	@Override
	public String toString() {
		String ans = new String("[");
		Node<T> tempNode = this.head;
		while (tempNode != null) {
			if (tempNode != this.head) {
				ans +=  ", ";
			}
			ans += tempNode.toString();
			tempNode = tempNode.getNext();
		}
		ans += ("]");
		return ans;
	}

	public Node<T> getHead() {
		return this.head;
	}

	public Node<T> getTail() {
		return this.tail;
	}

}
