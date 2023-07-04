/**Generic Node
*
*@author Idan Calvo
*@version 1.0
*/

public class Node<T> {

//#Fields	
	private T data;
	private Node<T> next;

//#Constructor
	public Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}

	public Node (T data) {
		this(data, null);
	}

//#Methods
	@Override
	public String toString () {
		if (data==null) {
			return "";
		}
		return data.toString();
	}
	
	public T getData() {
		return this.data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getNext() {
		return this.next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

}
